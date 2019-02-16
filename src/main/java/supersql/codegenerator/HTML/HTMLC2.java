package supersql.codegenerator.HTML;

import java.io.Serializable;

import org.apache.http.impl.cookie.AbstractCookieAttributeHandler;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Incremental;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Modifier;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class HTMLC2 extends Connector implements Serializable {

	private HTMLEnv htmlEnv;
	private HTMLEnv htmlEnv2;

	// 鐃緒申鐃藷ストラク鐃緒申
	public HTMLC2(Manager manager, HTMLEnv henv, HTMLEnv henv2) {
		this.htmlEnv = henv;
		this.htmlEnv2 = henv2;
	}

	@Override
	public String getSymbol() {
		return "HTMLC2";
	}

	// C2鐃緒申work鐃潤ソ鐃獣ワ申
	@Override
	public String work(ExtList data_info) {
		Log.out("------- C2 -------");
		Log.out("tfes.contain_itemnum=" + tfes.contain_itemnum());
		Log.out("tfessize=" + tfes.size());
		Log.out("countconnetitem=" + countconnectitem());

		this.setDataList(data_info);

		if (Incremental.flag || Ehtml.flag) {
			String outType = "div";

			if(htmlEnv.xmlDepth!=0){
				// 親のoutTypeを継承
				outType = htmlEnv.outTypeList.get(htmlEnv.xmlDepth-1);
			}
			if (decos.containsKey("table") || !outType.equals("div")) {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "table");
			} else {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
			}
			if (decos.containsKey("div")) {
				htmlEnv.outTypeList.add(htmlEnv.xmlDepth, "div");
			}
			//			System.out.println("C2 tableFlg = " + tableFlg + ", divFlg = " + divFlg);
			htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			Incremental.outXMLData(htmlEnv.xmlDepth,
					"<Connector" + htmlEnv.cNum
					+ " type=\'C2\' outType=\'" + htmlEnv.outTypeList.get(htmlEnv.xmlDepth) + "\' class=\'"
					+ HTMLEnv.getClassID(this) + "\'>\n");
			while (this.hasMoreItems()) {
				htmlEnv.cNum++;
				htmlEnv.xmlDepth++;
				this.worknextItem();
				htmlEnv.cNum--;
				htmlEnv.xmlDepth--;
			}
			Incremental.outXMLData(htmlEnv.xmlDepth, "</Connector"
					+ htmlEnv.cNum + ">\n");
			return null;
		} else {

			if (decos.containsKey("form")) {
				htmlEnv2.code.append("<form" + HTMLEnv.getFormNumber()
				+ "start />");
				if (decos.getStr("form").toLowerCase().equals("search")) {
					HTMLEnv.setSearch(true);
				}
			}

			if (decos.containsKey("insert")) {
				HTMLEnv.setIDU("insert");
			}
			if (decos.containsKey("update")) {
				HTMLEnv.setIDU("update");
			}
			if (decos.containsKey("delete")) {
				HTMLEnv.setIDU("delete");
			}

			String classname = Modifier.getClassName(decos, HTMLEnv.getClassID(this));
//			if (this.decos.containsKey("class")) {
//				classname = this.decos.getStr("class");
//			} else {
//				classname = HTMLEnv.getClassID(this);
//			}
			
				
			// tk
			// start////////////////////////////////////////////////////////////////
			htmlEnv.append_css_def_td(HTMLEnv.getClassID(this), this.decos);

			if (!GlobalEnv.isOpt()) {
				if (htmlEnv.decorationStartFlag.size() > 0) {
					if (htmlEnv.decorationStartFlag.get(0)) {
						HTMLDecoration.fronts.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.fronts.get(0).append(htmlEnv.tableBorder + "\"");
						HTMLDecoration.fronts.get(0).append(htmlEnv.getOutlineMode());
						HTMLDecoration.classes.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						//HTMLDecoration.ends.get(0).append("\">");
						HTMLDecoration.ends.get(0).append("\" "+ Modifier.getIdModifierValue(decos)+" >");//kotani_idmodifier_ok
						htmlEnv.decorationStartFlag.set(0, false);
					} else {
						HTMLDecoration.ends.get(0).append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
						HTMLDecoration.ends.get(0).append(htmlEnv.tableBorder + "\"");
						HTMLDecoration.ends.get(0).append(htmlEnv.getOutlineMode());
						HTMLDecoration.ends.get(0).append(" class=\"");
						HTMLDecoration.ends.get(0).append(classname);
						//HTMLDecoration.ends.get(0).append("\">");
						HTMLDecoration.ends.get(0).append("\" "+ Modifier.getIdModifierValue(decos)+" >");//kotani_idmodifier_ok
					}
				} else {
					htmlEnv.code
					.append("<TABLE cellSpacing=\"0\" cellPadding=\"0\" border=\"");
					htmlEnv.code.append(htmlEnv.tableBorder + "\" ");
					htmlEnv.code.append(htmlEnv.getOutlineMode());
					if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
						htmlEnv.code.append(" class=\"");
						htmlEnv.code.append(HTMLEnv.getClassID(this));
					}

					if (decos.containsKey("class")) {
						if (!htmlEnv.writtenClassId.contains(HTMLEnv
								.getClassID(this))) {
							htmlEnv.code.append(" class=\"");
						} else {
							htmlEnv.code.append(" ");
						}
						//htmlEnv.code.append(decos.getStr("class") + "\" ");
						htmlEnv.code.append(Modifier.getClassModifierValue(decos) + "\" ");//kotani_idmodifier_ok
					} else if (htmlEnv.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						htmlEnv.code.append("\" ");
					}
					htmlEnv.code.append(Modifier.getIdModifierValue(decos));//kotani_idmodifier_ok
					htmlEnv.code.append(">");
				}
			}
			if (GlobalEnv.isOpt()) {
				htmlEnv2.code.append("<tfe type=\"connect\" dimension=\"2\"");
				if (decos.containsKey("tablealign"))
					htmlEnv2.code.append(" align=\""
							+ decos.getStr("tablealign") + "\"");
				if (decos.containsKey("tablevalign"))
					htmlEnv2.code.append(" valign=\""
							+ decos.getStr("tablevalign") + "\"");
				if (decos.containsKey("height"))
					htmlEnv2.code.append(" height=\"" + decos.getStr("height")
					+ "\"");
				if (decos.containsKey("tabletype")) {
					htmlEnv2.code.append(" tabletype=\""
							+ decos.getStr("tabletype") + "\"");
					if (decos.containsKey("cellspacing")) {
						htmlEnv2.code.append(" cellspacing=\""
								+ decos.getStr("cellspacing") + "\"");
					}
					if (decos.containsKey("cellpadding")) {
						htmlEnv2.code.append(" cellpadding=\""
								+ decos.getStr("cellpadding") + "\"");
					}
					if (decos.containsKey("border")) {
						htmlEnv2.code.append(" border=\""
								+ decos.getStr("border").replace("\"", "")
								+ "\"");
					}

					if (decos.containsKey("tableborder")) {
						htmlEnv2.code.append(" tableborder=\""
								+ decos.getStr("tableborder").replace("\"", "")
								+ "\"");
					}
				} else {
					if (decos.containsKey("border")) {
						htmlEnv2.code.append(" border=\""
								+ decos.getStr("border").replace("\"", "")
								+ "\"");
					} else {
						htmlEnv2.code.append(" border=\""
								+ htmlEnv.tableBorder.replace("\"", "") + "\"");
					}
					if (decos.containsKey("tableborder")) {
						htmlEnv2.code.append(" tableborder=\""
								+ decos.getStr("tableborder").replace("\"", "")
								+ "\"");
					}
				}
				if (htmlEnv.writtenClassId.contains(HTMLEnv.getClassID(this))) {
					htmlEnv2.code.append(" class=\"");
					htmlEnv2.code.append(HTMLEnv.getClassID(this));
				}

				if (decos.containsKey("class")) {
					if (!htmlEnv.writtenClassId.contains(HTMLEnv
							.getClassID(this))) {
						htmlEnv2.code.append(" class=\"");
					} else {
						htmlEnv2.code.append(" ");
					}
					//htmlEnv2.code.append(decos.getStr("class"));
					htmlEnv2.code.append(Modifier.getClassModifierValue(decos));
				} else if (htmlEnv.writtenClassId.contains(HTMLEnv
						.getClassID(this))) {
					htmlEnv2.code.append("\" ");
				}
				Modifier.getIdModifierValue(decos);

				if (decos.containsKey("form")) {
					htmlEnv2.code.append(" form=\"" + HTMLEnv.getFormNumber()
					+ "\" ");
				}

				htmlEnv2.code.append(">");
			}
			/*
			 * if(decos.containsKey("outborder"))
			 * html_env.code.append(" noborder ");
			 */

			// html_env2.code.append(">");

			// System.out.println(html_env.code);

			// tk
			// end//////////////////////////////////////////////////////////////////

			// Log.out("<TABLE class=\""+HTMLEnv.getClassID(this) + "\">");

			int i = 0;

			if (decos.containsKey("form")) {
				htmlEnv.code.append(HTMLFunction.createForm(decos));
				HTMLEnv.setFormItemFlg(true, null);
			}

			while (this.hasMoreItems()) {
				htmlEnv.cNum++;
				htmlEnv.xmlDepth++;
				ITFE tfe = tfes.get(i);

				if (htmlEnv.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("<TR><TD class=\"" + classname + " nest\">\n");
				} else {
//					if(!data.isEmpty()){
//						if(!data.get(dindex).equals("dummydummydummy")){
//								Log.info("data:"+data);
							htmlEnv.code.append("<TR><TD class=\""
									+ HTMLEnv.getClassID(tfe) + " nest\">\n");
//						}
//					}else{
//						htmlEnv.code.append("<TR><TD class=\""
//								+ HTMLEnv.getClassID(tfe) + " nest\">\n");
//					}
				}
				String classid = HTMLEnv.getClassID(tfe);

				// Log.out("<TR><TD class=\"nest "
				// + HTMLEnv.getClassID(tfe) + " nest\"> decos:" + decos);
				this.worknextItem();

				//				if (htmlEnv.notWrittenClassId.contains(classid)) {
				//					htmlEnv.code.delete(htmlEnv.code.indexOf(classid),
				//							htmlEnv.code.indexOf(classid) + classid.length()
				//									+ 1);
				//				}

				if (htmlEnv.decorationStartFlag.size() > 0) {
					HTMLDecoration.ends.get(0).append("</TD></TR>\n");
				} else {
//					if(!data.get(dindex).equals("dummydummydummy")){
						htmlEnv.code.append("</TD></TR>\n");
//							Log.out("</TD></TR>");
//					}
				}
				i++;
				htmlEnv.cNum--;
				htmlEnv.xmlDepth--;
			}

			htmlEnv2.code.append("</tfe>");
			// Log.out("</TABLE>");
			if (decos.containsKey("form")) {
				htmlEnv2.code.append("<form" + HTMLEnv.getFormNumber()
				+ "end />");
				htmlEnv.code.append(HTMLEnv.exFormNameCreate());
				htmlEnv.code.append("</form>");
				HTMLEnv.setFormItemFlg(false, null);
				HTMLEnv.incrementFormNumber();
				if (decos.getStr("form").toLowerCase().equals("search"))
					HTMLEnv.setSearch(false);
			}

			if (htmlEnv.decorationStartFlag.size() > 0) {
				if (htmlEnv.decorationStartFlag.get(0)) {
					HTMLDecoration.ends.get(0).append("</TABLE>\n");
					htmlEnv.decorationStartFlag.set(0, false);
				} else {
					HTMLDecoration.ends.get(0).append("</TABLE>\n");
				}
			} else {
				htmlEnv.code.append("</TABLE>\n");
			}

			Log.out("TFEId = " + HTMLEnv.getClassID(this));
			// html_env.append_css_def_td(HTMLEnv.getClassID(this), this.decos);
			// System.out.println("</Connector" + htmlEnv.cNum + ">");
			// htmlEnv.xmlCode.append("</Connector" + htmlEnv.cNum + ">\n");
			Log.out("+++++++ C2 +++++++");
			return null;
		}
	}

}
