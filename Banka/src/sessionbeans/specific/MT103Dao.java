package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xml.mt102.Placanje;
import xml.mt103.MT103;

public class MT103Dao extends GenericDao<MT103, Long> implements MT103DaoLocal{
	
	
	public MT103Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}
	
	public static final String contextPath = "xml.mt102";
	
	public static final String schemaName = "mt102";

	public MT103Dao()
	{
		super(contextPath, schemaName);
	}

//	@Override
//	public List<Placanje> getPlacanjaForMT102(Long mtId) throws IOException,JAXBException {
//		List<MT103> sve = findAll();
//		
//		for (MT103 mt : sve)
//		{
//			if (mt.getId() == mtId)
//			{
//				return mt.getPlacanja();
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public void setPlacanja(Long mtId, List<Placanje> placanja)	throws IOException, JAXBException {
//		List<MT103> sve = findAll();
//		
//		for (MT103 mt : sve)
//		{
//			if (mt.getId() == mtId)
//			{
//				List<Placanje> staraPlacanja = mt.getPlacanja();
//				staraPlacanja.clear();
//				for (Placanje pl : placanja)
//				{
//					staraPlacanja.add(pl);
//				}
//			}
//			merge(mt, mt.getId());
//			break;
//		}		
//		
//	}
	
}
