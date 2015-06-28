package sessionbeans.specific;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import sessionbeans.common.GenericDao;
import xml.racunbanke.RacunBanke;

@Stateless
@LocalBean
@Local(RacunBankaDaoLocal.class)
public class RacunBankeDao extends GenericDao<RacunBanke, Long> implements RacunBankaDaoLocal {

	public static final String contextPath = "xml.racunbanke";
	
	public static final String schemaName = "bank-accounts";
	
	public RacunBankeDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
}
	
	public RacunBankeDao()
	{
		super(contextPath, schemaName);
	}

}
