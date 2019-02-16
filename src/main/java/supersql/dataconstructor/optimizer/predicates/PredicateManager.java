package supersql.dataconstructor.optimizer.predicates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

import supersql.dataconstructor.optimizer.attributes.Attribute;
import supersql.dataconstructor.optimizer.nodes.Node;
import supersql.dataconstructor.optimizer.nodes.NodePair;
import supersql.dataconstructor.optimizer.tables.OptimizerTable;
import supersql.dataconstructor.optimizer.tables.Table;

public class PredicateManager {
	private Predicate predicate;
	private Hashtable<Node, UnaryPredicate> unaryPredicates;
	private Hashtable<NodePair, BinaryPredicate> binaryPredicates;
	
	public Predicate getPredicate(){
		return predicate;
	}
	
	public Hashtable<Node, UnaryPredicate> getUnaryPredicates(){
		return unaryPredicates;
	}
	
	public Hashtable<NodePair, BinaryPredicate> getBinaryPredicates(){
		return binaryPredicates;
	}
	
	public void initPredicate(Predicate whereClausePredicate, Collection<Table> fromClauseTables){
		predicate = new Predicate();
		for(ElementaryPredicate ep: whereClausePredicate)
			predicate.addAll(duplicatePredicate(ep));
		predicate.addAll(predicatesForDuplication(fromClauseTables));
		
		return;
	}
	
	public void factorizePredicate(){
		unaryPredicates = new Hashtable<Node, UnaryPredicate>();
		binaryPredicates = new Hashtable<NodePair, BinaryPredicate>();
		for(ElementaryPredicate ep: predicate){
			if(ep instanceof ElementaryUnaryPredicate){
				ElementaryUnaryPredicate eup = (ElementaryUnaryPredicate) ep;
				OptimizerTable involvedTable = (OptimizerTable) eup.getOperand().getTable();
				Node involvedNode = involvedTable.getNode();
				if(unaryPredicates.containsKey(involvedNode))
					unaryPredicates.get(involvedNode).add(eup);
				else {
					UnaryPredicate newUp = new UnaryPredicate(involvedNode);
					newUp.add(eup);
					unaryPredicates.put(involvedNode, newUp);
				}
			}
			
			else if(ep instanceof ElementaryBinaryPredicate){
				ElementaryBinaryPredicate ebp = (ElementaryBinaryPredicate) ep;
				OptimizerTable involvedTable1 = (OptimizerTable) ebp.getOperand1().getTable();
				OptimizerTable involvedTable2 = (OptimizerTable) ebp.getOperand2().getTable();
				
				Node involvedNode1 = involvedTable1.getNode();
				Node involvedNode2 = involvedTable2.getNode();
				
				if(involvedNode1.equals(involvedNode2)){
					if(unaryPredicates.containsKey(involvedNode1))
						unaryPredicates.get(involvedNode1).add(ebp);
					else {
						UnaryPredicate newUp = new UnaryPredicate(involvedNode1);
						newUp.add(ebp);
						unaryPredicates.put(involvedNode1, newUp);
					}
				}
				else{
					NodePair involvedNodes = new NodePair(involvedNode1, involvedNode2);
					
					if(binaryPredicates.containsKey(involvedNodes))
						binaryPredicates.get(involvedNodes).add(ebp);
					else{
						BinaryPredicate newBp = new BinaryPredicate(involvedNodes);
						newBp.add(ebp);
						binaryPredicates.put(involvedNodes, newBp);
					}
				}
			}
		}
		return;
	}
	
	private ArrayList<ElementaryPredicate> duplicatePredicate(ElementaryPredicate ep){
		ArrayList<ElementaryPredicate> eps = new ArrayList<ElementaryPredicate>();
		if(ep instanceof ElementaryUnaryPredicate){
			ElementaryUnaryPredicate eup = (ElementaryUnaryPredicate) ep;
			Attribute operand = eup.getOperand();
			Table table = operand.getTable();
			
			for(OptimizerTable duplicatedTable : table.getDuplicatedTables()){
				Attribute newAttribute = duplicatedTable.getAttribute(operand.getName());
				eps.add(new ElementaryUnaryPredicate(eup.getOriginalText(), newAttribute));
			}
		}
		else if(ep instanceof ElementaryBinaryPredicate){
			ElementaryBinaryPredicate ebp = (ElementaryBinaryPredicate) ep;
			Attribute operand1 = ebp.getOperand1(), operand2 = ebp.getOperand2();
			Table table1 = operand1.getTable(), table2 = operand2.getTable();
			HashSet<Node> nodes1 = table1.getNodes(), nodes2 = table2.getNodes();
			ArrayList<Node> intersection = intersectionNodes(nodes1, nodes2);
			Attribute newOperand1 = null, newOperand2 = null;
			if(!intersection.isEmpty()){
				for(Node node: intersection){
					
					for(OptimizerTable optTable: node.getTables()){
						if(optTable.getOriginalTable().equals(table1))
							newOperand1 = optTable.getAttribute(operand1.getName());
						if(optTable.getOriginalTable().equals(table2))
							newOperand2 = optTable.getAttribute(operand2.getName());
					}
					eps.add(new ElementaryBinaryPredicate(ebp.getOriginalText(), newOperand1, newOperand2));
				}
			}
			else{
				newOperand1 = table1.getDuplicatedTables().get(0).getAttribute(operand1.getName());
				newOperand2 = table2.getDuplicatedTables().get(0).getAttribute(operand2.getName());
				eps.add(new ElementaryBinaryPredicate(ebp.getOriginalText(), newOperand1, newOperand2));
			}
		}
		
		return eps;
	}
	
	private Predicate predicatesForDuplication(Collection<Table> fromClauseTables){
		Predicate result = new Predicate();
		
		for(Table table: fromClauseTables){
			ArrayList<OptimizerTable> dts = table.getDuplicatedTables();
			HashSet<Attribute> pks = table.getPrimaryKeys();
			for(int i=1; i<dts.size(); i++){
				OptimizerTable before = dts.get(i-1), now = dts.get(i);
				
				for(Attribute pk: pks){
					result.add(ElementaryBinaryPredicate.equalityPredicate(before.getAttribute(pk.getName()), now.getAttribute(pk.getName())));
				}	
			}
		}
		
		return result;
	}
	
	private static ArrayList<Node> intersectionNodes(Collection<Node> nodes1, Collection<Node> nodes2){
		ArrayList<Node> result = new ArrayList<Node>();
		
		for(Node node: nodes1){
			if(nodes2.contains(node))
				result.add(node);
		}
		
		return result;
	}
}
