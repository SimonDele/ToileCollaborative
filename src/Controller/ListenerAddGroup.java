package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import Main.Main;
import View.MainFrame;
import View.MenuGroups;

/**
 * Listener to add a new Group; keeps track of the String the user inputs.
 */
public class ListenerAddGroup implements KeyListener{
	/**
	 * The Field where the user can input the name of the Group he wants to add.
	 */
	private JTextField inputGroupName;
	/**
	 * The menu of groups, pointer to MainFrame's
	 */
	private MenuGroups menuGroups;
	
	/**
	 * Constructor just taking the values and assigning them to the attributes
	 * @param inputGroupName for the attribute of the same name
	 * @param menuGroups the for the attribute of the same name
	 */
	public ListenerAddGroup(JTextField inputGroupName, MenuGroups menuGroups) {
		this.inputGroupName = inputGroupName;
		this.menuGroups = menuGroups;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

	/**
	 * Upon releasing the enter key, this is called : check whether the Group already exists, else add it through Main's distant object ServerApp.
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) {// Enter key
			// TODO ? lookup the latest ServerApp ?
			// Now let's check if it's not already a group
			String newGroupName = this.inputGroupName.getText();
			try {
				if (Main.serverApp.hasGroup(newGroupName)) {
					System.out.println("Ce groupe existe déjà.");
				} else {
					System.out.println("Nom libre; groupe créé");
					Main.USER.createNewGroup(this.inputGroupName.getText());
					this.menuGroups.refreshDisplay();
					MainFrame.pCanva.repaint();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
