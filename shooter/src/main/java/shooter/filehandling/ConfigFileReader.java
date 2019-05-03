/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter.filehandling;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Luokka, joka lukee erillisestä tiedostosta peliin liittyvät asetukset, kuten ruudun koon, vihollisten lukumäärän jne.
 * @author jajuuso
 */
public class ConfigFileReader {
    
    /**
     *
     */
    public ConfigFileReader() {
        
    }

    /**
     * 
     * @param property
     * @return
     */
    public static String getString(String property) {
        
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            return prop.getProperty(property);
        
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     *
     * @param property
     * @return
     */
    public static int getInt(String property) {
        try{
            Properties prop = new Properties();
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            return Integer.parseInt(prop.getProperty(property));
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     *
     * @param property
     * @return
     */
    public static double getDouble(String property) {
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            return Double.parseDouble(prop.getProperty(property));
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    
    

    
}
