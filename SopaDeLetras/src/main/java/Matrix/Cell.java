
package Matrix;


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
    
    

    public Cell(int row, int column, Cell[][] cells) {
        this.cells = cells;
        this.row = row;
        this.column = column;
        this.letra = "A";
        //this.setSize(new Dimension(20, 20));
        //this.setBackground(colors[BacktrackingExample.getRandomNumberInRange(0, 2)]);
        //this.addMouseListener(this);
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
    

}
