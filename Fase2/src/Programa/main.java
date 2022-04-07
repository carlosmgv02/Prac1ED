package Programa;
import java.util.Arrays;

import Data.*;
public class main {

	public static <T extends Comparable<T>>void main(String[] args) {
		// PROGRAMA PRINCIPAL PART HASHINGS
		HashElem[] tablaHash=new HashElem[3];
		HashTable<T> carlos=new HashTable<T>();
		String prueba="carlos";
		int carlitos=3;
		
		System.out.println(carlos.hash((T)prueba));
		String aux="solrac";
		System.out.println(carlos.hash((T)aux));
		
		T data;
		int hash=0;
		data=(T)"calros";
		//System.out.println(HashTable.getAlphaNumericString(300, 5));
		System.out.println(carlos.getAlphaNumericString(300, 5));
		carlos.printHash();
		
			
		int temp=(int)prueba.charAt(0);
		//int aux=Integer.parseInt(prueba);
		
		
		/*
		int i,j;
		for(i=0;i<tablaHash.length;i++) {
			tablaHash[i]=new HashElem(i);
		}
		System.out.println(tablaHash[1].hashCode());
		
		*/
	}
	
	public void mostrarMenu() {
		System.out.println("BENVINGUT/UDA AL PROGRAMA PRINCIPAL");
		System.out.println("1- Crear taula de hash");
		System.out.println("2- Insertar element a la taula de hash");
		System.out.println("3- ");
	}

}
