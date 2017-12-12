package server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Main.Main;
import View.MainFrame;

public class UserServerImpl extends UnicastRemoteObject implements UserServer {

	public UserServerImpl() throws RemoteException {
		super();
	}

	@Override
	public void lookForUpdates(ArrayList<Point> path) throws RemoteException{
		System.out.println("update");
		System.out.println(MainFrame.pCanva);
		Main.USER.getCurrentCanvas().drawPath(path);
		//MainFrame.pCanva.repaint();
	}

}
