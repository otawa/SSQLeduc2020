package supersql.dataconstructor.optimizer.predicates;

import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.nodes.NodePair;

/**
 * Class defining predicates that involve only attributes of two different nodees
 * @author arnaudwolf
 *
 */
public class BinaryPredicate extends Predicate {
	private Node node1, node2;
	
	/**
	 * Constructs an empty binary predicate from its nodees
	 * @param node1
	 * @param node2
	 */
	public BinaryPredicate(Node node1, Node node2){
		super();
		this.node1 = node1;
		this.node2 = node2;
	}
	
	/**
	 * Constructs an empty binary predicate from a node pair
	 * @param nodePair
	 */
	public BinaryPredicate(NodePair nodePair){
		super();
		this.node1 = nodePair.getNode1();
		this.node2 = nodePair.getNode2();
	}
	
	
	/**
	 * Returns the first node of this binary predicate
	 * @return the first node of this binary predicate
	 */
	public Node getNode1(){
		return node1;
	}
	
	
	/**
	 * Returns the second node of this binary predicate
	 * @return the second node of this binary predicate
	 */
	public Node getNode2(){
		return node2;
	}
	
	
	/**
	 * Returns the node pair of this binary predicate
	 * @return the node pair of this binary predicate
	 */
	public NodePair getNodePair(){
		return new NodePair(node1, node2);
	}
}
