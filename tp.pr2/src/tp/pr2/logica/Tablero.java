package tp.pr2.logica;


public class Tablero{
	
	//Atributos
	private int ancho;
	private int alto;
	private Ficha [ ][ ] tablero;
	
	
	// Metodos.
	//construye un tablero vacio
	//si el alto o el ancho es menor que uno se crea un tablero 1x1
	// tx - columnas, ty -filas.
	public Tablero(int tx, int ty)
	{
		if(tx<1 || ty<1){
			this.ancho=1;
			this.alto=1;
		}else{		
			this.ancho=tx;
			this.alto=ty;
		}
		this.tablero = new Ficha[this.alto][this.ancho];
		for(int i=0;i<this.alto;i++){
			for(int j=0;j<this.ancho;j++){
				//en en incio el tablero esta formado por fichas vacias
				this.tablero[i][j] = Ficha.VACIA;
			}			
		} 
	}
	
	//Metodo para obtener el alto del tablero.
	public int getAlto(){
		
		return this.alto;
	}
	
	// Metodo para obtener el ancho del tablero.
	public int getAncho(){
		
		return this.ancho;
	}
	
	// Metodo para acceder a la informacion de una casilla o celda concreta.
	public Ficha getCasilla(int x,int y){
		Ficha colorFicha;
		//comprobamos si el alto y el ancho es valido,sino devolvemos ficha vacia
		if(x>this.ancho || y>this.alto || x<=0 || y<=0)
			colorFicha=Ficha.VACIA;
		else
			colorFicha = tablero[y-1][x-1];
		return colorFicha;
	}
	
	// Permite especificar el nuevo contenido de una casilla. Tambien puede utilizarse para quitar una ficha.
	public void setCasilla(int x,int y,Ficha color){
		if(x<=this.ancho && y<=this.alto && x>0 && y>0)
			this.tablero[y-1][x-1]=color;
	}
	
	/// Método toString, devuelve en una cadena el tablero.
	public String toString()
	{
		 String cadena = "";
		
		// Pintamos |        | para las filas y utilizando las columnas.
		for(int fila = 0; fila < this.alto; fila++) 
		{
			cadena = cadena + "|";
			for(int columna = 0; columna < this.ancho; columna++) 
			{
				/* Dependiendo de lo que contenga el array de tablero, lo dejaremos vacio o pintaremos
				   la Ficha, si es blanca aparecerá O, y si es negra, aparecerá una X. */
				if(tablero[fila][columna] == Ficha.NEGRA)
				{
					cadena = cadena + "X";
				}
				else if(tablero[fila][columna] == Ficha.BLANCA)
				{
					cadena = cadena + "O";
				}
				else
				{
					cadena = cadena + " ";
				}
			}
			cadena = cadena + "|\n";
		}
		
		// Pintamos +-------+ 
		cadena = cadena + "+";
					
		for(int columna = 0; columna < this.ancho; columna++) 
		{
			cadena = cadena + "-";
		}
			
		cadena = cadena + "+\n ";
		
		// Pintamos los números de las columnas.
		for(int columna = 0; columna < this.ancho; columna++) 
		{
			cadena = cadena + (columna + 1) ;
		}
		cadena = cadena + "\n"+"\n";
		
		return cadena;
	}
	
	
 

}



