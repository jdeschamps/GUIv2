/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.TimeChart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import threader.CommonThreader;
import view.ListenerFactory;
import view.ListenerFactory.DoubleTextFieldListener;
import view.ListenerFactory.IntTextFieldListener;
import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import micromanager.utils;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class FocusPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5035259490968319272L;
	private ListenerFactory factory_;
	private MConfiguration config_;
	
    public FocusPanel(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    	
        initComponents();
    }

    private void initComponents() {

        jPanel_controls = new javax.swing.JPanel();
        jTextField_position = new javax.swing.JTextField();
        jToggleButton_lockz = new javax.swing.JToggleButton();
        jToggleButton_monitorZ = new javax.swing.JToggleButton();
        jLabel_position = new javax.swing.JLabel();
        jButton_set0 = new javax.swing.JButton();
        jTextField_0position = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Focus"));
        setMaximumSize(new java.awt.Dimension(462, 168));
        setMinimumSize(new java.awt.Dimension(462, 168));
        setPreferredSize(new java.awt.Dimension(462, 168));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        
        gr = new TimeChart("position","time","position",config_.getGraphNumberPoints(DefaultIdentifiers.id_graph_pi),370,220,false);
        
        //////////////////////////////////////////////////////////////////////////////////////////////// Position
        //////////////////////////
		DoubleTextFieldListener listener_maxpulse = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_position, jTextField_position);
        jTextField_position.addFocusListener(listener_maxpulse);
        jTextField_position.addActionListener(listener_maxpulse);
        

        //////////////////////////////////////////////////////////////////////////////////////////////// Lock
        //////////////////////////
        jToggleButton_lockz.setText("Lock");
        jToggleButton_lockz.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_lockz.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_sensor));



        //////////////////////////////////////////////////////////////////////////////////////////////// Monitor
        //////////////////////////
        jToggleButton_monitorZ.setText("Monitor");
        jToggleButton_monitorZ.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_monitorZ.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_monitorz));

        jLabel_position.setText("Position:");
        

        //////////////////////////////////////////////////////////////////////////////////////////////// 0 position
        //////////////////////////
        jButton_set0.setText("Set 0");
        jButton_set0.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_set0.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_0position));

        jTextField_0position.setText("0");
        jTextField_0position.setEditable(false);
/*
        javax.swing.GroupLayout jPanel_controlsLayout = new javax.swing.GroupLayout(jPanel_controls);
        jPanel_controls.setLayout(jPanel_controlsLayout);
        jPanel_controlsLayout.setHorizontalGroup( 
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton_lockz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_position, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_monitorZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jTextField_position))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel_controlsLayout.setVerticalGroup(
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_position)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jToggleButton_monitorZ, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton_lockz, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
*/
        javax.swing.GroupLayout jPanel_controlsLayout = new javax.swing.GroupLayout(jPanel_controls);
        jPanel_controls.setLayout(jPanel_controlsLayout);
        jPanel_controlsLayout.setHorizontalGroup(
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_controlsLayout.createSequentialGroup()
                        .addGroup(jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jToggleButton_lockz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton_monitorZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField_position, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_position, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_controlsLayout.createSequentialGroup()
                        .addGroup(jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_0position, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_set0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addGap(0, 29, Short.MAX_VALUE))))
        );
        jPanel_controlsLayout.setVerticalGroup(
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel_position)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_set0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_0position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton_monitorZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_lockz)
                .addGap(36, 36, 36))
        );
        

        add(jPanel_controls);
        add(gr.getChart());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public TimeChart gr;
    private javax.swing.JLabel jLabel_position;
    private javax.swing.JPanel jPanel_controls;
    private javax.swing.JTextField jTextField_position;
    private javax.swing.JToggleButton jToggleButton_lockz;
    private javax.swing.JToggleButton jToggleButton_monitorZ;
    private javax.swing.JTextField jTextField_0position;
    private javax.swing.JButton jButton_set0;
    // End of variables declaration//GEN-END:variables
}
