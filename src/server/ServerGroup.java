package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Modele.Canvas;
import View.PCanva;

public interface ServerGroup extends Remote{
	public void addMember(Canvas canvas) throws RemoteException;
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException;
}
