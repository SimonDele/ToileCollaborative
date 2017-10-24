package Main;

import Modele.Toolbox;
import View.MainFrame;
import View.SignIn_Up;

public class Main {

	public static void main(String[] args) {
		Toolbox toolbox = new Toolbox();
		MainFrame mainFrame = new MainFrame(toolbox);
		SignIn_Up signIn_Up = new SignIn_Up(mainFrame);
		System.out.println(signIn_Up.getOutput());
		

	}

}
