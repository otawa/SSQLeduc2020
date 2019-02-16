package supersql.dataconstructor.optimizer.predicates;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class defining a predicate.
 * @author arnaudwolf
 *
 */
public class Predicate extends HashSet<ElementaryPredicate> {

	public Predicate(){
		super();
	}
	
	public Predicate(Collection<ElementaryPredicate> eps) {
		super(eps);
	}
	
	public String getStringRepresentationBeforeMaterialize(){
		return getStringRepresentation(true);
	}
	
	public String getStringRepresentationAfterMaterialize(){
		return getStringRepresentation(false);
	}
	
	private String getStringRepresentation(boolean beforeMat){
		StringBuilder result = new StringBuilder();
		
		boolean begin = true;
		for(ElementaryPredicate ep: this){
			if(!begin)
				result.append(" AND ");
			else begin = false;
			String textToAppend = beforeMat? ep.getOriginalText(): ep.getTextAfterMaterialization();
			result.append("(").append(textToAppend).append(")");
		}
		
		return result.toString();
	}
}
