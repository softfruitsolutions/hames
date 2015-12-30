package hames.dbo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hames.bean.Staff;

public class StaffDbo {

	
	public ArrayList<Staff> getAll(){
		DbOperation oper=new DbOperation();
		ResultSet set=oper.getData("staff");
		ArrayList<Staff> list=new ArrayList<Staff>();
		try {
			while(set.next()){
				Staff staff=new Staff();
				staff.setStaffId(set.getLong(1));
				staff.setFirstName(set.getString(2));
				staff.setRoleId(set.getLong(12));
				list.add(staff);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public Staff getOne(int id){
		Staff staff=new Staff();
		DbOperation oper=new DbOperation();
		try{
			ResultSet set=oper.takeValueWRTid("staff", ""+id);
			set.next();
			staff.setStaffId(set.getLong(1));
			staff.setFirstName(set.getString(2));
			staff.setLastName(set.getString(4));
			staff.setRoleId(set.getLong(11));
		}catch(Exception e){
			
		}
		return staff;
	}
	public ArrayList<Staff> getAll(String tag){
		DbOperation oper=new DbOperation();
		ResultSet set=oper.getData("staff");
		ArrayList<Staff> list=new ArrayList<Staff>();
		try {
			while(set.next()){
				if(set.getString(2).contains(tag)){
					String role="Staff role";
					ResultSet tempset=oper.takeValueWRTid("staff_role", set.getString(12));
					tempset.next();
					role=tempset.getString(2);
					Staff staff=new Staff();
					staff.setStaffId(set.getLong(1));
					staff.setFirstName(set.getString(2));
					staff.setMiddleName(role);
					staff.setRoleId(set.getLong(12));
					list.add(staff);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
}
