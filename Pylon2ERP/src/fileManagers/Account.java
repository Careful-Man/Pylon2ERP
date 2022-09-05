package fileManagers;

import gui.MainFrame;

public class Account {
	
	private String accountCode;
	private String accountAX;
	private boolean isAccountDebit;
	private float accountAmount;
	
	public static final String MHNOS = " ΜΗΝΟΣ ";
	private static String correctedCodesConsoleMessage = "";
	
	
	public Account(String thisLine, MainFrame theMainFrame, int readLength) {
		try {
			
			
			thisLine = thisLine.substring(thisLine.length()-readLength);//με ενδιαφέρουν ΜΟΝΟ οι readLength τελευταίοι χαρακτήρες κάθε γραμμής εκτός της 1ης και της τελευταίας
			this.accountCode = thisLine.substring(0, 13);
			this.accountAX = theMainFrame.getAX();
			String accountCodeCorrected = correctAccountCode(this.accountCode, this.accountAX);
			
			if(!this.accountCode.equals(accountCodeCorrected)){
				correctedCodesConsoleMessage += "Ο κωδικός λογαριασμού " + this.accountCode + " άλλαξε σε " + accountCodeCorrected + ".\n";
				this.accountCode = accountCodeCorrected;
			}
			
			
			String amountInString = thisLine.substring(13).trim();//έχω κόψει ήδη τα κενά απο το String
			if(!amountInString.substring(0, 4).equals("0,00")) {//αν έχω χρέωση
				this.isAccountDebit = true;
				
				amountInString = amountInString.substring(0, amountInString.length() - 4).trim();//πετάω τα μηδενικά της πίστωστης 
				amountInString = getAmountFormat(amountInString);
				this.accountAmount = Float.parseFloat(amountInString);
				
			} else {//αλλιώς αν έχω πίστωση
				this.isAccountDebit = false;
				
				amountInString = amountInString.substring(4).trim();
				amountInString = getAmountFormat(amountInString);
				this.accountAmount = Float.parseFloat(amountInString);			
			}
		}catch(Exception e) {
			//έχει νόημα μόνο για την τελευταία γραμμή του αρχείου
			//υπάρχει περίπτωση να μπορέσει να εκτελέσει το try στην τελευταία γραμμή;
		}
		
	}
	

	//μέθοδος που ελέγχει αν η χρέωση και η πίστωση του άρθρου είναι ίσες
	public static boolean areDebitAndCreditAmountsEqual(String lastLine) {
		String spaces = "";
		
		lastLine = lastLine.trim();
		lastLine = getAmountFormat(lastLine);
		
		
		for(int i=0; i<lastLine.length(); i++) {//μετράω τους κενούς χαρακτήρες μεταξύ των ποσών για να χωρίσει σίγουρα τα ποσά χωρίς να σκάσει
			if(lastLine.charAt(i) == ' ')
				spaces += " ";
		}
		
		lastLine = lastLine.replace(spaces, "#");
		
		String[] amounts = lastLine.split("#");
		
		try {
			return Float.parseFloat(amounts[0]) == Float.parseFloat(amounts[1]);
		} catch (Exception e) {
			return false;
		}
	}
	
	//μέθοδος που επεξεργάζεται το format των ποσών
	public static String getAmountFormat(String someAmount) {
		someAmount = someAmount.replace(".", "");
		someAmount = someAmount.replace(",", ".");
				
		return someAmount;
	}
	
	//μέθοδος που διορθώνει τους κωδικούς λογιστικής
	public String correctAccountCode(String accountCode, String accountAX) {
		try {
			String first = accountCode.substring(0, 2);
			String second = accountCode.substring(3, 5);
			String third = accountCode.substring(6, 8);
			String fourth = accountCode.substring(9);
			
			if(first.equals("60")) {
				if(second.equals("00")) {
					if(!third.equals("00")) {
						if(fourth.equals("0000") || fourth.equals("1500") || fourth.equals("1600")) {
							fourth = "1000";
						} else if(fourth.equals("0700")) {//επίδ. αδείας
							fourth = "1700";
						}
					} else {
						//if third equals 00
					}
				} else if(second.equals("03")) {
					fourth = "1000";
				} else if(second.equals("04")) {
					second = "03";
				}
				if(!third.equals(accountAX)) {
					if(accountAX.equals("000") || accountAX.equals("001")) {
						third = accountAX.substring(0, 2);
						if(accountAX.equals("000")) {
							fourth = "0000";
						} else {
							fourth = "1000";
						}
					} else {
						third = accountAX;
					}	
				}
					
			}
			return first + "-" + second + "-" + third + "-" + fourth;
		} catch(Exception e) {
			return accountCode;
		}
		
	}
	
	
	//getters
	public String getAccountAX() {
		return this.accountAX;
	}
	public boolean getIsDebit() {
		return this.isAccountDebit;
	}
	public String getAccountCode() {
		return this.accountCode;
	}
	public float getAccountAmount() {
		return this.accountAmount;
	}
	public static String getCorrectedCodesConsoleMessage() {
		return Account.correctedCodesConsoleMessage;
	}
	
	
	//setters
	public static void setCorrectedCodesConsoleMessage(String aMessage) {
		Account.correctedCodesConsoleMessage = aMessage;
	}
}
