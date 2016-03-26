package com.hames.order.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;

import com.hames.bean.SaleOrder;
import com.hames.report.ReportPeriod;
import com.hames.util.model.QueryCriteria;

/**
 * Sale Order Search Criteria
 * @author afilansari
 *
 */
public class SaleOrderSearchCriteria extends SaleOrder implements QueryCriteria {
	
	private ReportPeriod reportPeriod;
	private DateTime fromOrderDate;
	private DateTime toOrderDate;
	private String groupBy;
	
	@Override
	public Criteria queryCriteria() {
		
		List<Criteria> andCriterias = buildCriteria();

		if(andCriterias == null || andCriterias.size() <= 0){
			return null;
		}else{
			return new Criteria().andOperator(andCriterias.toArray(new Criteria[andCriterias.size()]));	
		}
	}
	
	@Override
	public List<Criteria> buildCriteria(){
		
		List<Criteria> andCriterias = new ArrayList<Criteria>();
		
		if(getJobNo() != null && !getJobNo().isEmpty()){
			andCriterias.add(Criteria.where("jobNo").is(getJobNo()));
		}
		
		if(getFromOrderDate() != null){
			andCriterias.add(Criteria.where("orderDate").gte(getFromOrderDate().toDate()));
		}
		if(getToOrderDate() != null){
			andCriterias.add(Criteria.where("orderDate").lte(getToOrderDate().toDate()));
		}
		
		if(getSaleOrderStatus() != null){
			andCriterias.add(Criteria.where("saleOrderStatus").is(getSaleOrderStatus()));
		}
		
		if(getPayment() != null){
			if(getPayment().getPaymentStatus() != null){
				andCriterias.add(Criteria.where("payment.paymentStatus").is(getPayment().getPaymentStatus()));
			}
		}
		
		return andCriterias;
	}

	public ReportPeriod getReportPeriod() {
		return reportPeriod;
	}
	public void setReportPeriod(ReportPeriod reportPeriod) {
		this.reportPeriod = reportPeriod;
	}
	public DateTime getFromOrderDate() {
		return fromOrderDate;
	}
	public void setFromOrderDate(DateTime fromOrderDate) {
		this.fromOrderDate = fromOrderDate;
	}
	public DateTime getToOrderDate() {
		return toOrderDate;
	}
	public void setToOrderDate(DateTime toOrderDate) {
		this.toOrderDate = toOrderDate;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

}
