package micromanager;

import gui.MainFrame;

import javax.swing.SwingUtilities;

import mmcorej.CMMCore;

import org.micromanager.api.ScriptInterface;

import controller.Controller;
import device.MSystem;
import test.PluginTest;


public class GUIv2 implements org.micromanager.api.MMPlugin{
   public static String menuName = "GUIv3.1";
   public static String tooltipDescription = "control interface";
   private ScriptInterface scriptinterface_;            
   private CMMCore core_;
   private Log log_;
   private Controller controller_;
   
   
   public void dispose() {
	   
   }


   public void setApp(ScriptInterface app) {
	   scriptinterface_ = app;
	   core_ = scriptinterface_.getMMCore();											
	   controller_ = new Controller(scriptinterface_);
   }


   public void show() {
	   controller_.initialize();
   }


   public void configurationChanged() {
      // No clue what to do here
   }


   public String getDescription() {
      return "";
   }


   public String getInfo() {
      return "";
   }


   public String getVersion() {
      return "3.1";
   }


   public String getCopyright() {
      return "";
   }
   

}