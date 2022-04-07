package Data;

import java.io.*;

public class HashTable <T extends Comparable<T>>{
	final int tableSize=300;
	HashElem[] tablaHash;
	int firstElem;
	int minVal=300;
	int counter;
	int leastEl=0;
	int nElems;
	boolean firstTime=true;
	FileWriter writer;
	
	public HashTable() {
		
		tablaHash=new HashElem[tableSize];
		for(int x=0;x<tablaHash.length;x++) {
			tablaHash[x]=new HashElem();
		}
		//System.out.println(tablaHash[1].hashCode());

	}
	public int hashKey(T data) {
		int value=0;
		String temp=(String)data;
		int i=0;
		while(i<temp.length()) {
			value=(value*3+(int)(temp.charAt(i)))%tablaHash.length;
			i++;
		}
		return value;
	}

	int hashing(T data) {
		int value=hashKey(data);
		if(tablaHash[value]==null) {
			tablaHash[value]=new HashElem();
		}
		if(tablaHash[value].estado!=2) {
			tablaHash[value]=new HashElem(data);
			firstElem=value;
			nElems++;
			if(factorCarga())
				resize();
		}
		else if(tablaHash[value].estado==2) {
			int val=0;     
			//System.out.println("OCUPADO");
			counter++;
			//tablaHash[value].addElems();

			tablaHash[value].append(new Nodo(data));
		}

		return value;
	}
	//public String[] getAlphaNumericString(int nWords,int n)
	public void getAlphaNumericString(int nWords,int n)
	{
		System.out.println("COUNTER= "+ counter);
		
		String[] array=new String[nWords];
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		int i=0;
		
			
		try{
			if(firstTime)
				writer=new FileWriter("test.csv");
			else
				writer=new FileWriter("test.csv",true);
			StringBuilder pb=new StringBuilder();
		for(int j=0;j<nWords;j++) {
			
			StringBuilder sb = new StringBuilder(n);

			for ( i = 0; i < n; i++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index
				= (int)(AlphaNumericString.length()
						* Math.random());

				// add Character one by one in end of sb
				sb.append(AlphaNumericString
						.charAt(index));
			}
			//System.out.println("Hash: "+hash((T)sb.toString())+" Frase: "+sb.toString());
			//System.out.println("Hash: "+hashing((T)sb.toString())+" Frase: "+sb.toString());
			//System.out.println("Hash: "+hashing((T)sb.toString())+" Frase: "+sb.toString());
			int key=hashing((T)sb.toString());
			//System.out.println("hash: "+key+" frase: "+sb.toString());
			array[j]=sb.toString();
			
				writer.write("key= "+key+";"+sb.toString()+'\n');
				writer.flush();
			
			
			
			
		}
		}catch(IOException e) {
			System.out.println("file not found");
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstTime=false;

		//return array;
	}
	public int findElem(T data) {
		int posi=0;
		int key=0;
		key=hashKey(data);
		//System.out.println(key);
		if(tablaHash[key].lookFor(data)==1)
			System.out.println("El elemento "+data+" estaba en la posición "+key);
		else
			System.out.println("Element not found");
		return posi;
	}
	public void printHash() {
		int i=0;
		while(i<tablaHash.length) {
			try {
				System.out.println("Posi: "+i+", nElems= "+tablaHash[i].nElems);
			}catch(NullPointerException e) {
				System.out.println("Posi: "+i+", nElems=null");
			}
			//tablaHash[i].print();
			i++;
		}
	}

	public int getMin() {
		for(int i=0;i<tablaHash.length;i++) {
			try {
				if(tablaHash[i].nElems<minVal)
					minVal=tablaHash[i].nElems;
			}catch(NullPointerException e) {

			}
		}
		return minVal;
	}
	public String getOneSentence() {
		return tablaHash[firstElem].firstElem.data.toString();
	}
	public boolean factorCarga() {
		//System.out.println("nElems= "+nElems);
		float res=(float)nElems/tablaHash.length;
		//System.out.println(res);
		if(res>0.75) {
			return true;
		}
		return false;
	}
	public void resize() {
		
		HashElem[]aux=new HashElem[tablaHash.length*3];
		for(int i=0;i<tablaHash.length;i++) {
			aux[i]=tablaHash[i];
		}
		tablaHash=new HashElem[aux.length];
		for(int j=0;j<aux.length;j++) {
			tablaHash[j]=aux[j]; 
		}

	}
	public static void writeFile() {

	}
}

