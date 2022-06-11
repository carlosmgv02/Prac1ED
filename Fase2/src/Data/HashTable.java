package Data;
import java.io.*;
import java.math.BigInteger;
import java.util.Objects;

import Programa.main;
import Exceptions.*;
public class HashTable <K,T extends Comparable<T>>implements TADTaulaHash<K,T>{
	int tableSize=10;
	Nodo<K,T>[] tablaHash;
	String fileName;
	int firstElem;
	int counter;
	int nElems;
	boolean firstTime=true;
	FileWriter writer;

	/*
	 * Constructor principal de la clase HashTable
	 */
	public HashTable() {
		tablaHash=new Nodo[tableSize];

	}
	public int getIndex(K key){
		return hash(key)%tablaHash.length;
	}
	public int hash(K key){
		String str=key.toString();
		int power;
		int hash=0;
		for(int i=0;i<str.length();i++){
			power= (int) Math.pow(2,i);
			hash+=((int)str.charAt(i))*power;

		}
		hash=hash < 0 ? hash * -1 : hash;
		return hash;
	}
	public int hashKey(K key){
		String str=key.toString();
		int res=0;
		for(int i=0;i<str.length();i++){
			res=(res*32+(int)str.charAt(i))% tablaHash.length;
		}
		return res;
	}
	@Override
	public void Crear() {
		tablaHash=new Nodo[tableSize];
	}

	@Override
	public void Inserir(K key, T data) {
		if(factorCarga()>=0.75)
			resize();
		int hash=hash(key);
		int index=hash% tablaHash.length;
		if(tablaHash[index]==null){
			tablaHash[index]=new Nodo(key,data,hash);

		}
		else{
			tablaHash[index].add(key,data,hash);
		}
		nElems++;
	}

	@Override
	public T Obtenir(K key) {
		return null;
	}

	@Override
	public int Buscar(K key) throws ElementoNoEncontrado{
		int hash=hash(key);
		int index=hash%tableSize;
		int c=0;
		Nodo<K,T>temp=tablaHash[index];
		if(temp!=null){
			while(temp!=null){
				if(temp.hash==hash)return c;
				temp=temp.nextCol;
				c++;
			}

		}
		throw new ElementoNoEncontrado(c);
	}

	@Override
	public int Mida() {
		return 0;
	}

	@Override
	public void Esborrar(K key) {

	}


	/**
	 * Método que calcula el hash de los elementos genéricos
	 * @param data datos del cual queremos obtener el hash
	 * @return hash
	 */
	/*
	public int hashKey(T data) {
		int value=0;
		String temp;
		if(data instanceof Integer||data instanceof Long)
			temp=data.toString();
		else if(data instanceof String)
			temp=(String)data;
		else if(data instanceof Ciutada) {
			Ciutada ciud=(Ciutada)data;
			temp=ciud.getDni();
		}
		else return (data.hashCode()&0x7fffffff)%tableSize;
		int i=0;
		while(i<temp.length()) {
			value=(value*3+(int)(temp.charAt(i)))%tableSize;
			i++;
		}
		return value;
	}*/

	/**
	 * Returns hashKey for a long number in a range 0-tablaHash.length
	 * @param data number from which we want to get the hash
	 * @return hash key
	 */
	public int hashKey(long data) {
		return (int) (data%tableSize);
	}
	/**
	 * Method used to assign the integer to a position of the hashTable
	 * @param data integer we want to assign
	 * @return
	 */

	int hashing(long data) {
		int value = hashKey(data);
		if (tablaHash[value] == null) {
			//tablaHash[value]=new Nodo(data,value);}
			if (tablaHash[value] == null) {
				//tablaHash[value]=new Nodo(data,value);
				firstElem = value;
				nElems++;
				if (factorCarga() >= 0.75)
					resize();
			} else if (tablaHash[value] != null) {
				counter++;
				//tablaHash[value].add(data,value);
				//nElems++;
			}
		}
			return value;
	}
	/*
	int hashing(Ciutada user) {
		//int key=(prueba1.hashCode()&0x7fffffff)%300;
		int key=(user.getDni().hashCode()&0x7fffffff);
		return key;
	}
	 */

	/**
	 * Método que se encarga de asignar el dato a una posición de la tabla de hash y
	 * gestionar las colisiones
	 * @param data dato que queremos situar en la tabla de hash
	 * @return hash
	 */
	public int hashing(T data) {


		int value=0;
		if(data instanceof Long) {
			value=hashKey((Long)data);
		}
		else if(data instanceof Integer) {
			value=hashKey((Integer)data);
		}
		else 
			value=hashKey((Long) data);
		if(tablaHash[value]==null) {
			//tablaHash[value]=new Nodo<>(data,value);
		}
		if(tablaHash[value]==null) {
			//tablaHash[value]=new Nodo<>(data);
			firstElem=value;
			nElems++;
			if(factorCarga()>=0.75)
				resize();
		}
		else if(tablaHash[value]!=null) {

			//System.out.println("OCUPADO");
			counter++;
			//nElems++;
			//tablaHash[value].addElems();

			//tablaHash[value].addata,value);
		}


		return value;
	}
	public boolean conte(K elem){
		return true;
	}
	/**
	 * Method used to generate random long values given an upper limit
	 * @param nNumbers amount of numbers to generate
	 * @return array of generated long numbers
	 */
	public long[] getNumericLong(Integer nNumbers,long nDigits) {
		//nElems=nNumbers;
		long leftLimit=1L;
		long rightLimit;
		long number;int key;
		fileName=nNumbers.toString().concat("numbers.csv");
		FileWriter escribir=null;
		long [] array=new long[nNumbers];
		for(int i=0;i<nDigits-1;i++) {
			leftLimit*=10;

		}
		rightLimit=(leftLimit*10)-1;
		try{


			if(firstTime)
				escribir=new FileWriter(fileName);
			else
				escribir=new FileWriter(fileName,true);

			for(int i =0;i<nNumbers;i++) {
				number=leftLimit+(long)(Math.random()*(rightLimit-leftLimit));
				key=hashing(number);
				array[i]=number;
				System.out.println("1-key= "+key+" n= "+number);
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

	/**
	 * Método usado para buscar un elemento pasado como parámetro
	 * @param data elemento que queremos buscar
	 * @return texto que usaremos para guardar en el fichero LogBusqueda e imprimir por pantalla
	 */
	public int findElem(K data) {
		int iterations=0;
		int key=0;
		if(data instanceof Integer) {
			key=hashKey((Integer)data);
		}
		else if(data instanceof Long)
			key=hashKey(((Long) data).longValue());
		else if(data != null)
			key=hashKey((Long) data);
		else key=(data.hashCode()&0x7fffffff)%tableSize;
		int posi=0;
		String text=new String();
		if(tablaHash[key]!=null) {
		try {
			iterations=tablaHash[key].lookFor(data);
			return iterations;
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		}
		//System.out.println("El elemento "+data+" estaba en la posición "+key);}
		else {
			//System.out.println("Element not found");
			return -1;
		}
		
		return iterations;
	}
	/**
	 * Método que genera una string aleatoria de longitud length
	 * @param length longitud de la string a generar
	 * @return String generada

	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";
		for (int i = 0; i < length; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index
			= (int)(AlphaNumericString.length()
	 * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString
					.charAt(index));
		}
		return sb.toString();

	}
	 */

	/**
	 * Método que calcula el factor de carga para saber si hay que redimensionar la tabla de hash
	 * @return true si hay que redimensionar
	 */
	public float factorCarga() {
		//System.out.println("nElems= "+nElems);
		float res=(float)nElems/tablaHash.length;
		//System.out.println(res);
		return res;
	}
	/**
	 * Método encargado de redimensionar la tabla de hash y recalcular todos los hashes de nuevo
	 */
	public void resize() {
		Nodo[]listaAux=new Nodo[tablaHash.length*2];
		HashTable<K,T> tablaAux=new HashTable<>();
		HashTable <K,T> aux=new HashTable<>();
		Nodo temp;
		for(int i=0;i<tablaHash.length;i++){
			temp=tablaHash[i];
			while(temp!=null){

			}
			listaAux[i]=tablaHash[i];

		}
		tablaHash=listaAux;
		tableSize=tablaHash.length;

	}

	/**
	 * Método encargado de imprimir todos los elementos y colisiones de la tabla de hash
	 */
	public void printList() {
		Nodo aux=null;
		String toWrite=new String();
		String fileName=new String("hashing.csv");
		try {
			writer=new FileWriter(fileName);

			if(nElems<tablaHash.length) {
				for(int i=0;i<tablaHash.length;i++) {
					if(tablaHash[i]!=null) {
						aux=tablaHash[i];
						while(aux!=null) {
							System.out.println("key= "+i+", data= "+aux.data);
							writer.write("key= "+i+";"+aux.data+'\n');
							writer.flush();
							aux=aux.nextCol;
						}
					}
				}
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * NOT USED METHOD TO WRITE A FILE WITH ALL THE VALUES THAT CONTAINS THE HASH TABLE
	 */
	public void writeFile () {
		String fileName=new String();
		FileWriter escribir=null;
		int nElems=tablaHash.length;
		Nodo temp=null;
		int key;
		try {

			fileName="hashCodes.csv";
			escribir=new FileWriter(fileName);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated catch block
		for(int i=0;i<nElems;i++) {
			try {
				if(tablaHash[i]!=null) {
					temp=tablaHash[i];
					while(tablaHash[i]!=null&&tablaHash[i].data!=null&&temp!=null) {
						if(temp.data instanceof Long||temp.data instanceof Integer) {
							try {
								escribir.write("key= "+i+";"+temp.data+'\n');
								escribir.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else {
							try {
								escribir.write("key= "+i+";"+temp.data+'\n');
								escribir.flush();
							}catch(IOException e) {
								System.out.println(e.getMessage());
							}
						}
						temp=temp.nextCol;	
					}
				} 
			}catch(NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
	}


}

