package com.hames.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Use this class to build pipeline query like (group, sort, projection and match)
 * @author 
 */
public class HamesDBObject extends BasicDBObject{

	private DBObject value;
	private String operator;
	
	public HamesDBObject(String operator){
		this.operator = operator;
	}
	
	public DBObject get(){
		DBObject dbObject;
		if(getValue() == null){
			dbObject = new BasicDBObject(operator,this);	
		}else{
			dbObject = new BasicDBObject(operator,getValue());
		}
		return dbObject;
	}

	public DBObject getValue() {
		return value;
	}
	public void setValue(DBObject value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	
}
