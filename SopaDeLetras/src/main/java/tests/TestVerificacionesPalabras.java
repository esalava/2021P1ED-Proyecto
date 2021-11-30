
package tests;

import Matrix.LetraMatrix;
import Matrix.VerificacionesPalabras;
import TDA.DoblyCircularList;
import java.util.Comparator;
import java.util.Stack;

public class TestVerificacionesPalabras {
    public static void main(String[] args) {
        /*TEST REPETIDOS*/
        DoblyCircularList<LetraMatrix> repetidos = new DoblyCircularList<>();
        LetraMatrix l1 = new LetraMatrix(3,3,'x');
        LetraMatrix l2 = new LetraMatrix(3,2,'j');
        
        repetidos.addLast(l1);
        repetidos.addLast(l2);
        System.out.println(VerificacionesPalabras.verificarNoRepetido(repetidos));
        
        
        /*TEST HORIZONTAL*/
        DoblyCircularList<LetraMatrix> horizontal = new DoblyCircularList<>();
        LetraMatrix l3 = new LetraMatrix(0,11,'x');
        LetraMatrix l4 = new LetraMatrix(0,10,'j');
        LetraMatrix l5 = new LetraMatrix(0,9,'j');
        horizontal.addLast(l3);
        horizontal.addLast(l4);
        horizontal.addLast(l5);
        System.out.println(VerificacionesPalabras.verificarHorizontal(horizontal));
        
        /*TEST VERTICAL*/
        DoblyCircularList<LetraMatrix> vertical = new DoblyCircularList<>();
        LetraMatrix l6 = new LetraMatrix(11,0,'x');
        LetraMatrix l7 = new LetraMatrix(10,0,'j');
        LetraMatrix l8 = new LetraMatrix(9,0,'j');
        vertical.addLast(l6);
        vertical.addLast(l7);
        vertical.addLast(l8);
        System.out.println(VerificacionesPalabras.verficarVertical(vertical));
        
        /*TEST DIAGONAL*/
        DoblyCircularList<LetraMatrix> diagonal = new DoblyCircularList<>();
        LetraMatrix l9 = new LetraMatrix(1,1,'x');
        LetraMatrix l10 = new LetraMatrix(2,2,'j');
        LetraMatrix l11 = new LetraMatrix(3,3,'j');
        
        diagonal.addLast(l9);
        diagonal.addLast(l10);
        diagonal.addLast(l11);
        System.out.println(VerificacionesPalabras.verificarDiagonal(diagonal));
        
        
        System.out.println(VerificacionesPalabras.verificarSeleccionPalabra(vertical));
        
    }
}
