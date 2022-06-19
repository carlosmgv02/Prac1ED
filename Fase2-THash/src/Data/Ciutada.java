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

	public boolean validar() {

		String letraMayuscula = "";

		if(DNI.length() != 9 || Character.isLetter(this.DNI.charAt(8)) == false )
			return false;
		
		letraMayuscula = (this.DNI.substring(8)).toUpperCase();

		if(soloNumeros() == true && letraDNI().equals(letraMayuscula))
			return true;
		
		else return false;
		
	}

	private boolean soloNumeros() {

		int i, j = 0;
		String numero = ""; 
		String miDNI = ""; 
		String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

		for(i = 0; i < this.DNI.length() - 1; i++) {
			numero = this.DNI.substring(i, i+1);

			for(j = 0; j < unoNueve.length; j++) 
				if(numero.equals(unoNueve[j]))
					miDNI += unoNueve[j];	
		}
		if(miDNI.length() != 8) return false;
		else return true;
	}

	private String letraDNI() {

		int miDNI = Integer.parseInt(this.DNI.substring(0,8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

		resto = miDNI % 23;
		miLetra = asignacionLetra[resto];
		return miLetra;
	}


}
