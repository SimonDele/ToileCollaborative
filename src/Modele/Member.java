package Modele;

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
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

import server.ServerApp;
import Main.Main;

/**
 * Class that represents a member, having his name (pseudo) and password, as well as his groups and his own color.
 */
public class Member implements Serializable {
	/**
	 * the (unique) name of the Member. Used, for example, by another Member to add him to a group.
	 */
	private String pseudo;
	/**
	 * The Member's password - TODO : handle this securely.
	 */
	private String password;
	/**
	 * The unique color chosen by the member at creation, with which he draws and through which he can be recognized in the Canvas.
	 */
	private Color color; 
	/**
	 * The list of Groups the Member is part of.
	 */
	private ArrayList<Group> groupList;
	/**
	 * The toolbox the Member uses to draw on the miscellaneous Canvas
	 */
	private Toolbox toolbox;
	/**
	 * The Group (and by extension, the Canvas) the Member is currently on (displayed)
	 */
	private Group currentGroup;
	/**
	 * The last IPAddress with which the Member has connected - so the Server can find him back.
	 */
	private String iPAddress;
	
	/**
	 * Constructor that takes the parameters filled by the client upon registration, and initializes the groupList and toolbox
	 * @param pseudo the name chosen by the client
	 * @param password the password chosen by the client
	 * @param color the color chosen by the client 
	 */
	public Member(String pseudo,String password, Color color) {
		groupList = new ArrayList<Group>(); // Creation of an empty groupList
		this.pseudo = pseudo;
		this.password = password;
		this.color = color;
		this.setToolbox(new Toolbox());

	}
	
	/**
	 * Getter for the current IPAddress of the Member
	 * @return the current IPAddress
	 */
	public String getIPAddress() {
		return iPAddress;
	}
	
	/**
	 * Setter for the IPAddress
	 * @param iPAddress the new/latest IPAddress
	 */
	public void setIPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	/**
	 * Getter for the pseudo of the Member
	 * @return the member's pseudo (name)
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * Getter for the color of the Member
	 * @return the Member's color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Getter for the Member's password - TODO security
	 * @return the Member's password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Getter for the Member's list of groups
	 * @return the list of groups the Member is part of
	 */
	public ArrayList<Group> getGroupList() {
		return groupList;
	}
	/**
	 * Getter for the Group the Member is currently on
	 * @return Member's current group
	 */
	public Group getCurrentGroup() {
		return currentGroup;
	}
	/**
	 * Setter for the Group the Member is currently on
	 * @param currentGroup the new Group the Member is now on
	 */
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}

	/**
	 * Method to create a Group. The Group is named and added to the list, but above all sends the new Group's information to the ServerApp for the creation of a new server and editing of the file
	 * @param name the name of the group chosen by the Member 
	 */
	public void createNewGroup(String name) {
		//Create the group and add it to the list of groups
		Group newgroup = new Group(name);
		this.groupList.add(newgroup);
		this.currentGroup = newgroup;
		
		try {
			Main.serverApp.addNewServerGroup(this,newgroup);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Update the file on the server
		ArrayList<Member> members;
		try {
			// Get the list of all the members
			members = Main.serverApp.readFileMembers();
			// Then updating of the file
			for(int i=0; i<members.size();i++) { //Modify the current User
				if(members.get(i).pseudo.equals(this.pseudo) && members.get(i).password.equals(this.password)) {
					members.set(i,this);
				}
			}
			
			Main.serverApp.writeFileMembers(members); //And rewrite everything
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
/*
	public void saveBeforeExit() {
		ArrayList<Member> member = readFileMembers(); // Get the list of all the members
		writeFileMembers(member); //And rewrite it
		for(int i=0; i<this.groupList.size(); i++) {
			try {
				this.groupList.get(i).getCanvas().save(this.groupList.get(i).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
*/
	/**
	 * Getter for the Member's toolbox 
	 * @return the Member's toolbox
	 */
	public Toolbox getToolbox() {
		return toolbox;
	}
	/**
	 * Setter for the Member's toolbox to a new one
	 * @param toolbox the new toolbox
	 */
	public void setToolbox(Toolbox toolbox) {
		this.toolbox = toolbox;
	}
	/**
	 * Checks whether the member is part of a given group
	 * @param groupToCheck the group the member may be in
	 * @return boolean : whether the Member is in the Group
	 */
	public boolean isInGroup(Group groupToCheck) {
		boolean is = false;
		for (Group group : groupList) {
			if (group.getName().equals(groupToCheck.getName())) {
				is = true;
				break;
			}
		}
		return is;
	}
	/**
	 * Equivalent to an "equals" method using the fact that a Member's name is unique
	 * @param memberCompare the Member this one may be
	 * @return whether they are identical
	 */
	public boolean is(Member memberCompare) {
		return this.pseudo.equals(memberCompare.getPseudo());
	}
}
