package server;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Frame of the ServerApp as mark that the server is on, and for stopping it (by quitting the frame)
 *
 */
@SuppressWarnings("serial")
public class FrameServer extends JFrame implements WindowListener {
	/**
	 * The ServerApp associated with the frame
	 */
	ServerAppImpl serverAppImpl;
	/**
	 * The JLabel representing the message displayed on the frame
	 */
	JLabel message;
	
	/**
	 * Frame constructor, using the ServerApp implementation and a default message
	 * @param serverAppImpl
	 */
	public FrameServer(ServerAppImpl serverAppImpl) {
		this.setVisible(true);		
		this.serverAppImpl = serverAppImpl;
		addWindowListener(this);
		
		this.setTitle("Server App");
		
		message = new JLabel("Server for the application is currently running...");
		message.setFont(message.getFont().deriveFont(24.0f));
		this.add(message);
		
		this.pack();
		
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	/**
	 * Saving everything and closing the window and server
	 */
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
