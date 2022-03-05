package Programa;
import java.util.Scanner;

import Data.*;
import Exceptions.ElementoNoEncontrado;
import Exceptions.NoSePuede;
public class main {
	private static Scanner scan;
	public static void main(String[] args) throws ElementoNoEncontrado, NoSePuede {
		// TODO Auto-generated method stub
		int menOpt;
		ListaDoble llista=null;
		do {
		mostrarMenu();
		scan=new Scanner(System.in);
		menOpt=llegirOpcio();
		switch(menOpt) {
		case 1:
			llista=new ListaDoble();
			break;
		case 2:
			if(llista==null)
				System.out.println("ERR: la llista no s'ha creat encara");
			else {
			System.out.println("Introdueix l'element que vols afegir");
			//scan=new Scanner(System.in);
			llista.Inserir(scan.next());}
			break;
		case 3:
			if(llista==null)
				System.out.println("ERR: la llista no s'ha creat encara");
			else {
			//scan=new Scanner(System.in);
			System.out.println("Introdueix la posició on vols inserir l'element");
			int posi=scan.nextInt();
			System.out.println("Introdueix l'element que vols afegir");
			llista.Inserir(posi,scan.next());}
			
			break;
		case 4:
			System.out.println("Introdueix la posició desitjada per a obtenir el seu element: ");
			int ind=scan.nextInt();
			
			System.out.println(llista.Obtenir(ind));
			
			break;
		case 7:
			System.out.println("Introdueix l'element que vols buscar: ");
			//scan=new Scanner(System.in);
			int n=llista.Buscar(scan.next());
			System.out.println("S'han mirat "+n+ " elements fins a trobar el que buscaves");
			break;
		case 8:
			llista.recorrer();
			break;
		}
		System.out.println();
		}while(menOpt!=0);
	}
	public static void mostrarMenu() {
		System.out.println("BENVINGUT/UDA AL PROGRAMA: ");
		System.out.println("1- Crear llista doblement enllaçada");
		System.out.println("2- Inserir un element al final de la llista");
		System.out.println("3- Inserir un element a una posició desitjada");
		System.out.println("4- Obtenir element d'una posició escollida");
		System.out.println("5- Obtenir longitud de la llista enllaçada");
		System.out.println("6- Esborrar element d'una posició escollida");
		System.out.println("7- Comprovar si existeix un element a la llista");
		System.out.println("8- Mostrar el contingut de la llista");
		System.out.println("0- SORTIR");
		System.out.printf("R: ");
	}
	public static int llegirOpcio() {
		scan=new Scanner(System.in);
		int resp=0;
		do {
		try {
			resp=scan.nextInt();
		}catch(NumberFormatException e) {
			resp=1;
			System.out.println("Format de número incorrecte, opció 1 assignada per defecte");
		}}while(resp<0||resp>8);
		return resp;
	}

}
