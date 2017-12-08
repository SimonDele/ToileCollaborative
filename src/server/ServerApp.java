package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Modele.Member;

public interface ServerApp extends Remote {
	
	
	public Member connection(String pseudo, String password) throws RemoteException;
	public Member register(String pseudo, String password) throws RemoteException;
}
