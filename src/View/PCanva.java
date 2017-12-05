package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Modele.Canvas;
import Modele.Converter;
import Modele.Toolbox;
import server.ServerGroup;

public class PCanva extends JPanel {
	
	private transient BufferedImage drawing;	
	private transient Boolean toDrawPath;
	private transient ListenersPCanva listenersPCanva;
	private transient Toolbox toolbox;
	public transient Canvas canvas;
	public Converter converter;
	
	public PCanva(Toolbox toolbox, Canvas canvas) {
		converter = new Converter();
		toDrawPath = false;
		//drawing = converter.toBufferedImage(MainFrame.canvas.getDrawing());
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
    		drawing = converter.toBufferedImage(MainFrame.canvas.getDrawing());
    	}

        Graphics g = drawing.createGraphics();

       
		for(Point p : path) { //We iterate over our list of point in the path ArrayList
			 g.setColor(Color.red); //A changer 
			 g.fillRect((int)p.getX(),(int) p.getY(), toolbox.getSize(), toolbox.getSize());
		}
		

        g.dispose();
        // repaint panel with the modified painting
        repaint();
        //Save in Canvas
        MainFrame.canvas.setDrawing(converter.toByte(drawing));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(converter.toBufferedImage(MainFrame.canvas.getDrawing()), 0, 0, null);  
	}

    // draw painting
    public void updatePaint(){
    	if(MainFrame.canvas.getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = converter.toBufferedImage(MainFrame.canvas.getDrawing());
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
        MainFrame.canvas.setDrawing(converter.toByte(drawing));
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
