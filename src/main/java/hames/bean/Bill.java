package hames.bean;

public class Bill {

	int slno,rate;
	String ordername;
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getOrdername() {
		return ordername;
	}
	public int getRate() {
		return rate;
	}
	public int getSlno() {
		return slno;
	}
}
