package hames.core.service;

import hames.core.bean.ModelUtil;
import hames.core.dao.AbstractDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Service
public abstract class AbstractServiceImpl extends AbstractDaoImpl implements AbstractService{

	private static final Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class);

	@Override
	public <T> void save(T t) {
		saveOrUpdate(t);
	}

	@Override
	public <T> void update(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void validate(BindingResult result, T t) {
		logger.debug("Validating {} class",t.getClass());
		getValidator().validate(t, result);
		if(result.hasErrors()){
			ModelUtil.removeMessages();
			for(ObjectError oe : result.getAllErrors()){
				logger.debug("Error : {} ",oe.getDefaultMessage());
				ModelUtil.addError(oe.getDefaultMessage());
			}
		}
	}
}
