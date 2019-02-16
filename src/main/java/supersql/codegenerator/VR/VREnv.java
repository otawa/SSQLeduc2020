package supersql.codegenerator.VR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import supersql.codegenerator.Connector;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LocalEnv;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class VREnv extends LocalEnv implements Serializable{
	static String formPartsName = null;
	private static String checked = "";
	private static String[] formDetail = new String[256];
	private static String formItemName;
	// global form number : 1,2,3...
	private static int formNumber = 1;
	private static String formValueString;
	private static String IDUst = new String();
	private static boolean isFormItem;
	private static boolean search = false;
	private static String selected = "";
	private static boolean selectFlg;
	private static boolean selectRepeat;
	protected static int IDCounter = 0; // add oka
	protected static int IDOld = 0; // add oka
	public static String cond = "";
	public static String bg = "";
	public ArrayList<ArrayList<String>> decorationProperty = new ArrayList<ArrayList<String>>();
	public ArrayList<Boolean> decorationStartFlag = new ArrayList<Boolean>();
	public ArrayList<Boolean> decorationEndFlag = new ArrayList<Boolean>();
	// added by masato 20151202 
	public static boolean defaultCssFlag = true; 
	// added by masato 20151214 for paging
	public static int itemNumPerPage = 0;
	public static int itemCount = 0;
	// added by masato 20151214 for paging
	public static int g1RetNum = 0;
	public static int g2RetNum = 0;
	public static int g1PaginationRowNum = 0;
	public static int g1PaginationColumnNum = 0;
	public static int g2PaginationRowNum = 0;
	public static int g2PaginationColumnNum = 0;
	public org.w3c.dom.Document xml;
	public  Node currentNode;

	
	public static String condName = "";
	// global form item number : t1,t2,t3...
	public static int formPartsNumber = 1;
	public static String nameId = "";
	public static int searchId = 0;

	public static String getChecked() {
		return checked;
	}

	public static String getClassID(ITFE tfe) {
		String result;
		if (tfe instanceof VRC3) {
			result = getClassID((((VRC3) tfe).tfes.get(0)));
			return result;
		}
		if (tfe instanceof VRG3) {
			result = getClassID((((VRG3) tfe).tfe));
			return result;
		}
		result = "TFE" + tfe.getId();
		return result;
	}
	
	public static String getFormDetail(int i) {
		return formDetail[i];
	}

	public static boolean getFormItemFlg() {
		return isFormItem;
	}

	public static String getFormItemName() {
		if (formItemName == null) {
			return "0";
		}
		return formItemName;
	}

	public static String getFormName() {
		// return formNumber f1,f2,f3...
		return "f" + formNumber;
	}

	public static int getFormNumber() {
		// return formNumber 1,2,3...
		return formNumber;
	}

	public static String getFormPartsName() {
		if (formPartsName == null) {
			return "t" + formPartsNumber;
		} else {
			return formPartsName;
		}
	}

	public static String getFormValueString() {
		return formValueString;
	}

	public static String getIDU() {
		return IDUst;
	}

	public static String getNameid() {
		if (nameId != null) {
			return nameId;
		} else {
			return "";
		}
	}

	public static boolean getSearch() {
		return search;
	}

	public static String getSelected() {
		return selected;
	}

	public static boolean getSelectFlg() {
		return selectFlg;
	}

	public static boolean getSelectRepeat() {
		return selectRepeat;
	}

	public static void incrementFormNumber() {
		formNumber++;
	}

	public static void incrementFormPartsNumber() {
		formPartsNumber++;
	}

	public static void setChecked(String s) {
		System.out.println("checked:" + s);
		checked = s;
	}

	public static void setFormDetail(String s) {
		if (formDetail[formNumber] == null)
			formDetail[formNumber] = s;
		else
			formDetail[formNumber] += s;
	}

	// form tag is written : true
	public static void setFormItemFlg(boolean b, String s) {
		isFormItem = b;
		formItemName = s;
		return;
	}

	public static void setFormPartsName(String s) {
		formPartsName = s;
	}

	public static void setFormValueString(String s) {
		formValueString = s;
	}

	public static void setIDU(String s) {
		IDUst = s;
	}

	public static void setSearch(boolean b) {
		search = b;
		searchId = 0;
	}

	public static void setSelected(String s) {
		selected = s;
	}

	public static void setSelectFlg(boolean b) {
		selectFlg = b;
	}

	// select_repeat flag
	// not write "<tr><td>" between "<option>"s
	public static void setSelectRepeat(boolean b) {
		selectRepeat = b;
	}

	public static Document htmlEnv1;
	protected String charset = null; // added by goto 20120715
	protected Connector connector;
	protected StringBuffer div = new StringBuffer();
	protected StringBuffer meta = new StringBuffer();
	// outline�����������������������������������������������������?
	protected boolean OutlineMode = false;
	protected StringBuffer title = new StringBuffer();
	protected StringBuffer titleClass = new StringBuffer();

	public String ajaxCond = new String();

	// for ajax
	public String ajaxQuery = new String();

	public String ajaxtarget = new String();

	// add 20141203_masato
	public String code_tmp = "";
	
	public StringBuffer code;
	public static StringBuffer cs_code=new StringBuffer();//added by kotani 20161122
	
	// added by masato 20150914
	public static StringBuffer xmlCode;
	
	public int countFile;

	public static StringBuffer css;

	public StringBuffer cssFile = new StringBuffer();

	public String dragDivId = new String();

	public boolean draggable = false;

	public int embedCount = 0;

	public boolean embedFlag = false;

	public String fileName;

	public StringBuffer footer;

	public boolean foreachFlag;

	public int gLevel = 0;
	
	public ArrayList<String> outTypeList = new ArrayList<>();
	
	// added by masato 20150914
	public int cNum = 0;
	public int xmlDepth = 0;

	public boolean hasDispDiv = false;

	public int haveClass = 0;

	public static StringBuffer header;

	public int inEffect = 0;

	// for panel
	public boolean isPanel = false;
	// tk end//////////////////////////////////////////////////////

	public int linkFlag;

	public String linkOutFile;

	public String linkUrl;

	public String nextBackFile = new String();

	public Vector<String> notWrittenClassId = new Vector<String>();

	public String outDir;

	public int outEffect = 0;

	public String outFile;

	// for drag
	public StringBuffer script = new StringBuffer();

	public int scriptNum = 0;

	public boolean sinvokeFlag = false;
	
	// added by masato 20151124 for plink'values
	public ArrayList<String> valueArray;
	
	// added by masato 20151124 for plink'values
	public boolean plinkFlag = false;
	
	public String tableBorder = new String("1");

	public Vector<String> writtenClassId;

	public VREnv() {
		VREnv.htmlEnv1 = new Document("");
		new Document("");
		try {
			this.xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			this.currentNode = this.xml;
		} catch (ParserConfigurationException e) {
			this.xml = null;
			e.printStackTrace();
		}
	}
	
	public void getFooter() {
		header_creation();
	}

	public String getOutlineMode() {
		if (OutlineMode) {
			OutlineMode = false;
			return "";
		}
		// return " frame=void class=nest ";
		return " frame=void ";
	}

	public void header_creation() {
		this.currentNode = this.currentNode.appendChild(this.xml.createElement("DOC"));
	}

	public void setOutlineMode() {
		OutlineMode = true;
	}
	
	public static void initXML(){
		xmlCode = new StringBuffer();
		xmlCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

}
