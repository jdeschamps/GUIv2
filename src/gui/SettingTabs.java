/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import threader.Threader;
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
	MSystem sys_;
	Threader th_;
	
    public SettingTabs(MSystem sys, Threader th) {
    	sys_ = sys;
    	th_ = th;
        initComponents();
    }

    private void initComponents() {

        jTabbedPane_QPD = new javax.swing.JTabbedPane();
        qPDPanel = new QPDTab(sys_,th_);
        activationPanel = new ActivationTab(sys_,th_);
        laserParamTab = new LaserTab(sys_);
        controlPanel = new LensPanel(sys_);

        setMaximumSize(new java.awt.Dimension(462, 240));
        setMinimumSize(new java.awt.Dimension(440, 240));
        setPreferredSize(new java.awt.Dimension(462, 240));

        jTabbedPane_QPD.addTab("QPD", qPDPanel);
        jTabbedPane_QPD.addTab("Activation", activationPanel);
        jTabbedPane_QPD.addTab("Lasers", laserParamTab);

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
    private LaserTab laserParamTab;
    public QPDTab qPDPanel;
    // End of variables declaration//GEN-END:variables
}