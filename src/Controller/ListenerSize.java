package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Toolbox;
import View.MainFrame;

/**
 * Listener in the Toolbox for the size of the brush.
 */
public class ListenerSize implements ActionListener {
	/**
	 * The associated toolbox
	 */
	Toolbox toolbox;

	/**
	 * Initializes the toolbox
	 * @param toolbox the associated toolbox
	 */
	public ListenerSize(Toolbox toolbox) {
		this.toolbox = toolbox;
	}
	
	/**
	 * Upon clicking on a button, sets the brush size to the expected. (Small, Medium, Large). Refresh the display.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(MainFrame.pToolBox.getNameButtonS())) {
			toolbox.setSize(Toolbox.sizeS);
		}
		if(e.getActionCommand().equals(MainFrame.pToolBox.getNameButtonM())) {
			toolbox.setSize(Toolbox.sizeM);
		}
		if(e.getActionCommand().equals(MainFrame.pToolBox.getNameButtonL())) {
			toolbox.setSize(Toolbox.sizeL);
		}
		MainFrame.pToolBox.refresh(toolbox);
	}

}
