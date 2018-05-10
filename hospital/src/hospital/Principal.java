package hospital;

public class Principal {
			
    public static void main(String[] args){
        Evento actual;
        double tiempoSimulacion = 10080;//Establezco el tiempo de simulacion (168 hs)
        
        
        
        int cantidadCorridas =  1;
        double sumaTiempoOciosoLeve = 0, sumaTiempoEsperaColaLeve = 0,sumaTiempoOciosoMedio = 0, sumaTiempoEsperaColaMedio = 0,sumaTiempoOciosoGrave = 0, sumaTiempoEsperaColaGrave = 0;
        for(int i = 0; i<cantidadCorridas; i++){
            
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
            actual = new EventoArribo(0,0,null);//Genero el primer arribo leve
            fel.insertarFel(actual);//Lo inserto en la fel
            actual = new EventoArribo(0,1,null);//Genero el primer arribo medio
            fel.insertarFel(actual);//Lo inserto en la fel
            actual = new EventoArribo(0,2,null);//Genero el primer arribo grave
            fel.insertarFel(actual);//Lo inserto en la fel
            
            while(actual.getTipo()!=2){//Mientras que no encuentre el evento de fin de simulacion 
                actual = fel.suprimirFel();//Atiendo el evento en la primera posicion de la fel
                switch (actual.getItem().getTipo()){//Planifico el evento segun el tipo de item
                    case 0:{//Pacientes leves
                        if(actual.getTipo() == 1){//Si es un evento de salida, determino que servidor lo atendio y planifico  el evento
                            if(actual.getItem().getServidor() == servidorResidente1)
                                actual.planificarEvento(servidorResidente1, queueResidente1);
                            else
                                actual.planificarEvento(servidorResidente2, queueResidente2);
                        }
                        else//Si es un evento de arribo o de fin de servicio
                        {
                            if(servidorResidente1.isEstado() == false){//Si el servidor 1 esta desocupado, lo atiende
                                actual.getItem().setServidor(servidorResidente1);
                                actual.planificarEvento(servidorResidente1, queueResidente1);
                            }
                            else
                                if(servidorResidente2.isEstado() == false){//Si el servidor 2 esta desocupado, lo atiende
                                    actual.getItem().setServidor(servidorResidente2);
                                    actual.planificarEvento(servidorResidente2, queueResidente2);
                                }
                                else{//Si ambos estan ocupados me fijo que servidor tiene la cola mas corta y ese servidor lo atiende
                                    if(queueResidente1.cantidadElementos()<=queueResidente2.cantidadElementos()){//Si tienen la misma cantidad va a la cola 1, sino a la que tenga menos
                                        actual.getItem().setServidor(servidorResidente1);
                                        actual.planificarEvento(servidorResidente1, queueResidente1);
                                    }
                                    else{
                                        actual.getItem().setServidor(servidorResidente2);
                                        actual.planificarEvento(servidorResidente2, queueResidente2);
                                    }
                                }
                        }
                        break;
                    }
                    case 1:{//Pacientes medios 
                        actual.planificarEvento(servidorGeneral, queueGeneral);//Lo atiende
                        break;
                    }
                    case 2:{//Pacientes graves

                        if(actual.getTipo() == 1){//Si es un evento de salida, determino que servidor lo atendio y planifico  el evento
                            if(actual.getItem().getServidor() == servidorEspecialista1)
                                actual.planificarEvento(servidorEspecialista1, queueEspecialista1);
                            else
                                 actual.planificarEvento(servidorEspecialista2, queueEspecialista2);
                        }
                        else{//Si es un evento de arribo o de fin de servicio
                            if(servidorEspecialista1.isEstado() == false){//Si el servidor 1 esta desocupado, lo atiende
                                actual.getItem().setServidor(servidorEspecialista1);
                                actual.planificarEvento(servidorEspecialista1, queueEspecialista1);
                            }
                            else
                                if(servidorEspecialista2.isEstado() == false){//Si el servidor 2 esta desocupado, lo atiende
                                    actual.getItem().setServidor(servidorEspecialista2);
                                    actual.planificarEvento(servidorEspecialista2, queueEspecialista2);
                                }
                                else{//Si ambos estan ocupados me fijo que servidor tiene la cola mas corta y ese servidor lo atiende
                                    if(queueEspecialista1.cantidadElementos()<=queueEspecialista2.cantidadElementos()){//Si tienen la misma cantidad va a la cola 1, sino a la que tenga menos
                                        actual.getItem().setServidor(servidorEspecialista1);
                                        actual.planificarEvento(servidorEspecialista1, queueEspecialista1);
                                    }
                                    else{
                                        actual.getItem().setServidor(servidorEspecialista2);
                                        actual.planificarEvento(servidorEspecialista2, queueEspecialista2);
                                    }
                                }
                        }
                        break;
                    }
                }
            }
            //Genero las estadísticas para los médicos residentes

            sumaTiempoEsperaColaLeve += Estadisticas.calcularTiempoMedioCola(servidorResidente1.getTiempoEsperaCola(), servidorResidente1.getCantidadItems());
            sumaTiempoOciosoLeve += Estadisticas.calcularPorcentajeOcioso(servidorResidente1.getTiempoOcioso(), tiempoSimulacion);

            sumaTiempoEsperaColaLeve += Estadisticas.calcularTiempoMedioCola(servidorResidente2.getTiempoEsperaCola(), servidorResidente2.getCantidadItems());
            sumaTiempoOciosoLeve += Estadisticas.calcularPorcentajeOcioso(servidorResidente2.getTiempoOcioso(), tiempoSimulacion);


            sumaTiempoEsperaColaMedio += Estadisticas.calcularTiempoMedioCola(servidorGeneral.getTiempoEsperaCola(), servidorGeneral.getCantidadItems());
            sumaTiempoOciosoMedio += Estadisticas.calcularPorcentajeOcioso(servidorGeneral.getTiempoOcioso(), tiempoSimulacion);


            sumaTiempoEsperaColaGrave += Estadisticas.calcularTiempoMedioCola(servidorEspecialista1.getTiempoEsperaCola(), servidorEspecialista1.getCantidadItems());
            sumaTiempoOciosoLeve += Estadisticas.calcularPorcentajeOcioso(servidorEspecialista1.getTiempoOcioso(), tiempoSimulacion);

            sumaTiempoEsperaColaGrave += Estadisticas.calcularTiempoMedioCola(servidorEspecialista2.getTiempoEsperaCola(), servidorEspecialista2.getCantidadItems());
            sumaTiempoOciosoLeve += Estadisticas.calcularPorcentajeOcioso(servidorEspecialista2.getTiempoOcioso(), tiempoSimulacion);
            
            Fel.reiniciarFel();
            Item.reiniciarCantidadDeItems();
        }
        
        System.out.println("Tiempo medio espera en cola leve: "+sumaTiempoEsperaColaLeve/(cantidadCorridas*2)+" minutos");
        System.out.println("Porcentaje tiempo ocioso residentes: %"+sumaTiempoOciosoLeve/(cantidadCorridas*2));
        
        System.out.println("Tiempo medio espera en cola medio: "+sumaTiempoEsperaColaMedio/cantidadCorridas+" minutos");
        System.out.println("Porcentaje tiempo ocioso generalista: %"+sumaTiempoOciosoMedio/cantidadCorridas);
        
        System.out.println("Tiempo medio espera en cola grave: "+sumaTiempoEsperaColaGrave/(cantidadCorridas*2)+" minutos");
        System.out.println("Porcentaje tiempo ocioso especialistas: %"+sumaTiempoOciosoGrave/(cantidadCorridas*2));
        
    }
}
