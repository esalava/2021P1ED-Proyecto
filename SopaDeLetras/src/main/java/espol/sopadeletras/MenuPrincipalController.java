package espol.sopadeletras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuPrincipalController {
    
    
    
    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "Partida", 882, 654);
    }
}
