package hospital;

public class Item  {
    private int numero;
    private int servidor;
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
     * @param servidor
     */
    public Item(double tiempoArribo,int tipo, int servidor){
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;
        this.numero = cantidadLeves1 + cantidadLeves2 + cantidadMedios + cantidadGraves1 + cantidadGraves2;
        this.tipo = tipo;
        this.servidor = servidor;
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
    * @return Retorna la cantidad de pacientes segun el sevidor, el numero del servidor esta mal retorna -1
    */
    public static int getCantidad(int servidor) {
        switch(servidor){
            case 1: return cantidadLeves1;
            case 2: return cantidadLeves2;
            case 3: return cantidadMedios;
            case 4: return cantidadGraves1;
            case 5: return cantidadGraves2;
            default: return -1;
        }
        
    }

    /**
    * @param cantidadLeves The cantidadLeves to set.
    */
    public static void setCantidadLeves(int servidor, int cantidad) {
        switch(servidor){
            case 1: Item.cantidadLeves1 = cantidad;break;
            case 2: Item.cantidadLeves2 = cantidad;break;
            case 3: Item.cantidadMedios = cantidad;break;
            case 4: Item.cantidadGraves1 = cantidad;break;
            case 5: Item.cantidadGraves1 = cantidad;break;
            default: System.err.println("Le pifiaste crack");
        }
        
    }

    /**
     * @param servidor
    * @return Returns the tiempoEsperaCola dependiendo del servidor
    */
    public static double getTiempoEsperaCola(int servidor) {
        switch(servidor){
            case 1: return tiempoEsperaColaLeve1;
            case 2: return tiempoEsperaColaLeve2;
            case 3: return tiempoEsperaColaMedio;
            case 4: return tiempoEsperaColaGrave1;
            case 5: return tiempoEsperaColaGrave2;
            default: return -1;
        }
    }

    /**
    *  tiempoEsperaCola  tiempoEsperaCola to set.
    * @param tiempoActual 
    * @param tiempoDuracionServicio 
    * @param tiempoArribo
     * @param servidor numero de servidor
    */
    public static void setTiempoEsperaCola(double tiempoActual, double tiempoDuracionServicio, double tiempoArribo,int servidor) {
        switch(servidor){
            case 1: tiempoEsperaColaLeve1 += tiempoActual - tiempoArribo - tiempoDuracionServicio;break;
            case 2: tiempoEsperaColaLeve2 += tiempoActual - tiempoArribo - tiempoDuracionServicio;break;
            case 3: tiempoEsperaColaMedio += tiempoActual - tiempoArribo - tiempoDuracionServicio;break;
            case 4: tiempoEsperaColaGrave1 += tiempoActual - tiempoArribo - tiempoDuracionServicio;break;
            case 5: tiempoEsperaColaGrave2 += tiempoActual - tiempoArribo - tiempoDuracionServicio;break;
            default: System.err.println("Le pifiaste crack");
        }
    }

    /**
    * @return Returns the tiempoTransito.
    */
    public static double getTiempoTransito(int servidor) {
        switch(servidor){
            case 1: return tiempoTransitoLeve1;
            case 2: return tiempoTransitoLeve2;
            case 3: return tiempoTransitoMedio;
            case 4: return tiempoTransitoGrave1;
            case 5: return tiempoTransitoGrave2;
            default: return -1;
        }
    }

    /**
    * tiempoTransito The tiempoTransito to set.
    * @param tiempoActual
    * @param tiempoArribo
    */
    public static void setTiempoTransito(double tiempoActual, double tiempoArribo, int servidor) {
        switch(servidor){
            case 1: tiempoTransitoLeve1 += (tiempoActual - tiempoArribo);break;
            case 2: tiempoTransitoLeve2 += (tiempoActual - tiempoArribo);break;
            case 3: tiempoTransitoMedio += (tiempoActual - tiempoArribo);break;
            case 4: tiempoTransitoGrave1 += (tiempoActual - tiempoArribo);break;
            case 5: tiempoTransitoGrave2 += (tiempoActual - tiempoArribo);break;
            default: System.err.println("Le pifiaste crack");
        }
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
