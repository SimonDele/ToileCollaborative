package server;

import java.awt.Point;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Modele.Group;
import Modele.Member;

public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroup{

	private ImageIcon drawing;
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
		//Draw on the image 
		
		
		
		//Update the drawing of each member connected
		
		Registry registry;
		registry = LocateRegistry.getRegistry();
		
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			System.out.println("iterate over members");
			Member member = (Member) iterator.next();
			System.out.println(member.getPseudo());
			try {
				UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
				userServer.lookForUpdates(pixelsToDraw);
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
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
