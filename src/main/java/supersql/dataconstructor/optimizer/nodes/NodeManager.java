package supersql.dataconstructor.optimizer.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import supersql.dataconstructor.optimizer.attributes.TfeAttribute;
import supersql.dataconstructor.optimizer.attributes.TfePath;
import supersql.dataconstructor.optimizer.tables.OptimizerTable;
import supersql.dataconstructor.optimizer.tables.Table;

public class NodeManager {
	private ArrayList<Node> nodes;
	
	public NodeManager(){
		nodes = new ArrayList<Node>();
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public void initNodesAndTables(Collection<TfeAttribute> tfeAttributes, Collection<Table> fromClauseTables){
		//Initialize the map path attribute
		Hashtable<TfePath, TfeAttribute> mapPathAtt = new Hashtable<TfePath, TfeAttribute>();
		for(TfeAttribute att: tfeAttributes){
			for(TfePath path: att.getTfePaths())
				mapPathAtt.put(path, att);
		}
		
		//Order attributes by size
		Hashtable<Integer, ArrayList<TfePath>> pathsBySize = new Hashtable<Integer, ArrayList<TfePath>>();
		int sizeMax = 0;
		for(TfePath path: mapPathAtt.keySet()){
			int size = path.size();
			if(size > sizeMax)
				sizeMax = size;
			if(pathsBySize.containsKey(size))
				pathsBySize.get(size).add(path);
			else{
				ArrayList<TfePath> newList = new ArrayList<TfePath>();
				newList.add(path);
				pathsBySize.put(size, newList);
			}
		}
		
		//Build the branches
		Hashtable<TfePath, Node> mapLeafPathNode = new Hashtable<TfePath, Node>();
		for(int size=sizeMax; size>=1; size--){
			if(pathsBySize.containsKey(size)){
				for(TfePath path: pathsBySize.get(size)){
					Table table = mapPathAtt.get(path).getTable();
					boolean foundAtLeastOneNode = false;
					for(TfePath leafPath: mapLeafPathNode.keySet()){
						if(leafPath.containsPath(path)){
							foundAtLeastOneNode = true;
							duplicateTableInNode(table, mapLeafPathNode.get(leafPath));
						}
					}
					
					if(!foundAtLeastOneNode){
						Node newNode = newNode();
						duplicateTableInNode(table, newNode);
						mapLeafPathNode.put(path, newNode);
					}
				}
			}
		}
		
		//Add external branches
		for(Table table: fromClauseTables){
			if(table.isExternalTable()){
				Node newNode = newNode();
				duplicateTableInNode(table, newNode);
			}
		}
	}
	
	public void fusionNodes(List<Node> nodeList){
		ArrayList<Node> nodeListCopy = new ArrayList<Node>(nodeList);
		if(nodeListCopy.size()>1){
			
			Node newNode = nodeListCopy.get(0);
			for(int i=1; i<nodeListCopy.size(); i++){
				newNode = unionNodes(newNode, nodeListCopy.get(i));
			}
			
			for(Node node: nodeListCopy){
				nodes.remove(node);
			}
			nodes.add(newNode);
		}
	}
	
	public void fusionAllNodes(){
		fusionNodes(nodes);
	}
	
	private Node newNode(){
		Node node = new Node();
		nodes.add(node);
		return node;
	}
	
	private void duplicateTableInNode(Table table, Node node){
		if(node != null){
			if(!node.containsOriginalTable(table)){
				OptimizerTable duplicatedTable = new OptimizerTable(table, node);
				node.addTable(duplicatedTable);
				table.addDuplicatedTable(duplicatedTable);
			}
		}
	}	
	
	private Node unionNodes(Node node1, Node node2){
		
		Node newNode = new Node();
		for(OptimizerTable table: node1.getTables()){
			newNode.addTable(table);
			table.setNode(newNode);
			Table originalTable = table.getOriginalTable();
			originalTable.removeNode(node1).addNode(newNode);
		}
		
		for(OptimizerTable table: node2.getTables()){
			Table originalTable = table.getOriginalTable();
			if(newNode.containsOriginalTable(originalTable)){
				originalTable.removeDuplicatedTable(table);
				originalTable.removeNode(node2);
			}
			else {
				newNode.addTable(table);
				table.setNode(newNode);
				originalTable.removeNode(node2).addNode(newNode);
			}
			
		}
		
		return newNode;
	}
}
