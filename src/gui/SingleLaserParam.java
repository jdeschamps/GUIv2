/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import device.MSystem;
import micromanager.MConfiguration;

/**
 *
 * @author Ries
 */
public class SingleLaserParam extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068642939086006022L;
	String label_;
	int maxpower_,currentmaxpower_;
	boolean ispowermodif_;
	Color color_;
	MSystem sys_;

    public SingleLaserParam(MSystem sys, String label, int maxpower, boolean ispowermodif, Color color) {
    	sys_ = sys;
    	label_ = label;
    	maxpower_ = maxpower;
    	currentmaxpower_ = maxpower;
    	ispowermodif_ = ispowermodif;
    	color_ = color;
        initComponents();
    }

    private void initComponents() {

        jLabel_behaviour = new javax.swing.JLabel();
        jComboBox_behaviour = new javax.swing.JComboBox();
        jLabel_pulse = new javax.swing.JLabel();
        jTextField_pulse = new javax.swing.JTextField();
        jLabel_maxpower = new javax.swing.JLabel();
        jTextField_maxpower = new javax.swing.JTextField();
        jSlider_pulse = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, label_, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, color_));

        jLabel_behaviour.setText("Behaviour :");

        jComboBox_behaviour.setModel(new javax.swing.DefaultComboBoxModel(MConfiguration.laserbehaviourlabel));
        jComboBox_behaviour.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		int val=((JComboBox) e.getSource()).getSelectedIndex();
	    		sys_.setLaserBehaviour(label_, val);
	    	}
        });
        

        jLabel_pulse.setText("Pulse length :");

        jTextField_pulse.setText("0");
        jTextField_pulse.addKeyListener(new KeyAdapter(){
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = ((javax.swing.JTextField) ke.getSource()).getText();
        	    if(!typed.matches("\\d+")) {
        	        return;
        	    }
        	    sys_.setLaserPulseLength(label_, Integer.parseInt(typed));
        	}
        });    

        jLabel_maxpower.setText("Max power (mW) :");

        jTextField_maxpower.setText(Integer.toString(maxpower_));
        jTextField_maxpower.setEditable(ispowermodif_);
        jTextField_maxpower.addKeyListener(new KeyAdapter(){
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = ((javax.swing.JTextField) ke.getSource()).getText();
        	    if(!typed.matches("\\d+")) {
        	        return;
        	    }
        	    sys_.setLaserMaxPower(Integer.parseInt(typed));
        	}
        });    

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_maxpower)
                            .addComponent(jLabel_pulse)
                            .addComponent(jLabel_behaviour))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_behaviour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_pulse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_maxpower, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_behaviour)
                    .addComponent(jComboBox_behaviour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_pulse)
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_maxpower)
                    .addComponent(jTextField_maxpower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_behaviourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_behaviourActionPerformed

    }//GEN-LAST:event_jComboBox_behaviourActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox_behaviour;
    private javax.swing.JLabel jLabel_behaviour;
    private javax.swing.JLabel jLabel_maxpower;
    private javax.swing.JLabel jLabel_pulse;
    private javax.swing.JSlider jSlider_pulse;
    private javax.swing.JTextField jTextField_maxpower;
    private javax.swing.JTextField jTextField_pulse;
    // End of variables declaration//GEN-END:variables
}