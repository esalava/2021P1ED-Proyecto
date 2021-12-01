
package Matrix;

import TDA.DoblyCircularList;
import java.io.BufferedReader;
import java.io.FileReader;


public class Word {
    //METODO PARA LEER LAS PALABRAS DEL ARCHIVO, RETORNA LISTA
    public static DoblyCircularList<String> loadWords(String ruta){
        DoblyCircularList<String> listaPalabras = new DoblyCircularList<>();
        try {
            FileReader reader = new FileReader(ruta);
            BufferedReader br = new BufferedReader(reader);
            String linea;
            while ((linea = br.readLine())!= null){
                listaPalabras.addLast(linea.trim());
            }
            reader.close();
        } catch (Exception e){
            System.out.println("Arhivo de Palabras no ha sido encontrado");
        }
        return listaPalabras;
    }
}
