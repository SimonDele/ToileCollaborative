package Modele.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Modele.Canvas;

public class CanvasRMIServerImpl extends UnicastRemoteObject  implements CanvasRMIServer {
	//private Canvas canvas;
	private byte[] drawingInByte;
	private ArrayList<Canvas> memberConnected;
	
	public CanvasRMIServerImpl(String[] args, String name) throws RemoteException {
		super();
		this.memberConnected = new ArrayList<Canvas>();
		//canvas = new Canvas();
		
		//Set this instance in the RMI registry
		Registry registry;
		System.setProperty("java.rmi.server.hostname","192.168.1.5");
		if (args != null) registry = LocateRegistry.getRegistry(args[0]);
		else registry = LocateRegistry.getRegistry();
		registry.rebind(name, this);
		System.out.println("Le canvas" + name +" est enregistre");
	}
	public void addUser(Canvas canvas) throws RemoteException{
		this.memberConnected.add(canvas);
		System.out.println(this.memberConnected.size());
	}
	public void setDrawing(byte[] image)throws RemoteException {
		for(int i=0;i<this.memberConnected.size();i++) {
			this.memberConnected.get(i).modif();
		}
		this.drawingInByte = image;
	}
	public byte[] getDrawingInBytes() throws RemoteException {
		return this.drawingInByte;
	}
    public void save(String name) throws RemoteException, IOException{
       // this.canvas.save(name);
    }
}
