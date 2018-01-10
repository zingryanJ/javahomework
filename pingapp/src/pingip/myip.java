package pingip;

public class myip {
	private String ip;
	private String status;
	
	public myip(String i,String s) {
		ip = i;
		status = s;
	}
	
	public void setip(String i) {
		ip = i;
	}
	
	public String getip() {
		return ip;
	}
	
	public void setststus(String s) {
		status = s;
	}
	
	public String getstatus() {
		return status;
	}
}
