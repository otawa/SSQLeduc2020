package supersql.invoke;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import supersql.cache.CacheData;
import supersql.cache.CacheDataFileIO;
import supersql.codegenerator.CodeGenerator;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.parser.Start_Parse;

public class InvokeSSQL {

	public InvokeSSQL(PrintStream print, InvokeEnv ienv, int cacheLevel) {

		String filename = ienv.getFilename();
		String dyna_where = ienv.getDyna_where();
		String dbname = ienv.getDbname();
		String SSQLhtmlfile = ienv.getSSQLhtmlfile();

		
		String[] args = { "-o", SSQLhtmlfile, "-f", filename, "-cond",
				dyna_where, "-u", InvokeEnv.dbUser, "-db", dbname, "-h",
				InvokeEnv.dbHost, "-basedir", InvokeEnv.wwwBaseURI,
				"-cacheLevel", Integer.toString(cacheLevel),
				"-invokeservletpath", ienv.getInvokeServletPath(),
				"-quiet"};

		Log.err("filename :"+filename);
		
		Log.err(args);

		this.invoke_frontend(args);
		

		if(GlobalEnv.getErrFlag() == 0)
		{
			try {
				BufferedReader myReader = new BufferedReader(new FileReader(
						SSQLhtmlfile));

				String line;
				while ((line = myReader.readLine()) != null) {
					print.println(line);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				Log.err("file not found:"+e);
				print.close();
			} catch (IOException e) {
				Log.err("ioerror:"+e);
				print.close();
			}
		}

		ienv.unlinkTempFile();

		return;

	}

	private void invoke_frontend(String[] args) {

		GlobalEnv.setGlobalEnv(args);
		
		Start_Parse parser = new Start_Parse("online");

		String sig = CacheData.getSSQLsig(parser);
		

		if ((CacheData.cacheLevel() & 1) != 0) {
			if (CacheDataFileIO.readGeneratedResult(sig)) {
				return;
			}
		}
		
		if(GlobalEnv.getErrFlag() == 0)
		{

			CodeGenerator codegenerator = parser.getcodegenerator();
	
			DataConstructor dc = new DataConstructor(parser);
			
			codegenerator.generateCode(parser, dc.getData());
		
		}

		if ((CacheData.cacheLevel() & 1) != 0) {
			CacheDataFileIO.writeGeneratedResult(sig);
		}

		return;

	}

}