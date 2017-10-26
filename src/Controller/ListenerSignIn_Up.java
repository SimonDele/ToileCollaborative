package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Modele.Member;

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
		}
		
	}

}
