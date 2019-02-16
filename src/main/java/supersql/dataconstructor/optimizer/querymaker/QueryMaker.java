package supersql.dataconstructor.optimizer.querymaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.UUID;

import supersql.dataconstructor.optimizer.attributes.Attribute;
import supersql.dataconstructor.optimizer.attributes.TfeAttribute;
import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.nodes.NodePair;
import supersql.dataconstructor.optimizer.predicates.BinaryPredicate;
import supersql.dataconstructor.optimizer.predicates.ElementaryBinaryPredicate;
import supersql.dataconstructor.optimizer.predicates.ElementaryPredicate;
import supersql.dataconstructor.optimizer.predicates.ElementaryUnaryPredicate;
import supersql.dataconstructor.optimizer.predicates.UnaryPredicate;
import supersql.dataconstructor.optimizer.querygraph.QueryGraph;
import supersql.dataconstructor.optimizer.querygraph.QueryTree;
import supersql.dataconstructor.optimizer.tables.OptimizerTable;

public class QueryMaker {
	private ArrayList<Node> nodes;
	private Hashtable<Node, UnaryPredicate> unaryPredicates;
	private Hashtable<NodePair, BinaryPredicate> binaryPredicates;
	private QueryGraph queryGraph;
	
	private Hashtable<Node, String> directQueries;
	private ArrayList<String> materializationQueries;
	private ArrayList<ArrayList<String>> fullReducerQueries;
	private Hashtable<Node, String> retrievalQueries;
	
	private Hashtable<Node, String> tmpTables;
	private HashSet<Attribute> requiredAttributes;
	private ArrayList<QueryTree> queryTrees;
	
	public QueryMaker(ArrayList<Node> nds, Hashtable<Node, UnaryPredicate> ups, Hashtable<NodePair, BinaryPredicate> bps, QueryGraph qg){
		nodes = nds;
		unaryPredicates = ups;
		binaryPredicates = bps;
		queryGraph = qg;
		
		directQueries = new Hashtable<Node, String>();
		materializationQueries = new ArrayList<String>();
		fullReducerQueries = new ArrayList<ArrayList<String>>();
		retrievalQueries = new Hashtable<Node, String>();
		
		tmpTables = new Hashtable<Node, String>();
		requiredAttributes = new HashSet<Attribute>();
		queryTrees = queryGraph.getQueryTrees();
	}
	
	public void makeQueries(){
		getRequiredAttributes();
		makeDirectAndMaterializationQueries();
		makeFullReducerQueries();
	}
	
	public Hashtable<Node, String> getDirectQueries(){
		return directQueries;
	}
	
	public ArrayList<String> getMaterializationQueries(){
		return materializationQueries;
	}
	
	public ArrayList<ArrayList<String>> getFullReducerQueries(){
		return fullReducerQueries;
	}
	
	public Hashtable<Node, String> getRetrievalQueries(){
		return retrievalQueries;
	}
	
	private void getRequiredAttributes(){
		for(Node node: nodes){
			for(OptimizerTable table: node.getTables()){
				for(Attribute att: table.getAttributes()){
					if(att instanceof TfeAttribute)
						requiredAttributes.add(att);
				}
			}
		}
		
		for(BinaryPredicate bp: binaryPredicates.values()){
			for(ElementaryPredicate ep: bp){
				if(ep instanceof ElementaryUnaryPredicate)//not supposed to occur
					requiredAttributes.add(((ElementaryUnaryPredicate) ep).getOperand());
				else if(ep instanceof ElementaryBinaryPredicate){
					requiredAttributes.add(((ElementaryBinaryPredicate) ep).getOperand1());
					requiredAttributes.add(((ElementaryBinaryPredicate) ep).getOperand2());
				}
			}
		}
	}
	
	private void makeDirectAndMaterializationQueries(){
		for(Node node: nodes){
			String query = "";
			boolean toBeMaterialized = queryGraph.toBeMaterialized(node);
			if(toBeMaterialized)
				query += getCreateClause(node);
			query += getSelectClause(node) + getFromClause(node) + getWhereClauseBeforeMaterialize(node);
			
			if(toBeMaterialized)
				materializationQueries.add(query);
			else if(!node.isExternalNode()) 
				directQueries.put(node, query);
		}
	}
	
	private void makeFullReducerQueries(){
		for(QueryTree tree: queryTrees){
			if(!tree.isLeafNode())
				makeFullReducerQueries(tree);
		}
	}
	
	private void makeFullReducerQueries(QueryTree tree){
		ArrayList<String> queries = new ArrayList<String>();
		fullReducerQueries.add(queries);
		makeDescendingReducerQueries(tree, queries);
		makeAscendingReducerQueries(tree, queries);

	}
	
	private void makeDescendingReducerQueries(QueryTree tree, ArrayList<String> queries){
		for(QueryTree child: tree.getChildren()){
			if(!child.isLeafNode())
				makeDescendingReducerQueries(child, queries);
			makeReducerQuery(tree.getRoot(), child.getRoot(), queries);
		}
	}
	
	private void makeAscendingReducerQueries(QueryTree tree, ArrayList<String> queries){
		Node node = tree.getRoot();
		if(!tree.isLeafNode()){
			
			if(!node.isExternalNode())
				makeRetrievalAfterMaterializationQuery(node);
			if(!tree.isRootNode())
				makeReducerQuery(node, tree.getParent().getRoot(), queries);
			for(QueryTree child: tree.getChildren())
				makeAscendingReducerQueries(child, queries);
		}
		else if(!tree.isRootNode())
			makeLeafRetrievalQuery(node, tree.getParent().getRoot());
		else if(!node.isExternalNode()) //not supposed to happen
			makeRetrievalAfterMaterializationQuery(tree.getRoot()); 
	}
	
	private String getCreateClause(Node node){
		String tmpTableName = UUID.randomUUID().toString();
		tmpTables.put(node, tmpTableName);
		
		return "CREATE TEMPORARY TABLE " + tmpTableName + " AS ";
	}
	
	private String getSelectClause(Node node){
		String clause = "SELECT ";
		
		boolean beginning = true;
		for(OptimizerTable table: node.getTables()){
			if(beginning)
				beginning = false;
			else clause += ", ";
			clause += listAttributes(table);
		}
		
		return clause;
	}
	
	private String getFromClause(Node node){
		String clause = " FROM ";
		
		int i=1;
		for(OptimizerTable table: node.getTables()){
			clause += table.toString();
			if(i<node.size())
				clause += ", ";
			i++;
		}
			
		return clause;
	}
	
	private String getWhereClauseBeforeMaterialize(Node node){
		String clause = "";
		
		if(unaryPredicates.containsKey(node))
			clause += " WHERE " + unaryPredicates.get(node).getStringRepresentationBeforeMaterialize();
		return clause;
	}
	
	private void makeReducerQuery(Node root, Node target, ArrayList<String> queries){
		String tmpTableName = tmpTables.get(root);
		String query = "DELETE FROM " + tmpTableName + getWhereClauseAfterMaterialize(root, target);
		queries.add(query);
	}
	
	private void makeLeafRetrievalQuery(Node root, Node target){
		String query =  getRetrievalAfterMaterializationQuery(root) + getWhereClauseAfterMaterialize(root, target);
		retrievalQueries.put(root, query);
	}
	
	private String getWhereClauseAfterMaterialize(Node node1, Node node2){
		NodePair nodePair = new NodePair(node1, node2);
		BinaryPredicate bp = binaryPredicates.get(nodePair);
		return " WHERE " + bp.getStringRepresentationAfterMaterialize();
	}
	
	private void makeRetrievalAfterMaterializationQuery(Node node){
		retrievalQueries.put(node, getRetrievalAfterMaterializationQuery(node));
	}
	
	private String getRetrievalAfterMaterializationQuery(Node node){
		String select = "SELECT ";
		
		int i=0;
		for(OptimizerTable table: node.getTables()){
			if(i>0)
				select += ", ";
			ArrayList<Attribute> tfeAttributes = table.getTfeAttributes();
			for(int j=0; j<tfeAttributes.size(); j++){
				if(j>0)
					select += ", ";
				Attribute att = tfeAttributes.get(j);
				select += att.getAlias();
			}
			
			i++;
		}
		
		String from = "FROM " + tmpTables.get(node);
		
		return select + " " + from;
	}
	
	private String listAttributes(OptimizerTable table){
		String list = "";
		
		boolean beginning = true;
		for(Attribute att: table.getAttributes()){
			if(requiredAttributes.contains(att)){
				if(beginning)
					beginning = false;
				else list += ", ";
				list += att.getRetrievalRepresentation() + " AS " + att.getAlias();
			}
		}
		
		return list;
	}
}
