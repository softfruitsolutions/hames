package hames.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hames.bean.Customer;

public class CustomerDbo {
	
	Connection connection;
	java.sql.Statement statement;
	String exception;
	
	private void createConnection(){
		try {
			
			connection=DriverManager.getConnection(DbOperation.DB_URL,DbOperation.DB_USER,DbOperation.DB_PASS);
			statement=connection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeConnection(){
		try{
			statement.close();
			connection.close();
		}catch(Exception e){
			
		}
	}
	public ArrayList<Customer> getAll(){
		ArrayList<Customer> list=new ArrayList<Customer>();
		createConnection();
		String sql="SELECT * FROM customer";
		try {
			ResultSet set=statement.executeQuery(sql);
			while(set.next()){
				Customer customer=new Customer();
				customer.setCustomerId(set.getLong(1));
				customer.setFirstName(set.getString(2));
				customer.setMiddleName(set.getString(3));
				customer.setLastName(set.getString(4));
				customer.setPhoneNumber(set.getString(5));
				customer.setMobileNumber(set.getString(6));
				customer.setPermanentAddress(set.getString(7));
				customer.setTemporaryAddress(set.getString(8));
				customer.setStatus(set.getInt(9));
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return list;
	}
	public Customer getOne(int id){
		Customer customer=new Customer();
		createConnection();
		String sql="SELECT * FROM customer WHERE id="+id;
		try {
			ResultSet set=statement.executeQuery(sql);
			set.next();
			customer.setCustomerId(set.getLong(1));
			customer.setFirstName(set.getString(2));
			customer.setMiddleName(set.getString(3));
			customer.setLastName(set.getString(4));
			customer.setPhoneNumber(set.getString(5));
			customer.setMobileNumber(set.getString(6));
			customer.setPermanentAddress(set.getString(7));
			customer.setTemporaryAddress(set.getString(8));
			customer.setStatus(set.getInt(9));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return customer;
	}
	public ArrayList<Customer> getAll(String tag){
		DbOperation oper=new DbOperation();
		ResultSet set=oper.getData("customer");
		ArrayList<Customer> list=new ArrayList<Customer>();
		try {
			while(set.next()){
				if(set.getString(2).contains(tag)){
					Customer customer=new Customer();
					customer.setFirstName(set.getString(2));
					customer.setCustomerId(set.getLong(1));
					list.add(customer);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public String getCustomerName(String id){
		String name="";
		try{
			DbOperation op=new DbOperation();
			ResultSet set=op.getData("customer", "id='"+id+"'");
			set.next();
			name=set.getString(2);
		}catch(Exception e){
			
		}
		return name;
	}
}
