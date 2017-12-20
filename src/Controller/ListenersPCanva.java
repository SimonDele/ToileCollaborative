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
public class ListenersPCanva implements MouseListener, MouseMotionListener  {
	private ArrayList<Point> path;
	private ServerGroup serverGroup;
	
	public ListenersPCanva() {
		path = new ArrayList<Point>();
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			this.serverGroup = (ServerGroup) registry.lookup(Main.USER.getCurrentGroup().getName());
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}
	public void changeServer(String name) {
		Registry registry;
		System.out.println("change server");

		try {
			if(Main.adress != null) {
				registry = LocateRegistry.getRegistry(Main.adress);
			}else {
				registry = LocateRegistry.getRegistry();	
			}
			this.serverGroup = (ServerGroup) registry.lookup(name);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Point> getPath(){
		return path;
	}
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
