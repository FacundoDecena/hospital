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
    public static int getTiempoEntreArribos(int tiempo,int tipo){
        double aux = random.nextDouble();
        switch(tipo){
            case 0:
                if(((tiempo%1440)>= 420 && (tiempo%1440)<=540)||((tiempo%1440)>= 1200 && (tiempo%1440)<=1320)){
                    if(aux<0.50)
                        return 10;
                    else{
                        if(aux<0.85)
                            return 20;
                        else
                            return 30;
                    }
                }
                else{
                    if(aux<0.30)
                        return 20;
                    else{
                        if(aux<0.70)
                            return 30;
                        else
                            return 40;
                    }
                }
            
            case 1:
                if(((tiempo%1440)>= 420 && (tiempo%1440)<=540)||((tiempo%1440)>= 1200 && (tiempo%1440)<=1320)){
                    if(aux<0.35)
                        return 40;
                    else
                        return 50;
                }
                else{
                    if(aux<0.25)
                        return 60;
                    else
                        return 70;
                }
            
            case 2:
                if(((tiempo%1440)>= 420 && (tiempo%1440)<=540)||((tiempo%1440)>= 1200 && (tiempo%1440)<=1320)){
                    if(aux<0.4)
                        return 60;
                    else
                        return 90;
                }
                else{
                    if(aux<0.5)
                        return 120;
                    else
                        return 180;
                }  
                
            default: 
                return -1;
        }
    }
    
    /**
     * Genera aleatoriamente el tiempo de duracion de servicio de pacientes con afeccion leve
     * @return tiempo de duracion de servicio de pacientes con afeccion leve
     */
    public static double getTiempoDuracionServicio(int tipo){
        switch(tipo){
            case 0: 
                return -30*Math.log(1-random.nextDouble());
                
            case 1: 
                return 10*(random.nextDouble()+1);
                
            case 2:
                double sumaVariables=0;
                for(int  i = 0; i<12 ; i++ )
                    sumaVariables+=random.nextDouble();
                double z0 = sumaVariables - 6;
                return z0*30+120;
                
            default: 
                return -1;
        }
    }
}

    