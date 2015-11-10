/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ButtonGroup;

import device.MSystem;
import micromanager.MConfiguration;

/**
 *
 * @author Ries
 */
public class FilterPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5026466950800445539L;
	MSystem sys_;

    public FilterPanel(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }

    private void initComponents() {

        jToggleButton_filter1 = new javax.swing.JToggleButton();
        jToggleButton_filter2 = new javax.swing.JToggleButton();
        jToggleButton_filter3 = new javax.swing.JToggleButton();
        jToggleButton_filter4 = new javax.swing.JToggleButton();
        jToggleButton_filter5 = new javax.swing.JToggleButton();
        jToggleButton_filter6 = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Filters"));
        setMaximumSize(new java.awt.Dimension(372, 49));
        setMinimumSize(new java.awt.Dimension(372, 49));
        setPreferredSize(new java.awt.Dimension(372, 49));
        setLayout(new java.awt.GridLayout(1, 0));

        jToggleButton_filter1.setForeground(MConfiguration.blue);
        jToggleButton_filter1.setText(MConfiguration.filters[0]);
        jToggleButton_filter1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter1ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter1);

        jToggleButton_filter2.setForeground(MConfiguration.green);
        jToggleButton_filter2.setText(MConfiguration.filters[1]);
        jToggleButton_filter2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter2ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter2);

        jToggleButton_filter3.setForeground(MConfiguration.red);
        jToggleButton_filter3.setText(MConfiguration.filters[2]);
        jToggleButton_filter3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter3ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter3);

        jToggleButton_filter4.setForeground(MConfiguration.black);
        jToggleButton_filter4.setText(MConfiguration.filters[3]);
        jToggleButton_filter4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter4ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter4);

        jToggleButton_filter5.setForeground(MConfiguration.neutral);
        jToggleButton_filter5.setText(MConfiguration.filters[4]);
        jToggleButton_filter5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter5ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter5);

        jToggleButton_filter6.setForeground(MConfiguration.neutral);
        jToggleButton_filter6.setText(MConfiguration.filters[5]);
        jToggleButton_filter6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_filter6ActionPerformed(evt);
            }
        });
        add(jToggleButton_filter6);
        
        ButtonGroup group=new ButtonGroup();
        group.add(jToggleButton_filter1);
        group.add(jToggleButton_filter2);
        group.add(jToggleButton_filter3);
        group.add(jToggleButton_filter4);
        group.add(jToggleButton_filter5);
        group.add(jToggleButton_filter6);
        
        int selected = sys_.getServoState(MConfiguration.servokeys[0]);
        switch(selected){
    	case 0:
    		jToggleButton_filter1.setSelected(true);
    		break;
    	case 1:
    		jToggleButton_filter2.setSelected(true);
    		break;
    	case 2:
    		jToggleButton_filter3.setSelected(true);
    		break;
    	case 3:
    		jToggleButton_filter4.setSelected(true);
    		break;
    	case 4:
    		jToggleButton_filter5.setSelected(true);
    		break;
    	case 5:
    		jToggleButton_filter6.setSelected(true);
    		break;
        }
        
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton_filter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter1ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 0);
    }//GEN-LAST:event_jToggleButton_filter1ActionPerformed

    private void jToggleButton_filter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter2ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 1);
    }//GEN-LAST:event_jToggleButton_filter2ActionPerformed

    private void jToggleButton_filter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter3ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 2);
    }//GEN-LAST:event_jToggleButton_filter3ActionPerformed

    private void jToggleButton_filter4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter4ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 3);
    }//GEN-LAST:event_jToggleButton_filter4ActionPerformed

    private void jToggleButton_filter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter5ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 4);
    }//GEN-LAST:event_jToggleButton_filter5ActionPerformed

    private void jToggleButton_filter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_filter6ActionPerformed
    	sys_.setServoState(MConfiguration.servokeys[0], 4);
    }//GEN-LAST:event_jToggleButton_filter6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton_filter1;
    private javax.swing.JToggleButton jToggleButton_filter2;
    private javax.swing.JToggleButton jToggleButton_filter3;
    private javax.swing.JToggleButton jToggleButton_filter4;
    private javax.swing.JToggleButton jToggleButton_filter5;
    private javax.swing.JToggleButton jToggleButton_filter6;
    // End of variables declaration//GEN-END:variables
}
