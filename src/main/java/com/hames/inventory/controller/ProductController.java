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

import com.hames.inventory.enums.ProductStatus;
import com.hames.inventory.enums.ProductType;
import com.hames.inventory.enums.UnitOfMeasure;
import com.hames.inventory.model.Product;
import com.hames.inventory.model.ProductGroup;
import com.hames.inventory.service.ProductService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;
import com.hames.view.GenericView;

@Controller
@RequestMapping("/inventory/product")
public class ProductController extends GenericView {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_PRODUCT.getPermission())){
			return "error.403";
		}
		
		return "inventory.product.view";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String get(Model model,@PathVariable(value="id") String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRODUCT.getPermission())){
			return "error.403";
		}
		
		model.addAttribute("product", productService.getById(id));
		return null;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model, String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRODUCT.getPermission())){
			return "error.403";
		}
		
		model.addAttribute("productTypes", ProductType.values());
		model.addAttribute("uom", UnitOfMeasure.values());
		model.addAttribute("productStatus", ProductStatus.values());
		
		model.addAttribute("product", new Product());
		model.addAttribute("productGroup", new ProductGroup());
		
		return "inventory.product.create";
	}
	
	/**
	 * Save a product
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public JsonResponse save(@ModelAttribute Product product){
		JsonResponse jsonResponse;
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_PRODUCT.getPermission())){
			throw new AuthorizationException();
		}
		
		productService.save(product);
		
		LOGGER.debug("Product entity saved successfully. Sending back JSON Response.");
		jsonResponse = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Product saved successfully"));
		return jsonResponse;
	}
}
