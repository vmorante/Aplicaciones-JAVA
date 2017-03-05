// Paquete con las clases que controlan la ejecuci�n del juego.
package tp.pr1.control;


import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;
import tp.pr1.logica.Tablero;
public class Controlador {
	//atributos
	private Partida partida;
	private Scanner in;
	
	//inicializa los atributos de partida
	public Controlador (Partida p, java.util.Scanner in){
		this.partida=p;
		this.in = in;		
	}
	
	//dirige el funcionamiento de la partida
	public void run(){		
		String opcion;
		int col;
		Tablero tablero;
		boolean salir=false;
		Ficha color,colorGanador,turno;	
		//si la partida no ha terminado y salir es falso
		while(!partida.isTerminada() && !salir){
			//mostramos el tablero
			tablero=partida.getTablero();
			tablero.mostrarTablero();
			//conseguimos el turno de la partida
			turno=partida.getTurno();
			if(turno==Ficha.NEGRA)
				System.out.println("Juegan negras");
			else
				System.out.println("Juegan blancas");
			//pedimos al usuario que quiere hacer
			System.out.println("Qué quieres hacer?");
			opcion=in.nextLine();
			//convertimos la opcion a minuscula
			opcion=opcion.toLowerCase();			
			switch(opcion){
			case ("poner"):
				System.out.println("Introduce la columna:");
				col=in.nextInt();
				//cogemos entradas pendientes
				in.nextLine();
				color=partida.getTurno();
				//comprobamos si el movimiento es valido
				if(partida.ejecutaMovimiento(color,col)){
					//si la partida ha terminado
					if(partida.isTerminada()){
						//mostramos el tablero
						tablero=partida.getTablero();
						tablero.mostrarTablero();
						//comprobamos el ganador
						colorGanador= partida.getGanador();
						if(colorGanador==Ficha.VACIA)
							System.out.println("Partida terminada en tablas");
						else
							if(colorGanador==Ficha.NEGRA)
								System.out.println("Ganas las negras");
							else
								System.out.println("Ganas las blancas");
					}
				}else
					System.err.println("Movimiento incorrecto");				
			break;
			case ("deshacer"):
				//si es el primer movimiento no se puede deshacer
				if(!partida.undo())
					System.err.println("Imposible deshacer.");
			break;
			case("reiniciar"):
				partida.reset();
			System.out.println("Partida reiniciada.");
			break;
			case("salir"):
				salir=true;
			break;
			default:
				//el usuario mete una opcion incorrecta
				System.err.println("No te entiendo.");
				break;
				
			}			
		}
	}	
}
