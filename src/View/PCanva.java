package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Modele.Toolbox;

public class PCanva extends JPanel {
	
	private Boolean toDrawPath;
	private ListenersPCanva listenersPCanva;
	private Toolbox toolbox;
	
	public PCanva(Toolbox toolbox) {
		toDrawPath = false;
		
		this.toolbox = toolbox;
		
		//Listeners
		listenersPCanva = new ListenersPCanva(this);
		this.addMouseMotionListener(listenersPCanva);
		this.addMouseListener(listenersPCanva);
	
	}
	
	public void drawPath() {
		toDrawPath = true;
		this.repaint();

	}
	
	public void paintComponent(Graphics g) {
		 if(toDrawPath) {
			 for(Point p : listenersPCanva.getPath()) { //We iterate over our list of point in the path ArrayList
				 g.setColor(Color.red); //A changer
				 
				 g.fillRect((int)p.getX(),(int) p.getY(), toolbox.getSize(), toolbox.getSize());
			 }
		 }
	}
}
