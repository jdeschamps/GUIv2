package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import advancedacq.AcqListWrapper;
import advancedacq.Acquisition;
import device.MSystem;
import threader.AcqEngine;
import utils.StringText;


@SuppressWarnings("serial")
public class AcqTab extends javax.swing.JPanel {

	MainFrame parent_;
	MSystem sys_;

	AcqListWrapper acqlist;
	boolean advancedacq = false;
	
    /**
     * Creates new form AcqTab
     */
    public AcqTab(MainFrame parent, MSystem sys) {
    	parent_ = parent;
    	sys_ = sys;
    	acq = new AcqEngine(parent_, sys_);
    	
    	acqlist = new AcqListWrapper();
    	
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
        jLabel_UVwaittime = new javax.swing.JLabel();
        jTextField_UVwaittime = new javax.swing.JTextField();
        jCheckBox_usenumberpos = new javax.swing.JCheckBox();
        jLabel_numberofpos = new javax.swing.JLabel();
        jSpinner_numberofpose=  new javax.swing.JSpinner();
        jButton_save = new javax.swing.JButton();
        jButton_load = new javax.swing.JButton();

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

        jLabel_waittime.setText("Initial delay (s):");

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
        jCheckBox_stopMaxUV.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(jCheckBox_stopMaxUV.isSelected()){
					jTextField_UVwaittime.setEnabled(true);
				} else {
					jTextField_UVwaittime.setEnabled(false);
				}
			}
        });
        
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
        
        jLabel_UVwaittime.setText("Stop on UV (s):");

        jTextField_UVwaittime.setText("0");
        jTextField_UVwaittime.setEnabled(false);


        jLabel_numberofpos.setText("Number of positions:");
        
        jSpinner_numberofpose.setModel(new SpinnerNumberModel(1, 0, 10000, 1));
        jSpinner_numberofpose.setEnabled(false);

        jCheckBox_usenumberpos.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(jCheckBox_usenumberpos.isSelected()){
					jSpinner_numberofpose.setEnabled(true);
				} else {
					jSpinner_numberofpose.setEnabled(false);
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
                            .addComponent(jCheckBox_stopMaxUV)
                            .addGroup(jPanel_settingsLayout.createSequentialGroup()
                                .addComponent(jButton_start)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_stop)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_settingsLayout.createSequentialGroup()
                        .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_UVwaittime)
                            .addGroup(jPanel_settingsLayout.createSequentialGroup()
                                .addComponent(jLabel_numberofpos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox_usenumberpos)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner_numberofpose, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_UVwaittime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_UVwaittime)
                    .addComponent(jTextField_UVwaittime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner_numberofpose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_numberofpos))
                    .addComponent(jCheckBox_usenumberpos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox_stopMaxUV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_start, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        
        jButton_save.setText("Save");
        jButton_save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAcqSettings();
			}
        });
        
        jButton_load.setText("Load");
        jButton_load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadAcqSettings();
			}
        });
        
        jScrollPane1.setViewportView(jTextPane_progress);

        jButton_configAdvanced.setText("Configure");
        jButton_configAdvanced.addActionListener(new ActionListener(){
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
                        .addComponent(jCheckBox_advanced)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_displayLayout.createSequentialGroup()
                        .addComponent(jButton_configAdvanced)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_load)))
                .addContainerGap())
        );
        jPanel_displayLayout.setVerticalGroup(
            jPanel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_displayLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar_progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jCheckBox_advanced)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_configAdvanced)
                    .addComponent(jButton_save)
                    .addComponent(jButton_load))
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

    protected void loadAcqSettings() {
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Acquisition settings","acq");
    	fc.setFileFilter(filter);
    	int returnVal = fc.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File folder = fc.getSelectedFile();
    	    acqlist.loadList(folder.getAbsolutePath());
    	    text.clear();
    	    
    	    String s = "";
        	
           	for(int i=0;i<acqlist.getList().size();i++){
               	s += acqlist.getList().get(i).settingsToString();
           	}
           	
           	text.add(s);
    	}
	}

	protected void saveAcqSettings() {
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Acquisition settings","acq");
    	fc.setFileFilter(filter);
    	int returnVal = fc.showSaveDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File folder = fc.getSelectedFile();
    	    acqlist.saveList(folder.getAbsolutePath());
    	}
	}

	private void jButton_setpathActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	JFileChooser fc = new JFileChooser();
    	fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = fc.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    File folder = fc.getSelectedFile();
    	    jTextField_path.setText(folder.getAbsolutePath());
    	    
    	}
    }                                               

    private void jButton_startActionPerformed(java.awt.event.ActionEvent evt) { 
		int numFrames, numPos;
		String path;
		String acqname;
		int sleepTime,UVsleepTime;
		boolean stopmaxUV, useNumpos;
		
		numFrames = Integer.parseInt(jTextField_numframes.getText());
		path = jTextField_path.getText();
		acqname = jTextField_expname.getText();
		sleepTime = Integer.parseInt(jTextField_waittime.getText());
		UVsleepTime = Integer.parseInt(jTextField_UVwaittime.getText());
		stopmaxUV = jCheckBox_stopMaxUV.isSelected();
		useNumpos = jCheckBox_usenumberpos.isSelected();
		numPos = (Integer) jSpinner_numberofpose.getValue();
		
		if(advancedacq){
	       	File theDir = new File(getPath());

	       	// if the directory does not exist, create it
	       	if (!theDir.exists()) {
		         boolean result = false;
		
		         try{
		             theDir.mkdir();
		             result = true;
		         } 
		         catch(SecurityException se){
		             //handle it
		         }        
		         if(result) {    

		         }
	       	}
	       	
	    	String s = "";
	    	
	       	for(int i=0;i<acqlist.getList().size();i++){
	           	s += acqlist.getList().get(i).settingsToString();
	       	}
	       	
	    	PrintWriter writer;
	    	try {
	    		if(getPath().length()>1){
	    			writer = new PrintWriter(new FileWriter(getPath()+"/AdvancedAcq"+".txt", true));
	    	       	writer.print(s);
	    	       	writer.close();
	    		} else {
	    			writer = new PrintWriter(new FileWriter("AdvancedAcq"+".txt", true));
	    	       	writer.print(s);
	    	       	writer.close();
	    		}
	    	} catch (FileNotFoundException e) {
	    		// TODO Auto-generated catch block
	    	      e.printStackTrace();
	    	} catch (UnsupportedEncodingException e) {
	    		// TODO Auto-generated catch block
	    	  e.printStackTrace();
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	acqlist.saveList(getPath()+"/AdvancedAcq");
	    	
			acq.runAcqList(acqlist.getList(),path,acqname,sleepTime,UVsleepTime,stopmaxUV,numPos,useNumpos);
		} else {
			acq.runAcq(numFrames,path,acqname,sleepTime,UVsleepTime,stopmaxUV,numPos,useNumpos);
		}
    }                                             

    private void jButton_stopActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	acq.stopAcq();
    	text.setStopped();
    }                                            

    public void setAcqList(ArrayList<Acquisition> acqlist){
    	advancedacq = true;
    	//System.out.println("Got acquisitions, n: "+acqlist.size());
    	String s = "";
    	
       	for(int i=0;i<acqlist.size();i++){
           	s += acqlist.get(i).settingsToString();
       	}

    	text.add(s);
    	
    	this.acqlist.setList(acqlist);
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
    private javax.swing.JButton jButton_load;
    private javax.swing.JButton jButton_save;
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
    private javax.swing.JTextField jTextField_UVwaittime;
    private javax.swing.JLabel jLabel_UVwaittime;
    
    private javax.swing.JCheckBox jCheckBox_usenumberpos;
    private javax.swing.JLabel jLabel_numberofpos;
    private javax.swing.JSpinner jSpinner_numberofpose;
    // End of variables declaration                   
}
