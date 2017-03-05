package tp.pr2.logica;

public abstract class Movimiento {
	private Ficha turno;
	
	public Movimiento(Ficha color){
		this.turno=color;
	}
	
	public  Ficha getJugador(){
		return this.turno;
	}
	public abstract boolean ejecutaMovimiento(Tablero tab);
	public abstract void undo(Tablero tab);

}
