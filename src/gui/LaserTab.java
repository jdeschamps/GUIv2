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
public class LaserTab extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362289722441010537L;
	MSystem sys_;

    public LaserTab(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }

    private void initComponents() {

        singleLaserParamPanel3 = new SingleLaserParam(sys_,MConfiguration.laserkeys[3],300, true,MConfiguration.green);
        singleLaserParamPanel2 = new SingleLaserParam(sys_,MConfiguration.laserkeys[1],100, false,MConfiguration.blue);
        singleLaserParamPanel4 = new SingleLaserParam(sys_,MConfiguration.laserkeys[2],100, false,MConfiguration.red);
        singleLaserParamPanel1 = new SingleLaserParam(sys_,MConfiguration.laserkeys[0],100, false,MConfiguration.uv);

        setLayout(new java.awt.GridLayout(2, 2));
        add(singleLaserParamPanel1);
        add(singleLaserParamPanel2);
        add(singleLaserParamPanel3);
        add(singleLaserParamPanel4);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SingleLaserParam singleLaserParamPanel1;
    private SingleLaserParam singleLaserParamPanel2;
    private SingleLaserParam singleLaserParamPanel3;
    private SingleLaserParam singleLaserParamPanel4;
    // End of variables declaration//GEN-END:variables
}
