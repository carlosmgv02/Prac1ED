package Programa;
import Data.*;
import Exceptions.ElementoNoEncontrado;
import Exceptions.NoSePuede;
public class main {

	public static void main(String[] args) throws ElementoNoEncontrado, NoSePuede {
		// TODO Auto-generated method stub
		ListaDoble prueba=new ListaDoble();
		prueba.Inserir("Carlos");
		prueba.Inserir(123);
		prueba.Inserir("Espialidoso");
		prueba.Inserir(2, "Charlitos");
		prueba.recorrer();
		//prueba.Esborrar(2);
		System.out.println("This"+prueba.Buscar(1234));
		
		
		//System.out.println("El valor de la posición "+ n +" es: "+ trial.getData());
		Ciutada carlos=new Ciutada("Carlos","Martinez","49424598J");
		Ciutada prueba1=new Ciutada("Genis","Martinez","49424598J");
		//System.out.println(carlos.esIgual(prueba1));
		/*
		ListaEnlazada lista=new ListaEnlazada();
		lista.add1(123);
		lista.add1("Prueba");
		lista.add1(6456);
		*/
	}

}
