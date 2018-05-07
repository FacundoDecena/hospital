package hospital;

public abstract class Evento {
    private int tipo;
    private double tiempo;
    private Item item;
    
    /**
     * Constructor de la clase evento
     * @param tipo tipo de evento -> 0: Arribo, 1:Fin de Servicio, 2: Fin de Simulacion
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param item item que participa del evento
     */
    public Evento(int tipo,double tiempo,Item item){
        this.tipo = tipo;
        this.tiempo = tiempo;
        this.item = item;
    }
    
    /**
     * Getter del item del evento
     * @return item del evento
     */
    public Item getItem() {
        return item;
    }
    
    /**
     * Setter del item del evento
     * @param item del evento
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Getter del tiempo del evento
     * @return tiempo en el que transcurre el evento
     */
    public double getTiempo() {
        return tiempo;
    }
    
    /**
     * Setter del tiempo del evento
     * @param tiempo en el que transcurre el evento
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    
    /**
     * Getter del tipo del evento
     * @return tipo del evento. 0: Arribo, 1:Fin de Servicio, 2: Fin de Simulacion
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * Setter del tipo del evento
     * @param tipo del evento. 0: Arribo, 1:Fin de Servicio, 2: Fin de Simulacion
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return un string que contiene toda la informacion del evento 
     */
    @Override
    public String toString(){
        return "Tipo: "+tipo+"\n"+"Tiempo: "+tiempo+"\n"+item.toString()+"\n";
    }
    
    public abstract void planificarEvento(Servidor servidor,Queue queue);

}
