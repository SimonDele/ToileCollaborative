package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Main.Main;
import Modele.Member;
import server.ServerApp;

public class ListenerSignIn_Up implements ActionListener {
	JButton signUp;
	JTextField pseudo, password;
	JDialog jDialog;
	
	public ListenerSignIn_Up(JTextField pseudo, JTextField password, JButton signUp, JDialog jDialog) {
		this.signUp = signUp;
		this.password = password;
		this.pseudo = pseudo;
		this.jDialog = jDialog;	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.signUp.isEnabled() == false) { //If it is a sign Up
			Member member = new Member(pseudo.getText(), password.getText());
			Main.USER = member;
			System.out.println("Sign Up");
		}else {// it is a sign In
			Member member = new Member();
			member = member.connection(pseudo.getText(), password.getText());
			if(member != null) {
				System.out.println("Sign In");
				Main.USER = member;
				this.jDialog.dispose();
			}else {
				System.out.println("No yet Sign Up");
			}

		}
		
	}

}
