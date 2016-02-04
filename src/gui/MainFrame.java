/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;

import configuration.MConfiguration;
import view.ListenerFactory;


public class MainFrame extends javax.swing.JFrame {

	private ListenerFactory factory_;
	private MConfiguration config_;
	
    public MainFrame(ListenerFactory factory, MConfiguration config) {
    	factory_ = factory;
    	config_ = config;
    
    	initComponents();
    }

    private void initComponents() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
        opticsPanel = new ControlPanel(factory_, config_);
        focusPanel = new FocusPanel(factory_, config_);
        tabs = new SettingTabs(factory_, config_);

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(opticsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 290));
        getContentPane().add(focusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 530, 200));
        getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 530, 320));

        pack();
 	    this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	this.addWindowListener(factory_.createFrameListener());
    }

    public FocusPanel focusPanel;
    public ControlPanel opticsPanel;
    public SettingTabs tabs;
}
