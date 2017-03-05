package tp.pr2;

import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Partida p;
				Scanner in;
				Controlador c;
				ReglasJuego r;
				//creamos unas nuevas reglas de juego de conecta4
				r=new ReglasConecta4();
				//creamos una partida nueva
				p=new Partida(r) ;
				in = new Scanner(System.in);
				c=new Controlador (p, in);
				c.run();
				
			

	}

}
