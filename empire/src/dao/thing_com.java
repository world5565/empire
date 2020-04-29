package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.thing;
import unit.sql;

public class thing_com {
    public static Connection conn = null;
    public static Statement stmt=null;
    public static ResultSet rs = null;
    public static PreparedStatement ps = null;
    
    public static int findallrows(){
    	int totalrows=0;
    	
    	conn = sql.getConnection();
    	try{
        	String sql1 = "select count(*) from thing1";
    		ps = conn.prepareStatement(sql1);
	        rs = ps.executeQuery();
	        if(rs.next()){
	        	totalrows=rs.getInt(1);
	        }else{
	        	return 0;
	        }
    		
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
    	return totalrows;
    	
    }
    
    public static List<thing> findall2(int prepage, int pagesize){
    	
    	conn = sql.getConnection();
    	List<thing> list=new ArrayList<thing>();
    	
    	try{
    		String sql1 = "select * from thing1 limit ?,?";
    		ps = conn.prepareStatement(sql1);
    		ps.setInt(1, prepage);
    		ps.setInt(2, pagesize); 
	        rs = ps.executeQuery();
	        thing thing=null;
	        while(rs.next()){
	        	thing=new thing();
	        	thing.setid(rs.getInt("id"));
	        	thing.setname(rs.getString("name1"));
	        	thing.settime(rs.getString("time1"));
	        	list.add(thing);
	        }
    		
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
    	return list;	
    }
    
    public static List<thing> findall(){
    	
    	conn = sql.getConnection();
    	List<thing> list=new ArrayList<thing>();
    	
    	try{
    		String sql1 = "select * from thing1 ";
    		ps = conn.prepareStatement(sql1);
	        rs = ps.executeQuery();
	        thing thing=null;
	        while(rs.next()){
	        	thing=new thing();
	        	thing.setid(rs.getInt("id"));
	        	thing.setname(rs.getString("name1"));
	        	thing.settime(rs.getString("time1"));
	        	list.add(thing);
	        	
	        }
    		
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
		return list;
    }
    
    public static void add(String name){
    	
    	conn = sql.getConnection();
    	
    	String sql1 = "insert into thing1(name1,time1) values(?,?)";
    	
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String time = sdf.format(d);

    	try{
    		ps = conn.prepareStatement(sql1);
	        ps.setString(1,name);
	        ps.setString(2,time);
	        ps.executeUpdate();
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
    }
    
    public static void delete(int id){
    	
    	conn = sql.getConnection();
    	
    	String sql1 = "delete from thing1 where id=?";
    	
    	try{
    		ps = conn.prepareStatement(sql1);
	        ps.setInt(1,id);
	        ps.executeUpdate();
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
    }
    
    public static thing updata(String name,int id){
    	
    	conn = sql.getConnection();
    	
    	thing thing = new thing();
    	
    	String sql1 = "update thing1 set name1=?, time1=? where id=?";
    	
    	Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String time = sdf.format(d);
    	
    	thing.setid(id);
    	thing.setname(name);
    	thing.settime(time);

    	try{
    		ps = conn.prepareStatement(sql1);
	        ps.setString(1,name);
	        ps.setString(2,time);
	        ps.setInt(3,id);
	        ps.executeUpdate();
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
    	
    	return thing;
    	
    }
    
    public static thing findid(int id){
    	
    	thing thing=new thing();
    	conn = sql.getConnection();
    	
    	String sql1 = "select * from thing1 where id=?";
    	
    	try{
    		ps = conn.prepareStatement(sql1);
	        ps.setInt(1,id);
	        rs = ps.executeQuery();
	        
	        if(rs.next()){
	        	thing.setid(rs.getInt("id"));
	        	thing.setname(rs.getString("name1"));
	        	thing.settime(rs.getString("time1"));
	        }else{
	        	return null;
	        }
    	}catch(Exception e){
			e.printStackTrace();
		}finally{
			sql.close(rs,ps,conn);
		}
        return thing;
    }

}
