package tp.pr2.logica;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Tablero;

public class Partida {
	//atributos
		private Tablero tablero;
		private Ficha turno;
		private boolean terminada;
		private Ficha ganador;	
		private Movimiento []undoStack;
		private int numUndo;		
		private ReglasJuego reglas;
		
		//Constantes.
		final int MAXPOSICIONES=10;

	public Partida(ReglasJuego reglas){		
		this.reglas= reglas;
		this.tablero =  reglas.iniciaTablero();
		this.turno= reglas.jugadorInicial();
		this.terminada = false;
		this.ganador=Ficha.VACIA;
		this.undoStack=new Movimiento[MAXPOSICIONES];
		this.numUndo=0;
		
	}
	public void reset(ReglasJuego reglas){
		//inicializamos las reglas y el nuevo tablero
		this.tablero=reglas.iniciaTablero();
		this.reglas=reglas;
		this.turno=reglas.jugadorInicial();
		this.numUndo=0;
	}
	public boolean ejecutaMovimiento(Movimiento mov){
		boolean ok=true;
		//comprobamos si el turno corresponde con el movimiento
		if(this.turno==mov.getJugador()){
			//si la partida no ha terminado
			if(!isTerminada()){
				//comprobamos si el movimiento es valido
				if(mov.ejecutaMovimiento( tablero)){
					//si el array de guardar los movimientos ha llegado al maximo,se borra la primera posicion del array y se mueven los elementos
					if(this.numUndo==MAXPOSICIONES){
						for(int i=0;i<MAXPOSICIONES-1;i++){
							this.undoStack[i]=this.undoStack[i+1];			
						}
					}else
						this.numUndo++;
					this.undoStack[this.numUndo-1]=mov;
					//si hay ganador la partida ha acabado 
					 if(this.reglas.hayGanador(mov,tablero)!=Ficha.VACIA){
						 this.ganador=this.reglas.hayGanador(mov,tablero);
					 	 this.terminada=true;
					 	 this.turno=Ficha.VACIA;
					 	 //si hay tablas ,la partida ha terminado y no hay ganadores(solo conecta4)
					 }else if(this.reglas.tablas(this.turno,this.tablero)){
							 this.ganador=Ficha.VACIA;
						 	 this.terminada=true;
						 	 this.turno=Ficha.VACIA;
						 }else
							 //si no hay tablas ni ganadores,la partida continua y se calcula el siguiente turno
							 this.turno=this.reglas.siguienteTurno(this.turno,this.tablero);					 
				}else
					ok=false;		
			}else
				ok=false;
		}else
			ok=false;
	return ok;
}
	public boolean undo(){
		boolean deshacer=false;
		//si hay movimientos que deshacer llamamos deshacer del movimiento
		if(this.numUndo>0){
			deshacer=true;
		    this.undoStack[numUndo-1].undo(this.tablero);
		    this.turno= this.reglas.siguienteTurno(this.turno, this.tablero);
	        this.numUndo--;
		}
	    return deshacer;
	}
	public Ficha getTurno(){
		return this.turno;
	}
	public Ficha getGanador(){
		return this.ganador;
	}
	public boolean isTerminada(){
		return this.terminada;
	}
	public Tablero getTablero(){
		return this.tablero;
	}
	//metodo que devuelve una cadena con el string de la partida
	public String toString(){
		String cadena;
		cadena=this.tablero.toString();
		if(!terminada){
			if(this.turno==Ficha.NEGRA)
				cadena=cadena +"Juegan negras";
			else
				cadena=cadena +"Juegan blancas";
		
			cadena = cadena + "\n";
			
		}else{
			if(this.ganador==Ficha.VACIA)
				cadena=cadena+"Partida terminada en tablas.";
			else
				if(this.ganador==Ficha.NEGRA)
					cadena=cadena+"Ganan las negras";
				else
					cadena=cadena+"Ganan las blancas";
		}	
		return cadena;
	}

}
