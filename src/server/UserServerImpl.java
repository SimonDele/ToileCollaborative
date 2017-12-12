package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Main.Main;
import Modele.Group;
import View.MainFrame;

public class UserServerImpl extends UnicastRemoteObject implements UserServer {

	public UserServerImpl() throws RemoteException {
		super();
	}

	@Override
	public void drawPath(ArrayList<Point> path) throws RemoteException{
		System.out.println("update");
		System.out.println(MainFrame.pCanva);
		Main.USER.getCurrentGroup().getCanvas().drawPath(path);
//		MainFrame.pCanva.repaint();
	}

	@Override
	public void updateGroup(Group group) throws RemoteException {
		Main.USER.setCurrentGroup(group);		
	}

}
