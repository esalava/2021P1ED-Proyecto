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
        App.switchScenes(event, "Pregunta2", 389, 240);
    }
    
    @FXML
    private void switchToCate(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "Pregunta3", 389, 240);
    }
}
