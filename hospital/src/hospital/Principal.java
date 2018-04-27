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
        Servidor servidorResidente1 = new Servidor(0);//Creo el servidor
        Servidor servidorResidente2 = new Servidor(0);//Creo el servidor
        Servidor servidorGeneral = new Servidor(1);//Creo el servidor
        Servidor servidorEspecialista1 = new Servidor(2);//Creo el servidor
        Servidor servidorEspecialista2 = new Servidor(2);//Creo el servidor
        
        EventoFinSimulacion fin = new EventoFinSimulacion(tiempoSimulacion,0);//Creo el evento de fin de simulacion
        fel.insertarFel(fin);//Lo inserto en la fel
        actual = new EventoArribo(0,0);//Genero el primer arribo CUIDADO COCN EL TIPO
        fel.insertarFel(actual);//Lo inserto en la fel
        
        while(actual.getTipo()!=2){//Mientras que no encuentre el evento de fin de simulacion 
            actual = fel.suprimirFel();//Atiendo el evento en la primera posicion de la fel
            switch (actual.getTipo()){//Planifico el evento segun el tipo del cual sea
                case 0:{
                    if(queueResidente1.cantidadElementos()<=queueResidente2.cantidadElementos())//Si tienen la misma cantidad va a la cola 1, sino a la que tenga menos
                        actual.planificarEvento(servidorResidente1, queueResidente1);
                    else
                        actual.planificarEvento(servidorResidente2, queueResidente2);
                }
                case 1: actual.planificarEvento(servidorGeneral, queueGeneral);
                case 2: {
                    if(queueEspecialista1.cantidadElementos()<=queueEspecialista2.cantidadElementos())
                        actual.planificarEvento(servidorEspecialista1, queueEspecialista1);
                    else
                        actual.planificarEvento(servidorEspecialista2, queueEspecialista2);
                }
            }
        }
        //Genero las estadísticas para los médicos residentes
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorResidente1.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadLeves());
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorResidente2.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadLeves());
        //Genero las estadísticas para el medico generalista
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorGeneral.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadMedios());
        //Genero las estadisticas para los medicos especialistas
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorEspecialista1.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadGraves());
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorEspecialista2.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadGraves());
    }
}
