package supersql.dataconstructor.optimizer.querygraph;

import java.util.ArrayList;
import java.util.Hashtable;

import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.predicates.BinaryPredicate;

public class QueryTree {
	private Node root;
	private ArrayList<QueryTree> children;
	private Hashtable<QueryTree, BinaryPredicate> childrenBinaryPredicate;
	private QueryTree parent;
	private BinaryPredicate parentBinaryPredicate;
	
	public QueryTree(Node node, QueryTree parent, BinaryPredicate parentPredicate){
		root = node;
		children = new ArrayList<QueryTree>();
		childrenBinaryPredicate = new Hashtable<QueryTree, BinaryPredicate>();
		this.parent = parent;
		parentBinaryPredicate = parentPredicate;
	}
	
	public QueryTree addChild(QueryTree child, BinaryPredicate bp){
		children.add(child);
		childrenBinaryPredicate.put(child, bp);
		return this;
	}
	
	public Node getRoot(){
		return root;
	}
	
	public BinaryPredicate getChildPredicate(QueryTree child){
		if(childrenBinaryPredicate.containsKey(child))
			return childrenBinaryPredicate.get(child);
		else return null;
	}
	
	public ArrayList<QueryTree> getChildren(){
		return children;
	}
	
	public QueryTree getParent(){
		return parent;
	}
	
	public BinaryPredicate getParentPredicate(){
		return parentBinaryPredicate;
	}
	
	public boolean isRootNode(){
		return parent == null;
	}
	
	public boolean isLeafNode(){
		return children.isEmpty();
	}
	
	public String toString(){
		return textRepresentation();
	}
	
	private String textRepresentation(){
		String content = root.toString();
		for(QueryTree child: children){
			content += ", " + child.textRepresentation();
		}
		
		return "[" + content + "]";
	}
}
