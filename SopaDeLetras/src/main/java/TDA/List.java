/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

/**
 *
 * @author Administrador
 */
public interface List<E> {
    public boolean addLast(E element);
    public boolean addAt(E e, int pos);
    public E getIndex(int index);
    public boolean setAt(E element, int index);
    public boolean deleteLast();
    public boolean deleteAt(int index);
}
