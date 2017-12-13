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
			drawing = Converter.toIcon(ImageIO.read(new File(this.group.getName()+".png")));
		} catch (IOException e) {
			drawing = Converter.toIcon(new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB));
		}
		
		System.out.println("Server Group \"" + group.getName()+"\" Running");
	}
	
	/* TODO
    public void save() throws IOException{
        ImageIO.write(drawing, "PNG", new File("filename.png"));
    }
   */
    public void load() throws IOException {
        
    }

	@Override
	public void addMember(Member member) throws RemoteException {
		
		System.out.println(member.getPseudo() +" added to the server");
		// TODO Send him the image
		

		
		this.coMembers.add(member);
		for (Iterator iterator = coMembers.iterator(); iterator.hasNext();) {
			Member memberit = (Member) iterator.next();
			System.out.println(memberit.getPseudo());
		}
		
		// Send him the image if the current group of the member is this one
		if(member.getCurrentGroup().equals(this.group)) {
			//this.sendDrawing(member);
		}
	}

	@Override
	public void draw(Member drawer, ArrayList<Point> pixelsToDraw) throws RemoteException {
		// Draw on the server drawing 
		BufferedImage image;
		if( drawing != null) {
			image = Converter.toBufferedImage(drawing);
		}else {
			image = new BufferedImage(Canvas.width,Canvas.height,BufferedImage.TYPE_INT_RGB);
		}
		
        Graphics g = image.createGraphics();

        
		for(Point p : pixelsToDraw) { //We iterate over our list of point in the path ArrayList
			 g.setColor(Color.red); //A changer 
			 g.fillRect((int)p.getX(),(int) p.getY(), drawer.getToolbox().getSize(), drawer.getToolbox().getSize());
		}
		

        g.dispose();
		this.drawing = Converter.toIcon(image);

		
		
		
		//Update the drawing of each member connected
		Registry registry;
		registry = LocateRegistry.getRegistry();
		System.out.println("iterate over members");
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			try {
				UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
				System.out.println(member.getPseudo() + " " + member.getCurrentGroup().getName());
				if (userServer.getCurrentGroup().getName().equals(this.group.getName())) { // unique groupnames
						System.out.println(member.getPseudo() + "connected");
						userServer.drawPath(drawer, pixelsToDraw);
				} else {
					// TODO : notify of change
				}
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void updateMember(Member member) throws RemoteException {
		for (Iterator iterator = coMembers.iterator(); iterator.hasNext();) {
			Member memberit = (Member) iterator.next();
			if(memberit.getPseudo().equals(member.getPseudo())) {
				memberit = member;
				System.out.println(memberit.getPseudo() + " current group " + memberit.getCurrentGroup().getName());
			}
		}
/* Work
		System.out.println("after update");
		for (Iterator iterator = coMembers.iterator(); iterator.hasNext();) {
			Member memberit = (Member) iterator.next();
			System.out.println(member.getPseudo() + " current group " + member.getCurrentGroup().getName());
		}
*/
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
		registry = LocateRegistry.getRegistry();
		try {
			UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
			userServer.loadDrawing(drawing);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
