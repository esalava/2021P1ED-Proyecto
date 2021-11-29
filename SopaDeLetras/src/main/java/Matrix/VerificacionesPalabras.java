package Matrix;

import TDA.DoblyCircularList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

public class VerificacionesPalabras {
    
    
    /*
    Verfica si tiene alguna posicion x, y repetida dentro de la pila.
    retorna TRUE cuando no está repetido
    retorna FALSE cuando si está repetido
    */
    
    public static boolean verificarNoRepetido(DoblyCircularList<LetraMatrix> palabraSeleccionada){
        
        DoblyCircularList<LetraMatrix> newList = new DoblyCircularList<>();
        
        Comparator<LetraMatrix> cmp = (LetraMatrix o1, LetraMatrix o2) -> {
            if(o1.getX() == o2.getX() && o1.getY() == o2.getY()){
                return 0; //solo se necesita saber si son iguales
            } else {
                return -1;
            }
        };
        
        Iterator<LetraMatrix> iterator = palabraSeleccionada.iterator();
        
        while(iterator.hasNext()){
            LetraMatrix wordFromMatrix = iterator.next();
            if(newList.containsElement(wordFromMatrix, cmp)){
                return false;
            } else {
                newList.addLast(wordFromMatrix);
            }
        }
        
        return true;
    }
    
    public static boolean verificarHorizontal(DoblyCircularList<LetraMatrix> palabraSeleccionada){
        
        Stack<LetraMatrix> controlWord = new Stack<>();
        int pivoteY = -1;
        
        Iterator<LetraMatrix> iterator = palabraSeleccionada.iterator();
        while(iterator.hasNext()){
            if(controlWord.isEmpty()){
                LetraMatrix letter = iterator.next();
                controlWord.push(letter);
                pivoteY = letter.getY();
            } else {
            
                LetraMatrix newLetter = iterator.next();
                if(newLetter.getY() == pivoteY && (newLetter.getX() + 1 == controlWord.peek().getX() || newLetter.getX() - 1 == controlWord.peek().getX())){
                    //Solo se agregara si la ultima letra que se añadio a la pila tiene un +1 o -1 en el movimiento de X
                    //Tiene que tener el mismo pivote
                    controlWord.push(newLetter);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean verficarVertical(DoblyCircularList<LetraMatrix> palabraSeleccionada){
    
        Stack<LetraMatrix> controlWord = new Stack<>();
        int pivoteX = -1;
        
        Iterator<LetraMatrix> iterator = palabraSeleccionada.iterator();
        while(iterator.hasNext()){
            if(controlWord.isEmpty()){
                LetraMatrix letter = iterator.next();
                controlWord.push(letter);
                pivoteX = letter.getX();
            } else {
            
                LetraMatrix newLetter = iterator.next();
                if(newLetter.getX()== pivoteX && (newLetter.getY() + 1 == controlWord.peek().getY() || newLetter.getY() - 1 == controlWord.peek().getY())){
                    //Solo se agregara si la ultima letra que se añadio a la pila tiene un +1 o -1 en el movimiento de Y
                    //Tiene que tener el mismo pivote
                    controlWord.push(newLetter);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean verificarDiagonal(DoblyCircularList<LetraMatrix> palabraSeleccionada){
        Stack<LetraMatrix> controlWord = new Stack<>();
        
        
        Iterator<LetraMatrix> iterator = palabraSeleccionada.iterator();
        while(iterator.hasNext()){
            if(controlWord.isEmpty()){
                LetraMatrix letter = iterator.next();
                controlWord.push(letter);
                
            } else {
            
                LetraMatrix newLetter = iterator.next();
                if(
                        (controlWord.peek().getX()+1 ==newLetter.getX() && controlWord.peek().getY()+1 ==newLetter.getY()) ||
                        (controlWord.peek().getX()-1 ==newLetter.getX() && controlWord.peek().getY()+1 ==newLetter.getY()) ||
                        (controlWord.peek().getX()+1 ==newLetter.getX() && controlWord.peek().getY()-1 ==newLetter.getY()) ||
                        (controlWord.peek().getX()-1 ==newLetter.getX() && controlWord.peek().getY()-1 ==newLetter.getY())      
                    ){
                    //Solo se agregan las que tengan un offset de (+1,+1), (+1,-1), (-1, +1), (-1,-1)
                    controlWord.push(newLetter);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean verificarSeleccionPalabra(DoblyCircularList<LetraMatrix> palabraSeleccionada){
        if(palabraSeleccionada == null){
            return false;
        }
        
        if(!verificarNoRepetido(palabraSeleccionada)){
            return false;
        }
        
        int contador = 0;
        
        if(verificarHorizontal(palabraSeleccionada)){
            contador += 1;
        }
        
        if(verficarVertical(palabraSeleccionada)){
            contador += 1;
        }
        
        if(verificarDiagonal(palabraSeleccionada)){
            contador += 1;
        }
        
        if(contador > 1 || contador <= 0){
           return false;  //NOT NORMAL CASE
        }
        
        return true;
    }
}
