package espol.sopadeletras;

import Matrix.Cell;
import Matrix.LetraMatrix;
import Matrix.Matrix;
import Matrix.VerificacionesPalabras;
import Matrix.Word;
import TDA.DoblyCircularList;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PartidaEXTREMEController {
    Random rd;

    @FXML
    private GridPane sopa;

    @FXML
    private Text textPlayer = new Text(Pregunta2Controller.getNameValue());

    /* *********** PALABRA **************/
    @FXML
    private Text textWord;
    
    private String ACTUALWORD = ""; //Se aloja la palabra que se esta seleccionando
    
    private DoblyCircularList<LetraMatrix> LISTWORD = new DoblyCircularList<>();
    /* **********************************/
    
    /****************PUNTAJE*********************/
    @FXML
    private Text textPoints;
    
    private int PLAYERPOINTS = 0;
    
    @FXML
    private Text textError;
    
    private int errores = 0;
    /*********************************************/
    
    /****************PALABRAS ENCONTRADAS********************/
    
    private DoblyCircularList<String> WORDSFOUND = new DoblyCircularList<>(); //lista de palabras encontradas por el jugador
     @FXML
     private VBox VBoxPalabras;
     
     @FXML
     private Text textLLeno;
    
    /***********************************************************/
    @FXML
    private Text textTime = new Text(Pregunta2Controller.getTimeValue());
    
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
    
    private int cantidadAddDelete = 0; //Oportunidades para añadir una columna o una fila

    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    int rows = Pregunta2Controller.getFilaValue();
    int columns = Pregunta2Controller.getFilaValue();
    private Matrix MATRIX = new Matrix(rows, columns);
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("MenuPrincipal");
    }
    
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException{
    	mostrarAlerta(Alert.AlertType.WARNING, "Suerte para la próxima! Tú puedes! :D");
    	 realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);
    	App.switchScenes(event,"MenuPrincipal",593, 395);
    }

    @FXML
    private void initialize() {
    	Thread hiloCuenta = new Thread(new DecrementaCuenta());
        hiloCuenta.setDaemon(true);
        hiloCuenta.start();
        
    	textError.setText(errores+"");
        textPlayer.setText(Pregunta2Controller.getNameValue());
        MATRIX.agregarPalabras("palabras.txt");
        MATRIX.showMatrix();
        System.out.println("*********************************");
        System.out.println("Inicializacion de Partida Por Categorias");
        rd = new Random();
        //Palabras para poder llenar el vbox Verde.
        
        
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
        DoblyCircularList<String> listaPalabras = MATRIX.getListWords();
        Iterator<String> iteratorPalabras = listaPalabras.iterator();
        Comparator<String> cmp = (String o1, String o2) -> o1.compareTo(o2);
        while (iteratorPalabras.hasNext()) {
            String palabra = iteratorPalabras.next();
            TextField ptext = new TextField(palabra);
            ptext.setEditable(false);
            ptext.setAlignment(Pos.CENTER);
            ptext.setBackground(new Background(new BackgroundFill(Color.web("#88DBC2"), null, null)));
            if (WORDSFOUND.containsElement(palabra,cmp)) {
            	ptext.setBackground(new Background(new BackgroundFill(Color.web("118824"),null,null)));
            }
            VBoxPalabras.getChildren().add(ptext);
        }
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
        VBoxPalabras.getChildren().clear();
        textError.setText(errores+"");
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
        
        DoblyCircularList<String> listaPalabras = MATRIX.getListWords();
        Iterator<String> iteratorPalabras = listaPalabras.iterator();
        Comparator<String> cmp = (String o1, String o2) -> o1.compareTo(o2);
        VBoxPalabras.getChildren().add(textLLeno);
        while (iteratorPalabras.hasNext()) {
            String palabra = iteratorPalabras.next();
            TextField ptext = new TextField(palabra);
            ptext.setEditable(false);
            ptext.setAlignment(Pos.CENTER);
            if (WORDSFOUND.containsElement(palabra,cmp)) {
            	ptext.setBackground(new Background(new BackgroundFill(Color.web("118824"),null,null)));
            }else {ptext.setBackground(new Background(new BackgroundFill(Color.web("#88DBC2"), null, null)));
            }
            VBoxPalabras.getChildren().add(ptext);
        }
        if (WORDSFOUND.size() == listaPalabras.size()) {
        	mostrarAlerta(Alert.AlertType.INFORMATION,"Haz encontrado todas las palabras! Felicidades :D");
        	volverMenu();
        	realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);}
        
        
        System.out.println("Actualizacion correctamente");
        
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
    
    private static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
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
                //La palabra debe estar en la listas de palabras a encontrar (MATRIX.getListWords()) pero no debió haber sido encontrada anteriormente
                WORDSFOUND.addLast(ACTUALWORD);
                addPuntaje(ACTUALWORD.length());
                mostrarAlerta(Alert.AlertType.INFORMATION, "FELICIDADES HA ENCONTRADO UNA PALABRA! \n+"+ACTUALWORD.length()+" PUNTOS");
                cleanSopa(event);
                
            } else if (WORDSFOUND.containsElement(ACTUALWORD, cmp)) {
            	addErrores(1);
            	if (errores !=4) {
            	decPuntaje(1);
                mostrarAlerta(Alert.AlertType.WARNING, "La palabra ya se ha encontrado! \nSE HA DISMINUIDO -1 PUNTO(S)");}
            	else {mostrarAlerta(Alert.AlertType.WARNING, "Se te han acabado los intentos! Adiós :(");
            		  volverMenu();
            		  realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);}
            } else {
            	addErrores(1);
            	if (errores !=4) {
            	decPuntaje(ACTUALWORD.length());
                mostrarAlerta(Alert.AlertType.WARNING, "LA PALABRA NO EXISTE EN LA LISTA PARA ENCONTRAR! \n-"+ACTUALWORD.length()+" PUNTOS");
                cleanSopa(event);}
            	else {mostrarAlerta(Alert.AlertType.WARNING, "Se te han acabado los intentos! Adiós :(");
            		  volverMenu();
            		  realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);}
            }
        } else {
        	addErrores(1);
        	if(errores !=4) {
            decPuntaje(1);
            mostrarAlerta(Alert.AlertType.WARNING, "Realice una selección valida! \nSE HA DISMINUIDO -1 PUNTO(S)");
            cleanSopa(event);}
        	else{mostrarAlerta(Alert.AlertType.WARNING, "Se te han acabado los intentos! Adiós :(");
        		 volverMenu();
        		 realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);}
        }
        
    }
    
    private void volverMenu() {
    	try {
    	
            Parent root = App.loadFXML("MenuPrincipal");
            Stage gameStage = (Stage) textPlayer.getScene().getWindow();
            gameStage.close();
            Stage stage  = new Stage();
            Scene scene = new Scene(root, 593, 395);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e){
            System.out.println("File not found, Error al cargar pantalla");
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
    
    private void addErrores(int cantidad) {
    	errores+=cantidad;
    }
    
    private String getNombre() {
    	return textPlayer.getText();
    }
    
    private String getPuntaje() {
    	return textPoints.getText();
    } 
    
    class DecrementaCuenta implements Runnable {

        private int count = Integer.parseInt(Pregunta2Controller.getTimeValue());
        
        
        private void decrementCount() {
        if(count==0) {
            Platform.runLater(() -> {
                //Cuando se acaba el tiempo vuelve a la pantalla principal y guarda dentro del log
                mostrarAlerta(Alert.AlertType.WARNING, "Se ha acabado su tiempo, suerte la próxima!!");
                volverMenu();
                realizarLog(textPlayer.getText(),textPoints.getText(),textTime.getText(),timeStamp);
            });
            
           

        } else {
            count--;
            textTime.setText(Integer.toString(count));}
        }

        @Override
        public void run() {
            while (count>0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }

                decrementCount();
            }
            decrementCount();
        }

    }
    
    private void realizarLog(String nombre,String puntaje,String tiempo,String fecha) {
    	FileWriter fichero = null;
        BufferedWriter bw = null;
        try
        {
            fichero = new FileWriter("src/main/resources/espol/logs.txt",true);
            bw = new BufferedWriter(fichero);
            bw.write("PARTIDA XTREME: "+nombre+"  -  "+puntaje+"  -  "+"faltaban: "+tiempo+" segundos"+ "  -  "+fecha+"\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              bw.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
    	
    }
    
    
}
    
    
    
}
    
    