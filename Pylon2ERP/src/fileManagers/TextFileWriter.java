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
	
	//μέθοδος που φτιάχνει το αρχείο PR.txt και βάζει μέσα τους λογαριασμούς
	public static void generatePRtxtFile(String fileDirectory, MainFrame theMainFrame) {
		File pr = new File(fileDirectory + "PR.txt");
		if(pr.isFile()) {
			writeToPRtxtFile(fileDirectory, theMainFrame, true);
		} else {
			writeToPRtxtFile(fileDirectory, theMainFrame, false);
		}
	}
	
	//μεθοδος που καλείται μόνο από την πάνω μέθοδο και γράφει στο αρχείο PR.txt
	public static void writeToPRtxtFile(String fileDirectory, MainFrame theMainFrame, boolean append) {
		try {
			FileWriter prFile = new FileWriter(fileDirectory + "PR.txt", append);
			PrintWriter pw = new PrintWriter(new BufferedWriter(prFile));
			
			if(!append) {
				pw.write("GL START !!GLPR-ACC                                                                                              \n\n");
				theMainFrame.writeToConsole("\nΤο αρχείο PR.txt δημιουργήθηκε.\n");
			}
			
			ArrayList<GeneralLedgerArticle> articles = GeneralLedgerArticle.getAllArticles();
			theMainFrame.writeToConsole("\n");
			for(GeneralLedgerArticle art: articles) {
				for(Account acc: art.getArticleAccounts()) {
					String descSpaces = "";
					char accType;
					String amountSpaces = "";
					
					
					for(int i=0; i<48-art.getDescreption().length(); i++) {//θέλω η 2η ημερομηνία να ξεκινάει από τη στήλη 100 του αρχείου
						descSpaces += " ";
					}
					if(acc.getIsDebit())
						accType = 'Χ';
					else 
						accType = 'Π';
					for(int i=0; i<19-Float.toString(acc.getAccountAmount()).length()+1; i++)
						amountSpaces += " ";
					
					//αυτή η γραμμή γράφει στο αρχείο
					pw.println(GeneralLedgerArticle.HMEROLOGIO + art.getDate() + "  " + acc.getAccountCode() + "              " + GeneralLedgerArticle.SEIRA + "           " + art.getDescreption() + descSpaces + "Α" + art.getDate() + accType + amountSpaces + acc.getAccountAmount() + "Χ");
					
					theMainFrame.writeToConsole("Ο λογαριασμός με κωδικό " + acc.getAccountCode() + " εισήχθη στο αρχείο PR.txt επιτυχώς.\n");
				}
			}
			//αφού γράψει το άρθρο, πρέπει να αφήσει μια κενή γραμμή
			pw.println();
			pw.close();
			
			theMainFrame.writeToConsole(MainFrame.SEPARATOR);
			theMainFrame.writeToConsole("Το αρχείο PR.txt ενημερώθηκε επιτυχώς και βρίσκεται στην διαδρομή " + fileDirectory + "PR.txt .");
			new MessagePopUp("Το αρχείο PR.txt ενημερώθηκε επιτυχώς.");
		} catch (IOException e) {
			theMainFrame.writeToConsole("Σφάλμα:\nΥπήρξε σφάλμα εισόδου-εξόδου.\n" + e.getMessage());
			MainFrame.setError(true);
		}
	}
	
}
