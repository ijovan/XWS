package client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import xml.zahtevzaizvod.TZahtevZaIzvod;
import banka.BankaPort;

public class FirmaClient {

    public void testIt() {
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://banka", "bankaService");
			QName portName = new QName("http://banka", "bankaPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			BankaPort banka = service.getPort(portName, BankaPort.class); 
			
			TZahtevZaIzvod zahtev = new TZahtevZaIzvod();
			banka.zahtevZaIzvod(zahtev);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		FirmaClient client = new FirmaClient();
		client.testIt();
    }

}