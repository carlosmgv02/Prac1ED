package Data;

import java.util.*;

import Exceptions.NoSePuede;


public class CiutadaIterator<T extends Comparable <T>>implements Iterator<Ciutada> {
	ListaDoble list;
	int posi;
	public CiutadaIterator(ListaDoble aux) {
	list=aux.copia();
	posi=0;
	}
	@Override
	public boolean hasNext() {
		try {
			return (posi<list.Longitud()&&list.Obtenir(posi)!=null);
		} catch (NoSePuede e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return false;
		
	}

	@Override
	public Ciutada next() {
		Ciutada elem=null;
		try {
			 elem=(Ciutada) list.Obtenir(posi);
		} catch (NoSePuede e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posi++;
		return elem;
	}

}
