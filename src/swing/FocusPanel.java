/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import threader.Threader;
import micromanager.Configuration;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class FocusPanel extends javax.swing.JPanel {

	MSystem sys_;
	Threader th_;
	
    public FocusPanel(MSystem sys, Threader th) {
    	sys_ = sys;
    	th_ = th;
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

        jPanel_controls = new javax.swing.JPanel();
        jTextField_position = new javax.swing.JTextField();
        jToggleButton_lock = new javax.swing.JToggleButton();
        jToggleButton_monitor = new javax.swing.JToggleButton();
        jLabel_position = new javax.swing.JLabel();
        jPanel_graph = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Focus"));
        setMaximumSize(new java.awt.Dimension(462, 168));
        setMinimumSize(new java.awt.Dimension(462, 168));
        setPreferredSize(new java.awt.Dimension(462, 168));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        
        
        jTextField_position.setText(String.valueOf(sys_.getPIPosition()));
        jTextField_position.addKeyListener(new KeyAdapter(){
        	@Override
        	public void keyReleased(KeyEvent ke) {
        	    String typed = jTextField_position.getText();
        	    if(!typed.matches("\\d+") || typed.length() > 3) {
        	        return;
        	    }
        	    sys_.setStagePosition(Integer.parseInt(typed));
        	}
        });   
        	
        jToggleButton_lock.setText("Lock");
        jToggleButton_lock.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_lock.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					sys_.setStageSensor(1);
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					sys_.setStageSensor(0);
				}
            }
        });


        jToggleButton_monitor.setText("Monitor");
        jToggleButton_monitor.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_monitor.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					th_.startUpdater("PI");
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					th_.stopUpdater("PI");
				}
			}
        });


        jLabel_position.setText("Position:");

        javax.swing.GroupLayout jPanel_controlsLayout = new javax.swing.GroupLayout(jPanel_controls);
        jPanel_controls.setLayout(jPanel_controlsLayout);
        jPanel_controlsLayout.setHorizontalGroup(
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_monitor, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jToggleButton_lock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_controlsLayout.createSequentialGroup()
                        .addComponent(jLabel_position)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField_position))
                .addContainerGap())
        );
        jPanel_controlsLayout.setVerticalGroup(
            jPanel_controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_position)
                .addGap(3, 3, 3)
                .addComponent(jTextField_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jToggleButton_monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton_lock, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel_controls);
        
        jPanel_graph.setPreferredSize(new Dimension(378,158));
        jPanel_graph.setLayout(new GridLayout(1, 1));
        focusGraph = new Graph(300,100,"Pos","time",true,false,true);
        jPanel_graph.add(focusGraph);
        
    /*    javax.swing.GroupLayout jPanel_graphLayout = new javax.swing.GroupLayout(jPanel_graph);
        jPanel_graph.setLayout(jPanel_graphLayout);
        jPanel_graphLayout.setHorizontalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanel_graphLayout.setVerticalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
*/
        add(jPanel_graph);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Graph focusGraph;
    private javax.swing.JLabel jLabel_position;
    private javax.swing.JPanel jPanel_controls;
    private javax.swing.JPanel jPanel_graph;
    private javax.swing.JTextField jTextField_position;
    private javax.swing.JToggleButton jToggleButton_monitor;
    private javax.swing.JToggleButton jToggleButton_lock;
    // End of variables declaration//GEN-END:variables
}
