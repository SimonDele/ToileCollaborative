package View;

import java.awt.BorderLayout;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.SaveOnWindowClosed;
import Modele.Member;
import Modele.Toolbox;
import Modele.rmi.CanvasRMIServerImpl;

public class MainFrame extends JFrame{
	private String title;
	//Different Panels :
	public static Menu menu;
	public static PCanva pCanva;
	private PToolBox pToolBox;
	public static CanvasRMIServerImpl canvasServer;
	
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
		    canvasServer = user.getGroupList().get(0).getCanvas();
		    menu = new Menu(user.getGroupList().get(0), user, canvasServer);
		    System.out.println("size>0");
	    }else {
	    	try {
				canvasServer = new CanvasRMIServerImpl(null, "public");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	menu = new Menu(null, user, canvasServer);
	    }
	    container.add(menu, BorderLayout.WEST); //Say it will be displayed on the left
	    
	    pCanva = new PCanva(toolbox, canvasServer);
	    container.add(pCanva, BorderLayout.CENTER);
	    
	    pToolBox = new PToolBox(toolbox);
	    container.add(pToolBox, BorderLayout.NORTH);
	    
	    this.setContentPane(container); //Finally set the container as the Panel of "MainFrame"
	    
	    this.addWindowListener(new SaveOnWindowClosed(user));
	    
	}
}
