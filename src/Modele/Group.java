package Modele;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private Canvas canvas;

	public Group(String name) {
		this.name = name;
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();
		canvas = new Canvas();
	}

	public ArrayList<Member> getMemberList(){
		return this.memberList;
	}
	public String getName() {
		return name;
	}
	
	public void addMember(Member toAdd) {
		memberList.add(toAdd);
	}
}
