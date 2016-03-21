package com.hames.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.exception.ValidationException;
import com.hames.inventory.model.Product;
import com.hames.inventory.validator.ProductValidator;
import com.hames.mongo.Sequence;
import com.hames.mongo.SequenceDao;
import com.hames.service.GenericServiceImpl;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductGroupService productGroupService;
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public Validator getValidator() {
		return new ProductValidator();
	}

	@Override
	public String save(Product product) {
		validate(product);
		
		if(!productGroupService.isExists(product.getProductGroup())){
			LOGGER.error("Invalid product group supplied. Opeartion aborted");
			throw new ValidationException("Invalid Product Group");
		}
		
		//Setting auditable details
		product.setAudit();
		
		if (product.getProductId() == null || product.getProductId().isEmpty()) {
			//Setting sequence
			product.setProductCode(getNextProductCode());
			sequenceDao.updateSequence("product");
		}
		
		return super.save(product);
	}

	@Override
	public String getNextProductCode() {
		Sequence sequence = sequenceDao.findById("product");
		return new StringBuilder().append(sequence.getSequenceCode()).append("-").append(sequence.getSequence()).toString();
	}

}
