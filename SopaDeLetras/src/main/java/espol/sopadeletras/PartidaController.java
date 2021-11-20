package espol.sopadeletras;

import java.io.IOException;
import javafx.fxml.FXML;

public class PartidaController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("MenuPrincipal");
    }
    
    
}