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
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lori
 */
public class Tavolsagszamitas {

    final static double R = 6371.16;

    final static ArrayList<String> xmllist = new ArrayList<String>();

    final static ArrayList<Double> lista = new ArrayList<Double>();
    final static ArrayList<Double> lat = new ArrayList<Double>();
    final static ArrayList<Double> lon = new ArrayList<Double>();

    final static ArrayList<Double> lista2 = new ArrayList<Double>();
    final static ArrayList<Double> lat2 = new ArrayList<Double>();
    final static ArrayList<Double> lon2 = new ArrayList<Double>();

    final static ArrayList<Double> lista3 = new ArrayList<Double>();

    final static ArrayList<Double> lista4 = new ArrayList<Double>();
    final static ArrayList<Double> lat3 = new ArrayList<Double>();
    final static ArrayList<Double> lon3 = new ArrayList<Double>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double lat1 = 46.521979;
        double lon1 = 24.596798;
        double lat2 = 46.521902;
        double lon2 = 24.596856;
        double a = tavolsagHaversine(lat1,lat2,lon1,lon2);
        double b = tavolsagKoszinusz(lat1,lat2,lon1,lon2);
        String t = String.valueOf(new DecimalFormat("##.####").format(a * 1000));
        String tt = String.valueOf(new DecimalFormat("##.####").format(b * 1000));
        System.out.println(a);
        System.out.println(b);
        System.out.println(t);
        System.out.println(tt);
        /*
        xmlkiolvasas("proba.kml");
        vesszok("XMLlista.txt");

        vesszok("koordinatak.txt");
        vesszok("palya.txt");
        vesszok("asd.txt");
        eredetikoordinatak("palya.txt");
        mertkoordinatak("koordinatak.txt");

        for (int i = 0; i < lat.size(); i++) {
            lista3.add(tavolsagHaversine(lat.get(i), lat2.get(i), lon.get(i), lon2.get(i)) * 1000);
        }

        double osszeg = 0;
        for (int i = 0; i < lista3.size(); i++) {
            //System.out.println(lista3.get(i));
            osszeg += lista3.get(i);
        }
        double atlag = osszeg / lista3.size();
        System.out.println("Osszeg: " + osszeg + ".");
        System.out.println("Atlag: " + atlag + ".");

        mertkoordinatakatlaga("asd.txt");
        */
    }

    public static void xmlkiolvasas(String t) {
        try {
            File fXmlFile = new File(t);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("coordinates");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("Current Element :" + nNode.getNodeName());
                //System.out.println(nNode.getChildNodes());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

                    xmllist.add(eElement.getTextContent());
                    //System.out.println(eElement.getTextContent());
                }
            }
            System.out.println("Sikeres XML file kiolvasas!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("XMLlista.txt");
            for (int i = 0; i < xmllist.size(); i++) {
                //System.out.println(list.get(i));
                myWriter.write(xmllist.get(i));
                myWriter.append("\n");
            }
            myWriter.close();
            System.out.println("Sikeres XML lista kiiras!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void mertkoordinatakatlaga(String t) {
        try {
            File myObj = new File(t);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String d = myReader.next();
                double a = Double.parseDouble(d);
                //System.out.println(a);
                lista4.add(a);
            }
            myReader.close();
            System.out.println("Sikeres mert koordinatak beolvasasa!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int k = 0;
        for (int i = 0; i < lista4.size(); i++) {
            //System.out.println(lista.get(i));
            ++k;
        }
        System.out.println("Mert koordinatak szama (atlaghoz): " + k + ".");

        for (int i = 0; i < lista4.size(); i += 3) {
            //System.out.println(lista4.get(i));
            lon3.add(lista4.get(i));
        }

        for (int i = 1; i < lista4.size(); i += 3) {
            //System.out.println(lista4.get(i));
            lat3.add(lista4.get(i));
        }

        int x = lat3.size();
        int y = lon3.size();
        System.out.println("Szelesseg koordinatak szama: " + x + ".");
        System.out.println("Hosszusag koordinatak szama: " + y + ".");
        double osszeg2 = 0;
        double osszeg3 = 0;
        for (int i = 0; i < lat3.size(); i++) {
            osszeg2 += lat3.get(i);
            osszeg3 += lon3.get(i);
        }
        System.out.println("Szelesseg atlag: " + osszeg2 / lat3.size() + ".");
        System.out.println("Hosszusag atlag: " + osszeg3 / lon3.size() + ".");
    }

    public static void eredetikoordinatak(String t) {
        try {
            File myObj = new File(t);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String d = myReader.next();
                double a = Double.parseDouble(d);
                //System.out.println(a);
                lista.add(a);
            }
            myReader.close();
            System.out.println("Sikeres eredeti koordinatak beolvasasa!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int k = 0;
        for (int i = 0; i < lista.size(); i++) {
            //System.out.println(lista.get(i));
            ++k;
        }
        System.out.println("Eredeti koordinatak szama: " + k + ".");

        for (int i = 0; i < lista.size(); i += 3) {
            //System.out.println(lista.get(i));
            lon.add(lista.get(i));
        }

        for (int i = 1; i < lista.size(); i += 3) {
            //System.out.println(lista.get(i));
            lat.add(lista.get(i));
        }
    }

    public static void mertkoordinatak(String t) {
        try {
            File myObj = new File(t);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                String d = myReader.next();
                double a = Double.parseDouble(d);
                //System.out.println(a);
                lista2.add(a);
            }
            myReader.close();
            System.out.println("Sikeres mert koordinatak beolvasasa!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int k1 = 0;
        for (int i = 0; i < lista2.size(); i++) {
            //System.out.println(lista2.get(i));
            ++k1;
        }
        System.out.println("Mert koordinatak szama: " + k1 + ".");

        for (int i = 0; i < lista2.size(); i += 3) {
            //System.out.println(lista2.get(i));
            lon2.add(lista2.get(i));
        }

        for (int i = 1; i < lista2.size(); i += 3) {
            //System.out.println(lista2.get(i));
            lat2.add(lista2.get(i));
        }
    }

    public static void vesszok(String t) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File myObj = new File(t);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.replace(',', ' ');
                //System.out.println(data);
                list.add(data);
            }
            myReader.close();
            System.out.println("Sikeres file atalakitas!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(t);
            for (int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i));
                myWriter.write(list.get(i));
                myWriter.append("\n");
            }
            myWriter.close();
            System.out.println("Sikeres file kiiras!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static double tavolsagHaversine(double lat1, double lat2, double lon1, double lon2) {
        double dLon = Math.toRadians(lon2 - lon1);
        double dLat = Math.toRadians(lat2 - lat1);

        double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)) + (Math.cos(Math.toRadians(lat1))) * (Math.cos(Math.toRadians(lat2))) * (Math.sin(dLon / 2)) * (Math.sin(dLon / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = c * R;
        return d;
    }
    
    public static double tavolsagKoszinusz (double lat1, double lat2, double lon1, double lon2){
        double Lat1 = Math.toRadians(lat1);
        double Lat2 = Math.toRadians(lat2);
        double dLon = Math.toRadians(lon2 - lon1);
        
        double a = Math.acos((Math.sin(Lat1) * Math.sin(Lat2)) + Math.cos(Lat1) * Math.cos(Lat2) * Math.cos(dLon));
        double d = a * R;
        return d;
    }
}
