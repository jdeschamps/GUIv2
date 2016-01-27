/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Ries
 */
public class LaserControl extends javax.swing.JPanel {

    /**
     * Creates new form LaserControl
     */
    public LaserControl() {
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
        jToggleButton_userperc = new javax.swing.JToggleButton();
        jToggleButton_100perc = new javax.swing.JToggleButton();
        jToggleButton_20perc = new javax.swing.JToggleButton();
        jToggleButton_1perc = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 255)));

        jToggleButton_laserOperation.setText("ON");
        jToggleButton_laserOperation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_laserOperationActionPerformed(evt);
            }
        });

        jTextField_userInput.setText("50");

        jToggleButton_userperc.setText("50%");
        jToggleButton_userperc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_userperc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_userpercActionPerformed(evt);
            }
        });

        jToggleButton_100perc.setText("100%");
        jToggleButton_100perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_100perc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_100percActionPerformed(evt);
            }
        });

        jToggleButton_20perc.setText("20%");
        jToggleButton_20perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_20perc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_20percActionPerformed(evt);
            }
        });

        jToggleButton_1perc.setText("1%");
        jToggleButton_1perc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton_1perc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_1percActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton_1perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_20perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_100perc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_userperc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField_userInput, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_userperc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_100perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_20perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton_1perc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton_laserOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton_userpercActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_userpercActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_userpercActionPerformed

    private void jToggleButton_laserOperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_laserOperationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_laserOperationActionPerformed

    private void jToggleButton_100percActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_100percActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_100percActionPerformed

    private void jToggleButton_20percActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_20percActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_20percActionPerformed

    private void jToggleButton_1percActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_1percActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton_1percActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField_userInput;
    private javax.swing.JToggleButton jToggleButton_100perc;
    private javax.swing.JToggleButton jToggleButton_1perc;
    private javax.swing.JToggleButton jToggleButton_20perc;
    private javax.swing.JToggleButton jToggleButton_laserOperation;
    private javax.swing.JToggleButton jToggleButton_userperc;
    // End of variables declaration//GEN-END:variables
}
