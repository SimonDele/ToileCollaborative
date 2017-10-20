package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignIn_Up extends JDialog {
	
	JPanel content;
	JTextField pseudo;
	JTextField password;
	
	public SignIn_Up(JFrame parent) {
		super(parent, "Sign In or Sign Up", true);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		
		content = new JPanel();
		
		pseudo = new JTextField("Pseudo");
		pseudo.setPreferredSize(new Dimension(100,25));
		
		password = new JTextField("Mot de passe");
		password.setPreferredSize(new Dimension(100,25));
		
		content.add(pseudo);
		content.add(password);
		
		content.setBackground(Color.black);
		content.setMinimumSize(new Dimension(300,300));
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
