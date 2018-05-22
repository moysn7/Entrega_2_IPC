/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embarcacion;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import static javax.management.Query.value;


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
    @FXML
    private GridPane panel1;
    @FXML
    private GridPane panel2;
    @FXML
    private GridPane panel3;
    private CheckBox check1;
    private Button check2;
    private CheckBox check3;
    @FXML
    private Text dato_1_1;
    private Button check;
    @FXML
    private Label valor_roll;
    @FXML
    private Button but2;
    @FXML
    private Button but3;
    @FXML
    private Button but1;
    private boolean dia = true;
    @FXML
    private AnchorPane root;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Bindings de los botones
        
       // but1.setText(value).bind(
        //Bindings.dia);
    
        
        //Fin de bindings
        
        DecimalFormat df = new DecimalFormat("#.#####");
        
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
            String dat = String.valueOf(newValue) + "Kn";
            Platform.runLater(() -> {
                valor_aws.setText(dat);
            });
        });
        
        //--------------------- BLOQUE 2 "RUMBO" -----------------------------
        
        model.GPSproperty().addListener((observable, oldValue, newValue)-> {
            Platform.runLater(() -> {
                valor_lat.setText(String.valueOf(df.format(newValue.getLatitude())) +
                        " " + newValue.getLatitudeHemisphere());
                
                 valor_lon.setText(String.valueOf(df.format(newValue.getLongitude())) +
                        " " + newValue.getLongitudeHemisphere());
            });
        });
        
      
        
         
        model.COGProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "";
            Platform.runLater(() -> {
                valor_cog.setText(dat);
            });
        });
          
        model.SOGProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "Kn";
            Platform.runLater(() -> {
                valor_sog.setText(dat);
            });
        }); 
        
     //--------------------- BLOQUE 3 "     " -----------------------------
     
        model.ROLLProperty().addListener((observable, oldValue, newValue)-> {
            String dat = String.valueOf(newValue) + "-";
            Platform.runLater(() -> {
                valor_sog.setText(dat);
            });
        }); 
        
    }
    
    
    
    // CARGAR FICHERO NMEA 
    
    public void cargarDatos() throws FileNotFoundException{
    
        File ficheroNMEA = new File("Jul_20_2017_1871339_0183.NMEA");
        model.addSentenceReader(ficheroNMEA);
        
    }
    
    // Line chart

    //boton dia-noche pinche
    @FXML
    private void but2clk(ActionEvent event) {
    if(!dia){
                    root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mdia.css").toExternalForm());
                    dia = true;
                    but1.setText("Modo noche");
                    but2.setText("Modo noche");
                    but3.setText("Modo noche");
        }

                else{
                        root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mnoche.css").toExternalForm());
                        dia = false;
                        but1.setText("Modo día");
                        but2.setText("Modo día");
                        but3.setText("Modo día");
    }
    
        
    }

    @FXML
    private void but3clk(ActionEvent event) {
    if(!dia){
                    root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mdia.css").toExternalForm());
                    dia = true;
                    but1.setText("Modo noche");
                    but2.setText("Modo noche");
                    but3.setText("Modo noche");
        }

                else{
                        root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mnoche.css").toExternalForm());
                        dia = false;
                        but1.setText("Modo día");
                        but2.setText("Modo día");
                        but3.setText("Modo día");
    }
    }

    @FXML
    private void but1clk(ActionEvent event) {
        if(!dia){
                    root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mdia.css").toExternalForm());
                    dia = true;
                    but1.setText("Modo noche");
                    but2.setText("Modo noche");
                    but3.setText("Modo noche");
        }

                else{
                        root.getStylesheets().clear();
                    root.getStylesheets().add(Embarcacion.class.getResource("mnoche.css").toExternalForm());
                        dia = false;
                        but1.setText("Modo día");
                        but2.setText("Modo día");
                        but3.setText("Modo día");
    }
    }

    @FXML
    private void v1(Event event) {
        if(dia){
            but1.setText("Modo noche");
            but2.setText("Modo noche");
            but3.setText("Modo noche");
        }
        else{
            but1.setText("Modo día");
            but2.setText("Modo día");
            but3.setText("Modo día");
            
    }
    }

    @FXML
    private void v2(Event event) {
        if(dia){
            but1.setText("Modo noche");
            but2.setText("Modo noche");
            but3.setText("Modo noche");
        }
        else{
            but1.setText("Modo día");
            but2.setText("Modo día");
            but3.setText("Modo día");
            

    }
    }

    @FXML
    private void v3(Event event) {
        if(dia){
            but1.setText("Modo noche");
            but2.setText("Modo noche");
            but3.setText("Modo noche");
        }
        else{
            but1.setText("Modo día");
            but2.setText("Modo día");
            but3.setText("Modo día");
            

    }
        
    } 
    }
