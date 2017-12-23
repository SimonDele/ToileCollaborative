package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Main.Main;
import Modele.Group;
import View.MainFrame;
import View.Menu;
import server.ServerGroup;

/**
 * Listener on the list of groups the user has, and can potentially choose amongst. 
 */
public class ListenerSwitchGroup implements ActionListener {
/*
 * This listener is called when we want to switch from one group to another
 * It will change : 
 * 1) the current canvas 
 * 2) the list of members

 * 2) Not yet implemented
 */
	/**
	 * The Group the user chooses 
	 */
	Group newGroup;
	
	/**
	 * Simple constructor to set the values of the attribute
	 * @param group
	 */
	public ListenerSwitchGroup(Group group) {
		this.newGroup = group;
	}
	
	/**
	 * When clicking on a group, the associated ServerGroup is looked up and fetched; the user switches to this new group and retrieves its Canvas. This method should also update the display for the list of members 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Registry registry;
		try {
			// retrieve the newGroup on the server
			if(Main.address != null) {
				registry = LocateRegistry.getRegistry(Main.address);
			}else {
				registry = LocateRegistry.getRegistry();	
			}
			ServerGroup serverGroup = (ServerGroup) registry.lookup(this.newGroup.getName());
			this.newGroup = serverGroup.getGroup();
			
			// set all variables
			Main.USER.setCurrentGroup(this.newGroup);
			MainFrame.currentGroup = this.newGroup;
			MainFrame.pCanva.switchCanvas();
			System.out.println(this.newGroup.getMemberList());
			Menu.menuMembers.setListMembers(this.newGroup.getMemberList());
			System.out.println(Main.USER.getCurrentGroup().getName() + "current group");
		
			// load Drawing
			serverGroup.sendDrawing(Main.USER);
			
			Menu.menuGroups.refresh();
					
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
