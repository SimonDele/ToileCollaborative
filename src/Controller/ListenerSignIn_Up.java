package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Modele.Member;
import Main.Main;

public class ListenerSignIn_Up implements ActionListener {
	JButton signUp;
	JTextField pseudo, password;
	
	public ListenerSignIn_Up(JTextField pseudo, JTextField password, JButton signUp) {
		this.signUp = signUp;
		this.password = password;
		this.pseudo = pseudo;
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
			System.out.println("Sign In");
			Main.USER = member;
		}
		
	}

}
