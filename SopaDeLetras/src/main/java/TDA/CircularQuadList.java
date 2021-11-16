package TDA;
/*
    Lista circular doblemente enlazada
*/
public class CircularQuadList<E>{
    private CircularDoblyNodeList<E> Horizontallast;
    private CircularDoblyNodeList<E> verticalLast;
    private int size;
    
    public CircularQuadList(){
        this.Horizontallast = null;
        this.verticalLast = null;
        size = 0;
    }

    
    
    
    
    public boolean addHorizontalLast(E element) {
        if(element==null){
            return false;
        }
        
        if(this.size() == 0){
            CircularDoblyNodeList<E> newNode = new CircularDoblyNodeList<>(element);
            Horizontallast = newNode;
            Horizontallast.setNext(Horizontallast);
            Horizontallast.setPrevious(Horizontallast);
            
            size++;
        } else {
            CircularDoblyNodeList<E> newNode = new CircularDoblyNodeList<>(element);
            newNode.setNext(Horizontallast.getNext());
            Horizontallast.getNext().setPrevious(newNode);
            newNode.setPrevious(Horizontallast);
            Horizontallast.setNext(newNode);
            Horizontallast = newNode;
            
            size++;
        }
       
        return true;
    }
    
    
    
    
    public void show(){
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = Horizontallast.getNext(); travellerNode != Horizontallast ; travellerNode = travellerNode.getNext()){
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
        
        for(travellerNode = Horizontallast.getNext(); i < size ; travellerNode = travellerNode.getNext()){
            
            if( i == index ){
                return travellerNode.getContent();            
            }    
            
            i++;
           
        }
        
        return null;
    }
    
    public void doRightBitshifting(){
        E temporaryFirstValue = Horizontallast.getContent();
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = Horizontallast; travellerNode != Horizontallast.getNext() ; travellerNode = travellerNode.getPrevious()){
               travellerNode.setContent(travellerNode.getPrevious().getContent());
        }
        Horizontallast.getNext().setContent(temporaryFirstValue);

    }
    
    
    public void doLeftBitshifting(){
        E temporaryFirstValue = Horizontallast.getNext().getContent();
        CircularDoblyNodeList<E> travellerNode;
        
        for(travellerNode = Horizontallast.getNext(); travellerNode != Horizontallast ; travellerNode = travellerNode.getNext()){
               travellerNode.setContent(travellerNode.getNext().getContent());
        }
        Horizontallast.setContent(temporaryFirstValue);

    }

    
    
}
