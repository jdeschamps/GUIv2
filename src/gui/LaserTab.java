/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import view.ListenerFactory;
import device.MSystem;
import configuration.MConfiguration;

/**
 *
 * @author Ries
 */
public class LaserTab extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362289722441010537L;
	private ListenerFactory factory_;
	private MConfiguration config_;

    public LaserTab(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }

    private void initComponents() {


        singleLaserParamPanel1 = new SingleLaserParam(factory_,config_,0,false);
        singleLaserParamPanel2 = new SingleLaserParam(factory_,config_,1,false);
        singleLaserParamPanel3 = new SingleLaserParam(factory_,config_,2,true);
        singleLaserParamPanel4 = new SingleLaserParam(factory_,config_,3,false);

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
