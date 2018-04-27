package hospital;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue {
    private int cantidadItems;
    private LinkedList cola;

    public Queue(){
        cola=new LinkedList();
        cantidadItems=0;
    }

    public void insertarCola(Item item){
        cantidadItems++;
        cola.add(item);		
    }

    public Item suprimirCola(){
       cantidadItems--;
       return (Item)cola.remove();
    }

    public boolean HayCola(){
        return !cola.isEmpty();
    }
    
    public int cantidadElementos(){
        return cantidadItems;
    }

    public void mostrarCola(){
        System.out.println("/////////////////////////////////////////");
        Iterator i = cola.listIterator();
        while(i.hasNext()){
            System.out.println(i.next());
        } 
        System.out.println("/////////////////////////////////////////");
    }
}
