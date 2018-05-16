/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embarcacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author moiminme
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private LineChart<?, ?> dir_v;
    @FXML
    private LineChart<?, ?> int_v;
    @FXML
    private Text dato_1_1;
    @FXML
    private Label valor_twd;
    @FXML
    private Label valor_tws;
    @FXML
    private Label valor_awa;
    @FXML
    private Label valor_aws;
    
    private Model model;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            model=Model.getInstance();
            cargarDatos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //------------------- BLOQUE 1 - VIENTO ---------------------
        
        model.TWDProperty().addListener((observable, oldValue, newValue) -> {
            String dat = String.valueOf(newValue) + "º";
            Platform.runLater(() -> {
                valor_twd.setText(dat);
            });
        });
        
        model.TWSProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "Kn";
            Platform.runLater(() -> {
                valor_tws.setText(dat);
            });
        });
        
        model.AWAProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "º";
            Platform.runLater(() -> {
                valor_awa.setText(dat);
            });
        });
        
        model.AWSProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "Km/h (?)";
            Platform.runLater(() -> {
                valor_aws.setText(dat);
            });
        });
        
        //--------------------- BLOQUE 2 "    " -----------------------------
        
        
        
    }
    
    
    
    // CARGAR FICHERO NMEA 
    
    public void cargarDatos() throws FileNotFoundException{
    
        File ficheroNMEA = new File("Jul_20_2017_1871339_0183.NMEA");
        model.addSentenceReader(ficheroNMEA);
        
    }   
}
