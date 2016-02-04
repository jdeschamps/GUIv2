package model;

import java.util.Observable;

public abstract class Task<T extends Number> extends Observable {
	
	private MSystem sys_;
	private boolean running_ = false;
	private T[] output;
	private int N;
	
	public Task(MSystem sys, int N){
		sys_ = sys;
		this.N = N;
	}
	
	public abstract void instantiateOutput();
	
	public abstract void refresh();
	
	public int getNOutput(){
		return N;
	}
	
	public T getOutput(int i){
		return output[i];
	}
	
	public void start(){ 
		running_ = true;
	}
	
	public void stop(){
		running_ = false;
	}
	
	public boolean isRunning(){
		return running_;
	}
}
