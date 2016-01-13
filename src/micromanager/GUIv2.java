package micromanager;

import gui.MainFrame;
import javax.swing.SwingUtilities;
import mmcorej.CMMCore;
import org.micromanager.api.ScriptInterface;
import device.MSystem;
import test.PluginTest;


public class GUIv2 implements org.micromanager.api.MMPlugin{
   public static String menuName = "GUIv2.6b";
   public static String tooltipDescription = "control interface";
   private ScriptInterface gui_;            
   private CMMCore core_;
   private Log log_;
   private MSystem sys_;
   //private PluginTest pt_;
   private MainFrame frame;
   private MConfiguration config_;
   
   public void dispose() {
	   
	   log_.closeLog();
   }


   public void setApp(ScriptInterface app) {
      gui_ = app;
      core_ = gui_.getMMCore();
      log_ = new Log();
      log_.writeToLog("-------------  New session  ----------------");
      
      //pt_ = new PluginTest(log_,sys_);
   }


   public void show() {
	   
      SwingUtilities.invokeLater(new Runnable()								/// this is the right way to do it
       {
           @Override
           public void run()
           {
        	   config_ = new MConfiguration();
        	   sys_ = new MSystem(core_,log_,config_);
        	   frame = new MainFrame(sys_, log_,config_);
           }
       }); 
	   
	   //pt_.run();
	   //dispose();
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