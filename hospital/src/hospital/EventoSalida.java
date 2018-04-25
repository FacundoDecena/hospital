package hospital;

public class EventoSalida extends Evento {

    /**
     * Constructor de la clase EventoSalida
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param item item que participa del evento
     */
    public EventoSalida(float tiempo,Item item){
        super(1,tiempo,item);
    }
    /**
    * Metodo que procesa un evento de salida
    * @param servidor servidor de la simulacion
    * @param queue cola de espera
    */   
    @Override
    public void planificarEvento(Servidor servidor,Queue queue) {
        Item item = super.getItem();
        if(queue.HayCola()){//Si hay cola, atiende el servido atiende al tope de la cola, generando su evento de salida
            super.setItem(queue.suprimirCola());
            float tiempoServicioGenerado = GeneradorTiempos.getTiempoDuracionServicio();
            item.setTiempoDuracionServicio(tiempoServicioGenerado);
            EventoSalida eventoSalida = new EventoSalida(super.getTiempo()+tiempoServicioGenerado,super.getItem());
            Fel.getFel().insertarFel(eventoSalida);
        }
        else{//Si no hay cola, marca el servidor como desocupado
            servidor.setEstado(false);
            servidor.setTiempoInicioOcio(super.getTiempo());
        }
        //Guarda los tiempos en cola y en transito
        Item.setTiempoEsperaCola(super.getTiempo(), item.getTiempoDuracionServicio(), item.getTiempoArribo());
        Item.setTiempoTransito(super.getTiempo(), item.getTiempoArribo());
    }
}