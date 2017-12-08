package server;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Modele.Member;

public interface ServerGroup extends Remote{
	public void addMember(Member member) throws RemoteException;
	public void draw(ArrayList<Point> pixelsToDraw) throws RemoteException;
	public String getName() throws RemoteException;
	public boolean equals(String name) throws RemoteException;
}
