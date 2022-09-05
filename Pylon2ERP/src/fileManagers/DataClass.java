package fileManagers;

import java.util.HashMap;

import gui.MainFrame;

public abstract class DataClass {
	
	private static String day;
	private static String month;
	private static String year;
	
	private static HashMap<String, String> branchMap = new HashMap<>();
	private static HashMap<String, String> monthMap = new HashMap<>();
	
	//����������� ��� ����� ������� - �������� ���������������
	public static void initializeBranchMap() {//����� ��� ���� ��� �� �������� ������ � ���������
		branchMap.put("01", " ��.���������");
		branchMap.put("02", " ��.����������");
		branchMap.put("03", " ��.��������");
		branchMap.put("04", " ��.�������");
		branchMap.put("05", " ��.��������");
		
		branchMap.put("06", " ��.��������");
		branchMap.put("07", " ��.�������");
		branchMap.put("09", " ��.�������");
		branchMap.put("10", " ��.��������");
		branchMap.put("11", " ��.������");
		
		branchMap.put("13", " ��.���������");
		branchMap.put("15", " ��.�������");
		branchMap.put("16", " ��.������");
		branchMap.put("19", " ��.���/���");
		branchMap.put("20", " ��.���������");
		
		branchMap.put("21", " ��.�������");
		branchMap.put("22", " ��.�������");
		branchMap.put("23", " ��.��������");
		branchMap.put("24", " ��.������");
		branchMap.put("25", " ��.����������");
		
		branchMap.put("26", " ��.���-�������");
		
		
		
		branchMap.put("000", " ����-�����.");
		branchMap.put("001", " ����-����.");
	}
	
	//������ �� ���� ����� (������� ���� - ����� ����)
	public static void initializeMonthMap() {
		monthMap.put("01", "����������");
		monthMap.put("02", "�����������");
		monthMap.put("03", "�������");
		monthMap.put("04", "��������");
		monthMap.put("05", "�����");
		monthMap.put("06", "�������");
		
		monthMap.put("07", "�������");
		monthMap.put("08", "���������");
		monthMap.put("09", "�����������");
		monthMap.put("10", "���������");
		monthMap.put("11", "���������");
		monthMap.put("12", "����������");
		
	}
	
	//������� ��� ������� ��� ���������� ��� ��/���� ��� �������� � �������
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
			
			if(date.length() != 8 || !(sep1.equals(sep2)) ) {//������� ������ ��� �������������
				message += "������: \n";
				MainFrame.setError(true);
				
				if(date.length() != 8)
					message += "�� ����� ��� ������ \"��/��� ������\" ��� ����� ������.\n";
				if(!(sep1.equals(sep2)))
					message += "�� ����� \"��/��� ������\" ������ �� ������������ ��� ��� ������������ (�� ��/��/��).\n";
			}else {
				try {//����� �������� ������� ����, �����, ������;
					if(!doesDateExist(Integer.parseInt(day),
							         Integer.parseInt(month),
									 Integer.parseInt("20" + year))){
						message += "������:\n� ��/��� ��� ������� ��� �������.\n";
						MainFrame.setError(true);
					}else {
						//�� ������ ��� ����� ���� ��������, ���� ������ �� ����� ����� � ��/��� ��� �� ����� ��� ����������
						message += compareArticleWithDate(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt("20" + year), article);
					}
				}catch (NumberFormatException e) {
					message += "�� ����� \"��/��� ������\" ��� ������ �� ������������ �������� � ����������. \n";
				}
			}
		}catch (StringIndexOutOfBoundsException e) {
			message += "������:\n�� ����� \"��/��� ������\" ��� ����� ������ ������������.\n";
		}
		
		return message + "\n";
	}
	
	//������� ��� ������� �� ������� � ��/��� ��� �������� � �������
	public static boolean doesDateExist(int day, int month, int year) {
		boolean exists = false;
		
		if(!(year%4 == 0)) //�����������
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, false);
		else if(!(year%100 == 0)) //�������
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, true);
		else if(!(year%400 == 0)) //�����������
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, false);
		else //�������
			exists = checkIfDayIsNotHigherThanMonthsLastDay(day, month, year, exists, true);
		
		return exists;
	}
	
	//��������� ������� ��� ��� doesDateExist ��� ������� �� � ����� ������� ���� �������� ����
	public static boolean checkIfDayIsNotHigherThanMonthsLastDay(int day, int month, int year, boolean exists, boolean isLeap) {
		
		if(month == 1) {//����������
			if(day<=31)
				exists = true;
		}else if(month == 2) {//�����������
			if(isLeap) {
				if(day<=28 + 1)//�� �� ���� ����� �������, � ����������� ���� 29 �����
					exists = true;
			}else {
				if(day<=28) 
					exists = true;
			}
		}else if(month == 3) {//�������
			if(day<=31)
				exists = true;
		}else if(month == 4) {//��������
			if(day<=30)
				exists = true;
		}else if(month == 5) {//�����
			if(day<=31)
				exists = true;
		}else if(month == 6) {//�������
			if(day<=30)
				exists = true;
		}else if(month == 7) {//�������
			if(day<=31)
				exists = true;
		}else if(month == 8) {//���������
			if(day<=31)
				exists = true;
		}else if(month == 9) {//�����������
			if(day<=30)
				exists = true;
		}else if(month == 10) {//���������
			if(day<=31)
				exists = true;
		}else if(month == 11) {//���������
			if(day<=30)
				exists = true;
		}else if(month == 12) {//����������
			if(day<=31)
				exists = true;
		}
		return exists;
	}
	
	//������� ��/���� �� ��������� �� �� ����� ��� ����������
	//���������� ����� ����� ��� ��������� ��� ����
	//������� ����� ����� ��� ������������ ��� ����
	//���������� ����� ����� ��� ���-������������ ��� ����
	public static String compareArticleWithDate(int day, int month, int year, String article) {
		String message = "";
		
		if(article.equals("����������")) {
			message = checkArticleDate(day, month, year, article);
		}else if(article.equals("����. ������")) {
			message = checkArticleDate(day + 1, month, year, article);
		}else if(article.equals("����. ������")) {
			message = checkArticleDate(day + 2, month, year, article);
		}
		
		return message;
	}
	
	//��������� ������� ��� ��� compareArticleWithDate ��� ������� ��� ��� ���� ����� �������
	public static String checkArticleDate(int day, int month, int year, String article) {
		String message = "";
		
		if(!(year%4 == 0)) { //�����������
			if(!isArticleDayCorrect(day, month, year, false)) {
				message += "������:\n� ��/��� ��� ������� ��� ����� � ����� ��� �� �������� �����.\n(���������� �����: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else if(!(year%100 == 0)) { //�������
			if(!isArticleDayCorrect(day, month, year, true)) {
				message += "������:\n� ��/��� ��� ������� ��� ����� � ����� ��� �� �������� �����.\n(���������� �����: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else if(!(year%400 == 0)) { //�����������
			if(!isArticleDayCorrect(day, month, year, false)) {
				message += "������:\n� ��/��� ��� ������� ��� ����� � ����� ��� �� �������� �����.\n(���������� �����: " + article + ")\n";
				MainFrame.setError(true);
			}
		}else { //�������
			if(!isArticleDayCorrect(day, month, year, true)) {
				message += "������:\n� ��/��� ��� ������� ��� ����� � ����� ��� �� �������� �����.\n(���������� �����: " + article + ")\n";
				MainFrame.setError(true);
			}
		}
		
		
		return message;
	}
	
	//������ �� � ����� ��� ���� ����� ����� ��� �� ����� ��� ����������
	public static boolean isArticleDayCorrect(int day, int month, int year, boolean isLeap) {
		boolean isCorrect = false;
		
		if(month == 1) {//����������
			if(day==31)
				isCorrect = true;
		}else if(month == 2) {//�����������
			if(isLeap) {
				if(day==28 + 1)//�� �� ���� ����� �������, � ����������� ���� 29 �����
					isCorrect = true;
			}else {
				if(day==28) 
					isCorrect = true;
			}
		}else if(month == 3) {//�������
			if(day==31)
				isCorrect = true;
		}else if(month == 4) {//��������
			if(day==30)
				isCorrect = true;
		}else if(month == 5) {//�����
			if(day==31)
				isCorrect = true;
		}else if(month == 6) {//�������
			if(day==30)
				isCorrect = true;
		}else if(month == 7) {//�������
			if(day==31)
				isCorrect = true;
		}else if(month == 8) {//���������
			if(day==31)
				isCorrect = true;
		}else if(month == 9) {//�����������
			if(day==30)
				isCorrect = true;
		}else if(month == 10) {//���������
			if(day==31)
				isCorrect = true;
		}else if(month == 11) {//���������
			if(day==30)
				isCorrect = true;
		}else if(month == 12) {//����������
			if(day==31)
				isCorrect = true;
		}
		return isCorrect;
	}
	
	//������� ��� ������� ��� ���������� ��� �� ��� �������� � �������
	public static String checkAX(String ax) {
		String message = "";
		
		try {
			Integer.parseInt(ax);
		} catch(NumberFormatException nfe) {
			message += "������:\n�� ����� \"��\" ������ �� ����� ���� �������� �������� �������.\n";
			MainFrame.setError(true);
		}
		
		return message;
	}
	
	//������� ��� ���������� � ����� ��� ���������������
	public static String getBranchName(String aKey) {
		return DataClass.branchMap.get(aKey);
	}
	
	//������� ��� ���������� �� ����� ��� ����
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
