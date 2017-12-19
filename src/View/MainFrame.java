package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.SaveOnWindowClosed;
import Modele.Canvas;
import Modele.Group;
import Modele.Member;

public class MainFrame extends JFrame{
	private String title;
	//Different Panels :
	public static Menu menu;
	public static PCanva pCanva;
	public static PToolBox pToolBox;
	public static Canvas canvas;
	public static Group currentGroup;
	
	public MainFrame(Member user) {
		//Things related to JFrame properties
		title = "Canv'Us";
		this.setTitle(title);
		this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    
	    //Content
	    JPanel container = new JPanel(); //will contain all other JPanel
	    container.setLayout(new BorderLayout()); // BorderLayout enables to have 4 Panels define by WEST, CENTER, EAST, NORTH and SOUTH 
    
//	    user.setCurrentCanvas(user.getGroupList().get(0).getCanvas());
	    MainFrame.currentGroup = user.getCurrentGroup();
	    MainFrame.canvas = currentGroup.getCanvas();
	    menu = new Menu(MainFrame.currentGroup, user, canvas);

	    container.add(menu, BorderLayout.WEST); //Say it will be displayed on the left

	    pCanva = new PCanva(user.getToolbox());
	    container.add(pCanva, BorderLayout.CENTER);
	    
	    // North panel with welcome and toolbox
	    JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		
	    JLabel titlelabel = new JLabel("Canv'Us  -  " + user.getPseudo(),JLabel.CENTER);
	    Font f = new Font("Arial", Font.BOLD, 36);
	    titlelabel.setFont(f);
	    titlelabel.setOpaque(true);
	    titlelabel.setBackground(Color.gray);

	    panelNorth.add(titlelabel, BorderLayout.NORTH);

	    pToolBox = new PToolBox(user.getToolbox());
	    panelNorth.add(pToolBox, BorderLayout.CENTER);

	    container.add(panelNorth, BorderLayout.NORTH);
	    
	    // south panel for text label
	    JLabel colabel = new JLabel("CanvUs©Corporate",JLabel.RIGHT);
	    Font fo = new Font("Arial", Font.BOLD, 15);
	    colabel.setFont(fo);
	    colabel.setOpaque(true);
	    colabel.setBackground(Color.gray);
	    container.add(colabel, BorderLayout.SOUTH);

	    // display
	    this.setContentPane(container); //Finally set the container as the Panel of "MainFrame"
	    System.out.println(Canvas.height+panelNorth.getHeight());
	    this.addWindowListener(new SaveOnWindowClosed(user));
	    this.pack();
	}
	
	public static void setSelected(JButton b) {

		b.setBackground(Color.black);
		b.setForeground(Color.gray);
		b.setFocusPainted(false);

	}

	public static void setUnselected(JButton b) {

		b.setBackground(Color.gray);
		b.setForeground(Color.black);
		b.setFocusPainted(false);

	}
}
