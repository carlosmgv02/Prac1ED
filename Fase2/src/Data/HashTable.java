package Data;
import java.io.*;

import Programa.main;

public class HashTable <T extends Comparable<T>>{
	int tableSize=10;
	HashElem[] tablaHash;
	String fileName;
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
			//tablaHash[x]=new HashElem();
		}
		//System.out.println(tablaHash[1].hashCode());

	}
	public int hashKey(T data) {
		int value=0;
		String temp=new String();
		if(data instanceof Integer||data instanceof Long)
			temp=data.toString();
		else
			temp=(String)data;
		int i=0;
		while(i<temp.length()) {
			value=(value*3+(int)(temp.charAt(i)))%tableSize;
			i++;
		}
		return value;
	}
	/**
	 * Returns hashKey for a long number in a range 0-tablaHash.length
	 * @param data number from which we want to get the hash
	 * @return hash key
	 */
	public int hashKey(long data) {
		int value=0;
		long temp=data;
		int i=5;
		while(temp>0) {
			value+=(temp%10)*i;
			temp=temp/10;
			i++;
		}
		return (value%tableSize);
	}
	/**
	 * Method used to assign the integer to a position of the hashTable
	 * @param data integer we want to assign
	 * @return
	 */

	int hashing(long data) {
		int value=hashKey(data);
		if(tablaHash[value]==null) {
			tablaHash[value]=new HashElem();}
		if(tablaHash[value].estado!=2) {
			tablaHash[value]=new HashElem(data);
			firstElem=value;
			nElems++;
			if(factorCarga())
				resize();
		}
		else if (tablaHash[value].estado==2) {
			counter++;
			tablaHash[value].append(new Nodo(data));
			//nElems++;
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
	public int hashing(T data) {


		int value=0;
		value=hashKey(data);
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
			//nElems++;
			//tablaHash[value].addElems();

			tablaHash[value].append(new Nodo(data));
		}


		return value;
	}
	/**
	 * Method used to generate random long values given an upper limit
	 * @param nNumbers amount of numbers to generate
	 * @param rightLimit upper bound
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
	//public String[] getAlphaNumericString(int nWords,int n)
	public String[] getAlphaNumericString(int nWords,int n)
	{


		//nElems=nWords;
		//System.out.println("COUNTER= "+ counter);
		fileName=nWords+"strings.csv";
		String[] array=new String[nWords];
		// chose a Character random from this String

		try {
			if(firstTime)
				writer=new FileWriter(fileName);
			else
				writer=new FileWriter(fileName,true);
			// create StringBuffer size of AlphaNumericString
			int i=0;

			StringBuilder pb=new StringBuilder();
			for(int j=0;j<nWords;j++) {
				String temp=randomString(n);



				int key=hashing((T)temp);
				//System.out.println("1- key: "+key+" frase: "+temp);
				array[j]=temp;
				writer.write("key= "+key+";"+temp+'\n');
				writer.flush();
				//nElems++;
			}


			writer.close();
			System.out.println("-Valors guardats correctament al fitxer '"+fileName+"'");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		firstTime=false;
		return array;
	}
	public int findElem(T data) {
		int key=0;
		if(data instanceof Long)
			key=hashKey(((Long) data).longValue());
		else if(data instanceof T)
			key=hashKey(data);
		int posi=0;


		//System.out.println(key);

		if(tablaHash[key]!=null&&tablaHash[key].lookFor(data)==1)
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
		String toReturn=new String();
		try {
			toReturn=tablaHash[firstElem].firstElem.data.toString();
		}catch(NullPointerException e) {
			System.out.println("No se puede sugerir ningún código porque no"
					+ "hay elementos añadidos");
		}
		return toReturn;
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
		tableSize*=3;
		int size=tablaHash.length;
		HashElem[]aux=new HashElem[tableSize];
		for(int i=0;i<tablaHash.length;i++) {
			aux[i]=tablaHash[i];

		}
		tablaHash=new HashElem[aux.length];
		int i=0;
		int mode=1;
		int key=0;
		for(int j=0;j<size;j++) {
			//tablaHash[j]=aux[j];
			if(aux[j]!=null) {
				Nodo temp=aux[j].firstElem;
				while(aux[j].firstElem!=null&&aux[j].firstElem.data!=null&&temp!=null) {
					if(temp!=null&&temp.data instanceof Long) {
						System.out.println(temp.data);
						key=hashing((Long) temp.data);

						/*try {
						writeFile(nElems,(T)temp.data,key,mode);
						mode=2;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					}
					else {
						key=hashing((T)temp.data);
						/*try {
						writeFile(nElems,(T)temp.data,key,mode);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					}
					//System.out.println("2- key= "+key+" n= "+temp.data);
					temp=temp.nextCol;
				}
			}
		}

	}

	public void setNelems(int nElems) {
		this.tableSize=nElems;
	}
	public HashElem firstElem() {
		int i=0;
		HashElem temp=new HashElem();
		while(temp==null) {
			temp=tablaHash[i];
			i++;
		}
		return temp;
	}
	/*
	public void writeFile(int nElems,T sentence,int key,int mode) throws IOException {
		if(mode==1) {
			writer=new FileWriter(fileName,true);
		}
		else
			writer=new FileWriter(fileName,true);
		if(sentence instanceof T)
			writer.write("key= "+key+";"+sentence+'\n');
		else if (sentence instanceof Float)
			writer.write("key= "+key+";"+(Long)sentence+'\n');
		writer.flush();

	}*/
	public void writeFile () {
		String fileName=new String();
		FileWriter escribir=null;
		int nElems=tablaHash.length;
		Nodo temp=new Nodo();
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
					temp=tablaHash[i].firstElem;
					while(tablaHash[i].firstElem!=null&&tablaHash[i].firstElem.data!=null&&temp!=null) {
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

