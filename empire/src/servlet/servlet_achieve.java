/**
 * 
 */
/**
 * @author pc
 *
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.thing_com;
import dao.user_com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.thing;
import entity.user;
import unit.sql;

public class servlet_achieve extends HttpServlet{
    
    public static Connection conn = sql.getConnection();
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    
	user user= new user();
	
	int pagesize=6;
	int totalpage=0;
	int nowshowpage=1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request,response);
	    
	   }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String type=request.getParameter("who"); 
		
		if("finduser".equals(type)){
            finduser(request, response);
        } 
		if("flip".equals(type)){
            flip(request, response);
        } 
		if("findid".equals(type)){
            findid(request, response);
        }
		if("thingtopla".equals(type)){
			thingtopla(request, response);
        }
		if("platologin".equals(type)){
			platologin(request, response);
        }
		if("deletethpla".equals(type)){
			deletethpla(request, response);
        }
		if("deletethth".equals(type)){
			deletethth(request, response);
        }
		if("addpla".equals(type)){
			addpla(request, response);
        }
		if("addthing".equals(type)){
			addthing(request, response);
        }
		if("chthpla".equals(type)){
			chthpla(request, response);
        }
		if("chthth".equals(type)){
			chthth(request, response);
        }
		if("start".equals(type)){
			start(request, response);
        }
		if("lastpage".equals(type)){
			lastpage(request, response);
        }
		if("nextpage".equals(type)){
			nextpage(request, response);
        }
		if("ajaxnowshowpage".equals(type)){
			ajaxnowshowpage(request, response);
        }
		if("ajaxtotalpage".equals(type)){
			ajaxtotalpage(request, response);
        }
		
	}
	
	//查询全部（已禁用）
	public void flip(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		
		List<thing> list=new ArrayList<thing>();
		list=thing_com.findall();
    	request.setAttribute("id",user.id);
    	request.setAttribute("username",user.username);
		request.setAttribute("list",list);
    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		
	}
	
	public void finduser(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		user=user_com.finduser(username,password);
		
		if(user.username!=null){
/*			start(request,response);*/
        	request.setAttribute("id",user.id);
        	request.setAttribute("username",user.username);
        	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		}else{
        	request.setAttribute("error_login","用户名或密码错误！");
    		request.getRequestDispatcher("login.jsp").forward(request,response);
        	
        }
		
	}
	
	public void findid(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("findthingid"));
		thing thing = null;
		thing=thing_com.findid(id);
		
		if(thing!=null){
			request.setAttribute("findid",thing.id);
			request.setAttribute("findname",thing.name);
			request.setAttribute("findtime",thing.time);
			request.getRequestDispatcher("thing.jsp").forward(request,response);
		}else{
        	request.setAttribute("error_findthing","id错误");
        	nowpage(request,response);
		}
	}
	
	public void deletethpla(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		int dethplaid=Integer.parseInt(request.getParameter("dethplaid"));
		thing_com.delete(dethplaid);
		nowpage(request,response);
		
	}
	
	public void deletethth(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		int deththid = -1;
		try {
			deththid=Integer.parseInt(request.getParameter("deththid"));
			thing_com.delete(deththid);
			nowpage(request,response);
			nowshowpage();
	    	 } catch (Exception e) { 
	         	request.setAttribute("error_thdechid","id错误");
	    		request.getRequestDispatcher("thing.jsp").forward(request,response);
	    	 }
		}
	
	public void thingtopla(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		nowpage(request,response);
	}
	
	public void platologin(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		request.getRequestDispatcher("login.jsp").forward(request,response);
		
	}
	
	public void addpla(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		request.getRequestDispatcher("thing.jsp").forward(request,response);
		
	}
	public void addthing(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		String name=request.getParameter("addthname");
		thing_com.add(name);
		nowshowpage();
		request.getRequestDispatcher("thing.jsp").forward(request,response);
	}
	
	public void chthpla(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		int chthid=Integer.parseInt(request.getParameter("chthid"));
		String chthname=request.getParameter("chthname");
		String chthtime=request.getParameter("chthtime");
		
		request.setAttribute("findid",chthid);
		request.setAttribute("findname",chthname);
		request.setAttribute("findtime",chthtime);
		
		request.getRequestDispatcher("thing.jsp").forward(request,response);	
	}
	
	public void chthth(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		thing thing = new thing();
		int chthid = -1;
		try{
			chthid=Integer.parseInt(request.getParameter("chthid"));
			String chthname=request.getParameter("chthname");
			
			thing=thing_com.updata(chthname,chthid);
			
			request.setAttribute("findid",thing.id);
			request.setAttribute("findname",thing.name);
			request.setAttribute("findtime",thing.time);
			request.getRequestDispatcher("thing.jsp").forward(request,response);
		}catch(Exception e){
        	request.setAttribute("error_thdechid","id错误");
        	request.getRequestDispatcher("thing.jsp").forward(request,response);
		}
		
	}
	
	public void start(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
/*		List<thing> list=new ArrayList<thing>();
		user.prepage=0;
		nowshowpage=1;
		nowshowpage();
		list=thing_com.findall2(user.prepage, pagesize);
    	request.setAttribute("id",user.id);
    	request.setAttribute("username",user.username);
		request.setAttribute("list",list);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("nowshowpage",nowshowpage);
    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);*/
		
		user.prepage=0;
		nowshowpage=1;
		nowshowpage();
		
		List<thing> list=new ArrayList<thing>();
		list=thing_com.findall2(user.prepage, pagesize);

		String thinglist=JSONArray.toJSONString(list);
		response.getWriter().write(thinglist);
		
	}
	
	public void lastpage(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
	/*	List<thing> list=new ArrayList<thing>();
		if(nowshowpage>1){
		user.prepage=user.prepage-pagesize;
		nowshowpage=nowshowpage-1;
		list=thing_com.findall2(user.prepage, pagesize);
    	request.setAttribute("id",user.id);
    	request.setAttribute("username",user.username);
		request.setAttribute("list",list);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("nowshowpage",nowshowpage);
    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		}else{
			list=thing_com.findall2(user.prepage, pagesize);
	    	request.setAttribute("id",user.id);
	    	request.setAttribute("username",user.username);
			request.setAttribute("list",list);
			request.setAttribute("totalpage",totalpage);
			request.setAttribute("nowshowpage",nowshowpage);
	    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		}*/
		
		List<thing> list=new ArrayList<thing>();
		if(nowshowpage>1){
			user.prepage=user.prepage-pagesize;
			nowshowpage=nowshowpage-1;
			list=thing_com.findall2(user.prepage, pagesize);

			String thinglist=JSONArray.toJSONString(list);
			response.getWriter().write(thinglist);
			}else{
				
				list=thing_com.findall2(user.prepage, pagesize);

				String thinglist=JSONArray.toJSONString(list);
				response.getWriter().write(thinglist);
			}

	}
	
	public void nextpage(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
/*		List<thing> list=new ArrayList<thing>();
		if(nowshowpage<totalpage){
		user.prepage=user.prepage+pagesize;
		nowshowpage=nowshowpage+1;
		list=thing_com.findall2(user.prepage, pagesize);
    	request.setAttribute("id",user.id);
    	request.setAttribute("username",user.username);
		request.setAttribute("list",list);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("nowshowpage",nowshowpage);
    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		}else{
			list=thing_com.findall2(user.prepage, pagesize);
	    	request.setAttribute("id",user.id);
	    	request.setAttribute("username",user.username);
			request.setAttribute("list",list);
			request.setAttribute("totalpage",totalpage);
			request.setAttribute("nowshowpage",nowshowpage);
	    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		}*/
		
		List<thing> list=new ArrayList<thing>();
		if(nowshowpage<totalpage){
			user.prepage=user.prepage+pagesize;
			nowshowpage=nowshowpage+1;
			list=thing_com.findall2(user.prepage, pagesize);

			String thinglist=JSONArray.toJSONString(list);
			response.getWriter().write(thinglist);
			}else{
				
				list=thing_com.findall2(user.prepage, pagesize);

				String thinglist=JSONArray.toJSONString(list);
				response.getWriter().write(thinglist);
			}
	}
	
	public void nowpage(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		List<thing> list=new ArrayList<thing>();
		nowshowpage();
		list=thing_com.findall2(user.prepage, pagesize);
    	request.setAttribute("id",user.id);
    	request.setAttribute("username",user.username);
		request.setAttribute("list",list);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("nowshowpage",nowshowpage);
    	request.getRequestDispatcher("Planeptune.jsp").forward(request,response);
		
	}
	
	public void ajaxnowshowpage(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		String anowshowpage=String.valueOf(nowshowpage);
		response.getWriter().write(anowshowpage);
		
	}
	
	public void ajaxtotalpage(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException{
		String atotalpage=String.valueOf(totalpage);
		response.getWriter().write(atotalpage);
		
	}
	
	public void nowshowpage()
	           throws ServletException, IOException{
		int totalrows=thing_com.findallrows();
		if(totalrows/pagesize==0){
			totalpage=1;
		}else if(totalrows/pagesize>0&&totalrows%pagesize==0){
			totalpage=totalrows/pagesize;
		}else if(totalrows/pagesize>0&&totalrows%pagesize!=0){
			totalpage=(totalrows/pagesize)+1;
		}
		
	}
	
}