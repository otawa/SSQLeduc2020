package supersql.codegenerator.Mobile_HTML5;

import supersql.codegenerator.DecorateList;

//20131106 show
public class Mobile_HTML5_show {
	
	public Mobile_HTML5_show() {

	}
	
	static int show_count = 0;
	static int show_count_G2 = 0;
	//static boolean showFlg = false;
	//static String showSymbol = "";	//important variable
	
	//Process
	public static boolean showProcess(DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("show") || decos.containsKey("show-first") || decos.containsKey("show-next")){
			//showSymbol = symbol;
    		String show = "";
			String showEach = "0";
    		if(decos.containsKey("show"))				show = decos.getStr("show").replaceAll("px", "").replaceAll(";", "");
    		else if(decos.containsKey("show-first"))	show = decos.getStr("show-first").replaceAll("px", "").replaceAll(";", "");
    		if(decos.containsKey("show-next"))	showEach = decos.getStr("show-next").replaceAll("px", "").replaceAll(";", "");
    		//Log.e(show+"  "+showEach);
    		if(show.isEmpty())	show = showEach;
    		//Log.e(show+"  "+showEach);
    		show_count++;
    		
    		String showClass = "ssql_showmore_"+show_count;
    		html_env.code.append(
    				"\n<script>\n" +
    				"	$(document).ready(function() {\n" +
    				"		$('."+showClass+"').showmore({\n" +
    				"	       maxHeight: "+show+",\n" +
    				"	       showEach: "+showEach+"\n" +
    				"	    });\n" +
    				"	});\n" +
    				"</script>\n\n" +
    				"<DIV class=\""+showClass+"\">\n");
    		//showFlg = true;
    		return true;
        }
		else if(decos.containsKey("autoshow") || decos.containsKey("auto-show") || 
				decos.containsKey("autoshow-first") || decos.containsKey("auto-show-first") ||
				decos.containsKey("autoshow-next") || decos.containsKey("auto-show-next")){
			String autoshow = "";
			String autoshowEach = "0";
    		if(decos.containsKey("autoshow"))				autoshow = decos.getStr("autoshow").replaceAll("px", "").replaceAll(";", "");
    		else if(decos.containsKey("autoshow-first"))	autoshow = decos.getStr("autoshow-first").replaceAll("px", "").replaceAll(";", "");
    		else if(decos.containsKey("auto-show"))			autoshow = decos.getStr("auto-show").replaceAll("px", "").replaceAll(";", "");
    		else if(decos.containsKey("auto-show-first"))	autoshow = decos.getStr("auto-show-first").replaceAll("px", "").replaceAll(";", "");
    		if(decos.containsKey("autoshow-next"))			autoshowEach = decos.getStr("autoshow-next").replaceAll("px", "").replaceAll(";", "");
    		else if(decos.containsKey("auto-show-next"))	autoshowEach = decos.getStr("auto-show-next").replaceAll("px", "").replaceAll(";", "");
    		if(autoshow.isEmpty())	autoshow = autoshowEach;
    		show_count++;
    		
    		String autoshowClass = "ssql_showmore_"+show_count;
    		String autoshowButtonClass = "ssql_autoshowmore_"+show_count;
    		String loadID = "ssql_show_loading_"+show_count;
    		String loadImage = "jscss/loading.gif";
    		int timeout = 500;
    		html_env.code.append(
    				"\n<script>\n" +
					"	$(function() {\n" +
					"		//ページ最下部にきたら自動的に次を読み込む\n" +
					"	    $(window).scroll(function() {\n" +
					"		     var current = $(window).scrollTop() + window.innerHeight;\n" +
					"		     if (current < $(document).height() - 100) return;\n" +
					"		     if ($(this).data('loading')) return;\n" +
					"\n" +
					"		     $(this).data('loading', true);\n" +
					"			 var boxHeight = $(\"."+autoshowClass+"\").outerHeight();\n" +
					//"			 //alert( $(\"."+autoshowClass+"\").height() +\"/\"+ $(\"."+autoshowClass+"\").data().boxHeight);\n" +
					"			 if( $(\"."+autoshowClass+"\").height() < $(\"."+autoshowClass+"\").data().boxHeight )\n" +
					"			 	$(\"#"+loadID+"\").html(\"<div id='"+loadID+"'><img src='"+loadImage+"'/></div>\");\n" +
					"		     setTimeout(function(){\n" +
					"			 	$(\"#"+loadID+"\").html(\"<div id='"+loadID+"'></div>\");\n" +
					"				$(\"."+autoshowButtonClass+"\").eq(0).click();\n" +
					"	    	 },"+timeout+");\n" +
					"		     $(this).data('loading', false);\n" +
					"		});\n\n" +
    				"		$('."+autoshowClass+"').showmore({\n" +
    				//"		   moreLink: '<div><input type=\"button\" class=\""+autoshowButtonClass+"\" style=\"visibility:hidden;\"></div>',\n" +
    				"		   moreLink: '<div id=\""+loadID+"\"></div><div><input type=\"button\" class=\""+autoshowButtonClass+"\" style=\"visibility:hidden;\"></div>',\n" +
    				"	       maxHeight: "+autoshow+",\n" +
    				"	       showEach: "+autoshowEach+"\n" +
    				"	    });\n" +
    				"	});\n" +
    				"</script>\n\n" +
    				"<DIV class=\""+autoshowClass+"\">\n");
    		return true;
		}
		return false;
	}
	public static String addShowCountClassName(DecorateList decos){
		//not use
		if(decos.containsKey("show") || decos.containsKey("show-first") || decos.containsKey("show-next") ||
		   decos.containsKey("autoshow") || decos.containsKey("autoshow-first") || decos.containsKey("autoshow-next")||
		   decos.containsKey("auto-show") || decos.containsKey("auto-show-first") ||  decos.containsKey("auto-show-next")){
			show_count_G2++;
			return "show_count_G2_"+show_count+"_"+show_count_G2;
		}
		return "";
	}
	public static boolean showCloseProcess(DecorateList decos, Mobile_HTML5Env html_env){
		if(decos.containsKey("show") || decos.containsKey("show-first") || decos.containsKey("show-next") ||
		   decos.containsKey("autoshow") || decos.containsKey("autoshow-first") || decos.containsKey("autoshow-next")||
		   decos.containsKey("auto-show") || decos.containsKey("auto-show-first") ||  decos.containsKey("auto-show-next")){
        //if(symbol.equals(showSymbol) && showFlg){
        	html_env.code.append("</DIV><!-- End of Show More -->\n");
        	//showFlg = false;
        	show_count_G2 = 0;
        	return true;
        }
		return false;
	}

}
