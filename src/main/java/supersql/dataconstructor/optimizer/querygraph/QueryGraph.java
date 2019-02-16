package supersql.dataconstructor.optimizer.querygraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.nodes.NodePair;
import supersql.dataconstructor.optimizer.predicates.BinaryPredicate;

public class QueryGraph extends SimpleGraph<Node, DefaultEdge> {
	private static final long serialVersionUID = -3900449711437056170L;
	List<List<Node>> cycleBase;
	Hashtable<NodePair, BinaryPredicate> binaryPredicates;
	
	public QueryGraph(){
		super(DefaultEdge.class);
	}
	
	public QueryGraph(Collection<Node> nodes, Hashtable<NodePair, BinaryPredicate> binaryPredicates){
		super(DefaultEdge.class);
		this.binaryPredicates = binaryPredicates;
		for(Node node: nodes){
			addVertex(node);
		}
		
		for(NodePair nodePair: binaryPredicates.keySet()){
			addEdge(nodePair.getNode1(), nodePair.getNode2());
		}
	}
	
	public boolean detectCycle(){
		if(cycleBase == null)
			cycleBase = (new PatonCycleBase<Node, DefaultEdge>(this)).findCycleBase();
		return !cycleBase.isEmpty();
	}
	
	public List<List<Node>> getCycleBase(){
		if(cycleBase == null)
			cycleBase = (new PatonCycleBase<Node, DefaultEdge>(this)).findCycleBase();
		return cycleBase;
	}
	
	public ArrayList<QueryTree> getQueryTrees(){
		ArrayList<QueryTree> result = new ArrayList<QueryTree>();
		if(!detectCycle()){
			ArrayList<ArrayList<Node>> conComps = getConnectedComponents();
			
			for(ArrayList<Node> conComp: conComps){
				result.add(getQueryTree(conComp.get(0)));
			}
		}
		
		return result;
	}
	
	public boolean toBeMaterialized(Node node){
		return !edgesOf(node).isEmpty();
	}
	
	private QueryTree getQueryTree(Node root){
		return getQueryTree(root, null);
	}
	
	private QueryTree getQueryTree(Node root, QueryTree parent){
		QueryTree result = null;
		if(parent == null)
			result = new QueryTree(root, null, null);
		else result = new QueryTree(root, parent, getBinaryPredicate(root, parent.getRoot()));
		
		for(DefaultEdge edge: edgesOf(root)){
			Node source = getEdgeSource(edge), target = getEdgeTarget(edge);
			if(source.equals(root)){
				if(parent != null){
					if(!target.equals(parent.getRoot()))
						result.addChild(getQueryTree(target, result), getBinaryPredicate(target, root));
				}
				else result.addChild(getQueryTree(target, result), getBinaryPredicate(target, root));
			}
			else if(target.equals(root)){
				if(parent != null){
					if(!source.equals(parent.getRoot()))
						result.addChild(getQueryTree(source, result), getBinaryPredicate(source, root));
				}
				else result.addChild(getQueryTree(source, result), getBinaryPredicate(source, root));
			}
		}
		
		return result;
	}
	
	private ArrayList<ArrayList<Node>> getConnectedComponents(){
		ArrayList<ArrayList<Node>> result = new ArrayList<ArrayList<Node>>();
		ConnectivityInspector<Node, DefaultEdge> ci = new ConnectivityInspector<Node, DefaultEdge>(this);
		List<Set<Node>> conComp = ci.connectedSets();
		
		ArrayList<Node> currentComp;		
		Node[] compArray;
		for(int i=0; i<conComp.size(); i++){
			compArray = conComp.get(i).toArray(new Node[0]);
			currentComp = new ArrayList<Node>();
			
			for(int j=0; j<compArray.length; j++){
				currentComp.add(compArray[j]);
			}
			result.add(currentComp);
		}
		
		return result;
	}
	
	private BinaryPredicate getBinaryPredicate(Node node1, Node node2){
		NodePair pair = new NodePair(node1, node2);
		
		if(binaryPredicates.containsKey(pair))
			return binaryPredicates.get(pair);
		else return null;
	}
}
