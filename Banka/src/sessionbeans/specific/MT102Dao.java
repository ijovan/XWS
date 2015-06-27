package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xml.mt102.Placanje;
import xml.mt102.MT102;

@Stateless
@LocalBean
@Local(MT102DaoLocal.class)
public class MT102Dao extends GenericDao<MT102, Long> implements MT102DaoLocal{
	
	
	public MT102Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}
	
	public static final String contextPath = "xml.mt102";
	
	public static final String schemaName = "mt102";

	public MT102Dao()
	{
		super(contextPath, schemaName);
	}

	@Override
	public List<Placanje> getPlacanjaForMT102(Long mtId) throws IOException,JAXBException {
		List<MT102> sve = findAll();
		
		for (MT102 mt : sve)
		{
			if (mt.getId() == mtId)
			{
				return mt.getPlacanja();
			}
		}
		return null;
	}

	@Override
	public void setPlacanja(Long mtId, List<Placanje> placanja)	throws IOException, JAXBException {
		List<MT102> sve = findAll();
		
		for (MT102 mt : sve)
		{
			if (mt.getId() == mtId)
			{
				List<Placanje> staraPlacanja = mt.getPlacanja();
				staraPlacanja.clear();
				for (Placanje pl : placanja)
				{
					staraPlacanja.add(pl);
				}
			}
			merge(mt, mt.getId());
			break;
		}		
		
	}
	
	
	
}
