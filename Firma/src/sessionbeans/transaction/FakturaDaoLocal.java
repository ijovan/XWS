package sessionbeans.transaction;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xml.faktura.TFaktura;

public interface FakturaDaoLocal extends GenericDaoLocal<TFaktura, Long>{

	public TFaktura.StavkaFakture findItemInInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException;
	
	public TFaktura removeItemFromInvoice(Long invoiceId, Long itemId) throws IOException, JAXBException;

	public TFaktura createInvoiceItem(Long invoiceId, TFaktura.StavkaFakture item) throws IOException, JAXBException;

	public TFaktura updateInvoiceItem(Long invoiceId, TFaktura.StavkaFakture item) throws IOException, JAXBException;
	
	boolean validateInvoice(TFaktura invoice);
}
