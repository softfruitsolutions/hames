package com.hames.party.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.enums.PartyStatus;
import com.hames.enums.PartyType;
import com.hames.party.model.Supplier;
import com.hames.party.service.SupplierService;
import com.hames.party.service.SupplierTypeService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

@Controller
@RequestMapping(value="/party/supplier")
public class SupplierController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SupplierTypeService supplierTypeService;
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_SUPPLIER.getPermission())){
			return "error.403";
		}
		
		return "party.supplier.view";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String get(Model model,@PathVariable(value="id") String id){
		return create(model, id);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model, String id){
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_SUPPLIER.getPermission())){
			return "error.403";
		}
		
		Supplier supplier;
		if(id == null || id.isEmpty()){
			supplier = new Supplier();
			supplier.setPartyType(PartyType.SUPPLIER);
		}else{
			supplier = supplierService.getById(id);
		}
		
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierTypes", supplierTypeService.getAll());
		model.addAttribute("supplierStatus", PartyStatus.values());
		
		return "party.supplier.create";
	}
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute Supplier supplier){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_SUPPLIER.getPermission())){
			throw new AuthorizationException();
		}
		
		LOGGER.debug("Saving Supplier : {}",supplier.toString());
		supplierService.save(supplier);
		
		JsonResponse response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Supplier saved successfully"));
		return response;
	}
}
