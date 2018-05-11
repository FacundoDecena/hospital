package hospital;

public class Principal {
			
    public static void main(String[] args){
        Evento actual;
        double tiempoSimulacion = 10080;//Establezco el tiempo de simulacion (168 hs)

        final int cantidadCorridas =  50;
        double[] mediasTiempoColaLeve = new double[cantidadCorridas]; 
        double[] mediasTiempoOciosoLeve = new double[cantidadCorridas];
        double[] mediasTiempoColaMedio = new double[cantidadCorridas]; 
        double[] mediasTiempoOciosoMedio = new double[cantidadCorridas]; 
        double[] mediasTiempoColaGrave = new double[cantidadCorridas]; 
        double[] mediasTiempoOciosoGrave = new double[cantidadCorridas]; 
        
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

            mediasTiempoColaLeve[i] = (Estadisticas.calcularTiempoMedioCola(servidorResidente1.getTiempoEsperaCola(), servidorResidente1.getCantidadItems()) + Estadisticas.calcularTiempoMedioCola(servidorResidente2.getTiempoEsperaCola(), servidorResidente2.getCantidadItems()))/2; 
            mediasTiempoOciosoLeve[i] = (Estadisticas.calcularPorcentajeOcioso(servidorResidente1.getTiempoOcioso(), tiempoSimulacion)+Estadisticas.calcularPorcentajeOcioso(servidorResidente2.getTiempoOcioso(), tiempoSimulacion))/2;
            
            mediasTiempoColaMedio[i] = Estadisticas.calcularTiempoMedioCola(servidorGeneral.getTiempoEsperaCola(), servidorGeneral.getCantidadItems());
            mediasTiempoOciosoMedio[i] = Estadisticas.calcularPorcentajeOcioso(servidorGeneral.getTiempoOcioso(), tiempoSimulacion);

            mediasTiempoColaGrave[i] = (Estadisticas.calcularTiempoMedioCola(servidorEspecialista1.getTiempoEsperaCola(), servidorEspecialista1.getCantidadItems())+Estadisticas.calcularTiempoMedioCola(servidorEspecialista2.getTiempoEsperaCola(), servidorEspecialista2.getCantidadItems()))/2;
            mediasTiempoOciosoGrave[i] = (Estadisticas.calcularPorcentajeOcioso(servidorEspecialista1.getTiempoOcioso(), tiempoSimulacion)+Estadisticas.calcularPorcentajeOcioso(servidorEspecialista2.getTiempoOcioso(), tiempoSimulacion))/2;

            
            Fel.reiniciarFel();
            Item.reiniciarCantidadDeItems();
        }
        
        double mediaTiempoOciosoLeve, mediaTiempoEsperaColaLeve,mediaTiempoOciosoMedio,mediaTiempoEsperaColaMedio,mediaTiempoOciosoGrave, mediaTiempoEsperaColaGrave;
        double desviacionTiempoOciosoLeve, desviacionTiempoEsperaColaLeve,desviacionTiempoOciosoMedio,desviacionTiempoEsperaColaMedio,desviacionTiempoOciosoGrave, desviacionTiempoEsperaColaGrave;

        mediaTiempoEsperaColaLeve = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoColaLeve);
        mediaTiempoOciosoLeve = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoOciosoLeve);
        mediaTiempoEsperaColaMedio = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoColaMedio);
        mediaTiempoOciosoMedio = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoOciosoMedio);
        mediaTiempoEsperaColaGrave = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoColaGrave);
        mediaTiempoOciosoGrave = Estadisticas.calcularMedia(cantidadCorridas, mediasTiempoOciosoGrave);

        desviacionTiempoEsperaColaLeve = Estadisticas.calcularDesviacionEstandar(cantidadCorridas,mediaTiempoEsperaColaLeve, mediasTiempoColaLeve);
        desviacionTiempoOciosoLeve = Estadisticas.calcularDesviacionEstandar(cantidadCorridas, mediaTiempoOciosoLeve, mediasTiempoOciosoLeve);
        desviacionTiempoEsperaColaMedio = Estadisticas.calcularDesviacionEstandar(cantidadCorridas,mediaTiempoEsperaColaMedio, mediasTiempoColaMedio);
        desviacionTiempoOciosoMedio = Estadisticas.calcularDesviacionEstandar(cantidadCorridas,mediaTiempoOciosoMedio, mediasTiempoOciosoMedio);
        desviacionTiempoEsperaColaGrave = Estadisticas.calcularDesviacionEstandar(cantidadCorridas,mediaTiempoEsperaColaGrave, mediasTiempoColaGrave);
        desviacionTiempoOciosoGrave = Estadisticas.calcularDesviacionEstandar(cantidadCorridas,mediaTiempoOciosoGrave, mediasTiempoOciosoGrave);
        
        
        System.out.printf("Tiempo medio espera en cola leve: %.2f minutos\n",mediaTiempoEsperaColaLeve);
        System.out.printf("Porcentaje tiempo ocioso residentes: %.2f",mediaTiempoOciosoLeve);
        System.out.println(" %\n");
        
        System.out.printf("Tiempo medio espera en cola medio: %.2f minutos\n",mediaTiempoEsperaColaMedio);
        System.out.printf("Porcentaje tiempo ocioso generalista: %.2f",mediaTiempoOciosoMedio);
        System.out.println(" %\n");
        
        System.out.printf("Tiempo medio espera en cola grave: %.2f minutos\n",mediaTiempoEsperaColaGrave);
        System.out.printf("Porcentaje tiempo ocioso especialistas: %.2f",mediaTiempoOciosoGrave);
        System.out.println(" %\n");
        
    }
}
