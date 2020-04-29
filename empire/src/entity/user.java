/**
 * 
 */
/**
 * @author pc
 *
 */
package entity;

public class user{
	
	public int id;
	public String username;
	public String password;
	
	public int prepage;
	
	public int getid(){
		return id;
	}
	public void setid(int id){
		this.id=id;
	}
	
	public String getusername(){
		return username;
	}
	public void setusername(String username){
		this.username=username;
	}
	
	public String getpassword(){
		return password;
	}
	public void setpassword(String password){
		this.password=password;
	}
	
	public int getprepage(){
		return prepage;
	}
	public void setprepage(int prepage){
		this.prepage=prepage;
	}
}