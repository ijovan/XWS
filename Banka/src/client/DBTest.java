package client;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import sessionbeans.specific.RacunDao;
import xml.racunfirme.RacunFirme;
import baza.EntityManagerBaseX;

@Startup
@Singleton
public class DBTest {

	@EJB
	private static RacunDao racun;
	
//	@PostConstruct
//	public void run() throws Exception {
//		foo3();
//	}
	
	@PostConstruct
	public void test() throws Exception {
		for (int i = 0; i < 20; i++) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
		}
		System.out.println(racun);
	}
	
	public static void main(String[] args) throws Exception {
		foo2();
	}

	public static void foo0() throws Exception {
		EntityManagerBaseX.createSchema("accounts");
	}

	public static void foo1() throws Exception {
		{
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

	public static void foo2() throws Exception {
		List<RacunFirme> rf = racun.findAll();
		for (RacunFirme r : rf) {
			System.out.println(r.getId());
		}
	}

	public static void foo3() throws Exception {
		racun.remove(33L);
	}

}
