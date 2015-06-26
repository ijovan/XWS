package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xml.banka.Banka;
import xml.mt102.Placanje;

public interface BankaDaoLocal extends GenericDaoLocal< Banka , Long>{
	
	public List<Placanje> getPlacanjaForBank(Long bankId) throws IOException, JAXBException;
	
	public void setPlacanjaForBank(Long BankId,  List<Placanje> placanja) throws IOException, JAXBException;
	

}
