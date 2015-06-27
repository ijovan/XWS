package sessionbeans.specific;

import sessionbeans.common.GenericDao;
import xml.racunbanke.RacunBanke;

public class RacunBankeDao extends GenericDao<RacunBanke, Long> implements RacunBankaDaoLocal {

	public static final String contextPath = "xml.banka";
	
	public static final String schemaName = "banks";
	
	public RacunBankeDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
}
	
	public RacunBankeDao()
	{
		super(contextPath, schemaName);
	}

}
