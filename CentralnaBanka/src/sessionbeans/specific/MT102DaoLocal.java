package sessionbeans.specific;


import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDaoLocal;
import xml.mt102.MT102;
import xml.mt102.Placanje;

public interface MT102DaoLocal extends GenericDaoLocal<MT102, Long>{
	
	public List<Placanje> getPlacanjaForMT102 (Long mtId) throws IOException, JAXBException;
	
	public void setPlacanja (Long mtId, List<Placanje> placanja) throws IOException, JAXBException;

}
