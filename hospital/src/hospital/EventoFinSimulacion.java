package hospital;

public class EventoFinSimulacion extends Evento {
        
    /**
     * Constructor de la clase EventoFinSimulacion
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     */
    public EventoFinSimulacion(float tiempo,int tipo){
        super(2,tiempo,new Item(tiempo,tipo));
    }


    @Override
    public void planificarEvento(Servidor servidor,Queue queue){
        //No hacer nada
    }
}
