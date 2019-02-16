package supersql.common;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogError {
	// (1)Loggerオブジェクトの生成
	static Logger log = Logger.getLogger(LogError.class.getName());
	// (2)設定ファイルの読み込み
//	private final String ERR_XML = "/home/kyozai/toyama/SuperSQL/log4j/log4j_err.xml";
	private final String ERR_XML = GlobalEnv.getworkingDir() + GlobalEnv.OS_FS + "log4j" + GlobalEnv.OS_FS + "log4j_err.xml";


	// 20140625_masato 実習用　
	public LogError(){
		DOMConfigurator.configure(ERR_XML);
	}

	public static void logErr() {
		if(GlobalEnv.isLogger()){
			GlobalEnv.queryInfo += " x " + " | " + GlobalEnv.queryLog + " | " + Ssedit.getautocorrectValue();
			GlobalEnv.errorText_main += GlobalEnv.getusername() + " | " + GlobalEnv.errorText;
			LogError error = new LogError();
			error.logErr(GlobalEnv.errorText_main);
			LogInfo.logInfo(false);
		}
	}

	private void logErr(String queryInfo) {
		log.debug(queryInfo);
	}
}

