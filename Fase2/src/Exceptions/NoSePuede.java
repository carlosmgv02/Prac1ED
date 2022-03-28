package Exceptions;

public class NoSePuede extends Exception{
	private static final long serialVersionUID = 1L;
	public NoSePuede(int posi) {
		super("No se puede tratar el elemento en la posición: "+posi);
	}
}
