package Main;

import Modele.Group;
import Modele.Member;
import Modele.Toolbox;
import View.MainFrame;
import View.SignIn_Up;

public class Main {
	public static Member USER;
	
	public static void main(String[] args) {
		SignIn_Up signIn_Up = new SignIn_Up(null);
		MainFrame mainFrame = new MainFrame(USER);
		mainFrame.setVisible(true);
		

	}

}
