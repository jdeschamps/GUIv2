/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.GridLayout;

import device.MSystem;

/**
 *
 * @author Ries
 */
public class QPDPanel extends javax.swing.JPanel {

	MSystem sys_;

    public QPDPanel(MSystem sys) {
    	sys_ = sys;
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
        jPanel_lefthigh.add(new Graph(120,60,"X","time",false,false,false));

        jPanel_left.add(jPanel_lefthigh);

        // low
        jPanel_leftlow.setLayout(new GridLayout(1, 1));
        jPanel_leftlow.add(new Graph(120,60,"S","time",false,false,false));
        jPanel_left.add(jPanel_leftlow);

        add(jPanel_left);
        
        ////////////////////////////////////////////////////////////////////////////////////
        //// Right
        jPanel_2Dgraph.setPreferredSize(new java.awt.Dimension(390, 212));

        jPanel_2Dgraph.setLayout(new GridLayout(1, 1));
        jPanel_2Dgraph.add(new Graph(150,150,"Y","X",false,false,false));

        jToggleButton_monitor.setText("Monitor");
        jToggleButton_monitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_monitorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_rightLayout = new javax.swing.GroupLayout(jPanel_right);
        jPanel_right.setLayout(jPanel_rightLayout);
        jPanel_rightLayout.setHorizontalGroup(
            jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
            .addGroup(jPanel_rightLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jToggleButton_monitor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_rightLayout.setVerticalGroup(
            jPanel_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rightLayout.createSequentialGroup()
                .addComponent(jPanel_2Dgraph, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        add(jPanel_right);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton_monitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_monitorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_monitorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_2Dgraph;
    private javax.swing.JPanel jPanel_left;
    private javax.swing.JPanel jPanel_lefthigh;
    private javax.swing.JPanel jPanel_leftlow;
    private javax.swing.JPanel jPanel_right;
    private javax.swing.JToggleButton jToggleButton_monitor;
    // End of variables declaration//GEN-END:variables
}
