
package Interfaces.Matrix;


public interface MatrixMoves {
    /**
     * Método que se utiliza para mover una fila hacia a la DERECHA
     * de un objeto de la clase Matrix.
     * 
     * @param index índice de la fila dentro de la matriz
     * 
     * Se obtiene la lista de elementos que se encuentra en la matriz. Luego, 
     * gracias al uso del bitshifting (doRightBitshifting) hacia a la derecha 
     * mueve todos los elementos un espacio hacia a la derecha.
     * 
     * (Check CircularShifting Interface).
     * 
     * Complejidad de O(n). 
     * 
     * */
    public void moveRowToRightAt(int index);
    
    /**
     * Método que se utiliza para mover una fila hacia a la IZQUIERDA
     * de un objeto de la clase Matrix.
     * 
     * @param index índice de la fila dentro de la matriz
     * 
     * Se obtiene la lista de elementos que se encuentra en la matriz. Luego, 
     * gracias al uso del bitshifting hacia a la izquierda (doLeftBitshifting)  
     * mueve todos los elementos un espacio hacia a la derecha.
     * 
     * (Check CircularShifting Interface).
     * 
     * Complejidad de O(n). 
     * 
     * */
    
    public void moveRowToLeftAt(int index);
    
    /**
     * Método que se utiliza para mover una columna hacia arriba
     * de un objeto de la clase Matrix.
     * 
     * @param index índice de la columna dentro de la matriz
     * 
     * Con el uso de un iterador se recorre cada fila, obteniendo el elemento
     * puesto en la posicion (index) de esa fila en cuestión y se lo añade a una 
     * lista circular. Luego, a la nueva lista circular nos aprovechamos del 
     * (doLeftShifting) haciendo que todos los elementos se muevan una posición 
     * hacia a la izquierda, provocando el "movimiento hacia arriba".
     * 
     * (Check CircularShifting Interface).
     * 
     * Una vez hecho el shifting a la nueva lista circular se recorre de nuevo
     * la matriz, a cada una de las filas en la posición (index) se pone el nuevo
     * elemento que se cambió. (Uso de setAt(index))
     * 
     * 
     * Complejidad de O(n). 
     * 
     * */
    public void moveColumnUpAt(int index);
    
    /**
     * Método que se utiliza para mover una columna hacia abajo
     * de un objeto de la clase Matrix.
     * 
     * @param index índice de la columna dentro de la matriz
     * 
     * Con el uso de un iterador se recorre cada fila, obteniendo el elemento
     * puesto en la posicion (index) de esa fila en cuestión y se lo añade a una 
     * lista circular. Luego, a la nueva lista circular nos aprovechamos del 
     * (doRightShifting) haciendo que todos los elementos se muevan una posición 
     * hacia a la derecha, provocando el "movimiento hacia abajo".
     * 
     * (Check CircularShifting Interface).
     * 
     * Una vez hecho el shifting a la nueva lista circular se recorre de nuevo
     * la matriz, a cada una de las filas en la posición (index) se pone el nuevo
     * elemento que se cambió. (Uso de setAt(index))
     * 
     * 
     * Complejidad de O(n). 
     * */
    public void moveColumnDownAt(int index);
}
