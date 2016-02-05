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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

import utils.Bool2DecConverter;
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
	int maxpower_,currentmaxpower_,defaultBehaviour_;
	boolean ispowermodif_;
	Color color_;
	MSystem sys_;

    public SingleLaserParam(MSystem sys, String label, int maxpower, boolean ispowermodif, Color color, int defaultBehaviour) {
    	sys_ = sys;
    	label_ = label;
    	maxpower_ = maxpower;
    	currentmaxpower_ = maxpower;
    	ispowermodif_ = ispowermodif;
    	color_ = color;
    	defaultBehaviour_ = defaultBehaviour;
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
        jLabel_sequence = new javax.swing.JLabel();
        jTextField_sequence = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, label_, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, color_));

        jLabel_behaviour.setText("Behaviour :");

        jComboBox_behaviour.setModel(new javax.swing.DefaultComboBoxModel(MConfiguration.laserbehaviourlabel));
        jComboBox_behaviour.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		int val=((JComboBox) e.getSource()).getSelectedIndex();
	    		sys_.setLaserBehaviour(label_, val);
	    	}
        });
        jComboBox_behaviour.setSelectedIndex(defaultBehaviour_);
       //System.out.println("Behaviour selected for "+label_+" "+defaultBehaviour_);

        jLabel_pulse.setText("Pulse length :");

        jTextField_pulse.setText("0");
        jTextField_pulse.addKeyListener(new KeyAdapter(){																///////////////////////////////////////////////////////////////////////////m forgotten in the UV activation scheme
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = ((javax.swing.JTextField) ke.getSource()).getText();
        	    if(!typed.matches("\\d+")) {
        	        return;
        	    }
				int max  = 1000*sys_.getExposureTime() < MConfiguration.mojomaxpulse ? (int) (1000*sys_.getExposureTime()) : MConfiguration.mojomaxpulse;
				int val = Integer.parseInt(typed);
				if(val<max){
					sys_.setLaserPulseLength(label_, val);
					jSlider_pulse.setMaximum(max);
					jSlider_pulse.setValue(val);
				} else {
					sys_.setLaserPulseLength(label_, max);
					jSlider_pulse.setMaximum(max);
					jSlider_pulse.setValue(max);
				}
        	}
        });    
        
        jSlider_pulse.setMaximum(MConfiguration.mojomaxpulse);
        jSlider_pulse.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				 //System.out.println("[UV] mouse was released");

					int max  = 1000*sys_.getExposureTime() < MConfiguration.mojomaxpulse ? (int) (1000*sys_.getExposureTime()) : MConfiguration.mojomaxpulse;
					jSlider_pulse.setMaximum(max);
					if(jSlider_pulse.getValue()<max){
						try{
							jTextField_pulse.setText(String.valueOf(jSlider_pulse.getValue()));
							sys_.setLaserPulseLength(label_, jSlider_pulse.getValue());
						} catch(Exception ex){
				    		 sys_.writeToLog("Error setting UV pulse from slider to "+jSlider_pulse.getValue());
						}
					} else {
						jTextField_pulse.setText(String.valueOf(max));
						sys_.setLaserPulseLength(label_, max);
					}
			}});


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
        
        jLabel_sequence.setText("Sequence:");

        jTextField_sequence.setText("1111111111111111");
        jTextField_sequence.setMargin(new java.awt.Insets(2, 2, 2, 1));
        jTextField_sequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	int val = Bool2DecConverter.getDecimal16bits(jTextField_sequence.getText());
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
                        .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_sequence, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_sequence, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_maxpower)
                    .addComponent(jTextField_maxpower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_sequence)
                    .addComponent(jTextField_sequence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox_behaviour;
    private javax.swing.JLabel jLabel_behaviour;
    private javax.swing.JLabel jLabel_maxpower;
    private javax.swing.JLabel jLabel_pulse;
    public javax.swing.JSlider jSlider_pulse;
    private javax.swing.JTextField jTextField_maxpower;
    private javax.swing.JTextField jTextField_pulse;
    private javax.swing.JTextField jTextField_sequence;
    private javax.swing.JLabel jLabel_sequence;
    // End of variables declaration//GEN-END:variables
}
