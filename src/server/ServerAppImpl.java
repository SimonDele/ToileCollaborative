package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Modele.Member;

public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {

	protected ServerAppImpl() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			ServerAppImpl serverAppImpl = new ServerAppImpl();
			if(args.length > 0) {
				Registry registry = LocateRegistry.getRegistry(args[0]);
				registry.rebind("ServerApp", serverAppImpl);
			}else {
				
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void connection(Member member) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
