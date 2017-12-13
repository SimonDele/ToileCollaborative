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
		
		System.out.println("member added to the server");
		// TODO Send him the image
		this.coMembers.add(member);
	}

	@Override
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException {
		// TODO: Draw on the image 
//  Pb on ne connait pas la toolbox ni taille panel
/*		BufferedImage image;
		if( drawing != null) {
			image = Converter.toBufferedImage(drawing);
		}else {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		}
		
        Graphics g = image.createGraphics();

        
		for(Point p : pixelsToDraw) { //We iterate over our list of point in the path ArrayList
			 g.setColor(Color.red); //A changer 
			 g.fillRect((int)p.getX(),(int) p.getY(), 20, 20);
		}
		

        g.dispose();
		this.drawing = Converter.toIcon(image);
*/
		
//		this.group.getCanvas().drawPath(pixelsToDraw);
		//Update the drawing of each member connected
		Registry registry;
		registry = LocateRegistry.getRegistry();
		System.out.println("iterate over members");
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			System.out.println(member.getPseudo());
			if (member.getCurrentGroup().getName()==this.group.getName()) { // unique groupnames
				try {
					UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
					userServer.drawPath(pixelsToDraw);
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			} else {
				// TODO : notify of change
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

}
