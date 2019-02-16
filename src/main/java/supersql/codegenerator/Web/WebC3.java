package supersql.codegenerator.Web;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import supersql.codegenerator.Connector;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class WebC3 extends Connector{

	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebC3(Manager manager, WebEnv wEnv, WebEnv wEnv2) {
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	@Override
	public String work(ExtList data_info) {
		Log.out("------- C3 -------");
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// cssの情報を取得 (多分いらない？)
		webEnv.append_css_def_att(WebEnv.getClassID(this), this.decos);
		
		// <a>タグをattributeに含ませる処理
		int c3Items = tfeItems;
		webEnv.countFile++;
		webEnv.linkUrl = webEnv.linkOutFile + "_" + String.valueOf(webEnv.countFile) + ".html";
		webEnv.linkFlag++;
		this.worknextItem();
		webEnv.linkFlag--;
		
		// 今までの情報格納
		String parentfile = webEnv.fileName;
		String parentfile2 = webEnv2.fileName;
		
		// <a>タグ先のhtmlファイルを出力する処理
		for (int k = 1; k < c3Items; k++) {
			StringBuffer parentcode = webEnv.code;
			StringBuffer parentheader = webEnv.header;
			StringBuffer parentfooter = webEnv.footer;
			webEnv.code = new StringBuffer();
			webEnv.header = new StringBuffer();
			webEnv.footer = new StringBuffer();
			
			webEnv.fileName = webEnv.outFile + "_" + String.valueOf(webEnv.countFile) + ".html";
			
			// さらにリンク先がある場合
			if (k < c3Items - 1) {
				webEnv.countFile++;
				webEnv.linkUrl = webEnv.linkOutFile + "_" + String.valueOf(webEnv.countFile) + ".html";
				webEnv.linkFlag++;
			}
			
			this.worknextItem();
			
			if (k < c3Items - 1) {
				webEnv.linkFlag--;
			}
			
			// ヘッダーフッター呼び出し
			webEnv.getHeader();
			webEnv.getFooter();
			
			// ファイル出力
			try {
				PrintWriter pw;
				pw = new PrintWriter(new BufferedWriter(new FileWriter(webEnv.fileName)));
				pw.println(webEnv.header);
				pw.println(webEnv.code);
				pw.println(webEnv.footer);
				pw.close();
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			webEnv.code = parentcode;
			webEnv.header = parentheader;
			webEnv.footer = parentfooter;
		}
		
		// ファイル名を元に戻す
		webEnv.fileName = parentfile;
		webEnv2.fileName = parentfile2;
		
		Log.out("+++++++ C3 +++++++");
		
		return null;
	}
}
