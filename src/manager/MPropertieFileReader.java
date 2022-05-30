/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isi
 */
public class MPropertieFileReader {

    private String bdName, bdUrl, bdTimezone, bdUsername, bdPassword;
    private InputStream fs;
    private Properties props;

    public MPropertieFileReader() {

    }

    public void init(String filename) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        fs = loader.getResourceAsStream(filename);
        props = new Properties();
        try {
            props.load(fs);
        } catch (IOException ex) {
            Logger.getLogger(MPropertieFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        bdName = props.getProperty("DBNAME");
        bdUrl = props.getProperty("DBURL");
        bdTimezone = props.getProperty("SERVERTIMEZONE");
        bdUsername = props.getProperty("DBUSERNAME");
        bdPassword = props.getProperty("DBPASSWORD");
    }

    public String getBdName() {
        return this.bdName;
    }

    public String getBdUsername() {
        return this.bdUsername;
    }

    public String getBdUrl() {
        return this.bdUrl;
    }

    public String getBdPassword() {
        return this.bdPassword;
    }

    public String getBdTimezone() {
        return this.bdTimezone;
    }
}
