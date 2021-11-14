package TDA;

public class CircularDoblyNodeList<E> {
    private E content;
    private CircularDoblyNodeList<E> next;
    private CircularDoblyNodeList<E> previous;
    
    public CircularDoblyNodeList(E element){
        this.next = null;
        this.previous = null;
        this.content = element;
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
