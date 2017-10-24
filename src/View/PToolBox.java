package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.ListenersShapes;
import Modele.Toolbox;

public class PToolBox extends JPanel {
	
	JButton circle;
	JButton sizeSmall;
	
	ListenersShapes listenersToolBox;
	public PToolBox(Toolbox toolbox) {
		circle = new JButton(new ImageIcon(this.getClass().getResource("ressources/circle.png")));
		sizeSmall = new JButton("small");
		
		listenersToolBox = new ListenersShapes(toolbox, circle);
		circle.addActionListener(listenersToolBox);

		
		this.add(circle);
		this.add(sizeSmall);
	}
}
