package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Modele.Canvas;
import Modele.Toolbox;

public class PCanva extends JPanel {
	
	private BufferedImage drawing;	
	private Boolean toDrawPath;
	private ListenersPCanva listenersPCanva;
	private Toolbox toolbox;
	private Canvas canvas;
	
	public PCanva(Toolbox toolbox, Canvas canvas) {
		toDrawPath = false;
		drawing = canvas.getDrawing();
		//canvas.setDrawing(drawing);
		this.toolbox = toolbox;
		this.canvas = canvas;
		//Listeners
		listenersPCanva = new ListenersPCanva(this);
		this.addMouseMotionListener(listenersPCanva);
		this.addMouseListener(listenersPCanva);
	
	}
	
	public void drawPath() {
		toDrawPath = true;
		this.updatePaint();

	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(drawing, 0, 0, null);
       
	}

    // draw painting
    public void updatePaint(){
    	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
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
        canvas.setDrawing(drawing);
    }

    public void save() throws IOException{
        ImageIO.write(drawing, "PNG", new File("filename.png"));
    }

    public void load() throws IOException {
        drawing = ImageIO.read(new File("filename.png"));
        // update panel with new paint image
        repaint();
    }	
}
