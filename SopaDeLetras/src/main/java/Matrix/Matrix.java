
package Matrix;

import TDA.CircularDoblyNodeList;
import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Random;
import java.lang.Character;

public class Matrix {
    private int row;
    private int column;
    
    private DoblyCircularList<DoblyCircularList<Character>> matrixRow;
    
    public Matrix(int row, int column ){
        this.row = row;
        this.column = column;
        matrixRow = new DoblyCircularList<>();
        for(int i = 0; i < row ; i++){
            DoblyCircularList<Character> actualrow = new DoblyCircularList<>();
            for(int j = 0; j<column; j++){
              Random random = new Random();
              char rand = (char) (random.nextInt(26) + 'A');   //Letra aleatoria
                actualrow.addLast(rand); // valor con el que se inicializa la matriz
            }
            matrixRow.addLast(actualrow);
        }
    }
    
    //metodo para mostrar la matriz y sus valores
    public void showMatrix(){
        for(int i = 0; i < row ; i++){
            DoblyCircularList<Character> actualrow = matrixRow.getIndex(i);
            actualrow.show();
        }
    }
    
    //ACTUALIZAR RUTA, YO LO HICE EN REPLIT POR ESO NO NECESITABA RUTA, si funciona
    //metodo para cargar palabras a la sopa de letras
    
    public void agregarPalabras(){
      DoblyCircularList<String> listaPalabras = leerPalabras("palabras.txt");
      Random r = new Random();

      //FORRRRR para agregar más palabras
      //for (int c=0; c<6;c++){
      int x = r.nextInt(row);
      int y = r.nextInt(column);

      int indexpalabra = r.nextInt(listaPalabras.size());
      String palabra = listaPalabras.getIndex(indexpalabra);
      int lenPal = palabra.length(); 

      boolean invertirONo = r.nextBoolean();

      int numOrient = 0;
      while (lenPal>row && lenPal>column){
        if (lenPal<=row && lenPal>column) {
          numOrient = 1; //agg vertical
        } else if (lenPal<=column && lenPal>row){
          numOrient = 0; //agg horizontal
        } else if (lenPal <= column && lenPal<=row) {
          numOrient = r.nextInt(2); 
        } else{
          indexpalabra = r.nextInt(listaPalabras.size());
          palabra = listaPalabras.getIndex(indexpalabra);
          lenPal = palabra.length();
        }
      }

      if (invertirONo){
        palabra = invertirPalabra(palabra);
        //System.out.println(palabra);
      }


      //int numOrient = r.nextInt(2);
      switch (numOrient){
        case 0:
          //horizontal
          DoblyCircularList<Character> actualRow = matrixRow.getIndex(x);
        
          for (int i=0;i<lenPal;i++){
            Character letra = Character.toUpperCase(palabra.charAt(i));
            int indice = i+y;
            if (indice>=column){
              indice = indice-column;
            } 
            boolean conf = actualRow.setAt(letra,indice);
          }
          break;
        case 1:
          //VERTICAL
          for (int i=0; i<lenPal;i++){
            int indice = i+x;
            Character letra = Character.toUpperCase(palabra.charAt(i));
            if (indice>=row){
              indice = indice-row;
            } 
            DoblyCircularList<Character> actualrow = matrixRow.getIndex(indice);
            boolean conf = actualrow.setAt(letra, y);
          }
          break;

        case 2:
          //DIAGONAL
          /*
          se debe validar a que lado va a ingresar la palabra para que alcance
          en la diagonal.
          
          */

      }
       
      //}
      
    }
      
    
    
    //METODO PARA LEER LAS PALABRAS DEL ARCHIVO, RETORNA LISTA
    public static DoblyCircularList<String> leerPalabras(String ruta){
        DoblyCircularList<String> listaPalabras = new DoblyCircularList<>();
        try {
            FileReader reader = new FileReader(ruta);
            BufferedReader br = new BufferedReader(reader);
            String linea;
            while ((linea = br.readLine())!= null){
                listaPalabras.addLast(linea.strip());
            }
            reader.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaPalabras;
    }
    
    
    public static String invertirPalabra(String palabra){
        Stack<Character> pila = new Stack<>();
        String palabraInvert = "";
        for (int i=0; i<palabra.length(); i++){
            pila.push(String.valueOf(palabra.charAt(i)));
        }
        while (!pila.isEmpty()){
            palabraInvert += pila.pop();
        }
        return palabraInvert;

    }
    
    
    
    
    
    
    
    
}
