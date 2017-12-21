package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTextField;

import Modele.Group;
import Modele.Member;
import View.Menu;
import View.MenuMembers;
import Main.Main;

/**
 * Listener to add a new Member in the user's currentGroup; keeps track of the String the user inputs.
 */
public class ListenerAddMember implements KeyListener{
//	private Group group;
	/**
	 * The Field where the user can input the name of the Member he wants to add in his currentGroup.
	 */
	private JTextField inputMember;
	/**
	 * The menu of Member, pointer to MainFrame's.
	 */
	private MenuMembers menuMembers;
	
	/**
	 * Constructor to just set the values of the attributes
	 * @param inputMember to set the value of the attribute of the same name
	 * @param menuMembers to set the value of the attribute of the same name
	 */
	public ListenerAddMember(JTextField inputMember, MenuMembers menuMembers) {
		this.inputMember = inputMember;
		this.menuMembers = menuMembers;
	}

	/**
	 * Upon releasing the enter key, this is called : check whether the Member is already in the Group, else add him. Update Group affiliations.
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) { //Enter key
			Member memberToAdd;
			try {
				memberToAdd = Main.serverApp.getMember(inputMember.getText());
				if(memberToAdd != null) {
					
					if (memberToAdd.isInGroup(Main.USER.getCurrentGroup())) {
						System.out.println("Ce membre est déjà dans le groupe");
					} else {
						Main.USER.getCurrentGroup().addMember(memberToAdd);
						menuMembers.addMember();
						memberToAdd.getGroupList().add(Main.USER.getCurrentGroup());
						System.out.println(memberToAdd.getPseudo() + " added to " + Main.USER.getCurrentGroup().getName());
					}
				} else {
					System.out.println("Membre inexistant");
				}
				Menu.menuMembers.refreshDisplay();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
