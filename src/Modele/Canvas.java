package Modele;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import View.PCanva;
public class Canvas implements Serializable {
	public String name;
	public ImageIcon drawing;
	public PCanva pcanva;
	
	public Canvas(String name) {
		this.name = name;
		//drawing = new ImageIcon();
		/*try {
			//drawing.setImage(ImageIO.read(new File("drawings/az.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		/*
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			ServerGroup serverGroup = (ServerGroup) registry.lookup(this.name);
			serverGroup.addMember(this);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}*/
	}
	public void setDrawing(ImageIcon image) {
		this.drawing = image;
	}
	public ImageIcon getDrawing() {
		return this.drawing;
	}
	public void setPCanvas(PCanva pcanvas) {
		System.out.println("dans setPCanvas" + pcanvas);
		this.pcanva = pcanvas;
		
	}
	public String getName() {
		return name;
	}
    public void save(String name) throws IOException{
        ImageIO.write(Converter.toBufferedImage(drawing), "PNG", new File("drawings/" + name +".png"));
    }
    public void drawPath(ArrayList<Point> path) throws RemoteException {
    	System.out.println(pcanva);
		
        // repaint panel with the modified painting
        pcanva.drawPath(path);
    }
}
