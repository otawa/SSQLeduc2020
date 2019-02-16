package supersql.dataconstructor.optimizer.predicates;

import supersql.dataconstructor.optimizer.attributes.Attribute;
import supersql.dataconstructor.optimizer.attributes.AttributePair;
/**
 * Class defining elementary predicates that involves only one attribute.
 * Those predicates may be used during full reduction
 * @author arnaudwolf
 *
 */
public class ElementaryBinaryPredicate extends ElementaryPredicate {
	private Attribute operand1, operand2;
	private boolean createdFromDuplication;
	
	/**
	 * Constructs an elementary binary predicate from its text representation and operands
	 * @param text: the text representation of this predicate
	 * @param operand1: the first attribute involved in this predicate
	 * @param operand2: the second attribute involved in this predicate
	 */
	public ElementaryBinaryPredicate(String text, Attribute operand1, Attribute operand2){
		originalText = text;
		this.operand1 = operand1;
		this.operand2 = operand2;
		createdFromDuplication = false;
		textAfterMaterialization = (new String(text)).replace(operand1.getRetrievalRepresentation(), operand1.getAlias()).replace(operand2.getRetrievalRepresentation(), operand2.getAlias());
	}
	
	/**
	 * Returns the first operand of this predicate
	 * @return the first operand of this predicate
	 */
	public Attribute getOperand1(){
		return operand1;
	}
	
	/**
	 * Returns the second operand of this predicate
	 * @return the second operand of this predicate
	 */
	public Attribute getOperand2(){
		return operand2;
	}
	
	/**
	 * Returns true if this predicate has been created as an equality predicate in case of table duplication
	 */
	public boolean createdFromDuplication(){
		return createdFromDuplication;
	}
	
	/**
	 * Returns the predicate "operand1=operand2"
	 * @param operand1
	 * @param operand2
	 * @return the predicate "operand1=operand2"
	 */
	public static ElementaryBinaryPredicate equalityPredicate(Attribute operand1, Attribute operand2){
		String text = operand1.toString() + "=" + operand2.toString();
		ElementaryBinaryPredicate result = new ElementaryBinaryPredicate(text, operand1, operand2);
		result.createdFromDuplication = true;
		return result;
	}
	
	public boolean equals(Object o){
		if(o instanceof ElementaryBinaryPredicate){
			ElementaryBinaryPredicate ebp = (ElementaryBinaryPredicate) o;
			AttributePair compPair = new AttributePair(ebp.operand1, ebp.operand2), thisPair = new AttributePair(operand1, operand2);
			
			return ebp.originalText.equals(originalText) && compPair.equals(thisPair);
			
		}
		else return false;
	}
	
	public int hashCode(){
		return operand1.hashCode() + operand2.hashCode();
	}
}
