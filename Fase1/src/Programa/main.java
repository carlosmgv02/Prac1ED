package Programa;
import java.util.InputMismatchException;
import java.util.*;

import Data.*;
import Exceptions.ElementoNoEncontrado;
import Exceptions.NoSePuede;
public class main {
	private static Scanner scan;
	public static void main(String[] args) throws ElementoNoEncontrado, NoSePuede {
		// TODO Auto-generated method stub
		ListaDoble<Ciutada> lista=new ListaDoble<Ciutada>(4);
		Boolean bool=false;
		ListaDoble <Ciutada>listita=new ListaDoble<Ciutada>();
		Ciutada ciudadano=new Ciutada("Carlos","Martínez","49424598J");
		Ciutada ciudadano2=new Ciutada("Genis","Martínez","49422343K");
		Ciutada ciudadano3=new Ciutada("David","Martí","77726323A");
		Ciutada ciudadano4=new Ciutada("Albert","Solé","49424598Z");
		listita.Inserir(ciudadano);
		listita.Inserir(ciudadano2);
		listita.Inserir(ciudadano3);
		listita.Inserir(ciudadano4);
		programaPrinc(listita);
		
		listita.recorrer();
		
	}
	public static<T> void programaPrinc(ListaDoble llista) throws ElementoNoEncontrado, NoSePuede {
		int menOpt;
		//ListaDoble llista=null;
		
		do {
		mostrarMenu();
		scan=new Scanner(System.in);
		menOpt=llegirOpcio();
		switch(menOpt) {
		/**
		 * 1-Creació de la llista doblement encadenada
		 */
		case 1:
			llista=new ListaDoble();
			break;
		/**
		 * 2-Demanem a l'usuari un Ciutadà i l'afegim a la llista
		 */
		case 2:
			String name=new String();
			String lastName=new String();
			String id=new String();
			if(llista==null)
				System.out.println("ERR: la llista no s'ha creat encara");
			else {
			System.out.println("Introdueix el nom i cognom (separats per un espai) del Ciutadà que vols afegir");
			scan.nextLine();
			String str=scan.nextLine();
			try {
				name=str.split(" ")[0];
				lastName=str.split(" ")[1];
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
				System.out.println("Elon Musk definit per defecte");
				name="Elon";
				lastName="Musk";
			}
			System.out.println("Introdueix el DNI del ciutadà a afegir");
			id=scan.next();
			llista.Inserir(new Ciutada(name,lastName,id));
			}
			break;
		/**
		 * 3-Demanem una posició i un usuari i l'afegim a la llista en cas de ser possible
		 */
		case 3:
			if(llista==null)
				System.out.println("ERR: la llista no s'ha creat encara");
			else {
			//scan=new Scanner(System.in);
			System.out.println("Introdueix la posició on vols inserir l'element");
			int posi=scan.nextInt();
			System.out.println("Introdueix l'element que vols afegir");
			System.out.println("LLISTA ABANS DE PROVAR INSERCIÓ");
			llista.recorrer();
			llista.Inserir(posi,readCiutada());}
			System.out.println();
			System.out.println("LLISTA DESPRÉS DE PROVAR INSERCIÓ");
			llista.recorrer();
			break;
		/**
		 * 4-Demanem una posició i retornem el seu element en cas de ser possible
		 */
		case 4:
			System.out.println("Introdueix la posició desitjada per a obtenir el seu element: ");
			int ind=scan.nextInt();
			
			System.out.println(llista.Obtenir(ind));
			
			break;
		/**
		 * 5-Retornem la longitud de la llista doblement encadenada
		 */
		case 5:
			System.out.println("La longitud de la llista doblement encadenada es "+llista.Longitud());
			break;
		/**
		 * 6-Demanem una posició i esborrem l'element que es troba en ella en cas de ser possible
		 */
		case 6:
			int toDelete=0;
			int length=llista.Longitud();
			System.out.println("Introdueix l'index de la posició que vols eliminar");
			try {
			toDelete=scan.nextInt();
			}catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("LLISTA ABANS DE PROVAR ELIMINACIÓ");
			llista.recorrer();
			System.out.println();
			llista.Esborrar(toDelete);
			if(llista.Longitud()!=length)
				System.out.println("S'ha esborrat l'element correctament");
			else
				System.out.println("No s'ha esborrat cap element");
			System.out.println("LLISTA DESPRÉS DE PROVAR ELIMINACIÓ");
			llista.recorrer();
			break;
		/**
		 * 7-Demanem un número de DNI i intentem trobar si hi ha alguna coincidència
		 */
		case 7:
			System.out.println("Introdueix l'element que vols buscar: ");
			//scan=new Scanner(System.in);
			String temp=scan.next();
			int n=llista.Buscar(temp);
			System.out.println("S'han mirat "+n+ " elements fins a trobar el que buscaves");
			break;
		case 8:
			llista.recorrer();
			break;
		default:
			
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
	
	/**
	 * Demanem la opció del menú que vol executar l'usuari
	 * @return num d'opció
	 */
	public static int llegirOpcio() {
		scan=new Scanner(System.in);
		int resp=0;
		do {
		try {
			resp=scan.nextInt();
		}catch(NumberFormatException e) {
			resp=1;
			System.out.println("Format de número incorrecte, opció 1 assignada per defecte");
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		}while(resp<0||resp>8);
		return resp;
	}
	/**
	 * Llegim dades d'un nou usuari que crearem i retornarem
	 * @return new Ciutadà(nom,cognom,DNI);
	 */
	public static Ciutada readCiutada() {
		Ciutada aux=null;
		String nom,cognom,DNI=new String();
		System.out.println("Introdueix el nom del ciutadà");
		nom=scan.next();
		System.out.println("Introdueix el cognom del ciutadà");
		cognom=scan.next();
		System.out.println("Introdueix el DNI del ciutadà");
		DNI=scan.next();
		aux=new Ciutada(nom,cognom,DNI);
		return aux;
	}
	

}
