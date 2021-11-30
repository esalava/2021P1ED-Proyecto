
package Matrix;

import TDA.DoblyCircularList;
import java.util.Comparator;
import java.util.Stack;

public class LetraMatrix {
    private int y;
    private int x;
    private Character letra;
    
    public LetraMatrix(int posY, int posX, Character l){
        this.y = posY;
        this.x = posX;
        this.letra = l;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Character getLetra() {
        return letra;
    }

    public void setLetra(Character letra) {
        this.letra = letra;
    }
    
    public String toString(){
        return letra+"("+y + "," + x + ")";
    }
    
}
