package View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListenerAddGroup;
import Controller.ListenerSwitchGroup;
import Main.Main;
import Modele.Group;

/**
 * Panel that holds the visual information about the Groups of the Member the client is connected with. This covers group adding and switching.
 */
@SuppressWarnings("serial")
public class MenuGroups extends JPanel{
	/**
	 * The list of Groups to display, identical to the Member's the client is currently connected with.
	 */
	private ArrayList<Group> listGroups;
	/**
	 * The field where the client can input the name of the Group he wishes to create.
	 */
	private JTextField inputAddGroup;
	/**
	 * Each button associated with the list of Groups.
	 */
	private ArrayList<JButton> buttonGroups;
	/**
	 * The string to display at the location where the client will be able to add a new Group.
	 */
	private String strAddGroup;
	
	/**
	 * Initializes the list of Groups to display, and every button (and associated Listener) and String mentionned in the field, as well as their color and position.
	 * @param listGroups
	 */
	public MenuGroups(ArrayList<Group> listGroups) {
		try {
			this.listGroups = listGroups;			
		}catch(NullPointerException e) {
			this.listGroups = new ArrayList<Group>();
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.strAddGroup ="+ New Group" ;
		inputAddGroup = new JTextField(this.strAddGroup);
		inputAddGroup.addKeyListener(new ListenerAddGroup(inputAddGroup, this));
		this.add(inputAddGroup);
		inputAddGroup.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputAddGroup.getMinimumSize().height));
		
		this.buttonGroups = new ArrayList<JButton>();
		//Display all the groups belonging to the user
		for (int i=0 ; i<listGroups.size(); i++) {
			Group group = this.listGroups.get(i);
			this.buttonGroups.add(new JButton(""+group.getName()));
			this.buttonGroups.get(this.buttonGroups.size()-1).addActionListener(new ListenerSwitchGroup(this.listGroups.get(i)));
			this.add(this.buttonGroups.get(this.buttonGroups.size()-1));
		}	
		
		this.setBackground(Color.gray);
		this.refresh();
	}
	
	/**
	 * Completes the list of buttons based on the current list of Groups 
	 */
	public void refreshDisplay() {
		if(this.buttonGroups.size() < this.listGroups.size()) { // Check if one label is missing (might be true all the time)
			this.buttonGroups.add(new JButton(""+this.listGroups.get(listGroups.size()-1).getName())); // add to the arraylist of jbutton
			this.buttonGroups.get(this.buttonGroups.size()-1).addActionListener(new ListenerSwitchGroup(this.listGroups.get(this.listGroups.size()-1)));
			this.add(this.buttonGroups.get(this.buttonGroups.size()-1)); // add the new jlabel to the panel
		}

		this.revalidate();
		this.repaint();
		this.refresh();
	}
	
	/**
	 * Handles the coloration of the button depending on whether it's selected. Resets the String addGroup
	 */
	public void refresh() {
		for (JButton jbuttonI : this.buttonGroups) {
			if ((jbuttonI.getText()).equals(Main.USER.getCurrentGroup().getName())) {
				MainFrame.setSelected(jbuttonI);
			} else {
				MainFrame.setUnselected(jbuttonI);
			}
		}
		this.inputAddGroup.setText(strAddGroup);
		
	}
}
