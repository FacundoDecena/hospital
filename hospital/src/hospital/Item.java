package hospital;

public class Item  {
    private int numero;
    private double tiempoArribo;
    private double tiempoDuracionServicio;
    
    private static double tiempoEsperaColaLeve1 = 0;
    private static double tiempoEsperaColaLeve2 = 0;
    private static double tiempoEsperaColaMedio = 0;
    private static double tiempoEsperaColaGrave1 = 0;
    private static double tiempoEsperaColaGrave2 = 0;
    
    private static double tiempoTransitoLeve1 = 0;
    private static double tiempoTransitoLeve2 = 0;
    private static double tiempoTransitoMedio = 0;
    private static double tiempoTransitoGrave1 = 0;
    private static double tiempoTransitoGrave2 = 0;
    
    private static int cantidadLeves1 = 0;
    private static int cantidadLeves2 = 0;
    
    private static int cantidadMedios = 0;
    
    private static int cantidadGraves1 = 0;
    private static int cantidadGraves2 = 0;
    
    private int tipo; //0: leve, 1: medio, 2:grave
    
    /**
     * Constructor de la clase item
     * @param tiempoArribo tiempo en el que arriba el item 
     * @param tipo tipo de elemento
     */
    public Item(double tiempoArribo,int tipo, int servidor){
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;
        this.numero = cantidadLeves1 + cantidadLeves2 + cantidadMedios + cantidadGraves1 + cantidadGraves2;
        this.tipo = tipo;
        switch(this.tipo){
            case 0:
                if(servidor == 1)
                    cantidadLeves1++;
                else
                    cantidadLeves2++;
                break;
            case 1: 
                cantidadMedios++;
                break;
            case 2:
                if(servidor == 4)
                    cantidadGraves1++;
                else
                    cantidadGraves2++;
                break;
        }
    }


    /**
    * @return Returns the cantidadLeves
    */
    public static int getCantidadLeves() {
        return cantidadLeves;
    }

    /**
    * @param cantidadLeves The cantidadLeves to set.
    */
    public static void setCantidadLeves(int cantidadLeves) {
        Item.cantidadLeves = cantidadLeves;
    }
    
    /**
    * @return Returns the cantidadMedios
    */
    public static int getCantidadMedios() {
        return cantidadMedios;
    }

    /**
    * @param cantidadMedios The cantidadMedios to set.
    */
    public static void setCantidadMedios(int cantidadMedios) {
        Item.cantidadMedios = cantidadMedios;
    }
    
    /**
    * @return Returns the cantidadGraves
    */
    public static int getCantidadGraves() {
        return cantidadGraves;
    }

    /**
    * @param cantidadGraves The cantidadGraves to set.
    */
    public static void setCantidadGraves(int cantidadGraves) {
        Item.cantidadGraves = cantidadGraves;
    }
    

    /**
    * @return Returns the tiempoEsperaCola.
    */
    public static double getTiempoEsperaCola() {
        return tiempoEsperaCola;
    }

    /**
    *  tiempoEsperaCola  tiempoEsperaCola to set.
    * @param tiempoActual 
    * @param tiempoDuracionServicio 
    * @param tiempoArribo
    */
    public static void setTiempoEsperaCola(double tiempoActual, double tiempoDuracionServicio, double tiempoArribo) {
        tiempoEsperaCola += tiempoActual - tiempoArribo - tiempoDuracionServicio;
    }

    /**
    * @return Returns the tiempoTransito.
    */
    public static double getTiempoTransito() {
        return tiempoTransito;
    }

    /**
    * tiempoTransito The tiempoTransito to set.
    * @param tiempoActual
    * @param tiempoArribo
    */
    public static void setTiempoTransito(double tiempoActual, double tiempoArribo) {
        tiempoTransito += (tiempoActual - tiempoArribo);
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
       return "Número: "+numero+"\n"+"Tiempo de arribo: "+tiempoArribo+"\n"+"Duracion del servicio: "+tiempoDuracionServicio+"\n";
    }
}
