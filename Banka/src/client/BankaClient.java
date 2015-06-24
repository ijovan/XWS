package client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import xml.mt103.TMT103;
import centralnabanka.CentralnaBankaPort;

public class BankaClient {

    public void testIt() {
		try {
			URL wsdlLocation = new URL("http://localhost:8080/centralna_banka/services/CentralnaBanka?wsdl");
			QName serviceName = new QName("http://centralnabanka", "centralnaBankaService");
			QName portName = new QName("http://centralnabanka", "centralnaBankaPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			CentralnaBankaPort banka = service.getPort(portName, CentralnaBankaPort.class); 
			
			TMT103 mt103 = new TMT103();
			banka.mt103(mt103);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		BankaClient client = new BankaClient();
		client.testIt();
    }

}