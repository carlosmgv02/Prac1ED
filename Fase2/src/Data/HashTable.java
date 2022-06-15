package Data;
import java.io.*;

import Exceptions.*;
public class HashTable <K,T extends Comparable<T>>implements TADTaulaHash<K,T>{
	int tableSize=10;
	Nodo<K,T>[] tablaHash;
	int nElems;
	public HashTable(int nElems){
		tablaHash=new Nodo[nElems];
		tableSize=nElems;
	}
	/*
	 * Constructor principal de la clase HashTable
	 */
	public HashTable() {
		Crear();


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
		int hash=0;//=key.hashCode();

			for(int i=1;i<str.length();i++){

				hash=(hash*32+(int)str.charAt(i))%tablaHash.length;
			}
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
		nElems=0;
	}

	@Override
	public void Inserir(K key, T data)  {

		if(factorCarga()>=0.75){
			//printTable();
			try{
			resize();
			}catch(ElementoNoEncontrado ignored){
			}
		}
		int hash=hash(key);
		//int index=getIndex(key);
		if(tablaHash[hash]==null){
			tablaHash[hash]=new Nodo<>(key,data,hash);
			nElems++;
		}
		else{
			try{
				int offset=Buscar(key);
				replace(hash,offset,data);
			}catch (ElementoNoEncontrado e){
				tablaHash[hash].add(key,data,hash);

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
		//int index=hash%tablaHash.length;
		int c=1;
		Nodo<K,T>temp=tablaHash[hash];
		if(temp!=null){
			while(temp!=null){
				if(temp.key.equals(key)){
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
		return tablaHash.length;
	}

	/**
	 * Mètode que esborra un element en cas que existeixi
	 * @param key clau de l'element
	 * @throws ElementoNoEncontrado throws exception if element isn't found
	 */
	@Override
	public void Esborrar(K key) throws ElementoNoEncontrado {
		int hash=hash(key);
		//int index=getIndex(key);
		Nodo<K, T> elem=tablaHash[hash];
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
		Nodo<K,T> aux;
		ListaDoble<K,T> listaAux=new ListaDoble<>();
		for (Nodo<K, T> hash : tablaHash) {
			aux = hash;
			while (aux != null) {
				listaAux.Inserir(aux.getData());
				aux = aux.nextCol;
			}
		}
		return listaAux;
	}

	public ListaDoble<K,T>ObtenirClaus(){
		Nodo<K,T>aux;

		ListaDoble<K,T> listaAux=new ListaDoble<>();
		for (Nodo<K, T> hash : tablaHash) {
			aux = hash;
			while (aux != null) {

				listaAux.Inserir((T) aux.key);
				aux = aux.nextCol;
			}
		}
		return listaAux;
	}


	/**
	 * Método que calcula el factor de carga para saber si hay que redimensionar la tabla de hash
	 * @return true si hay que redimensionar
	 */
	public float factorCarga() {

		return (float)nElems/tablaHash.length;
	}
	/**
	 * Método encargado de redimensionar la tabla de hash y recalcular todos los hashes de nuevo
	 */
	public void resize() throws ElementoNoEncontrado {
		//Nodo[]listaAux=new Nodo[tablaHash.length*2];
		HashTable<K,T> tablaAux=new HashTable<>(tablaHash.length*2);
		Nodo<K,T> temp;
		K key;
		T data;
		for (Nodo<K, T> hash : tablaHash) {
			temp = hash;
			while (temp != null) {
				key = temp.getKey();
				data = temp.getData();
				tablaAux.Inserir(key, data);
				temp = temp.nextCol;
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
	/**
	 * METHOD USED TO WRITE A FILE WITH ALL THE VALUES THAT CONTAINS THE HASH TABLE
	 */
	public void writeFile () {

		String fileName;
		FileWriter escribir=null;
		int nElems=tablaHash.length;
		Nodo temp=null;
		try {

			fileName="hashCodes.txt";
			escribir=new FileWriter(fileName);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated catch block
		for(int i=0;i<nElems;i++) {
			try {
				if(tablaHash[i]!=null) {
					temp=tablaHash[i];
					while(temp!=null) {
								escribir.write("key= "+i+"; "+temp.data+"; hash= "+temp.hash+"\n");
								//escribir.write();
								escribir.flush();
						temp=temp.nextCol;	
					}
				} 
			}catch(NullPointerException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			escribir.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}


}

