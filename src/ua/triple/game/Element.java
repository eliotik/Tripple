package ua.triple.game;

import java.io.*;
import java.util.HashMap;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import static java.lang.String.format;

class Element {
    private static final String xmlFilePath = "/someDir";

    private boolean joinable;
    private String type;
    private String background;
    private DataInputStream out;

    public Element(String type, Boolean joinable, String background){
        this.type = type;
        this.joinable = joinable;
        this.background = background;
    }

    private void getStreamFromFile(String fileName) {
        try {
            String file = format("%s%s", xmlFilePath, fileName);
            this.out = new DataInputStream( new BufferedInputStream( new FileInputStream( file ) ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private NodeList xmlRead(){
        NodeList listOfElements = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse ( this.out );
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

    public HashMap<String, Element> elementsGenerator(){
        getStreamFromFile("filename.xml");
        NodeList listOfElements = xmlRead();
        HashMap<String, Element> elementMap = new HashMap<String, Element>();
        joinable = false;
        for( int j=0; j < listOfElements.getLength(); j++ ) {
            Node firstNode=listOfElements.item(j);
            if(firstNode.getNodeType()==Node.ELEMENT_NODE)
            {
                org.w3c.dom.Element elemj = (org.w3c.dom.Element) firstNode;
                if (elemj.getElementsByTagName("joinable").equals("1")) {
                    joinable = true;
                }
                else{
                    joinable = false;
                }
                elementMap.put(elemj.getElementsByTagName("type").toString(), new Element(elemj.getElementsByTagName("type").toString(), joinable, elemj.getElementsByTagName("background").toString() ));
            }
        }
        return elementMap;
    }
}