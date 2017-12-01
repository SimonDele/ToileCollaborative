package Modele;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Modele.rmi.CanvasRMIServer;
import Modele.rmi.CanvasRMIServerImpl;
public class Group implements Serializable {
	
	private String name;
	private ArrayList<Member> memberList;
	private ArrayList<Boolean> adminList;
	private transient Canvas canvas;

	public Group(String name) {
		this.name = name;
		memberList = new ArrayList<Member>();
		adminList = new ArrayList<Boolean>();

		canvas = new Canvas(name, true);

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
		System.out.println("look up registry : " + name);
		this.canvas = new Canvas(this.name, false);
		try {
			try {
				this.canvas.setRMIServer((CanvasRMIServer)LocateRegistry.getRegistry().lookup(name));
				this.canvas.canvasServer.addUser(canvas);
				System.out.println("Server found");
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("not found in registry");
				this.canvas.setRMIServer(new CanvasRMIServerImpl(null, name));	
				this.canvas.canvasServer.addUser(canvas);
			}
			System.out.println(this.canvas.canvasServer);
			this.canvas.setDrawing(ImageIO.read(new File("drawings/" + name+".png")));
		} catch (IOException e) {
			System.out.println("Connect exception");
			//e.printStackTrace();
			try {
				this.canvas.setRMIServer(new CanvasRMIServerImpl(null, name));
				this.canvas.canvasServer.addUser(canvas);
				this.canvas.setDrawing(ImageIO.read(new File("drawings/" + name+".png")));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
