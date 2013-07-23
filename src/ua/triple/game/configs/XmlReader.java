package ua.triple.game.configs;

import static java.lang.String.format;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader {

	private static DataInputStream out;	
	
    public static void getStreamFromFile(String fileName) {
        try {
            String file = format("%s%s", Config.xmlFilePath, fileName);
            out = new DataInputStream( new BufferedInputStream( new FileInputStream( file ) ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static NodeList read(){
        NodeList listOfElements = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( out );
            doc.getDocumentElement ().normalize ();

            listOfElements = doc.getElementsByTagName("element");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfElements;
    }	
	
}
