/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Ries
 */
public class SingleLaserParamPanel extends javax.swing.JPanel {

    /**
     * Creates new form SingleLaserParamPanel
     */
    public SingleLaserParamPanel() {
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

        jLabel_behaviour = new javax.swing.JLabel();
        jComboBox_behaviour = new javax.swing.JComboBox();
        jLabel_pulse = new javax.swing.JLabel();
        jTextField_pulse = new javax.swing.JTextField();
        jLabel_maxpower = new javax.swing.JLabel();
        jTextField_maxpower = new javax.swing.JTextField();
        jSlider_pulse = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Name"));

        jLabel_behaviour.setText("Behaviour :");

        jComboBox_behaviour.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Camera", "Rising", "Falling", "On", "Off" }));
        jComboBox_behaviour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_behaviourActionPerformed(evt);
            }
        });

        jLabel_pulse.setText("Pulse length :");

        jTextField_pulse.setText("0");

        jLabel_maxpower.setText("Max power (mW) :");

        jTextField_maxpower.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_maxpower)
                            .addComponent(jLabel_pulse)
                            .addComponent(jLabel_behaviour))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_behaviour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_pulse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_maxpower, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_behaviour)
                    .addComponent(jComboBox_behaviour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_pulse)
                    .addComponent(jTextField_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSlider_pulse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_maxpower)
                    .addComponent(jTextField_maxpower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_behaviourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_behaviourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_behaviourActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox_behaviour;
    private javax.swing.JLabel jLabel_behaviour;
    private javax.swing.JLabel jLabel_maxpower;
    private javax.swing.JLabel jLabel_pulse;
    private javax.swing.JSlider jSlider_pulse;
    private javax.swing.JTextField jTextField_maxpower;
    private javax.swing.JTextField jTextField_pulse;
    // End of variables declaration//GEN-END:variables
}