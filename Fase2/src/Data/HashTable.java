package Data;

public class HashTable <T extends Comparable<T>>{
	HashElem[] tablaHash;
	int counter;
	public HashTable() {
	tablaHash=new HashElem[300];
	for(int x=0;x<tablaHash.length;x++) {
		tablaHash[x]=new HashElem();
	}
	//System.out.println(tablaHash[1].hashCode());
	
	}
	
	public int hash(T data) {
		int value=0;
		int size=300;
		int i;
		if(data instanceof String ) {
			//System.out.println("String");
			String temp=(String)data;
			for(i=0;i<temp.length();i++) {
				value+=temp.charAt(i)*temp.charAt((temp.length()-1)-i);
			}
			value=value%size;
			if(tablaHash[value].estado!=2)
				tablaHash[value]=new HashElem(data);
			else if(tablaHash[value].estado==2) {
				System.out.println("OCUPADO");

				tablaHash[value].append(new Nodo(data));
				tablaHash[value].addElems();
				counter++;
				return 0;}
		}
		
		
		return value;
	}
	public int hashing(T data) {
		int value=0;
		String temp=(String)data;
		int i=0;
		while(i<temp.length()) {
			value=(value*3+(int)(temp.charAt(i)))%300;
			i++;
		}
		if(tablaHash[value].estado!=2)
			tablaHash[value]=new HashElem(data);
		else if(tablaHash[value].estado==2) {
			int val=0;     
			System.out.println("OCUPADO");
			counter++;
			tablaHash[value].append(new Nodo(data));}
		return value;
	}
	public String[] getAlphaNumericString(int nWords,int n)
    {
		String[] array=new String[nWords];
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        int i=0;
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
        System.out.println("Hash: "+hashing((T)sb.toString())+" Frase: "+sb.toString());
        array[j]=sb.toString();
        }
        System.out.println("COUNTER= "+ counter);
  
        return array;
    }
	public void printHash() {
		int i=0;
		while(i<300) {
			System.out.println("Posi: "+i+", nElems= "+tablaHash[i].nElems);
			tablaHash[i].print();
			i++;
		}
	}
	
}

