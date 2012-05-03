package au.com.regimo.core.service;

import java.util.Date;

import javax.transaction.Synchronization;

import org.hibernate.FlushMode;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.event.EventSource;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.regimo.core.domain.AuditLog;
import au.com.regimo.core.domain.AuditLogDetail;
import au.com.regimo.core.domain.AuditLogOperation;
import au.com.regimo.core.domain.IAuditable;
import au.com.regimo.core.domain.IdEntity;
import au.com.regimo.core.utils.SecurityUtils;

public class AuditLogEventListener implements PostInsertEventListener,
PostUpdateEventListener, PostDeleteEventListener {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(AuditLogEventListener.class);

	@Override
	public void onPostInsert(PostInsertEvent event) {
		if (event.getEntity() instanceof IAuditable) {
			AuditLog auditLog = createAuditLog((IAuditable)event.getEntity());
			auditLog.setOperation(AuditLogOperation.INSERT);
			for (int i = 0; i < event.getState().length; i++) {
				addAuditLogDetail(auditLog, event.getPersister().getPropertyNames()[i], 
						event.getState()[i], null);
			}
			persist(event.getSession(), auditLog);
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		if (event.getEntity() instanceof IAuditable) {
			AuditLog auditLog = createAuditLog((IAuditable)event.getEntity());
			auditLog.setOperation(AuditLogOperation.UPDATE);
			for (int i = 0; i < event.getState().length; i++) {
				addAuditLogDetail(auditLog, event.getPersister().getPropertyNames()[i], 
						event.getState()[i], event.getOldState()[i]);
			}
			persist(event.getSession(), auditLog);
		}
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		if (event.getEntity() instanceof IAuditable) {
			AuditLog auditLog = createAuditLog((IAuditable)event.getEntity());
			auditLog.setOperation(AuditLogOperation.DELETE);
			for (int i = 0; i < event.getDeletedState().length; i++) {
				addAuditLogDetail(auditLog, event.getPersister().getPropertyNames()[i], 
						null, event.getDeletedState()[i]);
			}
			persist(event.getSession(), auditLog);
		}
	}

	private void persist(EventSource session, AuditLog entry) {
		Transaction transaction = session.getTransaction();
		final AuditSync auditSync = new AuditSync(session, entry);
		transaction.registerSynchronization(auditSync);
	}

	private AuditLog createAuditLog(IAuditable entity){
		AuditLog entry = new AuditLog();
		entry.setTimestamp(new Date());
		entry.setEntity(entity.getClass().getSimpleName());
		entry.setEntityId(entity.getId());
		entry.setUser(SecurityUtils.getCurrentUser());
		return entry;
	}

	private void addAuditLogDetail(AuditLog auditLog, String fieldName, Object newValue, Object oldValue){
		if (newValue instanceof PersistentCollection) {
			logger.debug("skip new PersistentCollection owner: {}", 
					((PersistentCollection) newValue).getOwner());
			return;
		}
		if (oldValue instanceof PersistentCollection) {
			logger.debug("skip old PersistentCollection owner: {}", 
					((PersistentCollection) oldValue).getOwner());
			return;
		}
		if(newValue==oldValue 
				|| (oldValue != null && oldValue.equals(newValue)) 
				|| (newValue != null && newValue.equals(oldValue)) ){
			return;
		}
		
		AuditLogDetail detail = new AuditLogDetail(auditLog);
		detail.setFieldName(fieldName);
		detail.setNewValue(getStringValue(newValue));
		detail.setOldValue(getStringValue(oldValue));
		auditLog.getAuditLogDetails().add(detail);
	}
	
	private String getStringValue(Object object){
		String value = null;
		if (object != null) {
			if(object instanceof Date){
				value = LocalDateTime.fromDateFields((Date) object).toString();
			}
			else if(object instanceof IdEntity){
				value = ((IdEntity) object).getId().toString();
			}
			else{
				value = object.toString();
			}
		}
		return value;
	}
	
	private static class AuditSync implements Synchronization {

		private final EventSource session;
		private final AuditLog auditLog;

		public AuditSync(EventSource session, AuditLog auditLog) {
			this.session = session;
			this.auditLog = auditLog;
		}

		@Override
		public void beforeCompletion() {
			// within one transaction
			// see: http://www.jboss.com/index.html?module=bb&op=viewtopic&p=4178431
			if(!FlushMode.isManualFlushMode(session.getFlushMode()) && !session.isClosed()){
				session.save(auditLog);
				for(AuditLogDetail detail : auditLog.getAuditLogDetails()){
					session.save(detail);
				}
				session.flush();
			}
		}

		@Override
		public void afterCompletion(int status) {
			//do not delegate
		}

	}
}
