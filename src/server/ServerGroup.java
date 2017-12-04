package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import View.PCanva;

public interface ServerGroup extends Remote{
	public void addMember(PCanva pCanva) throws RemoteException;
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException;
}
