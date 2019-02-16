package supersql.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.PrimitiveType;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;

import supersql.FrontEnd;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Responsive.Responsive;

public class GlobalEnv {

	public static final char COMMENT_OUT_LETTER = '-';	//コメントアウト等による利用(ex: -- )

	/* [����] getProperty�᥽�åɤˤ�äơ������ƥ�ץ�ѥƥ�����(OS���ե�������ڤ�ʸ��ۡ��ࡢ����ʤ�)����� */
	/* �����ƥ�ץ�ѥƥ��Ͱ����μ���: System.getProperties().list(System.out); */
	public final static String USER_HOME = System.getProperty("user.home");				//�桼���Υۡ���ǥ��쥯�ȥ�
	public final static String OS = System.getProperty("os.name");						//OS��̾��("Mac OS X" ��)
	public final static String OS_LS = System.getProperty("line.separator");			//OS���Ȥβ��ԥ�����(Windows:"\r\n",Mac:"\r",UNIX:"\n" ��)
	public final static String OS_FS = System.getProperty("file.separator");			//OS���ȤΥե�������ڤ�ʸ��(Windows:"\" , Mac��Linux:"/" ��)
	public final static String OS_PS = System.getProperty("path.separator");			//OS���ȤΥѥ����ڤ�ʸ��(Windows";" , Mac��Linux":" ��)
	public final static String EXE_FILE_PATH = getCurrentPath();	//�¹ԥե�����Υѥ�(�¹�jar�ե�������ɤ��ˤ��뤫)����� (�����:���Хѥ����֤äƤ����礢��)
	public final static String USER_LANGUAGE = System.getProperty("user.language");		//�桼���θ���(���ܸ�:ja)  ���ܸ졦�Ѹ��ڤ��ؤ���ǽ���դ���Ȥ��˻��ѡ�
	public final static String USER_COUNTRY = System.getProperty("user.country");		//�桼���ι�̾(����:JP)   ���ܸ졦�Ѹ��ڤ��ؤ���ǽ���դ���Ȥ��˻��ѡ�

	public final static String MEDIA_XML = System.getProperty("user.dir")+OS_FS+"XML"+OS_FS+"ssql_medias.xml";

	public static final String DEFAULT_CHARACTER_CODE = "UTF-8";

	public static String query = "";

	private static Hashtable<String, String> envs;

	// 20140624_masato
	public static String errorText = "";
	public static String errorText_main = "";
	public static String queryInfo = "";
	public static String queryLog = "";
	public static String queryName = "";

	//����ե�����ξ���
	private static String layout = "";

	private static String host;

	private static String db;

	private static String user;

	private static String home;

	private static String outdir;

	private static String password;

	private static String encode;

	private static String apiServerUrl;

	//chie start
	private static String driver;

	private static String optimizer;

	private static String invokeServletPath; //used by online

	private static String fileDirectory; //used by online

	private static int tupleNum;

	//chie end

	//swf��
	public static String table_name;
	public static String where_line;

	//foreachなど
	public static boolean foreach_flag = false;

	//sessionなど
	public static boolean session_flag = false;

	//optimizerなど
	private static boolean optimizable = true;

	//tk embed��
	public static StringBuffer err = new StringBuffer();
	public static int online_flag = 0;
	public static int err_flag = 0;
	public static int EmbedbyQuery = 0;
	private static String embedtmp;
	private static ArrayList<String> EmbedFile = new ArrayList<String>(100);
	//static String driver = "org.postgresql.Driver";

	//for next/prev page
	public static int startnum = 0;
	public static int endnum = 0;


	public static void setGlobalEnv(String[] args) { // 引数のファイル名やオプション等を取得
		// err_flag = 0; // TODO 最初に初期化されているから必要ない？
		// err = new StringBuffer(); // TODO 上と同様？
		envs = new Hashtable<String, String>();
		String key = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				if (key != null) {
					envs.put(key, "");
				}
				key = args[i];
			} else {
				// modifed by masato 20151118 for ehtml
				if(key.equals("-query")){
					String q = "";
					for(int j = i; j < args.length; j++){
						if(!args[j].startsWith("-")){
							q += args[j] + " ";
						} else {
							envs.put(key, q);
							i = j;
							j = args.length-1;
						}
						if(j==args.length-1){
							envs.put(key, q);
							i = j;
							j = args.length-1;
						}
					}
				} else {
					//
					envs.put(key, args[i]);
				}
				key = null;
			}
		}
		//		for (int i = 0; i < args.length; i++) {
		//			if (args[i].startsWith("-")) {
		//				if (key != null) {
		//					envs.put(key, "");
		//				}
		//				key = args[i];
		//			} else {
		//				envs.put(key, args[i]);
		//				key = null;
		//			}
		//		}
		if (key != null) {
			envs.put(key, "");
		}

		//added by goto 20120707 start
		//optimize level　"-O0,-O1,-O2,-O3"
		//optimize level が設定されていればオプションを書き直す
		for (int i = 0; i <= 3; i++)
			if(envs.containsKey("-O"+i)){
				envs.remove("-O"+i);
				envs.put("-O", Integer.toString(i));
				break;
			}
		//added by goto 20120707 end

		setQuietLog();

		// added by masato 20150915 start
		setIncremental();
		// added by masato 20150915 end

		// added by masato 20151118 start
		setEhtml();
		// added by masato 20151118 end


		getConfig();

		Log.out("GlobalEnv is " + envs);
	}

	public static void setGlobalEnvEmbed(String[] args) {

		envs = new Hashtable<String, String>();
		String key = null;

		for (int i = 0; i < args.length; i++) {
			Log.out("args:"+args[i]);
			if (args[i].startsWith("-")) {
				if (key != null) {
					envs.put(key, "");
				}
				key = args[i];
			} else {
				envs.put(key, args[i]);
				key = null;
			}
		}
		if (key != null) {
			envs.put(key, "");
		}

		setQuietLog();
		Log.out("GlobalEnv is " + envs);
	}
	public static void getConfig() {
		host = null;
		db = null;
		user = USER_HOME;
		home = USER_HOME;
		outdir = null;
		driver = null;
		password = null;
		encode = null;
		optimizer = null;
		String config = getconfigfile(); // -cでconfigファイルを指定できる
		String[] c_value;

		if (config == null) {
			//changed by goto 20120624 start
			if(new File(home.concat("/.ssql")).exists())
				config = home.concat("/.ssql");
			else
				config = home.concat("/config.ssql");
			//changed by goto 20120624 end

			Log.out("offline config");
			c_value = getConfigValue(config);
		}
		else {
			Log.out("[GlobalEnv:getConfig] config file =" + config);
			c_value = getConfigValue(config);
		}


		if (c_value[0] == null && c_value[1] == null && c_value[2] == null
				&& c_value[3] == null) {
			Log.err("No config file("+config+")");
			return;
		}
		try {
			if (c_value[0] != null) {
				host = c_value[0];
			}
			if (c_value[1] != null) {
				db = c_value[1];
			}
			if (c_value[2] != null) {
				user = c_value[2];
			}
			if (c_value[3] != null) {
				outdir = c_value[3];
			}
			if (c_value[4] != null){
				embedtmp = c_value[4];
			}
			if (c_value[5] != null){
				driver = c_value[5];
			}
			if (c_value[6] != null){
				password = c_value[6];
			}
			if (c_value[7] != null){
				encode = c_value[7];
			}
			if (c_value[8] != null){
				optimizer = c_value[6];
			}
			if (c_value[9] != null){
				invokeServletPath = c_value[9];
			}
			if (c_value[10] != null){
				fileDirectory = c_value[10];
			}
			if (c_value[11] != null){
				setLayout(c_value[11]);
			}
			if (c_value[12] != null){
				setApiServerUrl(c_value[12]);
			}
			//added by goto 20161217  for responsive
			if (c_value[13] != null){
				Responsive.setOption(c_value[13]);
				Log.info("aaa"+c_value[13]);
			}
		} catch (Exception ex) {
		}

		if(embedtmp == null) //TODO
			embedtmp = "/tmp";
		Log.out("Config is {host=" + host + ", db=" + db + ", user=" + user + 
				", outdir=" + outdir + ", driver=" + driver + ", password=" + password + 
				", encode=" + encode + ", optimizer=" + optimizer +", embedtmp="+ embedtmp + 
				", "+Responsive.OPTION_NAME+"="+Responsive.getOption()+" }");
		return;
	}

	public static String seek(String key) {
		return envs.get(key);
	}

	public static String getconfigfile() {
		return seek("-c");
	}

	/*
	 * �ƥ��ȥǡ����Υե���?�λ�? ���ߤϻ��Ѥ��Ƥ��ʤ�
	 */
	public static String gettestdatafile() {
		return seek("-t");
	}

	/**
	 * SuperSQLの基本的読み込み方法
	 */
	public static String getfilename() {
		String filename = seek("-f");
		if(filename == null){// for embed ssql
			return "";
		}else{
			if(filename.indexOf(".ssql") > 0 || filename.indexOf(".sql") > 0){
				return filename;
			}else{
				System.err.println("file extension is must be '.ssql' or '.sql'");
				System.exit(1);
			}
			return seek("-f");
		}
	}

	//added by goto 20141130
	public static String getfileparent() {
		try {
			return new File(seek("-f")).getParent().toString();
		} catch (Exception e) {
			return ".";
		}
	}

	public static String getoutdirectory() {
		String ret = seek("-d");
		if (ret == null) {
			if (outdir != null) {
				ret = outdir;
			}
		}
		if (ret != null) {
			if (ret.startsWith("~/")) {
				ret = ret.substring(1);
				ret = home + ret;
			} else if (ret.startsWith("~")) {
				ret = ret.substring(1);
				String nyoro = home.substring(0,home.indexOf(user));
				ret = nyoro + ret;
			}
		}
		return ret;
	}

	/**
	 * ���ϥե�����̾
	 */
	public static String getoutfilename() {
		return seek("-o");
	}


	/**
	 * �ǡ����١�������³����桼��̾
	 */
	public static String getusername() {
		String ret = seek("-u");
		if (ret == null) {
			ret = user;
		}
		return ret;
	}

	/*
	 * ��³����ǡ����١��� ��ά���줿���桼��̾��Ʊ���Ȥ���
	 */
	public static String getdbname() {
		String ret = seek("-db");
		if (ret == null) {
			if (db != null) {
				ret = db;
			} else {
				ret = getusername();
			}
		}
		return ret;
	}

	/*
	 * ��³����DB�ۥ���̾
	 */
	public static String gethost() {
		String ret = seek("-h");
		if (ret == null) {
			if (host != null) {
				ret = host;
			}
			//			else {
			//				ret = "postgres.db.ics.keio.ac.jp";
			//			}
		}
		return ret;
	}

	public static String getdbms() {
		String ret = seek("-driver");
		if (ret == null) {
			if (driver != null) {
				ret = driver;
			} else {
				ret = "postgresql";
			}
		}
		return ret;
	}

	//��³����DB��url
	public static String geturl() {
		//added by goto 20141204
		if(getDriverName() != null)	driver = getDriverName();
		if(gethost() != null)		host = gethost();
		if(getdbname() != null)		db = getdbname();

		String ret = "jdbc:postgresql://" + host + "/" + db;;
		if (driver != null) {
			if(driver.equals("postgres")){
				ret = "jdbc:postgresql://" + host + "/" + db;
			}else if (driver.equals("mysql")) {
				ret = "jdbc:mysql://" + host + "/" + db + "?useUnicode=true&characterEncoding=SJIS";
			}else if (driver.equals("db2")) {
				ret = "jdbc:db2:" + db;
				//added by goto 20120518 start
			}else if (driver.equals("sqlite") || driver.equals("sqlite3")) {
				//				ret = "jdbc:sqlite:" + db;
				//added by goto 20141130 start
				ret = "jdbc:sqlite:";
				if (!new File(db).isAbsolute()) {
					if(GlobalEnv.getoutdirectory() != null)
						ret += GlobalEnv.getoutdirectory();
					else
						ret += GlobalEnv.getfileparent();
					ret += GlobalEnv.OS_FS;
				}
				ret += db;
				//added by goto 20141130 end
			}
			//added by goto 20120518 end
		} else {
			ret = "jdbc:postgresql://" + host + "/" + db;
		}

		return ret;
	}

	//��³����ɥ饤�ФΥѥ����
	public static String getpassword() {
		String ret = seek("-p");
		if (ret == null) {
			if (password != null) {
				ret = password;
			} else {
				ret = "";
			}
		}
		return ret;
	}

	//���ϥե������encode
	public static String getencode() {
		String ret;
		if (encode != null) {
			ret = encode;
		} else {
			ret = "";
		}
		return ret;
	}


	/*
	 * -debugでLog.outの出力、-quietでLog.infoも出力しない
	 */
	public static void setQuietLog() {
		if (seek("-debug") == null) {
			Log.setLog(0);
		} else {
			Log.setLog(1);
		}
		if (seek("-quiet") == null) {
			Log.setinfoLog(1);
		} else {
			Log.setinfoLog(0);
			Log.setLog(0);
		}
	}

	// added by masato 20150915 for incremental update data
	private static void setIncremental(){
		if(seek("-incremental") != null){
			Incremental.setIncremental();
		} else {
			return;
		}
	}

	// added by masato 20151118 for embedding
	private static void setEhtml(){
		if(seek("-ehtml") != null){
			Ehtml.setEhtml();
		} else {
			return;
		}
	}


	/*
	 * -queryによるクエリの入力(-f以外のパターン)
	 */
	public static String getQuery() {
		return seek("-query");
	}

	public static String cssout() {
		return seek("-cssout");
	}
	/////////////

	//added by goto 20161217  for responsive
	public static String getResponsiveURL() {
		String ret = seek("-"+Responsive.OPTION_NAME);
		if (ret == null) {
			if (Responsive.getOption() != null) {
				ret = Responsive.getOption();
			} else {
				ret = "";
			}
		}
		return ret;
	}


	// offline getConfigValue
	protected static String[] getConfigValue(String config) {

		BufferedReader filein = null;
		String line = new String();

		//(invokeServletPath and fileDirectory are not used in offline)
		String con[] = { "host", "db", "user", "outdir", "embedtmp", "driver", "password", "encode", "optimizer", 
				"invokeServletPath","fileDirectory", "layout", "api_server_url", Responsive.OPTION_NAME };
		String c_value[] = new String[con.length];

		try {
			filein = new BufferedReader(new FileReader(config));
			while (true) {
				try {
					line = filein.readLine();
				} catch (IOException e1) {
				}
				if (line == null)
					break;
				line = line.trim();
				for (int i = 0; i < con.length; i++) {
					if (line.startsWith(con[i])) {
						c_value[i] = line.substring(line.indexOf("=") + 1)
								.trim();
					}
				}
			}
			filein.close();
		} catch (FileNotFoundException e1) {
			Log.err("Configuration file " + config + " not found.");
		} catch (IOException e) {
			Log.err("IOEXception error from supersql.common.GlobalEnv.getConfigValue.");
		}

		return c_value;
	}

	//online getConfigValue
	@SuppressWarnings("resource")
	protected static String[] getConfigValue2(String config) {

		String line = new String();
		String con[] = { "host", "db", "user", "outdir", "embedtmp", "driver", "password", "encode", "optimizer",
				"invokeServletPath","fileDirectory" };
		String c_value[] = new String[con.length];
		BufferedReader dis;

		try{
			if(config.startsWith("http:"))
			{
				URL fileurl = new URL(config);
				URLConnection fileurlConnection = fileurl.openConnection();
				dis = new BufferedReader(new InputStreamReader(fileurlConnection.getInputStream()));
			}
			else
			{
				dis = new BufferedReader(new FileReader(config));
				line = null;
			}
			while (true) {
				try {
					line = dis.readLine();

					if(line == null)
						break;
					for (int i = 0; i < con.length; i++) {
						if (line.startsWith(con[i])) {
							c_value[i] = line.substring(line.indexOf("=") + 1)
									.trim();
						}
					}
				} catch (MalformedURLException me) {
					Log.err("MalformedURLException: " + me);
				} catch (IOException ioe) {
					Log.err("IOException: " + ioe);
				}
			}
		} catch (MalformedURLException me) {
			Log.err("MalformedURLException: " + me);
		} catch (IOException ioe) {
			Log.err("IOException: " + ioe);
		}

		return c_value;
	}
	/**
	 * ����ʸ��������ʸ��WHERE��˲ä���
	 */
	public static String getCondition() {
		return seek("-cond");
	}

	/**
	 * @return
	 */
	public static boolean getForeachFlag() {
		return foreach_flag;
	}

	/**
	 * Imagefile��ǡ����Хѥ��򵭽Ҥ����Ȥ��� �ղä���ǥ��쥯�ȥ�
	 */
	public static String getBaseDir() {
		return seek("-basedir");
	}

	/**
	 * cacheLevel
	 */
	public static String getCacheLevel() {
		return seek("-cacheLevel");
	}

	/**
	 * Invoke�Υ����֥�åȤ�path
	 */
	public static String getInvokeServletPath() {
		String is = seek("-invokeservletpath");

		if (is == null) {
			is = invokeServletPath;
			if(is == null){
				is = "http://www.db.ics.keio.ac.jp/servlet/supersql.invoke.InvokeServlet2";
				//is = "http://localhost:8080/invoke/servlet/supersql.invoke.InvokeServlet2";
			}
		}
		return is;
	}

	//tk///////////////////////////////////////////////////
	public static void addErr(String errMsg)
	{
		err.append(errMsg);
		err_flag = 1;
	}

	public static String getErr()
	{
		return err.toString();
	}
	public static void setOnlineFlag()
	{
		online_flag = 1;
	}
	public static int getOnlineFlag()
	{
		return online_flag;
	}

	public static int getErrFlag()
	{
		return err_flag;
	}

	public static int getEmbedOption() {
		if (seek("-eq") == null)
		{
			//without option
			return 0;
		}else {
			//with option
			return 1;
		}
	}

	public static int isNewEmbed() {

		if(seek("-newEmbed") == null)
		{
			//without option
			return 0;
		}else {
			//with option
			return 1;
		}
	}
	public static boolean isOpt() {
		if(optimizer != null && optimizer.equals("on")){
			//with option
			return true;
		}else{
			//modified by ria 20110912 start
			//if(seek("-optimizer") == null && seek("-O") == null)
			if(seek("-optimizer") == null)
				//modified by ria 20110912 end
			{
				//without option
				return false;
			}else {
				//with option
				return true;
			}
		}
	}
	public static boolean isMultiThread(){

		if(seek("-mt") == null)
			return false;
		else
			return true;
	}

	public static boolean isAjax(){

		if(seek("-ajax") == null)
			return false;
		else
			return true;
	}

	public static boolean isServlet(){

		if(seek("-servlet") == null && seek("-invokeservletpath") == null && invokeServletPath == null){
			return false;
		}else{
			return true;
		}
	}

	public static String getJsDirectory(){
		return seek("-jsdirectory");
	}

	public static String getAjaxTarget(){
		return seek("-ajaxtarget");
	}

	public static String getEmbedTmp(){
		return embedtmp;
	}

	public static ArrayList<String> getEmbedFile(){
		return EmbedFile;
	}

	public static void addEmbedFile(String file){
		EmbedFile.add(file);
	}

	//chie
	public static String getFileDirectory(){
		if(fileDirectory == null){
			return ".";
		}
		return fileDirectory;
	}

	public static String getDriverName(){
		return seek("-driver");
	}

	public static String getDriver(){
		String ret = seek("-driver");

		if (ret == null) {
			if (driver != null) {
				ret = driver;
			} else {
				ret = "postgresql";
			}
		}

		if(ret.equals("postgresql") || ret.equals("postgres")){
			ret = "org.postgresql.Driver";
		} else if(ret.equals("mysql")){
			ret = "com.mysql.jdbc.Driver";
		} else if(ret.equals("db2")){
			ret = "com.ibm.db2.jcc.DB2Driver";
			//added by goto 20120518 start
		} else if (ret.equals("sqlite") || ret.equals("sqlite3")) {
			ret = "org.sqlite.JDBC";
		}
		//added by goto 20120518 end
		return ret;
	}

	public static Hashtable<String, String> getEnv(){
		return envs;
	}
	public static void setEnv(Hashtable<String, String> env){
		envs = env;
	}

	//set and get number of taples
	public static void setTuplesNum(int num){
		tupleNum = num;
	}

	public static int getTuplesNum(){
		return tupleNum;
	}

	//added by ria 20110628 start
	public static int getOptLevel()
	{
		String s = seek( "-O" );
		if ( s == null )
		{
			return 2;
		}
		else
		{
			return Integer.parseInt( s );
		}
	}

	public static void setOptimizable( boolean b )
	{
		optimizable = b;
	}

	public static boolean isOptimizable()
	{
		return optimizable;
	}

	public static String getLayout() {
		return layout;
	}

	public static void setLayout(String layout) {
		GlobalEnv.layout = layout;
	}

	public static String getApiServerUrl() {
		return apiServerUrl;
	}

	public static void setApiServerUrl(String apiServerUrl) {
		GlobalEnv.apiServerUrl = apiServerUrl;
	}
	public static String getframeworklist() {
		return seek("-fwlist");
	}

	//added by goto 20141130
	//halken TFEmatcher
	public static boolean isTFEmatcher() {
		if(seek("-tfematcher") != null)
			return true;
		return false;
	}

	//added by goto 20141201
	public static boolean isLogger() {
		//Default: off
		if(seek("-logger") != null && seek("-logger").equalsIgnoreCase("on"))
			return true;
		return false;
	}

	//added by goto 20150112
	public static boolean isCheckquery() {
		if(seek("-checkquery") != null || seek("-getparseresult") != null)
			return true;
		return false;
	}


	//added by goto 20141201
	private static String getCurrentPath(){
		String cp = System.getProperty("java.class.path");
		if(cp.contains(OS_PS)){
			String cps[] = cp.split(OS_PS);
			for(int i=0; i<cps.length; i++){
				if(cps[i].contains(OS_FS)){
					cp = cps[i].trim();
					break;
				}
			}
		}
		if(cp.endsWith(".jar")){
			cp = new File(cp).getParent();
			if(cp.endsWith("libs"))
				cp = new File(cp).getParent();
		}
		return cp;
	}

	//added by goto 20141209
	//return the output directory's path
	public static String getOutputDirPath() {
		String outdir = GlobalEnv.getoutdirectory();
		if (outdir == null)
			outdir = GlobalEnv.getfileparent();
		if (outdir == null )
			outdir = GlobalEnv.getfileparent();
		return outdir;
	}

	// added by masato 20150525
	public static String getLinkValue(){
		return seek("-ehtmlarg");
	}

	// added by masato 20151128 for execute multiple query in ehtml or incremental
	public static Integer getQueryNum(){
		return Integer.parseInt(seek("-querynum"));
	}

	// added by yusuke 20161109 for autocorrect
	public static boolean isSsedit_autocorrect() {
		if(seek("-ssedit_autocorrect") != null)
			return true;
		return false;
	}

	//isNumber
	public static boolean isNumber(String val) {
		String regex = "^\\-?[0-9]*\\.?[0-9]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(val);
		return m.find();
	}


	// tbt embed for c_tab
	private static boolean c_tab_flag = false;

	public static void setCtabflag(){
		c_tab_flag = true;
	}
	public static void unsetCtabflag(){
		c_tab_flag = false;
	}

	public static boolean getCtabflag(){
		return c_tab_flag;
	}
	// tbt end

	// added by yusuke 20161206 for autocorrect
	public static String getworkingDir() {

		String workingDir = new File(GlobalEnv.EXE_FILE_PATH).getAbsolutePath(); // 実行jarファイルの絶対パスを取得

		if (workingDir.contains(":")) {
			workingDir = workingDir.substring(0, workingDir.indexOf(":"));
		}

		return workingDir;
	}


	//added by goto 170612  for --version
	private static boolean isVersion() {
		if(seek("--version") != null || seek("-version") != null || seek("-v") != null)
			return true;
		return false;
	}
	static long lastMod = Long.MIN_VALUE;
	static File choice = null;
	public static boolean versionProcess() {
		if(!isVersion()) return false;

		Log.info("SuperSQL version \""+FrontEnd.VERSION+"\"");

		String f = new FrontEnd().getClass().getResource("FrontEnd.class").toString();
		if(f.contains(":"))	f = f.substring(f.lastIndexOf(":")+1);
		if(f.contains("!"))
			f = f.substring(0, f.indexOf("!"));
		else{
			readFolder(new File(new File(f).getParent()));
			f = choice.toString();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Log.info("(build "+sdf.format(new File(f).lastModified())+")");
		return true;
	}
	private static void readFolder(File dir) {
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (File file : files) {
			if (!file.exists())
				continue;
			else if (file.isDirectory())
				readFolder(file);
			else if (file.isFile()){
				if (file.lastModified() > lastMod) {
					choice = file;
					lastMod = file.lastModified();
				}
			}
		}
	}
	//tbt add for centering
	private static boolean centeringflag = false;
	private static String pos = new String();
	public static void setCenteringflag() {
		// TODO 自動生成されたメソッド・スタブ
		centeringflag = true;
	}
	public static boolean getCenteringflag(){
		return centeringflag;
	}

	private static boolean detectcenteringflag = true;
	public static void setDetectcenteringflag() {
		detectcenteringflag = false;
	}
	public static boolean getDetectcenteringflag() {
		return detectcenteringflag;
	}

	public static void setPos(String str){
		pos = str;
	}
	public static String getPos(){
		return pos;
	}

	//tbt end

}
