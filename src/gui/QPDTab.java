/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.Chart;
import graph.TimeChart;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import micromanager.MConfiguration;
import threader.Threader;
import device.MSystem;

/**
 *
 * @author Ries
 */
public class QPDTab extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3185947669212669882L;
	MSystem sys_;
	Threader th_;
	
    public QPDTab(MSystem sys, Threader th) {
    	sys_ = sys;
    	th_ = th;
        initComponents();
    }

    private void initComponents() {

        gr1 = new TimeChart("X","t","X",MConfiguration.maxNQPD[0],200,100,true);
        gr2 = new TimeChart("S","t","S",MConfiguration.maxNQPD[1],200,100,true);
        gr3 = new Chart("tot","X","Y",MConfiguration.maxNQPD[2],300,300);

        jPanel_left = new javax.swing.JPanel();
        jPanel_lefthigh = new javax.swing.JPanel();
        jPanel_leftlow = new javax.swing.JPanel();
        jPanel_right = new javax.swing.JPanel();
        jPanel_2Dgraph = new javax.swing.JPanel();
        jToggleButton_monitor = new javax.swing.JToggleButton();

        ////////////////////////////////////////////////////////////////////////////////////
        //// Left
        setPreferredSize(new java.awt.Dimension(390, 212));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel_left.setLayout(new java.awt.GridLayout(2, 1));

        // high
        jPanel_lefthigh.setLayout(new GridLayout(1, 1));
        jPanel_lefthigh.add(gr1.getChart());

        jPanel_left.add(jPanel_lefthigh);

        // low
        jPanel_leftlow.setLayout(new GridLayout(1, 1));
        jPanel_leftlow.add(gr2.getChart());
        jPanel_left.add(jPanel_leftlow);

        add(jPanel_left);
        
        ////////////////////////////////////////////////////////////////////////////////////
        //// Right
        jPanel_2Dgraph.setPreferredSize(new java.awt.Dimension(390, 212));

        jPanel_2Dgraph.setLayout(new GridLayout(1, 1));
        jPanel_2Dgraph.add(gr3.getChart());

        jToggleButton_monitor.setText("Monitor");
        jToggleButton_monitor.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange()==ItemEvent.SELECTED){
					th_.startUpdater("QPD");
				}else if(e.getStateChange()==ItemEvent.DESELECTED){
					th_.stopUpdater("QPD");
				}
            }
        });

        javax.swing.GroupLayout jPanel_rightLayout = new javax.swing.GroupLayout(jPanel_right);
        jPanel_right.setLayout(jPanel_rightLayout);
        jPanel_rightLayout.setHorizontalGroup(
            jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_rightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton_monitor)
                .addContainerGap())
        );
        jPanel_rightLayout.setVerticalGroup(
            jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rightLayout.createSequentialGroup()
                .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );


        add(jPanel_right);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public TimeChart gr1, gr2;
    public Chart gr3;
    private javax.swing.JPanel jPanel_2Dgraph;
    private javax.swing.JPanel jPanel_left;
    private javax.swing.JPanel jPanel_lefthigh;
    private javax.swing.JPanel jPanel_leftlow;
    private javax.swing.JPanel jPanel_right;
    private javax.swing.JToggleButton jToggleButton_monitor;
    // End of variables declaration//GEN-END:variables
}
