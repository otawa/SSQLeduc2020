package supersql.codegenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import supersql.common.Log;

public class DecorateList extends Hashtable<String, Object> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static int classIdPointer = 1;
	private Hashtable<String, Object> conditions = new Hashtable<String, Object>();
	private Hashtable<String, Integer> classesIds = new Hashtable<String, Integer>();

	public String getStr(String s) {
		Object o = this.get(s);
		if (o instanceof String) {
			String ret = (String) (this.get(s));
			if (ret.startsWith("\"") && ret.endsWith("\"")) {
				ret = ret.substring(1, ret.length() - 1);
			}
			return ret;
		}
		return null;
	}

	public ITFE getTFE(String s) {
		Object o = this.get(s);
		if (o instanceof ITFE)
			return (ITFE) (this.get(s));
		return null;
	}

	public void debugout(int count) {
		if (this.size() > 0) {

			Debug dbgout = new Debug();
			dbgout.prt(count, "<DecorateList>");
			Enumeration<String> e = this.keys();
			while (e.hasMoreElements()) {
				String key = (String) (e.nextElement());
				Object val = this.get(key);
				if (val == null) {
					dbgout.prt(count + 1, "<Deco Key=" + key + "/>");
				} else if (val instanceof String) {
					// start oka
					if (key.equals("update")) {
						Log.out("@ update found @");
						Connector.updateFlag = true;
					} else if (key.equals("insert")) {
						Log.out("@ insert found @");
						Connector.insertFlag = true;
					} else if (key.equals("delete")) {
						Log.out("@ delete found @");
						Connector.deleteFlag = true;
					} else if (key.equals("login")) {
						Log.out("@ login found @");
						Connector.loginFlag = true;
					} else if (key.equals("logout")) {
						Log.out("@ logout found @");
						Connector.logoutFlag = true;
					}
					// end oka

					dbgout.prt(count + 1, "<Deco Key=" + key
							+ " type=value value=" + val + "/>");

				} else if (val instanceof ITFE) {
					dbgout.prt(count + 1, "<Deco Key=" + key + " type=TFE>");
					((ITFE) val).debugout(count + 2);
					dbgout.prt(count + 1, "</Deco>");
				} else {
					dbgout.prt(count + 1, "<Deco Key=" + key + ">");
					dbgout.prt(count + 2, val.toString());
					dbgout.prt(count + 1, "</Deco>");
				}
			}

			dbgout.prt(count, "</DecorateList>");
		}
	}

	public synchronized Object put(Object key, Object value, String condition) {
		if (getConditions().containsKey(condition)) {
			Object cond = getConditions().get(condition);
			ArrayList<String> conditionArray = new ArrayList<String>();
			if (cond instanceof String) {
				conditionArray.add((String) (key));
				conditionArray.add((String) cond);
			} else {
				((ArrayList<String>) cond)
						.addAll((Collection<? extends String>) cond);
			}
			getConditions().put(condition, conditionArray);
		} else {
			getConditions().put(condition, (String) key);
		}
		if (!getClassesIds().containsKey(condition))
			getClassesIds().put((String) condition, classIdPointer++);

		if (this.containsKey(key)) {
			String[] valueArray = new String[2];
			if (condition.startsWith("!")) {
				valueArray[1] = (String) value;
				valueArray[0] = (String) this.get(key);
			} else {
				valueArray[0] = (String) value;
				valueArray[1] = (String) this.get(key);
			}

			return super.put((String) key, valueArray);
		} else {
			return super.put((String) key, value);
		}
	}

	public Hashtable<String, Object> getConditions() {
		return conditions;
	}

	public int getConditionsSize() {
		int result = 0;
		for (String val : conditions.keySet()) {
			if (!val.startsWith("!"))
				result++;
		}
		return result;
	}

	public void setConditions(Hashtable<String, Object> conditions) {
		this.conditions = conditions;
	}

	public Hashtable<String, Integer> getClassesIds() {
		return classesIds;
	}

	public void setClassesIds(Hashtable<String, Integer> classesIds) {
		this.classesIds = classesIds;
	}

	public String getDecorationValueFromDecorationKeyAndCondition(
			String decorationKey, String condition) {
		Object decorationValue = this.get(decorationKey);
		if (decorationValue instanceof String[]) {
			if (condition.startsWith("!")) {
				this.put(decorationKey, ((String[]) (decorationValue))[0]);
				decorationValue = ((String[]) (decorationValue))[1];
			} else {
				this.put(decorationKey, ((String[]) (decorationValue))[1]);
				decorationValue = ((String[]) (decorationValue))[0];
			}
		} else {
			this.remove(decorationKey);
		}
		return (String) decorationValue;
	}

	public void alias(String oldKey, String newKey) {
		if (containsKey(oldKey)) {
			put(newKey, getStr(oldKey));
			remove(oldKey);
		}
	}

}
