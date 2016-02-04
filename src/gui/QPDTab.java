/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import graph.Chart;
import graph.TimeChart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import configuration.DefaultIdentifiers;
import configuration.MConfiguration;
import micromanager.utils;
import threader.CommonThreader;
import view.ListenerFactory;
import view.ListenerFactory.DoubleTextFieldListener;
import view.ListenerFactory.IntTextFieldListener;
import device.MSystem;


public class QPDTab extends javax.swing.JPanel {

	private static final long serialVersionUID = -3185947669212669882L;
	private ListenerFactory factory_;
	private MConfiguration configuration_;
	
    public QPDTab(ListenerFactory factory, MConfiguration configuration) {
    	factory_ = factory;
    	configuration_ = configuration;
    	
        initComponents();
    }
              
    private void initComponents() {

        gr3 = new Chart("tot","X","Y",configuration_.getGraphNumberPoints(DefaultIdentifiers.id_graph_qpd),270,270);

        jProgressBar = new javax.swing.JProgressBar();
        jToggleButton_monitor = new javax.swing.JToggleButton();
        jPanel_2Dgraph = new javax.swing.JPanel();
        jTextField_small = new javax.swing.JTextField();
        jTextField_large = new javax.swing.JTextField();
        jLabel_small = new javax.swing.JLabel();
        jLabel_large = new javax.swing.JLabel();
        jButton_upsmall = new javax.swing.JButton();
        jButton_uplarge = new javax.swing.JButton();
        jButton_downsmall = new javax.swing.JButton();
        jButton_downlarge = new javax.swing.JButton();
        jPanel_graph = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(390, 212));

        jToggleButton_monitor.setText("Monitor");
        jToggleButton_monitor.addItemListener(factory_.createJToggleButtonListener(DefaultIdentifiers.id_task, DefaultIdentifiers.id_task_monitorqpd));
        jProgressBar.setOrientation(1);
        jProgressBar.setMaximum(700);
        jProgressBar.setMinimum(0);

        jPanel_graph.setLayout(new GridLayout(1, 1));
        jPanel_graph.add(gr3.getChart());
        
        ///////////////////////////////////////////////////////////////////////////////////////////////// focus buttons and textfield
		DoubleTextFieldListener listener_smallsteps = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_smallstep, jTextField_small);
        jTextField_small.setText(configuration_.getDefaultSmallSteps());
        jTextField_small.addFocusListener(listener_smallsteps);
        jTextField_small.addActionListener(listener_smallsteps);

		DoubleTextFieldListener listener_largesteps = factory_.createDoubleTextFieldListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_largestep, jTextField_large);
        jTextField_large.setText(configuration_.getDefaultLargeSteps());
        jTextField_large.addFocusListener(listener_largesteps);
        jTextField_large.addActionListener(listener_largesteps);

        jLabel_small.setText(">");

        jLabel_large.setText(">>");

        jButton_upsmall.setText("^");
        jButton_upsmall.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_position));												////// here have trouble, does not comply

        jButton_uplarge.setText("^^");
        jButton_uplarge.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_position));

        jButton_downsmall.setText("v");
        jButton_downsmall.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_position));

        jButton_downlarge.setText("vv");
        jButton_downlarge.addActionListener(factory_.createJButtonListener(DefaultIdentifiers.id_objectivestage, DefaultIdentifiers.id_objectivestage_position));
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////// layouts
        javax.swing.GroupLayout jPanel_2DgraphLayout = new javax.swing.GroupLayout(jPanel_2Dgraph);
        jPanel_2Dgraph.setLayout(jPanel_2DgraphLayout);
        jPanel_2DgraphLayout.setHorizontalGroup(
            jPanel_2DgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_2DgraphLayout.createSequentialGroup()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_2DgraphLayout.setVerticalGroup(
            jPanel_2DgraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_monitor)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel_small)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField_small, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_upsmall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_uplarge, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton_downsmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_large)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_large, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton_downlarge)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton_uplarge)
                        .addGap(1, 1, 1)
                        .addComponent(jButton_upsmall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_small, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_small))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_large, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_large))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_downsmall)
                        .addGap(2, 2, 2)
                        .addComponent(jButton_downlarge)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jToggleButton_monitor)))
                .addContainerGap())
        );
    }// </editor-fold>                        


    /////////////////////////////////////////////////////////////////////////////////////////////
    ///// variable declaration
    public Chart gr3;
    private javax.swing.JButton jButton_downlarge;
    private javax.swing.JButton jButton_downsmall;
    private javax.swing.JButton jButton_uplarge;
    private javax.swing.JButton jButton_upsmall;
    private javax.swing.JLabel jLabel_large;
    private javax.swing.JLabel jLabel_small;
    private javax.swing.JPanel jPanel_2Dgraph;
    private javax.swing.JPanel jPanel_graph;
    public javax.swing.JProgressBar jProgressBar;
    private javax.swing.JTextField jTextField_large;
    private javax.swing.JTextField jTextField_small;
    private javax.swing.JToggleButton jToggleButton_monitor;
    // End of variables declaration                   
}

