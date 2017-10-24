package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.Toolbox;

public class MainFrame extends JFrame{
	private String title;
	//Different Panels :
	private Menu menu;
	private PCanva pCanva;
	private PToolBox pToolBox;
	
	public MainFrame(Toolbox toolbox) {
		//Things related to JFrame properties
		title = "CanvUs";
		this.setTitle(title);
		this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	    
	    //Containt
	    JPanel container = new JPanel(); //will contain all other JPanel
	    container.setLayout(new BorderLayout()); // BorderLayout enables to have 4 Panels define by WEST, CENTER, EAST, NORTH and SOUTH 
	    
	    menu = new Menu();
	    container.add(menu, BorderLayout.WEST); //Say it will be displayed on the left
	    
	    pCanva = new PCanva();
	    container.add(pCanva, BorderLayout.CENTER);
	    
	    pToolBox = new PToolBox(toolbox);
	    container.add(pToolBox, BorderLayout.NORTH);
	    
	    this.setContentPane(container); //Finally set the container as the Panel of "MainFrame"
	    
	}
}
