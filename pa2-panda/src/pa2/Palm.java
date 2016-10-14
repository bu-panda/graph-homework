package pa2;



import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import org.w3c.dom.Element;

import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import pa2.parser.ComponentNode;

/**
 * A model for the palm of a hand as a sphere scaled in one direction.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Palm extends Circular implements Displayable {

  /**
   * The OpenGL handle to the display list which contains all the components
   * which comprise this cylinder.
   */
  private int callListHandle;
  private String texturePath;
  private float X, Y, Z;
  private Texture skin;
  private GLU glu = new GLU();
  private boolean texture_enable = false;

  public Palm(Element info, GLUT glut) {
	    super(info, glut);
	    X = Float.valueOf(info.getAttribute("x-scale"));
	    Y= Float.valueOf(info.getAttribute("y-scale"));
	    Z = Float.valueOf(info.getAttribute("z-scale"));
	    texture_enable = Boolean.valueOf(info.getAttribute("texture-enable"));
	    
	    if(texture_enable){
	    	texturePath = info.getAttribute("texture");
	    }
	    else{
	    	texturePath = "";
	    }
	    
	    
	  }
  
  /**
   * {@inheritDoc}
   * 
   * @param gl
   *          {@inheritDoc}
   * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
   */
  @Override
  public void draw(GL2 gl) {
    gl.glCallList(this.callListHandle);
  }

  /**
   * Defines the OpenGL call list which draws a scaled sphere.
   * 
   * @param gl
   *          {@inheritDoc}
   * 
   * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
   */
  @Override
  public void initialize(final GL2 gl) {
    this.callListHandle = gl.glGenLists(1);

    // create an ellipsoid for the palm by scaling a sphere
    gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
    gl.glPushMatrix();
    // position this so that the sphere is drawn above the x-y plane, not at
    // the origin
    gl.glTranslated(0, 0, this.radius());
    gl.glScalef(X, Y, Z);
    
    if(texture_enable) {
    	try {
    		skin = TextureIO.newTexture(new File(texturePath), true);
    	} catch (IOException e) {
    		javax.swing.JOptionPane.showMessageDialog(null, e);
    	}

    	GLUquadric ecllipse = glu.gluNewQuadric();
    	glu.gluQuadricDrawStyle(ecllipse, glu.GLU_FILL);
    	glu.gluQuadricTexture(ecllipse, true);
    	glu.gluQuadricNormals(ecllipse, glu.GLU_SMOOTH);
    	gl.glEnable(gl.GL_TEXTURE_2D);
		gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE);
		skin.enable(gl);
		skin.bind(gl);
		glu.gluSphere(ecllipse, this.radius, 36, 28);
		skin.disable(gl);
		gl.glDisable(gl.GL_TEXTURE_2D);
    }
    else
    	this.glut().glutSolidSphere(this.radius(), 36, 18);
	
    gl.glPopMatrix();
    gl.glEndList();
  }

}
