package supersql.dataconstructor.optimizer.tables;

import supersql.dataconstructor.optimizer.attributes.Attribute;
import supersql.dataconstructor.optimizer.attributes.TfeAttribute;
import supersql.dataconstructor.optimizer.nodes.Node;

/**
 * Class defining tables used for the optimizer. They are derived from the Table class, and has the particularity that they belong only to one single node.
 * There may be several table derived from an original tables (when two nodees of the original TFE tree contain the same table). 
 * We say then that those tables are duplicated.
 * @author arnaudwolf
 *
 */
public class OptimizerTable extends Table{
	private Node node;
	private Table originalTable;
	private int duplicationId;
	
	/**
	 * Constructs an optimizer table from its original table
	 * @param originalTable
	 * @param node: the node this table belongs to
	 */
	public OptimizerTable(Table ot, Node node){
		super(ot.name, ot.alias);
		this.node = node;
		this.originalTable = ot;
		duplicationId = ot.getCurrentDuplicatedId();
		
		for(Attribute att: ot.getAttributes()){
			if(att instanceof TfeAttribute){
				TfeAttribute tfeAtt = (TfeAttribute) att;
				TfeAttribute newAtt = new TfeAttribute(tfeAtt.getName(), this, tfeAtt.getTfePaths());
				addAttribute(newAtt);
				if(ot.getPrimaryKeys().contains(att))
					primaryKeys.add(newAtt);
			}
			else{
				Attribute newAtt = new Attribute(att.getName(), this);
				addAttribute(newAtt);
				if(ot.getPrimaryKeys().contains(att))
					primaryKeys.add(newAtt);
			}
		}
	}
	
	/**
	 * Returns the node this table belongs to
	 * @return the node this table belongs to
	 */
	public Node getNode(){
		return node;
	}
	
	/**
	 * Returns the table this tables was built from
	 * @return the table this tables was built from
	 */
	public Table getOriginalTable(){
		return originalTable;
	}
	
	public int getDuplicationId(){
		return duplicationId;
	}
	
	public void setNode(Node n){
		node = n;
	}
	
	/**
	 * Returns true if this tables has been duplicated, that is, if more than one tables has been built from the original table of this table
	 * @return true if this tables has been duplicated
	 */
	public boolean isDuplicated(){
		return originalTable.getDuplicatedTables().size() > 1;
	}
	
	public boolean equals(Object o){
		if(o instanceof OptimizerTable){
			return ((OptimizerTable) o).originalTable.equals(originalTable) 
					&& ((OptimizerTable) o).duplicationId == duplicationId;
		}
		else return false;
	}
	
	public String toString(){
		return originalTable.toString() + "_" + duplicationId;
	}
}
