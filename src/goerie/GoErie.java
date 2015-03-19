/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goerie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Bhavik Moradiya
 */
public class GoErie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            URL goerie = new URL("http://www.goerie.com/local/crime/map/");
            URLConnection yc = goerie.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc
                    .getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);

            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
