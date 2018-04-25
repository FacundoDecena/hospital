package hospital;

public class Principal {
			
    public static void main(String[] args){
        Evento actual;
        float tiempoSimulacion = 10080;//Establezco el tiempo de simulacion (1 semana)
        Fel fel = Fel.getFel();//Creo la lista de eventos futuros
        Queue queueResidente = new Queue();//Creo la cola de espera
        Queue queueGeneral = new Queue();//Creo la cola de espera
        Queue queueEspecialista = new Queue();//Creo la cola de espera
        Servidor servidorResidente = new Servidor(0);//Creo el servidor
        Servidor servidorGeneral = new Servidor(1);//Creo el servidor
        Servidor servidorEspecialista = new Servidor(2);//Creo el servidor
        EventoFinSimulacion fin = new EventoFinSimulacion(tiempoSimulacion,0);//Creo el evento de fin de simulacion
        fel.insertarFel(fin);//Lo inserto en la fel
        actual = new EventoArribo(0,0);//Genero el primer arribo CUIDADO COCN EL TIPO
        fel.insertarFel(actual);//Lo inserto en la fel
        while(actual.getTipo()!=2){//Mientras que no encuentre el evento de fin de simulacion 
            actual = fel.suprimirFel();//Atiendo el evento en la primera posicion de la fel
            switch (actual.getTipo()){
                case 0: actual.planificarEvento(servidorGeneral, queueResidente);//Planifico el evento segun el tipo del cual sea
                case 1: actual.planificarEvento(servidorGeneral, queueGeneral);//Planifico el evento segun el tipo del cual sea
                case 2: actual.planificarEvento(servidorGeneral, queueEspecialista);//Planifico el evento segun el tipo del cual sea
            }
            
        }
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorResidente.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadLeves());//Genero las estadisticas
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorGeneral.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadMedios());//Genero las estadisticas
        Estadisticas.calcularEstadisticas(Item.getTiempoEsperaCola(),Item.getTiempoTransito(),servidorEspecialista.getTiempoOcioso(),tiempoSimulacion, Item.getCantidadGraves());//Genero las estadisticas
    }
}
