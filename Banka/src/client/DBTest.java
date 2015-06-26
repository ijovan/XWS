package client;

import java.math.BigDecimal;
import java.util.List;

import sessionbeans.specific.RacunDao;
import xml.racunfirme.RacunFirme;
import baza.EntityManagerBaseX;

public class DBTest {

	public static void main(String[] args) throws Exception {
		foo2();
	}

	private static void foo0() throws Exception {
		EntityManagerBaseX.createSchema("accounts");
	}

	private static void foo1() throws Exception {
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
			entity.setId(33L);
			racun.persist(entity);
		}
	}

	private static void foo2() throws Exception {
		RacunDao racun = new RacunDao();
		List<RacunFirme> rf = racun.findAll();
		for (RacunFirme r : rf) {
			System.out.println(r.getId());
		}
	}

	private static void foo3() throws Exception {
		RacunDao racun = new RacunDao();
		racun.remove(33L);
	}

}
