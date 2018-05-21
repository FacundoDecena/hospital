package hospital;

public class Estadisticas {
    /**
    * Imprime por pantalla las estadisticas de la simulacion
    * @param tiempoEsperaCola suma de todos los tiempos de espera en cola de los items 
    * @param cantidadItems cantidad de items que pasaron por el sistema
    * @return  tiempo Mmdio en cola
    */
    public static double calcularTiempoMedioCola(double tiempoEsperaCola, int cantidadItems){
        double minutos; 
        minutos = tiempoEsperaCola/cantidadItems;//Calculamos el tiempo promedio de espera en cola
        if (-0.00000001<minutos && minutos<0.00000001)//Corregimos el error de las variables de tipo double
            minutos = 0.0;
       return minutos;
    }
    /**
     * 
     * @param tiempoOcioso tiempo total en el que el servidor estuvo en desuso
     * @param tiempoFinSimulacion tiempo en el que termina la simulacion
     * @return 
     */
    public static double calcularPorcentajeOcioso(double tiempoOcioso, double tiempoFinSimulacion){
        return ((tiempoOcioso/tiempoFinSimulacion)*100);    
    }
    
    
    /**
     * 
     * @param cantidadCorridas cantidad de veces que se corre el programa
     * @param medias arreglo de las medias de cada corrida
     * @return media
     */
    public static double calcularMedia(int cantidadCorridas,double medias[]){
        double suma = 0;
        for(int i = 0;i<cantidadCorridas;i++){
            suma += medias[i];
        }
        return suma/cantidadCorridas;
    }
    
    
    /**
     * 
     * @param cantidadCorridas cantidad de veces que se corre el programa
     * @param media media de las medias
     * @param medias arreglo de las medias de cada corrida
     * @return desviacion estandar 
     */
    public static double calcularDesviacionEstandar(int cantidadCorridas,double media, double medias[]){
        double suma = 0;
        for(int i = 0;i<cantidadCorridas;i++){
            suma += (medias[i]-media)*(medias[i]-media);
        }
        return Math.sqrt(suma/(cantidadCorridas-1));
    }
    
}
