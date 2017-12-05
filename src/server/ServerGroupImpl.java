package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Modele.Canvas;
import Modele.Group;
import View.PCanva;

public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroup{

	byte[] drawing;
	HashSet<Canvas> canvaMembers;
	public ServerGroupImpl(Group group, String arg) throws RemoteException {
		super();
		canvaMembers = new HashSet<Canvas>();
		
		if(arg != null) {
			Registry registry = LocateRegistry.getRegistry(arg);
			registry.rebind(group.getName(), this);
		}else {
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(group.getName(), this);
		}
		System.out.println("Server Group \"" + group.getName()+"\" Running");
	}

	@Override
	public void addMember(Canvas canvas) throws RemoteException {
		//pCanva.canvas
		this.canvaMembers.add(canvas);
		
		
	}

	@Override
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException {
		for (Iterator iterator = this.canvaMembers.iterator(); iterator.hasNext();) {
			System.out.println("iterate over pcanvas");
			Canvas canva = (Canvas) iterator.next();
			//canva.drawPath(pixelsToDraw);
		}
		
	}

}
