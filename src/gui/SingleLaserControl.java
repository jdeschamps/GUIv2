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

import utils.utils;
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
	String label_;
	Color color_;
	MSystem sys_;
	
    public SingleLaserControl(MSystem sys, String label, Color color) {
    	sys_ = sys;
    	color_ = color;
    	label_ = label;
    	
        initComponents();
    }

    private void initComponents() {

        jToggleButton_laserOperation = new javax.swing.JToggleButton();
        jTextField_userInput = new javax.swing.JTextField();
        jToggleButton_userperc = new javax.swing.JToggleButton();
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
        
        jToggleButton_laserOperation.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					sys_.setLaserOperation(label_, 1);
				} else if(e.getStateChange()==ItemEvent.DESELECTED){
					sys_.setLaserOperation(label_, 0);
				}
			}
        });
        if(sys_.getLaserOperation(label_)==1){
        	jToggleButton_laserOperation.setSelected(true);
        } else {
        	jToggleButton_laserOperation.setSelected(false);
        }
        
        jTextField_userInput.setText("50");
        /*jTextField_userInput.addKeyListener(new KeyAdapter(){        	
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = jTextField_userInput.getText();
        	    if(!utils.isNumeric(typed)) {
        	        return;
        	    }  
        	    int val = Integer.parseInt(typed);
        	    if(val<=100){
	        	    jToggleButton_userperc.setText(typed+"%");
	        	    if(jToggleButton_userperc.isSelected()){
	        	    	sys_.setLaserPowerPerc(label_, val);
	        	    }
        	    }
        	}
        	}); */
        jTextField_userInput.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {}
			@Override
			public void focusLost(FocusEvent arg0) {
	       	    String typed = jTextField_userInput.getText();
        	    if(!utils.isNumeric(typed)) {
        	        return;
        	    }  
        	    int val = Integer.parseInt(typed);
        	    if(val<=100){
	        	    jToggleButton_userperc.setText(typed+"%");
	        	    if(jToggleButton_userperc.isSelected()){
	        	    	sys_.setLaserPowerPerc(label_, val);
	        	    }
        	    }
        	}
         });
        jTextField_userInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	       	    String typed = jTextField_userInput.getText();
        	    if(!utils.isNumeric(typed)) {
        	        return;
        	    }  
        	    int val = Integer.parseInt(typed);
        	    if(val<=100){
	        	    jToggleButton_userperc.setText(typed+"%");
	        	    if(jToggleButton_userperc.isSelected()){
	        	    	sys_.setLaserPowerPerc(label_, val);
	        	    }
        	    }
        	}
        });
        
        jToggleButton_userperc.setText("50%");
        jToggleButton_userperc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_userperc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
        	    	sys_.setLaserPowerPerc(label_, Integer.parseInt(jTextField_userInput.getText()));
				}
			}
        });

        jToggleButton_100perc.setText("100%");
        jToggleButton_100perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_100perc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
    				sys_.setLaserPowerPerc(label_,100);
    			}
			}
        });

        jToggleButton_20perc.setText("20%");
        jToggleButton_20perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_20perc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
    				sys_.setLaserPowerPerc(label_,20);
    			}
			}
        });

        jToggleButton_1perc.setText("1%");
        jToggleButton_1perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_1perc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
    				sys_.setLaserPowerPerc(label_,1);
    			}
			}
        });

        ButtonGroup group=new ButtonGroup();
        group.add(jToggleButton_userperc);
        group.add(jToggleButton_100perc);
        group.add(jToggleButton_20perc);
        group.add(jToggleButton_1perc);
        
        jToggleButton_100perc.setSelected(true);
        sys_.setLaserPowerPerc(label_, 100);
        
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
    private javax.swing.JToggleButton jToggleButton_userperc;
    // End of variables declaration//GEN-END:variables
}
