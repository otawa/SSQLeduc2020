package supersql.codegenerator;

import supersql.common.Log;

public class FunctionData {

	private String[] funcname = { "verb", "null", "imagefile", "link" ,"sinvoke" , "submit","select","checkbox","radio","inputtext","textarea","ssql::xpath","xpath","xmldata","ssql::xmlquery","xmlquery"};

	private int[] functype = { 0, 1, 1, 1 , 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

	// == 0 String only, > 0 TFE(n)+String
	private int count = 1;

	public FunctionData(String fn) {

		for (int i = 0; i < funcname.length; i++) {
			if (fn.equals(funcname[i])) {
				count = functype[i];
				break;
			}
		}

		Log.out("func name=" + fn + ", count=" + count);

	}

	public int nexttype() {

		if (count > 0) {
			count--;
			return 1;
		}
		return 0;

	}

}