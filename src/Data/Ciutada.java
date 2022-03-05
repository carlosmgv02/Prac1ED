package Data;

public class Ciutada implements Comparable<Ciutada>{
	private String nom;
	private String cognom;
	private String DNI;
	public Ciutada(String nom,String cognom,String DNI) {
		this.nom=nom;
		this.cognom=cognom;
		this.DNI=DNI;
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
	
}
