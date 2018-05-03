package hospital;

public class Principal {
			
    public static void main(String[] args){
        Evento actual;
        double tiempoSimulacion = 10080;//Establezco el tiempo de simulacion (1 semana)
        Fel fel = Fel.getFel();//Creo la lista de eventos futuros
        //Declaracion de colas
        Queue queueResidente1 = new Queue();//Creo la cola de espera para el médico residente 1
        Queue queueResidente2 = new Queue();//Creo la cola de espera para el médico residente 2
        Queue queueGeneral = new Queue();//Creo la cola de espera para el médico generalista
        Queue queueEspecialista1 = new Queue();//Creo la cola de espera para el médico especialista 1
        Queue queueEspecialista2 = new Queue();//Creo la cola de espera para el médico especialista 2
        //Declaracion de servidores
        Servidor servidorResidente1 = new Servidor(0,1);//Creo el servidor
        Servidor servidorResidente2 = new Servidor(0,2);//Creo el servidor
        Servidor servidorGeneral = new Servidor(1,3);//Creo el servidor
        Servidor servidorEspecialista1 = new Servidor(2,4);//Creo el servidor
        Servidor servidorEspecialista2 = new Servidor(2,5);//Creo el servidor
        
        EventoFinSimulacion fin = new EventoFinSimulacion(tiempoSimulacion,0);//Creo el evento de fin de simulacion
        fel.insertarFel(fin);//Lo inserto en la fel
        actual = new EventoArribo(0,0);//Genero el primer arribo leve
        fel.insertarFel(actual);//Lo inserto en la fel
        
        actual = new EventoArribo(0,1);//Genero el primer arribo medio
        fel.insertarFel(actual);//Lo inserto en la fel
        
        actual = new EventoArribo(0,2);//Genero el primer arribo grave
        fel.insertarFel(actual);//Lo inserto en la fel
        
        while(actual.getTipo()!=2){//Mientras que no encuentre el evento de fin de simulacion 
            actual = fel.suprimirFel();//Atiendo el evento en la primera posicion de la fel
            switch (actual.getItem().getTipo()){//Planifico el evento segun el tipo del cual sea
                case 0:{
                    if(queueResidente1.cantidadElementos()<=queueResidente2.cantidadElementos())//Si tienen la misma cantidad va a la cola 1, sino a la que tenga menos
                        actual.planificarEvento(servidorResidente1, queueResidente1);
                    else
                        actual.planificarEvento(servidorResidente2, queueResidente2);
                    break;
                }
                case 1:{ 
                    actual.planificarEvento(servidorGeneral, queueGeneral);
                    break;
                }
                case 2:{ 
                    if(queueEspecialista1.cantidadElementos()<=queueEspecialista2.cantidadElementos())
                        actual.planificarEvento(servidorEspecialista1, queueEspecialista1);
                    else
                        actual.planificarEvento(servidorEspecialista2, queueEspecialista2);
                    break;
                }
            }
        }
        //Genero las estadísticas para los médicos residentes
        System.out.println("Estadística servidor residente 1");
        Estadisticas.calcularEstadisticas(servidorResidente1.getTiempoEsperaCola(),servidorResidente1.getTiempoTransito(),servidorResidente1.getTiempoOcioso(),tiempoSimulacion, servidorResidente1.getCantidadItems());
        System.out.println("\nEstadística servidor residente 2");
        Estadisticas.calcularEstadisticas(servidorResidente2.getTiempoEsperaCola(),servidorResidente2.getTiempoTransito(),servidorResidente2.getTiempoOcioso(),tiempoSimulacion, servidorResidente2.getCantidadItems());
        //Genero las estadísticas para el medico generalista
        System.out.println("\nEstadística servidor generalista");
        Estadisticas.calcularEstadisticas(servidorGeneral.getTiempoEsperaCola(),servidorGeneral.getTiempoTransito(),servidorGeneral.getTiempoOcioso(),tiempoSimulacion, servidorGeneral.getCantidadItems());
        //Genero las estadisticas para los medicos especialistas
        System.out.println("\nEstadística servidor especialista 1");
        Estadisticas.calcularEstadisticas(servidorEspecialista1.getTiempoEsperaCola(),servidorEspecialista1.getTiempoTransito(),servidorEspecialista1.getTiempoOcioso(),tiempoSimulacion, servidorEspecialista1.getCantidadItems());
        System.out.println("\nEstadística servidor especialista 2");
        Estadisticas.calcularEstadisticas(servidorEspecialista2.getTiempoEsperaCola(),servidorEspecialista2.getTiempoTransito(),servidorEspecialista2.getTiempoOcioso(),tiempoSimulacion, servidorEspecialista2.getCantidadItems());
    }
}
