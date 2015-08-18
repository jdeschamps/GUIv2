/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

/**
 *
 * @author Ries
 */
public class ActivationPanel extends javax.swing.JPanel {

    /**
     * Creates new form ActivationPanel
     */
    public ActivationPanel() {
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
        jToggleButton_activate = new javax.swing.JToggleButton();
        jLabel_param1 = new javax.swing.JLabel();
        jLabel_param2 = new javax.swing.JLabel();
        jTextField_param1 = new javax.swing.JTextField();
        jTextField_param2 = new javax.swing.JTextField();
        jButton_GetN = new javax.swing.JButton();
        jTextField_N = new javax.swing.JTextField();
        jPanel_graph = new javax.swing.JPanel();
        jPanel_bottom = new javax.swing.JPanel();
        jLabel_threshold = new javax.swing.JLabel();
        jTextField_threshold = new javax.swing.JTextField();
        jButton_getthreshold = new javax.swing.JButton();
        jToggleButton_autothreshold = new javax.swing.JToggleButton();

        setPreferredSize(new java.awt.Dimension(390, 212));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButton_activate.setText("Activate");
        jToggleButton_activate.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_activate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_activateActionPerformed(evt);
            }
        });

        jLabel_param1.setText("Param 1:");

        jLabel_param2.setText("Param 2:");

        jTextField_param1.setText("0.5");

        jTextField_param2.setText("0.5");

        jButton_GetN.setText("Get N");
        jButton_GetN.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_GetN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GetNActionPerformed(evt);
            }
        });

        jTextField_N.setText("0");

        javax.swing.GroupLayout jPanel_leftLayout = new javax.swing.GroupLayout(jPanel_left);
        jPanel_left.setLayout(jPanel_leftLayout);
        jPanel_leftLayout.setHorizontalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_activate, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGroup(jPanel_leftLayout.createSequentialGroup()
                        .addGroup(jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_param2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_param1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_param2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_param1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_GetN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_N))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_leftLayout.setVerticalGroup(
            jPanel_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_param1)
                .addGap(2, 2, 2)
                .addComponent(jTextField_param1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_param2)
                .addGap(1, 1, 1)
                .addComponent(jTextField_param2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_GetN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField_N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton_activate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel_left, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 210));

        javax.swing.GroupLayout jPanel_graphLayout = new javax.swing.GroupLayout(jPanel_graph);
        jPanel_graph.setLayout(jPanel_graphLayout);
        jPanel_graphLayout.setHorizontalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel_graphLayout.setVerticalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        add(jPanel_graph, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 320, 160));

        jLabel_threshold.setText("Threshold:");

        jTextField_threshold.setText("0");

        jButton_getthreshold.setText("Get threshold");
        jButton_getthreshold.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_getthreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_getthresholdActionPerformed(evt);
            }
        });

        jToggleButton_autothreshold.setText("Auto");
        jToggleButton_autothreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_autothresholdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_bottomLayout = new javax.swing.GroupLayout(jPanel_bottom);
        jPanel_bottom.setLayout(jPanel_bottomLayout);
        jPanel_bottomLayout.setHorizontalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_threshold))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_getthreshold)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_autothreshold)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel_bottomLayout.setVerticalGroup(
            jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bottomLayout.createSequentialGroup()
                .addComponent(jLabel_threshold)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_getthreshold)
                    .addComponent(jToggleButton_autothreshold))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel_bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 320, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton_activateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_activateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_activateActionPerformed

    private void jButton_GetNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GetNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GetNActionPerformed

    private void jButton_getthresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_getthresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_getthresholdActionPerformed

    private void jToggleButton_autothresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_autothresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_autothresholdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_GetN;
    private javax.swing.JButton jButton_getthreshold;
    private javax.swing.JLabel jLabel_param1;
    private javax.swing.JLabel jLabel_param2;
    private javax.swing.JLabel jLabel_threshold;
    private javax.swing.JPanel jPanel_bottom;
    private javax.swing.JPanel jPanel_graph;
    private javax.swing.JPanel jPanel_left;
    private javax.swing.JTextField jTextField_N;
    private javax.swing.JTextField jTextField_param1;
    private javax.swing.JTextField jTextField_param2;
    private javax.swing.JTextField jTextField_threshold;
    private javax.swing.JToggleButton jToggleButton_activate;
    private javax.swing.JToggleButton jToggleButton_autothreshold;
    // End of variables declaration//GEN-END:variables
}
