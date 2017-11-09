package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Canvas;
import Modele.Group;
import View.MainFrame;
import View.Menu;

public class ListenerSwitchGroup implements ActionListener {
/*
 * This listener is call when we want to switch from one group to another
 * It will change : 
 * 1) the current canvas 
 * 2) the list of members

 * 2) Not yet implemented
 */
	Canvas canvas;
	Group newGroup;
	
	public ListenerSwitchGroup(Canvas canvas, Group group) {
		this.canvas = canvas;
		this.newGroup = group;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.canvas = this.newGroup.getCanvas();
		MainFrame.pCanva.repaint();
		System.out.println(this.newGroup.getMemberList());
		Menu.menuMembers.setListMembers(this.newGroup.getMemberList());
		System.out.println("Switch group");
	}

}
