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
	private Color color;
	private ArrayList<Group> groupList;
	
	private transient File fileMembers;
	private transient Toolbox toolbox;
	
	public Member(String pseudo,String password) {
		//Writing in the dedicated file the new member.
		fileMembers = new File("listeMembers.txt");
		ArrayList<Member> listMembres = this.readFileMembers(); // Since we can't append a FileOuputStream, firtly we store all its content
		//Then we rewrite it, and we add the new Member at the end
 
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			          new BufferedOutputStream(
			            new FileOutputStream(fileMembers)));
		    for (Iterator iterator = listMembres.iterator(); iterator.hasNext();) {
				Member member = (Member) iterator.next();
				oos.writeObject(member);
				
			}
		    oos.writeObject(this);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	     
	}
	public String getPseudo() {
		return pseudo;
	}
	private ArrayList<Member> readFileMembers(){
		ArrayList<Member> res = new ArrayList<Member>();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(
			          new BufferedInputStream(
			            new FileInputStream(fileMembers)));
			try {

				while(true) {
					try {
						Object obj = ois.readObject();
						res.add((Member)obj);
						System.out.println(obj);
					}catch(EOFException e) {
						ois.close();
						break;
					}

				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	
}
