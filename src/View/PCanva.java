package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Main.Main;
import Modele.Canvas;
import Modele.Converter;
import Modele.Member;

/**
 * Panel that holds the Canvas of the Member's currentGroup. The client draws on this.
 */
@SuppressWarnings("serial")
public class PCanva extends JPanel {
	/**
	 * The current drawing to display
	 */
	private BufferedImage drawing;
	
	/**
	 * Initializes the Listener for the Canvas, as well as the size of the Panel.
	 * @param toolbox 
	 */
	public PCanva() {
		//Listeners
		ListenersPCanva listenersPCanva = new ListenersPCanva();
		this.addMouseMotionListener(listenersPCanva);
		this.addMouseListener(listenersPCanva);
		this.setPreferredSize(new Dimension(Canvas.width,Canvas.height));
	}
	
	/**
	 * Method to draw a path of pixels on the Canvas, as ordered by the Member "drawer".
	 * @param drawer the Member that has drawn this path
	 * @param path the arraylist of pixels the drawer has selected
	 */
	public void drawPath(Member drawer, ArrayList<Point> path) {
		if(Main.USER.getCurrentGroup().getCanvas().getDrawing() == null) {
        	drawing = new BufferedImage(Canvas.width, Canvas.height, BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = Converter.toBufferedImage(Main.USER.getCurrentGroup().getCanvas().getDrawing());
    	}

        Graphics g = drawing.createGraphics();
        System.out.println(drawer.getColor());
		g.setColor(drawer.getColor()); 
		for(Point p : path) { //We iterate over our list of point in the path ArrayList
			 g.fillRect((int)p.getX(),(int) p.getY(), drawer.getToolbox().getSize(), drawer.getToolbox().getSize());
		}
		

        g.dispose();
        // repaint panel with the modified painting
        repaint();
        //Save in Canvas
        Main.USER.getCurrentGroup().getCanvas().setDrawing(Converter.toIcon(drawing));
	}
	
	/**
	 * Method called within repaint(). Draws the newest Canvas atop the old one.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Main.USER.getCurrentGroup().getCanvas().getDrawing() == null) {
			g.drawImage(new BufferedImage(Canvas.width,Canvas.height,BufferedImage.TYPE_INT_RGB), 0, 0, null);
		}else {
			g.drawImage(Converter.toBufferedImage(Main.USER.getCurrentGroup().getCanvas().getDrawing()), 0, 0, null);  
		}
	}
	/**
	 * When the canvas was changed, this method makes sure the latest one is displayed.
	 */
    public void switchCanvas() {
    	this.repaint();
    }
	
}
