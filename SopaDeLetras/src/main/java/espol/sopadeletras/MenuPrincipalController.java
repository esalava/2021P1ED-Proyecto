package espol.sopadeletras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuPrincipalController {
    
    
    
    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "Pregunta", 389, 240);
    }
    
    @FXML
    private void switchToXTREME(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "PartidaEXTREME", 882, 654);
    }
}
