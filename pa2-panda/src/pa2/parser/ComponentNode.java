package pa2.parser;


import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jogamp.opengl.util.gl2.GLUT;

import pa2.Axis;
import pa2.Circular;
import pa2.Component;
import pa2.Displayable;
import pa2.Palm;
import pa2.Point3D;
import pa2.RoundedCylinder;

public class ComponentNode {
	
	public Component comp;
	protected String label;
	protected Point3D pos;
	protected String color;
	protected ArrayList<ComponentNode> children;
	
	protected String activeColor = "ff0000";
	
	  protected double x_range_min, x_range_max;
	  protected double y_range_min, y_range_max;
	  protected double z_range_min, z_range_max;
	  protected double x_init, y_init, z_init;
	
	public ComponentNode(){
		comp = null;
		children = new ArrayList<>();
	}
	
	public static synchronized ComponentNode buildComponent(Element node, HashMap<String, ComponentNode> map, GLUT glut){
		
		final ComponentNode root = new ComponentNode();
		
		root.pos = root.readPosition(node.getAttribute("position"));
		root.label = node.getAttribute("label");
		root.color = node.getAttribute("color");
		System.out.println(root.label);
				
		Displayable shape = CylinderFactory.createCylinder(node, glut);
		
		root.comp = new Component(root.pos, shape, root.label);
		root.comp.setColor(root.color);
		root.loadPositionInfo(node);
		
		
		NodeList nodeList = node.getChildNodes();
		int length = nodeList.getLength();
		for(int i = 0; i < length; i++){
			Node nNode = nodeList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element e = (Element) nNode;
				root.addChild(buildComponent(e, map, glut));
			}
		}
		//map.put(root.label, root);
		return root;
	}


	private void loadPositionInfo(Element node) {
		
		String direct_string = node.getAttribute("direct");
		String xrange = node.getAttribute("x-rotate-range");
		String yrange = node.getAttribute("y-rotate-range");
		String zrange = node.getAttribute("z-rotate-range");
		
		String[] directs = direct_string.split(",");
		x_init = Double.valueOf(directs[0]);
		y_init = Double.valueOf(directs[1]);
		z_init = Double.valueOf(directs[2]);
		
		String[] xrange_str = xrange.split(",");
		x_range_min = Double.valueOf(xrange_str[0]);
		x_range_max = Double.valueOf(xrange_str[1]);
		
		String[] yrange_str = yrange.split(",");
		y_range_min = Double.valueOf(yrange_str[0]);
		y_range_max = Double.valueOf(yrange_str[1]);
		
		String[] zrange_str = zrange.split(",");
		z_range_min = Double.valueOf(zrange_str[0]);
		z_range_max = Double.valueOf(zrange_str[1]);
		
		this.comp.rotate(Axis.X, x_init);
		this.comp.rotate(Axis.Y, y_init);
		this.comp.rotate(Axis.Z, z_init);
		
		this.comp.setXNegativeExtent(x_range_min);
		this.comp.setXPositiveExtent(x_range_max);
		
		this.comp.setYNegativeExtent(y_range_min);
		this.comp.setYPositiveExtent(y_range_max);
		
		this.comp.setZNegativeExtent(z_range_min);
		this.comp.setZPositiveExtent(z_range_max);
		
	}

	public void addChild(ComponentNode node){
		this.comp.addChild(node.comp);
		this.children.add(node);
	}

	private Point3D readPosition(String attribute) {
		// TODO Auto-generated method stub
		
		String[] attrs = attribute.split(",");
		double x = Double.valueOf(attrs[0]);
		double y = Double.valueOf(attrs[1]);
		double z = Double.valueOf(attrs[2]);
		
		return new Point3D(x, y, z);
	}

	public void setActive(boolean b) {
		// TODO Auto-generated method stub
		if(b){
			comp.setColor(activeColor);
		}
		else{
			comp.setColor(color);
		}
	}

}
