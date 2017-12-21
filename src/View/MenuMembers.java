package View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListenerAddMember;
import Modele.Group;
import Modele.Member;

@SuppressWarnings("serial")
public class MenuMembers extends JPanel{
	private ArrayList<Member> listMembers;
	private JTextField inputAddMember;
	private ArrayList<JLabel> labMembers;
	private String strAddMember;
	
	public MenuMembers(Group group) {
		this.labMembers = new ArrayList<JLabel>();
		try {
			this.listMembers = group.getMemberList();			
		}catch(NullPointerException e) {
			this.listMembers = new ArrayList<Member>();
		}

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.strAddMember = "+ New Member";
		inputAddMember = new JTextField(this.strAddMember);
		inputAddMember.addKeyListener(new ListenerAddMember(inputAddMember, this));
		this.add(inputAddMember);
		inputAddMember.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputAddMember.getMinimumSize().height));


		this.refreshDisplay();
		this.setBackground(Color.gray);
	}
	
	public void setListMembers(ArrayList<Member> listMembers) {
		this.listMembers = listMembers;
		this.refreshDisplay();
	}
	public String getStrAddMember() {
		return strAddMember;
	}

	public void setStrAddMember(String sAddMember) {
		this.strAddMember = sAddMember;
	}

	public void refreshDisplay() {
		// Remove everything
		for(int i=0; i<this.labMembers.size(); i++) {
			this.remove(this.labMembers.get(i));
		}
		this.labMembers = new ArrayList<JLabel>();
		
		// Add the members
		for (int i=0; i<this.listMembers.size(); i++) {
			Member member = this.listMembers.get(i);
			this.labMembers.add(new JLabel(""+member.getPseudo()));	
			this.add(labMembers.get(i));	
		}
		
		this.inputAddMember.setText(strAddMember);
		this.revalidate();
		this.repaint();
	}
	
	public void addMember() {
		if(this.labMembers.size() < this.listMembers.size()) { // Check if one label is missing (might be true all the time)
			this.labMembers.add(new JLabel(""+this.listMembers.get(listMembers.size()-1).getPseudo())); // add to the arraylist of jbutton
			this.add(this.labMembers.get(this.labMembers.size()-1)); // add the new jlabel to the panel
		}

		this.inputAddMember.setText(strAddMember);
		this.revalidate();
		this.repaint();
	}
}
