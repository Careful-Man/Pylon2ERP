package fileManagers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import gui.MainFrame;
import gui.MessagePopUp;

public class PylonFileReader {
	
	private String filePath;
	private String date;
	private String ax;
	private String article;
	
	public static final String PYLON_FILE_START_CON1 = "��������";
	public static final String PYLON_FILE_START_CON2 = "���������";
	public static final String PYLON_FILE_START_CON3 = "�����������";
	public static final String PYLON_FILE_START_CON4 = "�����������";
	public static final String PYLON_FILE_START_CON5 = "������";
	public static final String PYLON_FILE_START_CON6 = "�������";
	
	public PylonFileReader(MainFrame theMainFrame) {
		this.filePath = theMainFrame.getFilePath();
		this.date = theMainFrame.getDate();
		this.ax = theMainFrame.getAX();
		this.article = theMainFrame.getArticle();
	}
	
	//������� ��� �������� ��� �� .txt ������
	public void readFromFile(String filePath, MainFrame theMainFrame) {
		String thisLine;
		String lastLine = "";
		int readLength;
		
		
		
		boolean isPylonFile;
		
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			GeneralLedgerArticle gla = null;
			thisLine = scanner.nextLine().substring(1);//�� ������ ��� pylon ����� ���� useless ��������� ���� ���� (���� encoding?) ��� ����� ������
			readLength = Math.abs(thisLine.indexOf("�����������") - thisLine.length());//������ ���������� �� �������� �� ��������� ��� �� ����� ���� �������
			isPylonFile = checkIfIsPylonFile(thisLine);
			
			if(isPylonFile) {
				theMainFrame.writeToConsole("����������� ��� �������  " + filePath + "\n(���" + DataClass.getBranchName(this.ax) + ")" + MainFrame.SEPARATOR);
				theMainFrame.writeToConsole(thisLine + "\n");
				gla = new GeneralLedgerArticle(scanner, theMainFrame);
				thisLine = gla.getNextLine();//���� �� �� ��� �� ��� � reader ������� ��� ������ ����� ������ �� ���� ��� ������ ��� ������� ���� ������������ ��� GeneralLedgerArticle
				
				while(scanner.hasNextLine()) {//��� ��� ���� ������ ��� ����� ��� �������
					theMainFrame.writeToConsole(thisLine + "\n");
					if(!thisLine.substring(0, 10).equals("          "))//�� ��� ����� ���� ��������� ������
						gla.addAccount(new Account(thisLine, theMainFrame, readLength));
					thisLine = scanner.nextLine();
					lastLine = thisLine;//������ ��� ��������� ������ ��� �� ������ ������� �������-��������
				}
				theMainFrame.writeToConsole(lastLine + "\n");
				
				if(!Account.areDebitAndCreditAmountsEqual(lastLine)) {
					theMainFrame.writeToConsole("������:\n� ������ ��� � ������� ��� ������ ��� ����� ����.\n");
					MainFrame.setError(true);
				} else {
					try {
						if(DataClass.getBranchName(ax).equals(null)) {
							theMainFrame.writeToConsole("������:\n� �� " + ax + " ��� �������.\n");
							MainFrame.setError(true);
						}
						if(gla.checkArticleAX(ax)) {
							theMainFrame.writeToConsole(MainFrame.SEPARATOR);
							theMainFrame.writeToConsole(Account.getCorrectedCodesConsoleMessage());
							
							TextFileWriter.generatePRtxtFile(getFileDirectory(filePath), theMainFrame);//��� ������ ��� ������
						} else {
							theMainFrame.writeToConsole("������:\n�� ������������ ����������� ��� ���������� ����������� ��� ����� ����. (����������� ��������������)");
							MainFrame.setError(true);
						}
					} catch (NullPointerException npe) {
						theMainFrame.writeToConsole("������:\n� �� " + ax + " ��� �������.\n");
						MainFrame.setError(true);
					}
					
				}
				theMainFrame.writeToConsole(MainFrame.SEPARATOR);
				scanner.close();
				
			} else {
				theMainFrame.writeToConsole("������:\n���� �� ������ ��� ������������� �� ������ ��� ������ ��� �� Pylon.\n");
				MainFrame.setError(true);
			}
			
			
			
		} catch (FileNotFoundException fnfe) {
			theMainFrame.writeToConsole("������:\n�� ������ ��� �������.\n");
			theMainFrame.writeToConsole(fnfe.getMessage());
			MainFrame.setError(true);
		}
	}

	//������� ��� ������� �� �� ������ ����� Pylon ������ 
	private boolean checkIfIsPylonFile(String thisLine) {
		boolean isPylonFile = (thisLine.contains(PYLON_FILE_START_CON1) && thisLine.contains(PYLON_FILE_START_CON2) && thisLine.contains(PYLON_FILE_START_CON3) &&
				   			   thisLine.contains(PYLON_FILE_START_CON4) && thisLine.contains(PYLON_FILE_START_CON5) && thisLine.contains(PYLON_FILE_START_CON6));
		try {
			return isPylonFile;
			
		} catch (IndexOutOfBoundsException ioobe) {
			return false;
		}
	}
	
	//������� �� ������ ����� ����� ���� ��� ���������� ������� ������
	public String getFieldValues() {
		String message = "";
		
		if(this.filePath.equals("") || this.date.equals("") || this.ax.equals("") || this.article.equals(null)) {
			message += "������: \n";
			MainFrame.setError(true);
			
			if(this.filePath.equals("")) 
				message += "�� ����� \"������ ������\" ����� ����. \n";
			if(this.date.equals("")) 
				message += "�� ����� \"��/��� ������\" ����� ����. \n";
			if(this.ax.equals("")) 
				message += "�� ����� \"AX\" ����� ����. \n";
			if(this.article.equals(null)) 
				message += "�� ����� \"�����\" ����� ����. \n";
			
		}
		return message + "\n";
	}

	
	//other methods
	public String getFileDirectory(String filePath) {
		String[] filePathDelimited;
		String fileDirectory = "";
		
		filePath = filePath.replace("\\", "#");
		filePathDelimited = filePath.split("#");
		for(int i=0; i<filePathDelimited.length-1; i++) {
			fileDirectory += filePathDelimited[i] + "\\";
		}
		
		return fileDirectory;
	}
	
	
	//getters
	public String getFilePath() {
		return this.filePath;
	}
	public String getAX() {
		return this.ax;
	}

}
