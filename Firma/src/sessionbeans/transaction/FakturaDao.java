package sessionbeans.transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import xml.faktura.Faktura;
import xml.faktura.Faktura.StavkaFakture;

@Stateless
@Local(FakturaDaoLocal.class)
public class FakturaDao extends GenericDao<Faktura, Long> implements FakturaDaoLocal{

	public static final String contextPath = "rs.ac.uns.ftn.xws.entities.payments";
	
	public static final String schemaName = "invoice";
	
	public FakturaDao() {
		super(contextPath, schemaName);
	}

	public Faktura.StavkaFakture findItemInInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException {
		Faktura invoice = findById(invoiceId);
		
		if (invoice instanceof Faktura) 
			for (Faktura.StavkaFakture item : invoice.getStavkaFakture())
				if (item.getRedniBroj().equals(itemId))
					return item;
				
		return null;
	}

	public Faktura removeItemFromInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException {
		Faktura invoice = findById(invoiceId);
		boolean nadjena = false;
		
		if (invoice instanceof Faktura) 
			for (Iterator<Faktura.StavkaFakture> iter = invoice.getStavkaFakture().listIterator(); iter.hasNext(); ) {
				Faktura.StavkaFakture item = iter.next();
			    if (item.getRedniBroj().equals(itemId)) {
			        iter.remove();
			        nadjena = true;
			    }
			}
		
		if (nadjena)
			return merge(invoice, invoiceId);
		else
			return null;
	}

	public Faktura createInvoiceItem(Long invoiceId, Faktura.StavkaFakture item) throws IOException, JAXBException {
		Faktura invoice = findById(invoiceId);
		
		if (invoice == null)
		{
			return null;
		}
		else if (invoice instanceof Faktura) { 
			invoice.getStavkaFakture().add(item);
		}
		
		return merge(invoice, invoiceId);
	}
	
	public Faktura updateInvoiceItem(Long invoiceId, Faktura.StavkaFakture item) throws IOException, JAXBException {
		Faktura invoice = findById(invoiceId);
		boolean nadjena = false;
		//List<Faktura.StavkaFakture> newList = new ArrayList<Faktura.StavkaFakture>();	MOZDA BABRA PO KOLEKCIJI KROZ KOJU ITERIRA
		
		if (invoice instanceof Faktura) { 
			for (Iterator<Faktura.StavkaFakture> iter = invoice.getStavkaFakture().listIterator(); iter.hasNext(); ) {
				Faktura.StavkaFakture invoiceItem = iter.next();
			    if (invoiceItem.getRedniBroj().equals(item.getRedniBroj())) {
			        iter.remove();
			        nadjena = true;
			    }
			}
			invoice.getStavkaFakture().add(item);
		}
		if (nadjena)
			return merge(invoice, invoiceId);
		else
			return null;
	}

	
	@Override
	public boolean validateInvoice(Faktura invoice){
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

	@Override
	public List<Faktura> getInvoicesForPartner(Long partnerId) throws IOException, JAXBException {
		List<Faktura> sveFakture = findAll();
		List<Faktura> povratna = new ArrayList<Faktura>();
		for (Faktura tf : sveFakture)
		{
			if ( (tf.getZaglavlje().getPIBDobavljaca().equals(partnerId.toString()) )
					|| (tf.getZaglavlje().getPIBKupca().equals(partnerId.toString())) ) 
					{
				 		povratna.add(tf);
					}
		}
		return povratna;
		
	}

	@Override
	public List<StavkaFakture> getInvoiceItemsForInvoice(Long invoiceId, Long partnerId) throws IOException, JAXBException {
		 List<Faktura> listaPartnerskih = getInvoicesForPartner(partnerId);
		 for (Faktura tf : listaPartnerskih)
		 {
			 if ( tf.getId() == invoiceId)
				 return tf.getStavkaFakture();
		 }
		return null;
	}
	
	

}
