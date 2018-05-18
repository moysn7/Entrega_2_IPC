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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author moiminme
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private LineChart<String, Number> dir_v;
   // ObservableList<XYChart.Data<String, Double>> xyList = 
    @FXML
    private LineChart<?, ?> int_v;
    @FXML
    
    private Label valor_twd;
    @FXML
    private Label valor_tws;
    @FXML
    private Label valor_awa;
    @FXML
    private Label valor_aws;
    
    private Model model;
    @FXML
    
    private Slider slider;
    @FXML
    private Label valor_lat;
    @FXML
    private Label valor_lon;
    @FXML
    private Label valor_cog;
    @FXML
    private Label valor_sog;
    
    private final int nx = 10;
    private final double[] arrayLat = new double[nx];
    

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
        
        //--------------------- BLOQUE 2 "RUMBO" -----------------------------
        
        model.LATProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "---";
            Platform.runLater(() -> {
                valor_lat.setText(dat);
            });
        });
        
        model.LONProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "---";
            Platform.runLater(() -> {
                valor_lon.setText(dat);
            });
        });
         
        model.COGProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "---";
            Platform.runLater(() -> {
                valor_cog.setText(dat);
            });
        });
          
        model.SOGProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "Km/h (?)";
            Platform.runLater(() -> {
                valor_sog.setText(dat);
            });
        }); 
        
     //--------------------- BLOQUE 3 "     " -----------------------------

        
    }
    
    
    
    // CARGAR FICHERO NMEA 
    
    public void cargarDatos() throws FileNotFoundException{
    
        File ficheroNMEA = new File("Jul_20_2017_1871339_0183.NMEA");
        model.addSentenceReader(ficheroNMEA);
        
    }
    
    // Line chart
    
}
