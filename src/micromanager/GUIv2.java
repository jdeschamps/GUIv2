package micromanager;

import mmcorej.CMMCore;

import org.micromanager.api.ScriptInterface;
import device.MSystem;
import swing.MainFrame;
import test.PluginTest;


public class GUIv2 implements org.micromanager.api.MMPlugin{
   public static String menuName = "GUIv2";
   public static String tooltipDescription = "control interface";
   private ScriptInterface gui_;            
   private CMMCore core_;
   private Log log_;
   private MSystem sys_;
   private PluginTest pt_;
   
   public void dispose() {
      /*
       * you can put things that need to be run on shutdown here
       * note: if you launch a JDialog from the plugin using show(), shutdown of the dialog will not automatically call dispose()
       * You will need to add a call to dispose() from the formWindowClosing() method of your JDialog.
       */
	   log_.closeLog();
   }


   public void setApp(ScriptInterface app) {
      gui_ = app;
      core_ = gui_.getMMCore();
      log_ = new Log();
      log_.writeToLog("-------------  New session  ----------------");
      sys_ = new MSystem(core_,log_);
      
      pt_ = new PluginTest(log_,sys_);
   }


   public void show() {
      //frame = new FrameGUI(gui_);
      //frame.setVisible(true);
	   pt_.run();
	   dispose();
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
      return "2.0";
   }


   public String getCopyright() {
      return "";
   }
   

}