package supersql.codegenerator.Web;

import supersql.codegenerator.FuncArg;
import supersql.codegenerator.Function;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebFunction extends Function {
	
	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebFunction() {
		
	}
	
	public WebFunction(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		super();
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("--- start Function ---");
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// クラス名の取得
		String classname;
		if (this.decos.containsKey("class")) {
			classname = WebEnv.stringsub(this.decos.getStr("class"));
		} else {
			classname = WebEnv.getClassID(this);
		}
		
		// cssの情報を取得
		webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// 関数名取得
		String FuncName = this.getFuncName();
		
		// anchor関数の発動
		if (FuncName.equalsIgnoreCase("anchor") || FuncName.equalsIgnoreCase("a")) {
			Func_url(classname);
		}
		
		// footer関数の発動
		if (FuncName.equalsIgnoreCase("footer")) {
			Func_footer(classname);
		}
		
		// header関数の発動
		if (FuncName.equalsIgnoreCase("header")) {
			Func_header(classname);
		}
		
		// image関数の発動
		if (FuncName.equalsIgnoreCase("image")) {
			Func_image(classname);
		}
		
		// line関数の発動
		if (FuncName.equalsIgnoreCase("line")) {
			Func_line(classname);
		}

		// null関数の発動
		if (FuncName.equalsIgnoreCase("null")) {
			Func_null();
		}

		// text関数の発動
		if (FuncName.equalsIgnoreCase("text")) {
			Func_text();
		}
		
		Log.out("--- end Function ---");
		return null;
	}
	
	// footer関数
	protected void Func_footer(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		String title = arg1.getStr();
		if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationStartFlag.get(0)) {
				WebDecoration.divFront.get(0).append("<div");
				WebDecoration.divclass.get(0).append(" class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					// defaultのfooter style // TODO
				}
				WebDecoration.divEnd.get(0).append(" footer\">");
				WebDecoration.divEnd.get(0).append(title);
				WebDecoration.divEnd.get(0).append("</div>\n");
				webEnv.decorationStartFlag.set(0, false);
			} else {
				WebDecoration.divEnd.get(0).append("<div class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					// defaultのfooter styke // TODO
				}
				WebDecoration.divEnd.get(0).append(" footer\">");
				WebDecoration.divEnd.get(0).append(title);
				WebDecoration.divEnd.get(0).append("</div>\n");
			}
		} else {
			webEnv.code.append("<div class=\"");
			webEnv.code.append(classname);
			webEnv.code.append(" footer\">");
			webEnv.code.append(title);
			webEnv.code.append("</div>\n");
		}
		
		WebEnv.footerFlag = true;
	}
	
	// header関数
	protected void Func_header(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		String title = arg1.getStr();
		if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationStartFlag.get(0)) {
				WebDecoration.divFront.get(0).append("<div");
				WebDecoration.divclass.get(0).append(" class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					// defaultのfooter style // TODO
				}
				WebDecoration.divEnd.get(0).append(" header\">");
				WebDecoration.divEnd.get(0).append(title);
				WebDecoration.divEnd.get(0).append("</div>\n");
				webEnv.decorationStartFlag.set(0, false);
			} else {
				WebDecoration.divEnd.get(0).append("<div class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					// defaultのfooter styke // TODO
				}
				WebDecoration.divEnd.get(0).append(" header\">");
				WebDecoration.divEnd.get(0).append(title);
				WebDecoration.divEnd.get(0).append("</div>\n");
			}
		} else {
			webEnv.code.append("<div class=\"");
			webEnv.code.append(classname);
			webEnv.code.append(" header\">");
			webEnv.code.append(title);
			webEnv.code.append("</div>\n");
		}
		
		WebEnv.headerFlag = true;
	}
	
	// image関数
	protected void Func_image(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		FuncArg arg2 = (FuncArg) this.Args.get(1);
		
		// image(path)
		String path = arg2.getStr() + "/" + arg1.getStr();
		
		// HTMLに書き込み
		if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationStartFlag.get(0)) {
				if (webEnv.tableFlag) {
					WebDecoration.divFront.get(0).append("<td>\n");
				}
				WebDecoration.divFront.get(0).append("<img");
				WebDecoration.divclass.get(0).append(" class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					WebDecoration.divEnd.get(0).append(" style-img");
				}
				WebDecoration.divEnd.get(0).append(" att\" src=\"" + path + "\">\n");
				if (webEnv.tableFlag) {
					WebDecoration.divEnd.get(0).append("</td>\n");
				}
				webEnv.decorationStartFlag.set(0, false);
			} else {
				if (webEnv.tableFlag) {
					WebDecoration.divEnd.get(0).append("<td>\n");
				}
				WebDecoration.divEnd.get(0).append("<img class=\"");
				WebDecoration.divEnd.get(0).append(classname);
				if (WebEnv.style != null) {
					WebDecoration.divEnd.get(0).append(" style-img");
				}
				WebDecoration.divEnd.get(0).append(" att\" src=\"" + path + "\">\n");
				if (webEnv.tableFlag) {
					WebDecoration.divEnd.get(0).append("</td>\n");
				}
			}
		} else {
			if (webEnv.tableFlag) {
				webEnv.code.append("<td>\n");
			}
			webEnv.code.append("<img class=\"");
			webEnv.code.append(classname);
			if (WebEnv.style != null) {
				webEnv.code.append(" style-img");
			}
			webEnv.code.append(" att\" src=\"" + path + "\">\n");
			if (webEnv.tableFlag) {
				webEnv.code.append("</td>\n");
			}
		}
	}
	
	// line関数
	protected void Func_line(String classname) {
		if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationStartFlag.get(0)) {
				try {
					FuncArg arg1 = (FuncArg) this.Args.get(0);
					try { // 引数2つ(色と太さ)
						FuncArg arg2 = (FuncArg) this.Args.get(1);
						String color = arg1.getStr();
						String size = arg2.getStr();
						WebDecoration.divFront.get(0).append("<hr");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-line");
						}
						WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"");
						WebDecoration.divEnd.get(0).append(color);
						WebDecoration.divEnd.get(0).append("\" size=\"");
						WebDecoration.divEnd.get(0).append(size);
						WebDecoration.divEnd.get(0).append("\">\n");
					} catch (Exception e) { // 引数1つ(色のみ)
						String color = arg1.getStr();
						WebDecoration.divFront.get(0).append("<hr");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-line");
						}
						WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"");
						WebDecoration.divEnd.get(0).append(color);
						WebDecoration.divEnd.get(0).append("\">\n");
					}
				} catch (Exception e) { // 引数なし(デフォルト)
					WebDecoration.divFront.get(0).append("<hr");
					WebDecoration.divclass.get(0).append(" class=\"");
					WebDecoration.divEnd.get(0).append(classname);
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" style-line");
					}
					WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"#000000\" size=\"10\">");
				}
				webEnv.decorationStartFlag.set(0, false);
			} else {
				try {
					FuncArg arg1 = (FuncArg) this.Args.get(0);
					try { // 引数2つ(色と太さ)
						FuncArg arg2 = (FuncArg) this.Args.get(1);
						String color = arg1.getStr();
						String size = arg2.getStr();
						WebDecoration.divEnd.get(0).append("<hr class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-line");
						}
						WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"");
						WebDecoration.divEnd.get(0).append(color);
						WebDecoration.divEnd.get(0).append("\" size=\"");
						WebDecoration.divEnd.get(0).append(size);
						WebDecoration.divEnd.get(0).append("\">\n");
					} catch (Exception e) { // 引数1つ(色のみ)
						String color = arg1.getStr();
						WebDecoration.divEnd.get(0).append("<hr class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-line");
						}
						WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"");
						WebDecoration.divEnd.get(0).append(color);
						WebDecoration.divEnd.get(0).append("\">\n");
					}
				} catch (Exception e) { // 引数なし(デフォルト)
					WebDecoration.divEnd.get(0).append("<hr class=\"");
					WebDecoration.divEnd.get(0).append(classname);
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" style-line");
					}
					WebDecoration.divEnd.get(0).append("\" width=\"100%\" color=\"#000000\" size=\"10\">");
				}
			}
		} else {
			try {
				FuncArg arg1 = (FuncArg) this.Args.get(0);
				try { // 引数2つ(色と太さ)
					FuncArg arg2 = (FuncArg) this.Args.get(1);
					String color = arg1.getStr();
					String size = arg2.getStr();
					webEnv.code.append("<hr class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-line");
					}
					webEnv.code.append("\" width=\"100%\" color=\"");
					webEnv.code.append(color);
					webEnv.code.append("\" size=\"");
					webEnv.code.append(size);
					webEnv.code.append("\">\n");
				} catch (Exception e) { // 引数1つ(色のみ)
					String color = arg1.getStr();
					webEnv.code.append("<hr class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-line");
					}
					webEnv.code.append("\" width=\"100%\" color=\"");
					webEnv.code.append(color);
					webEnv.code.append("\">\n");
				}
			} catch (Exception e) { // 引数なし(デフォルト)
				webEnv.code.append("<hr class=\"");
				webEnv.code.append(classname);
				if (WebEnv.style != null) {
					webEnv.code.append(" style-line");
				}
				webEnv.code.append("\" width=\"100%\" color=\"#000000\" size=\"10\">");
			}
		}
	}
	
	// null関数
	protected void Func_null() {
		// 特に何も表記しない // TODO
	}
	
	// text関数
	protected void Func_text() {
		// 引数の取得
		try {
			
		} catch (Exception e) { // 引数なし(デフォルト) 
			webEnv.code.append("<input type=\"text\">\n");
		}
	}
	
	// anchor関数 and a関数
	protected void Func_url(String classname) {
		// 引数の取得
		FuncArg arg1 = (FuncArg) this.Args.get(0);
		FuncArg arg2 = (FuncArg) this.Args.get(1);
		
		if (webEnv.decorationStartFlag.size() > 0) {
			if (webEnv.decorationStartFlag.get(0)) {
				try {// 引数が3つ
					FuncArg arg3 = (FuncArg) this.Args.get(2);
					
					// anchor(path, url, "image")
					String path = arg1.getStr();
					String url = arg2.getStr();
					// HTMLに書き込み
					if (webEnv.tableFlag) {
						WebDecoration.divFront.get(0).append("<td");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-table-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					} else {
						WebDecoration.divFront.get(0).append("<div");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					}
					WebDecoration.divEnd.get(0).append("<a");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-anchor\"");
					}
					WebDecoration.divEnd.get(0).append(" href=\"" + url + "\">");
					WebDecoration.divEnd.get(0).append("<img");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-img\"");
					}
					WebDecoration.divEnd.get(0).append(" src=\"" + path + "\"></a>");
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("</td>\n");
					} else {
						WebDecoration.divEnd.get(0).append("</div>\n");
					}
				} catch (Exception e) { // 引数が2つ
					// anchor(name, url)
					String name = arg1.getStr();
					String url = arg2.getStr();
					// HTMLに書き込み
					if (webEnv.tableFlag) {
						WebDecoration.divFront.get(0).append("<td");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-table-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					} else {
						WebDecoration.divFront.get(0).append("<div");
						WebDecoration.divclass.get(0).append(" class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					}
					WebDecoration.divEnd.get(0).append("<a");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-anchor\"");
					}
					WebDecoration.divEnd.get(0).append(" href=\"" + url + "\">" + name + "</a>");
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("</td>\n");
					} else {
						WebDecoration.divEnd.get(0).append("</div>\n");
					}
				}
				webEnv.decorationStartFlag.set(0, false);
			} else {
				try {// 引数が3つ
					FuncArg arg3 = (FuncArg) this.Args.get(2);
					
					// anchor(path, url, "image")
					String path = arg1.getStr();
					String url = arg2.getStr();
					// HTMLに書き込み
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("<td class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-table-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					} else {
						WebDecoration.divEnd.get(0).append("<div class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					}
					WebDecoration.divEnd.get(0).append("<a");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-anchor\"");
					}
					WebDecoration.divEnd.get(0).append(" href=\"" + url + "\">");
					WebDecoration.divEnd.get(0).append("<img");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-img\"");
					}
					WebDecoration.divEnd.get(0).append(" src=\"" + path + "\"></a>");
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("</td>\n");
					} else {
						WebDecoration.divEnd.get(0).append("</div>\n");
					}
				} catch (Exception e) { // 引数が2つ
					// anchor(name, url)
					String name = arg1.getStr();
					String url = arg2.getStr();
					// HTMLに書き込み
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("<td class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-table-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					} else {
						WebDecoration.divEnd.get(0).append("<div class=\"");
						WebDecoration.divEnd.get(0).append(classname);
						if (WebEnv.style != null) {
							WebDecoration.divEnd.get(0).append(" style-att");
						}
						WebDecoration.divEnd.get(0).append(" att\">");
					}
					WebDecoration.divEnd.get(0).append("<a");
					if (WebEnv.style != null) {
						WebDecoration.divEnd.get(0).append(" class=\"style-anchor\"");
					}
					WebDecoration.divEnd.get(0).append(" href=\"" + url + "\">" + name + "</a>");
					if (webEnv.tableFlag) {
						WebDecoration.divEnd.get(0).append("</td>\n");
					} else {
						WebDecoration.divEnd.get(0).append("</div>\n");
					}
				}
			}
		} else {
			try {// 引数が3つ
				FuncArg arg3 = (FuncArg) this.Args.get(2);
				
				// anchor(path, url, "image")
				String path = arg1.getStr();
				String url = arg2.getStr();
				// HTMLに書き込み
				if (webEnv.tableFlag) {
					webEnv.code.append("<td class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-table-att");
					}
					webEnv.code.append(" att\">");
				} else {
					webEnv.code.append("<div class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-att");
					}
					webEnv.code.append(" att\">");
				}
				webEnv.code.append("<a");
				if (WebEnv.style != null) {
					webEnv.code.append(" class=\"style-anchor\"");
				}
				webEnv.code.append(" href=\"" + url + "\">");
				webEnv.code.append("<img");
				if (WebEnv.style != null) {
					webEnv.code.append(" class=\"style-img\"");
				}
				webEnv.code.append(" src=\"" + path + "\"></a>");
				if (webEnv.tableFlag) {
					webEnv.code.append("</td>\n");
				} else {
					webEnv.code.append("</div>\n");
				}
			} catch (Exception e) { // 引数が2つ
				// anchor(name, url)
				String name = arg1.getStr();
				String url = arg2.getStr();
				// HTMLに書き込み
				if (webEnv.tableFlag) {
					webEnv.code.append("<td class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-table-att");
					}
					webEnv.code.append(" att\">");
				} else {
					webEnv.code.append("<div class=\"");
					webEnv.code.append(classname);
					if (WebEnv.style != null) {
						webEnv.code.append(" style-att");
					}
					webEnv.code.append(" att\">");
				}
				webEnv.code.append("<a");
				if (WebEnv.style != null) {
					webEnv.code.append(" class=\"style-anchor\"");
				}
				webEnv.code.append(" href=\"" + url + "\">" + name + "</a>");
				if (webEnv.tableFlag) {
					webEnv.code.append("</td>\n");
				} else {
					webEnv.code.append("</div>\n");
				}
			}
		}
	}

}
