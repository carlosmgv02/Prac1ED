package Programa;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Scanner;

import Data.*;
public class main {
	public static Scanner scan;
	public static <T extends Comparable<T>>void main(String[] args)throws InterruptedException, IOException  {
		//Ciutada prueba1=new Ciutada("Carlos","Martinez","49424598T");
		
		Ciutada prueba2=new Ciutada("Carlos","Martinez","49424598J");
		String prueba1=new String("carlos");
		
		//System.out.println(prueba2.hashCode());
		// PROGRAMA PRINCIPAL PART HASHINGS
		
		mostrarMenu();
		
		HashTable prueba=new HashTable();
		generateNumber(prueba,15);
		prueba.resize();
		int[]numeros= {1,2,3,4};
		String[]carlos= {"carlos","david","marc","ivan"};
		System.out.println(carlos[2]);
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
		//t.sleep(3500);
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
			System.out.println(i);
			initTime=System.nanoTime();
			sentence=strings.getAlphaNumericString(nElems, 15);	//15 is the length of the string
			endTime=System.nanoTime();	
			duration=(endTime-initTime);
			System.out.println("\tAfegir les "+ nElems+" strings ha trigat "+duration+" ns");
			
			//We add the long numbers to the long array called 'digits'
			initTime=System.nanoTime();
			digits=generateNumber(numbers,nElems);
			//numbers.printHash();
			endTime=System.nanoTime();
			duration=(endTime-initTime);
			System.out.println("\tAfegir els "+nElems+" longs ha trigat "+duration+" ns");
			
			//Trying to find some elements that don't exist and some other that do
			
			for(int j=0;j<nElems/5;j++) {
				String elem=sentence[j*2];
				//System.out.println("posi :"+j*2+" elem");
				strings.findElem(sentence[j*2]);
				
			}
			//t.sleep(1500);
			nElems*=10;i++;
		}while(nElems<100000);
		
		int n=0;
		
		/*do {
		searchElement(numbers);n++;
		}while(n<4);
		*/
		numbers.writeFile();
	}
	public static void separator() {
		System.out.println("**********************************************************");
	}
	public static long[] generateNumber(HashTable has,Integer nElems) {
		long nDigits=15L;
		long leftLimit=1L;
		long rightLimit;
		long number;int key;
		String fileName=new String();
		
		fileName=nElems.toString().concat("numbers.csv");
		FileWriter escribir=null;
		long [] array=new long[nElems];
		for(int i=0;i<nDigits-1;i++) {
			leftLimit*=10;

		}
		rightLimit=(leftLimit*10)-1;
		try{
			escribir=new FileWriter(fileName);
			for(int i =0;i<nElems;i++) {
				number=leftLimit+(long)(Math.random()*(rightLimit-leftLimit));
				key=has.hashing(number);
				array[i]=number;
				//System.out.println("1-key= "+key+" n= "+number);
				escribir.write("key= "+key+";"+number+'\n');
				escribir.flush();
				//nElems++;
			}
		}catch(IOException e) {
			System.out.println("file not found");
		}
		try {
			System.out.println("-Valors guardats correctament al fitxer '"+fileName+"'");
			escribir.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	public void generateString(int nElems) {
		
	}
	public static <T> void searchElement(HashTable table) {
		
		System.out.println("Escribe el elemento que quieras ");
		scan=new Scanner(System.in);
		String data=scan.next();
		
		if(isNumeric(data)) {
			table.findElem(Long.parseLong(data));
		}
		else
			table.findElem(data);
	}
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
}
