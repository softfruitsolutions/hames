package com.hames.inventory.validator;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.inventory.enums.ProductType;
import com.hames.inventory.enums.UnitOfMeasure;
import com.hames.inventory.model.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;
		
		if(product.getProductCode() == null || product.getProductCode().isEmpty()){
			errors.rejectValue("productCode", "", "Product code required");
		}
		
		if(product.getProductName() == null || product.getProductName().isEmpty()){
			errors.rejectValue("productName", "", "Product name required");
		}
		
		if(product.getProductCategory() == null || product.getProductCategory().isEmpty()){
			errors.rejectValue("productCategory", "", "Product category required");
		}
		
		if(!EnumUtils.isValidEnum(ProductType.class, product.getProductType().name())){
			errors.rejectValue("productType", "", "Invalid Product Type");
		}
		
		if(!EnumUtils.isValidEnum(UnitOfMeasure.class, product.getUom().name())){
			errors.rejectValue("uom", "", "Please select a valid Unit of measure");
		}
	}

}
