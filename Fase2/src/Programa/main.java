package Programa;

import java.io.FileNotFoundException;
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
		// PROGRAMA PRINCIPAL PART HASHINGS
		int opcio=1;	//1-Ints(default)	2-Ciutadans
		switch(opcio) {
		case 1:
			//Comprovació utilitzant ints
			mostrarMenu();
			break;
		case 2:
			//Comprovació utilitzant Ciutadans
			HashTable tab=new HashTable();
			Ciutada ciudadano=new Ciutada("Carlos","Martínez","49424598J");
			Ciutada ciudadano2=new Ciutada("Genis","Martínez","49422343K");
			Ciutada ciudadano3=new Ciutada("David","Martí","77726323A");
			Ciutada ciudadano4=new Ciutada("Albert","Solé","49424598Z");
			tab.hashing(ciudadano);
			tab.hashing(ciudadano2);
			tab.hashing(ciudadano3);
			tab.hashing(ciudadano4);
			searchElement(tab);
			break;
		}
	}
	
	
	public static void mostrarMenu() throws InterruptedException, IOException{
		Thread t=Thread.currentThread();
		long duration=0L;
		
		long initTime;
		long endTime;
		String print=new String();
		Long []searchElems;
		FileWriter search=null;
		//long duration;
		try {
			file=new FileWriter("CosteComputacional.csv");
			file.write("LIST SIZE;N SEARCHES;STDEV\n");
			file.flush();
			//file.close();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("BENVINGUT/UDA AL PROGRAMA PRINCIPAL");
		System.out.println("A continuació s'executaran els següents mètodes de manera automàtica:");
		System.out.println("1- Crear taula de hash");
		System.out.println("2- Insertar 100/1.000/10.000 longs a la taula de hash");
		System.out.println("3- Insertar 100/1.000/10.000 String a la taula de hash");
		System.out.println("4- Buscarem alguns elements que existeixin i uns altres que no");
		System.out.println("COMENCEM...\n");
		//t.sleep(3500);
		
		
		int nElems=100;
		HashTable numbers=null;
		int[]digits;
		int i=0;
		do {
			
			numbers=new HashTable();
			//We add the strings to the string array called 'sentence'
			separator();
			
			
			//We add the long numbers to the long array called 'digits'
			initTime=System.nanoTime();
			digits=generateNumber(numbers,nElems);
			//numbers.printHash();
			endTime=System.nanoTime();
			
			duration=(endTime-initTime);
			System.out.println("\tAfegir els "+nElems+" "+digits.getClass().getSimpleName()+ "  ha trigat "+duration+" ns");
			
			//Trying to find some elements that don't exist and some other that do
			try {
				//Escribimos en un fichero los elementos que encuentra y los que no, para poder visualizarlo más comodamente
				search=new FileWriter("LogBusqueda.txt");
			searchElems=new Long[nElems/5];
			for(int j=0;j<nElems/5;j++) {
				int elem=digits[j*2];
				initTime=System.nanoTime();
				searchElems[j]=Long.valueOf(numbers.findElem(elem));
				if(searchElems[j]==-1) {
					//System.out.println("yep");
					print="Element "+elem+" not found";
				}
				else
					print=searchElems[j]+" iterations 'till element found";
				endTime=System.nanoTime();
				duration=(endTime-initTime);
				search.write(print+'\n');
				search.flush();
				int iter=numbers.findElem(randomInt());
				if(iter==-1)
					print="Element "+elem+" not found";
				else
					print=iter+" iterations 'till element found";
				search.write(print+'\n');
				search.flush();
				writeFile("CosteComputacional.csv",nElems,searchElems[i],searchElems);
				
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//t.sleep(1500);
			nElems*=10;i++;
		}while(nElems<=100000);
		
		int n=0;
		System.out.println("Pots utilitzar el fitxer hashCodes.csv per a fer proves");
		
		numbers.writeFile();
		System.out.println("Escriu -1 per a sortir del programa");
		do {
		searchElement(numbers);n++;
		}while(n<4);
		//costes.close();
	}
	public static double stDev(Long[]nums) {
		double stDev=0.0,sum=0.0;
		for(int i=0;i<nums.length&&nums[i]!=null;i++) {
			sum+=nums[i];
		}
		double media=sum/nums.length;
		for(int j=0;j<nums.length&&nums[j]!=null;j++) {
			stDev+=Math.pow(nums[j]-media, 2);
		}
		 double sq = stDev / nums.length;
	        stDev = Math.sqrt(sq);
		return stDev;
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
	public static int[] generateNumber(HashTable has,Integer nElems) {
		int nDigits=10;
		
		int number;int key;
		String fileName=new String();
		
		fileName=nElems.toString().concat("numbers.csv");
		FileWriter escribir=null;
		int [] array=new int[nElems];
		
			
			for(int i =0;i<nElems;i++) {
				number=randomInt();
				key=has.hashing(number);
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
		try { 
		data=scan.next();
		
		if(isNumeric(data)) {
			if(data.equalsIgnoreCase("-1"))System.exit(0);;
			System.out.println(table.findElem(Integer.parseInt(data)));
			
		}
		else
			System.out.println(table.findElem(data));
		}catch(NumberFormatException e){
			System.out.println("EL número introduit no és vàlid");
		}
		}while(!data.equalsIgnoreCase("-1"));
		
	}
	public static void writeFile(String fileName,int listLength,Long accesos,Long[]soFar) {
		//FileWriter file=null;
		//double secs=(double)accesos/1000000000.0;
		try {
			file.write(listLength+";"+accesos+";"+stDev(soFar)+"\n");
			
				file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
