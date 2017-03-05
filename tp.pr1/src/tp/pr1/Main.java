package tp.pr1;

import tp.pr1.logica.Partida;
import tp.pr1.control.Controlador;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partida p;
		Scanner in;
		Controlador c;
		//creamos una partida nueva
		p=new Partida() ;
		in = new Scanner(System.in);
		c=new Controlador (p, in);
		c.run();
		
	}

}

