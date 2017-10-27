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
	
	private File fileMembers;
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
		
		ArrayList<Member> listMembers = this.readFileMembers(); // Since we can't append a FileOuputStream, firtly we store all its content
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
	public Member connection(String pseudo, String password) {
		ArrayList<Member> listMember = readFileMembers();
		Iterator iterator = listMember.iterator();
		boolean found = false;
		while(!found && iterator.hasNext()) {

			Member member = (Member) iterator.next();
			if((password.equals(member.password)) && (pseudo.equals(member.pseudo))) {
				System.out.println("ok");
				found = true;
				System.out.println("zae" + member.groupList.size());
				return member;
			}
		}
		return null;
	}
	private void writeFileMembers(ArrayList<Member> listMembers) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			          new BufferedOutputStream(
			            new FileOutputStream(fileMembers)));
		    for (Iterator iterator = listMembers.iterator(); iterator.hasNext();) {
				Member member = (Member) iterator.next();
				oos.writeObject(member);
				
			}
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	          		
	}
	private ArrayList<Member> readFileMembers(){
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
						System.out.println(obj);
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
		ArrayList<Member> temp = this.readFileMembers(); // Get the list of all the members
		for (int i = 0; i < temp.size(); i++) {
			if(temp.get(i).getPseudo().equals(this.pseudo) && temp.get(i).password.equals(this.password)) { //Look for the user in the list
				System.out.println("fdvgdfsq"+ this.groupList.size());
				temp.set(i, this); //And replace it with its new group
			}
		}
		this.writeFileMembers(temp);
	}

	
}
