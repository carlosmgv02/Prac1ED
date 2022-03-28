package Exceptions;

public class ElementoNoEncontrado extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ElementoNoEncontrado(int n) {
		super("Se han recorrido "+n+" posiciones pero no se ha encontrado el elemento");
	}
}
