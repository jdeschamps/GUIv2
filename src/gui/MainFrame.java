/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.Chart;
import graph.TimeChart;
import ij.process.ImageProcessor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import threader.Threader;
import micromanager.Log;
import micromanager.MConfiguration;
import mmcorej.CMMCore;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class MainFrame extends javax.swing.JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2764220934742005216L;
	MSystem sys_;
	Threader th_;
	Log log_;
	MConfiguration config_;

    public MainFrame(MSystem sys, Log log, MConfiguration config) {
    	sys_ = sys;
    	log_ = log;
    	config_ = config;
    	
    	//th_ = new Threader(this);
        th_ = new Threader(sys_, log_, this);

    	initComponents();
    }

    private void initComponents() {
    	    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
        opticsPanel = new ControlPanel(sys_);
        focusPanel = new FocusPanel(sys_, th_);
        tabs = new SettingTabs(sys_, th_, config_);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(opticsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));
        getContentPane().add(focusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 530, 200));
        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 530, 320));

        pack();
 	    this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	this.addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	    	System.out.println("Shutting down");
    	    	sys_.shutDown();
    	    	th_.stop();
    	    	log_.closeLog();
    	    	dispose();
    	    }
    	});
        
    }// </editor-fold>//GEN-END:initComponents

    ////////////////////////////////////////////////// Interface with Threader
    public TimeChart getFocusGraph(){
    	return focusPanel.gr;
    }
    
    public TimeChart getQPDGraph1(){
    	return tabs.qPDPanel.gr1;
    }    
    
    public TimeChart getQPDGraph2(){
    	return tabs.qPDPanel.gr2;
    }
    
    public Chart getQPDGraph3(){
    	return tabs.qPDPanel.gr3;
    }
    
    public TimeChart getNGraph(){
    	return tabs.activationPanel.gr;
    }
    
    public LogarithmicJSlider getUVSlider(){
    	return opticsPanel.uvPulsePanel.logarithmicJSlider;
    }
    
    public JTextField getUVtext(){
    	return opticsPanel.uvPulsePanel.jTextField_pulse;
    }
    
    public JTextField getUVCutoff(){
    	return tabs.activationPanel.jTextField_cutoff;
    }
    
    public boolean isNewCutOff(){
    	if(tabs.activationPanel.isAutoCutoffOn() || tabs.activationPanel.isCutoffNeeded()){
    		return true;
    	}
    	return false;
    }
    
    public void setRequestOff(){
    		tabs.activationPanel.setRequestOff();
    }

    public boolean isNMSChecked(){
    	return tabs.activationPanel.isNMSchecked();
    }
    
    public void setNMSImageProcessor(ImageProcessor ipn){
    	tabs.activationPanel.setImageProcessor(ipn);
    }
    
    public boolean isUVTextSelected(){
    	return opticsPanel.uvPulsePanel.isTextSelected();
    }

    public ActivationTab getActivateTab(){
    	return tabs.activationPanel;
    }
    
    public JSlider getUVParamSlider(){
    	return tabs.laserParamTab.singleLaserParamPanel1.jSlider_pulse;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public FocusPanel focusPanel;
    public ControlPanel opticsPanel;
    public SettingTabs tabs;
    // End of variables declaration//GEN-END:variables
}
