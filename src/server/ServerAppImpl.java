package server;

import java.awt.Color;
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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Modele.Group;
import Modele.Member;

/**
 * Implementation of the ServerApp RMI Interface
 */
public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {
	/**
	 * HashSet containing every ServerGroup currently online 
	 */
	HashSet<ServerGroup> listServerGroup;
	/**
	 * Name of the most important group, "public", which every member is part of
	 */
	public final String nameGroupPublic = "public"; 
	/**
	 * The "public" Group which every member is part of
	 */
	Group groupPublic;
	/**
	 * The file containing each and every Member as an object (so with access to their Groups)
	 */
	final File fileMembers = new File("listeMembers.txt");
	
	/**
	 * Initializes the HashSet of ServerGroup as well as the "public" group.
	 * @throws RemoteException
	 */
	protected ServerAppImpl() throws RemoteException {
		super();
		listServerGroup = new HashSet<ServerGroup>();

		//Create public canvas.
		this.groupPublic = new Group(nameGroupPublic);
		ServerGroupImpl newServerGroup = new ServerGroupImpl(groupPublic);
		this.listServerGroup.add(newServerGroup);
	}
 
	/**
	 * Member registration : creates and initializes the associated Member, adds it to the "public" Group and finally adds it to the file.
	 * @param pseudo the chosen name for the new Member
	 * @param password the chosen password for the new Member
	 * @param color the chosen color for the new Member
	 * @param iPAddress the IPAddress the Member is now connected with
	 * @throws RemoteException as RMI method
	 */
	public Member register(String pseudo, String password,  Color color, String iPAddress) throws RemoteException {
		System.out.println("registered");
		//Create member
		Member member = new Member(pseudo, password, color);
		member.setIPAddress(iPAddress);
		
		member.setCurrentGroup(groupPublic);
		member.getGroupList().add(groupPublic);
		//Add him to the group/server public 
		connectToServerGroup(groupPublic, member);
		
		//Update file
		ArrayList<Member> listMembers = this.readFileMembers();
		listMembers.add(member);
		this.writeFileMembers(listMembers);	
		
		return member;
	}
	
	/**
	 * Connects a member to one of the Groups that was on his list ; creates the associated ServerGroup if it isn't online yet (no member connected)
	 * @param group the Group to connect to
	 * @param member the member to connect to the server
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void connectToServerGroup(Group group, Member member) throws RemoteException{
		boolean serverFound = false;
		Iterator iteratorServer = listServerGroup.iterator();
		ServerGroup serverGroup = null;
		while(!serverFound && iteratorServer.hasNext()){
			serverGroup = (ServerGroup) iteratorServer.next();
			System.out.println("while " + serverGroup.getName());
			try {
				serverFound = (serverGroup.getName().equals(group.getName()));
				System.out.println(serverGroup.getName() +" " + group.getName() + " " + serverFound);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if(serverFound) {
			try {
				System.out.println("(ServerApp connectoserver)" + member.getPseudo() + " add to the server "+ serverGroup.getName());
				serverGroup.addMember(member);
			} catch (RemoteException e) {
				e.printStackTrace();
			}	
		}else {
			ServerGroup newServerGroup = new ServerGroupImpl(group);
			newServerGroup.addMember(member);
			listServerGroup.add(newServerGroup);
		}

	}
	
	/**
	 * Connection of a Member after signing in, by checking in the Members file
	 * @param pseudo name of the member trying to connect
	 * @param password the password of the member trying to connect
	 * @param iPAddress the new IPAddress of the Member
	 * @throws RemoteException as RMI method
	 * @return the associated Member
	 */
	@Override
	public Member connection(String pseudo, String password, String iPAddress) throws RemoteException {
				System.out.println("connection to the server...");
		
		ArrayList<Member> listMember = readFileMembers();
		Iterator iterator = listMember.iterator();
		boolean found = false;
		Member member = null;
		while(!found && iterator.hasNext()) {
			member = (Member) iterator.next();
			found = ((password.equals(member.getPassword())) && (pseudo.equals(member.getPseudo())));
		}
		if(found) {
			member.setIPAddress(iPAddress);
			//Check if all the group of member.getGroupList() have a "ServerGroup" otherwise it must be created 
			//For Each group
			for (Iterator itGroup = member.getGroupList().iterator(); itGroup.hasNext();) {
				Group group = (Group) itGroup.next();
				this.connectToServerGroup(group, member);
			}
			member.setCurrentGroup(groupPublic);
			return member;
		}else {
			//Not yet registered
			return null;
		}

	}
	
	/**
	 * The creation of a new ServerGroup based on a Member's order to create a Group
	 * @param creator the Group's creator
	 * @param group the group associated to this new ServerGroup
	 * @throws RemoteException as RMI method
	 */
	public void addNewServerGroup(Member creator, Group group) throws RemoteException {
		ServerGroupImpl newServerGroup = new ServerGroupImpl(group);
		newServerGroup.addMember(creator);
		this.listServerGroup.add(newServerGroup);
	}
	
	/**
	 * Method to write the file based on the list of members
	 * @param listMembers the list of Member to write on the file
 	 * @throws RemoteException as RMI method
	 */
	public void writeFileMembers(ArrayList<Member> listMembers) throws RemoteException{
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
	
	/**
	 * Method to get a list of members from the file
 	 * @throws RemoteException as RMI method
 	 * @return The Arraylist of Members found on the file
	 */
	public ArrayList<Member> readFileMembers() throws RemoteException{
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
	
	/**
	 * Method to get the member from the file of ServerApp at connection based on its name
	 * @param pseudo the name to search on the file of ServerApp
 	 * @throws RemoteException as RMI method
 	 * @returns The searched Member, null if nonexistent
	 */
	@Override
	public Member getMember(String pseudo) throws RemoteException {
		
		ArrayList<Member> listMembers = readFileMembers();
		for (Iterator iterator = listMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			if(member.getPseudo().equals(pseudo)) {
				return member;
			}
		}
		return null; //If the member doesn't exist
	}
	
	/**
	 * Main launched by the ServerApp administrator. The ServerApp is bound on the hosts' registry and its associated frame is launched.
	 * @throws RemoteException as RMI method
	 */
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
			FrameServer frameServer = new FrameServer(serverAppImpl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Server App running");
	}
	
	/**
	 * Getter for the name of the public Group
	 * @return The name of the public Group
	 * @throws RemoteException as RMI method
	 */
	@Override
	public String getNameGroupPublic() throws RemoteException {
		return this.nameGroupPublic;
	}

	/**
	 * Method to log out a given Member 
	 * @param user the Member to disconnect
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void logOut(Member user) throws RemoteException {
		for (Iterator iterator = user.getGroupList().iterator(); iterator.hasNext();) {
			Group group = (Group) iterator.next();
			for (Iterator iterator2 = listServerGroup.iterator(); iterator2.hasNext();) {
				ServerGroup serverGroup = (ServerGroup) iterator2.next();
				if(serverGroup.getName().equals(group.getName())) {
					serverGroup.logOut(user);
				}
			}
		}
	}
	
	/**
	 * Method to save all the Canvas before closing
	 */
	public void saveAll() {
		for (Iterator iterator = listServerGroup.iterator(); iterator.hasNext();) {
			ServerGroup serverGroup = (ServerGroup) iterator.next();
			try {
				serverGroup.save();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Whether the ServerApp already has a group named a given way
	 * @param newGroupName the String chosen by the creator, to check
	 * @return a boolean, whether the ServerApp already has this group
	 * @throws RemoteException as RMI method
	 */
	@Override
	public boolean hasGroup(String newGroupName) throws RemoteException {
		boolean has = false;
		for (ServerGroup serverGroup : listServerGroup) {
			if (serverGroup.getName().equals(newGroupName)) {
				has = true;
				break;
			}
		}
		return has;
	}

	/**
	 * Debugging method to simply print the ServerGroups that are currently on the ServerApp
	 * @throws RemoteException as RMI method
	 */
	@Override
	public void printCurrentGroups() throws RemoteException {
		System.out.println("liste groupes :");
		for (ServerGroup serverGroup : listServerGroup) {
			System.out.println(serverGroup.getName());
		}
	}
}
