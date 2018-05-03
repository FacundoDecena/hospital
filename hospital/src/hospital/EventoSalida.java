package hospital;

public class EventoSalida extends Evento {

    /**
     * Constructor de la clase EventoSalida
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param item item que participa del evento
     */
    public EventoSalida(double tiempo,Item item){
        super(1,tiempo,item);
    }
    /**
    * Metodo que procesa un evento de salida
    * @param servidor servidor de la simulacion
    * @param queue cola de espera
    */   
    @Override
    public void planificarEvento(Servidor servidor,Queue queue) {
        Item item = this.getItem();
        if(queue.HayCola()){//Si hay cola, atiende el servido atiende al tope de la cola, generando su evento de salida
            this.setItem(queue.suprimirCola());
            double tiempoServicioGenerado = GeneradorTiempos.getTiempoDuracionServicio(item.getTipo());
            item.setTiempoDuracionServicio(tiempoServicioGenerado);
            servidor.setCantidadItems(servidor.getCantidadItems()+1);
            EventoSalida eventoSalida = new EventoSalida(this.getTiempo()+tiempoServicioGenerado,this.getItem());
            Fel.getFel().insertarFel(eventoSalida);
        }
        else{//Si no hay cola, marca el servidor como desocupado
            servidor.setEstado(false);
            servidor.setTiempoInicioOcio(this.getTiempo());
        }
        //Guarda los tiempos en cola y en transito
        servidor.setTiempoEsperaCola(this.getTiempo() - item.getTiempoArribo() - item.getTiempoDuracionServicio());
        servidor.setTiempoTransito(this.getTiempo()- item.getTiempoArribo());
    }
}