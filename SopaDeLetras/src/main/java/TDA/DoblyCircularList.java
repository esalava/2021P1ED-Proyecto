package TDA;
/*
    Lista circular doblemente enlazada
*/
public class DoblyCircularList<E> implements List<E> {
    private CircularDoblyNodeList<E> last;
    private int size;
    
    public DoblyCircularList(){
        this.last = null;
        size = 0;
    }

    @Override
    public boolean addLast(E element) {
        if(element==null){
            return false;
        }
        
        if(this.size() == 0){
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
    
    
    
    
    public void show(){
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = last.getNext(); travellerNode != last ; travellerNode = travellerNode.getNext()){
                System.out.print(travellerNode.getContent()+" ");
        }
        System.out.print(travellerNode.getContent()+" ");
    }

    public int size() {
        /*
        
        // SE UTILIZA SI ES QUE NO HUBIESE UN ATRIBUTO SIZE
        
        int s = 0;
        //
        if(last == null){
            return 0;
        } else {
            CircularDoblyNodeList<E> travellerNode;
            for(travellerNode = last.getNext(); travellerNode != last ; travellerNode = travellerNode.getNext()){
                s++;
            }
            s++;
            
        }
        
        return s;*/
        
        
        return size;
    }
    
    
    //Funciona como indice en un ArrayList
    public E getIndex(int index){
        CircularDoblyNodeList<E> travellerNode;
        int i = 0;
        
        for(travellerNode = last.getNext(); i < size ; travellerNode = travellerNode.getNext()){
            
            if( i == index ){
                return travellerNode.getContent();            
            }    
            
            i++;
           
        }
        
        return null;
    }
    
    public void doRightBitshifting(){
        E temporaryFirstValue = last.getContent();
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = last; travellerNode != last.getNext() ; travellerNode = travellerNode.getPrevious()){
               travellerNode.setContent(travellerNode.getPrevious().getContent());
        }
        last.getNext().setContent(temporaryFirstValue);

    }
    
    
    public void doLeftBitshifting(){
        E temporaryFirstValue = last.getNext().getContent();
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = last.getNext(); travellerNode != last ; travellerNode = travellerNode.getNext()){
               travellerNode.setContent(travellerNode.getNext().getContent());
        }
        last.setContent(temporaryFirstValue);

    }

    
    
}
