/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haversine;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lori
 */
public class koordlista implements Serializable {

    public List<koord> koordinatak;

    public koordlista() {
        koordinatak = new ArrayList<koord>();
    }
    
    public void konyvListaBetoltesXML(){
        try{
            File fXmlFile = new File("gps.kml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

	
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("coordinates");
            //Node = Konyvtol konyvig
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                //System.out.println(nNode.getChildNodes());
                
                koord k = new koord();
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    //System.out.println("Konyv id : " + eElement.getAttribute("id"));
                    System.out.println("Cim : " + eElement.getElementsByTagName("Cim").item(0).getTextContent());
                    System.out.println("Szerzo : " + eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
                    System.out.println("Kiado : " + eElement.getElementsByTagName("Kiado").item(0).getTextContent());
                    System.out.println("ISBN : " + eElement.getElementsByTagName("ISBN").item(0).getTextContent());
                    System.out.println("KiadasEve : " + eElement.getElementsByTagName("KiadasEve").item(0).getTextContent());
                    System.out.println("Kolcsonozhetoe : " + eElement.getElementsByTagName("Kolcsonozhetoe").item(0).getTextContent());
                    
                   /* k.setID(eElement.getAttribute("id"));
                    k.setCim(eElement.getElementsByTagName("Cim").item(0).getTextContent());
                    k.setSzerzo(eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
                    k.setKiado(eElement.getElementsByTagName("Kiado").item(0).getTextContent());
                    k.setISBN(eElement.getElementsByTagName("ISBN").item(0).getTextContent());
                    k.setKiadasEve(eElement.getElementsByTagName("KiadasEve").item(0).getTextContent());
                    k.setKolcsonozhetoe(eElement.getElementsByTagName("Kolcsonozhetoe").item(0).getTextContent());
                    konyvek.add(k);*/
                }
            }
           // System.out.println("Betoltes SIKERES");
            
            
            

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
