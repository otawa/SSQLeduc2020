package supersql.dataconstructor.optimizer.predicates;

import supersql.dataconstructor.optimizer.nodes.Node;

/**
 * Class defining predicates that involves elementary predicates involving tables of a single branch
 * @author arnaudwolf
 *
 */
public class UnaryPredicate extends Predicate{
	private Node node;
	
	/**
	 * Constructs an empty binary predicate
	 * @param branch
	 */
	public UnaryPredicate(Node node){
		super();
		this.node = node;
	}
	
	/**
	 * Get the branch of this unary predicate
	 * @return the branch of this unary predicate
	 */
	public Node getNode(){
		return node;
	}
}
