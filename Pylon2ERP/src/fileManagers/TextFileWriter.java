package fileManagers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import gui.MainFrame;
import gui.MessagePopUp;

public abstract class TextFileWriter {
	
	//������� ��� �������� �� ������ PR.txt ��� ����� ���� ���� ������������
	public static void generatePRtxtFile(String fileDirectory, MainFrame theMainFrame) {
		File pr = new File(fileDirectory + "PR.txt");
		if(pr.isFile()) {
			writeToPRtxtFile(fileDirectory, theMainFrame, true);
		} else {
			writeToPRtxtFile(fileDirectory, theMainFrame, false);
		}
	}
	
	//������� ��� �������� ���� ��� ��� ���� ������ ��� ������ ��� ������ PR.txt
	public static void writeToPRtxtFile(String fileDirectory, MainFrame theMainFrame, boolean append) {
		try {
			FileWriter prFile = new FileWriter(fileDirectory + "PR.txt", append);
			PrintWriter pw = new PrintWriter(new BufferedWriter(prFile));
			
			if(!append) {
				pw.write("GL START !!GLPR-ACC                                                                                              \n\n");
				theMainFrame.writeToConsole("\n�� ������ PR.txt �������������.\n");
			}
			
			ArrayList<GeneralLedgerArticle> articles = GeneralLedgerArticle.getAllArticles();
			theMainFrame.writeToConsole("\n");
			for(GeneralLedgerArticle art: articles) {
				for(Account acc: art.getArticleAccounts()) {
					String descSpaces = "";
					char accType;
					String amountSpaces = "";
					
					
					for(int i=0; i<48-art.getDescreption().length(); i++) {//���� � 2� ���������� �� �������� ��� �� ����� 100 ��� �������
						descSpaces += " ";
					}
					if(acc.getIsDebit())
						accType = '�';
					else 
						accType = '�';
					for(int i=0; i<19-Float.toString(acc.getAccountAmount()).length()+1; i++)
						amountSpaces += " ";
					
					//���� � ������ ������ ��� ������
					pw.println(GeneralLedgerArticle.HMEROLOGIO + art.getDate() + "  " + acc.getAccountCode() + "              " + GeneralLedgerArticle.SEIRA + "           " + art.getDescreption() + descSpaces + "�" + art.getDate() + accType + amountSpaces + acc.getAccountAmount() + "�");
					
					theMainFrame.writeToConsole("� ����������� �� ������ " + acc.getAccountCode() + " ������� ��� ������ PR.txt ��������.\n");
				}
			}
			//���� ������ �� �����, ������ �� ������ ��� ���� ������
			pw.println();
			pw.close();
			
			theMainFrame.writeToConsole(MainFrame.SEPARATOR);
			theMainFrame.writeToConsole("�� ������ PR.txt ����������� �������� ��� ��������� ���� �������� " + fileDirectory + "PR.txt .");
			new MessagePopUp("�� ������ PR.txt ����������� ��������.");
		} catch (IOException e) {
			theMainFrame.writeToConsole("������:\n������ ������ �������-������.\n" + e.getMessage());
			MainFrame.setError(true);
		}
	}
	
}
