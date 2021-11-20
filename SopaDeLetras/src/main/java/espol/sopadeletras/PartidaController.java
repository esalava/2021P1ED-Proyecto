package espol.sopadeletras;

import Matrix.Cell;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PartidaController {

    @FXML
    private GridPane sopa;

    @FXML
    private Text textPlayer;

    @FXML
    private Text textWord;

    @FXML
    private Text textPoints;

    @FXML
    private Text textTime;


    
    int rows = 10;
    int columns = 10;
    Cell cells[][] = new Cell[rows][columns];
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("MenuPrincipal");
    }

    @FXML
    private void initialize() {
        sopa.setGridLinesVisible(true);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                System.out.println(y+","+x);
                Cell cell = new Cell(y, x, cells);
                VBox vbox = new VBox();
                Text text = new Text(cell.getLetter());
                //text.prefHeight(10);
                //text.prefWidth(10);
                
                vbox.getChildren().add(text);
                vbox.setPrefSize(50, 50);
                vbox.setAlignment(Pos.CENTER);
                //vbox.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
                sopa.add(vbox, y,x);
                cells[y][x] = cell;
            }
                
        }
        
    

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                cells[m][n].setNeighbours();
            }
        }
        //sopa.gridLinesVisibleProperty();
    }
    
    
    
    
}