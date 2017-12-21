package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modele.Canvas;
import Modele.Group;
import Modele.Member;

/**
 * The Panel class that holds both the menu of Members and the menu of Groups.
 */
public class Menu extends JPanel {
	/**
	 * The menu of Members that will be set under the Groups'. Will enable the client to see the current Members of his Group or add a new one.
	 */
	public static MenuMembers menuMembers;
	/**
	 * The menu of Groups that will be above the Members'. Will enable the client to see and switch between his Groups or add a new one.
	 */
	public static MenuGroups menuGroups;

	/**
	 * Initializes both menuGroup and menuMembers and sets them on this Panel.
	 * @param group The group sent to the Constructor of menuMembers to set their value
	 * @param member The member the client is using, used for initializing the Groups the client can see.
	 */
	public Menu(Group group, Member member) {
		menuMembers = new MenuMembers(group);
		menuGroups = new MenuGroups(member.getGroupList());
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JScrollPane(menuGroups));
		this.add(new JScrollPane(menuMembers));
	}
}
