package Main;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Modele.Member;
import View.MainFrame;
import View.SignIn_Up;
import server.ServerApp;
import server.ServerGroup;
import server.UserServerImpl;
/**
 * Main class launched by the client (acts as the application)
 */
public class Main {
	/**
	 * Member with which the client has connected
	 */
	public static Member USER;
	/**
	 * ServerApp Distant object which, first of all, allows to register or connect 
	 */
	public static ServerApp serverApp;
	/**
	 * Server's IP address, which has to be input by the client at launch
	 */
	public static String address;
	
	/**
	 * Main method launched at the execution. Handles the connection, sets up the links with both {@link ServerApp ServerApp} and the "public" {@link ServerGroup ServerGroup} and initializes and holds the {@link MainFrame MainFrame}
	 * @param args contains the IP address of the Server to connect to.
	 */
	public static void main(String[] args) {

		Registry registry = null;
		try {
			if(args.length > 0) {
				address = args[0];
				registry = LocateRegistry.getRegistry(args[0]);
			}else {
				registry = LocateRegistry.getRegistry();	
			}
			serverApp = (ServerApp) registry.lookup("ServerApp");
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Can't connect to the Server App");
		}
				
		SignIn_Up signIn_Up = new SignIn_Up(null);
		USER = signIn_Up.getMember();
		try {
			System.out.println("IP Adress is : " + InetAddress.getLocalHost().getHostAddress());
			USER.setIPAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		UserServerImpl userServerImpl;
		try {
			userServerImpl = new UserServerImpl();
			Registry regU = LocateRegistry.getRegistry();
			regU.rebind(USER.getPseudo(), userServerImpl);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Load the public drawing 
		try {
			ServerGroup serverPublic = (ServerGroup) registry.lookup(serverApp.getNameGroupPublic());
			serverPublic.sendDrawing(USER);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
				
		MainFrame mainFrame = new MainFrame(USER);
		mainFrame.setVisible(true);		
	}
}
