package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Main.Main;
import Modele.Group;
import Modele.Member;
import View.MainFrame;
import View.Menu;

public class UserServerImpl extends UnicastRemoteObject implements UserServer {

	public UserServerImpl() throws RemoteException {
		super();
	}

	@Override
	public void drawPath(Member drawer, ArrayList<Point> path) throws RemoteException{
		System.out.println("update");
		Main.USER.getCurrentGroup().getCanvas().drawPath(drawer, path);
	}

	@Override
	public void updateGroup(Group group) throws RemoteException {
		Main.USER.setCurrentGroup(group);		
	}

	@Override
	public void loadDrawing(ImageIcon drawing) throws RemoteException {
		Main.USER.getCurrentGroup().getCanvas().setDrawing(drawing);
		
	}

	@Override
	public Group getCurrentGroup() throws RemoteException {
		return Main.USER.getCurrentGroup();
	}

	@Override
	public void updateCurrentGroupInfo(Group group) throws RemoteException {
		// directly just replace it
		Main.USER.setCurrentGroup(group);
		// TODO erase following debugging display
		System.out.println("in member " + Main.USER.getPseudo() + " memberList of " + group.getName() + " is :");
		Main.USER.getCurrentGroup().printMembers();
		
		Menu.menuMembers.refreshDisplay();
	}

	@Override
	public void updateListGroup(Group group) throws RemoteException {
		// set new group in list and update display
		Main.USER.addedTo(group);
		Menu.menuGroups.refreshDisplay();
	}

}
