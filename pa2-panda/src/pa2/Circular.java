package pa2;
/**
 * Circular.java - a circular object
 */


import org.w3c.dom.Element;

import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl

import pa2.parser.ComponentNode;

/**
 * A circular object.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Circular {
  /** The OpenGL utility toolkit object to use to draw this object. */
  protected final GLUT glut;
  /** The radius of this object. */
  protected final double radius;
  


  public Circular(Element info, final GLUT glut){
	  this.radius =  Double.valueOf(info.getAttribute("radius"));
	  this.glut = glut;
	  
  }
  
  /**
   * Gets the OpenGL utility toolkit object.
   * 
   * @return The OpenGL utility toolkit object.
   */
  protected GLUT glut() {
    return this.glut;
  }

  /**
   * Gets the radius of this object.
   * 
   * @return The radius of this object.
   */
  protected double radius() {
    return this.radius;
  }
}
