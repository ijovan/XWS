package sessionbeans.transaction;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import sessionbeans.common.GenericDao;
import xml.faktura.Faktura;
import xml.faktura.Faktura.StavkaFakture;
import xml.faktura.Faktura.Zaglavlje;
import xml.globals.TFirma;
import baza.EntityManagerBaseX;

@Stateless
@LocalBean
@Local(FakturaDaoLocal.class)
public class FakturaDao extends GenericDao<Faktura, Long> implements FakturaDaoLocal{

	public static final String contextPath = "xml.faktura";
	
	public static final String schemaName = "fakture";
	
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
			System.out.println("Postavljanje konteksta");
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
			System.out.println("Pred marshall");
			jaxbMarshaller.marshal(invoice, new File("./xml/Faktura"+invoice.getId()+".xml"));
			System.out.println("Nakon marshalla");
		} catch (JAXBException e) {	
			return false;
		}
		catch (SAXException e) {
			return false;
		}
		catch (WebApplicationException e) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<Faktura> getInvoicesForPartner(Long partnerId) throws IOException, JAXBException {
		List<Faktura> sveFakture = findAll();
		List<Faktura> povratna = new ArrayList<Faktura>();
		if (sveFakture != null)
		{
			for (Faktura tf : sveFakture)
			{
				if ( (tf.getZaglavlje().getPIBDobavljaca().equals(partnerId.toString()) )
						|| (tf.getZaglavlje().getPIBKupca().equals(partnerId.toString())) ) 
						{
					 		povratna.add(tf);
						}
			}
		}
		return povratna;
		
	}

	@Override
	public List<StavkaFakture> getInvoiceItemsForInvoice(Long invoiceId, Long partnerId) throws IOException, JAXBException {
		 List<Faktura> listaPartnerskih = getInvoicesForPartner(partnerId);
		 if (listaPartnerskih != null)
			 {
				 for (Faktura tf : listaPartnerskih)
				 {
					 if ( tf.getId() == invoiceId)
						 return tf.getStavkaFakture();
				 }
			 }
			return null;
	}
	
	public static void init(){
		XMLGregorianCalendar date1 = null;
//		XMLGregorianCalendar date2 = null;
//		XMLGregorianCalendar date3 = null;
		XMLGregorianCalendar date4 = null;
		XMLGregorianCalendar date5 = null;
		try {
			date1 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 06, 18, DatatypeConstants.FIELD_UNDEFINED);
//			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 07, 11, DatatypeConstants.FIELD_UNDEFINED);
//			date3 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 8, 18, DatatypeConstants.FIELD_UNDEFINED);
			date4 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 10, 11, DatatypeConstants.FIELD_UNDEFINED);
			date5 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 11, 11, DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//firma 1
		TFirma firma1 = new TFirma();
		firma1.setRacun("111111");
		firma1.setModel(new BigInteger("10000"));
		firma1.setNaziv("Nekretnine_Propast");
		firma1.setPozivNaBroj("12");
		//firma 2
		TFirma firma2 = new TFirma();
		firma2.setRacun("222222");
		firma2.setModel(new BigInteger("20000"));
		firma2.setNaziv("Bananomancer");
		firma2.setPozivNaBroj("13");
		//firma 3
		TFirma firma3 = new TFirma();
		firma3.setRacun("333333");
		firma3.setModel(new BigInteger("20000"));
		firma3.setNaziv("RIS");
		firma3.setPozivNaBroj("14");
		
		/*//Racun 1
		Racun racun1 = new Racun();
		racun1.setBrojRacuna(new BigInteger("1"));
		racun1.setDatumRacuna(date1);
		//Racun 2
		TRacun racun2 = new Racun();
		racun2.setBrojRacuna(new BigInteger("2"));
		racun2.setDatumRacuna(date2);
		*/

		Faktura faktura1 = new Faktura();
		//Zaglavlje 1
		Zaglavlje z1 = new Zaglavlje();
		z1.setAdresaDobavljaca("Lopovska_23");
		z1.setAdresaKupca("Titova_2");
		z1.setBrojRacuna(new BigInteger("222222"));
		z1.setDatumRacuna(date1);
		z1.setDatumValute(date1);
		z1.setIDPoruke("1");
		z1.setIznosZaUplatu(new BigDecimal("590"));
		z1.setNazivDobavljaca("Nekretnine_Propast");
		z1.setNazivKupca("Bananomancer");
		z1.setOznakaValute("RSD");
		z1.setPIBDobavljaca("12");
		z1.setPIBKupca("13");
		z1.setUkupanPorez(new BigDecimal("12"));
		z1.setUkupanRabat(new BigDecimal("6"));
		z1.setUkupnoRobaIUsluga(new BigDecimal("560"));
		z1.setUplataNaRacun("111111");
		z1.setVrednostRobe(new BigDecimal("555"));
		z1.setVrednostUsluga(new BigDecimal("5"));
		
		faktura1.setZaglavlje(z1);
		long val1 = 0;

		//stavke1 faktura1
		StavkaFakture s1 = new StavkaFakture();		
		s1.setIznosRabata(new BigDecimal("1"));
		s1.setJedinicaMere("kg");
		s1.setJedinicnaCena(new BigDecimal("1"));
		s1.setKolicina(new BigDecimal("1"));
		s1.setNazivRobeIliUsluge("AK-47");
		s1.setProcenatRabata(new BigDecimal("1"));
		s1.setRedniBroj(new BigInteger(""+val1));
		s1.setUkupanPorez(new BigDecimal("1"));
		s1.setUmanjenoZaRabat(new BigDecimal("1"));
		s1.setVrednost(new BigDecimal("1"));

		faktura1.getStavkaFakture().add(s1);
		//faktura 1
		Faktura faktura2 = new Faktura();
		Zaglavlje z2 = new Zaglavlje();
		z2.setAdresaDobavljaca("Stekarska_11");
		z2.setAdresaKupca("Titova_2");
		z2.setBrojRacuna(new BigInteger("222222"));
		z2.setDatumRacuna(date4);
		z2.setDatumValute(date5);
		z2.setIDPoruke("5");
		z2.setIznosZaUplatu(new BigDecimal("999"));
		z2.setNazivDobavljaca("RIS");
		z2.setNazivKupca("Bananomancer");
		z2.setOznakaValute("RSD");
		z2.setPIBDobavljaca("14");
		z2.setPIBKupca("13");
		z2.setUkupanPorez(new BigDecimal("120"));
		z2.setUkupanRabat(new BigDecimal("60"));
		z2.setUkupnoRobaIUsluga(new BigDecimal("890"));
		z2.setUplataNaRacun("333333");
		z2.setVrednostRobe(new BigDecimal("888"));
		z2.setVrednostUsluga(new BigDecimal("2"));
		faktura2.setZaglavlje(z2);
		
		val1++;
		StavkaFakture s2 = new StavkaFakture();		
		s2.setIznosRabata(new BigDecimal("2"));
		s2.setJedinicaMere("m");
		s2.setJedinicnaCena(new BigDecimal("2"));
		s2.setKolicina(new BigDecimal("2"));
		s2.setNazivRobeIliUsluge("banana");
		s2.setProcenatRabata(new BigDecimal("2"));
		s2.setRedniBroj(new BigInteger(""+val1));
		s2.setUkupanPorez(new BigDecimal("2"));
		s2.setUmanjenoZaRabat(new BigDecimal("2"));
		s2.setVrednost(new BigDecimal("2"));
		
		val1++;
		StavkaFakture s3 = new StavkaFakture();		
		s3.setIznosRabata(new BigDecimal("3"));
		s3.setJedinicaMere("m");
		s3.setJedinicnaCena(new BigDecimal("3"));
		s3.setKolicina(new BigDecimal("3"));
		s3.setNazivRobeIliUsluge("krompir");
		s3.setProcenatRabata(new BigDecimal("3"));
		s3.setRedniBroj(new BigInteger(""+val1));
		s3.setUkupanPorez(new BigDecimal("3"));
		s3.setUmanjenoZaRabat(new BigDecimal("3"));
		s3.setVrednost(new BigDecimal("3"));
		
		faktura2.getStavkaFakture().add(s2);
		faktura2.getStavkaFakture().add(s3);

		try {
			EntityManagerBaseX.createSchema("fakture");
			EntityManagerBaseX<Faktura, Long> emf =new EntityManagerBaseX<Faktura, Long>("fakture", "xml.faktura");
			faktura1.setId(1L);
			System.out.println("fac1");
			emf.persist(faktura1, faktura1.getId());
			faktura2.setId(2L);
			System.out.println("fac2");
			emf.persist(faktura2, faktura2.getId());

			
		} catch (JAXBException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	try {

			convertToXML("xml.faktura", faktura1);
			convertToXML("xml.faktura", faktura2);
		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void convertToXML(String path, Object classTheIsConverted) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(path);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		if (classTheIsConverted instanceof Faktura) {
	    	Faktura temp = (Faktura) classTheIsConverted;
	    	jaxbMarshaller.marshal(temp, System.out);
		    jaxbMarshaller.marshal(temp, new File("../xml/Faktura"+temp.getId()+".xml"));
		}
	}
	
	

}
