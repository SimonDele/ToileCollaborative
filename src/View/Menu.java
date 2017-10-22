package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Menu extends JPanel {
	MenuMembers menuMembers;
	MenuGroups menuGroups;
	
	public Menu() {
		menuMembers = new MenuMembers();
		menuGroups = new MenuGroups();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(menuGroups);
		this.add(menuMembers);
	}
}
