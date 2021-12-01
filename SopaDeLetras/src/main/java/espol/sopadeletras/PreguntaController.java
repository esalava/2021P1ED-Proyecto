package espol.sopadeletras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
;


public class PreguntaController {

    public static String nombre;
    public static String medida;
    public static int fila;
    public static int colum;
    @FXML
    private static ComboBox<String> cb1 = new ComboBox<String>();
    @FXML
    private  TextField cuadroT3;
    @FXML
    private VBox vboxElementos;
    @FXML
    private Button botonOk;
    
    
    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.switchScenes(event, "PartidaNormal", 782, 654);
        
    	if ((cuadroT3.getText().trim().isEmpty())) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Completa todos los campos");
            alert.showAndWait();
            
            
        }else{
            App.switchScenes(event, "PartidaNormal", 782, 654);
            
            /*nombre = cuadroT3.getText();
            medida = cb1.getValue();
            String filas = medida.substring(0,0);
            String column = medida.substring(2,2); 
            fila = Integer.parseInt(filas);
            colum = Integer.parseInt(column);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Éxito");
            alert.setContentText("Jugador: "+cuadroT3.getText()+" creado");
            alert.showAndWait();*/
            
        }
        
    }
    
    private void initialize() {
    }
    
    
    public static int getFilaValue() {
    	return fila;
    }
    
    public static int getColumValue() {
    	return colum;
    }

    public static String getNameValue() {
    	return nombre;
    }
     

}
