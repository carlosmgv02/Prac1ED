package Data;

public class ListaDobleOrdenada<T extends Comparable<T>>extends ListaDoble<T>{
    @Override
    public void Inserir(T data){
        Nodo<T>aux=new Nodo<>(data);
        Nodo<T>temp=inicio;

        if(!empty()){
            aux.setSiguiente(inicio);
            inicio.setAnterior(aux);
            inicio=aux;
        }
        else{
            inicio=fin=aux;
        }
    }
}
