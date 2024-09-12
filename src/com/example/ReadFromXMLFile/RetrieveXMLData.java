package com.example.ReadFromXMLFile;

import com.example.DataAccessObject.Dao;
import com.example.DataAccessObject.Student;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RetrieveXMLData {


    public static final String PATHNAME = "C:\\Users\\tao\\IdeaProjects\\untitled2\\.idea\\Resources\\data.xml";

    public static ArrayList<Student> getXMLdata(File file) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Student> students = new ArrayList<>();
        //creating a constructor of file class and parsing an XML file
//        File file = new File(PATHNAME);

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
            System.out.println("\nNode name:" + node.getNodeName());
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                System.out.println();

                students.add(new Student(
                        Integer.valueOf(eElement.getElementsByTagName("id").item(0).getTextContent()),
                        eElement.getElementsByTagName("firstname").item(0).getTextContent(),
                        eElement.getElementsByTagName("lastname").item(0).getTextContent(),
                        eElement.getElementsByTagName("subject").item(0).getTextContent(),
                        eElement.getElementsByTagName("marks").item(0).getTextContent()
                        ));

            }
        }
        return students;
    }



}
