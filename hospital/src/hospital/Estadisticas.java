package hospital;

public class Estadisticas {
    /**
    * Imprime por pantalla las estadisticas de la simulacion
    * @param tiempoEsperaCola suma de todos los tiempos de espera en cola de los items
    * @param tiempoTransito suma de todos los tiempos en el que los items estuvieron en el sistema
    * @param tiempoOcioso tiempo total en el que el servidor estuvo en desuso
    * @param tiempoFinSimulacion tiempo en el que termina la simulacion
    * @param cantidadItems cantidad de items que pasaron por el sistema
    */
    public static void calcularEstadisticas(double tiempoEsperaCola, double tiempoTransito, double tiempoOcioso, 
                                                    double tiempoFinSimulacion, int cantidadItems){
        int minuto;
        double segundo, minutos; 
        minutos = tiempoEsperaCola/cantidadItems;//Calculamos el tiempo promedio de espera en cola
        if (-0.00000001<minutos && minutos<0.00000001)//Corregimos el error de las variables de tipo double
            minutos = 0.0;
        System.out.printf("Tiempo promedio en cola: %.2f minutos\n",minutos);
        System.out.printf("Porcentaje de tiempo ocioso: %.2f",tiempoOcioso/tiempoFinSimulacion*100);//Calculamos el porcentaje de tiempo ocioso del servidor
        System.out.println(" %");
        minutos = tiempoTransito/cantidadItems;// Calculamos el tiempo medio de tránsito
        System.out.printf("Tiempo medio de transito: %.2f minutos\n",minutos);
    }
}
