/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ButtonGroup;

import view.ListenerFactory;
import device.MSystem;
import configuration.DefaultIdentifiers;
import configuration.MConfiguration;

/**
 *
 * @author Ries
 */
public class FilterPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5026466950800445539L;
	private ListenerFactory factory_;
	private MConfiguration config_;

    public FilterPanel(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }

    private void initComponents() {
    																										/////////////////////// try to get number filters
        jToggleButton_filter1 = new javax.swing.JToggleButton();
        jToggleButton_filter2 = new javax.swing.JToggleButton();
        jToggleButton_filter3 = new javax.swing.JToggleButton();
        jToggleButton_filter4 = new javax.swing.JToggleButton();
        jToggleButton_filter5 = new javax.swing.JToggleButton();
        jToggleButton_filter6 = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Filters"));
        setMaximumSize(new java.awt.Dimension(372, 49));
        setMinimumSize(new java.awt.Dimension(372, 49));
        setPreferredSize(new java.awt.Dimension(372, 49));
        setLayout(new java.awt.GridLayout(1, 0));

        jToggleButton_filter1.setForeground(config_.getFilterColor(0));
        jToggleButton_filter1.setText(config_.getFilterName(0));
        jToggleButton_filter1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter1.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,0));
        add(jToggleButton_filter1);


        jToggleButton_filter2.setForeground(config_.getFilterColor(1));
        jToggleButton_filter2.setText(config_.getFilterName(1));
        jToggleButton_filter2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter2.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,1));
        add(jToggleButton_filter2);


        jToggleButton_filter3.setForeground(config_.getFilterColor(2));
        jToggleButton_filter3.setText(config_.getFilterName(2));
        jToggleButton_filter3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter3.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,2));
        add(jToggleButton_filter3);


        jToggleButton_filter4.setForeground(config_.getFilterColor(3));
        jToggleButton_filter4.setText(config_.getFilterName(3));
        jToggleButton_filter4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter4.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,3));
        add(jToggleButton_filter4);


        jToggleButton_filter5.setForeground(config_.getFilterColor(4));
        jToggleButton_filter5.setText(config_.getFilterName(4));
        jToggleButton_filter5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter5.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,4));
        add(jToggleButton_filter5);


        jToggleButton_filter6.setForeground(config_.getFilterColor(5));
        jToggleButton_filter6.setText(config_.getFilterName(5));
        jToggleButton_filter6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_filter6.addItemListener(factory_.createJToggleButtonValueListener(DefaultIdentifiers.id_fw, DefaultIdentifiers.id_fw_position,5));
        add(jToggleButton_filter6);
        
        ButtonGroup group=new ButtonGroup();
        group.add(jToggleButton_filter1);
        group.add(jToggleButton_filter2);
        group.add(jToggleButton_filter3);
        group.add(jToggleButton_filter4);
        group.add(jToggleButton_filter5);
        group.add(jToggleButton_filter6);
 
        
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jToggleButton_filter1;
    private javax.swing.JToggleButton jToggleButton_filter2;
    private javax.swing.JToggleButton jToggleButton_filter3;
    private javax.swing.JToggleButton jToggleButton_filter4;
    private javax.swing.JToggleButton jToggleButton_filter5;
    private javax.swing.JToggleButton jToggleButton_filter6;
    // End of variables declaration//GEN-END:variables
}
