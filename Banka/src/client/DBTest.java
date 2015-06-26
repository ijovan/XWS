package client;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import sessionbeans.specific.RacunDao;
import xml.racunfirme.RacunFirme;
import baza.EntityManagerBaseX;

public class DBTest {

	public static void main(String[] args) throws JAXBException, IOException {
		try {
			EntityManagerBaseX.createSchema("accounts");
		} catch (Exception e) {
			e.printStackTrace();
		}
		{
			RacunDao racun = new RacunDao();
			RacunFirme entity = new RacunFirme();
			entity.setBroj("123");
			entity.setStanje(new BigDecimal("1"));
			entity.setId(1L);
			racun.persist(entity);
		}
		{
			RacunDao racun = new RacunDao();
			RacunFirme entity = new RacunFirme();
			entity.setBroj("234");
			entity.setStanje(new BigDecimal("1"));
			entity.setId(2L);
			racun.persist(entity);
		}
		//		RacunFirme rf = racun.findById("123");
		//		System.out.println(rf.getStanje());
	}

}
