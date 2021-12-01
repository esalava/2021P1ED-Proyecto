
package Interfaces.Matrix;

public interface MatrixRandomInsert {
    
    /**
     *  Método que permite inserta una fila random.
     *  
     * @param index índice de la fila en donde se va a insertar
     * 
     * Se empieza con la creación de letras aleatorias que van a ser agregadas
     * a la fila de la matriz y gracias al método propuesto por las listas circulares
     * (addAt(element, index)) agregamos la nueva lista a la matriz.
     * 
     * Complejidad de O(n). 
     * 
     * */
    
    public void insertRandomRowAt(int index);
    
    
    /**
     *  Método que permite inserta una columna random.
     *  
     * @param index índice de la fila en donde se va a insertar
     * 
     * Se empieza con la creación de letras aleatorias que van a ser agregadas
     * a la columna de la matriz y gracias al método propuesto por las listas 
     * circulares (addAt(element, index)) iteramos a cada una de las filas y en 
     * la posición (index se añade). Como resultado se obtiene una "columna añadida"
     * 
     * Complejidad de O(n). 
     * 
     * */
    public void insertRandomColumnAt(int index);
}
