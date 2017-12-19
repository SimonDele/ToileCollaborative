package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Toolbox;
import View.MainFrame;

public class ListenerSize implements ActionListener {
	Toolbox toolbox;

	public ListenerSize(Toolbox toolbox) {
		this.toolbox = toolbox;
	}
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
