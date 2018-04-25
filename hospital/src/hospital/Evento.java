package hospital;

public abstract class Evento {
    private int tipo;
    private float tiempo;
    private Item item;
    
    /**
     * Constructor de la clase evento
     * @param tipo tipo de evento -> 0: Arribo, 1:Fin de Servicio, 2: Fin de Simulacion
     * @param tiempo instante de tiempo en el que se encuentra la simulacion al momento de generar el evento
     * @param item item que participa del evento
     */
    public Evento(int tipo,float tiempo,Item item){
        this.tipo = tipo;
        this.tiempo = tiempo;
        this.item = item;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public int getTipo() {
        return tipo;
    }

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
