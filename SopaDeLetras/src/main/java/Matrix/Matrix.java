
package Matrix;

import TDA.CircularDoblyNodeList;
import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Matrix {
    private int row;
    private int column;
    
    private DoblyCircularList<DoblyCircularList<String>> matrixRow;
    
    public Matrix(int row, int column ){
        this.row = row;
        this.column = column;
        matrixRow = new DoblyCircularList<>();
        for(int i = 0; i < row ; i++){
            DoblyCircularList<String> actualrow = new DoblyCircularList<>();
            for(int j = 0; j<column; j++){
                actualrow.addLast("0"); // valor con el que se inicializa la matriz
            }
            matrixRow.addLast(actualrow);
        }
    }
    
    //metodo para mostrar la matriz y sus valores
    public void showMatrix(){
        for(int i = 0; i < row ; i++){
            DoblyCircularList<String> actualrow = matrixRow.getIndex(i);
            actualrow.show();
        }
    }
    
    //METODO PARA LEER LAS PALABRAS DEL ARCHIVO, RETORNA LISTA
    public static DoblyCircularList<String> leerPalabras(String ruta){
        DoblyCircularList<String> listaPalabras = new DoblyCircularList<>();
        try {
            FileReader reader = new FileReader(ruta);
            BufferedReader br = new BufferedReader(reader);
            String linea;
            while ((linea = br.readLine())!= null){
                listaPalabras.addLast(linea.strip());
            }
            reader.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaPalabras;
    }
    
    
    
    
    
    
    
    
}
