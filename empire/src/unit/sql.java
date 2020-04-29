/**
 * 
 */
/**
 * @author pc
 *
 */
package unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql{
	
    public static final String URL="jdbc:mysql://localhost:3306/empire?useUnicode=yes&characterEncoding=utf8";
    public static final String NAME = "root";
    public static final String PASSWORD = "123456";
    public static final String DREIVER = "com.mysql.jdbc.Driver"; 
    
    static {
        try {
        	
            Class.forName(DREIVER);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
    	
    	try {
    		
    	 return  DriverManager.getConnection(URL, NAME, PASSWORD);
    	 
    	 } catch (SQLException e) { 
    		 
    		 e.printStackTrace();
    	 }
    	return null;
    	}
    
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
    
}