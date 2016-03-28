package com.hames.inventory.controller;

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

import com.hames.inventory.enums.PriceListStatus;
import com.hames.inventory.model.PriceList;
import com.hames.inventory.service.PriceListService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;
import com.hames.view.GenericView;

@Controller
@RequestMapping(value="/inventory/pricelist")
public class PriceListController extends GenericView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceListController.class);

	@Autowired
	private PriceListService priceListService;
	
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_PRICELIST.getPermission())){
			return "error.403";
		}
		
		return "inventory.pricelist.view";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String get(Model model,@PathVariable(value="id") String id){
		return create(model, id);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model, String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRICELIST.getPermission())){
			return "error.403";
		}
		
		PriceList priceList;
		if(id == null || id.isEmpty()){
			priceList = new PriceList();
		}else{
			priceList = priceListService.getById(id);
		}
		
		model.addAttribute("pricelist", priceList);
		model.addAttribute("pricelistStatus", PriceListStatus.values());
		
		return "inventory.pricelist.create";
	}
	
	/**
	 * Save a PriceList
	 * @param priceList
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute PriceList priceList){
		JsonResponse jsonResponse;
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRICELIST.getPermission())){
			throw new AuthorizationException();
		}
		
		priceListService.save(priceList);
		
		LOGGER.debug("Pricelist entity saved successfully. Sending back JSON Response.");
		jsonResponse = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Pricelist saved successfully"));
		return jsonResponse;
	}
	
	@ResponseBody 
	@RequestMapping("/datatable")
	public DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return priceListService.getDatatable(datatableRequest);
	}
}
