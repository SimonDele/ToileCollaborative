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

/**
 * Panel that holds the visual information about the Members of the Group the client is currently on. This covers member adding. (not functional)
 */
@SuppressWarnings("serial")
public class MenuMembers extends JPanel{
	/**
	 * The list of Members to display, identical to the Group's the client is currently on.
	 */
	private ArrayList<Member> listMembers;
	/**
	 * The field where the client can input the name of the Member he wishes to add to his current Group.
	 */
	private JTextField inputAddMember;
	/**
	 * Each label associated with each Member of the Group.
	 */
	private ArrayList<JLabel> labMembers;
	/**
	 * The string to display at the location where the client will be able to add a new Member to his Group.
	 */
	private String strAddMember;
	
	/**
	 * Initializes the list of Members and their labels, as well as the color and location of the different fields. Also initializes the Listener and the string for the field to add a Member.
	 * @param group the group fom which to extract the Members.
	 */
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
	
	/**
	 * Setter for the list of Members, then refreshes the display.
	 * @param listMembers the new list 
	 */
	public void setListMembers(ArrayList<Member> listMembers) {
		this.listMembers = listMembers;
		this.refreshDisplay();
	}
	/**
	 * Getter for the String to indicate where to add a Member.
	 * @return the string to indicate where to add a member
	 */
	public String getStrAddMember() {
		return strAddMember;
	}
	/**
	 * Setter for the String to indicate where to add a Member
	 * @param sAddMember the new String
	 */
	public void setStrAddMember(String sAddMember) {
		this.strAddMember = sAddMember;
	}

	/**
	 * Method to refresh the display based on the current list of Groups. Displays the right labels for every Member.
	 */
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
	
	/**
	 * Adds a new Member to the list and to the display upon the adding of a Member
	 */
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
