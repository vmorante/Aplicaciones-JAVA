package tp.pr1.logica;

import tp.pr1.logica.Tablero;

public class Partida {
	//atributos
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;	
	private int[]undoStack;
	private int numUndo;
	
	//Constantes.
	final int FILA=6;
	final int COLUMNA=7;
	final int MAXPOSICIONES=10;
	
	//inicializamos los atributos
	public Partida() {
		this.tablero = new Tablero(COLUMNA, FILA);
		this.turno= Ficha.BLANCA;
		this.terminada = false;
		this.ganador=Ficha.VACIA;
		this.numUndo=0;
		this.undoStack=new int[MAXPOSICIONES];
	}
	
	//metodo que indica si un moviento es valido
	public boolean ejecutaMovimiento(Ficha color,int col){
		boolean bien;
		int ultimaFila;
		Ficha turnoActual,ultimaCasilla;
		//comprobamos si la columna introducida por el ususario es valida
		if(col<=tablero.getAncho() && col>0){
		turnoActual=getTurno();
		//comprobamos si el turno es igual al color de la ficha
		if(turnoActual==color){	
			//comprobamos si la partida esta terminada
			if(!isTerminada()){
				//comprobamos si la ultima posicion de la fila introducida por el ususario esta vacia
				ultimaCasilla=tablero.getCasilla(col,1);
				if(ultimaCasilla==Ficha.VACIA){
					//devolvemos la ultima fila ocupada para cambiar su color y lo guardamos en el array de deshacer
					ultimaFila=devolverUltimaFilaOcupada(col);
					tablero.setCasilla(col,ultimaFila, color);
					guardarUltimaCasilla(col);
					//comprobamos si se ha formado un grupo
					if(existeGrupo(ultimaFila,col)){
						this.ganador=turno;
						this.terminada=true;
					}else{
						//comprobamos si ha habido tablas en la partida
						if(comprobarTablas()){
							this.terminada=true;
							this.ganador=Ficha.VACIA;
						}						
						cambiarTurno();
					}
					bien=true;
				}else
					bien=false;				
			}else
				bien=false;
		}else
			bien=false;
		}else
			bien=false;
return bien;
}




//metodo que devuelve la ultima fila ocupada de la columna	
private int devolverUltimaFilaOcupada(int col){
	int ultimaFila=tablero.getAlto();
	Ficha casilla;
	//empezamos mirando desde la ultima posicion y vamos mirando si esta vacio
	casilla=tablero.getCasilla(col,ultimaFila);
	while(casilla!=Ficha.VACIA && ultimaFila>0){
		ultimaFila--;
	    casilla=tablero.getCasilla(col,ultimaFila);
	}
	return ultimaFila;
}
//cambia el turno del jugador
private void cambiarTurno(){
	if(isTerminada()) {
		this.turno = Ficha.VACIA;
	}
	else if(turno == Ficha.BLANCA) {
		this.turno = Ficha.NEGRA;
	}
	else {
		this.turno= Ficha.BLANCA;
	}		
}
//devuelve el turno 
public Ficha getTurno(){			
	return this.turno;	
}

//pone todo el tablero con fichas vacias
public void reset(){
	int ty,tx;
	ty=tablero.getAlto();
	tx=tablero.getAncho();
	for(int i=1;i<=ty;i++){
		for(int j=1;j<=tx;j++){
			tablero.setCasilla(j,i,Ficha.VACIA);
		}
	}
	//reinicia el array de deshacer movimientos
	this.numUndo=0;
	this.turno=Ficha.BLANCA;
}

//deshace el ultimo movimiento
public boolean undo(){
	int columna,ultimaFila;
	boolean deshacer;	
	if(this.numUndo>0){
		deshacer=true;
		//indica la ultima columna utilizada
		columna=this.undoStack[this.numUndo-1];
		//miramos la ultima fila ocupada,porque la anterior es la del moviemnto
	    ultimaFila=devolverUltimaFilaOcupada(columna);
	    tablero.setCasilla(columna,ultimaFila+1,Ficha.VACIA);
	    cambiarTurno();
        this.numUndo--;        
	}
    else
    	deshacer=false;
	return deshacer;
}

//guarda el ultimo movimiento
private void guardarUltimaCasilla(int col){
	//si el array ha llegado al maximo,se borra la primera posicion del array y se mueven los elementos
	if(this.numUndo==MAXPOSICIONES){
		for(int i=0;i<MAXPOSICIONES-1;i++){
			this.undoStack[i]=this.undoStack[i+1];			
		}
	}else
		this.numUndo++;
	this.undoStack[this.numUndo-1]=col;
	
	
}
//indica si la partida ha terminada
public boolean isTerminada(){
	return this.terminada;	
}
//comprueba si ha formado un grupo de 4 fichas iguales
private int comprobar(int fila,int columna,int incFila,int incColumna){
	int nuevaFila=fila+incFila;
	int nuevaColumna=columna+incColumna;
	int cont=0;
	boolean salir=false;
	//comprobamos si la fila y columna es mayor que cero y no se ssale de las dimensiones
   if(nuevaFila>0 && nuevaFila<=tablero.getAlto() && nuevaColumna>0 && nuevaColumna<=tablero.getAncho() ){
	   //mientras las dos casillas sean iguales
	   while( (!salir) &&(tablero.getCasilla(columna,fila)==tablero.getCasilla(nuevaColumna,nuevaFila)) ){
			cont++;
			//si el contador es mayor que 3 ,ya se ha formado un grupo y salimos
	   	    if(cont>=3)
	   	    	salir=true;
	   	    else{
	   	    	nuevaFila+=incFila;
	   	    	nuevaColumna+=incColumna;
	   	    	if(nuevaFila<=0 || nuevaFila>tablero.getAlto() || nuevaColumna<=0 || nuevaColumna>tablero.getAncho())
	   	    		salir=true;
	   	    }
	   }
	}
   return cont;
}
//comprueba si se ha formado un grupo en horizontal
private boolean comprobarGrupoHorizontal(int fila,int columna){
	int cont1,cont2=0;
	boolean grupo;
	cont1=comprobar(fila,columna,0,1);
	//si el contador es menor que 3 ,todavia no se ha formado un grupo y miramos en la direccion opuesta
	if (cont1<3)
		cont2=comprobar(fila,columna,0,-1);
	//miramos si entre la suma de ambos hay un grupo
	if(cont1+cont2>=3)
		grupo=true;
	else
		grupo=false;
	return grupo;
}
//comprobamos si hay grupo en vertical
private boolean comprobarGrupoVertical(int fila,int columna){
	int cont1;
	boolean grupo;
	cont1=comprobar(fila,columna,1,0);
	if (cont1>=3)		
		grupo=true;
	else
		grupo=false;
	return grupo;
}
//devuelve si hay un grupo en la diagonal derecha
private boolean comprobarGrupoDiagonalDerecha(int fila,int columna){
	int cont1,cont2=0;
	boolean grupo;
	cont1=comprobar(fila,columna,1,-1);
	if (cont1<3)
		cont2=comprobar(fila,columna,-1,1);	
	if(cont1+cont2>=3)
		grupo=true;
	else
		grupo=false;
	return grupo;
}
//devuelve si hay un grupo en la diagonal izquierda
private boolean comprobarGrupoDiagonalIzquierda(int fila,int columna){
	int cont1,cont2=0;
	boolean grupo;
	cont1=comprobar(fila,columna,-1,-1);
	if (cont1<3)
		cont2=comprobar(fila,columna,1,1);
	
	if(cont1+cont2>=3)
		grupo=true;
	else
		grupo=false;
	return grupo;
}
//miramos todas las posibles combinaciones para saber si ha formado un grupo
private boolean existeGrupo(int fila,int columna){
	boolean existe;
	if((fila<=0) || (fila>tablero.getAlto()) || (columna<=0) || (columna>tablero.getAncho()))
		existe=false;
	else{
		if(comprobarGrupoHorizontal(fila,columna))
			existe=true;
		else if(comprobarGrupoVertical(fila,columna))
			 	existe=true;
			 else if(comprobarGrupoDiagonalDerecha(fila,columna))
				 		existe=true;
			 		else if(comprobarGrupoDiagonalIzquierda(fila,columna))
			 			existe=true;
			 		else
			 			existe=false;
		
	  }	
	
	return existe;
}
//devuelve el ganador de la partida
public Ficha getGanador(){	
	return this.ganador;	
}
//comprobamos is la partida ha terminado en tablas
private boolean comprobarTablas(){
	boolean tablas=true;
	int col=1;
	//comprobamos si la primera fila de las columnas esta vacia
	while(tablas && col<=tablero.getAncho()){
		if(tablero.getCasilla(col,1)==Ficha.VACIA)
			tablas=false;
	 col++;
	}
	return tablas;
}

//devuelve el tablero de la partida
public  Tablero getTablero(){
	return this.tablero;
}


}