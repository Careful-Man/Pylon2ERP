package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class BrowseFrame extends JFrame{
	
	private static final long serialVersionUID = 6403370772221056915L;
	
	private JPanel thePanel;
	private JFileChooser theFileChooser;
	
	
	//κατασκευαστής για popup επιλογής αρχείου pylon
	public BrowseFrame(MainFrame theMainFrame){//o παρακάτω κώδικας δημιουργήθηκε με το NetBeans
		thePanel = new JPanel();
        theFileChooser = new JFileChooser();
        
        FileFilter fileFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt", "TXT", "text");//επιλογή να μπορεί ο χρήστης μπορεί να διαλέξει να βλέπει αρχεία .txt

    	theFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//ο χρήστης μπορεί να δει και αρχεία και φακέλους
        theFileChooser.addChoosableFileFilter(fileFilter);//ο χρήστης μπορεί να διαλέξει να βλέπει μόνο αρχεία .txt
        theFileChooser.setFileFilter(fileFilter);//κάνει προεπιλεγμένη την προβολή των .txt αρχείων
        theFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));//κάνει προεπιλεγμένο το directory "desktop"
        theFileChooser.getActionMap().get("viewTypeDetails").actionPerformed(null);//κάνει προεπιλεγμένη την προβολή "details"
    
    	
        
        
        
        
        
        GroupLayout thePanelLayout = new GroupLayout(thePanel);
        thePanel.setLayout(thePanelLayout);
        thePanelLayout.setHorizontalGroup(
            thePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(theFileChooser, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        thePanelLayout.setVerticalGroup(
            thePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(theFileChooser, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(thePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(thePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        thePanel.add(theFileChooser);
        
        this.setContentPane(thePanel);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);//κεντράρει το παράθυρο για κάθε οθόνη
        this.setTitle("Pylon-to-ERP - Επιλέξτε ένα αρχείο");//τίτλος παραθύρου
	    this.setVisible(true);//αν δεν υπάρχει αυτή η γραμμή, το παράθυρο δεν φαίνεται..
	    //this.setIconImage(GlobalWindowSettings.getIcon());
	    this.setResizable(false);//ο χρήστης δεν μπορεί να αλλάξει το μέγεθος αυτού του παραθύρου
	    
	    
	    this.getOperation(this, theMainFrame);//αν ο χρήστης πατήσει open ή cancel
	    this.getCloseOperation(this, theMainFrame);//θέλω οταν ο χρήστης ανοιγει αυτό το παράθυρο να μην φαίνεται το mainFrame
	}
	
	
	//listener methods
    public void getOperation(BrowseFrame theBrowseFrame, MainFrame theMainFrame) {//αν ο χρήστης πατήσει το πλήκτρο "..." τότε
    	this.theFileChooser.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {//αν πατήσει open κρατάει το folder και το αποθηκεύει στο MainWindow
					theMainFrame.setRootFolderTextField(theBrowseFrame.theFileChooser.getSelectedFile().getAbsolutePath());
					theBrowseFrame.dispose();
					theMainFrame.setVisible(true);
				}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {//αλλιώς αν πατήσει cancel κλείνει το παράθυρο BrowseRootFolder
					theBrowseFrame.dispose();
					theMainFrame.setVisible(true);
				}
				
			}
    		
    	});
    }
    public void getCloseOperation(BrowseFrame theBrowseFrame, MainFrame theMainFrame) {//οταν είναι ανοιχτό το browseframe δεν φαίνεται το Mainframe
    	this.theFileChooser.addAncestorListener(new AncestorListener() {
    		
			@Override
			public void ancestorAdded(AncestorEvent event) {
			}
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				theMainFrame.setVisible(true);
			}
			@Override
			public void ancestorMoved(AncestorEvent event) {
			}
    		
    	});
    }
	
	
}
