package Data;
import Exceptions.*;
public class Ciutada implements Comparable<Ciutada>{
	private String nom;
	private String cognom;
	private String DNI;
	public Ciutada(String nom,String cognom,String DNI)  {
		this.DNI=DNI;
		this.nom=nom;
		this.cognom=cognom;
	}
	public String getDni() {
		return DNI;
	}


	@Override
	public int compareTo(Ciutada o) {

		//return (o.getDni().compareTo(nom));
		return o.getDni().compareToIgnoreCase(DNI);
	}
	public boolean esIgual(Ciutada o) {
		if(compareTo(o)==0)
			return true;
		else return false;
	}
	@Override
	public String toString() {
		return "Ciutada [nom=" + nom + ", cognom=" + cognom + ", DNI=" + DNI + "]";
	}





}
