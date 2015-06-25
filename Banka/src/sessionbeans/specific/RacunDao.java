package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xml.izvod.StavkaPreseka;
import xml.racunfirme.TRacunFirme;

@Stateless
@Local(RacunDaoLocal.class)
public class RacunDao extends GenericDao<TRacunFirme, String> implements RacunDaoLocal{

	public RacunDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
		// TODO Auto-generated constructor stub
	}

	public static final String contextPath = "xml.racunfirme";
	
	public static final String schemaName = "accounts";
	
	public RacunDao() {
		super(contextPath, schemaName);
	}

	@Override
	public List<StavkaPreseka> getStavkeForRacunId(String racunId) throws IOException, JAXBException {
		List<TRacunFirme> racuni = findAll();
		for (TRacunFirme r : racuni)
		{
			if (r.getBroj().equals(racunId) )
			{
				return r.getStavke();
			}
		}
		
		return null;
	}

	@Override
	public void setStavkeForRacunId(String racunId, List<StavkaPreseka> stavke)	throws IOException, JAXBException {
		
		List<TRacunFirme> racuni = findAll();
		for (TRacunFirme r : racuni)
		{
			if (r.getBroj().equals(racunId) )
			{
				List<StavkaPreseka> stareStavke = r.getStavke();
				stareStavke.clear();
				for (StavkaPreseka sp : stavke)
				{
					stareStavke.add(sp);
				}
				merge(r, racunId);
			}
		}

	}
	
	

}
