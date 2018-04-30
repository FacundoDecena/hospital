package hospital;

public class Servidor {
    private Item item;
    private boolean estado;
    private double tiempoOcioso;
    private double tiempoInicioOcio;
    private int tipo; //0 Residente, 1 General, 2 Especialista
    private int numero;

    /**
     * @param tipo 0 Residente, 1 General, 2 Especialista
     * @param numero numero del servidor 1-5
     */
    public Servidor(int tipo, int numero){
        item=null; //No hay items en el servidor
        estado=false; //Desocupado
        tiempoOcioso=0;//No hay tiempo Ocioso
        tiempoInicioOcio=0;//Inicio de Ocio en 0
        this.tipo = tipo;
        this.numero = numero;
    }

    public int getNumero(){
        return numero;
    }
    /**
     * @return Retorna el tipo de servidor
     */
    public int getTipo(){
        return tipo;
    }
    
    /**
     * @return Returns the item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item The item to set.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return Returns the estado.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado The estado to set.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return Returns the tiempoOcioso.
     */

    public double getTiempoOcioso() {
        return tiempoOcioso;
    }


    /**
     * @param tiempoOcioso The tiempoOcioso to set.
     */
    public void setTiempoOcioso(double tiempoOcioso) {
        this.tiempoOcioso = tiempoOcioso;
    }

    /**
     * @return Returns the tiempoInicioOcio.
     */

    public double getTiempoInicioOcio() {
        return tiempoInicioOcio;
    }

    /**
     * @param tiempoInicioOcio The tiempoInicioOcio to set.
     */
    public void setTiempoInicioOcio(double tiempoInicioOcio) {
        this.tiempoInicioOcio = tiempoInicioOcio;
    }
}
