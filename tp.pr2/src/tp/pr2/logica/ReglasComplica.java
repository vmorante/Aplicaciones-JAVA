package tp.pr2.logica;

public class ReglasComplica implements ReglasJuego {
	
	
	
	
	
	//Constantes.
		final int FILA=7;
		final int COLUMNA=4;
	
	public ReglasComplica(){
		
	}
	
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
		boolean grupoBlanco=Grupo.fichaGanador(t,Ficha.BLANCA),grupoNegro=Grupo.fichaGanador(t,Ficha.NEGRA);
		
		
		if(grupoBlanco && grupoNegro)
			ganador=Ficha.VACIA;
		else if (grupoBlanco)
				ganador=Ficha.BLANCA;
		else if(grupoNegro)
			ganador=Ficha.NEGRA;
		else
			ganador=Ficha.VACIA;
		
	
		
		return ganador;
	}
	
	
	public boolean tablas(Ficha ultimoEnPoner,Tablero t){
		return false;
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
