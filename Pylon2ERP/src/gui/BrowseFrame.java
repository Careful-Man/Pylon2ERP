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
	
	
	//������������� ��� popup �������� ������� pylon
	public BrowseFrame(MainFrame theMainFrame){//o �������� ������� ������������� �� �� NetBeans
		thePanel = new JPanel();
        theFileChooser = new JFileChooser();
        
        FileFilter fileFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt", "TXT", "text");//������� �� ������ � ������� ������ �� �������� �� ������ ������ .txt

    	theFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//� ������� ������ �� ��� ��� ������ ��� ��������
        theFileChooser.addChoosableFileFilter(fileFilter);//� ������� ������ �� �������� �� ������ ���� ������ .txt
        theFileChooser.setFileFilter(fileFilter);//����� ������������� ��� ������� ��� .txt �������
        theFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));//����� ������������� �� directory "desktop"
        theFileChooser.getActionMap().get("viewTypeDetails").actionPerformed(null);//����� ������������� ��� ������� "details"
    
    	
        
        
        
        
        
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
        this.setLocationRelativeTo(null);//��������� �� �������� ��� ���� �����
        this.setTitle("Pylon-to-ERP - �������� ��� ������");//������ ���������
	    this.setVisible(true);//�� ��� ������� ���� � ������, �� �������� ��� ��������..
	    //this.setIconImage(GlobalWindowSettings.getIcon());
	    this.setResizable(false);//� ������� ��� ������ �� ������� �� ������� ����� ��� ���������
	    
	    
	    this.getOperation(this, theMainFrame);//�� � ������� ������� open � cancel
	    this.getCloseOperation(this, theMainFrame);//���� ���� � ������� ������� ���� �� �������� �� ��� �������� �� mainFrame
	}
	
	
	//listener methods
    public void getOperation(BrowseFrame theBrowseFrame, MainFrame theMainFrame) {//�� � ������� ������� �� ������� "..." ����
    	this.theFileChooser.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {//�� ������� open ������� �� folder ��� �� ���������� ��� MainWindow
					theMainFrame.setRootFolderTextField(theBrowseFrame.theFileChooser.getSelectedFile().getAbsolutePath());
					theBrowseFrame.dispose();
					theMainFrame.setVisible(true);
				}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {//������ �� ������� cancel ������� �� �������� BrowseRootFolder
					theBrowseFrame.dispose();
					theMainFrame.setVisible(true);
				}
				
			}
    		
    	});
    }
    public void getCloseOperation(BrowseFrame theBrowseFrame, MainFrame theMainFrame) {//���� ����� ������� �� browseframe ��� �������� �� Mainframe
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
