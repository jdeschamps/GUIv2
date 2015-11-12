/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.TimeChart;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import micromanager.MConfiguration;
import threader.Threader;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class ActivationTab extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002444578776391643L;
	MSystem sys_;
	Threader th_;
	MConfiguration config_;
	ImageProcessor ip_;
	ImagePlus im_;
	boolean checkedNMS = false;
	DecimalFormat df;

    public ActivationTab(MSystem sys, Threader th, MConfiguration config) {
    	sys_ = sys;
    	th_ = th;
    	config_ = config;
    	ip_ = new ShortProcessor(200,200);
    	im_ = new ImagePlus("NMS result",ip_);
    	
    	df = new DecimalFormat();
		df.setDecimalSeparatorAlwaysShown(false);
    	
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void initComponents() {

        jPanel_left = new javax.swing.JPanel();
        jToggleButton_activate = new javax.swing.JToggleButton();
        jLabel_stdcoeff = new javax.swing.JLabel();
        jLabel_uvcoeff = new javax.swing.JLabel();
        jTextField_stdcoeff = new javax.swing.JTextField();
        jTextField_uvcoeff = new javax.swing.JTextField();
        jButton_GetN = new javax.swing.JButton();
        jTextField_N = new javax.swing.JTextField();
        jPanel_bottom = new javax.swing.JPanel();
        jLabel_cutoff = new javax.swing.JLabel();
        jTextField_cutoff = new javax.swing.JTextField();
        jButton_getcutoff = new javax.swing.JButton();
        jToggleButton_autocutoff = new javax.swing.JToggleButton();
        jCheckBox_showNMS = new javax.swing.JCheckBox();
        jButton_clear = new javax.swing.JButton();


        setPreferredSize(new java.awt.Dimension(390, 212));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jToggleButton_activate.setText("Activate");
        jToggleButton_activate.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_activate.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					th_.startUpdater("UV");
					activate = true;
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					th_.stopUpdater("UV");
					activate = false;
				}
            }
        });

        jLabel_stdcoeff.setText("Sd coeff:");

        jLabel_uvcoeff.setText("UV coeff:");

        jTextField_stdcoeff.setText(String.valueOf(config_.getSDcoeff()));

        jTextField_uvcoeff.setText(String.valueOf(config_.getUVcoeff()));

        jButton_GetN.setText("Get N");
        jButton_GetN.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_GetN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GetNActionPerformed(evt);
            }
        });

        jTextField_N.setText("0");


        javax.swing.GroupLayout jPanel_leftLayout = new javax.swing.GroupLayout(jPanel_left);
        jPanel_left.setLayout(jPanel_leftLayout);
        jPanel_leftLayout.setHorizontalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_activate, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGroup(jPanel_leftLayout.createSequentialGroup()
                        .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_uvcoeff, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_stdcoeff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_uvcoeff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_stdcoeff, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_GetN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_N))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_leftLayout.setVerticalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_stdcoeff)
                .addGap(2, 2, 2)
                .addComponent(jTextField_stdcoeff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_uvcoeff)
                .addGap(1, 1, 1)
                .addComponent(jTextField_uvcoeff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton_GetN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jToggleButton_activate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 280));


        //// Chart
        gr = new TimeChart("N","time","N",MConfiguration.maxNUV,300,300);
        add(gr.getChart(), new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 380, 230));
        
        jLabel_cutoff.setText("Cutoff:");

        jTextField_cutoff.setText("0");

        jButton_getcutoff.setText("Get cutoff");
        jButton_getcutoff.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_getcutoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_getcutoffActionPerformed(evt);
            }
        });
        
        jToggleButton_autocutoff.setText("Auto");
        jToggleButton_autocutoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_autocutoffActionPerformed(evt);
            }
        });
        
        jCheckBox_showNMS.setText("NMS");
        jCheckBox_showNMS.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
                jCheckBox_showNMSActionPerformed(evt);				
			}
        });
        

        jButton_clear.setText("Clear");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout jPanel_bottomLayout = new javax.swing.GroupLayout(jPanel_bottom);
        jPanel_bottom.setLayout(jPanel_bottomLayout);
        jPanel_bottomLayout.setHorizontalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel_cutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_getcutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_autocutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_clear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_showNMS, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_bottomLayout.setVerticalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_getcutoff)
                    .addComponent(jToggleButton_autocutoff)
                    .addComponent(jCheckBox_showNMS)
                    .addComponent(jLabel_cutoff)
                    .addComponent(jButton_clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        add(jPanel_bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 340, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GetNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GetNActionPerformed
    	
    	//System.out.println(df.format(gr.getLastPoint()));

    	jTextField_N.setText(String.valueOf(df.format(gr.getLastPoint())));
    }//GEN-LAST:event_jButton_GetNActionPerformed

    private void jButton_getcutoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_getcutoffActionPerformed
    	getcutoff = true;
    }//GEN-LAST:event_jButton_getcutoffActionPerformed

    private void jToggleButton_autocutoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_autocutoffActionPerformed
    	if(jToggleButton_autocutoff.isSelected()){
    		autocutoff = true;
    	} else {
    		autocutoff = false;
    	}
    }//GEN-LAST:event_jToggleButton_autocutoffActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	gr.clearChart();
    }       
    private void jCheckBox_showNMSActionPerformed(java.awt.event.ItemEvent evt) {                                                  
        if(evt.getStateChange() == ItemEvent.SELECTED){
        	System.out.println("Show img NMS");
        	checkedNMS = true;
        	im_.setProcessor(ip_);
        	im_.show();
        } else {
        	System.out.println("Close img NMS");
        	checkedNMS = false;
        	im_.close();
        }
    }             
    
    public void setImageProcessor(ImageProcessor ipn){
    	ip_ = ipn;
    	im_.setProcessor(ip_);
    	im_.updateAndRepaintWindow();
    }
    
    public boolean isNMSchecked(){
    	return checkedNMS;
    }

    public boolean isActivateOn(){
    	return jToggleButton_activate.isSelected();
    }

    public boolean isAutoCutoffOn(){
    	return jToggleButton_autocutoff.isSelected();
    }
    
    public boolean isCutoffNeeded(){
    	if(getcutoff){
    		getcutoff = false;
        	return true;
    	}
    	return false;
    }

    public double getCutoff(){
    	return Double.parseDouble(jTextField_cutoff.getText());
    }
    
    public void setCutoff(double val){
    	jTextField_cutoff.setText(String.valueOf(val));
    }

    public double getThreshold(){
    	return Double.parseDouble(jTextField_stdcoeff.getText());
    }

    public double getFeedback(){
    	return Double.parseDouble(jTextField_uvcoeff.getText());
    }

    public double getN(){
		//System.out.println("Sending N");
		//System.out.println(jTextField_N.getText());
    	return Double.parseDouble(jTextField_N.getText());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public double cutoff;																/// from config file
    public boolean getcutoff=false;
    public boolean autocutoff=false;
    public boolean activate=false;
    
    public TimeChart gr;
    private javax.swing.JButton jButton_GetN;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_getcutoff;
    private javax.swing.JCheckBox jCheckBox_showNMS;
    private javax.swing.JLabel jLabel_cutoff;
    private javax.swing.JLabel jLabel_stdcoeff;
    private javax.swing.JLabel jLabel_uvcoeff;
    private javax.swing.JPanel jPanel_bottom;
    private javax.swing.JPanel jPanel_left;
    private javax.swing.JTextField jTextField_N;
    public javax.swing.JTextField jTextField_cutoff;
    private javax.swing.JTextField jTextField_stdcoeff;
    private javax.swing.JTextField jTextField_uvcoeff;
    private javax.swing.JToggleButton jToggleButton_activate;
    private javax.swing.JToggleButton jToggleButton_autocutoff;
    // End of variables declaration//GEN-END:variables
}
