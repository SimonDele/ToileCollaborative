package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserServer extends Remote{
	public void lookForUpdates(ArrayList<Point> path) throws RemoteException;
}
