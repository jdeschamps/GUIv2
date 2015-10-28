/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import device.MSystem;
import micromanager.MConfiguration;

/**
 *
 * @author Ries
 */
public class LaserPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -994102071852952181L;
	MSystem sys_;

    public LaserPanel(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }
    
    private void initComponents() {
        laserControl1 = new SingleLaserControl(sys_,MConfiguration.laserkeys[0],MConfiguration.uv);
        laserControl2 = new SingleLaserControl(sys_,MConfiguration.laserkeys[1],MConfiguration.blue);
        laserControl3 = new SingleLaserControl(sys_,MConfiguration.laserkeys[3],MConfiguration.green);
        laserControl4 = new SingleLaserControl(sys_,MConfiguration.laserkeys[2],MConfiguration.red);

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
