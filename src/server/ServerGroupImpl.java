package server;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.imageio.ImageIO;

import Modele.Canvas;
import Modele.Converter;
import Modele.Group;
import View.PCanva;

public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroup{

	//byte[] drawing;
	private String name;
	private HashSet<Canvas> canvaMembers;
	
	public ServerGroupImpl(Group group, String arg) throws RemoteException {
		super();
		canvaMembers = new HashSet<Canvas>();
		this.name = group.getName();
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
		
		System.out.println("member added to the server");
		// Send him the image
		if(this.canvaMembers.size() > 0) {
			//A revoir il faudrait lire sur le pc plutot
			Iterator<Canvas> it = this.canvaMembers.iterator();
			canvas.setDrawing(it.next().getDrawing());
		}else { 
			try {
				canvas.setDrawing(Converter.toIcon(ImageIO.read(new File("drawings/" + name+".png"))));
			} catch (IOException e) {
				canvas.setDrawing(Converter.toIcon(new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB)));
				System.out.println("Drawing empty");
				//e.printStackTrace();
			}
		}
		this.canvaMembers.add(canvas);

	}

	@Override
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException {
		for (Iterator iterator = this.canvaMembers.iterator(); iterator.hasNext();) {
			System.out.println("iterate over canvas");
			Canvas canva = (Canvas) iterator.next();
			canva.drawPath(pixelsToDraw);
		}
		
	}

	@Override
	public String getName() throws RemoteException{
		return this.name;
	}

}
