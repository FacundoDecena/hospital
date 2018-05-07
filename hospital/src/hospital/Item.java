package hospital;

public class Item  {
    private int numero;
    private int servidor;
    private double tiempoArribo;
    private double tiempoDuracionServicio;
    private static int cantidadItems;
    private int tipo; //0: leve, 1: medio, 2:grave
    
    /**
     * Constructor de la clase item
     * @param tiempoArribo tiempo en el que arriba el item 
     * @param tipo tipo de item. 0: leve, 1: medio, 2:grave
     */
    public Item(double tiempoArribo,int tipo){
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;
        cantidadItems++;
        this.numero = cantidadItems;
        this.tipo = tipo;
    }

    /**
    * @return Returns the numero.
    */
    public int getNumero() {
        return numero;
    }

    /**
    * @param numero The numero to set.
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
    * @return Returns the tiempoArribo.
    */
    public double getTiempoArribo() {
        return tiempoArribo;
    }

    /**
    * @param tiempoArribo The tiempoArribo to set.
    */
    public void setTiempoArribo(double tiempoArribo) {
        this.tiempoArribo = tiempoArribo;
    }

    /**
    * @return Returns the tiempoDuracionServicio.
    */
    public double getTiempoDuracionServicio() {
        return tiempoDuracionServicio;
    }

    /**
    * @param tiempoDuracionServicio The tiempoDuracionServicio to set.
    */
    public void setTiempoDuracionServicio(double tiempoDuracionServicio) {
        this.tiempoDuracionServicio = tiempoDuracionServicio;
    }
    
    /**
    * @return Returns the tipo.
    */
    public int getTipo() {
        return tipo;
    }

    /**
    * @param tipo The tipo to set.
    */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    /**
     * 
     * @return un string que contiene toda la informacion del item  
     */
    @Override
    public String toString(){
       return "Número: "+numero+"\n"+"Tiempo de arribo: "+tiempoArribo+"\n"+"Duracion del servicio: "+tiempoDuracionServicio+"\n"+"Tipo: "+tipo+"\n";
    }
}
