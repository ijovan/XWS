package sessionbeans.specific;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import sessionbeans.common.GenericDao;
import xml.banka.Banka;
import xml.mt102.Placanje;

@Stateless
@LocalBean
@Local(BankaDaoLocal.class)
public class BankaDao extends GenericDao<Banka, Long> implements BankaDaoLocal {

	public BankaDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
		// TODO Auto-generated constructor stub
	}
	
	public static final String contextPath = "xml.banka";
	
	public static final String schemaName = "banks";
	
	public BankaDao()
	{
		super(contextPath, schemaName);
	}

	@Override
	public List<Placanje> getPlacanjaForBank(Long bankId) throws IOException,JAXBException {
		List<Banka> banke = findAll();
		for (Banka banka : banke)
		{
			if (banka.getId().equals(bankId))
			{
				if (!banka.getPlacanja().isEmpty())
					return banka.getPlacanja();
				break;
			}
		}
		return null;
	}

	@Override
	public void setPlacanjaForBank(Long bankId, List<Placanje> placanja) throws IOException, JAXBException {
		List<Banka> banke = findAll();
		for (Banka banka : banke)
		{
			if (banka.getId().equals(bankId))
			{
				List<Placanje> staraPlacanja = banka.getPlacanja();
				staraPlacanja.clear();
				for (Placanje pl : placanja)
				{
					staraPlacanja.add(pl);
				}
				merge(banka, banka.getId());
				break;
			}
		}
		
	}

}
