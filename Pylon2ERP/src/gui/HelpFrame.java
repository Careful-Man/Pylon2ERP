package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame{
	
	private static final long serialVersionUID = -1876613774149568514L;
	
	private JScrollPane helpConsoleScrollPane;
    private JTextArea helpConsoleTextArea;
    private JPanel thePanel;
	
	public HelpFrame(MainFrame theMainFrame) {
		thePanel = new JPanel();
        helpConsoleScrollPane = new JScrollPane();
        helpConsoleTextArea = new JTextArea();

        helpConsoleTextArea.setBackground(new Color(0, 0, 0));
        helpConsoleTextArea.setColumns(20);
        helpConsoleTextArea.setFont(new Font("Courier New", 1, 24)); // NOI18N
        helpConsoleTextArea.setForeground(new Color(200, 200, 10));
        helpConsoleTextArea.setRows(5);
        helpConsoleScrollPane.setViewportView(helpConsoleTextArea);
        
        helpConsoleTextArea.setEditable(false);
        
        appendText(helpConsoleTextArea);

        javax.swing.GroupLayout thePanelLayout = new javax.swing.GroupLayout(thePanel);
        thePanel.setLayout(thePanelLayout);
        thePanelLayout.setHorizontalGroup(
            thePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpConsoleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 9999, Short.MAX_VALUE)
        );
        thePanelLayout.setVerticalGroup(
            thePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpConsoleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 9999, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(thePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        
        this.setVisible(true);
        this.setTitle("Pylon-to-ERP - ???????");
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setPreferredSize(new java.awt.Dimension(900, 600));
        this.setMaximumSize(new java.awt.Dimension(900, 600));
        this.setMinimumSize(new java.awt.Dimension(900, 600));
        this.setLocationRelativeTo(null);
        
        this.pack();
	}

	private void appendText(JTextArea helpConsoleTextArea) {
		helpConsoleTextArea.append("####################################################################################################\r\n"
				+ "########################################  PYLON-TO-ERP  ############################################\r\n"
				+ "####################################################################################################\r\n"
				+ "##												  ##\r\n"
				+ "##  ?? ????????? ???? ??????????? ?? ?????? ?????? Pylon ??? ERP. ???????? ??? .txt ?????? ???    ##\r\n"
				+ "##  ???????? ??? ?? Pylon ??? ????? ????? ?????????? ??? ???? .txt ?????? ?? ????? ?????????      ##\r\n"
				+ "##  ??? ERP.											  ##\r\n"
				+ "##												  ##\r\n"
				+ "####################################################################################################\r\n"
				+ "####################################################################################################\r\n"
				+ "####################################################################################################\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "====================================================================================================\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "####################################################################################################\r\n"
				+ "########################################     ????????    ###########################################\r\n"
				+ "####################################################################################################\r\n"
				+ "##												  ##\r\n"
				+ "##					1) ?????? Pylon						  ##\r\n"
				+ "##					2) ?????? ??????? Pylon 				  ##\r\n"
				+ "##					3) ???????? ???????????					  ##\r\n"
				+ "##					4) ??????? ?????????? ??????				  ##\r\n"
				+ "##					5) ??????? \"????????\" 					  ##\r\n"
				+ "##					6) ???????? ???????? 					  ##\r\n"
				+ "##					7) ???????? ??????? Pylon				  ##\r\n"
				+ "##					8) ???????? ??????? ???????????				  ##\r\n"
				+ "##					9) ?? ?????? PR.txt					  ##\r\n"
				+ "##					10)\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "====================================================================================================\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "####################################################################################################\r\n"
				+ "########################################   ???????????   ###########################################\r\n"
				+ "####################################################################################################\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					1) ?????? Pylon						  ##\r\n"
				+ "##												  ##\r\n"
				+ "##      ????? ?????? ?? ????? ??????? ???? ??????? .txt ??? ?? ????????? Pylon. ?? ????? ???      ##\r\n"
				+ "##      ??????? ??? ?????? ????, ???? ? ???????????? ?????? ?? ????? UTF-16 LE (???? ????? ? 	  ##\r\n"
				+ "##      ?????????????).										  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					2) ?????? ??????? Pylon 				  ##\r\n"
				+ "##												  ##\r\n"
				+ "##      ? ??????? ?????? ?? ?????????. ?????? ?? ??????? ??? ????? \"?????? ??????\" ??? ??????-    ## \r\n"
				+ "##      ?? ??? ??????? ??? ????? ??????? ??' ?? Pylon. ??????? ? ?????????? ?? ??????????????     ##\r\n"
				+ "##      ??? ????? ????, ???? ??? ???????????. ?????? ???????? ?? ?????? ?? ??????? \"...\" ??       ##\r\n"
				+ "##      ????? ??????? ??? ???????? ?????????? ???? ?????????? ??????? ??? ??????????. ?? ????-    ##\r\n"
				+ "## 	???? ?????????? ????? ??????????? ???? ?? ???????? ?? ?????? ??? ??? ????????? ??????-    ##\r\n"
				+ "##      ?? ??? ?????????? ??? ?????? ???? .txt ?????? ??? ???????? ?? ??????????. ???? ? ???-     ##\r\n"
				+ "##  	???? ????????? ?? ?????? ??? ???????? ?????????? ?????? ?? ??????? ????? ???? ?? ????     ##\r\n"
				+ "##  	? ?? ?? ???????? ??? ?? ?????? Open (???????). ?? ??? ????? ???????? ????? ?? ???? ?-  	  ##\r\n"
				+ "##  	????????? (???????? ????????? ??????? ??? ????? \"?????? ??????\"). ? ???????? ???? ??-	  ##\r\n"
				+ "## 	??? ?????? ? ????? ???????????? ?? ????????? ??? ?? ???????? ?? ?????? .txt ??? Pylon.    ##\r\n"
				+ "##												  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##					3) ???????? ???????????					  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##      ? ?????????? ??? ??????? ? ??????? ?????? ?? ????? ???? ???????????. ?????? ????? ??      ##\r\n"
				+ "##  	????? ??? ?????? ????-????????????-?????-????????????-??????. ? ????, ? ????? ??? ?  	  ##\r\n"
				+ "## 	?????? ?????? ?? ????? ??? ????? ??? ?? ???????????? ?? ????? ???? ?????????? (??? ?-  	  ##\r\n"
				+ "## 	??? ??????? ?? ??????????, ????? ?? ???????????? ?? ????? ? ????? ??????????). ?????? 	  ##\r\n"
				+ "## 	???????????? ?????? ??????????? ????? : 01/01/21  30-11-20  29=02=20  19+12+96		  ##\r\n"
				+ "## 	? ?????????? ?????? ?????? ????? ?? ????? ??????? 8 ??????????. ?? ????????? ??? ???-	  ##\r\n"
				+ "## 	?????? ???? ??? 8 ?????????? ??? ????? \"??/???\". ??? ? ??????? ??????? ??? ??????????	  ##\r\n"
				+ "## 	?????? ?? ?????? ?????????? ??? ?? ????? ??????????? ?? ?????? ?????? (??? ?????? ??- 	  ##\r\n"
				+ "## 	???? ?? ???????? ?? ????? ??? ?????? ?? ??? ?? ?????? ???? ?????, ???? ?????? ?????    	  ##\r\n"
				+ "## 	?? ?????? ????????? ????? ??? ?? ??????????? ????. ???? ???????? ????? ????????, ???? 	  ##\r\n"
				+ "## 	????? ??? ????). ????? ?? ????????? ??????? ??? ? ??????? ???? ??????? ????????? ???-	  ##\r\n"
				+ "## 	??????? (?? ? ?????????? 30/02/20 ??? ???????). ??? ? ??????? ???? ??????? ?????????? 	  ##\r\n"
				+ "## 	?????????? ?? ????????? ????????? ??????? ?????? ???? ??????? ??? ??? ????????? ?? ??- 	  ##\r\n"
				+ "## 	???? ???????? ???? ????? ?? ???????? ??? ??????? .txt ??? ?? Pylon.			  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					4) ??????? ?????????? ??????				  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##	? ??????? ?????? ?? ???????? ??? ??? ?? ????????? ?????. ??' ??????? ????? ??????????	  ##\r\n"
				+ "## 	?? ????? \"??????????\". ? ??????? ??? ?????? ?????????? ??? ?????????? ??? ?????? ?? 	  ##\r\n"
				+ "## 	??????? ? ???????. ? ?????????? ????? ????? ??? ????????? ??? ????, ?? ??????? ?????? 	  ##\r\n"
				+ "## 	????? ????? ??? ???????????? ??? ? ?????????? ?????? ??? ???-????????????. ?? ???????-	  ##\r\n"
				+ "## 	???? ????????? ?? ????????? ????????? ??????? ?????? ???? ???????.			  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					5) ??????? \"????????\" 					  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##	???? ? ??????? ?????? ?? ??????? \"????????\" ?? ????????? ???????? ?? ???????? ??? ????    ##\r\n"
				+ "##   	??????? ? ??????? ??? ?????? ????? ??????, ???????? ?? ???????? ??? ?? ?????? .txt ???	  ##\r\n"
				+ "## 	???? ???????? ??? ????? \"?????? ??????\". ???? ?????? ? ??????? ?? ??????? ??? ???? ???-   ##\r\n"
				+ "## 	?????? ?? ?????? ????????????????? ??????? ??????? ??? ???? ?? ?? ?????? ??????? ???? 	  ##\r\n"
				+ "## 	???????? ?????? ?? ????????? ?? ????????. ??? ??? ??????? ??? ?????? ?????? ?? ???????-   ##\r\n"
				+ "##	?? ????????? ??????? ???????? ???? ??????? ??? ??? ???????? ???? ??? ?? Pylon ??????      ##\r\n"
				+ "##	???? ?????? ???? ??? .txt ?????? ??? ??????????.					  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					6) ???????? ???????? 					  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##	? ??????? ????????? ????? ??????? ???????? ??? ?? ????????? ???? ?????? ?????? ???? ???   ##\r\n"
				+ "##	????????. ???? ??????? ?????? ???????????? ?????? ???????? ???? ?? ??????????? ??? ??-	  ##\r\n"
				+ "##	????? ??? ?????????? ? ????? ??????? ??????????? ???????? ?? ??????. ?? ??????????? ??-	  ##\r\n"
				+ "##	??????? ??? ?????? ?????? \"?????????\" ??????? ???? ??? ???????? ??? ???????????? ????-	  ##\r\n"
				+ "##	??????? ??????? ?????? ???? ???????. ??? ???? ??????? ?????????? ?? ?????? \"??????:\"	  ## \r\n"
				+ "##	??? ???? ??? ????????? ??? ????????? ??? ????? ????? ???????? ?????????? ??? ??????	  ##\r\n"
				+ "##	PR.txt											  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					7) ???????? ??????? Pylon				  ##\r\n"
				+ "## 												  ##\r\n"
				+ "##	???? ??? ???????? ??? ??????? Pylon ??????????? ???? ?? 35 ?????????? ?????????? ????	  ##\r\n"
				+ "##	???????. ???? ?????????? ???? ?? ??????????? ??? ??????????? ??? ??? ?????????? ??? 	  ##\r\n"
				+ "##	ERP. ? ????? ?????? ???? ??????? ????????? ????? ???????? ????????? ??? ? ????????? 	  ##\r\n"
				+ "##	??????????????? ???? ??? ?? ????????? ?? ???? ??? ??????? ?? ???? ??? ???????? (???-	  ##\r\n"
				+ "##	??? ??????????? ?? ????? ???).								  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					8) ???????? ??????? ???????????				  ##\r\n"
				+ "##												  ##\r\n"
				+ "##	??????? ??? ??????? Pylon ??? ???? ???? ???????? ??????????? ???? ????? ??? ?????? ??	  ##\r\n"
				+ "##	???????????? ??? ERP. ?? ??????? ??????? ??? ???????? ????? ???????????? ???? ???????.	  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				+ "##					9) ?? ?????? PR.txt					  ##\r\n"
				+ "##												  ##\r\n"
				+ "##	???? ????? ? ???????? ??? ??????? ??????????, ?? ???????? ??? ????? ????????? ??? ?? 	  ##\r\n"
				+ "##	????????? ????? ???????? ??? ????? ??????? ?? ????????? ??? PR.txt. ?? ?? ?????? ????	  ##\r\n"
				+ "##	??? ???????, ?? ?????????? ?? ?????????. ?? ??????? ???, ?? ????????? ????????? ?? ????-  ##\r\n"
				+ "##	???? ???? ??? ?? ??? ????????? ??? ?????? ????? ?? ?? ?????????. ???? ????? ???????? ?	  ##\r\n"
				+ "##	????????? ??? ???????, ???? ??????????? ???? ??????? ? ???????? ???. (?? ?????? PR.txt	  ##\r\n"
				+ "##	????????????? ???? ???????? ??? ??? ????? ?????????? ?? ?????? Pylon. ???? ????? ?? 	  ##\r\n"
				+ "##	?????? Pylon ??? ????????? ?? ?????????? ?? ??? ???????? ?? Desktop ???? ?? ??????? 	  ##\r\n"
				+ "##	??? ???? ?????? PR.txt)									  ##\r\n"
				+ "##												  ##\r\n"
				+ "##												  ##\r\n"
				);
		
	}

}
