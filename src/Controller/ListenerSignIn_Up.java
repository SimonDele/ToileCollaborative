package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

import Main.Main;
import Modele.Member;
import View.JDialogColorChooser;
import View.SignIn_Up;

public class ListenerSignIn_Up implements ActionListener {
	JButton signUp;
	JTextField pseudo, password;
	SignIn_Up signIn_Up;
	JDialogColorChooser jdialogcolorchooser;
	
	public ListenerSignIn_Up(JTextField pseudo, JTextField password, JButton signUp, SignIn_Up signIn_Up, JDialogColorChooser jdialogcolorchooser) {
		this.signUp = signUp;
		this.password = password;
		this.pseudo = pseudo;
		this.signIn_Up = signIn_Up;	
		this.jdialogcolorchooser = jdialogcolorchooser;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String iPAdress = null;
		try {
			System.out.println("IP Adress is : " + InetAddress.getLocalHost().getHostAddress());
			iPAdress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		if(this.signUp.isEnabled() == false) { //If it is a sign Up
			System.out.println("Sign Up");
			
			try {
				signIn_Up.setMember(Main.serverApp.register(pseudo.getText(), password.getText(), signIn_Up.getColor(), iPAdress));
				this.signIn_Up.dispose();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}else {// it is a sign In
			
			try {
				Member member = Main.serverApp.connection(pseudo.getText(), password.getText(), iPAdress);
				if(member != null) {
					System.out.println("Sign In");
					signIn_Up.setMember(member);
					this.signIn_Up.dispose();
				}else {
					System.out.println("No yet Sign Up");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
