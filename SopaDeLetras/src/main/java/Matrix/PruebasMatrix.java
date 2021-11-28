/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 *
 * @author Administrador
 */
public class PruebasMatrix {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(5,5);
        //matrix.agregarPalabras();
        matrix.showMatrix();
        matrix.moveColumnDownAt(0);
        System.out.println("******************************");
        matrix.showMatrix();
    }
}
