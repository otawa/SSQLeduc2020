package supersql.cache;

import java.util.Enumeration;
import java.util.Hashtable;

public class CacheMethod {

	Hashtable<String, CacheMetaInfo> CacheTable = new Hashtable<String, CacheMetaInfo>();

	public boolean isCached(String sig) {
		return CacheTable.containsKey(sig);
	}

	public String getCache(String sig) {
		CacheMetaInfo metainfo = (CacheMetaInfo) CacheTable.get(sig);
		if (metainfo == null) {
			return null;
		}
		return metainfo.getCacheFile();
	}

	public void putCache(String sig, String resultfile) {
		if (CacheTable.containsKey(sig)) {
			((CacheMetaInfo) CacheTable.get(sig)).changeInfo(sig, resultfile);
		} else {
			CacheTable.put(sig, new CacheMetaInfo(sig, resultfile));
		}

	}

	public void removeCache(String sig) {

		if (CacheTable.containsKey(sig)) {
			((CacheMetaInfo) CacheTable.get(sig)).deleteFile();
			CacheTable.remove(sig);
		}

	}

	public void removeCacheAll() {

		String sig;
		Enumeration<String> keys = CacheTable.keys();
		while (keys.hasMoreElements()) {
			sig = (String) keys.nextElement();
			((CacheMetaInfo) CacheTable.get(sig)).deleteFile();
		}
		CacheTable.clear();

	}

	public String cacheString() {
		Enumeration<String> e = CacheTable.keys();
		String k;
		StringBuffer buf = new StringBuffer();
		while (e.hasMoreElements()) {
			k = (String) e.nextElement();
			buf.append(CacheTable.get(k));
			buf.append("--\n");
		}
		return buf.toString();

	}

	@Override
	protected void finalize() throws Throwable {
		removeCacheAll();
		super.finalize();
	}

}