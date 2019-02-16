package supersql.form;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supersql.common.GlobalEnv;
import supersql.common.Log;
//import java.util.Hashtable;

public class Update extends HttpServlet {

	private static final long serialVersionUID = 8021503235844232672L;


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

		String sqlfile = new String();
		sqlfile = req.getHeader("referer");

		String where = new String();
		String message = new String();

		String configfile = new String(); 
		while(true)
		{ 
			configfile = "http://localhost:8080/invoke/config.ssql";
			if(req.getParameter("configfile") != null)
				configfile = req.getParameter("configfile");
			String[] args = {"-f",sqlfile,"-cond",where,"-c",configfile};
			GlobalEnv.setGlobalEnv(args);
			FormEnv.getFormEnv();

			Enumeration<String> names = req.getParameterNames();
			String param = new String();

			//get sql param
			if(req.getParameter("sql_param") != null){
				param = req.getParameter("sql_param");
			}

			//get configfile
			if(req.getParameter("configfile") != null){
				configfile = req.getParameter("configfile");
			}

			if(names.hasMoreElements()){
				if(param.equals("insert")){
					Log.info("START INSERT DATABASE");
					message = insert(req,sqlfile);
					Log.info("=====================");
				}else if(param.equals("delete")){
					Log.info("START DELETE DATABASE");
					message = delete(req,sqlfile);
					Log.info("=====================");
				}else if(param.equals("update")){
					Log.info("START UPDATE DATABASE");
					message = update(req,sqlfile);
					Log.info("=====================");
				}
			}



			if(sqlfile.contains("message=")){
				int f = sqlfile.indexOf("message=");
				int e = sqlfile.length();
				if(sqlfile.indexOf("&",f) > 0){
					int m = sqlfile.indexOf("&",f) + 1;
					sqlfile = sqlfile.substring(0,f) + sqlfile.substring(m,e);
				}else{
					sqlfile = sqlfile.substring(0,f);
				}
			}
			if(message != null && !message.isEmpty()){
				sqlfile += "&message=" + URLEncoder.encode(message,enc);
			}
			
			Log.info("sqlfile is " + sqlfile);

			break;

		}		

		res.sendRedirect(sqlfile);

	} 

	public String insert(HttpServletRequest req, String sqlfile){
		Enumeration<String> names = req.getParameterNames();
		String attlist = new String();
		String vallist = new String();
		String sql = new String();
		String tabname = new String();
		String alias = new String();
		FormServlet fs = new FormServlet();

		//get TABLE
		if(req.getParameter("tableinfo") != null){
			String[] strAry = req.getParameter("tableinfo").split(" ");
			tabname = strAry[0];
			alias = strAry[1];
		}

		//get ATT and VALUE
		while (names.hasMoreElements()){
			String name = (String)names.nextElement();
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
						attlist += name.substring(name.indexOf(".")+1,name.length())  + ",";
						vallist += "'"+ hexString.toString()+ "' " + ",";
					}catch(Exception mde){
						//error
					}
				}else if(name.endsWith(":const")){

				}else if(!req.getParameter(name).equals(null) && req.getParameter(name).length() != 0 && !name.contains(":pwd")){
					attlist += name.substring(name.indexOf(".")+1,name.length())  + ",";
					vallist += "'"+ req.getParameter(name)+ "' " + ",";
				}else if( req.getParameter(name).equals(null) || req.getParameter(name).length() == 0){
					attlist += name.substring(name.indexOf(".")+1,name.length())  + ",";
					vallist += "'' " + ",";
				}
			}
		}

		//check att
		String[] tmpatt = attlist.split(",");
		String[] tmpval = vallist.split(",");
		String[][] tmpatt2 = new String[tmpatt.length][2];
		for(int i=0;i<tmpatt.length;i++){
			tmpatt2[i][0] = alias + "." +tmpatt[i];
			tmpatt2[i][1] = tmpval[i].replace("'", "").trim();
			//Log.info(tmpatt2[i][0] + tmpatt2[i][1]);
		}
		fs.checkAtt(tmpatt2, req);

		//CREATE SQL
		if(attlist.length() != 0 && !fs.errflg){
			attlist = attlist.substring(0,attlist.length()-1);
			vallist = vallist.substring(0,vallist.length()-1);
			sql = "INSERT INTO " + tabname + " (" + attlist + ") VALUES (" + vallist + ");";
			exec_query(sql,fs);
			fs.errMessage(sqlfile);
			return fs.errmessage;
		}else{
			if(attlist.length() == 0){
				fs.errmessage = "NO VALUE ERROR";
			}
			Log.err("SQLERROR(INSERT)"+fs.errmessage);
			fs.errMessage(sqlfile);
			return fs.errmessage;
		}
	}

	public String delete(HttpServletRequest req, String sqlfile){
		Enumeration<String> names = req.getParameterNames();
		ArrayList<String> vallist = new ArrayList<String>() ;
		String sql = new String();
		String tabname = new String();

		//get TABLE
		if(req.getParameter("tableinfo") != null){
			String[] strAry = req.getParameter("tableinfo").split(" ");
			tabname += strAry[0];
		}

		//get ATT and VALUE
		while (names.hasMoreElements()){
			String name = (String)names.nextElement();
			if(name.contains(".")){
				if(!req.getParameter(name).equals(null) && req.getParameter(name).length() != 0){
					String[] tmp = req.getParameterValues(name);
					for(int i=0;i < tmp.length;i++){
						vallist.add( name.split("\\.")[1] + " = " + tmp[i]);
					}
				}
			}
		}

		FormServlet fs = new FormServlet();
		fs.errMessage(sqlfile);
		//CREATE SQL
		if(vallist.size() != 0){
			sql = "DELETE FROM " + tabname + " WHERE ";
			for (int i=0; vallist.size() > i;i++){
				if(i != 0){
					sql += " OR ";
				}
				sql += vallist.get(i);
			}
			sql += ";";
			exec_query(sql,fs);
			return fs.errmessage;
		}else{
			return fs.errmessage;
		}
	}

	public String update(HttpServletRequest req,String sqlfile){
		Enumeration<String> names = req.getParameterNames();
		String sql = new String();
		String tabname = new String();
		String alias = new String();
		String[] pkey = new String[1];

		//get TABLE
		if(req.getParameter("tableinfo") != null){
			String[] strAry = req.getParameter("tableinfo").split(" ");
			tabname = strAry[0];
			alias = strAry[1];
		}

		//get pkey
		if(req.getParameter("pkey") != null){
			String[] pkeyList = req.getParameterValues("pkey");
			pkey = new String[pkeyList.length];
			for(int i = 0; i < pkeyList.length;i++){
				String[] strAry = pkeyList[i].split("\\.");
				pkey[i] = strAry[1];
			}
		}

		//get ATT and VALUE
		String[] setValue = new String[256];
		String[] whereValue = new String[256];
		String attlist = new String();
		String vallist = new String();
		boolean isfirst  = true;
		boolean isfirst2  = true;
		while (names.hasMoreElements()){
			String name = (String)names.nextElement();
			if(name.contains(".")){
				Log.info("updateItem" + name);
				if(req.getParameterValues(name) != null && req.getParameterValues(name).length != 0){
					if(req.getParameter(name + ":pwd") != null && req.getParameter(name + ":pwd").equals("md5")){
						String[] str = req.getParameterValues(name);
						String[] attname = name.split("\\.");
						try{
							MessageDigest md = MessageDigest.getInstance("MD5");  

							for(int j = 0; j < str.length;j++){
								md.update(str[j].getBytes());   
								byte[] hash = md.digest();   	
								StringBuffer hexString = new StringBuffer();   
								for (int k = 0; k < hash.length; k++) {   
									if ((0xff & hash[k]) < 0x10) {   
										hexString.append("0" + Integer.toHexString((0xFF & hash[k])));   
									} else {   
										hexString.append(Integer.toHexString(0xFF & hash[k]));   
									}   
								}  
								str[j] = hexString.toString();
								attlist += name.substring(name.indexOf(".")+1,name.length())  + ",";
								vallist += "'"+ str+ "' " + ",";
								if(isfirst || setValue[j] == null){
									setValue[j] = attname[1] + " = '" + str[j] + "' ";
									isfirst = false;
								}else{
									setValue[j] += "," +  attname[1] + " = '" + str[j] + "' ";
								}
								for(int k=0;k < pkey.length;k++){
									if(attname[1].equals(pkey[k])){
										if(isfirst2 || whereValue[j] == null){
											whereValue[j] = attname[1] + " = '" + str[j] + "' ";
											isfirst2 = false;
										}else{
											whereValue[j] += " AND " +attname[1] + " = '" + str[j] + "' ";
										}
									}
								}
							}
						}catch(Exception mde){
							//error
						}
					}else if(name.endsWith(":const")){

					}else{
						String[] str = req.getParameterValues(name);
						String[] attname = name.split("\\.");
						for(int j = 0; j < str.length;j++){
							if(isfirst || setValue[j] == null){
								setValue[j] = attname[1] + " = '" + str[j] + "' ";
								Log.info(setValue);
								isfirst = false;
							}else{
								setValue[j] += "," +  attname[1] + " = '" + str[j] + "' ";
							}
							for(int k=0;k < pkey.length;k++){
								if(attname[1].equals(pkey[k])){
									if(isfirst2 || whereValue[j] == null){
										whereValue[j] = attname[1] + " = '" + str[j] + "' ";
										isfirst2 = true;
									}else{
										whereValue[j] += " AND " +  attname[1] + " = '" + str[j] + "' ";
									}
								}
							}
							attlist += attname[1] + ",";
							vallist += "'"+ str[j]+ "' " + ",";

						}
					}
				}
			}
		}

		//check att
		String[] tmpatt = attlist.split(",");
		String[] tmpval = vallist.split(",");
		String[][] tmpatt2 = new String[tmpatt.length][2];
		for(int i=0;i<tmpatt.length;i++){
			tmpatt2[i][0] = alias + "." +tmpatt[i];
			tmpatt2[i][1] = tmpval[i].replace("'", "").trim();
		}
		FormServlet fs = new FormServlet();		
		fs.checkAtt(tmpatt2, req);

		//CREATESQL
		//sql += setValue;
		for(int i = 0; i < setValue.length; i++){
			sql = "UPDATE "+ tabname + " SET ";
			if( setValue[i] != null ){
				sql += setValue[i];
			}else{
				break;
			}
			if( whereValue[i] != null ){
				sql += " WHERE "+whereValue[i];
			}
			sql += ";";
			if(sql.length() != 0 && !fs.errflg){
				Log.info("sql is " + sql);
				exec_query(sql,fs);
			}
			
		}
		

		//EXEC SQL
		if(sql.length() != 0 && !fs.errflg){
			fs.errMessage(sqlfile);
			return fs.errmessage;
		}else{
			fs.errMessage(sqlfile);
			return fs.errmessage;
		}
	}

	public void exec_query(String sql,FormServlet fs){

		//update database
		try {
			String driver = GlobalEnv.getDriver();
			String dbms = GlobalEnv.getdbms();
			String dbname = GlobalEnv.getdbname();
			String hostname = GlobalEnv.gethost();
			String user = GlobalEnv.getusername();

			// connect db
			Class.forName(driver); //driver
			Connection con =
				DriverManager.getConnection("jdbc:"+dbms+"://"+ hostname + "/" +dbname,
						user,
						driver);
			// create statement object
			Statement stmt = con.createStatement();


			// execute query
			stmt.executeQuery(sql);

			return;
		} catch (Exception e) {
			Log.err("sqlerr : "+ e);
			if(e.toString().contains("No results were returned by the query.")){
			}else if(e.toString().contains("duplicate key violates unique constraint")){
				fs.errmessage += FormEnv.getUnique();
				fs.errflg = true;
			}else{
				fs.errmessage += e;
				fs.errflg = true;
			}
			return;
		}
	}
}