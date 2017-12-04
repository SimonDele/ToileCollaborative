package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Modele.Canvas;
import Modele.Toolbox;

public class PCanva extends JPanel {
	
	private transient BufferedImage drawing;	
	private Boolean toDrawPath;
	private ListenersPCanva listenersPCanva;
	private Toolbox toolbox;
	public Canvas canvas;
	public PCanva(Toolbox toolbox, Canvas canvas) {
		toDrawPath = false;
		drawing = MainFrame.canvas.getDrawing();
		//canvas.setDrawing(drawing);
		this.toolbox = toolbox;
		this.canvas = MainFrame.canvas;
		//Listeners
		listenersPCanva = new ListenersPCanva(this);
		this.addMouseMotionListener(listenersPCanva);
		this.addMouseListener(listenersPCanva);
	}
	
	public void drawPath() {
		toDrawPath = true;
		this.updatePaint();
	}
	public void drawPath(ArrayList<Point> path) {
		if(MainFrame.canvas.getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = MainFrame.canvas.getDrawing();
    	}

        Graphics g = drawing.createGraphics();

       
		for(Point p : path) { //We iterate over our list of point in the path ArrayList
			 g.setColor(Color.red); //A changer 
			 g.fillRect((int)p.getX(),(int) p.getY(), toolbox.getSize(), toolbox.getSize());
		}
		

        g.dispose();
        // repaint panel with new modified paint
        repaint();
        //Save in Canvas
        MainFrame.canvas.setDrawing(drawing);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(MainFrame.canvas.getDrawing(), 0, 0, null);  
	}

    // draw painting
    public void updatePaint(){
    	if(MainFrame.canvas.getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = MainFrame.canvas.getDrawing();
    	}

        Graphics g = drawing.createGraphics();

        if(toDrawPath) {
			 for(Point p : listenersPCanva.getPath()) { //We iterate over our list of point in the path ArrayList
				 g.setColor(Color.red); //A changer 
				 g.fillRect((int)p.getX(),(int) p.getY(), toolbox.getSize(), toolbox.getSize());
			 }
		 }

        g.dispose();
        // repaint panel with new modified paint
        repaint();
        //Save in Canvas
        MainFrame.canvas.setDrawing(drawing);
    }
    public void switchCanvas() {
    	this.repaint();
    	this.listenersPCanva.changeServer(MainFrame.canvas.getName());
    }
/*
    public void save() throws IOException{
        ImageIO.write(drawing, "PNG", new File("filename.png"));
    }
   */
/*
    public void load() throws IOException {
        drawing = ImageIO.read(new File("filename.png"));
        // update panel with new paint image
        repaint();
    }
   */	
}
