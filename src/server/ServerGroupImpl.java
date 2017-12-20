package server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Modele.Canvas;
import Modele.Converter;
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
		
		//Load the drawing 
		try {
			drawing = this.loadDrawing();
		} catch (IOException e) {
			drawing = Converter.toIcon(new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB));
		}
		
		System.out.println("Server Group \"" + group.getName()+"\" Running");
	}
	
	
    public void save() throws RemoteException{
        try {
			ImageIO.write(Converter.toBufferedImage(drawing), "PNG", new File("drawings/" + this.getName()+ ".png"));
	        System.out.println(this.getName() + " image saved");
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    public ImageIcon loadDrawing() throws IOException {
        return Converter.toIcon(ImageIO.read(new File("drawings/" + this.getName() + ".png")));
    }

	@Override
	public void addMember(Member member) throws RemoteException {
		
		System.out.println(member.getPseudo() +" added to the server");
		
		this.coMembers.add(member);
		for (Iterator iterator = coMembers.iterator(); iterator.hasNext();) {
			Member memberit = (Member) iterator.next();
			System.out.println(memberit.getPseudo());
		}
	}

	@Override
	public void draw(Member drawer, ArrayList<Point> pixelsToDraw) throws RemoteException {
		// Draw on the server drawing 
		// First convert to bufferedImage (changeable)
		BufferedImage image;
		if( drawing != null) {
			image = Converter.toBufferedImage(drawing);
		}else {
			image = new BufferedImage(Canvas.width,Canvas.height,BufferedImage.TYPE_INT_RGB);
		}
		
        Graphics g = image.createGraphics();
        // draw path
		 g.setColor(drawer.getColor());
		for(Point p : pixelsToDraw) { //We iterate over our list of point in the path ArrayList
			 g.fillRect((int)p.getX(),(int) p.getY(), drawer.getToolbox().getSize(), drawer.getToolbox().getSize());
		}
        g.dispose(); 
        // convert back
		this.drawing = Converter.toIcon(image);

		//Update the drawing of each member connected
		Registry registry;
		System.out.println("iterate over members");
<<<<<<< HEAD
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			System.out.println("(draw) IP member : " + member.getIPAdress());
			registry = LocateRegistry.getRegistry(member.getIPAdress());
=======
		for (Member memberI: coMembers) {
>>>>>>> marchePresque
			try {
				UserServer userServer = (UserServer) registry.lookup(memberI.getPseudo());
				System.out.println(memberI.getPseudo() + " " + memberI.getCurrentGroup().getName());
				if (userServer.getCurrentGroup().is(this.group)) { // unique groupnames
						System.out.println(memberI.getPseudo() + "connected");
						userServer.drawPath(drawer, pixelsToDraw);
				} else {
					// TODO : notify of change
				}
			} catch (NotBoundException e) {
				e.printStackTrace();
				System.out.println("Error on SGI.draw, NotBoundExc");
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

	@Override
	public Group getGroup() throws RemoteException {
		return this.group;
	}

	@Override
	public void sendDrawing(Member member) throws RemoteException {
		Registry registry;
		registry = LocateRegistry.getRegistry(member.getIPAdress());
		try {
			UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
			userServer.loadDrawing(drawing);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void logOut(Member user) throws RemoteException {
		Iterator iterator = coMembers.iterator();
		Boolean isFound = false;
		while ( !isFound &&  iterator.hasNext()) {
			Member member = (Member) iterator.next();
			if(member.getPseudo().equals(user.getPseudo())) {
				isFound = true;
				member = null;
			}
		}
		
		//System.out.println("removed " + user.getPseudo() + " from " + this.group.getName() + coMembers.remove(user));
	}

}
