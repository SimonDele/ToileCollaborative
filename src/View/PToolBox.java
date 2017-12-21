package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.ListenerSize;
import Controller.ListenersShapes;
import Modele.Toolbox;

/**
 * The Panel that displays the Member's Toolbox; hence manages all its buttons.
 */
@SuppressWarnings("serial")
public class PToolBox extends JPanel {
	/**
	 * Shape button : circle (not functional)
	 */
	JButton circle;
	/**
	 * Buttons for the choice of the brush's size
	 */
	JButton sizeSmall,sizeNormal,sizeLarge;
	/**
	 * Listener for the shapes of the Toolbox (not functional)
	 */
	ListenersShapes listenersToolBox;
	/**
	 * Listener associated with the buttons for the choice of the brush's size.
	 */
	ListenerSize listenerSize;
	/**
	 * String associated with the Small size Button
	 */
	String nameButtonS;
	/**
	 * String associated with the Medium size Button
	 */
	String nameButtonM;
	/**
	 * String associated with the Large size Button
	 */
	String nameButtonL;

	/**
	 * Constructor to initalize every field, the listeners associated with every button of the Toolbox and their position on the Panel.
	 * @param toolbox
	 */
	public PToolBox(Toolbox toolbox) {
		
		this.nameButtonS = "Small";
		this.nameButtonM = "Medium";
		this.nameButtonL = "Large";

		// Creation of the different button
		circle = new JButton("O"); //new ImageIcon(this.getClass().getResource("ressources/circle.png")));
		sizeSmall = new JButton(this.nameButtonS);
		sizeNormal = new JButton(this.nameButtonM);
		sizeLarge = new JButton(this.nameButtonL);

		// Initialization of the Listener
		listenersToolBox = new ListenersShapes(toolbox, circle);
		circle.addActionListener(listenersToolBox);

		listenerSize = new ListenerSize(toolbox);
		sizeSmall.addActionListener(listenerSize);
		sizeNormal.addActionListener(listenerSize);
		sizeLarge.addActionListener(listenerSize);

		// Initialization Design
		MainFrame.setSelected(sizeNormal);
		MainFrame.setSelected(circle);
		MainFrame.setUnselected(sizeSmall);
		MainFrame.setUnselected(sizeLarge);

		this.setBackground(Color.gray);

		// Add Button on the Panel
		this.add(circle);
		this.add(sizeSmall);
		this.add(sizeNormal);
		this.add(sizeLarge);
	}

	/**
	 * Getter for the name of the "small" button
	 * @return the name of the "small" button
	 */
	public String getNameButtonS() {
		return nameButtonS;
	}
	/**
	 * Getter for the name of the "medium" button
	 * @return the name of the "medium" button
	 */
	public String getNameButtonM() {
		return nameButtonM;
	}
	/**
	 * Getter for the name of the "large" button
	 * @return the name of the "large" button
	 */
	public String getNameButtonL() {
		return nameButtonL;
	}

	/**
	 * Method to handle the coloring of the size buttons : black if selected, else gray
	 * @param toolbox
	 */
	public void refresh(Toolbox toolbox) {
		if (toolbox.getSize() == Toolbox.sizeM) {
			MainFrame.setSelected(sizeNormal);
			MainFrame.setUnselected(sizeSmall);
			MainFrame.setUnselected(sizeLarge);
		} else 	if (toolbox.getSize() == Toolbox.sizeS) {
			MainFrame.setSelected(sizeSmall);
			MainFrame.setUnselected(sizeNormal);
			MainFrame.setUnselected(sizeLarge);
		} else if (toolbox.getSize() == Toolbox.sizeL) {
			MainFrame.setSelected(sizeLarge);
			MainFrame.setUnselected(sizeNormal);
			MainFrame.setUnselected(sizeSmall);
		}
	}
}

