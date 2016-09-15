package advancedacq;

import java.io.Serializable;

import utils.Bool2DecConverter;

public class LaserSettings implements Serializable {

	private String label;
	private int mode;
	private int pulselength;
	private int powerperc;
	private int sequence;
	
	public LaserSettings(String label, int mode, int pulselength, int powerperc, String seq){
		this.label = label;
		this.mode = mode;
		this.pulselength = pulselength;
		this.powerperc = powerperc;
		this.sequence = Bool2DecConverter.getDecimal16bits(seq);
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
	public int getSequence(){
		return sequence;
	}
}
