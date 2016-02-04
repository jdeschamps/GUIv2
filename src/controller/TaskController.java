package controller;

import java.util.ArrayList;

import model.MSystem;
import model.Task;

public class TaskController {
	
	private Controller parentc_;
	
	private MSystem sys_;
	private ArrayList<Task> tasklist_;
	
	public TaskController(MSystem sys, Controller parentc){
		sys_ = sys;
		parentc_ = parentc;
		
		tasklist_ = sys_.getTasksList();
	}
}
