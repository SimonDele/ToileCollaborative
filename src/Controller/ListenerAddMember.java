package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import Modele.Group;
import Modele.Member;
import View.MenuMembers;

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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) { //Enter key
			Member memberToAdd = Member.getMember(inputMember.getText());
			if(memberToAdd != null) {
				group.addMember(memberToAdd);
				menuMembers.addMember();
				System.out.println("Membre ajouté");
			}else {
				System.out.println("Membre inexistant");
			}
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
