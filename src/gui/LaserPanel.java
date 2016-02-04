/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import configuration.MConfiguration;
import view.ListenerFactory;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class LaserPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -994102071852952181L;
	private ListenerFactory factory_;
	private MConfiguration config_;

    public LaserPanel(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }
    
    private void initComponents() {
        laserControl1 = new SingleLaserControl(factory_,config_.getLaserKeys(0),config_.getLaserColor(0));																			/////// here numbers problem for the lasers
        laserControl2 = new SingleLaserControl(factory_,config_.getLaserKeys(1),config_.getLaserColor(1));
        laserControl3 = new SingleLaserControl(factory_,config_.getLaserKeys(2),config_.getLaserColor(2));						/////////// problem also with the laser identifiers
        laserControl4 = new SingleLaserControl(factory_,config_.getLaserKeys(3),config_.getLaserColor(3));

        setLayout(new java.awt.GridBagLayout());
        add(laserControl1, new java.awt.GridBagConstraints());
        add(laserControl2, new java.awt.GridBagConstraints());
        add(laserControl3, new java.awt.GridBagConstraints());
        add(laserControl4, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SingleLaserControl laserControl1;
    private SingleLaserControl laserControl2;
    private SingleLaserControl laserControl3;
    private SingleLaserControl laserControl4;
    // End of variables declaration//GEN-END:variables
}
