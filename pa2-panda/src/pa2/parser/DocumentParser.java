package pa2.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.jogamp.opengl.util.gl2.GLUT;

public class DocumentParser {
	
	
	private DocumentBuilder builder;
	private Document doc;
	
	public DocumentParser(String filename) throws ParserConfigurationException, SAXException, IOException{

		File xmlfile = new File(filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();
		this.doc = builder.parse(xmlfile);
	}
	
	public ComponentNode buildTree(HashMap<String , ComponentNode> map, final GLUT glut){
		
		Element e = doc.getDocumentElement();
		return ComponentNode.buildComponent(e, map, glut);
		
		
	}
}
