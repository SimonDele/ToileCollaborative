package View;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Modele.Member;

public class MenuMembers extends JScrollPane{
	
	private ArrayList<Member> listMembers;
	
	public MenuMembers() {}
	public MenuMembers(ArrayList<Member> listMembers) {
		this.listMembers = listMembers;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		for (Iterator iterator = listMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			this.add(new JLabel(""+member.getPseudo()));		
		}
	}
}
