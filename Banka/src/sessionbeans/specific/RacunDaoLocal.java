package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xml.izvod.StavkaPreseka;
import xml.racunfirme.RacunFirme;


public interface RacunDaoLocal extends GenericDaoLocal< RacunFirme , Long >{

/*	public StavkaPreseka findStavkaInRacun(Long racunId, Long stavkaId) throws IOException, JAXBException;
	
	public TFaktura removeStavkaFromRacun(Long racunId, Long stavkaId) throws IOException, JAXBException;

	public TFaktura createStavkaPreseka(Long racunId, StavkaPreseka stavkaId) throws IOException, JAXBException;

	public TFaktura updateInvoiceItem(Long invoiceId, TFaktura.StavkaFakture item) throws IOException, JAXBException;
	
	public List<TFaktura> getInvoicesForPartner(Long partnerId) throws IOException, JAXBException;
	*/
	
	public List<StavkaPreseka> getStavkeForRacunId(Long racunId) throws IOException, JAXBException;
	
	public void setStavkeForRacunId(Long racunId,  List<StavkaPreseka> stavke) throws IOException, JAXBException;
	
	
}
