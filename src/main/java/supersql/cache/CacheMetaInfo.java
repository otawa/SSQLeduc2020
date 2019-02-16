package supersql.cache;

import java.io.File;

public class CacheMetaInfo {

	String Signature;

	String CacheFile;

	long CreateDate = System.currentTimeMillis();

	long AccessDate = System.currentTimeMillis();

	public CacheMetaInfo(String sig, String filename) {

		Signature = sig;
		CacheFile = filename;

	}

	@Override
	protected void finalize() throws Throwable {
		deleteFile();
		super.finalize();
	}

	public void deleteFile() {
		File f = new File(CacheFile);
		f.delete();
	}

	/**
	 * @return accessDate
	 */
	public long getAccessDate() {
		return AccessDate;
	}

	/**
	 * @return cacheFile
	 */
	public String getCacheFile() {
		AccessDate = System.currentTimeMillis();
		return CacheFile;
	}

	/**
	 * @return createDate
	 */
	public long getCreateDate() {
		return CreateDate;
	}

	/**
	 * @param querystr
	 * @param resultfile
	 */
	public void changeInfo(String sig, String filename) {

		Signature = sig;
		CacheFile = filename;
		CreateDate = System.currentTimeMillis();
		AccessDate = System.currentTimeMillis();

	}

	@Override
	public String toString() {

		return "Signature  = " + this.Signature + "\n" + "CacheFile  = "
				+ this.CacheFile + "\n" + "CreateDate = " + this.CreateDate
				+ "\n" + "AccessDate = " + this.AccessDate + "\n";

	}

}