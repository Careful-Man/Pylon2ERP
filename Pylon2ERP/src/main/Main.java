package main;

import fileManagers.DataClass;
import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		
		DataClass.initializeBranchMap();
		DataClass.initializeMonthMap();
		
		MainFrame theMainFrame = new MainFrame();
		
		theMainFrame.addBrowseButtonListener(theMainFrame);
		theMainFrame.addHelpButtonListener(theMainFrame);
		theMainFrame.addStartButtonListener(theMainFrame);

		//ektelesimo
		//TODO �������� ����� ����������� �� ���� ������, ��������� ������� �� ascii, ������ encoding �� ansi
		
		
	}

}
