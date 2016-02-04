/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import configuration.DefaultIdentifiers;
import view.ListenerFactory;
import view.ListenerFactory.IntTextFieldListener;
import micromanager.utils;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class SingleLaserControl extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6007026837984553670L;
	/**
	 * 
	 */
	private String label_;
	private Color color_;
	private ListenerFactory factory_;
	
    public SingleLaserControl(ListenerFactory factory, String label, Color color) {
    	factory_ = factory;
    	color_ = color;
    	label_ = label;
    	
        initComponents();
    }

    private void initComponents() {

        jToggleButton_laserOperation = new javax.swing.JToggleButton();
        jTextField_userInput = new javax.swing.JTextField();
        jToggleButton_userperc = new CustomValueToggle(50);
        jToggleButton_100perc = new javax.swing.JToggleButton();
        jToggleButton_20perc = new javax.swing.JToggleButton();
        jToggleButton_1perc = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, label_, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, color_));

        jToggleButton_laserOperation.setBorderPainted(false);
        jToggleButton_laserOperation.setBorder(null);
        jToggleButton_laserOperation.setFocusable(false);
        jToggleButton_laserOperation.setContentAreaFilled(false);
        jToggleButton_laserOperation.setIcon(new ImageIcon("off.png"));
        jToggleButton_laserOperation.setSelectedIcon(new ImageIcon("on.png"));
        jToggleButton_laserOperation.setDisabledIcon(new ImageIcon("off.png"));
        
        jToggleButton_laserOperation.addItemListener(factory_.createJToggleButtonListener(label_, DefaultIdentifiers.id_laser_operation));
        
        jTextField_userInput.setText("50");
        
		IntTextFieldListener listener_powerperc = factory_.createIntTextFieldListener(label_, DefaultIdentifiers.id_laser_userpowerperc, jTextField_userInput);
        jTextField_userInput.addFocusListener(listener_powerperc);
        jTextField_userInput.addActionListener(listener_powerperc);
        
        jToggleButton_userperc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_userperc.addActionListener(factory_.createCustomToggleButtonListener(label_, DefaultIdentifiers.id_laser_powerperc,jToggleButton_userperc));

        jToggleButton_100perc.setText("100%");
        jToggleButton_100perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_100perc.addItemListener(factory_.createJToggleButtonValueListener(label_, DefaultIdentifiers.id_laser_powerperc,100));

        jToggleButton_20perc.setText("20%");
        jToggleButton_20perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_20perc.addItemListener(factory_.createJToggleButtonValueListener(label_, DefaultIdentifiers.id_laser_powerperc,20));

        jToggleButton_1perc.setText("1%");
        jToggleButton_1perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_1perc.addItemListener(factory_.createJToggleButtonValueListener(label_, DefaultIdentifiers.id_laser_powerperc,1));

        group = new ButtonGroup();
        group.add(jToggleButton_userperc);
        group.add(jToggleButton_100perc);
        group.add(jToggleButton_20perc);
        group.add(jToggleButton_1perc);
                    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_1perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_20perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_100perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_userperc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_userperc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_100perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_20perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton_1perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField_userInput;
    private javax.swing.JToggleButton jToggleButton_100perc;
    private javax.swing.JToggleButton jToggleButton_1perc;
    private javax.swing.JToggleButton jToggleButton_20perc;
    private javax.swing.JToggleButton jToggleButton_laserOperation;
    private CustomValueToggle jToggleButton_userperc;
    private javax.swing.ButtonGroup group;
    // End of variables declaration//GEN-END:variables
}
