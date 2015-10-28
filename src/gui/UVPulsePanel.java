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
	boolean textselected = false;
	
    public UVPulsePanel(MSystem sys) {
    	sys_ = sys;
        initComponents();
    }

    private void initComponents() {
        setBorder(javax.swing.BorderFactory.createTitledBorder("UV pulse"));
        
        jTextField_pulse = new javax.swing.JTextField();
        logarithmicJSlider = new LogarithmicJSlider(JSlider.VERTICAL,1, 10000, 10);
        
		logarithmicJSlider.setPaintTicks(true);
		logarithmicJSlider.setPaintTrack(true);
		logarithmicJSlider.setPaintLabels(true);
		logarithmicJSlider.setMajorTickSpacing(10);
		logarithmicJSlider.setMinorTickSpacing(10);
		logarithmicJSlider.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {					
					if(logarithmicJSlider.getValue()<1000*sys_.getExposureTime()){
						try{
							jTextField_pulse.setText(String.valueOf(logarithmicJSlider.getValue()));
							sys_.setLaserPulseLength(MConfiguration.laserkeys[0], logarithmicJSlider.getValue());
						} catch(Exception ex){
				    		 sys_.writeToLog("Error setting UV pulse from slider to "+logarithmicJSlider.getValue());
						}
					}	    
			}});


        jTextField_pulse.setText(Double.toString(sys_.getUVPulse()));
        jTextField_pulse.addActionListener(new java.awt.event.ActionListener() {
	         public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	 int val = 0; 
		    	 
		    	 try{  
		    		 val = Integer.parseInt(jTextField_pulse.getText());
		    	 }catch (NumberFormatException e) { 
		    		 sys_.writeToLog("Error parsing UV text field to number.");
		    		 return;
		    	 }
		   
		    	 if(val>0){
		    		 try {
						if(val<1000*sys_.getExposureTime()){
							 logarithmicJSlider.setValue(val); 
							 sys_.setLaserPulseLength(MConfiguration.laserkeys[0], val);
						 }
					} catch (Exception e) {
						sys_.writeToLog("�xception when setting UV pulse to "+val);
					}
		    	 }else{
		    		try {
		    			logarithmicJSlider.setValue(1);
		    			sys_.setLaserPulseLength(MConfiguration.laserkeys[0], 0);
					} catch (Exception e) {
						sys_.writeToLog("�xception when setting UV pulse to 0");
					}
		    	 }
	         }
	    });
        jTextField_pulse.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	textselected = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
            	textselected = false;
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
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logarithmicJSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        
    	
        
        
    }// </editor-fold>//GEN-END:initComponents
    
    
    public boolean isTextSelected(){
    	return textselected;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField jTextField_pulse;
    public LogarithmicJSlider logarithmicJSlider;
    // End of variables declaration//GEN-END:variables
}