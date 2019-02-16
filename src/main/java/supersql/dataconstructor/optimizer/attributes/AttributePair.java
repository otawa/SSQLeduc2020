package supersql.dataconstructor.optimizer.attributes;

public class AttributePair {
	private Attribute att1, att2;
	public AttributePair(Attribute a1, Attribute a2){
		att1 = a1;
		att2 = a2;
	}
	
	public boolean equals(Object o){
		if(o instanceof AttributePair){
			AttributePair atts = (AttributePair) o;
			
			return (atts.att1.equals(att1) && atts.att2.equals(att2))
					|| (atts.att1.equals(att2) && atts.att2.equals(att1));
		}
		
		else return false;
	}
	
	public int hashCode(){
		return att1.hashCode() + att2.hashCode();
	}
}
