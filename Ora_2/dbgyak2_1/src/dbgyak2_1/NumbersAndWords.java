package dbgyak2_1;

public enum NumbersAndWords {	//Ezen enum segítségével hoztuk létre a szám és szó párokat
	EGY(1),
	KETTO(2),
	HAROM(3),
	NEGY(4),
	OT(5),
	HAT(6),
	HET(7),
	NYOLC(8),
	KILENC(9);
	
	private final int num;
	
	private NumbersAndWords(int i) {
		this.num=i;
	}
	
	public int getNum() {
		return this.num;
		
	}
	
}
