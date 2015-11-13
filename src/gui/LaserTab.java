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
	MConfiguration config_;

    public LaserTab(MSystem sys, MConfiguration config) {
    	sys_ = sys;
    	config_ = config;
        initComponents();
    }

    private void initComponents() {

        singleLaserParamPanel3 = new SingleLaserParam(sys_,MConfiguration.laserkeys[3],300, true, MConfiguration.green,config_.getLaser3DefaultBehaviour());
        singleLaserParamPanel2 = new SingleLaserParam(sys_,MConfiguration.laserkeys[1],100, false, MConfiguration.blue,config_.getLaser2DefaultBehaviour());
        singleLaserParamPanel4 = new SingleLaserParam(sys_,MConfiguration.laserkeys[2],100, false, MConfiguration.red,config_.getLaser4DefaultBehaviour());
        singleLaserParamPanel1 = new SingleLaserParam(sys_,MConfiguration.laserkeys[0],100, false, MConfiguration.uv,config_.getLaser1DefaultBehaviour());

        setLayout(new java.awt.GridLayout(2, 2));
        add(singleLaserParamPanel1);
        add(singleLaserParamPanel2);
        add(singleLaserParamPanel3);
        add(singleLaserParamPanel4);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public SingleLaserParam singleLaserParamPanel1;
    private SingleLaserParam singleLaserParamPanel2;
    private SingleLaserParam singleLaserParamPanel3;
    private SingleLaserParam singleLaserParamPanel4;
    // End of variables declaration//GEN-END:variables
}
