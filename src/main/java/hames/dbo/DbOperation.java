package hames.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOperation {

	public static final String DB_URL="jdbc:postgresql://localhost:5000/hames";
	public static final String DB_USER="postgres";
	public static final String DB_PASS="postgres";
	//public void insert(String table_name,String )
	Connection connection;
	public ResultSet getData(String table_name){
		ResultSet set=null;
		try {
			Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql_query="SELECT * FROM "+table_name;
			set=stat.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	public String deleteData(String id){
		String ex="";
		try {
			Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql="DELETE FROM staff WHERE id='"+id+"'";
			stat.executeQuery(sql);
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ex=e.getMessage();
			e.printStackTrace();
		}
		return ex;
		}
	public ResultSet takeValueWRTid(String table_name,String value){
		ResultSet set=null;
		Connection con;
		try {
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql_query="SELECT * FROM "+table_name+" WHERE id="+value;
			set=stat.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return set;
	}
	public String getValueFromColum(String table_name,String Column,String value,int colmNumber){
		String val="";
		Connection con;
		try {
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql_query="SELECT * FROM "+table_name+" WHERE "+Column+"="+value;
			ResultSet set=stat.executeQuery(sql_query);
			set.next();
			val=set.getString(colmNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	public ResultSet getData(String table_name,String where){
		ResultSet set=null;
		try {
			Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql_query="SELECT * FROM "+table_name+" WHERE "+where;
			set=stat.executeQuery(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	public void update(String table_name,String where,String column,String value){
		try{
			Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql="UPDATE "+table_name+" SET "+column+"='"+value+"' WHERE "+where;
			stat.executeQuery(sql);
			stat.close();
			con.close();
		}catch(Exception e){
			
		}
		
	}
	public void insertRow(String table,String values){
		Connection con;
		try {
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			Statement stat=con.createStatement();
			String sql="INSERT INTO "+table+" VALUES("+values+")";
			stat.executeQuery(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public ResultSet runSql(String sql){
		ResultSet set=null;
		try{
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
		Statement stat=con.createStatement();
		//String sql="INSERT INTO "+table+" VALUES("+values+")";
		set=stat.executeQuery(sql);
		}catch(Exception e){
			
		}
		return set;
	}
}
