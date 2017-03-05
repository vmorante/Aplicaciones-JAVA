package tp.pr2.logica;

public class MovimientoConecta4 extends Movimiento {
	
	// Atributos.
	    private int[]undoStack;
		private int numUndo;
		private int posColumna;		
		
		final int MAXPOSICIONES=10;
		// Metodos.
		// Constructor.
		public MovimientoConecta4(int donde,Ficha color){
			//inicializamos primero los atributos de la clase padre
			super(color);
			this.posColumna = donde;
			this.numUndo = 0;
			this.undoStack=new int[MAXPOSICIONES];
			
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
						//devolvemos la ultima fila vacia para cambiar su color y lo guardamos en el array de deshacer
						ultimaFila=devolverUltimaFilaVacia(this.posColumna,tab);
						tab.setCasilla(this.posColumna,ultimaFila,getJugador());
						guardarUltimaCasilla(this.posColumna);
						
					}else
						bien=false;
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
			 tab.setCasilla(columna,ultimaFila+1,Ficha.VACIA);			    
		     this.numUndo--;			
		}
		
		//guarda la ultima columna puesta en el array
		//recibe la ultima columna introducida por el usuario
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
		//metodo que devuelve la ultima fila vacia de la columna
		//recibe el tablero y la columna introducida por el usuario
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


