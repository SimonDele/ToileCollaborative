package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTextField;

import Modele.Group;
import Modele.Member;
import View.MenuMembers;
import Main.Main;

public class ListenerAddMember implements KeyListener{
	private Group group;
	private JTextField inputMember;
	private MenuMembers menuMembers;
	//private ArrayList<Member> listMembers;
	
	public ListenerAddMember(Group group, JTextField inputMember, ArrayList<Member> listMembers, MenuMembers menuMembers) {
		this.group = group;
		this.inputMember = inputMember;
		//this.listMembers = listMembers;
		this.menuMembers = menuMembers;
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) { //Enter key
			Member memberToAdd;
			try {
				memberToAdd = Main.serverApp.getMember(inputMember.getText());
				if(memberToAdd != null) {
					group.addMember(memberToAdd);
					menuMembers.addMember();
					memberToAdd.getGroupList().add(this.group);
					System.out.println("Membre ajoute");
				}else {
					System.out.println("Membre inexistant");
				}
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
