package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;

import Main.Main;
import View.MainFrame;
import View.MenuGroups;

public class ListenerAddGroup implements KeyListener{
	
	private JTextField inputGroupName;
	private MenuGroups menuGroups;
	
	public ListenerAddGroup(JTextField inputGroupName, MenuGroups menuGroups) {
		this.inputGroupName = inputGroupName;
		this.menuGroups = menuGroups;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

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
