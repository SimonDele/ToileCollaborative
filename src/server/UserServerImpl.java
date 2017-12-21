package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Main.Main;
import Modele.Group;
import Modele.Member;

/**
 * Implementation of the UserServer RMI Interface.
 */
public class UserServerImpl extends UnicastRemoteObject implements UserServer {
	/**
	 * Empty constructor
	 * @throws RemoteException as RMI method
	 */
	public UserServerImpl() throws RemoteException {
		super();
	}

	/**
	 * Drawing method: draws on the Member's Canvas the path the Member drawer has ordered to draw.
	 * @param drawer The Member that has given the order
	 * @param path The path of pixels to draw
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void drawPath(Member drawer, ArrayList<Point> path) throws RemoteException{
		System.out.println("update");
		Main.USER.getCurrentGroup().getCanvas().drawPath(drawer, path);
	}

	/**
	 * Sets the Member's current Group to the one given
	 * @param group The new currentGroup
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void updateGroup(Group group) throws RemoteException {
		Main.USER.setCurrentGroup(group);		
	}

	/**
	 * Relays the drawing sent by the ServerGroup to the user on this host. Usually upon changing group / connection.
	 * @param drawing The drawing currently on the Group of this Member.
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void loadDrawing(ImageIcon drawing) throws RemoteException {
		Main.USER.getCurrentGroup().getCanvas().setDrawing(drawing);
		
	}
	
	/**
	 * Getter for this user's current Group.
 	 * @throws RemoteException as RMI method
	 * @return this user's current Group.
	 */
	@Override
	public Group getCurrentGroup() throws RemoteException {
		return Main.USER.getCurrentGroup();
	}

}
