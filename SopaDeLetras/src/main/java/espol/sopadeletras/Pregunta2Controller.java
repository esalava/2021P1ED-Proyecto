package espol.sopadeletras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Pregunta2Controller {
	@FXML
	private static ComboBox<String> cb1 = new ComboBox<String>();

    @FXML
    private static TextField cuadroT2;
    @FXML
    private static TextField cuadroT3;
    @FXML
    private VBox vboxElementos;
    @FXML
    private Button botonOk;
	


    //private static int filas = Integer.parseInt(cuadroT1.getText());
    //private static int columns = Integer.parseInt(cuadroT2.getText());
    //private static int nombre = Integer.parseInt(cuadroT3.getText());
    
    @FXML
    private void initialize() {
        System.out.println("Combobox inicializado");
    }
    
    public static String getMedidaValue2() {
    	String seleccion = cb1.getValue();
		return seleccion;
    }
    
    public static String getTimeValue() {
    	String seleccion = cuadroT2.getText();
		return seleccion;
    }

    public static String getNameValue2() {
    	String seleccion = cuadroT3.getText();
    	return seleccion;
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Alerta");
        alert.setHeaderText("Error de selección");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
   @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
    	/**String sel1 = getComboValue();
    	String sel2 = getNameValue();
    	if((sel1 == null) || sel2 == null) {
    		mostrarAlerta(Alert.AlertType.INFORMATION,"Completa todos los campos");
    	}**/
    	
        App.switchScenes(event, "PartidaEXTREME", 882, 654);
        
    }
    
    

}
