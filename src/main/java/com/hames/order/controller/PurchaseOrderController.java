package com.hames.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hames.view.GenericView;

@Controller
@RequestMapping(value="/order/purchase")
public class PurchaseOrderController extends GenericView{

	public String view(Model model){
		return "order.purchase.view";
	}
}
