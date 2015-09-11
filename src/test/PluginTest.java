package test;

import threader.PImonitor;
import threader.QPDmonitor;
import threader.UVautomator;
import device.Laser;
import device.MSystem;
import device.QPD;
import device.SSLaser;
import device.Servo;
import device.Stage;
import micromanager.MConfiguration;
import micromanager.Log;
import mmcorej.CMMCore;

public class PluginTest implements Runnable {

	private Log log_;
	private MSystem sys_;

	Servo[] s;
	QPD qpd;
	Stage pi;
	Laser[] luxx;
	SSLaser cobolt;
	
	PImonitor pim;
	QPDmonitor qpdm;
	UVautomator uva;
	
	CMMCore cm;
	
	public PluginTest(Log log, MSystem sys){
		log_ = log;
		sys_ = sys;
	}
	
	@Override
	public void run() {
		//testDevices();
		//testUpdaters();
		//testGUI();
		
		cm = sys_.getCore();
		//testFunctions();
	}
	
	public void testFunctions(){
		
		System.out.println("---------------------");
		try {

			QPD qpd_ = new QPD(MConfiguration.qpdlabel,cm,log_);
			Stage pi_ = new Stage(MConfiguration.pilabel,cm,log_);
			Laser l405_ = new Laser(MConfiguration.laserlabel[0], MConfiguration.ardlaserlabel[0],cm,log_);
			SSLaser l561_ = new SSLaser(MConfiguration.sslaserlabel, MConfiguration.ardlaserlabel[3], MConfiguration.ard2laserlabel,cm,log_);
			Servo fw_ = new Servo(MConfiguration.servolabel[0], MConfiguration.proplabel[0], MConfiguration.numposservo[0],cm,log_);
			Servo bfp_ = new Servo(MConfiguration.servolabel[1], MConfiguration.proplabel[1], MConfiguration.numposservo[1],cm,log_);
			Servo astig_ = new Servo(MConfiguration.servolabel[2], MConfiguration.proplabel[2], MConfiguration.numposservo[2],cm,log_);
			
			System.out.println("----Start-------");
			fw_.setState(1);
			pause(3);
			fw_.setState(3);
			pause(3);
			cm.setProperty("MaestroServo", "Position", 1740);
			pause(3);
			cm.setProperty("MaestroServo", "Position", 1250);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private void testDevices(){
		log_.writeToLog("---- Start test devices ----");
		
		// Servos
		log_.writeToLog("-- Servos --");
		s = new Servo[3];
		s[0] = (Servo) sys_.getServo("FW");
		s[1] = (Servo) sys_.getServo("BFP");
		s[2] = (Servo) sys_.getServo("3DA");
		for(int i=0;i<s.length;i++){
			log_.writeToLog("Servos to 0");
			s[i].setState(0);
			pause(5);
			log_.writeToLog("Servos to N-1");
			s[i].setState(s[i].getNState()-1);
			pause(5);
			log_.writeToLog("Servos to 0");
			s[i].setState(0);
			pause(5);
		}
		log_.writeToLog("Servos done");

		// QPD
		log_.writeToLog("-- QPD --");
		qpd = (QPD) sys_.getQPD();
		for(int i=0;i<4;i++){
			log_.writeToLog("X :"+qpd.getXSignal());
			log_.writeToLog("Y :"+qpd.getYSignal());
			log_.writeToLog("S :"+qpd.getSSignal());
		}
		log_.writeToLog("QPD done");

		// PI
		log_.writeToLog("-- PI --");
		pi = (Stage) sys_.getPIStage();
		for(int i=0;i<4;i++){
			log_.writeToLog("Pos :"+pi.getPosition());
			pause(1);
			log_.writeToLog("Move to 50");
			pi.setPosition(50);
			pause(5);
			log_.writeToLog("Pos :"+pi.getPosition());
			pause(1);
			log_.writeToLog("Sensor state :"+pi.getSensorState());
			pause(1);
			log_.writeToLog("Lock");
			pi.setSensorState(1);
			pause(1);
			log_.writeToLog("Sensor state :"+pi.getSensorState());
			pause(1);
			log_.writeToLog("Unock");
			pi.setSensorState(0);
			pause(1);
			log_.writeToLog("Sensor state :"+pi.getSensorState());
		}
		log_.writeToLog("PI done");

		// Lasers
		luxx = new Laser[3];
		log_.writeToLog("-- Lasers --");

		for(int i=0;i<3;i++){
			luxx[i] = (Laser) sys_.getLaser(MConfiguration.laserkeys[i]);
			log_.writeToLog("- "+luxx[i].getLabel()+" -");
			
			// Operation
			log_.writeToLog("Operation :"+luxx[i].getOperation());
			luxx[i].setOperation(1);
			log_.writeToLog("Operation :"+luxx[i].getOperation());

			// Percentage
			log_.writeToLog("% :"+luxx[i].getPowerPercentage());
			luxx[i].setPowerPercentage(50);
			log_.writeToLog("% :"+luxx[i].getPowerPercentage());

			// Behaviour
			log_.writeToLog("Behaviour :"+luxx[i].getBehaviour());
			luxx[i].setBehaviour(3);
			log_.writeToLog("Behaviour :"+luxx[i].getBehaviour());

			// Pulse
			log_.writeToLog("Pulse :"+luxx[i].getPulseLength());
			luxx[i].setPulseLength(50000);
			log_.writeToLog("Pulse :"+luxx[i].getPulseLength());
		}
		
		cobolt = (SSLaser) sys_.getLaser(MConfiguration.laserkeys[3]);
		log_.writeToLog("- "+cobolt.getLabel()+" -");
		// Operation
		log_.writeToLog("Operation :"+cobolt.getOperation());
		cobolt.setOperation(1);
		log_.writeToLog("Operation :"+cobolt.getOperation());

		// Percentage
		log_.writeToLog("% :"+cobolt.getPowerPercentage());
		cobolt.setPowerPercentage(50);
		log_.writeToLog("% :"+cobolt.getPowerPercentage());

		// Behaviour
		log_.writeToLog("Behaviour :"+cobolt.getBehaviour());
		cobolt.setBehaviour(3);
		log_.writeToLog("Behaviour :"+cobolt.getBehaviour());

		// Pulse
		log_.writeToLog("Pulse :"+cobolt.getPulseLength());
		cobolt.setPulseLength(50000);
		log_.writeToLog("Pulse :"+cobolt.getPulseLength());

		// Max power
		log_.writeToLog("Pulse :"+cobolt.getMaxPower());
		cobolt.setMaxPower(200);
		log_.writeToLog("Pulse :"+cobolt.getMaxPower());
		log_.writeToLog("Lasers done");		
	}
	
	private void testUpdaters(){
		log_.writeToLog("---- Start test updaters ----");

		pim = new PImonitor(pi);
		log_.writeToLog("- PI monitor -");
		for(int i=0;i<4;i++){
			log_.writeToLog("Pos :"+pim.getOutput(0));
			pause(1);
		}
		
		qpdm = new QPDmonitor(qpd);
		log_.writeToLog("- QPD monitor -");
		for(int i=0;i<4;i++){
			log_.writeToLog("X :"+qpdm.getOutput(0));
			log_.writeToLog("Y :"+qpdm.getOutput(1));
			log_.writeToLog("S :"+qpdm.getOutput(2));
			pause(1);
		}

		uva = new UVautomator(luxx[0], sys_, log_, null);
		log_.writeToLog("- UV automator -");
		for(int i=0;i<4;i++){
			log_.writeToLog("Pulse length :"+uva.getOutput(0));
			pause(1);
		}
		
		log_.writeToLog("---- Done ----");
	}
	
	private void testGUI(){
		
		// fill in
	}
	
	private boolean pause(int sec){
		try {
			Thread.sleep(1000*sec);
			return true;
		} catch (InterruptedException e) {
			log_.writeToLog("Unable to pause thread");
			return false;
		}
	}
}
