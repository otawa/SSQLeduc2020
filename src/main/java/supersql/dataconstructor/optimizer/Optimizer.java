package supersql.dataconstructor.optimizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import supersql.dataconstructor.optimizer.attributes.TfeAttribute;
import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.nodes.NodeManager;
import supersql.dataconstructor.optimizer.predicates.Predicate;
import supersql.dataconstructor.optimizer.predicates.PredicateManager;
import supersql.dataconstructor.optimizer.querygraph.QueryGraph;
import supersql.dataconstructor.optimizer.querymaker.QueryMaker;
import supersql.dataconstructor.optimizer.tables.Table;

public class Optimizer {
	//Inputs
	Collection<TfeAttribute> tfeAttributes;
	Collection<Table> fromClauseTables;
	Predicate whereClausePredicate;
	
	//Intermediate variables
	NodeManager nodeManager;
	PredicateManager predicateManager;
	QueryGraph queryGraph;
	QueryMaker queryMaker;
	
	public Optimizer(Collection<TfeAttribute> tfeAttributes, Collection<Table> fromClauseTables, Predicate whereClausePredicate){
		this.tfeAttributes = tfeAttributes;
		this.fromClauseTables = fromClauseTables;
		this.whereClausePredicate = whereClausePredicate;
		this.nodeManager = new NodeManager();
		this.predicateManager = new PredicateManager();
	}
	
	public void optimize(){
		nodeManager.initNodesAndTables(tfeAttributes, fromClauseTables);
		boolean existsCycles = true;
	
		while(existsCycles){
			predicateManager.initPredicate(whereClausePredicate, fromClauseTables);
			predicateManager.factorizePredicate();
			List<Node> nodes = nodeManager.getNodes();
			queryGraph = new QueryGraph(nodes, predicateManager.getBinaryPredicates());
			
			if(queryGraph.detectCycle()){
				manageCycles();
			}
			else{
				existsCycles = false;
				queryMaker = new QueryMaker(nodeManager.getNodes(), predicateManager.getUnaryPredicates(), predicateManager.getBinaryPredicates(), queryGraph);
				queryMaker.makeQueries();
			}
		}
	}
	
	public Hashtable<Node, String> getDirectQueries(){
		return queryMaker.getDirectQueries();
	}
	
	public ArrayList<String> getMaterializationQueries(){
		return queryMaker.getMaterializationQueries();
	}
	
	public ArrayList<ArrayList<String>> getFullReducerQueries(){
		return queryMaker.getFullReducerQueries();
	}
	
	public Hashtable<Node, String> getRetrievalQueries(){
		return queryMaker.getRetrievalQueries();
	}
	
	private void manageCycles(){
		List<List<Node>> cycles = queryGraph.getCycleBase();
		
		for(List<Node> cycle: cycles){
			nodeManager.fusionNodes(cycle);
		}
		return;
	}
}
