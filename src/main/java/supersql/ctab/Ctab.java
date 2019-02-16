package supersql.ctab;

import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Ctab {
	public ExtList makeCtab(ExtList tfe){
		Log.info("start_tfe:"+tfe);
		//tfe's number is lt 3
		if(tfe.size() < 7){
			System.err.println("cross_tab function argument is insufficient. Three arguments are required.");
			System.exit(1);
		}
		
		//separate tfe arg
		ExtList tfe1 = (ExtList)tfe.get(2);
		ExtList tfe2 = (ExtList)tfe.get(4);
		ExtList tfe3 = (ExtList)tfe.get(6);
		Log.info("tfe1:"+tfe1);
		Log.info("tfe2:"+tfe2);
		Log.info("tfe3:"+tfe3);
		
		//check whether forest or not
		//forest->{tfe, tfe, ...} tree->tfe
		//checkForest is return -1 if attribute is tree and return num if attribute is forest(the num is the number of tree)
		//if tfe3 is forest, tfe1 or tfe2 must be tree. so when tfe1 and tfe2 doesn't correspond to tfe3 return error
		int tfe1_forest = checkForest(tfe1);
		int tfe2_forest = checkForest(tfe2);
		int tfe3_forest = checkForest(tfe3);
//		if(tfe3_forest != -1){
//			if(tfe1_forest != tfe3_forest && tfe2_forest != tfe3_forest){
//				System.err.println("the number of attributes is not corresponding");
//				System.exit(1);
//			}
//		}
		Log.info("tfe1_num:"+tfe1_forest);
		Log.info("tfe2_num:"+tfe2_forest);
		Log.info("tfe3_num:"+tfe3_forest);
		
		//add sorting information. add ascend sort.
		//if there exists, than we do nothing.
		ExtList tfe1_sorted = addSort(tfe1, 0);
		ExtList tfe2_sorted = addSort(tfe2, 0);
		Log.info("tfe1_sorted:"+tfe1_sorted);
		Log.info("tfe2_sorted:"+tfe2_sorted);
		
		//add null to tfe2
		ExtList tfe2_addnull = addNull(tfe2, 0);
		Log.info("tfe2_null:"+tfe2_addnull);
		
		//if tfe is forest divide tfe
		ExtList tfe1_divided = tfe1_sorted;
		ExtList tfe2_divided = tfe2_addnull;
		ExtList tfe3_divided = tfe3;
		if(tfe1_forest != -1){
			tfe1_divided = divideTfe(tfe1_divided, tfe1_forest);
		}
		Log.info("tfe1_divided:"+tfe1_divided);
		if(tfe2_forest != -1){
			tfe2_divided = divideTfe(tfe2_divided, tfe2_forest);
		}
		Log.info("tfe2_divided:"+tfe2_divided);
		if(tfe3_forest != -1){
			tfe3_divided = divideTfe(tfe3_divided, tfe3_forest);
		}
		Log.info("tfe3_divided:"+tfe3_divided);
		
		//merge tfe3 and tfe1 or tfe2
		//if tfe1 is forest merge tfe1 and tfe3
		//if tfe2 is forest or no one is forest merge tfe2 and tfe3
		//if both tfe2 and tfe3 are forest
		if(tfe1_forest == -1 && tfe2_forest == -1 && tfe3_forest == -1){
			
		}else if(tfe1_forest != -1 && tfe3_forest != -1){
			
		}else if(tfe2_forest != -1 && tfe3_forest != -1){
			
		}else if(tfe1_forest != -1 && tfe2_forest != -1 && tfe3_forest != -1){
			
		}
		
		
		return tfe;
	}
	private ExtList divideTfe(ExtList tfe, int tfe_forest) {
		// TODO 自動生成されたメソッド・スタブ
		ExtList set = new ExtList();
		int vertical_num = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(1)).get(1)).get(0)).get(1)).get(0)).get(1)).size();
		for(int i = 0; i < vertical_num; i += 2){
			for(int j = 0; j < ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(1)).get(1)).get(0)).get(1)).get(0)).get(1)).get(i)).get(1)).size(); j += 2){
				set.add(((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(1)).get(1)).get(0)).get(1)).get(0)).get(1)).get(i)).get(1)).get(j));
			}
		}
		return set;
	}
	//if a tfe is forest, it must have a {}.
	//if tfe has many {}, ignore inner {}
	private int checkForest(ExtList tfe) {
		// TODO 自動生成されたメソッド・スタブ
		if(((ExtList)tfe.get(1)).get(0).equals("{")){
			//count contents
			//contents are combined horizontally or vertically
			//1110101.size -> v_exp
			//111010i01.size -> h_exp
			int vertical_num = ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(1)).get(1)).get(0)).get(1)).get(0)).get(1)).size();
			int tree_num = 0;
			for(int i = 0; i < (vertical_num / 2 + 1); i++){
				tree_num += ((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(1)).get(1)).get(0)).get(1)).get(0)).get(1)).get(2 * i)).get(1)).size() / 2 + 1;
			}
			if(tree_num != 1){
				return tree_num;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}

	private ExtList addNull(ExtList tfe, int flag) {
		// TODO 自動生成されたメソッド・スタブ
		if(tfe.get(0).equals("operand")){
			if(((ExtList)tfe.get(1)).get(0).equals("{")){
				ExtList tmp = new ExtList();
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				tmp = addNull((ExtList)((ExtList)tfe.get(1)).get(1), 1);
				tmp2.add("operand");
				tmp3.add("{");
				tmp3.add(tmp);
				tmp3.add("}");
				tmp2.add(tmp3);
				return tmp2;
			}else if(((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("sorting") || ((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("sl")){
				ExtList tmp = new ExtList();				
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				ExtList tmp4 = new ExtList();
				ExtList tmp5 = new ExtList();
				ExtList tmp6 = new ExtList();
				ExtList tmp7 = new ExtList();
				ExtList tmp8 = new ExtList();
				ExtList tmp9 = new ExtList();
				ExtList tmp10 = new ExtList();
				tmp.add("null");
				tmp9.add("any_name");
				tmp2.add("keyword");
				tmp2.add(tmp);
				tmp3.add(tmp2);
				tmp9.add(tmp3);
				tmp10.add(tmp9);
				tmp4.add("function_name");
				tmp4.add(tmp10);
				tmp5.add(tmp4);
				tmp5.add("(");
				tmp5.add(tfe);
				tmp5.add(")");
				tmp6.add("function");
				tmp6.add(tmp5);
				tmp7.add("operand");
				tmp8.add(tmp6);
				tmp7.add(tmp8);
				return tmp7;
			}else if(((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("function")){
				ExtList tmp = new ExtList();
				tmp = addNull(((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(0)).get(1)).get(2)), 0);
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				ExtList tmp4 = new ExtList();
				ExtList tmp5 = new ExtList();
				tmp2.add("operand");
				tmp3.add(((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(0)).get(1)).get(0)));
				tmp3.add("(");
				tmp3.add(tmp);
				tmp3.add(")");
				tmp4.add("function");
				tmp4.add(tmp3);
				tmp5.add(tmp4);
				tmp2.add(tmp5);
				return tmp2;
			}else{
				ExtList tmp = new ExtList();
				tmp.add("operand");
				tmp.add(addNull((ExtList)tfe.get(1), 1));
				return tmp;
			}
		}else if(tfe.get(0).equals("{")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			tmp.add(tfe.get(0));
			tmp.add(addNull((ExtList)tfe.get(1), 1));
			tmp.add("}");
			return tmp;
		}else if(tfe.get(0).equals("grouper")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			tmp.add("[");
			tmp.add(addNull((ExtList)((ExtList)tfe.get(1)).get(1), 0));
			tmp.add("]");
			tmp.add(",");
			tmp2.add("grouper");
			tmp2.add(tmp);
			return tmp2;
		}else if(tfe.get(0).equals("exp")){
			ExtList tmp = new ExtList();
			tmp.add("exp");
			tmp.add(addNull((ExtList)tfe.get(1), 1));
			return tmp;
		}else if(tfe.get(0).equals("d_exp") || tfe.get(0).equals("v_exp") || tfe.get(0).equals("h_exp")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			int child_num = 0;
			child_num = ((ExtList)tfe.get(1)).size() / 2 + 1;
			for(int i = 0; i < child_num; i++){
				if(i != 0){
					tmp.add(((ExtList)tfe.get(1)).get(2 * i - 1));
				}
				tmp.add(addNull((ExtList)((ExtList)tfe.get(1)).get(2 * i), 0));
			}
			tmp2.add(tfe.get(0));
			tmp2.add(tmp);
			return tmp2;
		}else{
			ExtList tmp = new ExtList();
			if(flag == 0){
				tmp.add(tfe.get(0));
				tmp.add(addNull((ExtList)tfe.get(1), 1));
			}else if(flag == 1){
				tmp.add(addNull((ExtList)tfe.get(0), 0));
			}
			return tmp;
		}
	}

	private ExtList addSort(ExtList tfe, int flag) {
		// TODO 自動生成されたメソッド・スタブ
		if(tfe.get(0).equals("operand")){
			if(((ExtList)tfe.get(1)).get(0).equals("{")){
				ExtList tmp = new ExtList();
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				tmp = addSort((ExtList)((ExtList)tfe.get(1)).get(1), 1);
				tmp2.add("operand");
				tmp3.add("{");
				tmp3.add(tmp);
				tmp3.add("}");
				tmp2.add(tmp3);
				return tmp2;
			}else if(((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("sorting") || ((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("sl")){
				return tfe;
			}else if(((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("function")){
				ExtList tmp = new ExtList();
				tmp = addSort(((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(0)).get(1)).get(2)), 0);
				ExtList tmp2 = new ExtList();
				ExtList tmp3 = new ExtList();
				ExtList tmp4 = new ExtList();
				ExtList tmp5 = new ExtList();
				tmp2.add("operand");
				tmp3.add(((ExtList)((ExtList)((ExtList)((ExtList)tfe.get(1)).get(0)).get(1)).get(0)));
				tmp3.add("(");
				tmp3.add(tmp);
				tmp3.add(")");
				tmp4.add("function");
				tmp4.add(tmp3);
				tmp5.add(tmp4);
				tmp2.add(tmp5);
				return tmp2;
			}else if(((ExtList)((ExtList)tfe.get(1)).get(0)).get(0).equals("attribute")){
				ExtList tmp = new ExtList();
				ExtList tmp2 = new ExtList();
				tmp.add("(");
				tmp.add("asc");
				tmp.add(")");
				tmp2.add("sorting");
				tmp2.add(tmp);
				((ExtList)tfe.get(1)).add(0, tmp2);
				return tfe;
			}else{
				ExtList tmp = new ExtList();
				tmp.add("operand");
				tmp.add(addSort((ExtList)tfe.get(1), 1));
				return tmp;
			}
		}else if(tfe.get(0).equals("{")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			tmp.add(tfe.get(0));
			tmp.add(addSort((ExtList)tfe.get(1), 1));
			tmp.add("}");
			return tmp;
		}else if(tfe.get(0).equals("grouper")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			tmp.add("[");
			tmp.add(addSort((ExtList)((ExtList)tfe.get(1)).get(1), 0));
			tmp.add("]");
			tmp.add(((ExtList)tfe.get(1)).get(3));
			tmp2.add("grouper");
			tmp2.add(tmp);
			return tmp2;
		}else if(tfe.get(0).equals("exp")){
			ExtList tmp = new ExtList();
			tmp.add("exp");
			tmp.add(addSort((ExtList)tfe.get(1), 1));
			return tmp;
		}else if(tfe.get(0).equals("d_exp") || tfe.get(0).equals("v_exp") || tfe.get(0).equals("h_exp")){
			ExtList tmp = new ExtList();
			ExtList tmp2 = new ExtList();
			int child_num = 0;
			child_num = ((ExtList)tfe.get(1)).size() / 2 + 1;
			for(int i = 0; i < child_num; i++){
				if(i != 0){
					tmp.add(((ExtList)tfe.get(1)).get(2 * i - 1));
				}
				tmp.add(addSort((ExtList)((ExtList)tfe.get(1)).get(2 * i), 0));
			}
			tmp2.add(tfe.get(0));
			tmp2.add(tmp);
			return tmp2;
		}else{
			ExtList tmp = new ExtList();
			if(flag == 0){
				tmp.add(tfe.get(0));
				tmp.add(addSort((ExtList)tfe.get(1), 1));
			}else if(flag == 1){
				tmp.add(addSort((ExtList)tfe.get(0), 0));
			}
			return tmp;
		}
	}
}
