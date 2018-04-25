package hospital;
import java.util.Random;


public class GeneradorTiempos {
	
    private static Random random;

    static{
        random=new Random(System.currentTimeMillis());
    }

    /**
     * Genera aleatoriamente el tiempo entre arribos
     * @return tiempo entre arribos
     */
    public static int getTiempoEntreArribos(){
        double aux;
        aux=random.nextDouble();
        if((aux)<0.4)
            return 4;
        else{
            if((aux)<0.7)
                return 5;
            else
                return 6;
        }         
    }
    
    /**
     * Genera aleatoriamente el tiempo de duracion de servicio
     * @return tiempo de duracion de servicio
     */
    public static int getTiempoDuracionServicio(){
        double aux;
        aux=random.nextDouble();
        if((aux)<0.5)
            return 3;
        else{
            if((aux)<0.9)
                return 4;
            else
                return 5;
        }
    }

}

    