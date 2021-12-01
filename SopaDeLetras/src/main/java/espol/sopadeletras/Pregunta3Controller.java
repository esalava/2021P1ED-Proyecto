package espol.sopadeletras;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
;


public class Pregunta3Controller {

	public static String nombre;
    public static String medida;
    public static int medidaVal;
    public static String categoria;
    
    @FXML
    private ComboBox<String> cb1;
    @FXML
    private ComboBox<String> cb2;
    @FXML
    private TextField cuadroT3;
    @FXML
    private VBox vboxElementos;
    @FXML
    private Button botonOk;
    
    
    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
    	
    	
    	if ((cuadroT3.getText().trim().isEmpty()) || cb1.getValue() == null || cb2.getValue() == null) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Completa todos los campos");
            alert.showAndWait();
            
            
        }else{
            nombre = cuadroT3.getText();
    		medida = cb1.getValue();
    		categoria = cb2.getValue();
    	
        	medidaVal = Integer.parseInt(medida.split("x")[0]); 
    		App.switchScenes(event, "PartidaCATE", 782, 656);//782,654
            
        }
        
    }
    
    @FXML
    private void initialize() {
    	nombre = "Buenas";
    	medida = "ads";
    	ObservableList<String> items = FXCollections.observableArrayList("9x9", "10x10", "11x11");
    	cb1.setItems(items);
    	ObservableList<String> items2 = FXCollections.observableArrayList("animales", "frutas", "países");
    	cb2.setItems(items2);
    	
    }
    
    
    public static int getFilaValue() {
    	return medidaVal;
    }
    

    public static String getNameValue() {
    	return nombre;
    }
    
    public static String getCateValue() {
    	return categoria;
    }
     

}
