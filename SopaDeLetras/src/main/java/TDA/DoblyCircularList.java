package TDA;

import java.util.Iterator;

/*
    Lista circular doblemente enlazada
 */
public class DoblyCircularList<E> implements List<E>, Iterable<E> {

    private CircularDoblyNodeList<E> last;
    private int size;

    public DoblyCircularList() {
        this.last = null;
        size = 0;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }

        if (this.size() == 0) {
            CircularDoblyNodeList<E> newNode = new CircularDoblyNodeList<>(element);
            last = newNode;
            last.setNext(last);
            last.setPrevious(last);

            size++;
        } else {
            CircularDoblyNodeList<E> newNode = new CircularDoblyNodeList<>(element);
            newNode.setNext(last.getNext());
            last.getNext().setPrevious(newNode);
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;

            size++;
        }

        return true;
    }

    /*Puede añadir un elemento en una posicion n de la lista (incluye que puede
    agregarlo al final de la lista)*/
    public boolean addAt(E e, int pos) {

        if (e != null && pos >= 0 && pos <= this.size()) {
            CircularDoblyNodeList<E> newNode = new CircularDoblyNodeList<>(e);
            CircularDoblyNodeList<E> p = last.getNext();

            if (pos == this.size()) {
                //No se añade size++ porque el metodo addLast lo tiene implementado
                this.addLast(e);
                return true;
            }

            for (int i = 0; i < pos; i++) {
                p = p.getNext();
            }

            /*Actualizacion de los nodos*/
            newNode.setNext(p);
            p.getPrevious().setNext(newNode);
            newNode.setPrevious(p.getPrevious());
            p.setPrevious(newNode);
            size++;

            return true;
        }
        return false;
    }

    /*BORRAR AL FINAL*/
    public void show() {
        CircularDoblyNodeList<E> travellerNode;

        for (travellerNode = last.getNext(); travellerNode != last; travellerNode = travellerNode.getNext()) {
            System.out.print(travellerNode.getContent() + " ");
        }
        System.out.print(travellerNode.getContent() + " ");
        System.out.println("");
    }

    public int size() {
        return size;
    }

    //Funciona como indice en un ArrayList O(n)
    public E getIndex(int index) {
        CircularDoblyNodeList<E> travellerNode;
        int i = 0;

        for (travellerNode = last.getNext(); i < size; travellerNode = travellerNode.getNext()) {
            if (i == index) {
                return travellerNode.getContent();
            }
            i++;
        }
        return null;
    }

    //Complejidad de O(n)
    public void doRightBitshifting() {
        E temporaryFirstValue = last.getContent();
        CircularDoblyNodeList<E> travellerNode;

        for (travellerNode = last; travellerNode != last.getNext(); travellerNode = travellerNode.getPrevious()) {
            travellerNode.setContent(travellerNode.getPrevious().getContent());
        }

        last.getNext().setContent(temporaryFirstValue);
    }

    //Complejidad de O(n)
    public void doLeftBitshifting() {
        E temporaryFirstValue = last.getNext().getContent();
        CircularDoblyNodeList<E> travellerNode;

        for (travellerNode = last.getNext(); travellerNode != last; travellerNode = travellerNode.getNext()) {
            travellerNode.setContent(travellerNode.getNext().getContent());
        }

        last.setContent(temporaryFirstValue);
    }

    //Complejidad de O(n)
    public boolean setAt(E element, int index) {

        CircularDoblyNodeList<E> travellerNode;
        int i = 0;
        if (index >= this.size()) { //se controla para que no sobreescriba
            return false;
        }

        for (travellerNode = last.getNext(); i <= index; travellerNode = travellerNode.getNext()) {
            if (i == index) {
                travellerNode.setContent(element);
                return true;
            }
            i++;
        }

        return false;
    }
    
    public boolean deleteLast(){
        
        last.getPrevious().setNext(last.getNext());
        last.getNext().setPrevious(last.getPrevious());
        last = last.getPrevious();
        size--;
        return true;
    }
    
    public boolean deleteAt(int index){
        CircularDoblyNodeList<E> travellerNode = last.getNext();
        int i = 0;
        
        if (index >= this.size() || index < 0) {
            
            return false;
        }
        
        if (index == this.size()-1){
            deleteLast(); //no se debe hacer --size porque ya esta implementado
            return true;
        }
        
        for (i = 0; i < index; i++) {
                travellerNode = travellerNode.getNext();
        }
        
        travellerNode.getPrevious().setNext(travellerNode.getNext());
        travellerNode.getNext().setPrevious(travellerNode.getPrevious());
        --size;
        
        return true;
        
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            CircularDoblyNodeList<E> cursor = last.getNext(); //Se toma de referencia el primero
            int contador = 0;
            int lim_superior = size;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                CircularDoblyNodeList<E> element = cursor;
                contador++;
                if (contador < lim_superior) {
                    cursor = cursor.getNext();
                } else {
                    cursor = null;
                }

                return element.getContent();
            }
        };

        return iterator;
    }
}
