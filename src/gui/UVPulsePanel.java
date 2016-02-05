/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;

import utils.utils;
import micromanager.MConfiguration;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class UVPulsePanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8987000512087033422L;
	MSystem sys_;
	private int maxpulse;
	
	//boolean textselected = false;
	
    public UVPulsePanel(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }

    private void initComponents() {
        setBorder(javax.swing.BorderFactory.createTitledBorder("UV pulse"));
        
        jTextField_pulse = new javax.swing.JTextField();
        logarithmicJSlider = new LogarithmicJSlider(JSlider.VERTICAL,1, MConfiguration.maxpulsedefault, 10);
        jTextField_maxpulse = new javax.swing.JTextField();
        
		jTextField_maxpulse.setText(String.valueOf(MConfiguration.maxpulsedefault));
		jTextField_maxpulse.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(java.awt.event.ActionEvent evt) {
				 //System.out.println("[UV] max action was performed");
		      	 
		    	 String s = jTextField_maxpulse.getText();
		    	 if(!utils.isNumeric(s)){
		    		 maxpulse = MConfiguration.maxpulsedefault;
		    		 jTextField_maxpulse.setText(String.valueOf(MConfiguration.maxpulsedefault));
		    	 } else {
		    		 maxpulse = (int) Math.round(Double.parseDouble(s));
		    	 } 
		    	 
		    	 if(maxpulse>0){
		    		 try {
						if(maxpulse<1000*sys_.getExposureTime()){
							logarithmicJSlider.setMaxWithin(maxpulse);
						 } else {
							maxpulse = (int) (1000*sys_.getExposureTime());
				    		logarithmicJSlider.setMaxWithin((int) (1000*sys_.getExposureTime()));
				    		jTextField_maxpulse.setText(String.valueOf((int) 1000*sys_.getExposureTime()));
						}
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting max UV pulse to "+maxpulse);
					}
		    	 }
	         }
	    });
       jTextField_pulse.addFocusListener(new FocusListener() {
           @Override
           public void focusGained(FocusEvent ex) {
           	//textselected = true;
           }

           @Override
           public void focusLost(FocusEvent ex) {
				 //System.out.println("[UV] max action was performed");
		      	 
		    	 String s = jTextField_maxpulse.getText();
		    	 if(!utils.isNumeric(s)){
		    		 maxpulse = MConfiguration.maxpulsedefault;
		    		 jTextField_maxpulse.setText(String.valueOf(MConfiguration.maxpulsedefault));
		    	 } else {
		    		 maxpulse = (int) Math.round(Double.parseDouble(s));
		    	 } 
		    	 
		    	 if(maxpulse>0){
		    		 try {
						if(maxpulse<1000*sys_.getExposureTime()){
							logarithmicJSlider.setMaxWithin(maxpulse);
						 } else {
							maxpulse = (int) (1000*sys_.getExposureTime());
				    		logarithmicJSlider.setMaxWithin((int) (1000*sys_.getExposureTime()));
				    		jTextField_maxpulse.setText(String.valueOf(1000*sys_.getExposureTime()));
						}
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting max UV pulse to "+maxpulse);
					}
		    	 }
	         }
	    });
        
        
		logarithmicJSlider.setPaintTicks(true);
		logarithmicJSlider.setPaintTrack(true);
		logarithmicJSlider.setPaintLabels(true);
		logarithmicJSlider.setMajorTickSpacing(10);
		logarithmicJSlider.setMinorTickSpacing(10);
		logarithmicJSlider.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {				
				  //System.out.println("[UV] mouse was released");
				  int val = logarithmicJSlider.getValue();
				  logarithmicJSlider.setValueWithin(val);
				  
				  try{
					  jTextField_pulse.setText(String.valueOf(logarithmicJSlider.getValue()));
					  sys_.setLaserPulseLength(MConfiguration.laserkeys[0], logarithmicJSlider.getValue());
				  } catch(Exception ex){
					  sys_.writeToLog("Error setting UV pulse from slider to "+logarithmicJSlider.getValue());
				  }  
			}});
		logarithmicJSlider.setValue(sys_.getUVPulse()>0 ? (int)sys_.getUVPulse() : 1);
		logarithmicJSlider.setMaximum((int) (1000*sys_.getExposureTime()));
		
        jTextField_pulse.setText(Integer.toString((int) sys_.getUVPulse()));
        jTextField_pulse.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(java.awt.event.ActionEvent evt) {
				 //System.out.println("[UV] action was performed");
	        	 int val = 0; 
	        	 String s = jTextField_pulse.getText();
	        	 if(!utils.isNumeric(s)){
	        		 return;
	        	 } else {
	        		 val = Integer.parseInt(s);
	        	 }
		   
		    	 if(val>0){
		    		 try {
		    			 logarithmicJSlider.setValueWithin(val); 
		    			 sys_.setLaserPulseLength(MConfiguration.laserkeys[0], logarithmicJSlider.getValue());
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting UV pulse to "+val);
					}
		    	 }else{
		    		try {
		    			logarithmicJSlider.setValueWithin(1);
		    			sys_.setLaserPulseLength(MConfiguration.laserkeys[0], 0);
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting UV pulse to 0");
					}
		    	 }
	         }
	    });
        jTextField_pulse.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	//textselected = true;
            }

            @Override
            public void focusLost(FocusEvent ex) {
				 //System.out.println("[UV] action was performed");
	        	 int val = 0; 
	        	 String s = jTextField_pulse.getText();
	        	 if(!utils.isNumeric(s)){
	        		 return;
	        	 } else {
	        		 val = Integer.parseInt(s);
	        	 }
		   
		    	 if(val>0){
		    		 try {
		    			 logarithmicJSlider.setValueWithin(val); 
		    			 sys_.setLaserPulseLength(MConfiguration.laserkeys[0], logarithmicJSlider.getValue());
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting UV pulse to "+val);
					}
		    	 }else{
		    		try {
		    			logarithmicJSlider.setValueWithin(1);
		    			sys_.setLaserPulseLength(MConfiguration.laserkeys[0], 0);
					} catch (Exception e) {
						sys_.writeToLog("Exception when setting UV pulse to 0");
					}
		    	 }
	         }
	    });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(logarithmicJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_maxpulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField_maxpulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logarithmicJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
    	
        
        
    }// </editor-fold>//GEN-END:initComponents
    
    public int getMaxPulse(){
    	 return maxpulse;
    }
    
   /* public boolean isTextSelected(){
    	return textselected;
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables        
    private javax.swing.JTextField jTextField_maxpulse;
    public javax.swing.JTextField jTextField_pulse;
    public LogarithmicJSlider logarithmicJSlider;
    // End of variables declaration//GEN-END:variables
}
