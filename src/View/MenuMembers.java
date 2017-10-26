package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListenerAddMember;
import Modele.Group;
import Modele.Member;

public class MenuMembers extends JPanel{
	
	private ArrayList<Member> listMembers;
	private JTextField inputAddMember;
	

	public MenuMembers(Group group) {
		this.listMembers = group.getMemberList();
		
		
		this.setSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		inputAddMember = new JTextField("Ajouter un membre");
		inputAddMember.addKeyListener(new ListenerAddMember(group, inputAddMember));
		this.add(inputAddMember);
		
		//Display all the member of the group
		for (Iterator iterator = listMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			this.add(new JLabel(""+member.getPseudo()));		
		}
	}
}
