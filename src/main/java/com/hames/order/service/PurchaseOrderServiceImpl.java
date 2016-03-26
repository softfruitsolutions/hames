package com.hames.order.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.order.model.PurchaseOrder;
import com.hames.order.validator.PurchaseOrderValidator;
import com.hames.service.GenericServiceImpl;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {

	@Override
	public Validator getValidator() {
		return new PurchaseOrderValidator();
	}

}
