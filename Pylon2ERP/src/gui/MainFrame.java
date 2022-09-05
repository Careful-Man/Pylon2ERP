package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import fileManagers.Account;
import fileManagers.DataClass;
import fileManagers.GeneralLedgerArticle;
import fileManagers.PylonFileReader;

public class MainFrame extends JFrame{//η πλειοψηφία του κώδικα δημιουργήθηκε με το NetBeans
	
	private static final long serialVersionUID = -4562397766838924685L;
	
	private javax.swing.JComboBox<String> articleComboBox;
    private javax.swing.JLabel articleLabel;
    private javax.swing.JLabel axLabel;
    private javax.swing.JTextField axTextField;
    private javax.swing.JButton browseButton;
    private javax.swing.JScrollPane consoleScrollPane;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JTextField folderTextField;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel jPanel;
    private javax.swing.JButton startButton;
    
    private static boolean error = false;
    
    public static final String START_MESSAGE = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$    ΕΝΑΡΞΗ    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n";
    public static final String END_MESSAGE =   "\n\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$     ΛΗΞΗ     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n";
    public static final String SEPARATOR = "\n===================================================================================================\n";
    
	
	public MainFrame() {
		jPanel = new javax.swing.JPanel();
        folderLabel = new javax.swing.JLabel();
        folderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        articleLabel = new javax.swing.JLabel();
        articleComboBox = new javax.swing.JComboBox<>();
        consoleScrollPane = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        helpButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        axLabel = new javax.swing.JLabel();
        axTextField = new javax.swing.JTextField();


        jPanel.setPreferredSize(new java.awt.Dimension(1120, 700));

        folderLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        folderLabel.setText("Αρχείο Άρθρου:");
        folderLabel.setAlignmentY(0.0F);
        folderLabel.setIconTextGap(1);

        folderTextField.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N

        browseButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        browseButton.setText("...");

        dateLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        dateLabel.setText("Ημ/νία Άρθρου:");
        dateLabel.setAlignmentY(0.0F);
        dateLabel.setIconTextGap(1);

        dateTextField.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        dateTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (dateTextField.getText().length() >= 8 ) // limit textfield to 8 characters
                    e.consume(); 
            }  
        });

        articleLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        articleLabel.setText("Άρθρο:");
        articleLabel.setAlignmentY(0.0F);
        articleLabel.setIconTextGap(1);

        articleComboBox.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        articleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ΜΙΣΘΟΔΟΣΙΑ", "ΕΠΙΔ. ΑΔΕΙΑΣ", "ΑΠΟΖ. ΑΔΕΙΑΣ", "ΔΩΡΟ ΠΑΣΧΑ", "ΔΩΡΟ ΧΡΙΣΤΟΥΓΕΝΝΩΝ" }));
        

        consoleTextArea.setBackground(new java.awt.Color(0, 0, 0));
        consoleTextArea.setColumns(20);
        consoleTextArea.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        consoleTextArea.setForeground(new java.awt.Color(255, 255, 255));
        consoleTextArea.setRows(5);
        consoleTextArea.setEditable(false);
        consoleScrollPane.setViewportView(consoleTextArea);

        helpButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        helpButton.setText("Βοήθεια");

        startButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        startButton.setText("Εκκίνηση");

        axLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        axLabel.setText("AX:");
        axLabel.setAlignmentY(0.0F);
        axLabel.setIconTextGap(1);

        
        
        axTextField.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        axTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
            	try {
            		if(!axTextField.getText().equals(""))//αν δεν ειναι αριθμος..
            			Integer.parseInt(axTextField.getText().trim()); 
            		
	                if (axTextField.getText().length() >= 2 ) { // limit textfield to 2 characters
	                	if(!axTextField.getText().equals("00")) {//επιτρέπει να γράψει 3 χαρακτήρες μόνο αν ξεκινάνε απο 00 και ο επόμενος είναι 0 ή 1
	                    	e.consume();
	                    }else if(!(e.getKeyChar() == KeyEvent.VK_0) && !(e.getKeyChar() == KeyEvent.VK_1)) {
	                    	e.consume();
	                    }else if(axTextField.getText().equals("000") || axTextField.getText().equals("001"))
	                		e.consume(); 
                	   
	                }
            	} catch (NumberFormatException nfe) {
                	e.consume();//..μην τον γραψεις. Δυστυχως αφήνει να γραψω ένα χαρακτήρα.
                }
            }  
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(consoleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(folderLabel)
                            .addComponent(dateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(folderTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseButton))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(axLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(axTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(articleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(articleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(folderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(folderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateLabel)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(articleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(articleLabel)
                        .addComponent(axTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(axLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consoleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pylon-to-ERP");
        setMaximumSize(new java.awt.Dimension(1120, 700));
        setMinimumSize(new java.awt.Dimension(1120, 700));
        setPreferredSize(new java.awt.Dimension(1120, 700));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.pack();
	}


	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	//listener methods
	public void addBrowseButtonListener(MainFrame theMainFrame) {//όταν ο χρήστης πατάει το πλήκτρο "..."
		this.browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theMainFrame.setVisible(false);
				new BrowseFrame(theMainFrame);
				
			}
		});
	}
	public void addHelpButtonListener(MainFrame theMainFrame) {//όταν ο χρήστης πατήσει το πλήκτρο "Βοήθεια"
		this.helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame(theMainFrame);
			}
			
		});
	}
	public void addStartButtonListener(MainFrame theMainFrame) {//όταν ο χρήστης πατήσει το πλήκτρο "Εκκίνηση"
		this.startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				theMainFrame.clearConsole();//κάθε φορά που ο χρήστης πατάει "Εκκίνηση" καθαρίζει την κονσόλα
				
				GeneralLedgerArticle.emptyArticlesArrayList();//κάθε φορά που ο χρήστης πατάει "Εκκίνηση" αδειάζει τα άρθρα που έχει διαβάσει το πρόγραμμα
				
				theMainFrame.writeToConsole(MainFrame.START_MESSAGE);
				PylonFileReader pfr = new PylonFileReader(theMainFrame);
				theMainFrame.writeToConsole(pfr.getFieldValues());//αν ο χρήστης εισάγει λάθος δεδομένα στα πεδία, εμφανίζει σχετικό μήνυμα
				theMainFrame.writeToConsole(DataClass.checkDateFormat(theMainFrame.getDate(), 
																	  theMainFrame.articleComboBox.getSelectedItem().toString()));//έλεγχος ημ/νίας
				theMainFrame.writeToConsole(DataClass.checkAX(theMainFrame.getAX()));
				if(!error) {//αν δεν υπάρχουν σφάλματα
					
					pfr.readFromFile(pfr.getFilePath(), theMainFrame);
					//διάβασε το αρχείο
					Account.setCorrectedCodesConsoleMessage("");
				}
				error = false;
				theMainFrame.writeToConsole(MainFrame.END_MESSAGE);
			}
		});
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	//other methods
	public void setRootFolderTextField(String absolutePath) {//βάζει τιμή στο πεδίο από το οποόιο διαβάζεται η διαδρομή του αρχείου
		this.folderTextField.setText(absolutePath);
	}
	public static void setError(boolean e) {
		error = e;
	}
	public String getFilePath() {//επιστρέφει την τιμή του πεδίου "Αρχείο:"
		return this.folderTextField.getText().trim();
	}
	public String getDate() {//επιστρέφει την τιμή του πεδίου "Μήνας:"
		return this.dateTextField.getText().trim();
	}
	public String getAX() {//επιστρέφει την τιμή του πεδίου "AX:"
		return this.axTextField.getText().trim();
	}
	public String getArticle() {//επιστρέφει την τιμή του πεδίου "Άρθρο:"
		return this.articleComboBox.getSelectedItem().toString().trim();
	}
	public void writeToConsole(String message) {//γράφει στην κονσόλα το μήνυμα message
		this.consoleTextArea.append(message);
	}
	public void clearConsole() {//καθαρίζει την κονσόλα
		this.consoleTextArea.setText("");
	}
	
	
	
}
