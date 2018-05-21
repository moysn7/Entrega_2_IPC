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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.nmea.sentence.HDGSentence;
import net.sf.marineapi.nmea.sentence.MDASentence;
import net.sf.marineapi.nmea.sentence.MWVSentence;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.XDRSentence;
import net.sf.marineapi.nmea.util.Measurement;

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
    
    // -------------DATOS REFERENTES AL RUMBO ----------------------- //
    
    private final DoubleProperty HDG = new SimpleDoubleProperty();
    public DoubleProperty HDGProperty(){
        return HDG;
    }
    
    // -------------DATOS REFERENTES AL CLINÓMETRO ----------------------- //
    
    private final DoubleProperty PITCH = new SimpleDoubleProperty();
    public DoubleProperty PITCHProperty(){
        return PITCH;
    }

    private final DoubleProperty ROLL = new SimpleDoubleProperty();
    public DoubleProperty ROLLProperty(){
        return ROLL;
    }
    
    
    // -------------DATOS REFERENTES AL GPS ----------------------- //

    private final DoubleProperty LAT = new SimpleDoubleProperty();
    public DoubleProperty LATProperty(){
        return LAT;
    }
    
    private final DoubleProperty LON = new SimpleDoubleProperty();
    public DoubleProperty LONProperty(){
        return LON;
    }
    
    private final DoubleProperty COG = new SimpleDoubleProperty();
    public DoubleProperty COGProperty(){
        return COG;
    }
    
    private final DoubleProperty SOG = new SimpleDoubleProperty();
    public DoubleProperty SOGProperty(){
        return SOG;
    }
    
    // -------------DATOS REFERENTES A LA TEMPERATURA ----------------------- //

    private final DoubleProperty TEMP = new SimpleDoubleProperty();
    public DoubleProperty TEMProperty(){
        return TEMP;
    }
    
    
    class MDASentenceListener
        extends AbstractSentenceListener<MDASentence> {

        @Override
        public void sentenceRead(MDASentence sentence) {
            // TRUE WIND DIR
           TWD.set(sentence.getTrueWindDirection());
        
           //TRUE WIND SPEED
           TWS.set(sentence.getWindSpeedKnots());
           
           //TEMPERATURA
           TEMP.set(sentence.getAirTemperature());
           }
    }
    
    class MWVSentenceListener
        extends AbstractSentenceListener<MWVSentence> {
        
        @Override
        public void sentenceRead(MWVSentence sentence){
            // APARENT WIND ANGLE
            AWA.set(sentence.getAngle());
            
            // APARENT WIND SPEED
            AWS.set(sentence.getSpeed());
        }
    }
    
    class  HDGSentenceListener
        extends AbstractSentenceListener<HDGSentence> {
    
        @Override
        public void sentenceRead(HDGSentence sentence){
            // HEADING
            HDG.set(sentence.getHeading());
        }
    }
    
    class RMCSentenceListener
        extends AbstractSentenceListener<RMCSentence> {
        
        @Override
        public void sentenceRead(RMCSentence sentence){
            //LATITUD
            
            LAT.set(sentence.getPosition().getLatitude());
            
            //LONGITUD
            LON.set(sentence.getPosition().getLongitude());
            
            //COURSE OVER GROUND
            COG.set(sentence.getCourse());
            
            //SPEED OVER GROUND
            SOG.set(sentence.getSpeed());
        }
    }
    
    class XDRSentenceListener
        extends AbstractSentenceListener<XDRSentence>{
    
        @Override
        public void sentenceRead(XDRSentence sentence){
            for (Measurement me : sentence.getMeasurements()) {
                if (me.getName().equals("PTCH")){
                      PITCH.set(me.getValue());
                }
                   else if(me.getName().equals("ROLL")) {
                    ROLL.set(me.getValue());
                }
            }
        }
    }
    
    public void addSentenceReader(File file) throws FileNotFoundException {

        InputStream stream = new FileInputStream(file);
        if (reader != null) { 
            reader.stop();
        }
        reader = new SentenceReader(stream);
 
    //====================== SentenceListener ================================//
    
    
        HDGSentenceListener hdg = new HDGSentenceListener();
        reader.addSentenceListener(hdg);

        MDASentenceListener mda = new MDASentenceListener();
        reader.addSentenceListener(mda);

        MWVSentenceListener mwv = new MWVSentenceListener();
        reader.addSentenceListener(mwv);

        RMCSentenceListener rmd = new RMCSentenceListener();
        reader.addSentenceListener(rmd);
        
        XDRSentenceListener xdr  = new XDRSentenceListener();
        reader.addSentenceListener(xdr);
       
                
    //==============================================================
    
        reader.setExceptionListener(e->{System.out.println(e.getMessage());});
         
    //================================================================
    
        reader.start();
    }
}


