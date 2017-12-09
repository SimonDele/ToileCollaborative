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
import Main.Main;
import Modele.Canvas;
import Modele.Converter;
import Modele.Toolbox;
import server.ServerGroup;

public class PCanva extends JPanel {
	
	private BufferedImage drawing;	
	private Boolean toDrawPath;
	private ListenersPCanva listenersPCanva;
	private Toolbox toolbox;
	public Canvas canvas;
	public Converter converter;
	
	public PCanva(Toolbox toolbox, Canvas canvas) {
		toDrawPath = false;
		//drawing = Converter.toBufferedImage(MainFrame.canvas.getDrawing());
		//canvas.setDrawing(drawing);
		this.toolbox = toolbox;
		this.canvas = Main.USER.getCurrentCanvas();
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
		System.out.println(path.size());
		if(Main.USER.getCurrentCanvas().getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = Converter.toBufferedImage(Main.USER.getCurrentCanvas().getDrawing());
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
        Main.USER.getCurrentCanvas().setDrawing(Converter.toIcon(drawing));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Main.USER.getCurrentCanvas().getDrawing() == null) {
			System.out.println("bug");
		}else {
			System.out.println("pas bug");
			g.drawImage(Converter.toBufferedImage(Main.USER.getCurrentCanvas().getDrawing()), 0, 0, null);  
		}
		
	}

    // draw painting
    public void updatePaint(){
    	if(Main.USER.getCurrentCanvas().getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = Converter.toBufferedImage(Main.USER.getCurrentCanvas().getDrawing());
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
        Main.USER.getCurrentCanvas().setDrawing(Converter.toIcon(drawing));
    }
    public void switchCanvas() {
    	this.repaint();
    	this.listenersPCanva.changeServer(Main.USER.getCurrentCanvas().getName());
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
