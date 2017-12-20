package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.ListenerSize;
import Controller.ListenersShapes;
import Modele.Toolbox;


public class PToolBox extends JPanel {

	JButton circle;
	JButton sizeSmall,sizeNormal,sizeLarge;

	ListenersShapes listenersToolBox;
	ListenerSize listenerSize;
	String nameButtonS;
	String nameButtonM;
	String nameButtonL;

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

	public String getNameButtonS() {
		return nameButtonS;
	}

	public void setNameButtonS(String nameButtonS) {
		this.nameButtonS = nameButtonS;
	}

	public String getNameButtonM() {
		return nameButtonM;
	}

	public void setNameButtonM(String nameButtonM) {
		this.nameButtonM = nameButtonM;
	}

	public String getNameButtonL() {
		return nameButtonL;
	}

	public void setNameButtonL(String nameButtonL) {
		this.nameButtonL = nameButtonL;
	}

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

