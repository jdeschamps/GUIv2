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
import utils.utils;
import micromanager.MConfiguration;
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
	MSystem sys_;
	CommonThreader th_;
	
    public FocusPanel(MSystem sys, CommonThreader th) {
    	sys_ = sys;
    	th_ = th;
        initComponents();
    }

    private void initComponents() {

        jPanel_controls = new javax.swing.JPanel();
        jTextField_position = new javax.swing.JTextField();
        jToggleButton_lockz = new javax.swing.JToggleButton();
        jToggleButton_monitorZ = new javax.swing.JToggleButton();
        jLabel_position = new javax.swing.JLabel();
        jToggleButton_set0 = new javax.swing.JToggleButton();
        jTextField_0position = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Focus"));
        setMaximumSize(new java.awt.Dimension(462, 168));
        setMinimumSize(new java.awt.Dimension(462, 168));
        setPreferredSize(new java.awt.Dimension(462, 168));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        
        gr = new TimeChart("position","time","position",MConfiguration.maxNPI,370,220,false);
        
        //////////////////////////////////////////////////////////////////////////////////////////////// Position
        //////////////////////////
        jTextField_position.setText(String.valueOf(sys_.getPIPosition()));
        jTextField_position.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {}
			@Override
			public void focusLost(FocusEvent arg0) {
				 String typed = jTextField_position.getText();
	        	 if(!utils.isNumeric(typed)) {
	        	    return;
	        	 }
	        	 sys_.setStagePosition(Integer.parseInt(typed));
			}
         });
        jTextField_position.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 String typed = jTextField_position.getText();
	        	 if(!utils.isNumeric(typed)) {
	        		 return;
	        	 }
	        	 sys_.setStagePosition(Integer.parseInt(typed));
			}
        });
        

        //////////////////////////////////////////////////////////////////////////////////////////////// Lock
        //////////////////////////
        jToggleButton_lockz.setText("Lock");
        jToggleButton_lockz.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_lockz.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					//System.out.println("[GUI] Stage sensor 1");
					sys_.setStageSensor(1);
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					//System.out.println("[GUI] Stage sensor 0");
					sys_.setStageSensor(0);
				}
            }
        });



        //////////////////////////////////////////////////////////////////////////////////////////////// Monitor
        //////////////////////////
        jToggleButton_monitorZ.setText("Monitor");
        jToggleButton_monitorZ.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_monitorZ.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					th_.startUpdater("PI");
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					th_.stopUpdater("PI");
				}
			}
        });

        jLabel_position.setText("Position:");
        

        //////////////////////////////////////////////////////////////////////////////////////////////// 0 position
        //////////////////////////
        jToggleButton_set0.setText("Set 0");
        jToggleButton_set0.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_set0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	double val = sys_.getPIPosition();
            	gr.setZero(val);
            	jTextField_0position.setText(String.valueOf(val));
            }
        });

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
                            //.addComponent(jTextField_0position, javax.swing.GroupLayout.Alignment.LEADING)
                            //.addComponent(jToggleButton_set0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        		)
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
                //.addComponent(jToggleButton_set0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               // .addComponent(jTextField_0position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JToggleButton jToggleButton_set0;
    // End of variables declaration//GEN-END:variables
}
