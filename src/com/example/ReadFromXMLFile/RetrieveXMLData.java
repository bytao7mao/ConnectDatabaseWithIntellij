package com.example.ReadFromXMLFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RetrieveXMLData {



    public static ArrayList<String> getXMLdata() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<String> xmlDataArrayList = new ArrayList<>();
        //creating a constructor of file class and parsing an XML file
        File file = new File("C:\\Users\\tao\\IdeaProjects\\untitled2\\.idea\\Resources\\data.xml");

        String xml = ""; //Populated XML String....
        //an instance of factory that gives a document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
//        System.out.println("Root element: " + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("student");



        // nodeList is not iterable, so we are using for loop
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
//            System.out.println("\nNode name:" + node.getNodeName());
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                //put id in ArrayList
                xmlDataArrayList.add(eElement.getElementsByTagName("id").item(0).getTextContent());
//                System.out.println("Student id: "+ eElement.getElementsByTagName("id").item(0).getTextContent());
                //put firstName in Arraylist
                xmlDataArrayList.add(eElement.getElementsByTagName("firstname").item(0).getTextContent());
//                System.out.println("First Name: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
                //put lastName in ArrayList
                xmlDataArrayList.add(eElement.getElementsByTagName("lastname").item(0).getTextContent());
//                System.out.println("Last Name: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
                //put subject in ArrayList
                xmlDataArrayList.add(eElement.getElementsByTagName("subject").item(0).getTextContent());
//                System.out.println("Subject: "+ eElement.getElementsByTagName("subject").item(0).getTextContent());
                //put marks in ArrayList
                xmlDataArrayList.add(eElement.getElementsByTagName("marks").item(0).getTextContent());
//                System.out.println("Marks: "+ eElement.getElementsByTagName("marks").item(0).getTextContent());
            }
        }
        return xmlDataArrayList;
    }



}
