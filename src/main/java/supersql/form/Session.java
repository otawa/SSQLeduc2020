package supersql.form;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import supersql.common.GlobalEnv;
import supersql.common.Log;

public class Session extends HttpServlet {

	private static final long serialVersionUID = 8021503235844232672L;

	static String host;
	static String db;
	static String user;
	static String dbms;
	static String driver;

	@Override
	public void doPost(HttpServletRequest req, 
			HttpServletResponse res) 
	throws ServletException, IOException {


		//os
		String enc = "EUC-JP";	
		if(System.getProperty("os.name").indexOf("Windows")>=0){
			enc =  "Shift_JIS";
		}

		// ContentType��ݒ�
		res.setContentType("text/html; charset="+enc);
		req.setCharacterEncoding(enc);

		res.getWriter();

		String sqlfile = new String();
		sqlfile = req.getHeader("referer");

		if(req.getParameter("sqlfile") != null){
			sqlfile = req.getParameter("sqlfile");
		}
		if(req.getParameter("linkfile") != null){
			sqlfile = req.getParameter("linkfile");
		}

		String configfile = new String(); 
		configfile = "http://localhost:8080/invoke/config.ssql";
		//get configfile
		if(req.getParameter("configfile") != null){
			configfile = req.getParameter("configfile");
		}

		String[] stmp = {"-f",sqlfile,"-o",sqlfile,"-c",configfile,"-debug"};
		GlobalEnv.setGlobalEnv(stmp);
		//dbms setting
		host = GlobalEnv.gethost();
		db = GlobalEnv.getdbname();
		user = GlobalEnv.getusername();
		dbms = GlobalEnv.getdbms();
		driver = GlobalEnv.getDriver();
		
		if(!sqlfile.toLowerCase().startsWith("http://")){
			sqlfile = GlobalEnv.getFileDirectory() + sqlfile;
		}

		Log.info(sqlfile);

		while(true)
		{   

			Enumeration<String> names = req.getParameterNames();
			String message = new String();
			String sql = new String();
			String tabname = new String();
			Vector<String> att = new Vector<String>();
			String attcond = new String("true ");
			String session_att = new String();

			
			if(req.getParameter("logout") != null){
				HttpSession session = req.getSession(false);
				if (session!=null) {
					session.invalidate();
				}
				break;
			}

			//get TABLE
			if(req.getParameter("tableinfo") != null){
				tabname += req.getParameter("tableinfo");
			}
			//get att set
			if(req.getParameter("att") != null){
				session_att = req.getParameter("att");
			}
			//get ATT and VALUE
			while (names.hasMoreElements()){
				String name = names.nextElement();
				if(name.contains(".")){
					if(req.getParameter(name + ":pwd") != null && req.getParameter(name + ":pwd").equals("md5")){
						String s = req.getParameter(name);
						try{
							MessageDigest md = MessageDigest.getInstance("MD5");  
							md.update(s.getBytes());   
							byte[] hash = md.digest();   	
							StringBuffer hexString = new StringBuffer();   
							for (int j = 0; j < hash.length; j++) {   
								if ((0xff & hash[j]) < 0x10) {   
									hexString.append("0" + Integer.toHexString((0xFF & hash[j])));   
								} else {   
									hexString.append(Integer.toHexString(0xFF & hash[j]));   
								}   
							}
							attcond += " AND " + name + " = '" + hexString.toString() + "'";
							att.add(name.substring(name.indexOf(".")+1,name.length()));
						}catch(Exception mde){
							//error
						}
					}else if(!req.getParameter(name).equals(null) && req.getParameter(name).length() != 0 && !name.contains(":pwd")){
						attcond += " AND " + name + " = '" + req.getParameter(name) + "'";
						att.add(name.substring(name.indexOf(".")+1,name.length()));
					}
				}
			}
			if(attcond.length() > 5){
				sql = "SELECT * FROM " + tabname + " WHERE " + attcond + ";";
				ResultSet rs = logincheck(sql);	
				if(!(rs == null)){
					// start session
					HttpSession session = req.getSession(true);
					// session message
					message = "login ok";
					try{
						//if att = all, set all atts to session
						if(session_att != null && session_att.equalsIgnoreCase("all")){
							att = new Vector<String>();
							ResultSetMetaData metaData = rs.getMetaData();
							int columnCount = metaData.getColumnCount();
							for (int i=0; i<columnCount; i++) {
								att.add(metaData.getColumnName(i+1));
							}		    				
						}else if(session_att != null){
							ResultSetMetaData metaData = rs.getMetaData();
							int columnCount = metaData.getColumnCount();
							for (int i=0; i<columnCount; i++) {
								if(session_att.equals(metaData.getColumnName(i+1)))
									att.add(metaData.getColumnName(i+1));
							}
						}
						rs.beforeFirst();
						while(rs.next()){
							for( int i = 0; i < att.size(); i++){
								// �擾
								String tmp = rs.getString(att.get(i));
								session.setAttribute( att.get(i) , tmp);
								Log.info("SESSION  " + att.get(i) + " : " + tmp + "," + session.getAttribute(att.get(i)));
							}
						}
					}catch(Exception e){

					}
				}else{
					message = "NOT LOGINED";
				}
			}else{
				message = "";
			}
			if(sqlfile.indexOf("&message=") > 0){
				if(sqlfile.indexOf("&",sqlfile.indexOf("&message=")) > 0){
					String tmp1 = sqlfile.substring(0,sqlfile.indexOf("&message="));
					String tmp2 = sqlfile.substring(sqlfile.indexOf("&message=")+9);
					String tmp3;
					if(tmp2.contains("&")){
						tmp3 = tmp2.substring(sqlfile.indexOf("&"));
					}else{
						tmp3 = "";
					}
					sqlfile = tmp1 + tmp3;
				}else{
					sqlfile = sqlfile.substring(0,sqlfile.indexOf("&message="));
				}
			}
			sqlfile += "&message=" + message;

			Log.info("sql:"+sql);

			break;

		}
		res.sendRedirect(sqlfile);
	} 
	
	public ResultSet logincheck(String sql){

		//select database
		try {
			// connect db
			Class.forName(driver);
			Connection con =
				DriverManager.getConnection("jdbc:"+dbms+"://" + host + "/" + db,
						user,driver);
			// create st obj
			Statement stmt = con.createStatement();

			// exec
			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;
			while(rs.next()) {
				count++;
			}

			if(count == 1){
				return rs;
			}else{
				return null;
			}

		} catch (Exception e) {
			Log.err("sqlerr:"+ e);
			return null;
		}
	}
}