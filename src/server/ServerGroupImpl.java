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

/**
 * Implementation of the ServerGroup RMI Interface
 */
public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroup{
	/**
	 * Server's drawing on which changes are also made (along with Members' actions)
	 */
	private ImageIcon drawing;
	/**
	 * Name of the Group (and of this ServerGroup by extension)
	 */
	private String name;
	/**
	 * list of connected Members (subset of the "listMembers" set of the group)
	 */
	private HashSet<Member> coMembers;
	/**
	 * Group associated with this ServerGroup
	 */
	private Group group;
	
	/**
	 * Constructor based on a Group. Loads the drawing, and binds the ServerGroup to the registry.
	 * @param group the Group which needs a server
	 * @throws RemoteException as RMI method
	 */
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
	
	/**
	 * Writes the drawing on the file (named after the group) to save it 
	 */
    public void save() throws RemoteException{
        try {
			ImageIO.write(Converter.toBufferedImage(drawing), "PNG", new File("drawings/" + this.getName()+ ".png"));
	        System.out.println(this.getName() + " image saved");
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    /**
     * Loads the drawing from the file named after the group
     * @return the drawing from the file
     * @throws IOException from reading the file
     */
    public ImageIcon loadDrawing() throws IOException {
        return Converter.toIcon(ImageIO.read(new File("drawings/" + this.getName() + ".png")));
    }

    /**
     * Adds a member to the list of connected members
     * @param member the Member to add
     */
	@Override
	public void addMember(Member member) throws RemoteException {
		System.out.println(member.getPseudo() +" added to the server");
		
		this.coMembers.add(member);
		// debugging : print current list
		for (Iterator iterator = coMembers.iterator(); iterator.hasNext();) {
			Member memberit = (Member) iterator.next();
			System.out.println(memberit.getPseudo());
		}
	}

	/**
	 * Method to draw a path of points when asked by a given drawer. The path is drawn on the group's Canvas, then the order is sent to every connected Member that is currently on this Group.
	 * @param drawer the Member that has emitted the order to draw
	 * @param pixelsToDraw the arraylist of pixels the drawer has sent
	 * @throws RemoteException as RMI method
	 */
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
		
		for (Iterator iterator = this.coMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			System.out.println("(draw) IP member : " + member.getIPAddress());
			registry = LocateRegistry.getRegistry(member.getIPAddress());
			try {
				UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
				System.out.println(member.getPseudo() + " " + member.getCurrentGroup().getName());
				if (userServer.getCurrentGroup().is(this.group)) { // unique groupnames
						System.out.println(member.getPseudo() + "connected");
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
	
	/**
	 * Getter for the name of the Group/ServerGroup
	 * @return the name of the Group
	 */
	@Override
	public String getName() throws RemoteException{
		return this.name;
	}
	
	/**
	 * Whether this ServerGroup has the same name as another (names are unique so this is an equality test)
	 * @param name the name of the Group to compare itself to
	 * @returns whether the groups are the same
	 */
	@Override
	public boolean equals(String name) throws RemoteException {
		return name.equals(this.name);
	}

	/**
	 * Getter for the associated group
	 * @return the ServerGroup's Group
	 */
	@Override
	public Group getGroup() throws RemoteException {
		return this.group;
	}

	/**
	 * Sends the current drawing to a specified member by looking it up on his registry through IP. Called when changing group or at first arrival for public.
	 * @param member The member to send the drawing to
	 * @throws RemoteException as RMI method 
	 */
	@Override
	public void sendDrawing(Member member) throws RemoteException {
		Registry registry;
		registry = LocateRegistry.getRegistry(member.getIPAddress());
		try {
			UserServer userServer = (UserServer) registry.lookup(member.getPseudo());
			userServer.loadDrawing(drawing);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Locally logs a member out, that is - remove it from the list of connected members
	 * @param user The Member to remove from the list
	 * @throws RemoteException as RMI method
	 */
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
	}
}
