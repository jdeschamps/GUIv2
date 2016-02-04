/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.ListenerFactory;
import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import device.MSystem;


/**
 *
 * @author Deschamps
 */
public class LensPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListenerFactory factory_;																			///////// keep???
	private MConfiguration config_;
	
	public LensPanel(ListenerFactory factory, MConfiguration config) {
		factory_ = factory;
		config_ = config;
		
        initComponents();
    }

    private void initComponents() {

        jToggleButton_bfp = new javax.swing.JToggleButton();
        jToggleButton_3DA = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));

        jToggleButton_bfp.setText(config_.getLensLabel(DefaultIdentifiers.id_bfp));
        jToggleButton_bfp.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_bfp.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_bfp, DefaultIdentifiers.id_lens_position));
        
        jToggleButton_3DA.setText(config_.getLensLabel(DefaultIdentifiers.id_3da));
        jToggleButton_3DA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_3DA.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_3da, DefaultIdentifiers.id_lens_position));
        
        
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
