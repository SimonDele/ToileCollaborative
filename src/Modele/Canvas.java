package Modele;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.ServerGroup;

public class Canvas{
	//private transient BufferedImage drawing;
	public String name;
	public byte[] drawing;
	
	public Canvas(String name) {
		this.name = name;
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			ServerGroup serverGroup = (ServerGroup) registry.lookup(this.name);
			serverGroup.addMember(this);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	public void setDrawing(byte[] image) {
		this.drawing = image;
	}
	/*public BufferedImage getDrawing() {
		return drawing;
	}*/
	public byte[] getDrawing() {
		return this.drawing;
	}
	public String getName() {
		return name;
	}
    public void save(String name) throws IOException{
        //ImageIO.write(drawing, "PNG", new File("drawings/" + name +".png"));
    }
}
