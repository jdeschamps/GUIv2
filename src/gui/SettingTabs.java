/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import threader.CommonThreader;
import threader.UVThreader;
import view.ListenerFactory;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class SettingTabs extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 744836798730047169L;
	private ListenerFactory factory_;
	private MConfiguration config_;
	
    public SettingTabs(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }

    private void initComponents() {

        jTabbedPane_QPD = new javax.swing.JTabbedPane();
        qPDPanel = new QPDTab(factory_,config_);
        activationPanel = new ActivationTab(factory_,config_);
        laserParamTab = new LaserTab(factory_,config_);
        controlPanel = new LensPanel(factory_,config_);

        setMaximumSize(new java.awt.Dimension(462, 240));
        setMinimumSize(new java.awt.Dimension(440, 240));
        setPreferredSize(new java.awt.Dimension(462, 240));

        jTabbedPane_QPD.addTab(DefaultIdentifiers.id_tab_qpd, qPDPanel);
        jTabbedPane_QPD.addTab(DefaultIdentifiers.id_tab_activation, activationPanel);
        jTabbedPane_QPD.addTab(DefaultIdentifiers.id_tab_laser, laserParamTab);

        controlPanel.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane_QPD, javax.swing.GroupLayout.PREFERRED_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane_QPD)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public ActivationTab activationPanel;
    private LensPanel controlPanel;
    private javax.swing.JTabbedPane jTabbedPane_QPD;
    public LaserTab laserParamTab;
    public QPDTab qPDPanel;
    // End of variables declaration//GEN-END:variables
}
