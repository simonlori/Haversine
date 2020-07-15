/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haversine;

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
        double lat01 = 46.186666670721024;
        double lon01 = 24.586666646879166;
        double lat02 = 46.186388892820105;
        double lon02 = 24.586388868978247;
        double a = getDistance(lat01, lat02, lon01, lon02);
        System.out.println("kilometer:");
        System.out.println(a);
        double b = a * 1000;
        System.out.println("meter:");
        System.out.println(b);

        double[] tomb1 = new double[10];
        for (int i = 0; i < tomb1.length; i++) {
            tomb1[i] = 1;
        }

        /*double eredmeny,perc,mperc;
         perc = (float) 35/60;
         System.out.println(perc);
         mperc = (float) 49.28/3600;
         System.out.println(mperc);
         eredmeny = 24 + perc + mperc;
         System.out.println(eredmeny);*/
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
