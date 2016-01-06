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


public class QPDTab extends javax.swing.JPanel {

	private static final long serialVersionUID = -3185947669212669882L;
	MSystem sys_;
	Threader th_;
	
    public QPDTab(MSystem sys, Threader th) {
    	sys_ = sys;
    	th_ = th;
        initComponents();
    }
              
    private void initComponents() {

        gr3 = new Chart("tot","X","Y",MConfiguration.maxNQPD[2],270,270);


        jToggleButton_monitor = new javax.swing.JToggleButton();
        jPanel_2Dgraph = new javax.swing.JPanel();
        jProgressBar = new javax.swing.JProgressBar();
        jPanel_graph = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(390, 212));

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
        jProgressBar.setOrientation(1);
        jProgressBar.setMaximum(700);
        jProgressBar.setMinimum(0);

        jPanel_graph.setLayout(new GridLayout(1, 1));
        jPanel_graph.add(gr3.getChart());
        
        javax.swing.GroupLayout jPanel_2DgraphLayout = new javax.swing.GroupLayout(jPanel_2Dgraph);
        jPanel_2Dgraph.setLayout(jPanel_2DgraphLayout);
        jPanel_2DgraphLayout.setHorizontalGroup(
            jPanel_2DgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_2DgraphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel_2DgraphLayout.setVerticalGroup(
            jPanel_2DgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_2DgraphLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel_2DgraphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton_monitor)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToggleButton_monitor)
                .addContainerGap())
            .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    public Chart gr3;
    private javax.swing.JPanel jPanel_2Dgraph;
    private javax.swing.JPanel jPanel_graph;
    public javax.swing.JProgressBar jProgressBar;
    private javax.swing.JToggleButton jToggleButton_monitor;
    // End of variables declaration                   
}
