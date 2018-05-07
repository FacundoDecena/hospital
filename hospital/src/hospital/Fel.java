package hospital;

import java.util.Iterator;
import java.util.LinkedList;

public class Fel{
    private static Fel fel;
    private LinkedList lista;
    
    /**
    * Constructor de la FEL
    */
    private Fel(){
        lista=new LinkedList();	
    }
    
    /*
    *Si existe, retorna la fel y si no la crea. 
    */
    public static Fel getFel(){
            if(fel==null)
                    fel=new Fel();
            return(fel);
    }
    
    /**
    * Inserta un evento en la fel y la ordena segun el tiempo
    * @param e evento a insertar en la fel
    */
    public void insertarFel(Evento e){
        lista.add(e);
        lista.sort(new OrdenarEventosPorTiempo());
    }
    
    /**
    * Elimina el primer evento de la fel y lo retorna
    * @return evento eliminado
    */
    public Evento suprimirFel(){
        return (Evento) lista.remove();
    }
    
    /**
    * Imprime por pantalla los eventos contenidos en la FEL
    */
    public void mostrarFel(){
        System.out.println("-------------------------------------------------");
        Iterator i = lista.listIterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * @return Returns the lista.
     */
    public LinkedList getLista() {
            return lista;
    }
	
}
