/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Ries
 */
public class UVPulsePanel extends javax.swing.JPanel {

    /**
     * Creates new form UVPulsePanel
     */
    public UVPulsePanel() {
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

    	this.setPreferredSize(new Dimension(93, 279));
        setBorder(javax.swing.BorderFactory.createTitledBorder("UV pulse"));
        

    /*    
        this.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.BOTH;

    	c.insets = new Insets(5,5,0,5);

    	jTextField_pulse = new JTextField("0",5);
		
    	jTextField_pulse.setSize(90,20);
    	jTextField_pulse.setFont(new Font("Arial", Font.BOLD, 16));

    	c.weighty = 0.1;

		c.weightx = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		
	
	
		this.add(jTextField_pulse,c);
		
		logarithmicJSlider1= new LogarithmicJSlider(JSlider.VERTICAL,1, 10000, 10);
		logarithmicJSlider1.setPaintTicks(true);
		logarithmicJSlider1.setPaintTrack(true);
		logarithmicJSlider1.setPaintLabels(true);
		logarithmicJSlider1.setMajorTickSpacing(10);
		logarithmicJSlider1.setMinorTickSpacing(10);
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.7;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 2;
		
		this.add(logarithmicJSlider1,c);
		c.insets = new Insets(0,5,10,5);
        */
        jTextField_pulse = new javax.swing.JTextField();
        logarithmicJSlider1 = new LogarithmicJSlider(JSlider.VERTICAL,1, 10000, 10);
		logarithmicJSlider1.setPaintTicks(true);
		logarithmicJSlider1.setPaintTrack(true);
		logarithmicJSlider1.setPaintLabels(true);
		logarithmicJSlider1.setMajorTickSpacing(10);
		logarithmicJSlider1.setMinorTickSpacing(10);

        setBorder(javax.swing.BorderFactory.createTitledBorder("UV pulse"));

        jTextField_pulse.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(logarithmicJSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logarithmicJSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        
    	
        
        
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField_pulse;
    private LogarithmicJSlider logarithmicJSlider1;
    // End of variables declaration//GEN-END:variables
}
