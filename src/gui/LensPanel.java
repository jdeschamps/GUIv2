/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import micromanager.MConfiguration;
import device.MSystem;


/**
 *
 * @author Ries
 */
public class LensPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MSystem sys_;
	String bpflabel_ = MConfiguration.servokeys[1];
	String astiglabel_ = MConfiguration.servokeys[2];
	
	public LensPanel(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }

    private void initComponents() {

        jToggleButton_bfp = new javax.swing.JToggleButton();
        jToggleButton_3DA = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));

        jToggleButton_bfp.setText(bpflabel_);
        jToggleButton_bfp.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_bfp.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					sys_.setServoState(bpflabel_, MConfiguration.bfpPosition);						//// maybe here have a different tab for this
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					sys_.setServoState(bpflabel_, 0);
				}
            }
        });
        if(sys_.getServoState(bpflabel_)==1){
        	jToggleButton_bfp.setSelected(true);
        } else {
        	jToggleButton_bfp.setSelected(false);
        }
        
        jToggleButton_3DA.setText(astiglabel_);
        jToggleButton_3DA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_3DA.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					sys_.setServoState(astiglabel_, 1);		
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					sys_.setServoState(astiglabel_, 0);		
				}
            }
        });
        if(sys_.getServoState(astiglabel_)==1){
        	jToggleButton_3DA.setSelected(true);
        } else {
        	jToggleButton_3DA.setSelected(false);
        }
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToggleButton_bfp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToggleButton_3DA, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton_bfp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jToggleButton_3DA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 136, Short.MAX_VALUE))
        );
    }
    
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton_3DA;
    private javax.swing.JToggleButton jToggleButton_bfp;
    // End of variables declaration//GEN-END:variables
}
