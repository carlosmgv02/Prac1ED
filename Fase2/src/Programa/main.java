package Programa;
import java.io.IOException;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Scanner;

import Data.*;
public class main {
	public static Scanner scan;
	public static <T extends Comparable<T>>void main(String[] args)throws InterruptedException  {
		
		// PROGRAMA PRINCIPAL PART HASHINGS
		mostrarMenu();
		HashTable prueba=new HashTable();
		prueba.getAlphaNumericString(10, 5);
		prueba.resize();
		System.out.println("Hash when elements are 300: "+prueba.hashKey("PRjrdszuREmEbkT"));
		System.out.println("Hash when elements are 300: "+prueba.hashKey("g0XUjqZlMbzzJyK"));
		System.out.println("Hash when elements are 300: "+prueba.hashKey("OFDe7gRjEv3OSou"));
		prueba.setNelems(600);
		System.out.println("Hash when elements are 600: "+prueba.hashKey("PRjrdszuREmEbkT"));
		System.out.println("Hash when elements are 600: "+prueba.hashKey("g0XUjqZlMbzzJyK"));
		System.out.println("Hash when elements are 600: "+prueba.hashKey("OFDe7gRjEv3OSou"));
		
		
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
			digits=numbers.getNumericLong(nElems, 15);
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
		
	}
	public static void separator() {
		System.out.println("**********************************************************");
	}
	
	
}
