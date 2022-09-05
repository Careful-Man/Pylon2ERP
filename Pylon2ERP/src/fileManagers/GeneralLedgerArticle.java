package fileManagers;

import java.util.ArrayList;
import java.util.Scanner;

import gui.MainFrame;

public class GeneralLedgerArticle {
	
	private String articleDescription;
	private String articleDate;
	private String nextLine;
	private ArrayList<Account> articleAccounts = new ArrayList<>();
	
	private static ArrayList<GeneralLedgerArticle> allArticles = new ArrayList<>();
	
	public static final String SEIRA = "��";
	public static final String HMEROLOGIO = "002";
	
	//�������������
	public GeneralLedgerArticle(Scanner scanner, MainFrame theMainFrame) {
		this.articleDate = DataClass.getDay() + DataClass.getMonth() + DataClass.getYear();
		
		//��������� ������
		String nextLine;
		String branch;
		nextLine = scanner.nextLine();
		this.nextLine = nextLine;
		nextLine = nextLine.substring(nextLine.length()-35);
		
		branch = theMainFrame.getAX();
		
		this.articleDescription = theMainFrame.getArticle() + 
				   DataClass.getBranchName(branch) + 
				   Account.MHNOS + 
				   DataClass.getMonthName(DataClass.getMonth());
		allArticles.add(this);
		
	}
	
	//������� ��� ���������� ��� �����
	public void addAccount (Account someAccount) {
		this.articleAccounts.add(someAccount);
	}
	
	//������� ��� ������� ���� �� ��� ���������� ������
	public boolean checkArticleAX(String ax) {
		boolean equal = true;
		
		for(Account acc: articleAccounts) {
			if(acc.getIsDebit()) {
				if(!ax.equals(acc.getAccountAX())) {
					equal = false;
				}
			}
		}
		return equal;
	}
	
	//������� ��� �������� ��� ���� �� �� �����
	public static void emptyArticlesArrayList() {
		GeneralLedgerArticle.allArticles.removeAll(allArticles);
	}
	
	
	//getters
	public String getDescreption() {
		return this.articleDescription;
	}
	public String getDate() {
		return this.articleDate;
	}
	public String getNextLine() {
		return this.nextLine;
	}
	public ArrayList<Account> getArticleAccounts(){
		return this.articleAccounts;
	}
	public static ArrayList<GeneralLedgerArticle> getAllArticles() {
		return GeneralLedgerArticle.allArticles;
	}
	
	//setters
	public void setDescreption(String aDescription) {
		this.articleDescription = aDescription;
	}
	
}
