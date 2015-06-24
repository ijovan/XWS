package banka;

import java.io.Serializable;
import java.util.ArrayList;

import xml.mt102.Placanje;

public class Banka implements Serializable {

	private static final long serialVersionUID = 3325667229120096108L;
	
	private String oznakaBanke;
	private String obracunskiRacun;
	private String swiftKod;
	private ArrayList<Placanje> placanja = new ArrayList<Placanje>();
	
	public Banka() {
		super();
	}

	public Banka(String oznakaBanke, String obracunskiRacun, String swiftKod) {
		super();
		this.oznakaBanke = oznakaBanke;
		this.obracunskiRacun = obracunskiRacun;
		this.swiftKod = swiftKod;
	}

	public String getOznakaBanke() {
		return oznakaBanke;
	}

	public void setOznakaBanke(String oznakaBanke) {
		this.oznakaBanke = oznakaBanke;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

	public ArrayList<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(ArrayList<Placanje> placanja) {
		this.placanja = placanja;
	}
	
}
