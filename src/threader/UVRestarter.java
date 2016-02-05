package threader;

public class UVRestarter implements Runnable {
	
	UVThreader uv_;
	
	public UVRestarter(UVThreader uv){
		uv_ = uv;
	}
	
	@Override
	public void run() {
		uv_.restartUV();
	}
	
	public void setUVThreader(UVThreader uv){
		uv_ = uv;
	}

}
