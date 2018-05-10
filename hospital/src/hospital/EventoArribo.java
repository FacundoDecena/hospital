package hospital;

public class EventoArribo extends Evento{
    
    /**
     * Constructor de la clase EventoArribo
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param tipo Tipo de arribo, leve, medio, grave
     * @param servidor numero de servidor
     */
    public EventoArribo(double tiempo, int tipo,Servidor servidor){
        super(0,tiempo,new Item(tiempo, tipo,servidor));
    } 
    /**
     * Metodo que procesa un evento de arribo
     * @param servidor servidor de la simulacion
     * @param queue cola de espera
     */
    @Override
    public void planificarEvento(Servidor servidor,Queue queue){
        if(servidor.isEstado())//Si el servidor esta ocupado, pone al item en  la cola
            queue.insertarCola(this.getItem());
        else{//Si no lo esta, atiende al evento y genera su salida (marca el servidor ocupado)
            servidor.setTiempoOcioso(servidor.getTiempoOcioso()+this.getTiempo()-servidor.getTiempoInicioOcio());//Setea el tiempo ocioso
            servidor.setEstado(true);//Marca el servidor como ocupado
            servidor.setItem(this.getItem());//Setea el item al que esta haciendo el servicio
            double tiempoServicioGenerado = GeneradorTiempos.getTiempoDuracionServicio(this.getItem().getTipo());//Generamos tiempo de servicio
            this.getItem().setTiempoDuracionServicio(tiempoServicioGenerado);
            servidor.setCantidadItems(servidor.getCantidadItems()+1);//Actualizamos cantidad de items
            EventoSalida es = new EventoSalida(this.getTiempo()+tiempoServicioGenerado,this.getItem());//Genera su salida
            Fel.getFel().insertarFel(es);
        }
        //Genera el proximo evento de arribo
        int tiempoEntreArribos = GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),this.getItem().getTipo());
        EventoArribo ea = new EventoArribo(this.getTiempo()+tiempoEntreArribos,this.getItem().getTipo(),null);
        Fel.getFel().insertarFel(ea);
    }
}
