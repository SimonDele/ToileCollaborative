package Modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
	
	private String name;
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private transient Canvas canvas;

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
	public Canvas getCanvas() {
		return canvas;
	}
}
