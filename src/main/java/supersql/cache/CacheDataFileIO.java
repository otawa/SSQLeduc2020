package supersql.cache;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class CacheDataFileIO {

	static final String CacheDir = "/tmp/Cache";

	public static boolean writeSQLResult(String sig, ExtList data_info) {

		String filename = CacheDir + "/SQL/" + System.currentTimeMillis()
				+ ".dat";

		Log.out("[write cache][SQL] sig=[" + sig + "] " + " filename="
				+ filename);

		try {
			writeData(filename, data_info);
		} catch (Exception e) {
			Log.out("[Err] Can't Write SQL Result");
			return false;
		}

		CacheData.SQLOut_Cache.putCache(sig, filename);
		Log.out("Cache write done ...");

		return true;

	}

	public static boolean writeNestedResult(String sig, ExtList data_info) {

		String filename = CacheDir + "/Nest/" + System.currentTimeMillis()
				+ ".dat";

		Log.out("[write cache][Nest] sig=[" + sig + "] " + " filename="
				+ filename);

		try {
			writeData(filename, data_info);
		} catch (Exception e) {
			Log.out("[Err] Can't Write Nested Result");
			return false;
		}

		CacheData.NestData_Cache.putCache(sig, filename);
		Log.out("Cache write done ...");

		return true;

	}

	private static void writeData(String filename, ExtList data_info)
			throws Exception {

		try {
			FileOutputStream ostream = new FileOutputStream(filename);
			ObjectOutputStream p = new ObjectOutputStream(ostream);
			p.writeObject(data_info);

			p.flush();
			ostream.close();
		} catch (Exception e) {
			Log.out(e);
			throw (e);
		}

	}

	public static boolean readSQLResult(String sig, ExtList data_info) {

		Log.out("[read cache][SQL] sig=[" + sig + "]");
		String filename = CacheData.SQLOut_Cache.getCache(sig);

		if (filename == null) {
			Log.out("cache not found.");
			// not Found
			return false;
		}
		Log.out("CacheFile=" + filename);

		try {
			readData(filename, data_info);
		} catch (Exception e) {
			Log.out("[Err] Can't Read SQL Result");
			return false;
		}

		Log.out("Cache read done ...");
		return true;

	}

	public static boolean readNestedResult(String sig, ExtList data_info) {

		Log.out("[read cache][Nest] sig=[" + sig + "]");
		String filename = CacheData.NestData_Cache.getCache(sig);

		if (filename == null) {
			Log.out("cache not found.");
			// not Found
			return false;
		}
		Log.out("CacheFile=" + filename);

		try {
			readData(filename, data_info);
		} catch (Exception e) {
			Log.out("[Err] Can't Read Nested Result");
			return false;
		}

		Log.out("Cache read done ...");
		return true;

	}

	private static void readData(String filename, ExtList data_info)
			throws Exception {

		try {
			FileInputStream ostream = new FileInputStream(filename);
			ObjectInputStream p = new ObjectInputStream(ostream);
			data_info.clear();
			data_info.addAll((ExtList) p.readObject());
			ostream.close();
		} catch (Exception e) {
			Log.out(e);
			throw (e);
		}
		return;

	}

	public static boolean writeGeneratedResult(String sig) {

		String datafile = GlobalEnv.getoutfilename();
		String filename = CacheDir + "/Generated/" + System.currentTimeMillis()
				+ ".dat";

		Log.out("[write cache][Generated] sig=[" + sig + "] " + " filename="
				+ filename);

		try {
			copyFile(datafile, filename);
		} catch (Exception e) {
			Log.out("[Err] Can't Write SQL Result");
			return false;
		}

		CacheData.Generated_Cache.putCache(sig, filename);
		Log.out("Cache write done ...");

		return true;

	}

	public static boolean readGeneratedResult(String sig) {

		String datafile = GlobalEnv.getoutfilename();

		Log.out("[read cache][Generated] sig=[" + sig + "]");
		String filename = CacheData.Generated_Cache.getCache(sig);

		if (filename == null) {
			Log.out("cache not found.");
			// not Found
			return false;
		}
		Log.out("CacheFile=" + filename);

		try {
			copyFile(filename, datafile);
		} catch (Exception e) {
			Log.out("[Err] Can't Read Nested Result");
			return false;
		}

		Log.out("Cache read done ...");
		return true;

	}

	private static void copyFile(String fromfile, String tofile)
			throws Exception {

		Log.out("[Copy File] from=" + fromfile + " to=" + tofile);
		// cp cachefile outfile
		try {
			BufferedReader is = new BufferedReader(new FileReader(fromfile));
			PrintWriter ps = new PrintWriter(new FileWriter(tofile));
			String s;
			while ((s = is.readLine()) != null) {
				ps.println(s);
			}
			is.close();
			ps.close();
		} catch (Exception e) {
			// CacheError
			Log.out(e);
			throw (e);
		}
		return;
	}

}