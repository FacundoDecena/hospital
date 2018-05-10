package hospital;

public class EventoFinSimulacion extends Evento {
        
    /**
     * Constructor de la clase EventoFinSimulacion
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param tipo tipo de evento
     */
    public EventoFinSimulacion(double tiempo,int tipo){
        super(2,tiempo,new Item(tiempo,tipo, new Servidor(0)));
    }


    @Override
    public void planificarEvento(Servidor servidor,Queue queue){
        //No hacer nada
    }
}
