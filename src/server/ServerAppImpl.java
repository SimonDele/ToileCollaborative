package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Modele.Group;
import Modele.Member;

public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {
	
	HashSet<ServerGroup> listServerGroup;
	final String nameGroupPublic = "public"; 
	final File fileMembers = new File("listeMembers.txt");
	
	protected ServerAppImpl() throws RemoteException {
		super();
		listServerGroup = new HashSet<ServerGroup>();
		System.out.println("Server App running");
		
		//Create public canvas.
		Group group = new Group(nameGroupPublic);
		Registry registry = LocateRegistry.getRegistry();
		ServerGroup serverGroupPublic;
		try {
			serverGroupPublic = (ServerGroup) registry.lookup(nameGroupPublic);
			//And add it to the listServerGroup
			listServerGroup.add(serverGroupPublic);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

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
	public Member register(String pseudo, String password) throws RemoteException {
		System.out.println("registered");
		//Create member
		Member member = new Member(pseudo, password);
		
		//Add him to the group/server public 
		connectToServerGroup(nameGroupPublic, member);

		//TODO update file
		return member;
	}
	
	public void connectToServerGroup(String serverName, Member member) {
		boolean serverFound = false;
		Iterator iteratorServer = listServerGroup.iterator();
		ServerGroup serverGroup = null;
		while(!serverFound && iteratorServer.hasNext()){
			serverGroup = (ServerGroup) iteratorServer.next();
			try {
				serverFound = serverGroup.equals(serverName);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if(serverFound) {
			try {
				serverGroup.addMember(member);
			} catch (RemoteException e) {
				e.printStackTrace();
			}	
		}else {
			// TODO create a server
			//listServerGroup.add(new ServerGroupImpl(group, null));
		}

	}
	@Override
	public Member connection(String pseudo, String password) throws RemoteException {
		
		System.out.println("connection to the server...");
		
		
		//
		ArrayList<Member> listMembers = this.readFileMembers();
		ArrayList<Member> listMember = readFileMembers();
		Iterator iterator = listMember.iterator();
		boolean found = false;
		Member member = null;
		while(!found && iterator.hasNext()) {
			member = (Member) iterator.next();
			if((password.equals(member.getPassword())) && (pseudo.equals(member.getPseudo()))) {
				found = true;
			}
		}
		if(found) {
			//Check if all the group of member.getGroupList() have a "ServerGroup" otherwise it must be created 
			
			//For Each group
			for (Iterator itGroup = member.getGroupList().iterator(); itGroup.hasNext();) {
				Group group = (Group) itGroup.next();
				System.out.println("group");
				this.connectToServerGroup(group.getName(), member);
			}
			return member;
		}else {
			//Not yet registered
			return null;
		}
		
		

	}
	private void writeFileMembers(ArrayList<Member> listMembers) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			          new BufferedOutputStream(
			            new FileOutputStream(fileMembers)));
		    for (int i=0; i<listMembers.size();i++) {
				Member member = listMembers.get(i);
				oos.writeObject(member);	
			}
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	          		
	}
	private ArrayList<Member> readFileMembers(){
		ArrayList<Member> res = new ArrayList<Member>();
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(this.fileMembers)));
				while(true) {
					try {
						Member obj = (Member)ois.readObject();
						res.add(obj);
					}catch(EOFException | ClassNotFoundException e) { // Catch if we have reached the end of the file
						ois.close();
						break;
					}

				}
				
			ois.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return res;
	}

}
