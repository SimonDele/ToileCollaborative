package Main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Modele.Member;
import View.MainFrame;
import View.SignIn_Up;
import server.ServerApp;
/**
 * @author s1m0n
 * Entry point of the app
 *
 */
public class Main {
	public static Member USER;
	public static ServerApp serverApp;
	
	public static void main(String[] args) {
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			serverApp = (ServerApp) registry.lookup("ServerApp");
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Can't connect to the Server App");
		}
		
		
		SignIn_Up signIn_Up = new SignIn_Up(null);
		USER = signIn_Up.getMember();
		
		MainFrame mainFrame = new MainFrame(USER);
		mainFrame.setVisible(true);
		

	}

}
