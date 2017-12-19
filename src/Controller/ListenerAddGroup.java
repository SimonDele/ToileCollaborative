package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JTextField;

import Main.Main;
import Modele.Group;
import Modele.Member;
import View.MenuGroups;
import server.ServerApp;

public class ListenerAddGroup implements KeyListener{
	
	private ArrayList<Group> listGroups;
	private JTextField inputGroupName;
	private MenuGroups menuGroups;
	private Member user;
	
	public ListenerAddGroup(ArrayList<Group> listGroups,JTextField inputGroupName, MenuGroups menuGroups) {
		this.listGroups = listGroups;
		this.inputGroupName = inputGroupName;
		this.menuGroups = menuGroups;
		this.user = user;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) {// Enter key
			// TODO ? lookup the latest ServerApp ?
			// Now let's check if it's not already a group
			String newGroupName = this.inputGroupName.getText();
			try {
				if (Main.serverApp.hasGroup(newGroupName)) {
					System.out.println("Ce groupe existe déjà.");
				} else {
					System.out.println("Nom libre; groupe créé");
					Main.USER.createNewGroup(this.inputGroupName.getText());
					this.menuGroups.refreshDisplay();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
