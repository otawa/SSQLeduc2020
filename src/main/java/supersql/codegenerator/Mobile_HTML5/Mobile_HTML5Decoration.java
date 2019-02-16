package supersql.codegenerator.Mobile_HTML5;

import supersql.codegenerator.Decorator;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.codegenerator.Web.WebEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Mobile_HTML5Decoration extends Decorator {

	Manager manager;
	
	Mobile_HTML5Env html_env;
    Mobile_HTML5Env html_env2;
    
    public Mobile_HTML5Decoration(Manager manager, Mobile_HTML5Env henv, Mobile_HTML5Env henv2) {
    	this.manager = manager;
        this.html_env = henv;
        this.html_env2 = henv2;
    }
    
    @Override
    public String work(ExtList data_info) {
    	Log.out("------- Decoration -------");
		Log.out("tfes.contain_itemnum = " + tfes.contain_itemnum());
		Log.out("tfes.size = " + tfes.size());
		Log.out("countconnectitem = " + countconnectitem());
		
		// connector カウント初期化
		this.setDataList(data_info);
		
		// 子要素に書き込み
		int i = 0;
		while (this.hasMoreItems()) {
			ITFE tfe = tfes.get(i);
			String classid = WebEnv.getClassID(tfe);
	
			this.worknextItem();
			i++;
		}
		
		Log.out("+++++++ Decoration +++++++");
		return null;
    }
}
