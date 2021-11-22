package espol.sopadeletras;

import Matrix.Cell;
import TDA.DoblyCircularList;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PartidaCATEController {

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
    private Text textCate;

    
  
    
    int rows = 10;
    int columns = 10;
    Cell cells[][] = new Cell[rows][columns];
    
    @FXML
    private void switchTo() throws IOException {
        App.setRoot("MenuPrincipal");
    }

    @FXML
    private void initialize() {
        
    	
        sopa2.setGridLinesVisible(true);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                //System.out.println(y+","+x);
                Cell cell = new Cell(y, x, cells); //A 
                VBox vbox = new VBox();
                Text text = new Text(cell.getLetter());
                //text.prefHeight(10);
                //text.prefWidth(10);
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                //vbox.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
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
        //loadButtonsRight();
    }
    
    //carga los botones del lado derecho para mover las filas hacia la derecha
    @FXML
    private void moveRowRight(int ind){
        //Cell[] currentRow = cells[0]; //ABC
        
        
        DoblyCircularList<String> circularList = new DoblyCircularList<>();
        
        //se recorre la fila
        for (int i = 0 ; i<cells[ind].length ; i++){
            circularList.addLast(cells[i][ind].getLetter());
            //System.out.print(currentRow[i].getLetter() + " ");
        }
        
        circularList.doRightBitshifting();
        
        for (int i = 0 ; i<cells[ind].length ; i++){
            cells[i][ind].setLetter(circularList.getIndex(i));
        }
        
      
        updateSopaPane();
        
        System.out.println("Se ha movido +1 hacia la derecha");
    }
    
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
               
                sopa2.add(vbox, y,x);
                //cells[y][x] = cell;
            }
        }
        System.out.println("Actualizacion correctamente");
    }
    
    /**private void loadButtonsRight(){
        
        for(int i = 0; i < cells[0].length ; i++){
            HBox newBox = new HBox();
            
            newBox.getChildren().add(new Text("MOVE"));
            newBox.prefHeight(50);
            newBox.prefWidth(50);
            newBox.setOnMouseClicked(e -> {
                moveRowRight(i);
            });
            
            VBRightButtons2.getChildren().add(newBox);
        }
    
    }**/
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Alerta");
        alert.setHeaderText("Error de selección");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    
    
    
    
}
    
