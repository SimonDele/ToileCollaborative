package Main;

import Modele.Group;
import Modele.Toolbox;
import View.MainFrame;
import View.SignIn_Up;

public class Main {

	public static void main(String[] args) {
		Toolbox toolbox = new Toolbox();
		Group group = new Group();
		MainFrame mainFrame = new MainFrame(toolbox, group);
		SignIn_Up signIn_Up = new SignIn_Up(mainFrame);
		mainFrame.setVisible(true);
		System.out.println(signIn_Up.getOutput());
		

	}

}
