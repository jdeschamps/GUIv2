/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.TimeChart;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import micromanager.utils;
import threader.UVThreader;
import view.ListenerFactory;
import view.ListenerFactory.DoubleTextFieldListener;
import view.ListenerFactory.IntTextFieldListener;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class ActivationTab extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8002444578776391643L;
	private ListenerFactory factory_;
	private MConfiguration config_;
	private ImageProcessor ip_;
	private ImagePlus im_;
	
    public ActivationTab(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
    	ip_ = new ShortProcessor(200,200);
    	im_ = new ImagePlus("NMS result",ip_);

        initComponents();
    }
    
    private void initComponents() {

        jPanel_left = new javax.swing.JPanel();
        jToggleButton_activate = new javax.swing.JToggleButton();
        jLabel_stdcoeff = new javax.swing.JLabel();
        jLabel_uvcoeff = new javax.swing.JLabel();
        jTextField_stdcoeff = new javax.swing.JTextField();
        jTextField_uvcoeff = new javax.swing.JTextField();
        jLabel_dT = new javax.swing.JLabel();
        jTextField_dT = new javax.swing.JTextField();
        jButton_GetN = new javax.swing.JButton();
        jTextField_N = new javax.swing.JTextField();
        jPanel_bottom = new javax.swing.JPanel();
        jLabel_cutoff = new javax.swing.JLabel();
        jTextField_cutoff = new javax.swing.JTextField();
        jButton_getcutoff = new javax.swing.JButton();
        jToggleButton_autocutoff = new javax.swing.JToggleButton();
        jCheckBox_activate = new javax.swing.JCheckBox();
        jCheckBox_showNMS = new javax.swing.JCheckBox();
        jButton_clear = new javax.swing.JButton();


        setPreferredSize(new java.awt.Dimension(390, 212));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Toggle button Activate
        jToggleButton_activate.setText("Activate");
        jToggleButton_activate.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_activate.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_monitoruv));

        jCheckBox_activate.setSelected(false);
        jCheckBox_activate.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_changeuv));
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Text Fields first part
        /////////////////////////////////////////////////////////////////////////////////////////////// sdcoeff
		DoubleTextFieldListener listener_sdcoeff = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_sdcoeff, jTextField_stdcoeff);
        jTextField_stdcoeff.setText(config_.getDefaultSDcoeff());
        jTextField_stdcoeff.addFocusListener(listener_sdcoeff);
        jTextField_stdcoeff.addActionListener(listener_sdcoeff);
        
        /////////////////////////////////////////////////////////////////////////////////////////////// uvcoeff
		DoubleTextFieldListener listener_uvcoeff = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_uvcoeff, jTextField_uvcoeff);
        jTextField_uvcoeff.setText(config_.getDefaultUVcoeff());
        jTextField_uvcoeff.addFocusListener(listener_uvcoeff);
        jTextField_uvcoeff.addActionListener(listener_uvcoeff);
        
        /////////////////////////////////////////////////////////////////////////////////////////////// N
        jTextField_N.setText("0");
		DoubleTextFieldListener listener_n0 = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_n0, jTextField_N);
        jTextField_N.addFocusListener(listener_n0);
        jTextField_N.addActionListener(listener_n0);
        
        /////////////////////////////////////////////////////////////////////////////////////////////// dT
        //jTextField_dT.setText("10");
		//DoubleTextFieldListener listener_uvcoeff = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_settings, DefaultIdentifiers.id_settings_uvcoeff, jTextField_uvcoeff);
        //jTextField_dT.addFocusListener();
        //jTextField_dT.addActionListener();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// JButton first part
        jButton_GetN.setText("Get N");
        jButton_GetN.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_GetN.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_getn));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Labels
        jLabel_stdcoeff.setText("Sd coeff:");
        jLabel_uvcoeff.setText("UV coeff:");
        jLabel_dT.setText("dT:");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Layout
        javax.swing.GroupLayout jPanel_leftLayout = new javax.swing.GroupLayout(jPanel_left);
        jPanel_left.setLayout(jPanel_leftLayout);
        jPanel_leftLayout.setHorizontalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_activate, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGroup(jPanel_leftLayout.createSequentialGroup()
                        .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_uvcoeff)
                                .addComponent(jLabel_stdcoeff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_uvcoeff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_stdcoeff)
                                .addComponent(jButton_GetN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_N, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_dT)
                                .addComponent(jTextField_dT))
                            .addComponent(jCheckBox_activate))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_leftLayout.setVerticalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_stdcoeff)
                .addGap(2, 2, 2)
                .addComponent(jTextField_stdcoeff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_uvcoeff)
                .addGap(1, 1, 1)
                .addComponent(jTextField_uvcoeff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_dT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_dT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton_GetN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_activate)
                .addGap(1, 1, 1)
                .addComponent(jToggleButton_activate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 280));


        //// Chart
        gr = new TimeChart("N","time","N",config_.getGraphNumberPoints(DefaultIdentifiers.id_graph_uv),300,300, true);
        add(gr.getChart(), new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 380, 230));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        /// cutoff
        jLabel_cutoff.setText("Cutoff:");

		DoubleTextFieldListener listener_cutoff = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_cutoff, jTextField_cutoff);
        jTextField_cutoff.setText("0");
        jTextField_cutoff.addFocusListener(listener_cutoff);
        jTextField_cutoff.addActionListener(listener_cutoff);

        jButton_getcutoff.setText("Get cutoff");
        jButton_getcutoff.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_getcutoff.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_getcutoff));
        
        jToggleButton_autocutoff.setText("Auto");
        jToggleButton_autocutoff.addActionListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_autocutoff));
        
        jCheckBox_showNMS.setText("NMS");
        jCheckBox_showNMS.setSelected(false);
        jCheckBox_showNMS.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_displaynms));
        

        jButton_clear.setText("Clear");
        jButton_clear.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_cleargraph));


        javax.swing.GroupLayout jPanel_bottomLayout = new javax.swing.GroupLayout(jPanel_bottom);
        jPanel_bottom.setLayout(jPanel_bottomLayout);
        jPanel_bottomLayout.setHorizontalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel_cutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_getcutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_autocutoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_clear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_showNMS, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_bottomLayout.setVerticalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cutoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_getcutoff)
                    .addComponent(jToggleButton_autocutoff)
                    .addComponent(jCheckBox_showNMS)
                    .addComponent(jLabel_cutoff)
                    .addComponent(jButton_clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        add(jPanel_bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 340, 30));
    }
       
    /*private void jCheckBox_showNMSActionPerformed(java.awt.event.ItemEvent evt) {                                                  
        if(evt.getStateChange() == ItemEvent.SELECTED){
        	System.out.println("Show img NMS");
        	checkedNMS = true;
        	im_.setProcessor(ip_);
        	im_.show();
        } else {
        	System.out.println("Close img NMS");
        	checkedNMS = false;
        	im_.close();
        }
    }     */        
    
    public void setImageProcessor(ImageProcessor ipn){																									/////////// keep?
    	ip_ = ipn;
    	im_.setProcessor(ip_);
    	im_.updateAndRepaintWindow();
    }
    
    public TimeChart gr;
    private javax.swing.JButton jButton_GetN;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_getcutoff;
    private javax.swing.JCheckBox jCheckBox_showNMS;
    private javax.swing.JCheckBox jCheckBox_activate;
    private javax.swing.JLabel jLabel_cutoff;
    private javax.swing.JLabel jLabel_dT;
    private javax.swing.JLabel jLabel_stdcoeff;
    private javax.swing.JLabel jLabel_uvcoeff;
    private javax.swing.JPanel jPanel_bottom;
    private javax.swing.JPanel jPanel_left;
    private javax.swing.JTextField jTextField_N;
    public javax.swing.JTextField jTextField_cutoff;
    private javax.swing.JTextField jTextField_stdcoeff;
    private javax.swing.JTextField jTextField_uvcoeff;
    private javax.swing.JTextField jTextField_dT;
    private javax.swing.JToggleButton jToggleButton_activate;
    private javax.swing.JToggleButton jToggleButton_autocutoff;
    // End of variables declaration//GEN-END:variables
}
