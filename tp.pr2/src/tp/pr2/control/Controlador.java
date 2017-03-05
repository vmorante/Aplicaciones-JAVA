package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoComplica;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasComplica;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.ReglasJuego;



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
		ReglasJuego reglas=new ReglasConecta4();
		String tipoDeJuego="c4";
		Movimiento mov;		
		boolean salir=false;
		Ficha color;	
		//si la partida no ha terminado y salir es falso
		while(!partida.isTerminada() && !salir){
			//mostramos el tablero y el turno del jugador
			System.out.print(this.partida.toString());
			System.out.print("Qué quieres hacer? ");
			opcion=in.nextLine();
			//convertimos la opcion a minuscula
			opcion=opcion.toLowerCase();			
			switch(opcion){
			case("jugar co"):
				//si el juego es complica la partida se reinicia
				reglas=new ReglasComplica();	
				tipoDeJuego="complica";
			    partida.reset(reglas);
				System.out.println("Partida reiniciada.");				
			break;
			case ("jugar c4"):
				//si el jugador elige conecta 4 ,la partida se reinicia con las nuevas reglas
				reglas=new ReglasConecta4();
			    tipoDeJuego="c4";
			    partida.reset(reglas);
				System.out.println("Partida reiniciada.");					
			break;
			case ("poner"):
				System.out.print("Introduce la columna: ");
				col=in.nextInt();
				//cogemos entradas pendientes
				in.nextLine();				
				color=partida.getTurno();
				if(tipoDeJuego=="complica"){
					mov=new MovimientoComplica(col,color);					
				}else
					mov=new MovimientoConecta4(col,color);
				//comprobamos si el movimiento es valido
				if(partida.ejecutaMovimiento(mov)){
					//si la partida ha terminado
					if(partida.isTerminada())
						//mostramos el tablero y los ganadores
						System.out.println(this.partida.toString());					
				}else
					System.err.println("Movimiento incorrecto");				
			break;
			case ("deshacer"):
				//si es el primer movimiento no se puede deshacer
				if(!partida.undo())
					System.err.println("Imposible deshacer.");
			break;
			case("reiniciar"):
				partida.reset(reglas);
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

