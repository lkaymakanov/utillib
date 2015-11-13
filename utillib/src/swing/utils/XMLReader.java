package swing.utils;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.is_bg.ltf.db.common.ConnectionProperties;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
	
		private String filename = "";
		private String [] table  = {"driver", "url",  "user", "pass",  "name"};
		private String conEl = "connection";   //name of connection elements
		
		//list with connections
		private List<ConnectionProperties> connections  = new ArrayList<ConnectionProperties>();
	    
	    
	    //constructor with  filename
	    public XMLReader(String filename) {
			// TODO Auto-generated constructor stub
	    	this.filename = filename;
	    }
	    
	    
	    //parse XML file content
	    public void parseContent(){
	    	
	    	//clear list
	    	connections.clear();
	    	
	    	//read connections
	    	try{
		    	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		   	    DocumentBuilder db = dbf.newDocumentBuilder();
		    	Document doc= db.parse(filename);       //open the file
		    	NodeList nl = doc.getElementsByTagName(conEl);  //

		    	List<String> coonectionsprop = new ArrayList<String>();
		 		for(int i=0; i < nl.getLength(); i++)
		 		{
		 			
		 			coonectionsprop.clear();
		 			for(int j=0; j < table.length; j++)
		 			{
		 			   
		 			   String val = doc.getElementsByTagName(table[j]).item(i).getChildNodes().item(0).getNodeValue();
		 			   coonectionsprop.add(val);
		 			   System.out.println(table[j] + " = " + val);
		 			}
		 			connections.add(new ConnectionProperties(coonectionsprop));
		 			System.out.println();
		 		}
	    	}catch (Exception e) {
				// TODO: handle exception
	    		System.out.println("Exception occured in parseContent method...");
			}
		 		
		 		
	    }
	    
	    
	    
	    public static void parse(String xmlString, String rootNodeName) throws ParserConfigurationException, SAXException, IOException{
	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        InputSource is = new InputSource(new StringReader(xmlString));
	        Document doc = builder.parse(is);
	        
	        NodeList nl = doc.getElementsByTagName(rootNodeName);
	        
	        for(int i=0; i < nl.getLength(); i++){
	        	processNode(nl.item(i));
	        }
	    }
	    
	    private static void processNode(Node currentNode){
	    	if(currentNode == null) return;
	    	NodeList nl = currentNode.getChildNodes();
	    	for(int i=0; i < nl.getLength(); i++){
		        processNode(nl.item(i));
		    }
	    }
	    


		public List<ConnectionProperties> getConnections() {
			return connections;
		}
	    
}















/*NodeList nameNlc=    doc.getElementsByTagName("diver");
 Element nameElements=(Element)nameNlc.item(i);
 String nameTagValue=nameElements.getChildNodes().item(0).getNodeValue();
 
 
 NodeList authorNlc=    doc.getElementsByTagName("url");
 Element authorElements=(Element)authorNlc.item(i);
 String authorTagValue=authorElements.getChildNodes().item(0).getNodeValue();
 
 NodeList dateNlc=    doc.getElementsByTagName("publication-date");
 Element dateElements=(Element)dateNlc.item(i);
 String dateTagValue=dateElements.getChildNodes().item(0).getNodeValue();
 
 System.out.println("name :"+nameTagValue);    
 System.out.println("author :"+authorTagValue);    
 System.out.println("publication-date :"+dateTagValue);    */
