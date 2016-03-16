package com.hames.inventory.view;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hames.inventory.enums.ProductType;
import com.hames.inventory.enums.UnitOfMeasure;
import com.hames.inventory.service.ProductService;
import com.hames.system.auth.Permission;
import com.hames.view.GenericView;

@Controller
@RequestMapping("/inventory/product")
public class ProductController extends GenericView {

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
		
		model.addAttribute("productType", ProductType.values());
		model.addAttribute("uom", UnitOfMeasure.values());
		
		return "inventory.product.create";
	}
}
