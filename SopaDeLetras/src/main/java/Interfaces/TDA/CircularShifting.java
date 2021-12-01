
package Interfaces.TDA;

public interface CircularShifting {
    /**
     * Metodo que permite hacer bitshifting a los elementos de una lista
     * circular (hacia a la derecha). Empieza desde un nodo viajero declarado
     * como inicial,NODO LAST, tratándolo como una lista de tipo lineal hasta 
     * llegar al nodo inicial. Cada vez que itera se obtiene el previous y se
     * modifica el nodo viajero.
     * 
     * En cada iteracion al nodo viajero se inserta el contenido del nodo 
     * anterior.
     * 
     * Complejidad de O(n). Recorre la lista solo una vez.
     * */
    public void doRightBitshifting();
    
    /**
     * Metodo que permite hacer bitshifting a los elementos de una lista
     * circular (hacia a la izquierda). Empieza desde un nodo viajero declarado
     * como inicial,el primer elemento de la lista circular (last.getNext), 
     * tratándolo como una lista de tipo lineal hasta llegar al nodo finalt (last). 
     * Cada vez que itera se obtiene el next y se modifica el nodo viajero.
     * 
     * En cada iteracion al nodo viajero se inserta el contenido del nodo 
     * posterior.
     * 
     * Complejidad de O(n). Recorre la lista solo una vez.
     * */
    public void doLeftBitshifting();
}
