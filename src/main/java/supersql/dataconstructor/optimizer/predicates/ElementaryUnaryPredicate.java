package supersql.dataconstructor.optimizer.predicates;

import supersql.dataconstructor.optimizer.attributes.Attribute;

/**
 * Class defining elementary predicates that involves only one attribute.
 * @author arnaudwolf
 *
 */
public class ElementaryUnaryPredicate extends ElementaryPredicate {
	private Attribute operand;
	
	/**
	 * Constructs an elementary unary predicate from its text representation and operand
	 * @param text: the text representation of this predicate
	 * @param operand: the attribute involved in this predicate
	 */
	public ElementaryUnaryPredicate(String text, Attribute operand){
		originalText = text;
		this.operand = operand;
		String alias = operand.getAlias();
		textAfterMaterialization = (new String(text)).replace(operand.getRetrievalRepresentation(), alias);
	}
	
	/**
	 * Returns the operand of this elementary predicate
	 * @return the operand of this elementary predicate
	 */
	public Attribute getOperand(){
		return operand;
	}
	
	
	public boolean equals(Object o){
		if(o instanceof ElementaryUnaryPredicate){
			ElementaryUnaryPredicate eup = (ElementaryUnaryPredicate) o;
			return eup.originalText.equals(this.originalText)
					&& eup.operand.equals(this.operand);
		}
		else return false;
	}
	
	public int hashCode(){
		return operand.hashCode();
	}
}
