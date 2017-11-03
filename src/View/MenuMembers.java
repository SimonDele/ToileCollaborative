package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListenerAddMember;
import Controller.ListenerSwitchGroup;
import Modele.Group;
import Modele.Member;

public class MenuMembers extends JPanel{
	
	private ArrayList<Member> listMembers;
	private JTextField inputAddMember;
	private ArrayList<JLabel> labMembers;

	public MenuMembers(Group group) {
		this.labMembers = new ArrayList<JLabel>();
		try {
			this.listMembers = group.getMemberList();			
		}catch(NullPointerException e) {
			this.listMembers = new ArrayList<Member>();
		}
		
		this.setSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		inputAddMember = new JTextField("Ajouter un membre");
		inputAddMember.addKeyListener(new ListenerAddMember(group, inputAddMember, listMembers, this));
		this.add(inputAddMember);
		
		//Display all the member of the group
		for (int i=0; i<this.listMembers.size(); i++) {
			Member member = this.listMembers.get(i);
			this.labMembers.add(new JLabel(""+member.getPseudo()));	
			this.add(labMembers.get(i));	
		}
	}
	public void refreshDisplay() {
		if(this.labMembers.size() < this.listMembers.size()) { // Check if one label is missing (might be true all the time)
			this.labMembers.add(new JLabel(""+this.listMembers.get(listMembers.size()-1).getPseudo())); // add to the arraylist of jbutton
			this.add(this.labMembers.get(this.labMembers.size()-1)); // add the new jlabel to the panel
		}

		this.revalidate();
		this.repaint();
	}
}
