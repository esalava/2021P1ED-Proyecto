package espol.sopadeletras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PreguntaController {

    @FXML
    private static TextField cuadroT1;
    @FXML
    private static TextField cuadroT2;
    @FXML
    private static TextField cuadroT3;
    @FXML
    private VBox vboxElementos;

    //private static int filas = Integer.parseInt(cuadroT1.getText());
    //private static int columns = Integer.parseInt(cuadroT2.getText());
    //private static int nombre = Integer.parseInt(cuadroT3.getText());

    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "Partida", 882, 654);
    }

}
