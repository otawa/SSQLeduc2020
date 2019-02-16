package supersql.dataconstructor.optimizer.predicates;

/**
 * Class defining the elementary predicates. Elementary predicates are the atomic elements of polynomial predicates. 
 * They can involve either one (unary predicates) or two (binary predicates) attributes
 * Two elementary predicates are said to be equal if they have the same text representation
 * @author arnaudwolf
 *
 */
public class ElementaryPredicate {
	protected String originalText;
	protected String textAfterMaterialization;
	
	public String getOriginalText(){
		return originalText;
	}
	
	public String getTextAfterMaterialization(){
		return textAfterMaterialization;
	}
	
	public String toString(){
		return textAfterMaterialization;
	}
}
