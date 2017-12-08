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
import Modele.Member;

public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroup{

	//byte[] drawing;
	private String name;
	private HashSet<Member> coMembers;
	private Group group;
	
	public ServerGroupImpl(Group group, String arg) throws RemoteException {
		super();
		this.group = group;
		coMembers = new HashSet<Member>();
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
	public void addMember(Member member) throws RemoteException {
		
		System.out.println("member added to the server");
		// Send him the image
		member.setCurrentCanvas(this.group.getCanvas());
		this.coMembers.add(member);

	}

	@Override
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException {
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			System.out.println("iterate over members");
			Member member = (Member) iterator.next();
			member.getCurrentCanvas().drawPath(pixelsToDraw);
		}
		
	}

	@Override
	public String getName() throws RemoteException{
		return this.name;
	}
	@Override
	public boolean equals(String name) throws RemoteException {
		return name == this.name;
	}

}
