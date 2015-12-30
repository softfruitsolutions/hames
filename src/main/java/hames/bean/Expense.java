package hames.bean;

import java.util.ArrayList;

import org.omg.CORBA.NameValuePair;

public class Expense {

	String expense,item1name,item1cost,item2name,item2cost,item3name,item3cost,item4name,item4cost,date;
	Integer id;
	ArrayList<NameValuePair> list;
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public String getExpense() {
		return expense;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setList(ArrayList<NameValuePair> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public String getItem1cost() {
		return item1cost;
	}
	public String getItem1name() {
		return item1name;
	}
	public String getItem2cost() {
		return item2cost;
	}
	public String getItem2name() {
		return item2name;
	}
	public String getItem3cost() {
		return item3cost;
	}
	public String getItem3name() {
		return item3name;
	}
	public String getItem4cost() {
		return item4cost;
	}
	public String getItem4name() {
		return item4name;
	}
	public void setItem1cost(String item1cost) {
		this.item1cost = item1cost;
	}
	public void setItem1name(String item1name) {
		this.item1name = item1name;
	}
	public void setItem2cost(String item2cost) {
		this.item2cost = item2cost;
	}
	public void setItem2name(String item2name) {
		this.item2name = item2name;
	}
	public void setItem3cost(String item3cost) {
		this.item3cost = item3cost;
	}
	public void setItem3name(String item3name) {
		this.item3name = item3name;
	}
	public void setItem4cost(String item4cost) {
		this.item4cost = item4cost;
	}
	public void setItem4name(String item4name) {
		this.item4name = item4name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
}
