/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embarcacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Moga
 */
public class FXMLPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private Slider slider;
    @FXML
    private LineChart<?, ?> dir_v;
    @FXML
    private LineChart<?, ?> int_v;
    @FXML
    private Text dato4;
    @FXML
    private Text dato3;
    
    //Para saltar entre viento, direccion y coordenadas sin cambiar de stage
    private Parent root1;
    private Parent root2;
    private Parent root3;
    private VBox vbox;
    @FXML
    private Text dato1;
    @FXML
    private Text dato2;
    @FXML
    private Text valor3;
    @FXML
    private Text valor4;
    @FXML
    private Text valor2;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void vientoclk(MouseEvent event) {
        dato1.setText("TWD");
        dato2.setText("AWD");
        dato3.setText("TWS");
        dato4.setText("AWS");
        
    }

    @FXML
    private void dirclk(MouseEvent event) {
        dato1.setText("HDG");
        dato2.setText("PITCH");
        dato3.setText("ROLL");
        dato4.setText("---");
    }
    @FXML
    private void coordclk(MouseEvent event) {
        dato1.setText("LAT");
        dato2.setText("LON");
        dato3.setText("COG");
        dato4.setText("SOG");
    
    }
    
}
