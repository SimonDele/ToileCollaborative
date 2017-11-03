package Modele;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Member implements Serializable {
	
	private String pseudo;
	private String password;
	private Color color; // Not used yet
	private ArrayList<Group> groupList;
	
	private static File fileMembers;
	private transient Toolbox toolbox;
	
	public Member() {	
		fileMembers = new File("listeMembers.txt");
		groupList = new ArrayList<Group>(); // Creation of an empty groupList
	}
	
	public Member(String pseudo,String password) {
		groupList = new ArrayList<Group>(); // Creation of an empty groupList
		//Writing in the dedicated file the new member.
		this.pseudo = pseudo;
		this.password = password;
		
		fileMembers = new File("listeMembers.txt");
		
		ArrayList<Member> listMembers = readFileMembers(); // Since we can't append a FileOuputStream, firtly we store all its content
		//Then we rewrite it, and we add the new Member at the end
		listMembers.add(this);
		this.writeFileMembers(listMembers);
	}
	public String getPseudo() {
		return pseudo;
	}
	public ArrayList<Group> getGroupList() {
		return groupList;
	}
	public static Member getMember(String pseudo) {
		ArrayList<Member> listMembers = readFileMembers();
		for (Iterator iterator = listMembers.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			if(member.getPseudo().equals(pseudo)) {
				return member;
			}
		}
		return null; //If the member doesn't exist
	}
	public Member connection(String pseudo, String password) {
		ArrayList<Member> listMember = readFileMembers();
		Iterator iterator = listMember.iterator();
		boolean found = false;
		while(!found && iterator.hasNext()) {

			Member member = (Member) iterator.next();
			if((password.equals(member.password)) && (pseudo.equals(member.pseudo))) {
				found = true;
				for(int i=0; i<member.groupList.size(); i++) {
					member.groupList.get(i).loadImg();
				}
				return member;
			}
		}
		return null;
	}
	private static void writeFileMembers(ArrayList<Member> listMembers) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			          new BufferedOutputStream(
			            new FileOutputStream(fileMembers)));
		    for (int i=0; i<listMembers.size();i++) {
				Member member = listMembers.get(i);
				oos.writeObject(member);	
			}
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	          		
	}
	private static ArrayList<Member> readFileMembers(){
		ArrayList<Member> res = new ArrayList<Member>();
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(fileMembers)));
				while(true) {
					try {
						Member obj = (Member)ois.readObject();
						res.add(obj);
					}catch(EOFException | ClassNotFoundException e) { // Catch if we have reached the end of the file
						ois.close();
						break;
					}

				}
				
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return res;
	}
	
	public void createNewGroup(String name) {
		this.groupList.add(new Group(name));
		ArrayList<Member> members = readFileMembers(); // Get the list of all the members
		
		for(int i=0; i<members.size();i++) { //Modify the current User
			if(members.get(i).pseudo.equals(this.pseudo) && members.get(i).password.equals(this.password)) {
				members.set(i,this);
			}
		}
		
		writeFileMembers(members); //And rewrite everything
	}
	
	public void saveBeforeExit() {
		ArrayList<Member> member = readFileMembers(); // Get the list of all the members
		writeFileMembers(member); //And rewrite it
		for(int i=0; i<this.groupList.size(); i++) {
			try {
				this.groupList.get(i).getCanvas().save(this.groupList.get(i).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
