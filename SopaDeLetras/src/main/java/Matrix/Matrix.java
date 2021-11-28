
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
            DoblyCircularList<Character> actualRow = matrixRow.getIndex(x);
        
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
              DoblyCircularList<Character> actualrow = matrixRow.getIndex(indice);
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
              DoblyCircularList<Character> actualrow = matrixRow.getIndex(indicex);
              boolean conf = actualrow.setAt(letra, indicey);

            } else if (lenPal<cont2){
              int indicex = x-i;
              int indicey = y-i;
              Character letra = Character.toUpperCase(palabra.charAt(i));
              DoblyCircularList<Character> actualrow = matrixRow.getIndex(indicex);
              boolean conf = actualrow.setAt(letra, indicey);

            } else if (lenPal<cont3){
              int indicex = x+i;
              int indicey = y-i;
              Character letra = Character.toUpperCase(palabra.charAt(i));
              DoblyCircularList<Character> actualrow = matrixRow.getIndex(indicex);
              boolean conf = actualrow.setAt(letra, indicey);

            } else if (lenPal<cont4){
              int indicex = x-i;
              int indicey = y+i;
              Character letra = Character.toUpperCase(palabra.charAt(i));
              DoblyCircularList<Character> actualrow = matrixRow.getIndex(indicex);
              boolean conf = actualrow.setAt(letra, indicey);
            }
            
            
          }
          break;

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
