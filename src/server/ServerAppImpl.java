package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;

import Modele.Group;
import Modele.Member;

public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {
	
	HashSet<ServerGroup> listServerGroup;
	
	protected ServerAppImpl() throws RemoteException {
		super();
		listServerGroup = new HashSet<ServerGroup>();
		System.out.println("Server App running");
		
		//Create public canvas.
		ServerGroupImpl serverGroupPublic = new ServerGroupImpl(new Group("public"), null);
	
		//And add it to the listServerGroup
		listServerGroup.add(serverGroupPublic);
	}

	public static void main(String[] args) throws RemoteException {
		try {
			ServerAppImpl serverAppImpl = new ServerAppImpl();
			if(args.length > 0) {
				Registry registry = LocateRegistry.getRegistry(args[0]);
				registry.rebind("ServerApp", serverAppImpl);
			}else {
				Registry registry = LocateRegistry.getRegistry();
				registry.rebind("ServerApp", serverAppImpl);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void connection(Member member) throws RemoteException {
		
		System.out.println("connection to the server...");
		//Check if all the group of member.getGroupList() have a "ServerGroup" otherwise it must be created 
		
		//For Each group
		for (Iterator iterator = member.getGroupList().iterator(); iterator.hasNext();) {
			Group group = (Group) iterator.next();
			System.out.println("group");
			boolean groupHasAServer = false;
			//Check if this group has a server
			Iterator iteratorServer = listServerGroup.iterator();
			while(!groupHasAServer && iteratorServer.hasNext()){
				ServerGroup serverGroup = (ServerGroup) iteratorServer.next();
				groupHasAServer = serverGroup.getName() == group.getName();
			}
			//If not create one
			if(!groupHasAServer) {
				System.out.println(group.getName() + " server created");
				listServerGroup.add(new ServerGroupImpl(group, null));
			}else {
				System.out.println(group.getName() + " already got a server");
			}
		}

	}

}
