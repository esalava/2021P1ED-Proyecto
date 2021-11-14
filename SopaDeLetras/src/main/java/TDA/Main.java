package TDA;

public class Main {
    public static void main(String[] args) {
        DoblyCircularList<Integer> l1 = new DoblyCircularList<>();
        l1.addLast(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        l1.addLast(7);
        
        
        l1.show();
        System.out.println("");
        //System.out.println(l1.size());
        System.out.println("Right Shifting");
        l1.doRightBitshifting();
        l1.show();
        System.out.println("");
        System.out.println("Left Shifting");
        
        l1.doLeftBitshifting();
        l1.show();
        
    }
}
