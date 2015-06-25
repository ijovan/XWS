package sessionbeans.transaction;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xml.faktura.Faktura;

public interface FakturaDaoLocal extends GenericDaoLocal<Faktura, Long>{

	public Faktura.StavkaFakture findItemInInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException;
	
	public Faktura removeItemFromInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException;

	public Faktura createInvoiceItem(Long invoiceId, Faktura.StavkaFakture item) throws IOException, JAXBException;

	public Faktura updateInvoiceItem(Long invoiceId, Faktura.StavkaFakture item) throws IOException, JAXBException;
	
	public List<Faktura> getInvoicesForPartner(Long partnerId) throws IOException, JAXBException;
	
	public List<Faktura.StavkaFakture> getInvoiceItemsForInvoice(Long invoiceId, Long partnerId) throws IOException, JAXBException;
	
	boolean validateInvoice(Faktura invoice);
}
