package com.triple.game.configs;

import static java.lang.String.format;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.triple.game.elements.ElementsFactory;
import com.triple.sprites.Tiles;

public class XmlReader {

	private static DataInputStream out;
	private static final Logger log = Logger.getLogger(XmlReader.class);
	
    public static void getStreamFromFile(String fileName) {
        try {
            String file = format("%s%s", Config.xmlFilePath, fileName);
            //out = new DataInputStream( new BufferedInputStream( new FileInputStream( file ) ) );
            out = new DataInputStream( new BufferedInputStream( Tiles.class.getResourceAsStream(file) ) );
        } catch (Exception e) {
        	if (log.isDebugEnabled()){
				log.debug("Could not initialize elements configuration!");
			}
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
        	if (log.isDebugEnabled()){
				log.debug("Could not parse elements configuration!");
			}
        } catch (SAXException e) {
        	if (log.isDebugEnabled()){
				log.debug("SAXException while parsing elements configuration!");
			}
        } catch (IOException e) {
        	if (log.isDebugEnabled()){
				log.debug("IOException while parsing elements configuration!");
			}
        }

        return listOfElements;
    }	
	
}
