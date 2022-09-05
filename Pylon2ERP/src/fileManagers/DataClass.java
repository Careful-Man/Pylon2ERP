package fileManagers;

import java.util.HashMap;

import gui.MainFrame;

public abstract class DataClass {
	
	private static String day;
	private static String month;
	private static String year;
	
	private static HashMap<String, String> branchMap = new HashMap<>();
	private static HashMap<String, String> monthMap = new HashMap<>();
	
	//αρχικοποιεί τον χάρτη κωδικών - ονομάτων υποκαταστημάτων
	public static void initializeBranchMap() {//πρώτα ενα κενό για να φαίνεται καθαρή η περιγραφή
		branchMap.put("01", " ΥΠ.ΧΑΙΡΙΑΝΩΝ");
		branchMap.put("02", " ΥΠ.ΠΕΡΙΚΛΕΟΥΣ");
		branchMap.put("03", " ΥΠ.ΚΑΡΑΚΑΣΗ");
		branchMap.put("04", " ΥΠ.ΚΡΩΜΝΗΣ");
		branchMap.put("05", " ΥΠ.ΚΗΦΙΣΙΑΣ");
		
		branchMap.put("06", " ΥΠ.ΛΑΜΠΡΑΚΗ");
		branchMap.put("07", " ΥΠ.ΠΛΑΓΙΩΝ");
		branchMap.put("09", " ΥΠ.ΜΑΡΤΙΟΥ");
		branchMap.put("10", " ΥΠ.ΕΓΝΑΤΙΑΣ");
		branchMap.put("11", " ΥΠ.ΘΕΡΜΗΣ");
		
		branchMap.put("13", " ΥΠ.ΝΙΚΟΠΟΛΗΣ");
		branchMap.put("15", " ΥΠ.ΜΑΡΑΣΛΗ");
		branchMap.put("16", " ΥΠ.ΙΘΑΚΗΣ");
		branchMap.put("19", " ΥΠ.ΠΑΡ/ΛΟΥ");
		branchMap.put("20", " ΥΠ.ΕΠΤΑΛΟΦΟΥ");
		
		branchMap.put("21", " ΥΠ.ΠΥΛΑΙΑΣ");
		branchMap.put("22", " ΥΠ.ΑΙΓΑΙΟΥ");
		branchMap.put("23", " ΥΠ.ΒΙΘΥΝΙΑΣ");
		branchMap.put("24", " ΥΠ.ΠΟΝΤΟΥ");
		branchMap.put("25", " ΥΠ.ΧΑΛΚΙΔΙΚΗΣ");
		
		branchMap.put("26", " ΥΠ.ΕΓΝ-ΠΥΛΑΙΑΣ");
		
		
		
		branchMap.put("000", " ΕΔΡΑ-ΔΙΟΙΚ.");
		branchMap.put("001", " ΕΔΡΑ-ΔΙΑΘ.");
	}
	
	//χάρτης με τους μήνες (αριθμός μήνα - όνομα μήνα)
	public static void initializeMonthMap() {
		monthMap.put("01", "ΙΑΝΟΥΑΡΙΟΥ");
		monthMap.put("02", "ΦΕΒΡΟΥΑΡΙΟΥ");
		monthMap.put("03", "ΜΑΡΤΙΟΥ");
		monthMap.put("04", "ΑΠΡΙΛΙΟΥ");
		monthMap.put("05", "ΜΑΙΟΥ");
		monthMap.put("06", "ΙΟΥΝΙΟΥ");
		
		monthMap.put("07", "ΙΟΥΛΙΟΥ");
		monthMap.put("08", "ΑΥΓΟΥΣΤΟΥ");
		monthMap.put("09", "ΣΕΠΤΕΜΒΡΙΟΥ");
		monthMap.put("10", "ΟΚΤΩΒΡΙΟΥ");
		monthMap.put("11", "ΝΟΕΜΒΡΙΟΥ");
		monthMap.put("12", "ΔΕΚΕΜΒΡΙΟΥ");
		
	}
	
	//μέθοδος που ελέγχει την εγκυρότητα της ημ/νίας που εισήγαγε ο χρήστης
	public static String checkDateFormat(String date, String article) {
		String message = "";
		
		try {
			String day = date.substring(0, 2);
			DataClass.day = day;
			String sep1 = date.substring(2, 3);
			
			String month = date.substring(3, 5); 
			DataClass.month = month;
			String sep2 = date.substring(5, 6); 
			
			String year = date.substring(6, 8);
			DataClass.year = year;
			
			if(date.length() != 8 || !(sep1.equals(sep2)) ) {//έλεγχος μήκους και διαχωριστικών
				message += "Σφάλμα: \n";
				MainFrame.setError(true);
				
				if(date.length() != 8)
					message += "Το μήκος του πεδίου \"Ημ/νία Άρθρου\" δεν είναι έγκυρο.\n";
				if(!(sep1.equals(sep2)))
					message += "Το πεδίο \"Ημ/νία Άρθρου\" πρέπει να περιλαμβάνει δύο ίσα διαχωριστικά (πχ ΗΗ/ΜΜ/ΧΧ).\n";
			}else {
				try {//είναι ακέραιοι αριθμοί μέρα, μήνας, χρόνος;
					if(!doesDateExist(Integer.parseInt(day),
							         Integer.parseInt(month),
									 Integer.parseInt("20" + year))){
						message += "Σφάλμα:\nΗ Ημ/νία που εισήχθη δεν υπάρχει.\n";
						MainFrame.setError(true);
					}else {
						//αν περάσω από όλους τους ελέγχους, τότε ελέγχω αν είναι σωστή η ημ/νία για το άρθρο που επιλέχθηκε
						message += compareArticleWithDate(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt("20" + year), article);
					}
				}catch (NumberFormatException e) {
					message += "Το πεδίο \"Ημ/νία Άρθρου\" δεν μπορεί να περιλαμβάνει γράμματα ή χαρακτήρες. \n";
				}
			}
		}catch (StringIndexOutOfBoundsException e) {
			message += "Σφάλμα:\nΤο πεδίο \"Ημ/νία Άρθρου\" δεν είναι πλήρως συμπληρωμένο.\n";
		}
		
		return message + "\n";
	}
	
	//μέθοδος που ελέγχει αν υπάρχει η ημ/νία που εισήγαγε ο χρήστης
	public static boolean doesDateExist(int day, int month, int year) {
		boolean exists = false;
		
		if(!(year%4 == 0)) //συνηθισμένο
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, false);
		else if(!(year%100 == 0)) //δίσεκτο
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, true);
		else if(!(year%400 == 0)) //συνηθισμένο
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, false);
		else //δίσεκτο
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, true);
		
		return exists;
	}
	
	//βοηθητική μέθοδος για την doesDateExist που ελέγχει αν η ημέρα υπάρχει στον δεδομένο μήνα
	public static boolean checkIfDayIsNotHigherThanMonthsLastDay(int day, int month, int year, boolean exists, boolean isLeap) {
		
		if(month == 1) {//Ιανουάριος
			if(day<=31)
				exists = true;
		}else if(month == 2) {//Φεβρουάριος
			if(isLeap) {
				if(day<=28 + 1)//αν το έτος είναι δίσεκτο, ο Φεβρουάριος έχει 29 μέρες
					exists = true;
			}else {
				if(day<=28) 
					exists = true;
			}
		}else if(month == 3) {//Μάρτιος
			if(day<=31)
				exists = true;
		}else if(month == 4) {//Απρίλιος
			if(day<=30)
				exists = true;
		}else if(month == 5) {//Μάιος
			if(day<=31)
				exists = true;
		}else if(month == 6) {//Ιούνιος
			if(day<=30)
				exists = true;
		}else if(month == 7) {//Ιούλιος
			if(day<=31)
				exists = true;
		}else if(month == 8) {//Αύγουστος
			if(day<=31)
				exists = true;
		}else if(month == 9) {//Σεπτέμβριος
			if(day<=30)
				exists = true;
		}else if(month == 10) {//Οκτώβριος
			if(day<=31)
				exists = true;
		}else if(month == 11) {//Νοέμβριος
			if(day<=30)
				exists = true;
		}else if(month == 12) {//Δεκέμβριος
			if(day<=31)
				exists = true;
		}
		return exists;
	}
	
	//ελεγχος ημ/νιας σε συναρτηση με το αρθρο που επιλέχθηκε
	//μισθοδοσία είναι πάντα την τελευταία του μήνα
	//επίδομα είναι πάντα την προτελευταία του μήνα
	//αποζημίωση είναι πάντα την προ-προτελευταία του μήνα
	public static String compareArticleWithDate(int day, int month, int year, String article) {
		String message = "";
		
		if(article.equals("ΜΙΣΘΟΔΟΣΙΑ")) {
			message = checkArticleDate(day, month, year, article);
		}else if(article.equals("ΕΠΙΔ. ΑΔΕΙΑΣ")) {
			message = checkArticleDate(day + 1, month, year, article);
		}else if(article.equals("ΑΠΟΖ. ΑΔΕΙΑΣ")) {
			message = checkArticleDate(day + 2, month, year, article);
		}
		
		return message;
	}
	
	//βοηθητική μέθοδος για την compareArticleWithDate που ελέγχει εάν ένα έτος είναι δίσεκτο
	public static String checkArticleDate(int day, int month, int year, String article) {
		String message = "";
		
		if(!(year%4 == 0)) { //συνηθισμένο
			if(!isArticleDayCorrect(day, month, year, false)) {
				message += "Σφάλμα:\nΗ ημ/νία που εισήχθη δεν είναι η σωστή για το δεδομένο άρθρο.\n(Επιλεγμένο άρθρο: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else if(!(year%100 == 0)) { //δίσεκτο
			if(!isArticleDayCorrect(day, month, year, true)) {
				message += "Σφάλμα:\nΗ ημ/νία που εισήχθη δεν είναι η σωστή για το δεδομένο άρθρο.\n(Επιλεγμένο άρθρο: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else if(!(year%400 == 0)) { //συνηθισμένο
			if(!isArticleDayCorrect(day, month, year, false)) {
				message += "Σφάλμα:\nΗ ημ/νία που εισήχθη δεν είναι η σωστή για το δεδομένο άρθρο.\n(Επιλεγμένο άρθρο: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else { //δίσεκτο
			if(!isArticleDayCorrect(day, month, year, true)) {
				message += "Σφάλμα:\nΗ ημ/νία που εισήχθη δεν είναι η σωστή για το δεδομένο άρθρο.\n(Επιλεγμένο άρθρο: " + article + ")\n";
				MainFrame.setError(true);
			}
		}
		
		
		return message;
	}
	
	//ελέγχω αν η ημέρα του μήνα είναι σωστή για το άρθρο που επιλέχθηκε
	public static boolean isArticleDayCorrect(int day, int month, int year, boolean isLeap) {
		boolean isCorrect = false;
		
		if(month == 1) {//Ιανουάριος
			if(day==31)
				isCorrect = true;
		}else if(month == 2) {//Φεβρουάριος
			if(isLeap) {
				if(day==28 + 1)//αν το έτος είναι δίσεκτο, ο Φεβρουάριος έχει 29 μέρες
					isCorrect = true;
			}else {
				if(day==28) 
					isCorrect = true;
			}
		}else if(month == 3) {//Μάρτιος
			if(day==31)
				isCorrect = true;
		}else if(month == 4) {//Απρίλιος
			if(day==30)
				isCorrect = true;
		}else if(month == 5) {//Μάιος
			if(day==31)
				isCorrect = true;
		}else if(month == 6) {//Ιούνιος
			if(day==30)
				isCorrect = true;
		}else if(month == 7) {//Ιούλιος
			if(day==31)
				isCorrect = true;
		}else if(month == 8) {//Αύγουστος
			if(day==31)
				isCorrect = true;
		}else if(month == 9) {//Σεπτέμβριος
			if(day==30)
				isCorrect = true;
		}else if(month == 10) {//Οκτώβριος
			if(day==31)
				isCorrect = true;
		}else if(month == 11) {//Νοέμβριος
			if(day==30)
				isCorrect = true;
		}else if(month == 12) {//Δεκέμβριος
			if(day==31)
				isCorrect = true;
		}
		return isCorrect;
	}
	
	//μέθοδος που ελέγχει την εγκυρότητα του ΑΧ που εισήγαγε ο χρήστης
	public static String checkAX(String ax) {
		String message = "";
		
		try {
			Integer.parseInt(ax);
		} catch(NumberFormatException nfe) {
			message += "Σφάλμα:\nΤο πεδίο \"ΑΧ\" πρέπει να είναι ένας διψήφιος ακέραιος αριθμός.\n";
			MainFrame.setError(true);
		}
		
		return message;
	}
	
	//μέθοδος που επιστρέφει ο όνομα του υποκαταστήματος
	public static String getBranchName(String aKey) {
		return DataClass.branchMap.get(aKey);
	}
	
	//μέθοδος που επιστρέφει το όνομα του μήνα
	public static String getMonthName(String aKey) {
		return DataClass.monthMap.get(aKey);
	}
	
	//getters
	public static String getDay() {
		return DataClass.day;
	}
	public static String getMonth() {
		return DataClass.month;	
	}
	public static String getYear() {
		return DataClass.year;
	}

	
	
	
	
	
	
}
