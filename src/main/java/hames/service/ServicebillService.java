package hames.service;

import java.util.Date;

import hames.dbo.OrderDbo;
import hames.dbo.PaymentDbo;

public class ServicebillService {

	public String getToday(){
		Date date=new Date();
		int day=date.getDate();
		int month=date.getMonth()+1;
		int year=date.getYear();
		year=year%100;
		String yr="20"+year;
		String today=""+day+"/"+month+"/"+yr;
		return today;
	}
	public boolean saveBill(String order, String payment) {
		// TODO Auto-generated method stub
		try{
			PaymentDbo dbp=new PaymentDbo();
			dbp.addNewPayment(order, payment);
			//except=dbp.getException();
			OrderDbo db=new OrderDbo();
			db.setPaid(order);
			return true;
			}catch(Exception e){
				return false;
			}
	}
	public boolean saveBill(String order, String payment,String discount) {
		// TODO Auto-generated method stub
		try{
			PaymentDbo dbp=new PaymentDbo();
			dbp.addNewPayment(order, payment);
			OrderDbo db=new OrderDbo();
			db.setPaid(order,discount);
			return true;
			}catch(Exception e){
				return false;
			}
	}
}
