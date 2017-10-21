package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignIn_Up extends JDialog {
	
	JPanel content;
	JTextField pseudo;
	JTextField password;
	JButton submit;
	
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
		
		submit = new JButton("Valider");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();		
			}
		});
		
		content.add(pseudo);
		content.add(password);
		content.add(submit);
		
		content.setBackground(Color.black);
		content.setMinimumSize(new Dimension(300,300));
		
		//Add everything in the JDialog
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setVisible(true);
	}
	public String[] getOutput() {
		String[] res = new String[2];
		res[0] = pseudo.getText();
		res[1] = password.getText();
		return res; 
	}
}
