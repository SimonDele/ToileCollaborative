package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Modele.Member;

public interface ServerApp extends Remote {
	
	
	public void connection(Member member) throws RemoteException;

}
