package supersql.extendclass;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ExtHashSet extends HashSet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ExtHashSet union(Collection c) {

		ExtHashSet result = new ExtHashSet();
		result.addAll(this);
		result.addAll(c);

		return result;

	}

	public ExtHashSet intersection(Collection c) {

		ExtHashSet result = new ExtHashSet();
		Object o;

		Iterator i = c.iterator();
		while (i.hasNext()) {
			o = i.next();
			if (this.contains(o)) {
				result.add(o);
			}
		}

		return result;
	}

}