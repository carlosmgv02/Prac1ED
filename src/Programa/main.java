package Programa;
import Data.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaDoble prueba=new ListaDoble();
		prueba.Inserir("Carlos");
		prueba.Inserir(123);
		prueba.Inserir("Espialidoso");
		prueba.recorrer();
		prueba.Esborrar(2);
		int n=3;
		Nodo trial=(Nodo)prueba.Obtenir(n);
		
		System.out.println("El valor de la posición "+ n +" es: "+ trial.getData());
		Ciutada carlos=new Ciutada("Carlos","Martinez","49424598J");
		Ciutada prueba1=new Ciutada("Genis","Martinez","49424598J");
		System.out.println(carlos.esIgual(prueba1));
		/*
		ListaEnlazada lista=new ListaEnlazada();
		lista.add1(123);
		lista.add1("Prueba");
		lista.add1(6456);
		*/
	}

}
