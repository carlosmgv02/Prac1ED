package Programa;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Scanner;

import Data.*;
public class main {
	public static FileWriter file;
	public static Scanner scan;
	public static <T extends Comparable<T>>void main(String[] args)throws InterruptedException, IOException  {
		//Ciutada prueba1=new Ciutada("Carlos","Martinez","49424598T");
		
		
		
		Ciutada prueba2=new Ciutada("Carlos","Martinez","49424598J");
		Ciutada prueba3=new Ciutada("Genis","Martinez","12345678R");
		Ciutada prueba4=new Ciutada("David","Marti","777239192R");
		Ciutada prueba5=new Ciutada("Markote11","riosalido","487237842Q");
		
		String prueba1=new String("carlos");
		
		//System.out.println(prueba2.hashCode());
		// PROGRAMA PRINCIPAL PART HASHINGS
		
		mostrarMenu();
		HashTable lista=new HashTable();
		generateNumber(lista,100);
		int n=lista.hashing(prueba2);
		 n=lista.hashing(prueba3);
		n=lista.hashing(prueba4);
		n=lista.hashing(prueba5);
		//prueba.printNelems();
		lista.resize();
		//prueba.printNelems();
		
		searchElement(lista);
		int[]numeros= {1,2,3,4};
		//prueba.writeFile(numeros, 4);
		/*prueba.getAlphaNumericString(10, 5);
		prueba.resize();
		System.out.println("Hash when elements are 300: "+prueba.hashKey("PRjrdszuREmEbkT"));
		System.out.println("Hash when elements are 300: "+prueba.hashKey("g0XUjqZlMbzzJyK"));
		System.out.println("Hash when elements are 300: "+prueba.hashKey("OFDe7gRjEv3OSou"));
		prueba.setNelems(600);
		System.out.println("Hash when elements are 600: "+prueba.hashKey("PRjrdszuREmEbkT"));
		System.out.println("Hash when elements are 600: "+prueba.hashKey("g0XUjqZlMbzzJyK"));
		System.out.println("Hash when elements are 600: "+prueba.hashKey("OFDe7gRjEv3OSou"));
		*/
		
	}
	
	
	public static void mostrarMenu() throws InterruptedException{
		Thread t=Thread.currentThread();
		long initTime;
		long endTime;
		long duration;
		
		System.out.println("BENVINGUT/UDA AL PROGRAMA PRINCIPAL");
		System.out.println("A continuació s'executaran els següents mètodes de manera automàtica:");
		System.out.println("1- Crear taula de hash");
		System.out.println("2- Insertar 100/1.000/10.000 longs a la taula de hash");
		System.out.println("3- Insertar 100/1.000/10.000 String a la taula de hash");
		System.out.println("4- Buscarem alguns elements que existeixin i uns altres que no");
		System.out.println("COMENCEM...\n");
		t.sleep(3500);
		
		HashTable strings;
		HashTable numbers;
		int nElems=100;
		String[]sentence;
		long[]digits;
		int i=1;
		do {
			strings=new HashTable();
			numbers=new HashTable();
			
			//We add the strings to the string array called 'sentence'
			separator();
			//System.out.println(i);
			
			//We add the long numbers to the long array called 'digits'
			initTime=System.nanoTime();
			digits=generateNumber(numbers,nElems);
			//numbers.printHash();
			endTime=System.nanoTime();
			duration=(endTime-initTime);
			System.out.println("\tAfegir els "+nElems+" longs ha trigat "+duration+" ns");
			
			//Trying to find some elements that don't exist and some other that do
			try {
				file=new FileWriter("LogBusqueda.txt");
			
			for(int j=0;j<nElems/5;j++) {
				long elem=digits[j*2];
				//System.out.println("posi :"+j*2+" elem");
				//numbers.findElem(elem);
				//System.out.println(numbers.findElem(elem));
				file.write(numbers.findElem(elem)+'\n');
				file.flush();
				file.write(numbers.findElem(randomInt())+'\n');
				file.flush();
				
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//t.sleep(1500);
			nElems*=10;i++;
		}while(nElems<100000);
		
		int n=0;
		System.out.println("Pots utilitzar el fitxer hashCodes.csv per a fer proves");
		//numbers.printNelems();
		numbers.writeFile();
		System.out.println("Escriu -1 per a sortir del programa");
		do {
		searchElement(numbers);n++;
		}while(n<4);
		
	}
	public static void separator() {
		System.out.println("**********************************************************");
	}
	/**
	 * Método que se encarga de rellenar la tabla con números aleatorios
	 * @param has tabla de hash en la que queremos añadir los nElementos 
	 * @param nElems número de elementos a añadir
	 * @return lista con los números aleatorios añadidos
	 */
	public static long[] generateNumber(HashTable has,Integer nElems) {
		int nDigits=10;
		
		long number;int key;
		String fileName=new String();
		
		fileName=nElems.toString().concat("numbers.csv");
		FileWriter escribir=null;
		long [] array=new long[nElems];
		
			
			for(int i =0;i<nElems;i++) {
				number=randomInt();
				key=has.hashing((long)number);
				array[i]=number;
			}
		return array;
	}
	/**
	 * Método que se encarga de pedir un elemento y buscarlo en la lista pasada como parámetro
	 * @param table tabla en la que buscaremos el elemento que se introduzca
	 */
	public static void searchElement(HashTable table) {
		String data=new String();
		do {
		System.out.println("Escriu l'element que vulguis buscar: ");
		scan=new Scanner(System.in);
		 data=scan.next();
		
		if(isNumeric(data)) {
			if(data.equalsIgnoreCase("-1"))System.exit(0);;
			System.out.println(table.findElem(Long.parseLong(data)));
			
		}
		else
			System.out.println(table.findElem(data));
		}while(!data.equalsIgnoreCase("-1"));
	}
	/**
	 * Comprueba si una String es un número
	 * @param str string a comprobar
	 * @return true if numeric
	 */
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	/**
	 * Genera número long aleatorio
	 * @return random long
	 */
	public static long randomLong() {
		
		long leftLimit=1L;
		long rightLimit;
		long number;
		for(int i=0;i<10-1;i++) {
			leftLimit*=10;

		}
		rightLimit=(leftLimit*10)-1;
		number=leftLimit+(long)(Math.random()*(rightLimit-leftLimit));
		return number;
	}
	/**
	 * Genera un número entero aleatorio
	 * @return random int
	 */
	public static int randomInt() {
		int leftLimit=1;
		int rightLimit;
		int number;
		for(int i=0;i<10-1;i++) {
			leftLimit*=10;
		}
		rightLimit=(leftLimit*10)-1;
		number=leftLimit+(int)(Math.random()*(rightLimit-leftLimit));
		return number;
	}
	
}
