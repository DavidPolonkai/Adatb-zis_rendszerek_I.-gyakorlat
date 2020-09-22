package dbgyak2_2;

import java.io.Serializable;

public class Auto implements Serializable{	//Az ObjectOutputStream miatt kell implementálni a Serializable interfacet
	private String rsz;
	private String tipus;
	private int ar;
	
	
	
	public Auto(String rsz, String tipus, int ar) {
		super();
		this.rsz = rsz;
		this.tipus = tipus;
		this.ar = ar;
	}
	
	public Auto() {
		super();
	}
	
	public String getRsz() {
		return rsz;
	}
	public void setRsz(String rsz) {
		this.rsz = rsz;
	}
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
	
	
	public String toString() {
		return "rsz: "+this.rsz+" tipus: "+this.tipus+" ar: "+this.ar;
	}
	
}
