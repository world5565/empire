/**
 * 
 */
/**
 * @author pc
 *
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.user;
import unit.sql;

public class user_com{
	
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps = null;
	
	public static user finduser(String username,String password){
		
		user user=new user();
		conn = sql.getConnection();
		
		String sql1 = "select * from user_information where username=? and password=?";
		
		try{
			ps = conn.prepareStatement(sql1);
	        ps.setString(1,username);
	        ps.setString(2,password);
	        rs = ps.executeQuery();
	        
	        if(rs.next()){
	        	user.setid(rs.getInt("id"));
	        	user.setusername(rs.getString("username"));
	        	user.setpassword(rs.getString("password"));
	        	user.setprepage(0);
	        }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
		
		return user;
	}
	
}