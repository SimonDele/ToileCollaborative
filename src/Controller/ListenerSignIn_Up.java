package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import Main.Main;
import Modele.Member;

public class ListenerSignIn_Up implements ActionListener {
	JTextField pseudo, password;
	JDialog jDialog;
	JButton submit;
	String textSignUp;
	
	public ListenerSignIn_Up(JTextField pseudo, JTextField password,JButton submit, JDialog jDialog, String textSignUp) {
		this.password = password;
		this.pseudo = pseudo;
		this.submit = submit;
		this.jDialog = jDialog;
		this.textSignUp = textSignUp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JButton)e.getSource()).getActionCommand() );
		if(((JButton)e.getSource()).getActionCommand() == textSignUp) { //If it is a sign Up
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
