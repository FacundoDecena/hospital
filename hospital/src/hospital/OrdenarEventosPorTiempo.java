package hospital;

import java.util.Comparator;


public class OrdenarEventosPorTiempo implements Comparator<Evento>{

    /**
     * Metodo mediante el cual se ordena la fel
     */
    @Override
    public int compare(Evento evento1, Evento evento2) {
        if ((int)evento1.getTiempo()- (int)evento2.getTiempo()==0){
            return evento2.getTipo()-evento1.getTipo();
        }
        else return (int)evento1.getTiempo()- (int)evento2.getTiempo();
    }
}
