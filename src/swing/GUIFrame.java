/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import graph.Chart;
import graph.TimeChart;
import ij.process.ImageProcessor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import threader.Threader;
import micromanager.Log;
import mmcorej.CMMCore;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class GUIFrame extends javax.swing.JFrame {

	
	MSystem sys_;
	Threader th_;
	Log log_;

    public GUIFrame(MSystem sys, Log log) {
    	sys_ = sys;
    	log_ = log;

    	//th_ = new Threader(this);
        th_ = new Threader(sys_, log_, this);

    	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
        opticsPanel = new OpticsPanel(sys_);
        focusPanel = new FocusPanel(sys_, th_);
        tabs = new Tabs(sys_, th_);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(opticsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));
        getContentPane().add(focusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));
        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 460, 250));

        pack();
 	    this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	this.addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	    	sys_.shutDown();
    	    	th_.stop();
    	    	log_.closeLog();
    	    	dispose();
    	    }
    	});
        
    }// </editor-fold>//GEN-END:initComponents

    /*
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFrame(new MSystem(), new Log()).setVisible(true);
            }
        });
    }
    */
    
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
    	return opticsPanel.uVPulsePanel.logarithmicJSlider;
    }
    
    public JTextField getUVtext(){
    	return opticsPanel.uVPulsePanel.jTextField_pulse;
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

    public boolean isNMSChecked(){
    	return tabs.activationPanel.isNMSchecked();
    }
    
    public void setNMSImageProcessor(ImageProcessor ipn){
    	tabs.activationPanel.setImageProcessor(ipn);
    }
    
    public boolean isUVTextSelected(){
    	return opticsPanel.uVPulsePanel.isTextSelected();
    }

    public ActivationPanel getActivateTab(){
    	return tabs.activationPanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public FocusPanel focusPanel;
    public OpticsPanel opticsPanel;
    public Tabs tabs;
    // End of variables declaration//GEN-END:variables
}
