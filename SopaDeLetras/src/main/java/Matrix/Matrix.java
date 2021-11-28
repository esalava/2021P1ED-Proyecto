
package Matrix;

import TDA.CircularDoblyNodeList;
import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Random;
import java.lang.Character;
import java.util.Iterator;

public class Matrix implements MatrixMoves {
    private int row;
    private int column;
    
    private DoblyCircularList<DoblyCircularList<Character>> matrix;
    
    public Matrix(int row, int column ){
        this.row = row;
        this.column = column;
        matrix = new DoblyCircularList<>();
        for(int i = 0; i < row ; i++){
            DoblyCircularList<Character> actualrow = new DoblyCircularList<>();
            for(int j = 0; j<column; j++){
              Random random = new Random();
              char rand = (char) (random.nextInt(26) + 'A');   //Letra aleatoria
                actualrow.addLast(rand); // valor con el que se inicializa la matriz
            }
            matrix.addLast(actualrow);
        }
    }
    
    public DoblyCircularList<DoblyCircularList<Character>> getMatrix(){
        return matrix;
    }
    
    //metodo para mostrar la matriz y sus valores
    public void showMatrix(){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        while(iterator.hasNext()){
            iterator.next().show();
        }
    }
    
    @Override
    public void moveRowToRightAt(int index){
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doRightBitshifting();
        matrix.setAt(actualRow, index);
    }
    
    @Override
    public void moveRowToLeftAt(int index){
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doLeftBitshifting();
        matrix.setAt(actualRow, index);
    }
    
    @Override
    public void moveColumnUpAt(int index){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while(iterator.hasNext()){
            actualColumn.addLast(iterator.next().getIndex(index));
        }
        
        actualColumn.doLeftBitshifting();
        
        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while(rowIterator.hasNext() && newColumnIterator.hasNext()){
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }
    
    }
    
    @Override
    public void moveColumnDownAt(int index){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while(iterator.hasNext()){
            actualColumn.addLast(iterator.next().getIndex(index));
        }
        
        actualColumn.doRightBitshifting();
        
        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while(rowIterator.hasNext() && newColumnIterator.hasNext()){
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }
    
    }
    
    //ACTUALIZAR RUTA, YO LO HICE EN REPLIT POR ESO NO NECESITABA RUTA, si funciona
    //metodo para cargar palabras a la sopa de letras
    
    public void agregarPalabras(){
      DoblyCircularList<String> listaPalabras = Word.loadWords("palabras.txt");
      Random r = new Random();
    }

    public static String invertirPalabra(String palabra){
        Stack<Character> pila = new Stack<>();
        String palabraInvert = "";
        for (int i=0; i<palabra.length(); i++){
            pila.push(palabra.charAt(i));
        }
        while (!pila.isEmpty()){
            palabraInvert += pila.pop();
        }
        return palabraInvert;

    }
    
    
    
    
    
    
    
    
}
