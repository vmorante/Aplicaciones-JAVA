package tp.pr2.logica;




public class Grupo {
	
	
	public  static boolean fichaGanador(Tablero t,Ficha color){
		boolean fichaGanadora=false;
		if(comprobarGrupoEnHorizontal(t,color))
			fichaGanadora=true;
		else{ if(comprobarGrupoEnVertical(t,color))
			fichaGanadora=true;
			else{ if(comprobarGrupoEnDiagonalDerecha(t,color))
				fichaGanadora=true;
				else if (comprobarGrupoEnDiagonalIzquierda(t,color))
					fichaGanadora=true;
			}
		}
	
		
		return fichaGanadora;
		
	}
	private static boolean comprobarGrupoEnHorizontal(Tablero t,Ficha color){
		boolean grupo=false;
		int fila=t.getAlto(),columna,cont;
		
		while(!grupo && fila>=1){
			columna=1;
			cont=0;
			while(!grupo && columna<=t.getAncho() ){
				if(t.getCasilla(columna,fila)==color)
					cont++;
				else
					cont=0;
				if(cont>=4)
					grupo=true;
				columna++;
			}
			fila--;
		}
		return grupo;
	}
	
	private static boolean comprobarGrupoEnVertical(Tablero t,Ficha color){
		boolean grupo=false;
		int fila,columna=1,cont;
		
		while(!grupo && columna<=t.getAncho()){
			fila=t.getAlto();
			cont=0;
			while(!grupo && fila>=1  ){
				if(t.getCasilla(columna,fila)==color)
					cont++;
				else
					cont=0;
				if(cont>=4)
					grupo=true;
				fila--;
			}
			columna++;
		}
		return grupo;
	}
	private static boolean comprobarGrupoEnDiagonalDerecha(Tablero t,Ficha color){
		boolean grupo=false;
		int fila=t.getAlto(),columna=1,cont,columnaDiagonal,filaDiagonal;
		
		while(!grupo && columna<=t.getAncho()){
			fila=t.getAlto();
			cont=0;
			columnaDiagonal=columna;
			while(!grupo && columnaDiagonal<=t.getAncho() ){
				if(t.getCasilla(columnaDiagonal,fila)==color)
					cont++;
				else
					cont=0;
				if(cont>=4)
					grupo=true;
				columnaDiagonal++;
				fila--;
			}
			columna++;
		}
		
		if(!grupo){
			fila=t.getAlto()-1;
			while(!grupo && fila>=4){
				filaDiagonal=fila;
				cont=0;
				columnaDiagonal=1;
				while(!grupo && columnaDiagonal<=t.getAncho() && filaDiagonal>=1 ){
					if(t.getCasilla(columnaDiagonal,filaDiagonal)==color)
						cont++;
					else
						cont=0;
					if(cont>=4)
						grupo=true;
					columnaDiagonal++;
					filaDiagonal--;
				}
				
				fila--;
			}
			
			
			
		}
		return grupo;
	}
	private static boolean comprobarGrupoEnDiagonalIzquierda(Tablero t,Ficha color){
		boolean grupo=false;
		int fila=t.getAlto(),columna=1,cont,columnaDiagonal,filaDiagonal;
		
		while(!grupo && columna<=t.getAncho()){
			fila=t.getAlto();
			cont=0;
			columnaDiagonal=columna;
			while(!grupo && columnaDiagonal>=1  ){
				if(t.getCasilla(columnaDiagonal,fila)==color)
					cont++;
				else
					cont=0;
				if(cont>=4)
					grupo=true;
				columnaDiagonal--;
				fila--;
			}
			columna++;
		}
		
		if(!grupo){
			fila=t.getAlto()-1;
			while(!grupo && fila>=4){
				filaDiagonal=fila;
				cont=0;
				columnaDiagonal=t.getAncho();
				while(!grupo && columnaDiagonal>=1 && filaDiagonal>=1 ){
					if(t.getCasilla(columnaDiagonal,filaDiagonal)==color)
						cont++;
					else
						cont=0;
					if(cont>=4)
						grupo=true;
					columnaDiagonal--;
					filaDiagonal--;
				}
				
				fila--;
			}
		}
		return grupo;
	
	}

}
