package supersql.dataconstructor;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import supersql.codegenerator.AttributeItem;
import supersql.common.Log;
import supersql.extendclass.ExtHashSet;
import supersql.extendclass.ExtList;
import supersql.parser.WhereInfo;
import supersql.parser.WhereParse;

public class MakeGroup {

	private ExtList tbl_group;

	public MakeGroup(Hashtable att, WhereInfo where) {
		makeGroup_main(att, where);
	}

	public void makeGroup_main(Hashtable att, WhereInfo where) {

		ExtList result = new ExtList();
		HashSet table_list = new HashSet();

		Iterator wc = (where.getWhereClause()).iterator();
		while (wc.hasNext()) {
			table_list.add(((WhereParse) (wc.next())).getUseTables());
		}

		Enumeration att_e = att.elements();
		while (att_e.hasMoreElements()) {
			table_list.add(((AttributeItem) (att_e.nextElement()))
					.getUseTables());
		}

		Log.out("[makeGroup] table_ExtList : " + table_list);
			

		Iterator table_e = table_list.iterator();
		//added by goto 20130306 start  "FROMなしクエリ対策 1/3"
//		result.add(table_e.next());
		try{
			result.add(table_e.next());
		}catch(Exception e){
			//Log.info("There is no 'FROM'.");
		}
		//added by goto 20130306 end
		while (table_e.hasNext()) {
			ExtHashSet tbl1 = (ExtHashSet) table_e.next();
			//		  Log.out("[makeGroup] tbl1 : "+tbl1);
			for (int i = 0; i < result.size(); i++) {
				//			Log.out("[makeGroup] result : "+result);
				ExtHashSet tbl2 = (ExtHashSet) result.get(i);
				//			Log.out("[makeGroup] tbl2 : "+tbl2);
				if (!tbl1.intersection(tbl2).isEmpty()) {
					tbl1 = tbl1.union((ExtHashSet) tbl2.clone());
					result.remove(i);
					i--;
					//			  Log.out("[makeGroup] **tbl1 : "+tbl1);
					//			  Log.out("[makeGroup] **result : "+result);
				}
			}
			result.add(tbl1);
		}
		tbl_group = new ExtList(result);
	}

	@Override
	public String toString() {
		return "{ tbl_group : " + tbl_group + " }";
	}

	public ExtList getTblGroup() {
		return tbl_group;
	}

}