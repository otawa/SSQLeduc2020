package supersql.codegenerator;

import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;

import com.ibm.db2.jcc.am.id;

import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;

// added by halken
public class Modifier {
	
	public static ArrayList<String> replaceModifierValues (String property, String data_info) {
		String value = data_info;
		
		if (property.equals("align") || property.equals("text-align")) {
			property = "text-align";
		}
		
		if (property.equals("bgcolor") || property.equals("background-color")) {
			property = "background-color";
		}
		
		if (property.equals("color") || property.equals("font-color")) {
			property = "color";
		}
		
		if (property.equals("height")) {
			if (!GlobalEnv.isNumber(data_info)) {
				value = data_info;
			} else {
				value = data_info + "px";
			}
		}
		
		if (property.equals("padding")) {
			if (!GlobalEnv.isNumber(data_info)) {
				value = data_info;
			} else {
				value = data_info + "px";
			}
		}
		
		if (property.equals("size") || property.equals("font-size")) {
			property = "font-size";
			if (!GlobalEnv.isNumber(data_info)) {
				value = data_info;
			} else {
				value = data_info + "px";
			}
		}
		
		if (property.equals("valign") || property.equals("vertical-align")) {
			property = "vertical-align";
		}
		
		if (property.equals("width")) {
			if (!GlobalEnv.isNumber(data_info)) {
				value = data_info;
			} else {
				value = data_info + "px";
			}
		}
		
		ArrayList<String> data = new ArrayList<String>();
		data.add(property);
		data.add(value);
		return data;
	}








	public static String getClassModifierValue(DecorateList decos) {//class
		
		if (decos.containsKey("class")) {
			return  decos.getStr("class") ;
		}
		
		return "";
	} 
	public static String getIdModifierValue(DecorateList decos) {//id
		
		if (decos.containsKey("id")) {
			return  " id=\""+ decos.getStr("id") + "\" " ;
		}
		
		return "";
	}
	public static String getClassAndIdMOdifierValues(DecorateList decos) {//classとid
		
		String a = getClassModifierValue(decos);
		String b = getIdModifierValue(decos);
		String r ="";
		
		if(!a.isEmpty()){
			r += " class=\"" + a + "\" ";
		}
		if(!b.isEmpty()){
			r +=   b ;
		}
		return r;
	} 
	
	public static String getClassName(DecorateList decos, String getclassid){//classしか考えてない。idは考えてない
		String classname;
		if (decos.containsKey("class")) {
			classname = decos.getStr("class");
		} else {
			classname = getclassid;
		}
		return classname;
	}

	public static String getClassModifierPutValue(DecorateList decos, DecorateList cssclass, String classid) {//class
		
		if (decos.containsKey("class")) {
			cssclass.put(classid, decos.getStr("class"));
		}
		
		return "";
	} 
	public static String getIdModifierPutValue(DecorateList decos, DecorateList cssclass, String classid) {//id
		
		if (decos.containsKey("id")) {
			cssclass.put(classid, decos.getStr("id"));
		}
		
		return "";
	}



}
