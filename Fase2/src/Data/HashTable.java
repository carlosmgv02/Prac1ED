package Data;
import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import Exceptions.*;
public class HashTable <K,T extends Comparable<T>>implements TADTaulaHash<K,T>{
	Scanner scan;
	int tableSize=10;


	Nodo<K,T>[] tablaHash;
	String fileName;
	int firstElem;
	int counter;
	int nElems;
	boolean firstTime=true;
	FileWriter writer;
	public HashTable(int nElems){
		tablaHash=new Nodo[nElems];
		tableSize=nElems;
	}
	/*
	 * Constructor principal de la clase HashTable
	 */
	public HashTable() {
		tablaHash=new Nodo[tableSize];
		nElems=0;

	}
	public int getIndex(K key){
		int hash=hash(key);
		return hash%tablaHash.length;
	}

	/**
	 * Method used to return an object's hash
	 * @param key key from which we want to obtain the hash
	 * @return hash of the key passed by parameter
	 */
	public int hash(K key){

		String str=key.toString();
		BigInteger power;
		int hash=key.hashCode();

			/*for(int i=1;i<str.length();i++){

				hash+=((int)str.charAt(i))*(32+i);
			}*/
			hash=hash < 0 ? hash * -1 : hash;
		return hash;
	}

	/**
	 * Alternative method used to return an object's hash
	 * @param key object from we want to obtain the hash
	 * @return hash of the object passed by parameter
	 */
	public int hashKey(K key){
		String str=key.toString();
		int res=0;
		for(int i=0;i<str.length();i++){
			res+=str.charAt(i)*Math.pow(32,i);
		}
		res=res < 0 ? res * -1 : res;
		return res;
	}
	@Override
	public void Crear() {
		tablaHash=new Nodo[tableSize];
	}

	@Override
	public void Inserir(K key, T data)  {

		if(factorCarga()>=0.75){
			//printTable();
			try{
			resize();

			}catch(ElementoNoEncontrado e){

			}
			//System.out.println("RESIZED");
		}
		int hash=hash(key);
		int index=getIndex(key);
		if(tablaHash[index]==null){
			tablaHash[index]=new Nodo(key,data,hash);
			nElems++;
		}
		else{
			try{
				int offset=Buscar(key);
				replace(index,offset,data);
			}catch (ElementoNoEncontrado e){
				tablaHash[index].add(key,data,hash);
				//nElems++;
			}

		}

	}

	@Override
	public T Obtenir(K key) {
		return null;
	}

	@Override
	public int Buscar(K key) throws ElementoNoEncontrado{
		int hash=hash(key);
		int index=hash%tablaHash.length;
		int c=0;
		Nodo<K,T>temp=tablaHash[index];
		if(temp!=null){
			while(temp!=null){
				if(temp.hash==hash){
					//System.out.println("FOUND at "+index+" /hash: "+hash);
					return c;
				}
				temp=temp.nextCol;
				c++;
			}

		}
		throw new ElementoNoEncontrado(c);
	}
	@Override
	public int Mida() {
		return nElems;
	}

	/**
	 * Mètode que esborra un element en cas que existeixi
	 * @param key clau de l'element
	 * @throws ElementoNoEncontrado
	 */
	@Override
	public void Esborrar(K key) throws ElementoNoEncontrado {
		int hash=hash(key);
		int index=getIndex(key);
		Nodo<K, T> elem=tablaHash[index];
		if(elem!=null){
			try {
				int offset = Buscar(key);
				if (offset == 0) {
					elem=elem.nextCol;
				} else {
					while (elem.nextCol.hash != hash) {
						elem = elem.nextCol;
					}
					elem.nextCol = elem.nextCol.nextCol;
				}
				}catch(ElementoNoEncontrado e){
					System.out.println(e.getMessage());
				}
		}
		else{
			throw new ElementoNoEncontrado(0);
		}
	}
	public ListaDoble<K,T>ObtenirValors(){
		Nodo<K,T>aux;
		ListaDoble listaAux=new ListaDoble();
		for(int i=0;i<tablaHash.length;i++){
			aux=tablaHash[i];
			while(aux!=null){
				listaAux.Inserir(aux);
				aux=aux.nextCol;
			}
		}
		return listaAux;
	}

	public ListaDoble<K,T>ObtenirClaus(){
		Nodo<K,T>aux;
		ListaDoble listaAux=new ListaDoble();
		for(int i=0;i<tablaHash.length;i++){
			aux=tablaHash[i];
			while(aux!=null){
				listaAux.Inserir((Comparable)aux.key);
				aux=aux.nextCol;
			}
		}
		return listaAux;
	}


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
	public void resize() throws ElementoNoEncontrado {
		//Nodo[]listaAux=new Nodo[tablaHash.length*2];
		HashTable<K,T> tablaAux=new HashTable<>(tablaHash.length*2);
		HashTable <K,T> aux=new HashTable<>();
		Nodo temp;
		for(int i=0;i<tablaHash.length;i++){
			temp=tablaHash[i];
			while(temp!=null){
				tablaAux.Inserir((K)temp.key,(T)temp.data);
				//System.out.println("Num: "+temp.data+"\tHash: "+tablaAux.hash((K)temp.key)+", index: "+tablaAux.getIndex((K)temp.key));
				temp=temp.nextCol;
			}

		}
		tablaHash=tablaAux.tablaHash;
		tableSize=tablaHash.length;

	}

	/**
	 * Mètode auxiliar per a sobreescriure un valor en cas de que la clau ja existeixi
	 * @param index index de la taula de hahs
	 * @param offset número de la col·lisió
	 * @param data nou valor a assignar
	 */
	public void replace(int index,int offset,T data){
		Nodo node=tablaHash[index];
		int i=0;
			while(i!=offset&&node!=null){
				node=node.nextCol;
				i++;
			}
			try{
			node.setData(data);

			}catch(NullPointerException e){

			}

	}


	public void printTable(){
		Nodo aux;//=tablaHash[0];
		for(int i=0;i< tablaHash.length;i++){
			aux=tablaHash[i];
			while(aux!=null){
				System.out.println("Index: "+i+"\t Valor: "+aux.data+"\t hash: "+aux.hash);
				aux=aux.nextCol;
			}
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

							try {
								escribir.write("key= "+i+"; "+temp.data+"; hash= "+temp.hash+"\n");
								escribir.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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

