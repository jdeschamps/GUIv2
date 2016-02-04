package view;

import gui.CustomValueToggle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;

import micromanager.utils;
import configuration.DefaultIdentifiers;
import controller.BoolPropertyIdentifier;
import controller.Controller;
import controller.DoublePropertyIdentifier;
import controller.IntPropertyIdentifier;
import controller.PropertyIdentifier;

public class ListenerFactory {

	Controller controller_;
	
	public ListenerFactory(Controller controller){
		controller_ = controller;
	}

	public FrameListener createFrameListener(){
		return new FrameListener(controller_);
	}
	
	public SliderListener createSliderListener(String devicename, String propertyname, JSlider component){
		return new SliderListener(controller_, new IntPropertyIdentifier(devicename, propertyname), component);
	}

	public JToggleButtonListener createJToggleButtonListener(String devicename, String propertyname){
		return new JToggleButtonListener(controller_, new BoolPropertyIdentifier(devicename, propertyname));
	}
	
	public JToggleButtonValueListener createJToggleButtonValueListener(String devicename, String propertyname, int val){
		return new JToggleButtonValueListener(controller_, new IntPropertyIdentifier(devicename, propertyname),val);
	}

	public CustomToggleButtonListener createCustomToggleButtonListener(String devicename, String propertyname, CustomValueToggle compo){
		return new CustomToggleButtonListener(controller_, new IntPropertyIdentifier(devicename, propertyname),compo);
	}
	
	public JButtonListener createJButtonListener(String devicename, String propertyname){
		return new JButtonListener(controller_, new PropertyIdentifier(devicename, propertyname));
	}

	public IntTextFieldListener createIntTextFieldListener(String devicename, String propertyname, JTextField component){
		return new IntTextFieldListener(controller_, new IntPropertyIdentifier(devicename, propertyname),component);
	}
	
	public DoubleTextFieldListener createDoubleTextFieldListener(String devicename, String propertyname, JTextField component){
		return new DoubleTextFieldListener(controller_, new DoublePropertyIdentifier(devicename, propertyname),component);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Reporter interface
	private interface Reporter{
		public void notifyController();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Window adapter
	private class FrameListener extends WindowAdapter implements Reporter{
		private Controller controller_;
		
		public FrameListener(Controller controller){
			controller_ = controller;
		}
		
		@Override
	    public void windowClosing(WindowEvent e) {
			notifyController();
		}
		
		@Override
		public void notifyController() {
			controller_.reportChange(new PropertyIdentifier(DefaultIdentifiers.id_frame,DefaultIdentifiers.id_frame_closing));	
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Slider listener
	private class SliderListener extends MouseAdapter implements Reporter{
		private Controller controller_;
		private IntPropertyIdentifier property_;
		private JSlider component_;
		
		public SliderListener(Controller controller, IntPropertyIdentifier property, JSlider component){
			controller_ = controller;
			property_ = property;
			component_ = component;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			updatePropertyIdentifier();
			notifyController();
		}

		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}
		
		public void updatePropertyIdentifier(){
			property_.setValue(component_.getValue());
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// JToggleButton listener
	private class JToggleButtonListener implements ItemListener, ActionListener, Reporter{
		private Controller controller_;
		private BoolPropertyIdentifier property_;
		
		public JToggleButtonListener(Controller controller, BoolPropertyIdentifier property){
			controller_ = controller;
			property_ = property;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				property_.setValue(true);
			} else if(e.getStateChange()==ItemEvent.DESELECTED){
				property_.setValue(false);
			}
			notifyController();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {															/////// maybe here get state and use it in the property identifier value
			notifyController();
		}
		
		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}

	}

	private class JToggleButtonValueListener implements ItemListener, ActionListener, Reporter{									//////////// this is not so good, mixing up combobox and jtoggle.... in the end it can creates problems...
		private Controller controller_;
		private IntPropertyIdentifier property_;
		
		public JToggleButtonValueListener(Controller controller, IntPropertyIdentifier property, int value) {
			controller_ = controller;
			property_ = property;
			property_.setValue(value);
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				notifyController();
			} 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JComboBox){
				property_.setValue(((JComboBox) e.getSource()).getSelectedIndex());
				notifyController();
			}
		}
		
		
		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}
	}

	private class CustomToggleButtonListener implements ActionListener, Reporter{
		private Controller controller_;
		private IntPropertyIdentifier property_;
		private CustomValueToggle component_;
		
		public CustomToggleButtonListener(Controller controller, IntPropertyIdentifier property, CustomValueToggle component) {
			controller_ = controller;
			property_ = property;
			component_ = component;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			property_.setValue(component_.getValue());
			notifyController();
		
		}
		
		
		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// JButton listener
	private class JButtonListener implements ActionListener, Reporter{
		private Controller controller_;
		private PropertyIdentifier property_;
		
		public JButtonListener(Controller controller, PropertyIdentifier property){
			controller_ = controller;
			property_ = property;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			notifyController();
		}

		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// JTextField listener
	private abstract class TextFieldListener implements ActionListener, FocusListener, Reporter{
		private Controller controller_;
		PropertyIdentifier property_;
		JTextField component_;
		
		public TextFieldListener(Controller controller, PropertyIdentifier property, JTextField component){
			controller_ = controller;
			property_ = property;
			component_ = component;
		}
		
		@Override
		public void focusGained(FocusEvent e) {}

		@Override
		public void focusLost(FocusEvent e) {
			updatePropertyIdentifier();
			notifyController();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			updatePropertyIdentifier();
			notifyController();
		}

		@Override
		public void notifyController() {
			controller_.reportChange(property_);
		}
		
		public abstract void updatePropertyIdentifier();
	}
	
	public class IntTextFieldListener extends TextFieldListener{
		public IntTextFieldListener(Controller controller, IntPropertyIdentifier property, JTextField component) {
			super(controller, property, component);
		}

		@Override
		public void updatePropertyIdentifier() {
			int val = 0; 
			String s = component_.getText();

			if(!utils.isNumeric(s)){
				return;
			} else {
				val = utils.getIntFromString(s);
			}
			((IntPropertyIdentifier) property_).setValue(val);
		}
		
	}
	
	public class DoubleTextFieldListener extends TextFieldListener{
		public DoubleTextFieldListener(Controller controller, DoublePropertyIdentifier property, JTextField component) {
			super(controller, property, component);
		}

		@Override
		public void updatePropertyIdentifier() {
			double val = 0; 
			String s = component_.getText();

			if(!utils.isNumeric(s)){
				return;
			} else {
				val = Double.parseDouble(s);
			}
			((DoublePropertyIdentifier) property_).setValue(val);
		}
		
	}
}
