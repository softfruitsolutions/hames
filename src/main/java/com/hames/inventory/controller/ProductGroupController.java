package com.hames.inventory.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hames.inventory.model.ProductGroup;
import com.hames.inventory.service.ProductGroupService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;
import com.hames.view.GenericView;

@Controller
@RequestMapping(value="/inventory/product/group")
public class ProductGroupController extends GenericView{

	@Autowired
	private ProductGroupService productGroupService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute ProductGroup productGroup){
		JsonResponse jsonResponse;
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRODUCT_CATEGORY.getPermission())){
			throw new AuthorizationException();
		}
		productGroupService.save(productGroup);
		jsonResponse = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Product Group saved successfully"));
		return jsonResponse;
	}
	
}
