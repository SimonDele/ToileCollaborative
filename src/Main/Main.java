package Main;

import Modele.Group;
import Modele.Member;
import Modele.Toolbox;
import View.MainFrame;
import View.SignIn_Up;

public class Main {
	public static Member USER;
	
	public static void main(String[] args) {
		Toolbox toolbox = new Toolbox();

		SignIn_Up signIn_Up = new SignIn_Up(null);
		System.out.println(USER.getGroupList().size());
		MainFrame mainFrame = new MainFrame(toolbox, USER);
		//mainFrame.setGroup(USER.getGroupList());
		mainFrame.setVisible(true);
		

	}

}
