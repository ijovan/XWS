package client;

import java.math.BigDecimal;

import sessionbeans.specific.BankaDao;
import sessionbeans.specific.RacunDao;
import xml.banka.Banka;
import xml.racunfirme.RacunFirme;
import baza.EntityManagerBaseX;

public class InitDB {

	public static void main(String[] args) throws Exception {
		EntityManagerBaseX.createSchema("accounts");
		EntityManagerBaseX.createSchema("banks");
		RacunDao racunDao = new RacunDao();
		BankaDao bankaDao = new BankaDao();
		RacunFirme rf1 = new RacunFirme("012000000000000000", new BigDecimal("300000"));
		RacunFirme rf2 = new RacunFirme("012000000000000001", new BigDecimal("0"));
		RacunFirme rf3 = new RacunFirme("345000000000000000", new BigDecimal("300000"));
		RacunFirme rf4 = new RacunFirme("345000000000000001", new BigDecimal("0"));
		rf1.setId(1L);
		rf2.setId(2L);
		rf3.setId(3L);
		rf4.setId(4L);
		racunDao.persist(rf1);
		racunDao.persist(rf2);
//		racunDao.persist(rf3);
//		racunDao.persist(rf4);
		Banka b1 = new Banka("345", "000000000000000001", "BBBBBBBB");
		Banka b2 = new Banka("012", "000000000000000002", "CCCCCCCC");
		b1.setId(1L);
		b2.setId(2L);
		bankaDao.persist(b1);
		bankaDao.persist(b2);
	}

}
