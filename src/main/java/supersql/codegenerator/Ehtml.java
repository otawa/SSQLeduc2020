package supersql.codegenerator;

import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;
import supersql.common.Log;

public class Ehtml {
	public static boolean flag = false;

	// set ehtml flag
	public static void setEhtml() {
		flag = true;
	}

	// added by masato 20151118 for ehtml
	public static void appendToHeadFromBody(String path) {
		// if(flag){
		String cssFileName = Jscss.getGenerateCssFileName(1);
		String js = "jscss/makeTable.js";

		int num = GlobalEnv.getQueryNum() - 1;
		int pageNum = 0;

		if (HTMLEnv.g1PaginationRowNum != 0
				&& HTMLEnv.g1PaginationColumnNum != 0) {
			pageNum = (int) Math
					.ceil((double) HTMLEnv.itemCount
							/ (HTMLEnv.g1PaginationRowNum * HTMLEnv.g1PaginationColumnNum));
		} else if ((HTMLEnv.g2PaginationRowNum != 0 && HTMLEnv.g2PaginationColumnNum != 0)) {
			pageNum = (int) Math
					.ceil((double) HTMLEnv.itemCount
							/ (HTMLEnv.g2PaginationRowNum * HTMLEnv.g2PaginationColumnNum));
		}

		Log.ehtmlInfo("<script>");

		// makeTable.jsに受け渡すxmlファイルのパス
		if (num == 0) {
//			Log.ehtmlInfo("	var ItemNumPerPage = " + HTMLEnv.itemNumPerPage
//					+ ";");
			Log.ehtmlInfo("	var pageNum = " + pageNum + ";");
			Log.ehtmlInfo("	var itemCount = " + HTMLEnv.itemCount + ";");

			Log.ehtmlInfo("	var g1PaginationRowNum = [];");
			Log.ehtmlInfo("	var g1PaginationColumnNum = [];");
			Log.ehtmlInfo("	var g2PaginationRowNum = [];");
			Log.ehtmlInfo("	var g2PaginationColumnNum = [];");

			Log.ehtmlInfo("	var g1RetNum = [];");
			Log.ehtmlInfo("	var g2RetNum = [];");

			Log.ehtmlInfo("	var xmlFileName = [];");
			// Log.ehtmlInfo("	var outType = [];");
		}
		Log.ehtmlInfo("	g1PaginationRowNum[" + num + "]="
				+ HTMLEnv.g1PaginationRowNum + ";\n");
		Log.ehtmlInfo("	g1PaginationColumnNum[" + num + "]="
				+ HTMLEnv.g1PaginationColumnNum + ";\n");
		Log.ehtmlInfo("	g2PaginationRowNum[" + num + "]="
				+ HTMLEnv.g2PaginationRowNum + ";\n");
		Log.ehtmlInfo("	g2PaginationColumnNum[" + num + "]="
				+ HTMLEnv.g2PaginationColumnNum + ";\n");

		Log.ehtmlInfo("	g1RetNum[" + num + "]=" + HTMLEnv.g1RetNum + ";\n");
		Log.ehtmlInfo("	g2RetNum[" + num + "]=" + HTMLEnv.g2RetNum + ";\n");
		Log.ehtmlInfo("	xmlFileName[" + num + "]=\"" + path + "\";\n");

		// // table or div
		// if(HTMLEnv.tableFlag) {
		// Log.ehtmlInfo("	outType[" + num + "]=\"" + "table" + "\";\n");
		// } else if(HTMLEnv.divFlag) {
		// Log.ehtmlInfo("	outType[" + num + "]=\"" + "div" + "\";\n");
		// }

		// css
		Log.ehtmlInfo("	var css=document.createElement(\"link\");");
		Log.ehtmlInfo("	css.setAttribute(\"rel\", \"stylesheet\");");
		Log.ehtmlInfo("	css.setAttribute(\"type\", \"text/css\");");
		Log.ehtmlInfo("	css.setAttribute(\"href\", \"" + cssFileName + "\");");
		Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(css);\n");

		if (num == 0) {
			Log.ehtmlInfo("	var pagecss=document.createElement(\"link\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"rel\", \"stylesheet\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"type\", \"text/css\");");
			Log.ehtmlInfo("	pagecss.setAttribute(\"href\", \"jscss/simplePagination.css\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(pagecss);\n");

			// jquery library
			// Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
			// Log.ehtmlInfo("	jq.setAttribute(\"src\",\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");
			Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
			Log.ehtmlInfo("	jq.setAttribute(\"src\",\"jscss/jquery.js\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");
			
			Log.ehtmlInfo("	var jq=document.createElement(\"script\");");
			Log.ehtmlInfo("	jq.setAttribute(\"src\",\"jscss/jquery-1.10.2.min.js\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jq);\n");

			// Log.ehtmlInfo("	var jqp=document.createElement(\"script\");");
			// Log.ehtmlInfo("	jqp.setAttribute(\"src\",\"jscss/jquery-p.js\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(jqp);\n");

			Log.ehtmlInfo("	var pagejs=document.createElement(\"script\");");
			Log.ehtmlInfo("	pagejs.setAttribute(\"src\",\"jscss/jquery.simplePagination.js\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(pagejs);\n");

			// js for xml parse
			// Log.ehtmlInfo("	var js_proto=document.createElement(\"script\");");
			// Log.ehtmlInfo("	js_proto.setAttribute(\"src\",\"jscss/prototype.js\");");
			// Log.ehtmlInfo("	js_proto.setAttribute(\"type\",\"text/javascript\");");
			// Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(js_proto);\n");

			//taji add
			Log.ehtmlInfo("	var infinite_scroll=document.createElement(\"script\");");
			Log.ehtmlInfo("	infinite_scroll.setAttribute(\"src\",\"jscss/jquery.infinitescroll.min.js\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(infinite_scroll);\n");
			
			Log.ehtmlInfo("	var infinitescroll=document.createElement(\"script\");\n");
			Log.ehtmlInfo("	var infinite_definition = "
					+ "\"$(function){"
					+ "$('#content').infinitescroll({"
					+ "navSelector  : \\\".navigation\\\","
					+ "nextSelector : \\\".navigation a\\\","
					+ "itemSelector : \\\"#wrapper\\\""
					+ "});});\";\n"
					);
			Log.ehtmlInfo("infinitescroll.appendChild(document.createTextNode(infinite_definition));\n");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(infinitescroll);\n");
			
			// js generated by ssql for making table
			Log.ehtmlInfo("	var make_table=document.createElement(\"script\");");
			Log.ehtmlInfo("	make_table.setAttribute(\"src\",\"" + js + "\");");
			Log.ehtmlInfo("	make_table.setAttribute(\"type\",\"text/javascript\");");
			Log.ehtmlInfo("	document.getElementsByTagName(\"head\")[0].appendChild(make_table);\n");
			

			// if ((HTMLEnv.g1PaginationRowNum != 0 &&
			// HTMLEnv.g1PaginationColumnNum != 0)
			// || (HTMLEnv.g2PaginationRowNum != 0 &&
			// HTMLEnv.g2PaginationColumnNum != 0)) {
			// Log.ehtmlInfo("	$(function() {\n" +
			// "	    $(\"#paging\").pagination({\n" +
			// "	        items: pageNum, // ページボタンの数\n" +
			// "	        displayedPages: 1,\n" +
			// "	        cssStyle: 'light-theme',\n" +
			// "	        prevText: '<<',\n" +
			// "	        nextText: '>>',\n" +
			// "	        onPageClick: function(pageNumber){show(pageNumber)}\n"
			// +
			// "	    })\n" +
			// "	});\n" +
			// "	function show(pageNumber){\n" +
			// "		var page = \"#page-\"+pageNumber;\n" +
			// "		$('.selection').hide()\n" +
			// "		$(page).show()\n" +
			// "	}\n");
			// }
		}
		Log.ehtmlInfo("</script>");
		// }
	}

	public static void createBaseHTMLCode() {
		// TODO masato ここで指定するidは固有のものにする、また複数クエリが同ページ内で実行される用の処理も追加
		String id = "ssqlResult" + GlobalEnv.getQueryNum();
		Log.ehtmlInfo("<div id=\"" + id + "\">");
		Log.ehtmlInfo("</div>");
		if ((HTMLEnv.g1PaginationRowNum != 0 && HTMLEnv.g1PaginationColumnNum != 0)
				|| (HTMLEnv.g2PaginationRowNum != 0 && HTMLEnv.g2PaginationColumnNum != 0)) {
			Log.ehtmlInfo("<div id=\"paging\">");
			Log.ehtmlInfo("</div>");
		}
		// if (HTMLEnv.itemNumPerPage != 0) {
		// Log.ehtmlInfo("<div id=\"paging\">");
		// Log.ehtmlInfo("</div>");
		// }
	}
}
