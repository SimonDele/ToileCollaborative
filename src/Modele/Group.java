package Modele;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import server.ServerGroupImpl;

public class Group implements Serializable {
	
	private String name;
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private transient Canvas canvas;

	public Group(String name) {
		this.name = name;
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();

		try {
			ServerGroupImpl serverGroup = new ServerGroupImpl(this, null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		canvas = new Canvas(name);
	}

	public ArrayList<Member> getMemberList(){
		return this.memberList;
	}
	public String getName() {
		return name;
	}
	
	public void addMember(Member toAdd) {
		memberList.add(toAdd); // Add the toAdd member to the list of members of this group
		toAdd.createNewGroup(this.getName()); // Add this group to the member added		
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public void loadImg() {
		this.canvas = new Canvas(this.name);		
		//this.canvas.setDrawing(ImageIO.read(new File("drawings/" + name+".png")));
	}
}
