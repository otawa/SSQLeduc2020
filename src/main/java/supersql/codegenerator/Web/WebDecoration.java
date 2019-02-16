package supersql.codegenerator.Web;

import java.util.ArrayList;

import supersql.codegenerator.Decorator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebDecoration extends Decorator {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public static ArrayList<StringBuffer> divFront = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> divclass = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> divStyle = new ArrayList<StringBuffer>();
	public static ArrayList<StringBuffer> divEnd = new ArrayList<StringBuffer>();
	
	public WebDecoration(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("------- Decoration -------");
		Log.out("tfes.contain_itemnum = " + tfes.contain_itemnum());
		Log.out("tfes.size = " + tfes.size());
		Log.out("countconnectitem = " + countconnectitem());
		
		StringBuffer Front = new StringBuffer();
		StringBuffer classname = new StringBuffer();
		StringBuffer Style = new StringBuffer();
		StringBuffer End = new StringBuffer();
		divFront.add(0, Front);
		divclass.add(0, classname);
		divStyle.add(0, Style);
		divEnd.add(0, End);
//		webEnv.decorationFlag = true;
		
		ArrayList<String> decoproperty = new ArrayList<String>();
		webEnv.decorationProperty.add(0, decoproperty);
		webEnv.decorationStartFlag.add(0, false);
		webEnv.decorationEndFlag.add(0, false);
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// cssの情報を取得
		// webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// htmlコード書き込み
//	 	if (!GlobalEnv.isOpt()) {
//			webEnv.code.append("<div class=\"");
//			webEnv.code.append(WebEnv.getClassID(this));
//			webEnv.code.append(" Deco\">\n");
//		}
		
		// 子要素に書き込み
		int i = 0;
		while (this.hasMoreItems()) {
			ITFE tfe = tfes.get(i);
			String classid = WebEnv.getClassID(tfe);
			
//			webEnv.decorationStartFlag = true;
			webEnv.decorationStartFlag.set(0, true);
			
			this.worknextItem();
			
//			webEnv.decorationEndFlag = true;
			webEnv.decorationEndFlag.set(0, true);
			i++;
		}
		
		if (webEnv.decorationStartFlag.size() > 1) {
			divEnd.get(1).append(divFront.get(0));
			if (!divStyle.get(0).equals("")) {
				divEnd.get(1).append(" style=\"");
				divEnd.get(1).append(divStyle.get(0));
				divEnd.get(1).append("\"");
			}
			divEnd.get(1).append(divclass.get(0));
			divEnd.get(1).append(divEnd.get(0));
		} else {
			webEnv.code.append(divFront.get(0));
			if (!divStyle.get(0).equals("")) {
				webEnv.code.append(" style=\"");
				webEnv.code.append(divStyle.get(0));
				webEnv.code.append("\"");
			}
			webEnv.code.append(divclass.get(0));
			webEnv.code.append(divEnd.get(0));
		}
		
		divFront.remove(0);
		divclass.remove(0);
		divStyle.remove(0);
		divEnd.remove(0);
		
		webEnv.decorationProperty.remove(0);
//		webEnv.decorationStartFlag = false;
		webEnv.decorationStartFlag.remove(0);
//		webEnv.decorationFlag = false;
//		webEnv.decorationEndFlag = false;
		webEnv.decorationEndFlag.remove(0);

		Log.out("+++++++ Decoration +++++++");
		return null;
	}
}
