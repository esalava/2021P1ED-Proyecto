package TDA;

public class CircularQuadNode<E> {
    private E content;
    private CircularDoblyNodeList<E> next;
    private CircularDoblyNodeList<E> previous;
    private CircularDoblyNodeList<E> up;
    private CircularDoblyNodeList<E> down;
    
    
    public CircularQuadNode(E element){
        this.next = null;
        this.previous = null;
        this.up = null;
        this.down = null;
        this.content = element;
    }

    public CircularDoblyNodeList<E> getUp() {
        return up;
    }

    public void setUp(CircularDoblyNodeList<E> up) {
        this.up = up;
    }

    public CircularDoblyNodeList<E> getDown() {
        return down;
    }

    public void setDown(CircularDoblyNodeList<E> down) {
        this.down = down;
    }
    
    public E getContent(){
        return this.content;
    }
    
    public CircularDoblyNodeList<E> getNext(){
        return next;
    }
    
    public CircularDoblyNodeList<E> getPrevious(){
        return previous;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(CircularDoblyNodeList<E> next) {
        this.next = next;
    }

    public void setPrevious(CircularDoblyNodeList<E> previous) {
        this.previous = previous;
    }
}
