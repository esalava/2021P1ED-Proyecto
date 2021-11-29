/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.Matrix;

/**
 *
 * @author Administrador
 */
public interface MatrixMoves {
    public void moveRowToRightAt(int index);
    public void moveRowToLeftAt(int index);
    public void moveColumnUpAt(int index);
    public void moveColumnDownAt(int index);
}
