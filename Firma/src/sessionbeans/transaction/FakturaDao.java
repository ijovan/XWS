package sessionbeans.transaction;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import sessionbeans.common.GenericDao;
import xml.faktura.TFaktura;

@Stateless
@Local(FakturaDaoLocal.class)
public class FakturaDao extends GenericDao<TFaktura, Long> implements FakturaDaoLocal{

	public static final String contextPath = "rs.ac.uns.ftn.xws.entities.payments";
	
	public static final String schemaName = "invoice";
	
	public FakturaDao() {
		super(contextPath, schemaName);
	}

	public TFaktura.StavkaFakture findItemInInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException {
		TFaktura invoice = findById(invoiceId);
		
		if (invoice instanceof TFaktura) 
			for (TFaktura.StavkaFakture item : invoice.getStavkaFakture())
				if (item.getRedniBroj().equals(itemId))
					return item;
				
		return null;
	}

	public TFaktura removeItemFromInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException {
		TFaktura invoice = findById(invoiceId);
		
		if (invoice instanceof TFaktura) 
			for (Iterator<TFaktura.StavkaFakture> iter = invoice.getStavkaFakture().listIterator(); iter.hasNext(); ) {
				TFaktura.StavkaFakture item = iter.next();
			    if (item.getRedniBroj().equals(itemId)) {
			        iter.remove();
			    }
			}
		
		return merge(invoice, invoiceId);
	}

	public TFaktura createInvoiceItem(Long invoiceId, TFaktura.StavkaFakture item) throws IOException, JAXBException {
		TFaktura invoice = findById(invoiceId);
		
		if (invoice instanceof TFaktura) { 
			invoice.getStavkaFakture().add(item);
		}
		
		return merge(invoice, invoiceId);
	}
	
	public TFaktura updateInvoiceItem(Long invoiceId, TFaktura.StavkaFakture item) throws IOException, JAXBException {
		TFaktura invoice = findById(invoiceId);
		//List<TFaktura.StavkaFakture> newList = new ArrayList<TFaktura.StavkaFakture>();	MOZDA BABRA PO KOLEKCIJI KROZ KOJU ITERIRA
		
		if (invoice instanceof TFaktura) { 
			for (Iterator<TFaktura.StavkaFakture> iter = invoice.getStavkaFakture().listIterator(); iter.hasNext(); ) {
				TFaktura.StavkaFakture invoiceItem = iter.next();
			    if (invoiceItem.getRedniBroj().equals(item.getRedniBroj())) {
			        iter.remove();
			    }
			}
			invoice.getStavkaFakture().add(item);
		}
		
		return merge(invoice, invoiceId);
	}

	
	@Override
	public boolean validateInvoice(TFaktura invoice){
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("xml.project.faktura");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//postavljanje validacije
			//W3C sema
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//lokacija seme
			Schema schema = schemaFactory.newSchema(new File("./wsdl/faktura.xsd"));
			//setuje se sema
			jaxbMarshaller.setSchema(schema);
			//EventHandler, koji obradjuje greske, ako se dese prilikom validacije
			jaxbMarshaller.setEventHandler(new util.MyValidationEventHandler());
            //ucitava se objektni model, a da se pri tome radi i validacija
			jaxbMarshaller.marshal(invoice, new File("./xml/Faktura"+invoice.getId()+".xml"));
		} catch (JAXBException e) {	
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			return false;
		}
		return true;
	}
	
	

}
