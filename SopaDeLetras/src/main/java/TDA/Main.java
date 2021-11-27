package TDA;

import Matrix.Matrix;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        DoblyCircularList<Character> list = new DoblyCircularList<>();
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        System.out.println(list.size());
        list.addAt('X', 0);
        
        list.show();
        System.out.println(list.size());
        
        Iterator<Character> iterator = list.iterator();
        
        while(iterator.hasNext()){
            System.out.print(iterator.next()+ " ");
        }
    }
}
