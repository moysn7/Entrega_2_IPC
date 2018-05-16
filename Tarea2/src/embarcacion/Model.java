/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embarcacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.nmea.sentence.MDASentence;
import net.sf.marineapi.nmea.sentence.MWVSentence;

/**
 *
 * @author moiminme
 */
public class Model {
    
    private static Model model;

    private Model() {
    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    
    
    private SentenceReader reader;
    
    // -------------DATOS REFERENTES AL VIIENTO ----------------------- //
    
    private final DoubleProperty TWD = new SimpleDoubleProperty();
    public DoubleProperty TWDProperty() {
        return TWD;
    }
    
    private final DoubleProperty TWS = new SimpleDoubleProperty();
    public DoubleProperty TWSProperty() {
        return TWS;
    }
    
    private final DoubleProperty AWA = new SimpleDoubleProperty();
    public DoubleProperty AWAProperty() {
        return AWA;
    }
    
    private final DoubleProperty AWS = new SimpleDoubleProperty();
    public DoubleProperty AWSProperty() {
        return AWS;
    }
    
    
    class MDASentenceListener
        extends AbstractSentenceListener<MDASentence> {

        @Override
        public void sentenceRead(MDASentence sentence) {
            // TRUE WIND DIR
           TWD.set(sentence.getTrueWindDirection());
        
           //TRUE WIND SPEED
           TWS.set(sentence.getWindSpeedKnots());
           }
    }
    
    class MWVSentenceListener
        extends AbstractSentenceListener<MWVSentence> {
        
        public void sentenceRead(MWVSentence sentence){
            // APARENT WIND ANGLE
            AWA.set(sentence.getAngle());
            
            // APARENT WIND SPEED
            AWS.set(sentence.getSpeed());
        }
    }
    
    
    
    
    public void addSentenceReader(File file) throws FileNotFoundException {

    InputStream stream = new FileInputStream(file);
    if (reader != null) { 
        reader.stop();
    }
    reader = new SentenceReader(stream);
 
    //==================================================================
    //============= Registra todos los sentenceListener que necesites
    
    
   // HDGSentenceListener hdg = new HDGSentenceListener();
    //reader.addSentenceListener(hdg);

    MDASentenceListener mda = new MDASentenceListener();
    reader.addSentenceListener(mda);

    MWVSentenceListener mwv = new MWVSentenceListener();
    reader.addSentenceListener(mwv);

    //    RMCSentenceListener rmd = new RMCSentenceListener();
    //    reader.addSentenceListener(rmd);
        
                
    //===============================================================

    //===============================================================
    //== Anadimos un exceptionListener para que capture las tramas que 
    // == no tienen parser, ya que no las usamos
    reader.setExceptionListener(e->{System.out.println(e.getMessage());});
         
    //================================================================
    //======== arrancamos el SentenceReader para que empieze a escucha             
    reader.start();
    }
}


