package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Modele.Group;

public interface UserServer extends Remote{
	public void updateGroup(Group group) throws RemoteException;
	public void drawPath(ArrayList<Point> path) throws RemoteException;
}
