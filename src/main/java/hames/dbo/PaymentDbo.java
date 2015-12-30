package hames.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import hames.bean.Payment;
import hames.service.ServicebillService;


public class PaymentDbo {

	private DataFieldMaxValueIncrementer incrementor;
	private String exception="";
	public ArrayList<Payment> getAll(){
		ArrayList<Payment> list=new ArrayList<Payment>();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.getData("payment");
			while(set.next()){
				Payment payment=new Payment();
				payment.setPaymentId(set.getLong(1));
				payment.setOrderId(set.getLong(3));
				payment.setPaymentAmount(set.getBigDecimal(2));
				list.add(payment);
			}
		}catch(Exception e){
			exception=e.getMessage();
		}
		return list;
	}
	public void addNewPayment(String order_id,String payment){
		try{
			Connection con=DriverManager.getConnection(DbOperation.DB_URL,DbOperation.DB_USER,DbOperation.DB_PASS);
			Statement st=con.createStatement();
			ResultSet set=st.executeQuery("SELECT payment_id FROM payment ORDER BY payment_id DESC");
			set.next();
			int id=set.getInt(1);
			id++;
			st.close();
			st=con.createStatement();
			set.close();
			set=st.executeQuery("INSERT INTO payment(payment_id, payment_amount, order_id, idx) VALUES ("+id+", '"+payment+"', '"+order_id+"', 0);");
			con.close();
			
		}catch(Exception e){
			
		}
	}
	
	public void addNewPayment(String order_id,String payment,String discount){
		try{
			Connection con=DriverManager.getConnection(DbOperation.DB_URL,DbOperation.DB_USER,DbOperation.DB_PASS);
			Statement st=con.createStatement();
			ResultSet set=st.executeQuery("SELECT payment_id FROM payment ORDER BY payment_id DESC");
			set.next();
			int id=set.getInt(1);
			id++;
			st.close();
			st=con.createStatement();
			set.close();
			String today=new ServicebillService().getToday();
			set=st.executeQuery("INSERT INTO payment(payment_id, payment_amount, order_id, idx,date) VALUES ("+id+", '"+payment+"', '"+order_id+"', 0,'"+today+"');");
			con.close();
			
		}catch(Exception e){
			
		}
	}
	
	
	public String getException(){
		return exception;
	}
}
