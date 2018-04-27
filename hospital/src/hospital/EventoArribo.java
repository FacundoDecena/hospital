package hospital;

public class EventoArribo extends Evento{
    
    /**
     * Constructor de la clase EventoArribo
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     */
    public EventoArribo(double tiempo, int tipo){
        super(0,tiempo,new Item(tiempo, tipo));
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
            servidor.setTiempoOcioso(servidor.getTiempoOcioso()+this.getTiempo()-servidor.getTiempoInicioOcio());
            servidor.setEstado(true);
            double tiempoServicioGenerado = GeneradorTiempos.getTiempoDuracionServicio(this.getTipo());
            super.getItem().setTiempoDuracionServicio(tiempoServicioGenerado);
            EventoSalida es = new EventoSalida(this.getTiempo()+tiempoServicioGenerado,this.getItem());
            Fel.getFel().insertarFel(es);
        }
        //Genera el proximo evento de arribo
        int tiempoEntreArribos = GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),this.getTipo());
        EventoArribo ea = new EventoArribo(this.getTiempo()+tiempoEntreArribos,this.getTipo());
        Fel.getFel().insertarFel(ea);
    }
}
