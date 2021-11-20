
package Matrix;


import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ggmendez
 */
public class Cell{

    public Cell up, down, left, right;
    //private static final Color colors[] = {new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255)};
    private final int row;
    private final int column;
    private String letra;
    private final Cell cells[][];
    private boolean neighboursSet = false;
    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rd;
   
    
    

    public Cell(int row, int column, Cell[][] cells) {
        this.cells = cells;
        this.row = row;
        this.column = column;
        
        //carga de letras aleatorias
        rd = new Random();
        
        this.letra = LETRAS.charAt(rd.nextInt(LETRAS.length()-1)) + "";
        this.setNeighbours();
    }

    public void setNeighbours() {
        try {
            up = cells[row - 1][column];
        } catch (Exception e) {
            up = null;
        }
        try {
            down = cells[row + 1][column];
        } catch (Exception e) {
            down = null;
        }
        try {
            left = cells[row][column - 1];
        } catch (Exception e) {
            left = null;
        }
        try {
            right = cells[row][column + 1];
        } catch (Exception e) {
            right = null;
        }
    }
    
    public String getLetter(){
        return letra;
    }
    
    public void setLetter(String letter){
        this.letra = letter;
    }
    

}
