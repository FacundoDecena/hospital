package hospital;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue {
    private int cantidadItems;
    private LinkedList cola;

    /**
     * Genera una cola de tipo LinkedList con cantidad de items = 0
     */
    public Queue(){
        cola = new LinkedList();
        cantidadItems = 0;
    }

    /**
     * Inserta un item a la cola
     * @param item item a insertar
     */
    public void insertarCola(Item item){
        cantidadItems++;
        cola.add(item);		
    }

    /**
     * Elimina el primer elemento de la cola
     * @return el primer item
     */
    public Item suprimirCola(){
       cantidadItems--;
       return (Item)cola.remove();
    }

    /**
     * 
     * @return true si hay cola, false si no hay
     */
    public boolean HayCola(){
        return !cola.isEmpty();
    }
    
    /**
     * 
     * @return cantidad de elementos en la cola
     */
    public int cantidadElementos(){
        return cantidadItems;
    }

    /**
     * Muestra los elementos de la cola
     */
    public void mostrarCola(){
        System.out.println("/////////////////////////////////////////");
        Iterator i = cola.listIterator();
        while(i.hasNext()){
            System.out.println(i.next());
        } 
        System.out.println("/////////////////////////////////////////");
    }
}
