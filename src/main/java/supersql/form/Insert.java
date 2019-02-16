 package supersql.form;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supersql.common.Log;

public class Insert extends HttpServlet {
	
  private static final long serialVersionUID = 8021503235844232672L;

  @Override
public void doPost(HttpServletRequest req, 
                      HttpServletResponse res) 
                          throws ServletException, IOException {

    // ContentType��ݒ�
    res.setContentType("text/html; charset=Shift_JIS");
    req.setCharacterEncoding("Shift-JIS");
    
    res.getWriter();

    String sqlfile = new String();
    sqlfile = req.getHeader("referer");

	Log.info(sqlfile);
	/*
	sqlfile = sqlfile.substring(sqlfile.indexOf("query=")+6);
	sqlfile = sqlfile.substring(0,sqlfile.indexOf("&cond"));

	Log.info(sqlfile);
    */
    //sqlfile = sqlfile.substring(sqlfile.indexOf("&query="),sqlfile.indexOf("&",sqlfile.indexOf("&query=")));
    /*
    try{
    	sqlfile = req.getParameter("sqlfile");
    }catch(NullPointerException e){
    	out.println("no ssql file \n");
    	System.exit(-1);
    }
    */

    while(true)
	{   
    	Enumeration<String> names = req.getParameterNames();
    	String sql = new String();
    	String tabname = new String();
    	String attlist = new String();
    	String vallist = new String();
    	
    	//get TABLE
    	if(req.getParameter("tableinfo") != null){
    		String[] strAry = req.getParameter("tableinfo").split(" ");
        	tabname += strAry[0];
    	}
    	
    	//get ATT and VALUE
        while (names.hasMoreElements()){
        	String name = names.nextElement();
        	if(name.contains(".")){
        		if(!req.getParameter(name).equals(null) && req.getParameter(name).length() != 0){
        			attlist += name.substring(name.indexOf(".")+1,name.length())  + ",";
    	        	vallist += "'"+ req.getParameter(name)+ "' " + ",";
    	    	}
        	}
        }
        if(attlist.length() != 0){
	        attlist = attlist.substring(0,attlist.length()-1);
	        vallist = vallist.substring(0,vallist.length()-1);
	       	sql = "INSERT INTO " + tabname + " (" + attlist + ") VALUES (" + vallist + ");";
	    	insert(sql);
        }
        
    	Log.info("sql:"+sql);
    	
    	break;
    	
	}
    res.sendRedirect(sqlfile);
  } 
  public void insert(String sql){
	  
	  //update database
	  try {
		  Class.forName("org.postgresql.Driver"); // PostgreSQL�̏ꍇ
	      Connection con =
	        DriverManager.getConnection("jdbc:postgresql:ssql",
	                                    "chie",
	                                    ""); // PostgreSQL�̏ꍇ
	      Statement stmt = con.createStatement();
	
	      stmt.executeQuery(sql);
	      
	      return;
      } catch (Exception e) {
    	  Log.err("sqlerr"+ e);
      }
  }
}