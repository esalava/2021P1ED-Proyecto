package tests;

import Matrix.Matrix;
import TDA.DoblyCircularList;
import java.util.Iterator;

public class TestTDA {
    public static void main(String[] args) {
        DoblyCircularList<Character> list = new DoblyCircularList<>();
        list.addLast('0');
        list.addLast('1');
        list.addLast('2');
        list.addLast('3');
        list.addLast('4');
        list.addLast('5');
        list.addLast('6');
        list.addLast('7');
        list.addLast('8');
        list.addLast('9');
        
        System.out.println(list.size());
        list.show();
        System.out.println(list.deleteAt(list.size()-1));
        System.out.println(list.deleteAt(list.size()-1));
        System.out.println(list.deleteAt(list.size()-1));
        list.show();
        System.out.println(list.size());
    }
}
