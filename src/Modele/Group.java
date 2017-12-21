package Modele;

import java.io.File;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Class that represents a group of Members and the Canvas they draw on. A Group is named.
 *
 */
@SuppressWarnings("serial")
public class Group implements Serializable {
	/**
	 * Group's name, set by the creator
	 */
	private String name;
	/**
	 * Arraylist of all the Group's Members
	 */
	private ArrayList<Member> memberList;
	/**
	 * (TODO) ArrayList of boolean parallel to memberList, to determine the Group's administrators (specific methods) 
	 */
	private ArrayList<Boolean> adminList;
	/**
	 * The Group's Canvas on which the members draw
	 */
	private Canvas canvas;
	
	/**
	 * Group's constructor, initializing the lists and name given by creator
	 * @param name the name chosen by the creator
	 */
	public Group(String name) {
		this.name = name;
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();


		canvas = new Canvas();
	}

	/**
	 * Getter for the memberlist
	 * @return the Group's list of members
	 */
	public ArrayList<Member> getMemberList(){
		return this.memberList;
	}
	
	/**
	 * Getter for the Group's name
	 * @return the name of the Group
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to add a member to the Group
	 * @param toAdd the member to add to the group
	 */
	public void addMember(Member toAdd) {
		memberList.add(toAdd); // Add the toAdd member to the list of members of this group
		toAdd.createNewGroup(this.getName()); // Add this group to the member added		
	}
	
	/**
	 * Getter for the Group's Canvas
	 * @return the Group's Canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Reads the Canvas' file and sets it as the Group's Canvas
	 */
	public void loadImg() {
		this.canvas = new Canvas();
		try {
			this.canvas.setDrawing(Converter.toIcon(ImageIO.read(new File("drawings/" + name+".png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tells whether a member is part of the group
	 * @param nameMember the name of the member to check
	 * @return whether the member is part of the group as a boolean
	 */
	public boolean hasMember(Member memberToCheck) {
		boolean is = false;
		for (Member member : memberList) {
			if (member.getPseudo().equals(memberToCheck.getPseudo())) {
				is = true;
				break;
			}
		}
		return is;
	}
	
	/**
	 * Equivalent to an "equals" method using the fact that a Group's name is unique
	 * @param group the Group that may be equal to this one
	 * @return whether they are the same group (same name)
	 */
	public boolean is(Group group) {
		return this.name.equals(group.getName());
	}
}
