package Modele;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import View.MainFrame;
import server.ServerGroup;

public class Canvas implements Serializable{
	//private transient BufferedImage drawing;
	public String name;
	public ImageIcon drawing;
	
	public Canvas(String name) {
		this.name = name;
		//drawing = new ImageIcon();
		/*try {
			//drawing.setImage(ImageIO.read(new File("drawings/az.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			ServerGroup serverGroup = (ServerGroup) registry.lookup(this.name);
			serverGroup.addMember(this);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	public void setDrawing(ImageIcon image) {
		this.drawing = image;
	}
	/*public BufferedImage getDrawing() {
		return drawing;
	}*/
	public ImageIcon getDrawing() {
		return this.drawing;
	}
	public String getName() {
		return name;
	}
    public void save(String name) throws IOException{
        //ImageIO.write(drawing, "PNG", new File("drawings/" + name +".png"));
    }
    public void drawPath(ArrayList<Point> path) {
    	MainFrame.pCanva.drawPath(path);
    }
}
