
package Interfaces.Matrix;

public interface MatrixDelete {
    
    /**
     *  Método que permite eliminar una fila.
     *  
     * @param index índice de la fila a la que se va a eliminar
     * 
     * La fila es eliminada gracias al método propuesto por las listas 
     * circulares (deleteAt). Se obtiene el índice de la fila a la que queremos
     * eliminar y hacemos uso de este método. Se aprovecha la propiedad de la 
     * clase Matrix que usa Listas Circulares de Listas Circulares.
     * 
     * Complejidad de O(n). 
     * 
     * */
    public void deleteRowAt(int index);
    
    /**
     *  Método que permite eliminar una columna.
     *  
     * @param index índice de la columna a la que se va a eliminar
     * 
     * La columna es eliminada gracias al método propuesto por las listas 
     * circulares (deleteAt). Se recorre a cada una de las filas de la matriz
     * y se elimina el elemento en la posición (index), causando la eliminación 
     * de la columna.
     * 
     * Complejidad de O(n). 
     * 
     * */
    public void deleteColumnAt(int index);
}
