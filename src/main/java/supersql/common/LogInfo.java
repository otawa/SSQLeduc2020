package supersql.common;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogInfo {
	// (1)Loggerオブジェクトの生成
	static Logger log = Logger.getLogger(LogInfo.class.getName());
	// (2)設定ファイルの読み込み
//	private final String INFO_XML = "/home/kyozai/toyama/SuperSQL/log4j/log4j_info.xml";
	private final String INFO_XML = GlobalEnv.getworkingDir() + GlobalEnv.OS_FS + "log4j" + GlobalEnv.OS_FS + "log4j_info.xml";


	// 20140625_masato 実習用　
	public LogInfo(){
		DOMConfigurator.configure(INFO_XML);
	}

	public static void logInfo(boolean succeeded) {
		if(GlobalEnv.isLogger() && !GlobalEnv.queryInfo.trim().isEmpty()){
			if(succeeded)
				GlobalEnv.queryInfo +=  "o" + " | " + GlobalEnv.queryLog + " | " + Ssedit.getautocorrectValue();
			LogInfo info = new LogInfo();
			info.logInfo(GlobalEnv.queryInfo);
		}
	}

	private void logInfo(String queryInfo) {
		log.debug(queryInfo);
	}

}

