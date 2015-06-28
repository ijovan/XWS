package client;

import java.math.BigDecimal;

import sessionbeans.specific.RacunBankeDao;
import xml.racunbanke.RacunBanke;
import baza.EntityManagerBaseX;

public class InitDB {

	public static void main(String[] args) throws Exception {
		EntityManagerBaseX.createSchema("bank-accounts");
		EntityManagerBaseX.createSchema("mt102s");
		EntityManagerBaseX.createSchema("mt103s");
		
		RacunBankeDao racunBankeDao = new RacunBankeDao();
		
		RacunBanke rb1 = new RacunBanke("AAAAAAAA", "000000000000000000", new BigDecimal(10000000));
		RacunBanke rb2 = new RacunBanke("BBBBBBBB", "000000000000000001", new BigDecimal(10000000));
		rb1.setId(1L);
		rb2.setId(2L);
		racunBankeDao.persist(rb1);
		racunBankeDao.persist(rb2);
	}

}
