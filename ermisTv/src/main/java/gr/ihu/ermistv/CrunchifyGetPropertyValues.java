/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.ihu.ermistv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author User
 */
public class CrunchifyGetPropertyValues {
    String file;

    public CrunchifyGetPropertyValues(String file) {
        this.file = file;
    }

    public String getProperty(String property) {

        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }
        return prop.getProperty(property);
    }
}
