package Modele.rmi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Modele.Canvas;

public class CanvasRMIServerImpl extends UnicastRemoteObject  implements CanvasRMIServer {
	private Canvas canvas;

	public CanvasRMIServerImpl(String[] args, String name) throws RemoteException {
		super();
		
		canvas = new Canvas();
		
		//Set this instance in the RMI registry
		Registry registry;
		if (args != null) registry = LocateRegistry.getRegistry(args[0]);
		else registry = LocateRegistry.getRegistry();
		registry.rebind(name, this);
		System.out.println("Le canvas est enregistre");
	}
	public void setDrawing(BufferedImage image)throws RemoteException {
		this.canvas.setDrawing(image);
	}
	public BufferedImage getDrawing() throws RemoteException {
		return this.canvas.getDrawing();
	}
    public void save(String name) throws RemoteException, IOException{
        this.canvas.save(name);
    }
}
