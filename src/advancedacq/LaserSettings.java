package advancedacq;

public class LaserSettings {

	private String label;
	private int mode;
	private int pulselength;
	private int powerperc;
	
	public LaserSettings(String label, int mode, int pulselength, int powerperc){
		this.label = label;
		this.mode = mode;
		this.pulselength = pulselength;
		this.powerperc = powerperc;
	}

	public String getLabel(){
		return label;
	}
	public int getMode(){
		return mode;
	}
	public int getPulseLength(){
		return pulselength;
	}
	public int getPowerPerc(){
		return powerperc;
	}
}
