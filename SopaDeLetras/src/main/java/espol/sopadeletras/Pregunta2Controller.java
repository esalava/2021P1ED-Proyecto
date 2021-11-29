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
	
	public static String nombre;
    public static String medida;
    public static int fila;
    public static int colum;
    public static String tiempo;

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
    
    
    
   @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
	   if ((cuadroT3.getText() == null) || (cuadroT3.getText().trim().isEmpty()) || (cuadroT2.getText() == null) || (cuadroT2.getText().trim().isEmpty())) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("Error");
           alert.setContentText("Completa todos los campos");
           alert.showAndWait();
           
           
       }else{
       	App.switchScenes(event, "PartidaEXTREME", 1400, 800);
           nombre = cuadroT3.getText();
           medida = cb1.getValue();
           tiempo = cuadroT2.getText();
           String filas = medida.substring(0,0);
           String column = medida.substring(2,2); 
           fila = Integer.parseInt(filas);
           colum = Integer.parseInt(column);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText("Éxito");
           alert.setContentText("Jugador: "+cuadroT3.getText()+" creado");
           alert.showAndWait();
           
       }
       
   }
    
   
   public static int getFilaValue() {
   	return fila;
   }
   
   public static int getColumValue() {
   	return colum;
   }
   
   public static String getTimeValue() {
   	return tiempo;
   }

   public static String getNameValue() {
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
    

}
