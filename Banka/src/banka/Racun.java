package banka;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import xml.izvod.TIzvod.StavkaPreseka;

public class Racun implements Serializable {

	private static final long serialVersionUID = 5087455123272449871L;
	
	private String broj;
	private BigDecimal stanje;
	private ArrayList<StavkaPreseka> stavke = new ArrayList<StavkaPreseka>();
	
	public Racun() {
		super();
	}

	public Racun(String broj, BigDecimal stanje) {
		super();
		this.broj = broj;
		this.stanje = stanje;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public BigDecimal getStanje() {
		return stanje;
	}

	public void setStanje(BigDecimal stanje) {
		this.stanje = stanje;
	}

	public ArrayList<StavkaPreseka> getStavke() {
		return stavke;
	}

	public void setStavke(ArrayList<StavkaPreseka> stavke) {
		this.stavke = stavke;
	}
	
}
