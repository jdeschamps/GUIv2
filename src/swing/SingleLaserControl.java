/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import micromanager.Configuration;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class SingleLaserControl extends javax.swing.JPanel {

	String label_;
	Color color_;
	MSystem sys_;
	
    public SingleLaserControl(MSystem sys, String label, Color color) {
    	sys_ = sys;
    	color_ = color;
    	label_ = label;
    	
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton_laserOperation = new javax.swing.JToggleButton();
        jTextField_userInput = new javax.swing.JTextField();
        jToggleButton_user = new javax.swing.JToggleButton();
        jToggleButton_100perc = new javax.swing.JToggleButton();
        jToggleButton_20perc = new javax.swing.JToggleButton();
        jToggleButton_1perc = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, label_, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, color_));

        jToggleButton_laserOperation.setBorderPainted(false);
        jToggleButton_laserOperation.setBorder(null);
        jToggleButton_laserOperation.setFocusable(false);
        jToggleButton_laserOperation.setContentAreaFilled(false);
        jToggleButton_laserOperation.setIcon(new ImageIcon("C:/Users/Ries/Desktop/MM/Micro-Manager-1.4.21/off.png"));
        jToggleButton_laserOperation.setSelectedIcon(new ImageIcon("C:/Users/Ries/Desktop/MM/Micro-Manager-1.4.21/on.png"));
        jToggleButton_laserOperation.setDisabledIcon(new ImageIcon("C:/Users/Ries/Desktop/MM/Micro-Manager-1.4.21/off.png"));
        
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

        jTextField_userInput.setText("50");
        jTextField_userInput.addKeyListener(new KeyAdapter(){
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = jTextField_userInput.getText();
        	    if(!typed.matches("\\d+") || typed.length() > 3) {
        	        return;
        	    }
        	    jToggleButton_user.setText(typed+"%");
        	    if(jToggleButton_user.isSelected()){
        	    	sys_.setLaserPowerPerc(label_, Integer.parseInt(typed));
        	    }
        	}
        	});     
        
        jToggleButton_user.setText("50%");
        jToggleButton_user.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_user.addItemListener(new ItemListener(){
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
        group.add(jToggleButton_user);
        group.add(jToggleButton_100perc);
        group.add(jToggleButton_20perc);
        group.add(jToggleButton_1perc);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jToggleButton_100perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToggleButton_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton_20perc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToggleButton_1perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_user)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_100perc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_20perc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_1perc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
        );
        
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField_userInput;
    private javax.swing.JToggleButton jToggleButton_100perc;
    private javax.swing.JToggleButton jToggleButton_1perc;
    private javax.swing.JToggleButton jToggleButton_20perc;
    private javax.swing.JToggleButton jToggleButton_laserOperation;
    private javax.swing.JToggleButton jToggleButton_user;
    // End of variables declaration//GEN-END:variables
}
