package Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import View.PCanva;
import server.ServerGroup;
import server.ServerGroupImpl;
import Main.Main;

/**
 * Listening the actions performed on the Canvas, such as drawPath
 */
public class ListenersPCanva implements MouseListener, MouseMotionListener  {
	/**
	 * The path drawn by the user (arraylist of points)
	 */
	private ArrayList<Point> path;
	
	/**
	 * Constructor to simply initialize the path arraylist
	 */
	public ListenersPCanva() {
		path = new ArrayList<Point>();

	}
	/**
	 * Getter for the path of points
	 * @return the path attribute
	 */
	public ArrayList<Point> getPath(){
		return path;
	}
	
	/**
	 * Upon finishing the path drawn (releasing the mouseclick), the path is sent to the (looked up) ServerGroup of the user's current server, which relays the information.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			ServerGroup serverG = (ServerGroup) registry.lookup(Main.USER.getCurrentGroup().getName());
			System.out.println("(ListenerPC.released) Order to draw on "+ serverG.getName());
			serverG.draw(Main.USER, path);
		} catch (RemoteException | NotBoundException e1) {
			e1.printStackTrace();
			System.out.println("Remote Exc error in ListenPCanva.mouseReleased");
		}

		this.path = new ArrayList<Point>();		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.path.add(new Point(e.getX(), e.getY()));
		//pCanva.drawPath();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
