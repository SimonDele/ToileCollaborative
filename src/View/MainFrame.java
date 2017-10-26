package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.Member;
import Modele.Toolbox;

public class MainFrame extends JFrame{
	private String title;
	//Different Panels :
	private Menu menu;
	private PCanva pCanva;
	private PToolBox pToolBox;
	
	public MainFrame(Toolbox toolbox, Member user) {
		//Things related to JFrame properties
		title = "CanvUs";
		this.setTitle(title);
		this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(500, 500);
	    
	    //Containt
	    JPanel container = new JPanel(); //will contain all other JPanel
	    container.setLayout(new BorderLayout()); // BorderLayout enables to have 4 Panels define by WEST, CENTER, EAST, NORTH and SOUTH 
	    
	    if(user.getGroupList().size() > 0) {
		    menu = new Menu(user.getGroupList().get(0), user);
	    }else {
	    	menu = new Menu(null, user);
	    }

	    container.add(menu, BorderLayout.WEST); //Say it will be displayed on the left
	    
	    pCanva = new PCanva(toolbox);
	    container.add(pCanva, BorderLayout.CENTER);
	    
	    pToolBox = new PToolBox(toolbox);
	    container.add(pToolBox, BorderLayout.NORTH);
	    
	    this.setContentPane(container); //Finally set the container as the Panel of "MainFrame"
	    
	}
}
