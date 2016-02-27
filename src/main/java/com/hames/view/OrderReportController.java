package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.order.model.SaleOrderSearchCriteria;
import com.hames.report.ReportPeriod;
import com.hames.report.ReportRequest;
import com.hames.report.ReportResponse;
import com.hames.service.SaleOrderService;
import com.hames.system.auth.Permission;

@Controller
@RequestMapping("/report")
public class OrderReportController extends GenericView{
	
	@Autowired
	private SaleOrderService saleOrderService;

	@RequestMapping("/saleorder")
	public String getSaleReport(Model model){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_SALE_ORDER_REPORT.getPermission())){
			throw new AuthorizationException();
		}
		
		SaleOrderSearchCriteria searchCriteria = new SaleOrderSearchCriteria();
		searchCriteria.setFromOrderDate(new DateTime().withDayOfMonth(1));
		searchCriteria.setToOrderDate(new DateTime());
		
		model.addAttribute("saleOrderSearchCriteria", searchCriteria);		
		model.addAttribute("reportPeriod", ReportPeriod.values());
		
		return "sale.order.report";
	}
	
	@ResponseBody
	@RequestMapping("/saleorder/data")
	private ReportResponse getReportData(@ModelAttribute SaleOrderSearchCriteria criteria){
		ReportRequest reportRequest = new ReportRequest();
		if(criteria != null){
			reportRequest.setQueryCriteria(criteria);
		}
		return saleOrderService.generateSaleReport(reportRequest);
	}
}
