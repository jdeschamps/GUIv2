package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import advancedacq.Acquisition;
import device.MSystem;
import threader.AcqEngine;
import utils.StringText;


@SuppressWarnings("serial")
public class AcqTab extends javax.swing.JPanel {

	MainFrame parent_;
	MSystem sys_;

	ArrayList<Acquisition> acqlist;
	boolean advancedacq = false;
	
    /**
     * Creates new form AcqTab
     */
    public AcqTab(MainFrame parent, MSystem sys) {
    	parent_ = parent;
    	sys_ = sys;
    	acq = new AcqEngine(parent_, sys_);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel_settings = new javax.swing.JPanel();
        jTextField_path = new javax.swing.JTextField();
        jTextField_expname = new javax.swing.JTextField();
        jLabel_path = new javax.swing.JLabel();
        jButton_setpath = new javax.swing.JButton();
        jLabel_expname = new javax.swing.JLabel();
        jLabel_numframes = new javax.swing.JLabel();
        jTextField_numframes = new javax.swing.JTextField();
        jLabel_waittime = new javax.swing.JLabel();
        jTextField_waittime = new javax.swing.JTextField();
        jButton_start = new javax.swing.JButton();
        jButton_stop = new javax.swing.JButton();
        jCheckBox_stopMaxUV = new javax.swing.JCheckBox();
        jPanel_display = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_progress = new javax.swing.JTextPane();
        jProgressBar_progress = new javax.swing.JProgressBar();
        jButton_configAdvanced = new javax.swing.JButton();
        jCheckBox_advanced = new javax.swing.JCheckBox();


        jLabel_path.setText("Path");

        jButton_setpath.setText("...");
        jButton_setpath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_setpathActionPerformed(evt);
            }
        });

        jLabel_expname.setText("Name");

        jLabel_numframes.setText("Number of frames:");

        jTextField_numframes.setText("50000");

        jLabel_waittime.setText("Waiting time (s):");

        jTextField_waittime.setText("0");

        jButton_start.setText("Start");
        jButton_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_startActionPerformed(evt);
            }
        });

        jButton_stop.setText("Stop");
        jButton_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_stopActionPerformed(evt);
            }
        });

        jCheckBox_stopMaxUV.setText("Stop on max UV");

        jCheckBox_advanced.setText("Advanced acquisition");
        jCheckBox_advanced.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(jCheckBox_advanced.isSelected()){
					jTextField_numframes.setEnabled(false);
					advancedacq = true;
				} else {
					jTextField_numframes.setEnabled(true);
					advancedacq = false;
				}
			}
        });

        javax.swing.GroupLayout jPanel_settingsLayout = new javax.swing.GroupLayout(jPanel_settings);
        jPanel_settings.setLayout(jPanel_settingsLayout);
        jPanel_settingsLayout.setHorizontalGroup(
            jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_path)
                    .addComponent(jTextField_expname)
                    .addGroup(jPanel_settingsLayout.createSequentialGroup()
                        .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_path)
                            .addComponent(jLabel_expname)
                            .addComponent(jLabel_numframes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_setpath, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_numframes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_settingsLayout.createSequentialGroup()
                        .addComponent(jLabel_waittime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_waittime, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_settingsLayout.createSequentialGroup()
                        .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_advanced)
                            .addComponent(jCheckBox_stopMaxUV)
                            .addGroup(jPanel_settingsLayout.createSequentialGroup()
                                .addComponent(jButton_start)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_stop)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_settingsLayout.setVerticalGroup(
            jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_path)
                    .addComponent(jButton_setpath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_expname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_expname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_numframes)
                    .addComponent(jTextField_numframes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_waittime)
                    .addComponent(jTextField_waittime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jCheckBox_advanced)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_stopMaxUV)
                .addGap(18, 18, 18)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_start, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );


        jScrollPane1.setViewportView(jTextPane_progress);

        jButton_configAdvanced.setText("Configure");
        jButton_configAdvanced.addActionListener(new ActionListener() 
        {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lauchAdvancedAcq();
			}
        });
        
        javax.swing.GroupLayout jPanel_displayLayout = new javax.swing.GroupLayout(jPanel_display);
        jPanel_display.setLayout(jPanel_displayLayout);
        jPanel_displayLayout.setHorizontalGroup(
            jPanel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_displayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_displayLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar_progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_displayLayout.createSequentialGroup()
                        .addComponent(jButton_configAdvanced)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_displayLayout.setVerticalGroup(
            jPanel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_displayLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar_progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_configAdvanced)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_display, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        

    	text = new StringText(jTextPane_progress);
        
    }// </editor-fold>                        

    private void jButton_setpathActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = fc.showSaveDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File folder = fc.getSelectedFile();
    	    jTextField_path.setText(folder.getAbsolutePath());
    	    
    	}
    }                                               

    private void jButton_startActionPerformed(java.awt.event.ActionEvent evt) { 
		int numFrames;
		String path;
		String acqname;
		int sleepTime;
		boolean stopmaxUV;
		
		numFrames = Integer.parseInt(jTextField_numframes.getText());
		path = jTextField_path.getText();
		acqname = jTextField_expname.getText();
		sleepTime = Integer.parseInt(jTextField_waittime.getText());
		stopmaxUV = jCheckBox_stopMaxUV.isSelected();
		
		if(advancedacq){
			acq.runAcqList(acqlist,path,acqname,sleepTime,stopmaxUV);
		} else {
			acq.runAcq(numFrames,path,acqname,sleepTime,stopmaxUV);
		}
    }                                             

    private void jButton_stopActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	acq.stopAcq();
    	text.setStopped();
    }                                            

    public void setAcqList(ArrayList<Acquisition> acqlist){
    	advancedacq = true;
    	System.out.println("Got acquisitions, n: "+acqlist.size());
    	this.acqlist = acqlist;
    }
    
    public void lauchAdvancedAcq(){
    	if(getPath().equals(null)){
    		 Object infoMessage;
			JOptionPane.showMessageDialog(null, "Please set a path first", "Error", JOptionPane.INFORMATION_MESSAGE);
    	} else {
	    	AdvancedAcqFrame acqframe = new AdvancedAcqFrame(this, sys_);
			acqframe.setVisible(true);
    	}
    }
    
    public String getPath(){
    	return jTextField_path.getText();
    }
    
    //////////////////////////////////////////
    private File folder;
    public StringText text;
    private AcqEngine acq;
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton_setpath;
    private javax.swing.JButton jButton_start;
    private javax.swing.JButton jButton_stop;
    private javax.swing.JCheckBox jCheckBox_stopMaxUV;
    private javax.swing.JLabel jLabel_expname;
    private javax.swing.JLabel jLabel_numframes;
    private javax.swing.JLabel jLabel_path;
    private javax.swing.JLabel jLabel_waittime;
    private javax.swing.JPanel jPanel_display;
    private javax.swing.JPanel jPanel_settings;
    public javax.swing.JProgressBar jProgressBar_progress;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_expname;
    private javax.swing.JTextField jTextField_numframes;
    private javax.swing.JTextField jTextField_path;
    private javax.swing.JTextField jTextField_waittime;
    private javax.swing.JTextPane jTextPane_progress;
    private javax.swing.JButton jButton_configAdvanced;
    private javax.swing.JCheckBox jCheckBox_advanced;
    
    // End of variables declaration                   
}
