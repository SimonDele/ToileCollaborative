package Modele;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Modele.rmi.CanvasRMIServerImpl;

public class Group implements Serializable {
	
	private String name;
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private transient CanvasRMIServerImpl canvas;

	public Group(String name) {
		this.name = name;
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();
		try {
			canvas = new CanvasRMIServerImpl(null, name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public CanvasRMIServerImpl getCanvas() {
		return canvas;
	}
	public void loadImg() {
		try {
			this.canvas = new CanvasRMIServerImpl(null, name);		
			this.canvas.setDrawing(ImageIO.read(new File("drawings/" + name+".png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
