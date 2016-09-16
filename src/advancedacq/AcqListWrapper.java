package advancedacq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AcqListWrapper implements Serializable  {

	private ArrayList<Acquisition> acqlist;
	private String path = "";
	
	public AcqListWrapper(){
		acqlist = new ArrayList<Acquisition>();
	}
	
	public AcqListWrapper(ArrayList<Acquisition> list){
		acqlist = new ArrayList<Acquisition>();
		acqlist = list;
	}
	
	public void setList(ArrayList<Acquisition> list){
		acqlist = list;
	}
	
	public ArrayList<Acquisition> getList(){
		return acqlist;
	}
	
	public String getPath(){
		return path;
	}
	
	public void loadList(String path){
		if(path.substring(path.length()-3).equals("acq")){
			try {
				Object obj;
				FileInputStream f_in;
				f_in = new FileInputStream(path);
				
				ObjectInputStream obj_in;
				obj_in = new ObjectInputStream (f_in);

				obj = obj_in.readObject();

				obj_in.close();
				
				if (obj instanceof AcqListWrapper){
					if(((AcqListWrapper) obj).getList() != null && ((AcqListWrapper) obj).getList().size()>0)
						this.setList(((AcqListWrapper) obj).getList());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveList(String path){
		if(acqlist != null && acqlist.size()>0){
			if(path.substring(path.length()-3).equals("acq")){
				try {
					File f = new File(path);
					FileOutputStream f_out = new FileOutputStream(f);
				  	ObjectOutputStream obj_out;
					obj_out = new ObjectOutputStream (f_out);
					
					obj_out.writeObject(this);

					obj_out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				path = path+".acq";
				try {
					File f = new File(path);
					FileOutputStream f_out = new FileOutputStream(f);
				  	ObjectOutputStream obj_out;
					obj_out = new ObjectOutputStream (f_out);
					
					obj_out.writeObject(this);

					obj_out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
