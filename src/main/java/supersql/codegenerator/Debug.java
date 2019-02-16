package supersql.codegenerator;

import supersql.common.Log;

public class Debug {

	public Debug() {
	}

	public void prt(int count, String str) {
		String indentchar = new String();
		for (int i = 0; i < count; i++) {
			indentchar = indentchar + "  ";
		}
		Log.out(indentchar + str);

	}


}
