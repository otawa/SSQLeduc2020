package supersql.cache;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;;

public class CacheData {

	public static CacheMethod SQLOut_Cache = new CacheMethod();

	public static CacheMethod NestData_Cache = new CacheMethod();

	public static CacheMethod Generated_Cache = new CacheMethod();

	@Override
	protected void finalize() throws Throwable {
		SQLOut_Cache.finalize();
		NestData_Cache.finalize();
		Generated_Cache.finalize();
		super.finalize();
	}

	public static String getSQLsig(Start_Parse parser, ExtList sep_sch) {

		StringBuffer sig = new StringBuffer("[SQL]");

		addEnvsig(sig);
		sig.append(parser.getSQLsig(sep_sch));

		Log.out("[SQL sig][" + sig + "]");

		return sig.toString();

	}

	public static String getTFEsig(Start_Parse parser, ExtList sep_sch) {

		StringBuffer sig = new StringBuffer("[TFE]");

		addEnvsig(sig);
		sig.append(parser.getTFEsig(sep_sch));

		Log.out("[TFE sig][" + sig + "]");

		return sig.toString();

	}

	public static String getSSQLsig(Start_Parse ssqlp) {

		StringBuffer sig = new StringBuffer("[SSQL]");

		addEnvsig(sig);
		sig.append(ssqlp.getSSQLsig());

		Log.out("[SSQL sig][" + sig + "]");

		return sig.toString();

	}

	private static void addEnvsig(StringBuffer sig) {
		sig.append(GlobalEnv.gethost().toLowerCase());
		sig.append("@@");
		sig.append(GlobalEnv.getdbname().toLowerCase());
		sig.append("@@");
		sig.append(GlobalEnv.getusername().toLowerCase());
		sig.append("@@");
	}

	/**
	 * cacheLevel
	 */
	public static int cacheLevel() {
		String str = GlobalEnv.getCacheLevel();
		if (str == null) {
			return 0;
		}
		if (str == "") {
			return 7;
		}
		//Log.out("str="+str+" int="+Integer.parseInt(str));
		return Integer.parseInt(str);
	}

	
	public static void resetCacheAll() {
		SQLOut_Cache.removeCacheAll();
		NestData_Cache.removeCacheAll();
		Generated_Cache.removeCacheAll();
	}

	public static String cacheToString() {
		
		StringBuffer buf = new StringBuffer("=== Cache Status ===\n");
		buf.append("[SQLOut Cache]\n");
		buf.append(SQLOut_Cache.cacheString());
		buf.append("[NestData Cache]\n");
		buf.append(NestData_Cache.cacheString());
		buf.append("[Generated Cache]\n");
		buf.append(Generated_Cache.cacheString());
		return buf.toString();

	}
}