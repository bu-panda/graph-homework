package pa2.parser;

import org.w3c.dom.Element;

import com.jogamp.opengl.util.gl2.GLUT;

import pa2.Circular;
import pa2.Displayable;
import pa2.Palm;
import pa2.RoundedCylinder;

public class CylinderFactory {
	
	public static Displayable createCylinder(Element e, GLUT glut){
		
		String type = e.getAttribute("type");
		
		if("palm".equals(type)){
			return new Palm(e, glut);
		}
		else if("round".equals(type)){
			return new RoundedCylinder(e, glut);
		}
		return null;
	}

}
