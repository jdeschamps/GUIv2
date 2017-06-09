package advancedacq;

import java.io.Serializable;
import java.util.ArrayList;

import org.micromanager.api.SequenceSettings;

public class SequenceSettingsWraper implements Serializable {

	public SequenceSettings getSettings(Acquisition acq){
		SequenceSettings seq = new SequenceSettings();
		
		System.out.println(acq.getPath());
		
		switch(acq.getAcq()){
		case BFPSNAP:
			seq.numFrames = 1;
			seq.intervalMs = 0;
			seq.root = acq.getPath();
			seq.save = true;
			seq.timeFirst = true;
			seq.usePositionList = false;
			break;
		case ZSTACK:
			seq.relativeZSlice = true;
			ArrayList<Double> slice = new ArrayList<Double>();
			Double z;
			for(int i=0;i<=4000/50;i++){
				z=-2+i*0.05;
				slice.add(z);
			}
			seq.slices = slice;
			seq.numFrames = 1;
			seq.intervalMs = 0;
			seq.root = acq.getPath();
			seq.save = true;
			seq.usePositionList = false;
			break;
		case TSTACK:
			seq.numFrames = acq.getNumberFrames();
			seq.intervalMs = 0;
			seq.root = acq.getPath();
			seq.save = true;
			seq.timeFirst = true;
			seq.usePositionList = false;
			break;
		case NONE:
			seq.numFrames = 0;
			seq.intervalMs = 0;
			seq.root = acq.getPath();
			seq.save = false;
			seq.usePositionList = false;
			break;
		}
	
		
		return seq;
	}
	
}