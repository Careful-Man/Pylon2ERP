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
	
	public static final String PYLON_FILE_START_CON1 = "Εταιρεία";
	public static final String PYLON_FILE_START_CON2 = "Περιγραφή";
	public static final String PYLON_FILE_START_CON3 = "Λογαριασμού";
	public static final String PYLON_FILE_START_CON4 = "Λογαριασμός";
	public static final String PYLON_FILE_START_CON5 = "Χρέωση";
	public static final String PYLON_FILE_START_CON6 = "Πίστωση";
	
	public PylonFileReader(MainFrame theMainFrame) {
		this.filePath = theMainFrame.getFilePath();
		this.date = theMainFrame.getDate();
		this.ax = theMainFrame.getAX();
		this.article = theMainFrame.getArticle();
	}
	
	//μέθοδος που διαβάζει απο το .txt αρχείο
	public void readFromFile(String filePath, MainFrame theMainFrame) {
		String thisLine;
		String lastLine = "";
		int readLength;
		
		
		
		boolean isPylonFile;
		
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			GeneralLedgerArticle gla = null;
			thisLine = scanner.nextLine().substring(1);//τα αρχεία του pylon έχουν έναν useless χαρακτήρα στην αρχή (Λόγω encoding?) τον οποίο αφαιρώ
			readLength = Math.abs(thisLine.indexOf("Λογαριασμός") - thisLine.length());//πόσους χαρακτήρες θα διαβάζει το πρόγραμμα απο το τέλος κάθε γραμμής
			isPylonFile = checkIfIsPylonFile(thisLine);
			
			if(isPylonFile) {
				theMainFrame.writeToConsole("Περιεχόμενα του αρχείου  " + filePath + "\n(για" + DataClass.getBranchName(this.ax) + ")" + MainFrame.SEPARATOR);
				theMainFrame.writeToConsole(thisLine + "\n");
				gla = new GeneralLedgerArticle(scanner, theMainFrame);
				thisLine = gla.getNextLine();//θελω να δω τον ΑΧ και ο reader θυμαται που έμεινε οπότε πρέπει να πάρω την γραμμή που διάβασα στον κατασκευαστή της GeneralLedgerArticle
				
				while(scanner.hasNextLine()) {//όσο δεν έχει φτάσει στο τέλος του αρχείου
					theMainFrame.writeToConsole(thisLine + "\n");
					if(!thisLine.substring(0, 10).equals("          "))//αν δεν είναι στην τελευταία γραμμή
						gla.addAccount(new Account(thisLine, theMainFrame, readLength));
					thisLine = scanner.nextLine();
					lastLine = thisLine;//κρατάω την τελευταία γραμμή για να ελέγξω ισότητα χρέωσης-πίστωσης
				}
				theMainFrame.writeToConsole(lastLine + "\n");
				
				if(!Account.areDebitAndCreditAmountsEqual(lastLine)) {
					theMainFrame.writeToConsole("Σφάλμα:\nΗ χρέωση και η πίστωση του άρθρου δεν είναι ίσες.\n");
					MainFrame.setError(true);
				} else {
					try {
						if(DataClass.getBranchName(ax).equals(null)) {
							theMainFrame.writeToConsole("Σφάλμα:\nΟ ΑΧ " + ax + " δεν υπάρχει.\n");
							MainFrame.setError(true);
						}
						if(gla.checkArticleAX(ax)) {
							theMainFrame.writeToConsole(MainFrame.SEPARATOR);
							theMainFrame.writeToConsole(Account.getCorrectedCodesConsoleMessage());
							
							TextFileWriter.generatePRtxtFile(getFileDirectory(filePath), theMainFrame);//εδω γράφει στο αρχείο
						} else {
							theMainFrame.writeToConsole("Σφάλμα:\nΟι τριτοβάθμιοι λογαριασμοί των χρεωστικών λογαριασμών δεν είναι ίσοι. (διαφορετικά υποκαταστήματα)");
							MainFrame.setError(true);
						}
					} catch (NullPointerException npe) {
						theMainFrame.writeToConsole("Σφάλμα:\nΟ ΑΧ " + ax + " δεν υπάρχει.\n");
						MainFrame.setError(true);
					}
					
				}
				theMainFrame.writeToConsole(MainFrame.SEPARATOR);
				scanner.close();
				
			} else {
				theMainFrame.writeToConsole("Σφάλμα:\nΑυτό το αρχείο δεν αναγνωρίζεται ως αρχείο που εξήχθη από το Pylon.\n");
				MainFrame.setError(true);
			}
			
			
			
		} catch (FileNotFoundException fnfe) {
			theMainFrame.writeToConsole("Σφάλμα:\nΤο αρχείο δεν βρέθηκε.\n");
			theMainFrame.writeToConsole(fnfe.getMessage());
			MainFrame.setError(true);
		}
	}

	//μέθοδος που ελέγχει αν το αρχείο είναι Pylon αρχείο 
	private boolean checkIfIsPylonFile(String thisLine) {
		boolean isPylonFile = (thisLine.contains(PYLON_FILE_START_CON1) && thisLine.contains(PYLON_FILE_START_CON2) && thisLine.contains(PYLON_FILE_START_CON3) &&
				   			   thisLine.contains(PYLON_FILE_START_CON4) && thisLine.contains(PYLON_FILE_START_CON5) && thisLine.contains(PYLON_FILE_START_CON6));
		try {
			return isPylonFile;
			
		} catch (IndexOutOfBoundsException ioobe) {
			return false;
		}
	}
	
	//ελέγχει αν κάποιο πεδίο είναι κενό και επιστρέφει ανάλογο μήνυμα
	public String getFieldValues() {
		String message = "";
		
		if(this.filePath.equals("") || this.date.equals("") || this.ax.equals("") || this.article.equals(null)) {
			message += "Σφάλμα: \n";
			MainFrame.setError(true);
			
			if(this.filePath.equals("")) 
				message += "Το πεδίο \"Αρχείο Άρθρου\" είναι κενό. \n";
			if(this.date.equals("")) 
				message += "Το πεδίο \"Ημ/νία Άρθρου\" είναι κενό. \n";
			if(this.ax.equals("")) 
				message += "Το πεδίο \"AX\" είναι κενό. \n";
			if(this.article.equals(null)) 
				message += "Το πεδίο \"Άρθρο\" είναι κενό. \n";
			
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
