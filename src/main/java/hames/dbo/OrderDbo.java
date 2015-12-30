package hames.dbo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import hames.bean.Order;

public class OrderDbo {
	Connection connection;
	java.sql.Statement statement;
	
	public ArrayList<Order> getAll(){
		ArrayList<Order> list=new ArrayList<Order>();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.getData("orders");
			while(set.next()){
				Order order=new Order();
				order.setOrderId(set.getLong(1));
				order.setJobNo(set.getString(2));
				order.setCustomerId(set.getLong(3));
				order.setJobName(set.getString(4));
				order.setJobDescription(set.getString(5));
				order.setTotalAmount(set.getBigDecimal(27));
				list.add(order);
			}
		}catch(Exception e){
			
		}
		return list;
		
	}
	public Order getOne(int id){
		Order order=new Order();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.getData("orders");
			set.next();
			order.setOrderId(set.getLong(1));
			order.setJobNo(set.getString(2));
			order.setCustomerId(set.getLong(3));
			order.setJobName(set.getString(4));
			order.setJobDescription(set.getString(5));
			order.setTotalAmount(set.getBigDecimal(27));
		}catch(Exception e){
			
		}
		return order;
	}
	public ArrayList<Order> getAll(String tag){
		ArrayList<Order> list=new ArrayList<Order>();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.getData("orders","order_status>0");
			while(set.next()){
				if(set.getString(3).equals(tag)){
					Order order=new Order();
					order.setOrderId(set.getLong(1));
					order.setJobNo(set.getString(2));
					order.setCustomerId(set.getLong(3));
					order.setJobName(set.getString(4));
					order.setJobDescription(set.getString(5));
					order.setTotalAmount(set.getBigDecimal(27));
					String paymnt=oper.getValueFromColum("payment", "order_id", ""+set.getLong(1), 2);
					order.setAdvance(paymnt);
					//String tot=""+set.getBigDecimal(27);
					float tot=Float.parseFloat(""+set.getBigDecimal(27));
					float pymnt=Float.parseFloat(paymnt);
					BigDecimal due=BigDecimal.valueOf(tot-pymnt);
					order.setBalanceDue(due);
					list.add(order);
				}
			}
		}catch(Exception e){
			
		}
		return list;
	}
	public void setPaid(String orderId){
		DbOperation operation=new DbOperation();
		operation.update("orders", "id='"+orderId+"'", "order_status", "0");
	}
	public void setPaid(String orderId,String discount){
		DbOperation operation=new DbOperation();
		operation.update("orders", "id='"+orderId+"'", "order_status", "0");
		operation.update("orders", "id='"+orderId+"'", "discount", discount);
	}
	public ArrayList<Order> getById(String id){
		ArrayList<Order> list=new ArrayList<Order>();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.getData("orders","id="+id);
			while(set.next()){
					Order order=new Order();
					order.setOrderId(set.getLong(1));
					order.setJobNo(set.getString(2));
					order.setCustomerId(set.getLong(3));
					order.setJobName(set.getString(4));
					order.setJobDescription(set.getString(5));
					order.setTotalAmount(set.getBigDecimal(27));
					String paymnt=oper.getValueFromColum("payment", "order_id", ""+set.getLong(1), 2);
					order.setAdvance(paymnt);
					float tot=Float.parseFloat(""+set.getBigDecimal(27));
					float pymnt=Float.parseFloat(paymnt);
					BigDecimal due=BigDecimal.valueOf(tot-pymnt);
					order.setBalanceDue(due);
					list.add(order);
				}
		}catch(Exception e){
			
		}
		return list;
	}
}
