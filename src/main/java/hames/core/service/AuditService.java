package hames.core.service;

import hames.core.dao.AbstractDao;
import hames.core.enums.AuditEnum;

public interface AuditService extends AbstractDao{

	/**
	 * Functionality to Log Audit
	 * @param audit
	 */
	public void logAudit(AuditEnum audit);
	
	
}
