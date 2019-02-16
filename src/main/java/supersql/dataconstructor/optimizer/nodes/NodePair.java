package supersql.dataconstructor.optimizer.nodes;

public class NodePair {
	private Node node1, node2;
	
	public NodePair(Node node1, Node node2){
		this.node1 = node1;
		this.node2 = node2;
	}
	
	public Node getNode1(){
		return node1;
	}
	
	public Node getNode2(){
		return node2;
	}
	
	
	public boolean equals(Object o){
		if(o instanceof NodePair){
			NodePair bp = (NodePair) o;
			return (bp.node1.equals(node1) && bp.node2.equals(node2))
					|| (bp.node1.equals(node2) && bp.node2.equals(node1));
		}
		else return false;
	}
	
	public int hashCode(){
		return node1.hashCode() + node2.hashCode();
	}
	
	public String toString(){
		return "(" + node1.toString() + ", " + node2.toString() + ")";
	}
}
