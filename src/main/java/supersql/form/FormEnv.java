package supersql.form;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import supersql.common.GlobalEnv;
import supersql.common.Log;

public class FormEnv {

	private static String[] errorMessage = {"LOGIN","NOTNULL","ENGLISH","NUMBER","NUMORENG","UNIQUE"};
	private static String[] errorMessageValues = {"NOT LOGIN","IS NULL","IS NOT ENGLISH","IS NOT NUMBER","IS NOT NUMBER OR ENGLISH","IS USED"};

	public static String getLogin(){
		return errorMessageValues[0];
	}
	public static String getNull(){
		return errorMessageValues[1];
	}
	public static String getEnglish(){
		return errorMessageValues[2];
	}
	public static String getNumber(){
		return errorMessageValues[3];
	}
	public static String getNumEng(){
		return errorMessageValues[4];
	}

	public static String getUnique(){
		return errorMessageValues[5];
	}
	
	//online getConfigValue
	public static void getFormEnv() {
		//Errorfile
		String errFile = GlobalEnv.getFileDirectory() + "error.ssql";
		String line = new String();
		BufferedReader dis;

		try{			
			if(errFile.startsWith("http:")){
				URL fileurl = new URL(errFile);
				URLConnection fileurlConnection = fileurl.openConnection();
				dis = new BufferedReader(new InputStreamReader(fileurlConnection.getInputStream()));
			}else{
				dis = new BufferedReader(new FileReader(errFile));
				line = null;
			}
			while (true) {
				line = dis.readLine();					
				if(line == null)
					break;
				for(int i=0;i< errorMessage.length;i++){
					if(line.toUpperCase().startsWith(errorMessage[i])){
						int f = line.indexOf("\"")+1;
						int e = line.indexOf("\"",f);
						errorMessageValues[i] = line.substring(f,e);
					}
				}
			}
			dis.close();
		} catch (MalformedURLException me) {
			Log.err("MalformedURLException: " + me);
		}catch (FileNotFoundException e) {
			Log.err("ErrorFileIsNotFound: " + e);
			return;
		} catch (IOException ioe) {
			Log.err("IOException: " + ioe);
		}
		return;
	}

}
