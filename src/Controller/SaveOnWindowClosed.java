package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Modele.Member;

public class SaveOnWindowClosed implements WindowListener {
	Member user;
	public SaveOnWindowClosed(Member member) {
		this.user = member;
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {

		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		user.saveBeforeExit();
		System.out.println("Saved");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
