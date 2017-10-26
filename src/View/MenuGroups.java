package View;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListenerAddGroup;
import Modele.Group;

public class MenuGroups extends JPanel{
	
	private ArrayList<Group> listGroups;
	private JTextField inputAddGroup;
	private ArrayList<JButton> buttonGroups;
	
	public MenuGroups(ArrayList<Group> listGroups) {
		
		try {
			this.listGroups = listGroups;			
		}catch(NullPointerException e) {
			this.listGroups = new ArrayList<Group>();
		}
		
		this.setSize(new Dimension(200,200));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		inputAddGroup = new JTextField("Créer un groupe");
		inputAddGroup.addKeyListener(new ListenerAddGroup(listGroups, inputAddGroup, this));
		this.add(inputAddGroup);
		
		this.buttonGroups = new ArrayList<JButton>();
		//Display all the group belonging to the user
		for (Iterator iterator = listGroups.iterator(); iterator.hasNext();) {
			Group group = (Group) iterator.next();
			this.buttonGroups.add(new JButton(""+group.getName()));		
			this.add(this.buttonGroups.get(this.buttonGroups.size()-1));
		}	
	}
	public void refreshDisplay() {
		if(this.buttonGroups.size() < this.listGroups.size()) { // Check if one label is missing (might be true all the time)
			this.buttonGroups.add(new JButton(""+this.listGroups.get(listGroups.size()-1).getName())); // add to the arraylist of jlabels
			this.add(this.buttonGroups.get(this.buttonGroups.size()-1)); // add the new jlabel to the panel
		}

		this.revalidate();
		this.repaint();
	}
}
