package supersql.extendclass;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.runtime.tree.RewriteRuleNodeStream;

import supersql.common.Log;

public class ExtList<T> extends ArrayList<T> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ExtList() {
		super();
	}

	public ExtList(Collection<T> c) {
		super(c);
	}

	public ExtList<T> ExtsubList(int fromIndex, int toIndex) {
		return new ExtList<T>(this.subList(fromIndex, toIndex));
	}

	public int contain_itemnum() {
		return this.unnest().size();
	}

	public ExtList<T> unnest() {
		ExtList<T> list = new ExtList<T>();
		for (int i = 0; i < this.size(); i++) {
			T o = this.get(i);
			if (o instanceof ExtList) {
					list.addAll(((ExtList<T>) o).unnest());
			} else {
				list.add((T) o);
			}
		}
		return list;
	}
	
	public ExtList getExtList(int... value_array){
		ExtList tmp = this;
		for(int i = 0; i < value_array.length; i++){
//			Log.info("tmp"+tmp);
			try{
				tmp = (ExtList)tmp.get(value_array[i]);
			}catch(ClassCastException castException){
				Log.err("the return value is not ExtList");
				castException.printStackTrace();
				return null;
			}
		}
		return tmp;
	}
	
	public String getExtListString(int... value_array){
		ExtList tmp = this;
		int length = value_array.length;
		for(int i = 0; i < length; i++){
//			Log.info("tmp"+tmp);
			if(tmp.get(value_array[i]) instanceof String){
				String return_value = tmp.get(value_array[i]).toString();
				if(i >= length - 1){
					return return_value;
				}else{
					Log.err("return value is "+return_value+".");
					break;
				}
			}else{
				try{
					tmp = (ExtList)tmp.get(value_array[i]);
				}catch(ClassCastException castException){
					castException.printStackTrace();
				}
			}
//			try{
//				tmp = (ExtList)tmp.get(value_array[i]);
//			}catch(ClassCastException castException){
//				String return_value = tmp.get(value_array[i]).toString();
//				if(i >= length - 1){
//					return return_value;
//				}else{
//					Log.err("return value is "+return_value+".");
//					castException.printStackTrace();
//					break;
//				}
//			}
		}
		Log.err("Index is wrong.");
		return null;
	}
}