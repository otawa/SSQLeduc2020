package supersql.dataconstructor;

public class ByColumn implements java.util.Comparator {

	public int compare(Object a, Object b) {
		
		if ( ( (Attribute) a ).getColumn() < ( (Attribute) b ).getColumn() )
			return -1;
		else return 1;
	}
	
} 