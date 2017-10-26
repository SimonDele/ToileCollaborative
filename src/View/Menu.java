package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modele.Group;
import Modele.Member;

public class Menu extends JPanel {
	MenuMembers menuMembers;
	MenuGroups menuGroups;

	public Menu(Group group, Member member) {
		menuMembers = new MenuMembers(group);
		menuGroups = new MenuGroups(member.getGroupList());
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JScrollPane(menuGroups));
		this.add(new JScrollPane(menuMembers));
	}
}
