package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import Modele.Group;

public class ListenerAddMember implements KeyListener{
	private Group group;
	private JTextField inputMember;
	public ListenerAddMember(Group group, JTextField inputMember) {
		this.group = group;
		this.inputMember = inputMember;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10) { //Enter key
			//group.addMember(inputMember.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
