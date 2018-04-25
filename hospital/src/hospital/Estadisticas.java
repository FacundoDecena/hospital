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
    public static void calcularEstadisticas(float tiempoEsperaCola, float tiempoTransito, float tiempoOcioso, 
                                                    float tiempoFinSimulacion, int cantidadItems){
        int minuto;
        float segundo; 
        minuto = (int) tiempoEsperaCola/cantidadItems;
        segundo = (tiempoEsperaCola/cantidadItems%1)*60;
        System.out.printf("Tiempo promedio en cola: %d minutos con %.2f segundos\n",minuto,segundo);
        System.out.printf("Porcentaje de tiempo ocioso: %.2f",tiempoOcioso/tiempoFinSimulacion*100);
        System.out.println(" %");
        minuto = (int) tiempoTransito/cantidadItems;
        segundo = (tiempoTransito/cantidadItems%1)*60;
        System.out.printf("Tiempo medio de transito: %d minutos con %.2f segundos\n",minuto,segundo);
    }
}
