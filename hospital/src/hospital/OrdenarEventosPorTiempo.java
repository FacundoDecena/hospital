package hospital;

import java.util.Comparator;


public class OrdenarEventosPorTiempo implements Comparator<Evento>{

    /**
     * Metodo mediante el cual se ordena la fel
     */
    @Override
    public int compare(Evento evento1, Evento evento2) {
        if (evento1.getTiempo()- evento2.getTiempo() == 0.0){
            return evento2.getTipo()-evento1.getTipo();
        }
        else{
            if((evento1.getTiempo() - evento2.getTiempo()) < 0)
                return -1;
            else
                return 1;
        }
    }
   
}


