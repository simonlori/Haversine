/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haversine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lori
 */
public class Haversine {

    final static double R = 6371.16;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*double lat01 = 46.186666670721024;
         double lon01 = 24.586666646879166;
         double lat02 = 46.186388892820105;
         double lon02 = 24.586388868978247;
         double a = getDistance(lat01, lat02, lon01, lon02);
         System.out.println("kilometer:");
         System.out.println(a);
         double b = a * 1000;
         System.out.println("meter:");
         System.out.println(b);*/

        /*
         try {
         File myObj = new File("koordinatak.txt");
         Scanner myReader = new Scanner(myObj);
         while (myReader.hasNextLine()) {
         String data = myReader.nextLine();
         data = data.replace(',', ' ');
         System.out.println(data);
         }
         myReader.close();
         } catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
         }
         */
        ArrayList<Double> lista = new ArrayList<Double>();
        ArrayList<Double> lat = new ArrayList<Double>();
        ArrayList<Double> lon = new ArrayList<Double>();
        
        ArrayList<Double> lista2 = new ArrayList<Double>();
        ArrayList<Double> lat2 = new ArrayList<Double>();
        ArrayList<Double> lon2 = new ArrayList<Double>();
        
        try {
            File myObj = new File("koordinatak.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String d = myReader.next();
                double a = Double.parseDouble(d);
                //System.out.println(a);
                lista.add(a);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int k = 0;
        for (int i = 0; i < lista.size(); i++) {
            //System.out.println(lista.get(i));
            ++k;
        }
        //System.out.println(k);

        for (int i = 0; i < lista.size(); i += 3) {
            //System.out.println(lista.get(i));
            lat.add(lista.get(i));
        }

        for (int i = 1; i < lista.size(); i += 3) {
            //
            System.out.println(lista.get(i));
            lon.add(lista.get(i));
        }

    }

    public static double getDistance(double lat1, double lat2, double lon1, double lon2) {
        double dLon = Math.toRadians(lon2 - lon1);
        double dLat = Math.toRadians(lat2 - lat1);

        double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)) + (Math.cos(Math.toRadians(lat1))) * (Math.cos(Math.toRadians(lat2))) * (Math.sin(dLon / 2)) * (Math.sin(dLon / 2));
        double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double result = angle * R;
        return result;
    }
}
