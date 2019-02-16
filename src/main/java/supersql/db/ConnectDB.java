package supersql.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import supersql.common.GlobalEnv;
import supersql.common.Log;

public class ConnectDB extends Thread{
	
	private String url,user,driver,password;

	public Connection conn;
    
	public ConnectDB(String in_url, String in_user, String in_driver, String in_password){
		url = in_url;
		user = in_user;
		driver = in_driver;
		password = in_password;
	}
	
	@Override
	public void run(){
		
        Log.out("[Thread Open]");
        
        try {
  
        	Class.forName(driver);
            Log.out("********** Database's URL is **********");
            Log.out(url);
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.err
                    .println("Error[Thread]: Can't Connect DB : jdbc path = "
                            + url + " , user = " + user); 
            //tk////////////////////////////////////////////////////
            GlobalEnv.addErr("Error[Thread]: Can't Connect DB : jdbc path = "
                    + url + " , user = " + user); 
            return ;
//        	System.exit(-1);
            //tk////////////////////////////////////////////////////
        } catch (ClassNotFoundException e) {
            System.err
                    .println("Error[Thread]: Can't Load JDBC driver : driver = "
                            + driver);
            //tk////////////////////////////////////////////////////
            GlobalEnv.addErr("Error[Thread]: Can't Load JDBC driver : driver = "
                            + driver);
            return ;
//        	System.exit(-1);
            //tk////////////////////////////////////////////////////
        }

	}
	
	public Connection getConn(){
		return conn;
		
	}
}