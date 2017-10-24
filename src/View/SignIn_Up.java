package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignIn_Up extends JDialog {
	
	JPanel content;
	JLabel labpseudo;
	JLabel labpassword;
	JTextField pseudo;
	JTextField password;
	JButton submit;
	JButton buttonColor;
	JPanel pPseudo;
	JPanel pPassword;
	JDialog signIn_Up;
	public SignIn_Up(JFrame parent) {
		super(parent, "Sign In or Sign Up", true);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		signIn_Up = this;
		
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.setAlignmentX(LEFT_ALIGNMENT);
		content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//Pseudo
		labpseudo = new JLabel("Pseudo : ");
		pseudo = new JTextField();
		pseudo.setMaximumSize(new Dimension(100,25));
		pPseudo = new JPanel();
		pPseudo.setLayout(new BoxLayout(pPseudo, BoxLayout.LINE_AXIS));
		pPseudo.add(labpseudo);
		pPseudo.add(pseudo);
		pPseudo.setAlignmentX(LEFT_ALIGNMENT);
		//Password
		labpassword = new JLabel("Mot de passe :");
		password = new JTextField();
		password.setMaximumSize(new Dimension(100,25));
		pPassword = new JPanel();
		pPassword.setLayout(new BoxLayout(pPassword, BoxLayout.LINE_AXIS));
		pPassword.add(labpassword);
		pPassword.add(password);
		pPassword.setAlignmentX(LEFT_ALIGNMENT);
		
		buttonColor = new JButton("Couleur");
		buttonColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialogColorChooser jDialogColorChooser = new JDialogColorChooser(signIn_Up);
			}
			
		});
		
		submit = new JButton("Valider");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();		
			}
		});
		
		content.add(pPseudo);
		content.add(pPassword);
		content.add(buttonColor);
		content.add(submit);
		
		//content.setBackground(Color.black);
		//content.setMinimumSize(new Dimension(300,300));
		
		//Add everything in the JDialog
		this.getContentPane().add(content);
		this.setVisible(true);
	}
	public String[] getOutput() {
		String[] res = new String[2];
		res[0] = pseudo.getText();
		res[1] = password.getText();
		return res; 
	}
}
