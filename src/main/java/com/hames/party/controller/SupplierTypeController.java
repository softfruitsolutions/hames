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

import com.hames.party.enums.SupplierTypeStatus;
import com.hames.party.model.SupplierType;
import com.hames.party.service.SupplierTypeService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;
import com.hames.view.GenericView;

@Controller
@RequestMapping(value="/party/supplier/type")
public class SupplierTypeController extends GenericView{

	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierTypeController.class);

	@Autowired
	private SupplierTypeService supplierTypeService;
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_SUPPLIER_TYPE.getPermission())){
			return "error.403";
		}
		
		return "party.supplier.type";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String get(Model model,@PathVariable(value="id") String id){
		return create(model, id);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model, String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_SUPPLIER_TYPE.getPermission())){
			return "error.403";
		}
		
		SupplierType supplierType;
		if(id == null || id.isEmpty()){
			supplierType = new SupplierType();
		}else{
			supplierType = supplierTypeService.getById(id);
		}
		
		model.addAttribute("supplierType", supplierType);
		model.addAttribute("supplierTypeStatus", SupplierTypeStatus.values());
		
		return "party.supplier.type";
	}

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute SupplierType supplierType){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_SUPPLIER.getPermission())){
			throw new AuthorizationException();
		}
		
		LOGGER.debug("Saving Supplier Type : {}",supplierType.toString());
		supplierTypeService.save(supplierType);
		
		JsonResponse response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Supplier type saved successfully"));
		return response;
	}
	
	@RequestMapping(value="/datatable",method=RequestMethod.GET)
	public @ResponseBody DatatableResponse getDataTable(@ModelAttribute DatatableRequest request){
		return supplierTypeService.getDatatable(request);
	}
}
