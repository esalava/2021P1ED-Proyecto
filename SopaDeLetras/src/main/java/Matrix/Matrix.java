
package Matrix;

import TDA.CircularDoblyNodeList;
import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Random;
import java.lang.Character;
import java.util.Iterator;

public class Matrix implements MatrixMoves {
    private int row;
    private int column;
    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rd;
    private DoblyCircularList<DoblyCircularList<Character>> matrix;
    
    public Matrix(int row, int column ){
        rd = new Random();
        this.row = row;
        this.column = column;
        matrix = new DoblyCircularList<>();
        for(int i = 0; i < row ; i++){
            DoblyCircularList<Character> actualrow = new DoblyCircularList<>();
            for(int j = 0; j<column; j++){
              Random random = new Random();
              char rand = (char) (random.nextInt(26) + 'A');   //Letra aleatoria
                actualrow.addLast(rand); // valor con el que se inicializa la matriz
            }
            matrix.addLast(actualrow);
        }
    }
    
    public DoblyCircularList<DoblyCircularList<Character>> getMatrix(){
        return matrix;
    }
    
    //metodo para mostrar la matriz y sus valores
    public void showMatrix(){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        while(iterator.hasNext()){
            iterator.next().show();
        }
    }
    
    @Override
    public void moveRowToRightAt(int index){
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doRightBitshifting();
        matrix.setAt(actualRow, index);
    }
    
    @Override
    public void moveRowToLeftAt(int index){
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doLeftBitshifting();
        matrix.setAt(actualRow, index);
    }
    
    @Override
    public void moveColumnUpAt(int index){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while(iterator.hasNext()){
            actualColumn.addLast(iterator.next().getIndex(index));
        }
        
        actualColumn.doLeftBitshifting();
        
        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while(rowIterator.hasNext() && newColumnIterator.hasNext()){
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }
    
    }
    
    @Override
    public void moveColumnDownAt(int index){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while(iterator.hasNext()){
            actualColumn.addLast(iterator.next().getIndex(index));
        }
        
        actualColumn.doRightBitshifting();
        
        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while(rowIterator.hasNext() && newColumnIterator.hasNext()){
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }
    
    }
    
    //ACTUALIZAR RUTA, YO LO HICE EN REPLIT POR ESO NO NECESITABA RUTA, si funciona
    //metodo para cargar palabras a la sopa de letras
    
    public void agregarPalabras(){
        DoblyCircularList<String> listaPalabras = Word.loadWords("palabras.txt");
        Random r = new Random();

        //FORRRRR
        //for (int c=0; c<6;c++){
        int x = r.nextInt(row);
        int y = r.nextInt(column);

        int indexpalabra = r.nextInt(listaPalabras.size());
        String palabra = listaPalabras.getIndex(indexpalabra);
        int lenPal = palabra.length(); 

        boolean invertirONo = r.nextBoolean();

        while (lenPal>row && lenPal>column){
            indexpalabra = r.nextInt(listaPalabras.size());
            palabra = listaPalabras.getIndex(indexpalabra);
            lenPal = palabra.length();
        }

        if (invertirONo){
            palabra = invertirPalabra(palabra);
        }
        System.out.println(palabra);
      
        int numOrient = r.nextInt(3);

        switch (numOrient){
            case 0:
                //horizontal
                if ((lenPal<=column && lenPal>row) || (lenPal <= column && lenPal<=row)){
                    DoblyCircularList<Character> actualRow = matrix.getIndex(x);
        
                    for (int i=0;i<lenPal;i++){
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        int indice = i+y;
                        if (indice>=column){
                            indice = indice-column;
                        } 
                        boolean conf = actualRow.setAt(letra,indice);
                    }
                }
                break;
      
            case 1:
                //VERTICAL
                if ((lenPal<=row && lenPal>column) || (lenPal <= column && lenPal<=row)){
                    for (int i=0; i<lenPal;i++){
                        int indice = i+x;
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        if (indice>=row){
                            indice = indice-row;
                        } 
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indice);
                        boolean conf = actualrow.setAt(letra, y);
                    }
                }
                break;

            case 2:
                //DIAGONAL
                int indx = x;
                int indy = y;
                int cont=0;
                int cont2=1;
                System.out.println("x= "+String.valueOf(x));
                System.out.println("y= "+String.valueOf(y));
                System.out.println("len = "+ String.valueOf(lenPal));

                //len que entran derecha abajo
                while (indx<row && indy<column){ 
                    indx++;
                    indy++;
                    cont++;
                }
                indx = x;
                indy = y;
                //len que entran izquierda arriba
                while (indx>0 && indy>0){
                indx--;
                indy--;
                cont2++;
                }
                indx = x;
                indy = y;
                int cont3=0;
                //len que entran izquierda abajo
                while (indx<row && indy>0){
                    indx++;
                    indy--;
                    cont3++;
                }
                indx = x;
                indy = y;
                int cont4=0;
                //len que entran derecha arriba
                while (indx>0 && indy<column){
                    indx--;
                    indy++;
                    cont4++;
                }
          
                //System.out.println("derecha abajo = "+String.valueOf(cont));
                //System.out.println("izquierda arriba = "+String.valueOf(cont2));
                //System.out.println("izquierda abajo = "+String.valueOf(cont3));
                //System.out.println("derecha arriba = "+String.valueOf(cont4));


                for (int i=0;i<lenPal;i++){
                    if (lenPal<cont){
                        int indicex = x+i;
                        int indicey = y+i;
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        boolean conf = actualrow.setAt(letra, indicey);

                    } else if (lenPal<cont2){
                        int indicex = x-i;
                        int indicey = y-i;
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        boolean conf = actualrow.setAt(letra, indicey);

                    } else if (lenPal<cont3){
                        int indicex = x+i;
                        int indicey = y-i;
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        boolean conf = actualrow.setAt(letra, indicey);

                    } else if (lenPal<cont4){
                        int indicex = x-i;
                        int indicey = y+i;
                        Character letra = Character.toUpperCase(palabra.charAt(i));
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        boolean conf = actualrow.setAt(letra, indicey);
                    }
             
                }
            break;
        }
       
      //}
      
    }

    public static String invertirPalabra(String palabra){
        Stack<Character> pila = new Stack<>();
        String palabraInvert = "";
        for (int i=0; i<palabra.length(); i++){
            pila.push(palabra.charAt(i));
        }
        while (!pila.isEmpty()){
            palabraInvert += pila.pop();
        }
        return palabraInvert;

    }
    
    public void insertRandomRowAt(int index){
        int rowSize = matrix.getIndex(0).size();
        DoblyCircularList<Character> newRow = new DoblyCircularList<>();
        for(int i = 0 ; i < rowSize ; i++){
            Character c = LETRAS.charAt(rd.nextInt(LETRAS.length()));
            newRow.addLast(c);
        }
        matrix.addAt(newRow, index);  
    }
    
    public void insertRandomColumnAt(int index){
        int columnSize = matrix.size();
        DoblyCircularList<Character> newColumn = new DoblyCircularList<>();
        for(int i = 0 ; i < columnSize ; i++){
            Character c = LETRAS.charAt(rd.nextInt(LETRAS.length()));
            newColumn.addLast(c);
        }
        Iterator <DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newElementIterator = newColumn.iterator();
        while(rowIterator.hasNext()){
            rowIterator.next().addAt(newElementIterator.next(), index);
        }
    }
    
    public void deleteRowAt(int index){
        matrix.deleteAt(index);
    }
    
    public void deleteColumnAt(int index){
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        while(iterator.hasNext()){
            iterator.next().deleteAt(index);
        }
    }
}
