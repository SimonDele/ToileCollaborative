package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import Modele.Group;
import View.MenuGroups;

public class ListenerAddGroup implements KeyListener{
	
	private ArrayList<Group> listGroups;
	private JTextField inputGroupName;
	private MenuGroups menuGroups;
	
	public ListenerAddGroup(ArrayList<Group> listGroups,JTextField inputGroupName, MenuGroups menuGroups) {
		this.listGroups = listGroups;
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
			this.listGroups.add(new Group(inputGroupName.getText()));
			this.menuGroups.refreshDisplay();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
