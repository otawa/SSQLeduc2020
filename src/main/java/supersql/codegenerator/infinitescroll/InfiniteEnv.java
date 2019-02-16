package supersql.codegenerator.infinitescroll;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LocalEnv;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5C3;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5G3;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class InfiniteEnv extends LocalEnv {
	// added by taji start
	public static boolean defaultCssFlag = true;
	public static int itemNumPerPage = 0;
	public static int itemCount = 0;
	public static int g1RetNum = 0;
	public static int g2RetNum = 0;
	public static int g1PaginationRowNum = 0;
	public static int g1PaginationColumnNum = 0;
	public static int g2PaginationRowNum = 0;
	public static int g2PaginationColumnNum = 0;
	public static StringBuffer xmlCode;
	public int countFile;
	public StringBuffer cssFile = new StringBuffer();
	public String dragDivId = new String();
	public int embedCount = 0;
	public boolean embedFlag = false;
	public String fileName;
	public boolean foreachFlag;
	public ArrayList<String> outTypeList = new ArrayList<>();
	public int cNum = 0;
	public int xmlDepth = 0;
	//added by taji end

	String data;

	String pre_operator;

	Vector written_classid;

	Connector connector;

	String title = "";		//added by goto 20130411  "title"
	String bg = "";			//added by goto 20130311  "background"
	//    int maxWidth = 350;		//added by goto 20130512  "max-width"	Default:350
	int portraitWidth = -1;		//added by goto 20130512  "max-width"	Default:-1
	int landscapeWidth = -1;	//added by goto 20130512  "max-width"	Default:-1
	int pcWidth = -1;			//added by goto 20130512  "max-width"	Default:-1

	public static String 	//added by goto 20130515  "search"
	PHP = "<?php\n" +	//初期定義
			//"//XSS対策\n" +
			"function checkHTMLsc($str){\n" +
			"	return htmlspecialchars($str, ENT_QUOTES, 'UTF-8');\n" +
			"}\n" +
			"?>\n";

	Vector<String> not_written_classid= new Vector();

	int total_element = 0;

	private int glevel = 0;

	public String filename = "";

	public String outfile;

	public String linkoutfile;

	public String nextbackfile = new String();

	String outdir;

	public int countfile;

	PrintWriter writer;

	public StringBuffer code;

	public static StringBuffer css;

	static int ID_counter=0;	//add oka

	static int ID_old=0;		//add oka

	public String charset=null;					//added by goto 20120715
	static boolean charsetFlg=false;		//added by goto 20120715

	String copyright="";					//added by goto 20130518
	String fff = "";						//20130518  "show query"

	Boolean flickBarFlg = false;				//20130521  "flickbar"

	StringBuffer meta = new StringBuffer();
	StringBuffer div = new StringBuffer();
	StringBuffer titleclass = new StringBuffer();
	public static String jscss = new String();		//js and css file names that using in the Mobile_HTML5
	StringBuffer cssfile = new StringBuffer();
	StringBuffer jsFile = new StringBuffer();		//added by goto 20130703
	StringBuffer cssjsFile = new StringBuffer();	//added by goto 20130703
	String tableborder=new String("1");
	boolean embedflag = false;
	int embedcount = 0;

	int haveClass = 0;

	//for ajax
	String ajaxquery = new String();
	String ajaxcond = new String();
	String ajaxatt = new String();
	String ajaxtarget = new String();
	int inEffect = 0;
	int outEffect = 0;
	boolean has_dispdiv = false;

	//for drag
	StringBuffer script = new StringBuffer();
	int scriptnum = 0;
	boolean draggable = false;
	String dragdivid = new String();

	//for panel
	boolean isPanel = false;

	public StringBuffer header;

	public StringBuffer footer;

	//20130503  Panel
	StringBuffer code1;
	StringBuffer code2;
	StringBuffer panel = new StringBuffer();
	int panelCount = 1;

	boolean foreach_flag;

	boolean sinvoke_flag = false;

	int link_flag;

	String linkurl;

	String plink_glink_onclick = "";		//added by goto 20161109 for plink/glink

	public static int uiGridCount = 0;		//20130314  C1 ui-Grid用
	public static int uiGridCount2 = 0;	//20130314  G1 ui-Grid用

	public static int tabCount = 1;			//20130330  tab用
	public static int maxTab = 15;				//20130330  tab用


	static boolean noAd = false;		//20131106

	// ��?�Ѥ�CSS CLASS����?��?
	private String KeisenMode = "";

	public InfiniteEnv() {
	}

	public String getEncode(){
		if(getOs().contains("Windows")){
			return "Shift_JIS";
		}else{
			return "EUC_JP";
		}
	}

	public String getOs(){
		String osname = System.getProperty("os.name");
		return osname;
	}

	public static String getClassID(ITFE tfe) {
		String result;
		if (tfe instanceof Mobile_HTML5C3) {
			result = getClassID(((ITFE) ((Mobile_HTML5C3) tfe).tfes.get(0)));
			return result;
		}
		if (tfe instanceof Mobile_HTML5G3) {
			result = getClassID(((ITFE) ((Mobile_HTML5G3) tfe).tfe));
			return result;
		}
		result =  "TFE" + tfe.getId();
		return result;
	}

	/***start oka***/
	public static String getDataID(ITFE tfe) {
		String ClassID;
		int DataID = 0;
		String return_value;

		if (tfe instanceof Mobile_HTML5C3) {
			return getClassID(((ITFE) ((Mobile_HTML5C3) tfe).tfes.get(0)));
		}
		if (tfe instanceof Mobile_HTML5G3) {
			return getClassID(((ITFE) ((Mobile_HTML5G3) tfe).tfe));
		}
		ClassID = String.valueOf(tfe.getId());
		DataID = Integer.valueOf((ClassID.substring(ClassID.length()-3,ClassID.length()))).intValue();

		Log.out("ClassID="+ClassID);
		Log.out("DataID="+DataID);
		Log.out("ID_counter="+ID_counter);

		if(DataID < ID_old){
			ID_counter = DataID;
		}
		else{
			if(DataID != ID_counter && DataID > ID_counter){
				DataID = ID_counter;
			}
		}
		ID_counter++;
		ID_old = DataID;
		return_value = String.valueOf(DataID);
		return return_value;
	}


	/********  form method  ************/
	/********** 2009 chie **************/

	//
	public static void initAllFormFlg(){
		setFormItemFlg(false,null);
		setSelectFlg(false);
		setSelectRepeat(false);
		setFormValueString(null);
		setFormPartsName(null);
		setSelected("");
		setIDU("");
		form_parts_number = 1;
		exchange_form_name = new String();
		form_detail = new String[256];
		form_number = 1;
		nameId = "";
		search = false;
		searchid = 0;
		cond_name="";
		cond ="";
	}


	static boolean isFormItem;
	static String formItemName;
	//form tag is written : true
	public static void setFormItemFlg(boolean b,String s){
		isFormItem = b;
		formItemName = s;
		return;
	}

	public static boolean getFormItemFlg(){
		return isFormItem;
	}

	public static String getFormItemName(){
		if(formItemName == null){
			return "0";
		}
		return formItemName;
	}

	static boolean select_flg;
	//function select flg -> in func_select true

	//set and get select_flg
	public static void setSelectFlg(boolean b){
		select_flg = b;
	}

	public static boolean getSelectFlg(){
		return select_flg;
	}


	static String formValueString;
	public static void setFormValueString(String s){
		formValueString = s;
	}
	public static String getFormValueString(){
		return formValueString;
	}



	static boolean select_repeat;
	//select_repeat flag
	//not write "<tr><td>" between "<option>"s
	//set and get select_repeat
	public static void setSelectRepeat(boolean b){
		select_repeat = b;
	}
	public static boolean getSelectRepeat(){
		return select_repeat;
	}

	//global form item number : t1,t2,t3...
	static int form_parts_number = 1;
	static String form_parts_name = null;
	public static String getFormPartsName(){
		if(form_parts_name == null){
			return "t"+form_parts_number;
		}else{
			return form_parts_name;
		}
	}
	public static void incrementFormPartsNumber(){
		form_parts_number++;
	}


	public static void setFormPartsName(String s){
		form_parts_name = s;
	}

	private static String exchange_form_name = new String();
	public static void exFormName(){
		String s = "t" + form_parts_number + ":" + form_parts_name +":";
		if(exchange_form_name == null || exchange_form_name.isEmpty()){
			exchange_form_name = ":"+s;
		}else{
			if(!exchange_form_name.contains(s))
				exchange_form_name += s;
		}
	}
	public static String exFormNameCreate(){
		String ret = new String();
		if(exchange_form_name != null){
			ret = "<input type=\"hidden\" name=\"exchangeName\" value=\""+exchange_form_name+"\" />";
			setFormDetail(ret);
			return ret;
		}else{
			return null;
		}
	}


	//add js or css file names that using in the Mobile_HTML5 to the header
	public static void addJsCss(String filename){
		if(!jscss.contains(filename)){
			jscss += "<script src=\""+filename+"\"></script>\n";
		}
	}


	//global form number : 1,2,3...
	static int form_number = 1;
	public static void incrementFormNumber(){
		form_number++;
	}

	public static int getFormNumber(){
		//return formNumber 1,2,3...
		return form_number;
	}
	public static String getFormName(){
		//return formNumber f1,f2,f3...
		return "f"+form_number;
	}

	static String[] form_detail = new String[256];
	public static void setFormDetail(String s){
		if(form_detail[form_number] == null)
			form_detail[form_number] = s;
		else
			form_detail[form_number] += s;
	}
	public static String getFormDetail(int i){
		return form_detail[i];
	}

	static String IDUst = new String();
	public static void setIDU(String s){
		IDUst = s;
	}

	public static String getIDU(){
		return IDUst;
	}

	static String selected = "";

	public static void setSelected(String s){
		selected = s;
	}
	public static String getSelected(){
		return selected;
	}

	static String nameId = "";
	public static String getNameid(){
		if(nameId != null){
			return nameId;
		}else{
			return "";
		}
	}

	static String checked = "";

	public static void setChecked(String s){
		System.out.println("checked:"+s);
		checked = s;
	}
	public static String getChecked(){
		return checked;
	}

	static boolean search = false;
	static int searchid = 0;
	static String cond_name = "";
	static String cond = "";

	public static void setSearch(boolean b){
		search = b;
		searchid = 0;
	}
	public static boolean getSearch(){
		return search;
	}


	//goto 20131123
	public String getFileName(){
		//absolute path filename (/home/---/XXX.html)
		if(!filename.isEmpty())	return filename;
		else 					return GlobalEnv.getfilename();
	}
	public String getFileName1(){
		//absolute path filename (/home/---/XXX.html)
		return getFileName();
	}
	public String getFileName2(){
		//absolute path filename (/home/---/XXX)
		return getFileName().substring(0, getFileName().lastIndexOf("."));
	}
	public String getFileName3(){
		//file name (XXX.html)
		return new File(getFileName1()).getName();
	}
	public String getFileName4(){
		//file name (XXX)
		return new File(getFileName2()).getName();
	}
	public String getFileParent(){
		//file path (/home/---/)
		return new File(filename).getParent();
	}

	public static void initXML(){
		xmlCode = new StringBuffer();
		xmlCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	public int getGlevel() {
		return glevel;
	}

	public void setGlevel(int glevel) {
		this.glevel = glevel;
	}

}
