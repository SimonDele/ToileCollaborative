package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modele.Group;

public class Menu extends JPanel {
	MenuMembers menuMembers;
	MenuGroups menuGroups;
	
	public Menu(Group group) {
		menuMembers = new MenuMembers(group);
		menuGroups = new MenuGroups();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JScrollPane(menuGroups));
		this.add(new JScrollPane(menuMembers));
	}
}
