package Modele;

import java.util.ArrayList;

public class Group {
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private Canvas canvas;

	public Group() {
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();
		canvas = new Canvas();
	}
	public void addMember(Member toAdd) {
		memberList.add(toAdd);
	}
}
