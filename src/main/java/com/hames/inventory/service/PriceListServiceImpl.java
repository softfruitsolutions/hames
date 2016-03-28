package com.hames.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.inventory.model.PriceList;
import com.hames.service.GenericServiceImpl;

@Service
public class PriceListServiceImpl extends GenericServiceImpl<PriceList> implements PriceListService {

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String save(PriceList t) {
		//Setting audit
		t.setAudit();
		
		System.out.println(t.toString());
		return "";//super.save(t);
	}

}