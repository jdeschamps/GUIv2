package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import micromanager.MConfiguration;
import advancedacq.Acquisition;
import advancedacq.LaserSettings;
import device.MSystem;

/**
 *
 * @author Ries
 */
@SuppressWarnings("serial")
public class AdvancedAcqTab extends javax.swing.JPanel {

	MSystem sys_;
	String path_;
	Acquisition acq;
	
    public AdvancedAcqTab(MSystem sys, String path) {
    	sys_ = sys;
    	path_ = path;
    	acq = new Acquisition();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	jLabel_numframes = new javax.swing.JLabel();
    	jSpinner_numframes = new javax.swing.JSpinner();
        jLabel_filter = new javax.swing.JLabel();
        jComboBox_filter = new javax.swing.JComboBox();
        jLabel_acqtype = new javax.swing.JLabel();
        jComboBox_acqtype = new javax.swing.JComboBox();
        jCheckBox_activation = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_lasers = new javax.swing.JTable();
        jLabel_waittime = new javax.swing.JLabel();
        jSpinner_waitingtime = new javax.swing.JSpinner();
        jLabel_exposuretime = new javax.swing.JLabel();
        jSpinner_exposure = new javax.swing.JSpinner();
        jCheckBox_activation_3d = new javax.swing.JCheckBox();

        jLabel_numframes.setText("Num frames:");

        jLabel_filter.setText("Filter:");

        jComboBox_filter.setModel(new javax.swing.DefaultComboBoxModel(MConfiguration.filters));

        jLabel_acqtype.setText("Acquisition type:");


        jSpinner_numframes.setModel(new SpinnerNumberModel(50000, 0, 1000000, 1));
        jComboBox_acqtype.setModel(new javax.swing.DefaultComboBoxModel(acq.getAcqType()));
        jComboBox_acqtype.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if(jComboBox_acqtype.getSelectedIndex()==0){
	    			jSpinner_numframes.setEnabled(true);
	    			jSpinner_numframes.setValue(50000);
	    			jCheckBox_activation.setEnabled(true);
	    			jCheckBox_activation.setSelected(true);
	    		} else {
	    			jSpinner_numframes.setValue(1);
	    			jSpinner_numframes.setEnabled(false);
	    			jCheckBox_activation.setEnabled(false);
	    			jCheckBox_activation.setSelected(false);
	    		}
	    	}
	    });

        jCheckBox_activation.setText("Activation on");
        jCheckBox_activation.setSelected(true);
        jCheckBox_activation_3d.setText("3DA");
        jCheckBox_activation_3d.setSelected(false);
        
        //////////////////////////////////////////////////////////////////////////////////////////
        /// JTable
        jTable_lasers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, 100, 0},
                {null, null, 100, 0},
                {null, null, 100, 0},
                {null, null, 100, 0}
            },
            new String [] {
                "Laser", "Mode", "Power", "Pulse"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        
        ArrayList<String[]> values = new ArrayList<String[]>(2);
        values.add(sys_.getLaserList());
        values.add(sys_.getLaserModeList());
        
        TableColumn col;
        for(int i=0;i<values.size();i++){
        	col = jTable_lasers.getColumnModel().getColumn(i);
            col.setCellEditor(new ComboBoxEditor(values.get(i)));
            col.setCellRenderer(new ComboBoxRenderer(values.get(i)));
        }
        /*
        ListSelectionModel cellSelectionModel = jTable_lasers.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent e) {
            String selectedData = null;

            int[] selectedRow = jTable_lasers.getSelectedRows();
            int[] selectedColumns = jTable_lasers.getSelectedColumns();

            for (int i = 0; i < selectedRow.length; i++) {
              for (int j = 0; j < selectedColumns.length; j++) {
                selectedData = (String) jTable_lasers.getValueAt(selectedRow[i], selectedColumns[j]);
              }
            }
            System.out.println("Selected: " + selectedData);
          }

        });*/

        
        ////////////////////////////////////////////////////////////////////////////////////////////
        jScrollPane1.setViewportView(jTable_lasers);

        jLabel_waittime.setText("Waiting time (s):");

        jLabel_exposuretime.setText("Exposure time (ms):");
        
        jSpinner_waitingtime.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
        
        jSpinner_exposure.setModel(new SpinnerNumberModel(((Double)sys_.getExposureTime()).intValue(), 0, 200, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_numframes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinner_numframes, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_acqtype)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_acqtype, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel_filter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jCheckBox_activation_3d)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox_activation))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSpinner_exposure, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_waittime)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(jSpinner_waitingtime, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_exposuretime))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_acqtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_acqtype))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_numframes)
                    .addComponent(jSpinner_numframes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_filter)
                    .addComponent(jComboBox_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_exposure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_exposuretime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_waittime)
                    .addComponent(jSpinner_waitingtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_activation_3d)
                    .addComponent(jCheckBox_activation))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private javax.swing.JCheckBox jCheckBox_activation;
    private javax.swing.JComboBox jComboBox_acqtype;
    private javax.swing.JComboBox jComboBox_filter;
    private javax.swing.JSpinner jSpinner_numframes;
    private javax.swing.JLabel jLabel_acqtype;
    private javax.swing.JLabel jLabel_exposuretime;
    private javax.swing.JLabel jLabel_filter;
    private javax.swing.JLabel jLabel_numframes;
    private javax.swing.JLabel jLabel_waittime;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_exposure;
    private javax.swing.JSpinner jSpinner_waitingtime;
    private javax.swing.JTable jTable_lasers;
    private javax.swing.JCheckBox jCheckBox_activation_3d;
    // End of variables declaration            
    
    public Acquisition createAcq(){
    	String acqtype = (String) jComboBox_acqtype.getSelectedItem();
    	String filter = (String) jComboBox_filter.getSelectedItem();
    	int filt = posInStringList(MConfiguration.filters,filter);
    	int exposure = (Integer) jSpinner_exposure.getValue();
    	int waitingtime = (Integer) jSpinner_waitingtime.getValue();
    	int numframes = (Integer) jSpinner_numframes.getValue();
    	ArrayList<LaserSettings> listlasers = new ArrayList<LaserSettings>();
    	boolean activation = jCheckBox_activation.isSelected();
    	boolean astigmatism = jCheckBox_activation_3d.isSelected();
    	
    	for(int i=0;i<jTable_lasers.getRowCount();i++){
    		String s = (String) jTable_lasers.getValueAt(i,0);
    		if(s == null){
    			break;
    		} else if(!((String)jTable_lasers.getValueAt(i,0)).equals("None")){
    			String val1 = (String)jTable_lasers.getValueAt(i,0);
    			int val2 = posInStringList(sys_.getLaserModeList(),(String)jTable_lasers.getValueAt(i,1));
    			int val3 = (Integer) jTable_lasers.getValueAt(i,3);
    			int val4 = (Integer) jTable_lasers.getValueAt(i,2);
    			
    			listlasers.add(new LaserSettings(val1,val2,val3,val4));
    		}
    	}

    	return new Acquisition(acqtype,listlasers,filt,exposure,numframes,waitingtime,astigmatism,activation,path_);
    }
    
    public int posInStringList(String[] s, String val){
    	for(int i=0;i<s.length;i++){
    		if(s[i].equals(val)){
    			return i;
    		}
    	}
    	return -1;
    }
    
	class ComboBoxRenderer extends JComboBox implements TableCellRenderer {
		public ComboBoxRenderer(String[] items) {
			super(items);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				setForeground(table.getForeground());
				super.setBackground(table.getBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			setSelectedItem(value);
			return this;
		}
	}
		
	class ComboBoxEditor extends DefaultCellEditor {
		public ComboBoxEditor(String[] items) {
			super(new JComboBox(items));
		}
	}

}

