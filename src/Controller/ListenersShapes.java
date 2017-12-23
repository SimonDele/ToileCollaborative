package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modele.Toolbox;

/**
 * Not implemented : the Listener for the different shapes the user might want to use.
 */
public class ListenersShapes implements ActionListener {
	/**
	 * Among the list of possible (theoretical) shapes, the circle button.
	 */
	JButton circle;
	/**
	 * The associated toolbox on which the buttons are displayed
	 */
	Toolbox toolbox;
	
	/**
	 * Constructor to initialize the attributes
	 * @param toolbox for the attribute of the same name
	 * @param circle for the attribute of the same name
	 */
	public ListenersShapes(Toolbox toolbox, JButton circle) {
		this.circle = circle;
		this.toolbox = toolbox;
	}
	
	/**
	 * (Useless for now) set the right shape to selected, so the right message is sent upon the drawing order
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(circle == e.getSource()) {
			toolbox.setShapeSelected("CIRCLE");
		}
	}
	
}
