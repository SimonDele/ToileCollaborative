package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Main;
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
		MainFrame.currentGroup = this.newGroup;
		Main.USER.setCurrentCanvas(this.newGroup.getCanvas());
		Main.USER.getCurrentCanvas().setPCanvas(MainFrame.pCanva);
		MainFrame.pCanva.switchCanvas();
		System.out.println(this.newGroup.getMemberList());
		Menu.menuMembers.setListMembers(this.newGroup.getMemberList());
		System.out.println("Switch group");
	}

}
