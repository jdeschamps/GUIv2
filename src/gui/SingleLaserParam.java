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

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import view.ListenerFactory;
import view.ListenerFactory.DoubleTextFieldListener;
import view.ListenerFactory.IntTextFieldListener;
import device.MSystem;
import configuration.DefaultIdentifiers;
import configuration.MConfiguration;

/**
 *
 * @author Ries
 */
public class SingleLaserParam extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068642939086006022L;
	private String label_;
	private int maxpower_,currentmaxpower_,defaultBehaviour_;
	private boolean ispowermodif_;
	private Color color_;

	private ListenerFactory factory_;
	private MConfiguration configuration_;
	
    public SingleLaserParam(ListenerFactory factory, MConfiguration configuration, int numlaser, boolean ispowermodif) {
    	factory_ = factory;
    	configuration_ = configuration;
    	
    	label_ = configuration_.getLaserKeys(numlaser);
    	maxpower_ = configuration_.getLaserMaxPower(numlaser);
    	currentmaxpower_ = maxpower_;																					//////////// what is this?????
    	ispowermodif_ = ispowermodif;
    	color_ = configuration_.getLaserColor(numlaser);
    	defaultBehaviour_ = configuration_.getLaserDefaultBehavior(numlaser);
    	
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

        jComboBox_behaviour.setModel(new DefaultComboBoxModel(configuration_.getLaserBehaviorList()));
        jComboBox_behaviour.addActionListener(factory_.createJToggleButtonValueListener(label_, DefaultIdentifiers.id_laser_behavior,0));
        jComboBox_behaviour.setSelectedIndex(defaultBehaviour_);

        jLabel_pulse.setText("Pulse length :");

        jTextField_pulse.setText("0");
		IntTextFieldListener listener_pulse = factory_.createIntTextFieldListener(label_, DefaultIdentifiers.id_laser_pulselength, jTextField_pulse);
        jTextField_pulse.addActionListener(listener_pulse);
        jTextField_pulse.addFocusListener(listener_pulse);
        
        jSlider_pulse.setMaximum(configuration_.getMaxPulseLasers());
        jSlider_pulse.addMouseListener(factory_.createSliderListener(label_, DefaultIdentifiers.id_laser_pulselength, jSlider_pulse));

        jLabel_maxpower.setText("Max power (mW) :");

        jTextField_maxpower.setText(Integer.toString(maxpower_));
        jTextField_maxpower.setEditable(ispowermodif_);
    	DoubleTextFieldListener listener_maxpower = factory_.createDoubleTextFieldListener(label_, DefaultIdentifiers.id_laser_maxpower, jTextField_pulse);
    	jTextField_maxpower.addActionListener(listener_maxpower);
    	jTextField_maxpower.addFocusListener(listener_maxpower);  

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox_behaviour;
    private javax.swing.JLabel jLabel_behaviour;
    private javax.swing.JLabel jLabel_maxpower;
    private javax.swing.JLabel jLabel_pulse;
    public javax.swing.JSlider jSlider_pulse;
    private javax.swing.JTextField jTextField_maxpower;
    private javax.swing.JTextField jTextField_pulse;
    // End of variables declaration//GEN-END:variables
}
