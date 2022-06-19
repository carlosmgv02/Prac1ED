package Programa;

import java.io.*;
import java.util.*;

import Data.*;
import Exceptions.ElementoNoEncontrado;

public class main {

	public static void main(String[] args)throws InterruptedException, IOException  {
		// PROGRAMA PRINCIPAL PART HASHINGS

		HashTable<String,Ciutada>tablaAux=new HashTable<>();

		Ciutada carlos=new Ciutada("Carlos","Martinez","49424598J");
		Ciutada david=new Ciutada("David","Marti","7771391023");
		Ciutada nil=new Ciutada("Carlos","Martinez","44548898T");
		Ciutada genis=new Ciutada("Genis","Martinez","73981391P");
		Ciutada roger=new Ciutada("Roger","Massana","3731918T");
		Ciutada lluis=new Ciutada("Lluis","Gallart","713739189O");
		Ciutada gerard=new Ciutada("Gerard","Panisello","3241233Y");
		Ciutada eros=new Ciutada("Eros","Villar","1413133T");

		try{
			tablaAux.Inserir("49424598J",carlos);
			tablaAux.Inserir("7771391023",david);
			tablaAux.Inserir("44548898T",nil);
			tablaAux.Inserir("73981391P",genis);
			tablaAux.Inserir("3731918T",roger);
			tablaAux.Inserir("713739189O",lluis);
			tablaAux.Inserir("3241233Y",gerard);
			tablaAux.Inserir("1413133",eros);

			System.out.println(tablaAux.Buscar("49424598J"));
			System.out.println(tablaAux.Buscar("7771391023"));
			System.out.println(tablaAux.Buscar("44548898T"));
			System.out.println(tablaAux.Buscar("73981391P"));
			System.out.println(tablaAux.Buscar("3731918T"));
			System.out.println(tablaAux.Buscar("713739189O"));
			System.out.println(tablaAux.Buscar("3241233Y"));
			System.out.println(tablaAux.Buscar("1413133"));



		}catch(ElementoNoEncontrado e){

		}



		//Joc de proves de la taula de hash
		mostrarMenu();
		System.out.println("TABLE DONE");
		//Anàlisi de la llista doblement encadenada
		JocProvesLlista();
		System.out.println("LIST DONE");
	}


	public static void mostrarMenu() throws IOException {

		int searchElems;

		System.out.println("BENVINGUT/UDA AL PROGRAMA PRINCIPAL");
		System.out.println("A continuació s'executaran els següents mètodes de manera automàtica:");
		System.out.println("-Després de finalitzar cada inerció dels elements a la taula de hash anirem buscant" +
				" números generats aleatoriament");
		System.out.println("-Un cop generades totes les taules de hash i calculats els seus costos d'accès," +
				"generarem un fitxer que recollirà un anàlisi del cost mig i desviació estandard tenint en compte" +
				"els factors mencionats previament.");
		System.out.println("COMENCEM...\n");
		//t.sleep(3500);


		int nElems=1000;
		HashTable<Integer,Integer> numbers;
		int[]digits;
		int i=0;
		ArrayList<ArrayList<Integer>>llistaAux=new ArrayList<>();
		do {
			System.out.println("nElems= "+nElems);
			llistaAux.add(new ArrayList<>());
			numbers=new HashTable<>(nElems);
			digits = new int[nElems];

			for(int k=0;k<nElems;k++) {
				digits[k] = randomInt(nElems/2);

				numbers.Inserir(digits[k],digits[k]);
			}

			String fileName="Analisi/LogCerques/"+nElems+"searches.txt";
			PrintStream output;
			try {
				output = new PrintStream(new FileOutputStream(fileName));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			System.setOut(output);
			//Escribimos en un fichero los elementos que encuentra y los que no, para poder visualizarlo más comodamente

			for(int j=0;j<nElems;j++) {

				try{
					searchElems=numbers.Buscar(randomInt(numbers.Mida()/2));
					llistaAux.get(i).add(searchElems);
					//totalSearches[i]+=searchElems;
					System.out.println(searchElems+" iteration until element has been found");

				}catch(ElementoNoEncontrado e){
					searchElems=e.getN();
					llistaAux.get(i).add(searchElems);
					System.out.println(e.getMessage());
				}

			}
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			nElems+=1000;i++;
		}while(nElems<=50000);
		//numbers.writeFile();

		//Escritura del fitxer
		FileWriter analisis=new FileWriter("Analisi/CostCompuTaula.csv");
		analisis.write("MIDA;"+"N ACCESSOS;"+"DESV EST\n");
		int Elems=1000;
		for (ArrayList<Integer> lista : llistaAux) {
			analisis.write(Elems + ";" + mean(lista) + ";" + stDev(lista)+"\n");
			Elems += 1000;
		}
		analisis.close();


	}
	public static void JocProvesLlista(){
		ListaDoble<Integer,Integer>lista;
		int nElems=1000;
		int[]digits;
		int searchElems;
		ArrayList<ArrayList<Integer>>llistaAux=new ArrayList<>();
		int i=0;
		FileWriter analisis=null;
		try {
			analisis = new FileWriter("Analisi/CostCompuLlista.csv");
			analisis.write("MIDA;"+"N ACCESSOS;"+"DESV EST\n");
		}catch(IOException e) {
			System.out.println("FILE NOT FOUND");
		}
		do{
			lista=new ListaDoble<>();
			System.out.println("nElems= "+nElems);
			llistaAux.add(new ArrayList<>());
			digits=new int[nElems];
			for(int j=0; j<nElems; j++){
				digits[j]=randomInt(nElems/2);
				lista.Inserir(digits[j]);
			}
			for(int k=0;k<nElems;k++){
				try{
					searchElems=lista.Buscar(randomInt(nElems/2));
					llistaAux.get(i).add(searchElems);
				}catch(ElementoNoEncontrado e){
					searchElems=e.getN();
					llistaAux.get(i).add(searchElems);
					//System.out.println(e.getMessage());
				}
			}


			try {

				analisis.write(nElems + ";" + mean(llistaAux.get(i)) + ";" + stDev(llistaAux.get(i)) + "\n");
			}
			catch(NullPointerException e){
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("FILE ERROR");
			}
			i++;
			nElems+=1000;
		}while(nElems<=50000);
		try{
			analisis.close();

		}catch(IOException e){

		}


	}
	public static double mean(ArrayList<Integer> lista){
		int sum=0;
		for(int temp: lista){
			sum+=temp;
		}
		return (double)sum/ lista.size();

	}
	public static double stDev(ArrayList<Integer>lista) {
		int[]nums=new int[lista.size()];
		for(int i=0;i<lista.size();i++){
			nums[i]=lista.get(i);
		}
		double stDev=0.0,sum=0.0;
		for(int i=0;i<nums.length&&nums[i]!=0;i++) {
			sum+=nums[i];
		}
		double media=sum/nums.length;
		for(int j=0;j<nums.length&&nums[j]!=0;j++) {
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
	 * Genera un número entero aleatorio
	 * @return random int
	 */
	public static int randomInt(int rightLimit) {
		int leftLimit=1;
		return leftLimit+(int)(Math.random()*(rightLimit-leftLimit));
	}

}
