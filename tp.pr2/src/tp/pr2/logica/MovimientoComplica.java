package tp.pr2.logica;

public class MovimientoComplica extends Movimiento {
	// Atributos.
	private int[]undoStack;
	private Ficha[] arrayFichas;
	private int numUndo;
	private int posColumna;
					
	final int MAXPOSICIONES=10;
		// Metodos.
		// Constructor.
		public MovimientoComplica(int donde,Ficha color){
			//primero inicializamos la clase padre
			super(color);
			this.posColumna = donde;
			this.undoStack=new int[MAXPOSICIONES];
			this.arrayFichas=new Ficha[MAXPOSICIONES];
			this.numUndo = 0;
		}
			
		public  boolean ejecutaMovimiento(Tablero tab){
			boolean bien=true;
			int ultimaFila;
			Ficha ultimaCasilla;			
			//comprobamos si la columna introducida por el ususario es valida
			if((this.posColumna <= tab.getAncho()) && (this.posColumna > 0)){
				//comprobamos si la ultima posicion de la fila introducida por el ususario esta vacia
				ultimaCasilla=tab.getCasilla(this.posColumna,1);
				if(ultimaCasilla==Ficha.VACIA){
					//devolvemos la ultima fila ocupada para cambiar su color y lo guardamos en el array de deshacer
					ultimaFila=devolverUltimaFilaVacia(this.posColumna,tab);
					tab.setCasilla(this.posColumna,ultimaFila, getJugador());
					guardarUltimaCasilla(this.posColumna,Ficha.VACIA);
					
				}else{
					//guardamos la columna y el color en ambos arrays
					guardarUltimaCasilla(this.posColumna,tab.getCasilla(this.posColumna,tab.getAlto()));
					//la columna esta llena,se elimina la ultima ficha de la fila y pone la nueva	
					for(int i=tab.getAlto();i>1;i--){
						tab.setCasilla( this.posColumna,i,tab.getCasilla(this.posColumna,i-1));
					}
					tab.setCasilla(this.posColumna,1,getJugador());						
				}
								
				}else
					bien=false;
				
			return bien;
		}
			public  void undo(Tablero tab){
				int columna,ultimaFila;	
				//indica la ultima columna utilizada
				columna=this.undoStack[this.numUndo-1];
				//miramos la ultima fila vacia,porque la anterior es la del moviemnto
				 ultimaFila=devolverUltimaFilaVacia(columna,tab);
				 //si no es ficha vacia es que la ficha ha sido quitada del tablero porque la columna estaba llena
				 if(ultimaFila==0 && this.arrayFichas[this.numUndo-1]!=Ficha.VACIA){
					 //eliminamos la primera ficha de la columna y subimos a la ultima posicion la del array
					 for(int i=1;i<tab.getAlto();i++){
							tab.setCasilla( columna,i,tab.getCasilla(columna,i+1));
					 }
					tab.setCasilla(columna,tab.getAlto(),this.arrayFichas[this.numUndo-1]);					 
				 }else
					 tab.setCasilla(columna,ultimaFila+1,Ficha.VACIA);			    
			     this.numUndo--;			
			}
			//metodo que guarda la ultima columna y color de la ultima ficha puesta
			//recibe la columna introducida por el usuario y el color de la ficha si ha caido del tablero
			//si la ficha es vacia no se ha desplazado ninguna columna
			private void guardarUltimaCasilla(int col,Ficha color){
				//si el array ha llegado al maximo,se borra la primera posicion del array y se mueven los elementos
				if(this.numUndo==MAXPOSICIONES){
					for(int i=0;i<MAXPOSICIONES-1;i++){
						this.undoStack[i]=this.undoStack[i+1];	
						this.arrayFichas[i]=this.arrayFichas[i+1];
					}
				}else
					this.numUndo++;
				this.undoStack[this.numUndo-1]=col;	
				this.arrayFichas[this.numUndo-1]=color;
			}
			
			//metodo que devuelve la ultima fila vacia de la columna
			//recibe el tablero y la ultima columna introducida por el usuario
			private int devolverUltimaFilaVacia(int col,Tablero tab){
				int ultimaFila=tab.getAlto();
				Ficha casilla;
				//empezamos mirando desde la ultima posicion y vamos mirando si esta vacio
				casilla=tab.getCasilla(col,ultimaFila);
				while(casilla!=Ficha.VACIA && ultimaFila>0){
					ultimaFila--;
				    casilla=tab.getCasilla(col,ultimaFila);
				}
				return ultimaFila;
			}
		
}
