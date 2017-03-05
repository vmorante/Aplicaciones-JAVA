package tp.pr2.logica;
import tp.pr2.logica.Tablero;
public class ReglasConecta4 implements ReglasJuego {
	
	
	
	//Constantes.
		final int FILA=6;
		final int COLUMNA=7;
	
	public ReglasConecta4(){}
	
	public Tablero iniciaTablero(){
		Tablero tablero=new Tablero(COLUMNA, FILA);
		return tablero;
	}
	public Ficha jugadorInicial(){
		Ficha inicial=Ficha.BLANCA;
		return inicial;		
	}
	public Ficha hayGanador(Movimiento ultimoMovimiento,Tablero t){
		Ficha ganador;
		
		boolean grupo=Grupo.fichaGanador(t,ultimoMovimiento.getJugador());
		
		
		if(grupo)
			ganador=ultimoMovimiento.getJugador();
		else
			ganador=Ficha.VACIA;
		
	
		
		return ganador;
	}
	public boolean tablas(Ficha ultimoEnPoner,Tablero t){
		boolean tablas=true;
		int col=1;
		//comprobamos si la primera fila de las columnas esta vacia
		while(tablas && col<=t.getAncho()){
			if(t.getCasilla(col,1)==Ficha.VACIA)
				tablas=false;
		 col++;
		}
		return tablas;
	}
	public Ficha siguienteTurno(Ficha ultimoEnPoner,Tablero t){
		Ficha siguienteTurno;
		
		if(ultimoEnPoner == Ficha.BLANCA) 
			siguienteTurno = Ficha.NEGRA;		
		else 
			siguienteTurno = Ficha.BLANCA;
				
		return siguienteTurno;
	}
	
	
}















