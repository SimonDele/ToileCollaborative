package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import Controller.ListenersPCanva;
import Modele.Toolbox;
import Modele.rmi.CanvasRMIServerImpl;

public class PCanva extends JPanel {
	
	private BufferedImage drawing;	
	private Boolean toDrawPath;
	private ListenersPCanva listenersPCanva;
	private Toolbox toolbox;
	private CanvasRMIServerImpl canvasServer;
	
	public PCanva(Toolbox toolbox, CanvasRMIServerImpl canvasServer) {
		toDrawPath = false;
		try {
			drawing = MainFrame.canvasServer.getDrawing();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//canvasServer.setDrawing(drawing);
		this.toolbox = toolbox;
		this.canvasServer = MainFrame.canvasServer;
		//Listeners
		listenersPCanva = new ListenersPCanva(this);
		this.addMouseMotionListener(listenersPCanva);
		this.addMouseListener(listenersPCanva);
	}
	
	public void drawPath() throws RemoteException {
		toDrawPath = true;
		this.updatePaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(MainFrame.canvasServer.getDrawing(), 0, 0, null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

    // draw painting
    public void updatePaint() throws RemoteException{
    	if(MainFrame.canvasServer.getDrawing() == null) {
        	drawing = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);  		
    	}else {
    		drawing = MainFrame.canvasServer.getDrawing();
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
        MainFrame.canvasServer.setDrawing(drawing);
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
