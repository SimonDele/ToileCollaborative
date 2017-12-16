package server;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameServer extends JFrame implements WindowListener {
	
	ServerAppImpl serverAppImpl;
	JLabel titre;
	public FrameServer(ServerAppImpl serverAppImpl) {
		this.setVisible(true);		
		this.serverAppImpl = serverAppImpl;
		addWindowListener(this);
		
		this.setTitle("Server App");
		
		titre = new JLabel("Server for the application is currently running...");
		titre.setFont(titre.getFont().deriveFont(24.0f));
		this.add(titre);
		
		this.pack();
		
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		serverAppImpl.saveAll();
		System.out.println("window closed");
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
