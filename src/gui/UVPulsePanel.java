/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JSlider;

import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import view.ListenerFactory;
import view.ListenerFactory.IntTextFieldListener;

public class UVPulsePanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2972231012673105880L;

	private ListenerFactory factory_;
	private MConfiguration config_;

    public UVPulsePanel(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }

    private void initComponents() {
        setBorder(javax.swing.BorderFactory.createTitledBorder("UV pulse"));
        
        jTextField_pulse = new javax.swing.JTextField();
        logarithmicJSlider = new LogarithmicJSlider(JSlider.VERTICAL,1, 1000, 10);																				//////////////////////////////////////////////////////////// here use values
        jTextField_maxpulse = new javax.swing.JTextField();
        
		IntTextFieldListener listener_maxpulse = factory_.createIntTextFieldListener(DefaultIdentifiers.id_laser+1, DefaultIdentifiers.id_laser_maxpulse, jTextField_maxpulse);
		jTextField_maxpulse.addActionListener(listener_maxpulse);
		jTextField_maxpulse.addFocusListener(listener_maxpulse);
        
		logarithmicJSlider.setPaintTicks(true);
		logarithmicJSlider.setPaintTrack(true);
		logarithmicJSlider.setPaintLabels(true);
		logarithmicJSlider.setMajorTickSpacing(10);
		logarithmicJSlider.setMinorTickSpacing(10);
		logarithmicJSlider.addMouseListener(factory_.createSliderListener(DefaultIdentifiers.id_laser+1, DefaultIdentifiers.id_laser_pulselength, logarithmicJSlider));

		IntTextFieldListener listener_pulse = factory_.createIntTextFieldListener(DefaultIdentifiers.id_laser+1, DefaultIdentifiers.id_laser_pulselength, jTextField_pulse);
        jTextField_pulse.addActionListener(listener_pulse);
        jTextField_pulse.addFocusListener(listener_pulse);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(logarithmicJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_maxpulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField_maxpulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logarithmicJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables        
    private javax.swing.JTextField jTextField_maxpulse;
    public javax.swing.JTextField jTextField_pulse;
    public LogarithmicJSlider logarithmicJSlider;
    // End of variables declaration//GEN-END:variables
}
