package centralnabanka;

import java.math.BigDecimal;

public class Banka {

	private String swift;
	private String obracunskiRacun;
	private BigDecimal stanje;
	
	public Banka() {
		super();
	}

	public Banka(String swift, String obracunskiRacun, BigDecimal stanje) {
		super();
		this.swift = swift;
		this.obracunskiRacun = obracunskiRacun;
		this.stanje = stanje;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public BigDecimal getStanje() {
		return stanje;
	}

	public void setStanje(BigDecimal stanje) {
		this.stanje = stanje;
	}
	
}
