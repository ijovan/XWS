package client;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import xml.izvod.TIzvod;
import xml.nalogzaplacanje.TNalogZaPlacanje;
import xml.zahtevzaizvod.TZahtevZaIzvod;
import banka.BankaPort;

public class FirmaClient {

	private static String brojRacunaFirme;
	private static BankaPort banka;

	static {
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://banka", "bankaService");
			QName portName = new QName("http://banka", "bankaPort");

			Service service = Service.create(wsdlLocation, serviceName);

			banka = service.getPort(portName, BankaPort.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		slanjeNalogaZaPlacanje();
		zahtevZaIzvod();
	}

	private static void zahtevZaIzvod() {
		try {
			GregorianCalendar c = new GregorianCalendar();
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			int i = 0;
			while (true) {
				TIzvod presek = slanjeZahtevaZaIzvod(i++, date);
				if (presek.getStavkaPreseka().size() == 0) {
					break;
				}
			}
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private static void slanjeNalogaZaPlacanje() {
		try {
			GregorianCalendar c = new GregorianCalendar();
			XMLGregorianCalendar date;
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			TNalogZaPlacanje nalogZaPlacanje = new TNalogZaPlacanje();
			nalogZaPlacanje.setIDPoruke("0");
			nalogZaPlacanje.setDuznikNalogodavac("A");
			nalogZaPlacanje.setSvrhaPlacanja("Uplata");
			nalogZaPlacanje.setPrimalacPoverilac("B");
			nalogZaPlacanje.setDatumNaloga(date);
			nalogZaPlacanje.setDatumValute(date);
			nalogZaPlacanje.setRacunDuznika("012000000000000000");
			nalogZaPlacanje.setModelZaduzenja(new BigInteger("97"));
			nalogZaPlacanje.setPozivNaBrojZaduzenja("012");
			nalogZaPlacanje.setRacunPoverioca("345000000000000001");
			nalogZaPlacanje.setModelOdobrenja(new BigInteger("97"));
			nalogZaPlacanje.setPozivNaBrojOdobrenja("345");
			nalogZaPlacanje.setIznos(new BigDecimal("300000"));
			nalogZaPlacanje.setOznakaValute("RSD");
			nalogZaPlacanje.setHitno(false);

			banka.nalogZaPlacanje(nalogZaPlacanje);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static TIzvod slanjeZahtevaZaIzvod(int redniBrojPreseka, XMLGregorianCalendar datum) {
		TZahtevZaIzvod zahtev = new TZahtevZaIzvod();
		zahtev.setBrojRacuna(brojRacunaFirme);
		zahtev.setDatum(datum);
		return banka.zahtevZaIzvod(zahtev);
	}

}