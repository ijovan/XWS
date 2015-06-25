package client;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import sessionbeans.specific.RacunDao;
import xml.racunfirme.RacunFirme;

public class DBTest {

	private static RacunDao racun = new RacunDao();
	
	public static void main(String[] args) throws JAXBException, IOException {
		RacunFirme entity = new RacunFirme();
		entity.setBroj("123");
		entity.setStanje(new BigDecimal("0"));
		racun.persist(entity);
		RacunFirme rf = racun.findById("123");
		System.out.println(rf.getStanje());
	}
	
}
