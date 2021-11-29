package espol.sopadeletras;

import Matrix.Cell;
import TDA.DoblyCircularList;
import java.io.IOException;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PartidaEXTREMEController extends Pregunta2Controller{

    @FXML
    private GridPane sopa2;

    @FXML
    private VBox VBRightButtons2;

    @FXML
    private Text textPlayer2;

    @FXML
    private Text textWord2;

    @FXML
    private Font x1;

    @FXML
    private Text textPoints2;

    @FXML
    private Text textTime;
    
    @FXML
    private VBox VBRightButtons;
    @FXML
    private VBox VBLeftButtons;
    
    @FXML
    private HBox HBUpButtons;

    @FXML
    private HBox HBDownButtons;


    Random rd;
    int rows = Pregunta2Controller.getFilaValue();
    int columns = Pregunta2Controller.getColumValue();
    String tiempo = Pregunta2Controller.getTimeValue();
    String nombre = Pregunta2Controller.getNameValue();
    Cell cells[][] = new Cell[rows][columns];
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("MenuPrincipal");
    }

    @FXML
    private void initialize() {
    	
    	Thread hiloCuenta = new Thread(new DecrementaCuenta());
        hiloCuenta.setDaemon(true);
        hiloCuenta.start();
        rd = new Random();
        
        sopa2.setGridLinesVisible(true);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                //System.out.println(y+","+x);
                Cell cell = new Cell(y, x, cells); //A 
                VBox vbox = new VBox();
                Text text = new Text(cell.getLetter());
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                vbox.setOnMouseClicked(e -> {
                    vbox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
                });
                
                sopa2.add(vbox, y,x);
                cells[y][x] = cell;
            }
                
        }
        
    

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                cells[m][n].setNeighbours();
            }
        }
        
        
        //sopa.gridLinesVisibleProperty();
        loadAllButtons();
        setTextJugador(nombre);
        setTimeJugador(tiempo);
    }
    
    //carga los botones del lado derecho para mover las filas hacia la derecha
    
    private void setTextJugador(String nombre) {
    	textPlayer2.setText(nombre);
    }
    private void setTimeJugador(String tiempo) {
    	textTime.setText(tiempo);
    }
    
    @FXML
    private void moveRowRight(int index){
        //Cell[] currentRow = cells[0]; //ABC
        
        
        DoblyCircularList<String> circularList = new DoblyCircularList<>();
        
        //se recorre la fila
        for (int i = 0 ; i<cells[index].length ; i++){
            circularList.addLast(cells[i][index].getLetter());
           
        }
        
        circularList.doRightBitshifting();
        
        for (int i = 0 ; i<cells[index].length ; i++){
            cells[i][index].setLetter(circularList.getIndex(i));
        }
        
      
        updateSopaPane();
        
        System.out.println("Se ha movido +1 hacia la derecha");
    }
    
    private void moveRowLeft(int index){
        //Cell[] currentRow = cells[0]; //ABC
        
        
        DoblyCircularList<String> circularList = new DoblyCircularList<>();
        
        //se recorre la fila
        for (int i = 0 ; i<cells[index].length ; i++){
            circularList.addLast(cells[i][index].getLetter());
           
        }
        
        circularList.doLeftBitshifting();
        
        for (int i = 0 ; i<cells[index].length ; i++){
            cells[i][index].setLetter(circularList.getIndex(i));
        }
        
      
        updateSopaPane();
        
        System.out.println("Se ha movido +1 hacia la izquierda");
    }
    
    private void moveColumnUp(int index){
        //Cell[] currentRow = cells[0]; //ABC
        
        
        DoblyCircularList<String> circularList = new DoblyCircularList<>();
        
        //se recorre la columna
        for (int y = 0 ; y<cells.length ; y++){
            circularList.addLast(cells[index][y].getLetter());
        }
        
        circularList.doLeftBitshifting();
        
        for (int i = 0 ; i<cells.length ; i++){
            cells[index][i].setLetter(circularList.getIndex(i));
        }

        System.out.println("Se ha movido +1 hacia la arriba");
        updateSopaPane();
    }
    
    private void moveColumnDown(int index){
        //Cell[] currentRow = cells[0]; //ABC
        
        
        DoblyCircularList<String> circularList = new DoblyCircularList<>();
        
        //se recorre la columna
        for (int y = 0 ; y<cells.length ; y++){
            circularList.addLast(cells[index][y].getLetter());
        }
        
        circularList.doRightBitshifting();
        
        for (int i = 0 ; i<cells.length ; i++){
            cells[index][i].setLetter(circularList.getIndex(i));
        }

        System.out.println("Se ha movido +1 hacia la abajo");
        updateSopaPane();
    }
    
    
    
    
    /** actualiza la sopa de letras: el metodo debe ser llamado en cualquier
     *  cambio de la sopa
     **/
    
    private void updateSopaPane(){
        sopa2.getChildren().clear();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                
                Cell cell = cells[y][x];
                VBox vbox = new VBox();
                Text text = new Text(cell.getLetter());
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                vbox.setOnMouseClicked(e -> {
                    vbox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null)));
                });
                sopa2.add(vbox, y,x);
                //cells[y][x] = cell;
            }
        }
        System.out.println("Actualizacion correctamente");
    }
    
    private void loadAllButtons(){
        loadButtonsRight();
        loadButtonsLeft();
        loadButttonsUp();
        loadButttonsDown();
    }
    
    private void loadButtonsRight(){
        
        for(int i = 0; i < cells[0].length ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            newBox.getChildren().add(new Text("MOVE"));
            newBox.prefHeight(50);
            newBox.prefWidth(50);
            newBox.setOnMouseClicked(e -> {
                moveRowRight(idx);
            }); 
            
            VBRightButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButtonsLeft(){
        
        for(int i = 0; i < cells[0].length ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            newBox.getChildren().add(new Text("MOVE"));
            newBox.prefHeight(50);
            newBox.prefWidth(50);
            newBox.setOnMouseClicked(e -> {
                moveRowLeft(idx);
            }); 
            
            VBLeftButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButttonsUp(){
        for(int i = 0; i < cells[0].length ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            newBox.getChildren().add(new Text("MOVE"));
            newBox.setOnMouseClicked(e -> {
                moveColumnUp(idx);
            }); 
            
            HBUpButtons.getChildren().add(newBox);
        }
    }
    
    private void loadButttonsDown(){
        for(int i = 0; i < cells[0].length ; i++){
            final int idx = i;
            HBox newBox = new HBox();
            
            newBox.getChildren().add(new Text("MOVE"));
            newBox.setOnMouseClicked(e -> {
                moveColumnDown(idx);
            }); 
            
            HBDownButtons.getChildren().add(newBox);
        }
    
    
    }
    
    class DecrementaCuenta implements Runnable {

        private int count = Integer.parseInt(tiempo);

        
        private void decrementCount() {
        if(count==0) {/**mostrarAlerta(Alert.AlertType.INFORMATION,"Se ha acabado el tiempo :)"**/
        	System.out.println("SE ACABOOOO");
        			  count--;}
        else {
            count--;
            textTime.setText(Integer.toString(count));}
        }

        @Override
        public void run() {
            while (count>=0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }

                decrementCount();
            }
        }

    }
    
    
    
}
    
    