package Programa;
import java.util.Arrays;
import java.util.Scanner;

import Data.*;
public class main {
	public static Scanner scan;
	public static <T extends Comparable<T>>void main(String[] args) {
		
		// PROGRAMA PRINCIPAL PART HASHINGS
		HashElem[] tablaHash=new HashElem[3];
		HashTable<T> carlos=new HashTable<T>();
		carlos.writeFile();
		String prueba="carlos";
		int carlitos=3;
		T data;
		int hash=0;
		data=(T)"calros";
		//System.out.println(HashTable.getAlphaNumericString(300, 5));
		long time=System.nanoTime();
		System.out.println(carlos.getAlphaNumericString(1000, 9));
		long endTime=System.nanoTime();
		long duracion=(endTime-time);
		
		//carlos.printHash();
		System.out.println(carlos.getAlphaNumericString(1000, 9));
		//carlos.printHash();
		System.out.println("El algoritmo ha tardado "+duracion+" ns");
		System.out.println("Min val= "+carlos.getMin());
		scan=new Scanner(System.in);
		System.out.println(carlos.getOneSentence());
		System.out.println("Introduce el texto a bucar");
		String textoAbuscar=scan.nextLine();
		time=0;endTime=0;
		System.out.println();
		time=System.nanoTime();
		carlos.findElem((T)textoAbuscar);
		endTime=System.nanoTime();
		duracion=((endTime-time));
		System.out.println("El algoritmo de búsqueda ha tardado "+ duracion+" ns");

	}
	
	public void mostrarMenu() {
		System.out.println("BENVINGUT/UDA AL PROGRAMA PRINCIPAL");
		System.out.println("1- Crear taula de hash");
		System.out.println("2- Insertar element a la taula de hash");
		System.out.println("3- ");
	}

}
