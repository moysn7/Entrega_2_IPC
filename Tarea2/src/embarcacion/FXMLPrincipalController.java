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
    private PieChart twd;
    @FXML
    private Text tit_dat3;
    @FXML
    private Text tit_dat4;
    @FXML
    private Text dato4;
    @FXML
    private Text dato3;
    @FXML
    private PieChart awa;
    
    //Para saltar entre viento, direccion y coordenadas sin cambiar de stage
    private Parent root1;
    private Parent root2;
    private Parent root3;
    private VBox vbox;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*
        try {
            // TODO
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("FXMLPrincipal.fxml"));
            root1 = loader1.load();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("FXMLDir.fxml"));
            root2 = loader2.load();
            
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("FXMLCoord.fxml"));
            root3 = loader3.load();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    

    @FXML
    private void vientoclk(MouseEvent event) {
    }

    @FXML
    private void dirclk(MouseEvent event) {
    }

    @FXML
    private void coordclk(MouseEvent event) {
    }
    
}
