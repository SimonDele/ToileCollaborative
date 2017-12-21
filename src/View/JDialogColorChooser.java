package View;

import java.awt.BorderLayout;


import javax.swing.JColorChooser;
import javax.swing.JDialog;

/**
 * Class that handles the client's choice of color upon registration. It uses a very complete class from java.swing : JColorChooser.
 */
public class JDialogColorChooser extends JDialog{
	/**
	 * The Class that displays the frame for choosing a color
	 */
	public JColorChooser jcolorchooser;
	
	/**
	 * 
	 * @param parent
	 */
	public JDialogColorChooser(JDialog parent){
		super(parent, "Connexion/Inscription", true);
		this.setTitle("Selectionne une couleur");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		jcolorchooser = new JColorChooser();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jcolorchooser, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
