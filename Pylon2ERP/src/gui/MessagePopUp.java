package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessagePopUp extends JFrame{

	private static final long serialVersionUID = 5873293365970865614L;
	
	public MessagePopUp(String aMessage) {
		JOptionPane.showMessageDialog(this, aMessage);
	}

}
