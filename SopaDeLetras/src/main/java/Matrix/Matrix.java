package Matrix;

import Interfaces.Matrix.MatrixMoves;
import Interfaces.Matrix.MatrixDelete;
import Interfaces.Matrix.MatrixRandomInsert;
import TDA.CircularDoblyNodeList;
import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Random;
import java.lang.Character;
import java.util.Iterator;

public class Matrix implements MatrixMoves, MatrixRandomInsert, MatrixDelete {

    private int row;
    private int column;
    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rd;
    private DoblyCircularList<DoblyCircularList<Character>> matrix;
    private DoblyCircularList<String> listWords;

    public Matrix(int row, int column) {
        rd = new Random();
        this.row = row;
        this.column = column;
        matrix = new DoblyCircularList<>();
        for (int i = 0; i < row; i++) {
            DoblyCircularList<Character> actualrow = new DoblyCircularList<>();
            for (int j = 0; j < column; j++) {
                Random random = new Random();
                char rand = (char) (random.nextInt(26) + 'A');   //Letra aleatoria
                actualrow.addLast('0'); // valor con el que se inicializa la matriz
            }
            matrix.addLast(actualrow);
        }
    }

    public DoblyCircularList<DoblyCircularList<Character>> getMatrix() {
        return matrix;
    }

    public DoblyCircularList<String> getListWords() {
        return listWords;
    }

    //metodo para mostrar la matriz y sus valores
    public void showMatrix() {
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        while (iterator.hasNext()) {
            iterator.next().show();
        }
    }

    @Override
    public void moveRowToRightAt(int index) {
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doRightBitshifting();
        matrix.setAt(actualRow, index);
    }

    @Override
    public void moveRowToLeftAt(int index) {
        DoblyCircularList<Character> actualRow = matrix.getIndex(index);
        actualRow.doLeftBitshifting();
        matrix.setAt(actualRow, index);
    }

    @Override
    public void moveColumnUpAt(int index) {
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while (iterator.hasNext()) {
            actualColumn.addLast(iterator.next().getIndex(index));
        }

        actualColumn.doLeftBitshifting();

        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while (rowIterator.hasNext() && newColumnIterator.hasNext()) {
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }

    }

    @Override
    public void moveColumnDownAt(int index) {
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        DoblyCircularList<Character> actualColumn = new DoblyCircularList<>();
        while (iterator.hasNext()) {
            actualColumn.addLast(iterator.next().getIndex(index));
        }

        actualColumn.doRightBitshifting();

        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newColumnIterator = actualColumn.iterator();
        while (rowIterator.hasNext() && newColumnIterator.hasNext()) {
            rowIterator.next().setAt(newColumnIterator.next(), index);
        }

    }

    //metodo para cargar palabras a la sopa de letras
    public void agregarPalabras(String ruta) {
        DoblyCircularList<String> listaPalabras = Word.loadWords("src/main/resources/espol/"+ruta);
        DoblyCircularList<String> palabrasSopa = new DoblyCircularList<>();
        Random r = new Random();

        int conteo = 0;
        while (conteo < 3) {  //NUMERO DE PALABRAS QUE SE VAN A INGRESAR
            int x = r.nextInt(row);
            int y = r.nextInt(column);

            int indexpalabra = r.nextInt(listaPalabras.size());
            String palabra = listaPalabras.getIndex(indexpalabra);
            //String copyPalabra = palabra;
            int lenPal = palabra.length();

            boolean invertirONo = r.nextBoolean();

            while (lenPal > row && lenPal > column) {
                indexpalabra = r.nextInt(listaPalabras.size());
                palabra = listaPalabras.getIndex(indexpalabra);
                lenPal = palabra.length();
            }

            if (invertirONo) {
                palabra = invertirPalabra(palabra);
            }

            int numOrient = r.nextInt(3);

            boolean v = confirmar(x, y, palabra, numOrient);
            if (v) {
                switch (numOrient) {
                    case 0:
                        //horizontal
                        if ((lenPal <= column && lenPal > row) || (lenPal <= column && lenPal <= row)) {
                            DoblyCircularList<Character> actualRow = matrix.getIndex(x);

                            for (int i = 0; i < lenPal; i++) {
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                int indice = i + y;
                                if (indice >= column) {
                                    indice = indice - column;
                                }
                                boolean conf = actualRow.setAt(letra, indice);
                            }
                            conteo++;
                            if (invertirONo){
                                String notinverted = invertirPalabra(palabra); //la regresa a su orden original
                                palabrasSopa.addLast(notinverted.toUpperCase());
                            } else {
                                palabrasSopa.addLast(palabra.toUpperCase());
                            }    
                        }
                        break;

                    case 1:
                        //VERTICAL
                        if ((lenPal <= row && lenPal > column) || (lenPal <= column && lenPal <= row)) {
                            for (int i = 0; i < lenPal; i++) {
                                int indice = i + x;
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                if (indice >= row) {
                                    indice = indice - row;
                                }
                                DoblyCircularList<Character> actualrow = matrix.getIndex(indice);
                                boolean conf = actualrow.setAt(letra, y);
                            }
                            conteo++;
                            if (invertirONo){
                                String notinverted = invertirPalabra(palabra); //la regresa a su orden original
                                palabrasSopa.addLast(notinverted.toUpperCase());
                            } else {
                                palabrasSopa.addLast(palabra.toUpperCase());
                            }
                        }

                        break;

                    case 2:
                        //DIAGONAL
                        int indx = x;
                        int indy = y;
                        int cont = 0;
                        int cont2 = 1;

                        //len que entran derecha abajo
                        while (indx < row && indy < column) {
                            indx++;
                            indy++;
                            cont++;
                        }
                        indx = x;
                        indy = y;
                        //len que entran izquierda arriba
                        while (indx > 0 && indy > 0) {
                            indx--;
                            indy--;
                            cont2++;
                        }
                        indx = x;
                        indy = y;
                        int cont3 = 0;
                        //len que entran izquierda abajo
                        while (indx < row && indy > 0) {
                            indx++;
                            indy--;
                            cont3++;
                        }
                        indx = x;
                        indy = y;
                        int cont4 = 0;
                        //len que entran derecha arriba
                        while (indx > 0 && indy < column) {
                            indx--;
                            indy++;
                            cont4++;
                        }

                        boolean conf = false;
                        for (int i = 0; i < lenPal; i++) {
                            if (lenPal < cont) {
                                int indicex = x + i;
                                int indicey = y + i;
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                                conf = actualrow.setAt(letra, indicey);

                            } else if (lenPal < cont2) {
                                int indicex = x - i;
                                int indicey = y - i;
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                                conf = actualrow.setAt(letra, indicey);

                            } else if (lenPal < cont3) {
                                int indicex = x + i;
                                int indicey = y - i;
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                                conf = actualrow.setAt(letra, indicey);

                            } else if (lenPal < cont4) {
                                int indicex = x - i;
                                int indicey = y + i;
                                Character letra = Character.toUpperCase(palabra.charAt(i));
                                DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                                conf = actualrow.setAt(letra, indicey);

                            }

                        }

                        if (conf) {
                            conteo++;
                            if (invertirONo){
                                String notinverted = invertirPalabra(palabra); //la regresa a su orden original
                                palabrasSopa.addLast(notinverted.toUpperCase());
                            } else {
                                palabrasSopa.addLast(palabra.toUpperCase());
                            }
                        }
                        break;
                } //switch
            }   //if de v
        } //if  
        //rellenarRandom();
        listWords = palabrasSopa;
    }

    public void rellenarRandom() {
        Iterator<DoblyCircularList<Character>> it = matrix.iterator();
        while (it.hasNext()) {
            DoblyCircularList<Character> lista = it.next();
            for (int j = 0; j < row; j++) {
                Character letter = lista.getIndex(j);
                if (letter == '0') {
                    Random random = new Random();
                    char rand = (char) (random.nextInt(26) + 'A');
                    lista.setAt(rand, j);
                }
            }
        }
    }

    public boolean confirmar(int x, int y, String palabra, int orientacion) {
        int len = palabra.length();
        boolean confirmacion = true;

        switch (orientacion) {
            case 0:
                int i = 0;
                DoblyCircularList<Character> actualRow = matrix.getIndex(x);
                while (confirmacion == true && i < len) {
                    int indicey = i + y;
                    if (indicey >= column) {
                        indicey = indicey - column;
                    }
                    if (actualRow.getIndex(indicey) != '0') {
                        confirmacion = false;
                    }
                    i++;
                }
                break;

            case 1:
                int s = 0;
                while (confirmacion == true && s < len) {
                    int indice = s + x;
                    if (indice >= row) {
                        indice = indice - row;
                    }
                    DoblyCircularList<Character> actualrow = matrix.getIndex(indice);
                    if (actualrow.getIndex(indice) != '0') {
                        confirmacion = false;
                    }
                    s++;
                }
                break;

            case 2:

                //DIAGONAL
                int indx = x;
                int indy = y;
                int cont = 0;
                int cont2 = 1;

                //len que entran derecha abajo
                while (indx < row && indy < column) {
                    indx++;
                    indy++;
                    cont++;
                }
                indx = x;
                indy = y;
                //len que entran izquierda arriba
                while (indx > 0 && indy > 0) {
                    indx--;
                    indy--;
                    cont2++;
                }
                indx = x;
                indy = y;
                int cont3 = 0;
                //len que entran izquierda abajo
                while (indx < row && indy > 0) {
                    indx++;
                    indy--;
                    cont3++;
                }
                indx = x;
                indy = y;
                int cont4 = 0;
                //len que entran derecha arriba
                while (indx > 0 && indy < column) {
                    indx--;
                    indy++;
                    cont4++;
                }

                int t = 0;
                boolean conf = false;
                while (confirmacion == true && t < len) {
                    if (len < cont) {
                        int indicex = x + t;
                        int indicey = y + t;
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        if (actualrow.getIndex(indicey) != '0') {
                            confirmacion = false;
                        }
                    } else if (len < cont2) {
                        int indicex = x - t;
                        int indicey = y - t;
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        if (actualrow.getIndex(indicey) != '0') {
                            confirmacion = false;
                        }

                    } else if (len < cont3) {
                        int indicex = x + t;
                        int indicey = y - t;
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        if (actualrow.getIndex(indicey) != '0') {
                            confirmacion = false;
                        }

                    } else if (len < cont4) {
                        int indicex = x - t;
                        int indicey = y + t;
                        DoblyCircularList<Character> actualrow = matrix.getIndex(indicex);
                        if (actualrow.getIndex(indicey) != '0') {
                            confirmacion = false;
                        }
                    }

                    t++;
                }
        }
        return confirmacion;
    }

    public static String invertirPalabra(String palabra) {
        Stack<Character> pila = new Stack<>();
        String palabraInvert = "";
        for (int i = 0; i < palabra.length(); i++) {
            pila.push(palabra.charAt(i));
        }
        while (!pila.isEmpty()) {
            palabraInvert += pila.pop();
        }
        return palabraInvert;

    }

    @Override
    public void insertRandomRowAt(int index) {
        int rowSize = matrix.getIndex(0).size();
        DoblyCircularList<Character> newRow = new DoblyCircularList<>();
        for (int i = 0; i < rowSize; i++) {
            Character c = LETRAS.charAt(rd.nextInt(LETRAS.length()));
            newRow.addLast(c);
        }
        matrix.addAt(newRow, index);
    }

    @Override
    public void insertRandomColumnAt(int index) {
        int columnSize = matrix.size();
        DoblyCircularList<Character> newColumn = new DoblyCircularList<>();
        for (int i = 0; i < columnSize; i++) {
            Character c = LETRAS.charAt(rd.nextInt(LETRAS.length()));
            newColumn.addLast(c);
        }
        Iterator<DoblyCircularList<Character>> rowIterator = matrix.iterator();
        Iterator<Character> newElementIterator = newColumn.iterator();
        while (rowIterator.hasNext()) {
            rowIterator.next().addAt(newElementIterator.next(), index);
        }
    }

    @Override
    public void deleteRowAt(int index) {
        matrix.deleteAt(index);
    }

    @Override
    public void deleteColumnAt(int index) {
        Iterator<DoblyCircularList<Character>> iterator = matrix.iterator();
        while (iterator.hasNext()) {
            iterator.next().deleteAt(index);
        }
    }
}
