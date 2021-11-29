package espol.sopadeletras;

import Matrix.Cell;
import Matrix.LetraMatrix;
import Matrix.Matrix;
import Matrix.VerificacionesPalabras;
import TDA.DoblyCircularList;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PartidaNormalController {
    Random rd;

    @FXML
    private GridPane sopa;

    @FXML
    private Text textPlayer;

    /* *********** PALABRA **************/
    @FXML
    private Text textWord;
    
    private String ACTUALWORD = "";
    
    private DoblyCircularList<LetraMatrix> LISTWORD = new DoblyCircularList<>();
    /* **********************************/
    
    /****************PUNTAJE*********************/
    @FXML
    private Text textPoints;
    
    private int PLAYERPOINTS = 0;
    /*********************************************/
    /****************PALABRAS ENCONTRADAS********************/
    
    private DoblyCircularList<String> WORDSFOUND = new DoblyCircularList<>();
    
    /***********************************************************/
    @FXML
    private Text textTime = new Text("-");
    
    /************BOTONES PARA MOVER FILAS Y COLUMNAS **********/
    @FXML
    private VBox VBRightButtons;
    @FXML
    private VBox VBLeftButtons;
    @FXML
    private HBox HBUpButtons;
    @FXML
    private HBox HBDownButtons;
    
    /***********************************************************/
    
    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private int cantidadAddDelete = 0;

    int rows = 10;
    int columns = 10;
    Cell cells[][] = new Cell[rows][columns];
    private Matrix MATRIX = new Matrix(rows, columns);
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("MenuPrincipal");
    }

    @FXML
    private void initialize() {
        
        MATRIX.agregarPalabras();
        MATRIX.showMatrix();
        System.out.println("*********************************");
        System.out.println("Inicializacion de Partida Normal");
        rd = new Random();
        
        sopa.setGridLinesVisible(true);
        
        DoblyCircularList<DoblyCircularList<Character>> matrixList = MATRIX.getMatrix();
        Iterator<DoblyCircularList<Character>> rowIterator = matrixList.iterator();
        int y = 0;
        
        while(rowIterator.hasNext()){
            DoblyCircularList<Character> columnElements = rowIterator.next();
            Iterator<Character> colElementsIterator = columnElements.iterator();
            int x = 0;
            while(colElementsIterator.hasNext()){
                Character c = colElementsIterator.next();
                
                VBox vbox = new VBox();
                Text text = new Text(c + "");
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                
                final int indexX = x;
                final int indexY = y;
                
                vbox.setOnMouseClicked(e -> {
                    
                    vbox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
                    LetraMatrix letraMatrix = new LetraMatrix(indexY, indexX, c);
                    LISTWORD.addLast(letraMatrix);
                    
                    System.out.println(indexY + "," +indexX);
                    updateWord(c);
                    
                });
             
                
                sopa.add(vbox, x,y);  //column = x, row = y
                
                x++;
            }
            y++;
        }      
        
        loadAllButtons();
    }
    
    @FXML
    private void moveRowRight(int index){
        
        MATRIX.moveRowToRightAt(index);
        updateSopaPane();
        System.out.println("Se ha movido +1 hacia la derecha");
    }
    
    private void moveRowLeft(int index){
       
        MATRIX.moveRowToLeftAt(index);
        updateSopaPane();
        
        System.out.println("Se ha movido +1 hacia la izquierda");
    }
    
    private void moveColumnUp(int index){
        MATRIX.moveColumnUpAt(index);
        System.out.println("Se ha movido +1 hacia la arriba");
        updateSopaPane();
    }
    
    private void moveColumnDown(int index){
        MATRIX.moveColumnDownAt(index);
        System.out.println("Se ha movido +1 hacia la abajo");
        updateSopaPane();
    }
    
    
    /** actualiza la sopa de letras: el metodo debe ser llamado en cualquier
     *  cambio de la sopa
     **/
    
    private void updateSopaPane(){
        MATRIX.showMatrix();
        sopa.getChildren().clear();
        
        DoblyCircularList<DoblyCircularList<Character>> matrixList = MATRIX.getMatrix();
        Iterator<DoblyCircularList<Character>> rowIterator = matrixList.iterator();
        int y = 0;
        sopa.setGridLinesVisible(true);
        while(rowIterator.hasNext()){
            DoblyCircularList<Character> columnElements = rowIterator.next();
            Iterator<Character> colElementsIterator = columnElements.iterator();
            int x = 0;
            while(colElementsIterator.hasNext()){
                Character c = colElementsIterator.next();
                
                VBox vbox = new VBox();
                Text text = new Text(c + "");
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                
                final int indexX = x;
                final int indexY = y;
                
                vbox.setOnMouseClicked(e -> {
                    
                    vbox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
                    LetraMatrix letraMatrix = new LetraMatrix(indexY, indexX, c);
                    LISTWORD.addLast(letraMatrix);
                    
                    System.out.println(indexY + "," +indexX);
                    updateWord(c);
                });
                
                sopa.add(vbox, x,y);
                
                x++;
            }
            y++;
        } 
        
        System.out.println("Actualizacion correctamente");
        
        //loadAllButtons();
    }
    
    private void loadAllButtons(){
        loadButtonsRight();
        loadButtonsLeft();
        loadButttonsUp();
        loadButttonsDown();
    }
    
    private void loadButtonsRight(){
        
        for(int i = 0; i < MATRIX.getMatrix().size() ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            try(FileInputStream input = new FileInputStream("src/main/resources/espol/images/right_arrow.png")) {
                Image image = new Image(input, 40, 40, false, false);
                ImageView imV = new ImageView(image);
                newBox.getChildren().add(imV);
                newBox.setAlignment(Pos.CENTER);
                
            } catch (Exception e) {
                System.out.println("Image Not Found");
            }
              
            newBox.prefHeight(50);
            newBox.prefWidth(50);
            newBox.setOnMouseClicked(e -> {
                moveRowRight(idx);
            }); 
            
            VBRightButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButtonsLeft(){
        
        for(int i = 0; i < MATRIX.getMatrix().size() ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            try(FileInputStream input = new FileInputStream("src/main/resources/espol/images/left_arrow.png")) {
                Image image = new Image(input, 40, 40, false, false);
                ImageView imV = new ImageView(image);
                newBox.getChildren().add(imV);
                newBox.setAlignment(Pos.CENTER);
                
            } catch (Exception e) {
                System.out.println("Image Not Found");
            }

            newBox.prefHeight(50);
            newBox.prefWidth(50);
            newBox.setOnMouseClicked(e -> {
                moveRowLeft(idx);
            }); 
            
            VBLeftButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButttonsUp(){
        for(int i = 0; i < MATRIX.getMatrix().getIndex(0).size() ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            try(FileInputStream input = new FileInputStream("src/main/resources/espol/images/up_arrow.png")) {
                Image image = new Image(input, 40, 40, false, false);
                ImageView imV = new ImageView(image);
                newBox.getChildren().add(imV);
                newBox.setAlignment(Pos.CENTER);
                
            } catch (Exception e) {
                System.out.println("Image Not Found");
            }

            newBox.setOnMouseClicked(e -> {
                moveColumnUp(idx);
            }); 
            
            HBUpButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButttonsDown(){
        for(int i = 0; i < MATRIX.getMatrix().getIndex(0).size() ; i++){
            final int idx = i;
            HBox newBox = new HBox();
           
            try(FileInputStream input = new FileInputStream("src/main/resources/espol/images/down_arrow.png")) {
                Image image = new Image(input, 40, 40, false, false);
                ImageView imV = new ImageView(image);
                newBox.getChildren().add(imV);
                newBox.setAlignment(Pos.CENTER);
                
            } catch (Exception e) {
                System.out.println("Image Not Found");
            }

            newBox.setOnMouseClicked(e -> {
                moveColumnDown(idx);
            }); 
            
            HBDownButtons.getChildren().add(newBox);
        }
    
    }   
    
    private void updateButtons(){
        VBRightButtons.getChildren().clear();
        VBLeftButtons.getChildren().clear();
        HBUpButtons.getChildren().clear();
        HBDownButtons.getChildren().clear();
        
        loadAllButtons();
    }
    
    @FXML
    void addColumn(ActionEvent event) {
        if (cantidadAddDelete < 2){
            MATRIX.insertRandomColumnAt(0);
            updateSopaPane();
            updateButtons();
            cantidadAddDelete++;
            mostrarAlerta(Alert.AlertType.INFORMATION, "Quedan " + Integer.toString(2-cantidadAddDelete) + " oportunidades disponibles");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "HA ALCANZADO LAS OPORTUNIDADES MAXIMAS DE AGREGAR O ELIMINAR FILAS/COLUMNAS");
            System.out.println("YA NO PUEDE AGREGAR/ELIMINAR FILAS/COLUMNAS");
   
        }
    }

    @FXML
    void addRow(ActionEvent event) {  
        if (cantidadAddDelete < 2){
            MATRIX.insertRandomRowAt(0);
            updateSopaPane();
            updateButtons();
            cantidadAddDelete++;
            mostrarAlerta(Alert.AlertType.INFORMATION, "Quedan " + Integer.toString(2-cantidadAddDelete) + " oportunidades disponibles");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "HA ALCANZADO LAS OPORTUNIDADES MAXIMAS DE AGREGAR O ELIMINAR FILAS/COLUMNAS");
            System.out.println("YA NO PUEDE AGREGAR/ELIMINAR FILAS/COLUMNAS");
   
        }
    }

    @FXML
    void deleteColumn(ActionEvent event) {
        if (cantidadAddDelete < 2){
            MATRIX.deleteColumnAt(0);
            updateSopaPane();
            updateButtons();
            cantidadAddDelete++;
            mostrarAlerta(Alert.AlertType.INFORMATION, "Quedan " + Integer.toString(2-cantidadAddDelete) + " oportunidades disponibles");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "HA ALCANZADO LAS OPORTUNIDADES MAXIMAS DE AGREGAR O ELIMINAR FILAS/COLUMNAS");
            System.out.println("YA NO PUEDE AGREGAR/ELIMINAR FILAS/COLUMNAS");
  
        }
        
    }  

    @FXML
    void deleteRow(ActionEvent event) {
        if (cantidadAddDelete < 2){
            MATRIX.deleteRowAt(0);
            updateSopaPane();
            updateButtons();
            cantidadAddDelete++;
            mostrarAlerta(Alert.AlertType.INFORMATION, "Quedan " + Integer.toString(2-cantidadAddDelete) + " oportunidades disponibles");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "HA ALCANZADO LAS OPORTUNIDADES MAXIMAS DE AGREGAR O ELIMINAR FILAS/COLUMNAS");
            System.out.println("YA NO PUEDE AGREGAR/ELIMINAR FILAS/COLUMNAS");

        }
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    void cleanSopa(ActionEvent event) {
        updateSopaPane();
        
        //Se borra lo que estaba seleccionado
        ACTUALWORD = "";
        cleanListWord();
        textWord.setText(ACTUALWORD);
    }
    
    private void updateWord(Character c){
        ACTUALWORD += c + "";
        textWord.setText(ACTUALWORD);
    }
    
    
    private void cleanListWord(){
        DoblyCircularList<LetraMatrix> newListWord = new DoblyCircularList<>();
        LISTWORD = newListWord;
    }
    
    @FXML
    void validarPalabra(ActionEvent event) {
       
        if(VerificacionesPalabras.verificarSeleccionPalabra(LISTWORD)){
            /*La palabra se encuentra en ACTUALWORD aqui es donde se valida y se agregan los puntos si no se encuentran se restan*/
            System.out.println("La seleccion es valida");
            System.out.println(ACTUALWORD);
            Comparator<String> cmp = (String o1, String o2) -> o1.compareTo(o2);
            
            MATRIX.getListWords().show();
            if(MATRIX.getListWords().containsElement(ACTUALWORD, cmp) && !WORDSFOUND.containsElement(ACTUALWORD, cmp)){
                WORDSFOUND.addLast(ACTUALWORD);
                addPuntaje(ACTUALWORD.length());
                mostrarAlerta(Alert.AlertType.INFORMATION, "FELICIDADES HA ENCONTRADO UNA PALABRA! \n+"+ACTUALWORD.length()+" PUNTOS");
                cleanSopa(event);
            } else if (WORDSFOUND.containsElement(ACTUALWORD, cmp)) {
                decPuntaje(1);
                mostrarAlerta(Alert.AlertType.WARNING, "La palabra ya se ha encontrado! \nSE HA DISMINUIDO -1 PUNTO(S)");
            } else {
                decPuntaje(ACTUALWORD.length());
                mostrarAlerta(Alert.AlertType.WARNING, "LA PALABRA NO EXISTE EN LA LISTA PARA ENCONTRAR! \n-"+ACTUALWORD.length()+" PUNTOS");
                cleanSopa(event);
            }
        } else {
            decPuntaje(1);
            mostrarAlerta(Alert.AlertType.WARNING, "Realice una selección valida! \nSE HA DISMINUIDO -1 PUNTO(S)");
            cleanSopa(event);
        }
        
    }
    
    private void addPuntaje(int cantidad){
        PLAYERPOINTS+=cantidad;
        textPoints.setText(String.valueOf(PLAYERPOINTS));
    }
    
    private void decPuntaje(int cantidad){
        PLAYERPOINTS-=cantidad;
        textPoints.setText(String.valueOf(PLAYERPOINTS));
    }
    
}