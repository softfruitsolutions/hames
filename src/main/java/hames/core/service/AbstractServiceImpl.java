package hames.core.service;

import hames.core.bean.ModelUtil;
import hames.core.dao.AbstractDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;

@Service
public abstract class AbstractServiceImpl extends AbstractDaoImpl implements AbstractService{

	private static final Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class);

	@Override
	public <T> void save(T t) {
		try{
			logger.debug("Validating object : {}",getEntityClass());
			if(validate(t)){
				saveOrUpdate(t);
			}
		}catch(HibernateException e){
			throw new HibernateException(e);
		}
		
	}

	@Override
	public <T> void update(T t) {
		logger.debug("Validating object : {}",getEntityClass());
		if(validate(t)){
			saveOrUpdate(t);
		}
	}

	@Override
	public <T> Boolean validate(T t) {
		logger.debug("Validating {} class",t.getClass());
		Map<String, String> errorMap = new HashMap<String, String>();
		MapBindingResult errors = new MapBindingResult(errorMap, getEntityClass().getName());
		getValidator().validate(t, errors);
		if(errors.hasErrors()){
			for(ObjectError oe : errors.getAllErrors()){
				logger.debug("Error : {} ",oe.getDefaultMessage());
				ModelUtil.addError(oe.getDefaultMessage());
			}
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	@Override
	public <T> List<T> findAll() {
		return super.findAll(getEntityClass());
	}
	
	@Override
	public <T> T findOne(Long id) {
		return super.findOne(getEntityClass(), id);
	}
}
