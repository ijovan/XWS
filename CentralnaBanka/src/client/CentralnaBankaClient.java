package client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import xml.mt102.MT102;
import xml.mt900.TMT900;
import banka.BankaPort;

public class CentralnaBankaClient {

    public void testIt() {
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://banka", "bankaService");
			QName portName = new QName("http://banka", "bankaPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			BankaPort banka = service.getPort(portName, BankaPort.class); 
			
			TMT900 mt900 = new TMT900();
			banka.mt900(mt900);
			
			MT102 mt102 = new MT102();
			banka.mt102(mt102);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		CentralnaBankaClient client = new CentralnaBankaClient();
		client.testIt();
    }

}