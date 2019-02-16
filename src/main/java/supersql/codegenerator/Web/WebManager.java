package supersql.codegenerator.Web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Jscss;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.dataconstructor.DataConstructor;
import supersql.extendclass.ExtList;

public class WebManager extends Manager {

	private WebEnv webEnv;
	private WebEnv webEnv2;
	
	public WebManager(WebEnv wEnv, WebEnv wEnv2) {
		this.webEnv = wEnv;
		this.webEnv2 = wEnv2;
	}
	
	private void connectOutdir(String outdir, String outfile) {
		String fileDir = new File(webEnv.outFile).getAbsoluteFile().getParent();
		if (fileDir.length() < webEnv.outFile.length() && fileDir.equals(webEnv.outFile.substring(0, fileDir.length()))) {
			webEnv.outFile = webEnv.outFile.substring(fileDir.length() + 1);
		}
		
		String tmpqueryfile = new String();
		if (webEnv.outFile.indexOf("/") > 0) {
			if (outfile != null) {
				if (webEnv.outFile.startsWith(".") || webEnv.outFile.startsWith("/")) {
					tmpqueryfile = webEnv.outFile.substring(webEnv.outFile.indexOf("/") + 1);
				}
			} else {
				tmpqueryfile = webEnv.outFile.substring(webEnv.outFile.lastIndexOf("/") + 1);
			}
		} else {
			tmpqueryfile = webEnv.outFile;
		}
		webEnv.outFile = outdir.concat(tmpqueryfile);
	}

	@Override
	public void finish() {
	}
	
	@Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {
		
		// 引数の宣言(ないとNullPointerException)
		webEnv.code = new StringBuffer();
		webEnv.css = new StringBuffer();
		webEnv.header = new StringBuffer();
		webEnv.footer = new StringBuffer();
		webEnv.writtenClassId = new Vector();
		webEnv.notWrittenClassId = new Vector();
		
		Log.out("[HTML5Manager:generateCode]");

		// ファイル名、出力先の設定
		getOutfilename();
		
		// 出力ファイル名の決定
		webEnv.fileName = webEnv.outFile + ".html";
		webEnv2.fileName = webEnv.outFile + ".xml";
		
		// 順にコードの生成
		tfe_info.work(data_info);
		
		// <html><head> ~ </head><body>まで記述
		webEnv.getHeader();
		// </body></html>まで記述
		webEnv.getFooter();
		
		// ファイルの生成
		try {
			if (CodeGenerator.getMedia().equalsIgnoreCase("web")) {
				if (!GlobalEnv.isOpt()) {
					PrintWriter pw;
					pw = new PrintWriter(new BufferedWriter(new FileWriter(webEnv.fileName)));
					
					pw.println(webEnv.header);
					pw.println(webEnv.code);
					pw.println(webEnv.footer);
					pw.close();
				}
			}
			// CSSファイル生成
			Jscss.process();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e){
			System.err.println("Error[HTML5Manager]: File IO Error in HTML5Manager");
			GlobalEnv.addErr("Error[HTML5Manager]: File IO Error in HTML5Manager");
		}
	}
	
	private String getOutfile(String outfile) {
		String out = new String();
		if (outfile.indexOf(".html") > 0) {
			out = outfile.substring(0, outfile.indexOf(".html"));
		} else {
			out = outfile;
		}
		return out;
	}
	
	protected void getOutfilename() { // ファイル名、出力先の設定
		String file = GlobalEnv.getfilename();
		String outdir = GlobalEnv.getoutdirectory();
		String outfile = GlobalEnv.getoutfilename();
		
		webEnv.outDir = outdir;
		
		if (outfile == null) {
			if (file.toLowerCase().indexOf(".sql") > 0) {
				webEnv.outFile = file.substring(0, file.toLowerCase().indexOf(".sql"));
			} else if (file.toLowerCase().indexOf(".ssql") > 0) {
				webEnv.outFile = file.substring(0, file.toLowerCase().indexOf(".ssql"));
			}
		} else {
			webEnv.outFile = getOutfile(outfile);
		}
		
		if (webEnv.outFile.indexOf("/") > 0) {
			webEnv.linkOutFile = webEnv.outFile.substring(webEnv.outFile.lastIndexOf("/") + 1);
		} else {
			webEnv.linkOutFile = webEnv.outFile;
		}
		
		if (outdir != null) {
			connectOutdir(outdir, outfile);
		}
	}
}
