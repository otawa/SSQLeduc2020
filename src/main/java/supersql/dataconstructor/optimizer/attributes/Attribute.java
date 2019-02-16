package supersql.dataconstructor.optimizer.attributes;

import supersql.dataconstructor.optimizer.tables.OptimizerTable;
import supersql.dataconstructor.optimizer.tables.Table;

/**
 * Class defining attributes coming from tables of the database
 * Two attributes are considered as equal if they have the same name and are from the same table
 * @author arnaudwolf
 *
 */
public class Attribute {
	private String name;
	private Table table;
	private String alias;
	
	/**
	 * Constructs an attribute from its name and table
	 * @param name: the attribute's name
	 * @param table: the attribute's table
	 */
	public Attribute(String name, Table table){
		this.name = name;
		this.table = table;
		alias = this.name + "_" + this.table.getName();
		if(this.table.hasAlias())
			alias += "_" + this.table.getAlias();
		if(this.table instanceof OptimizerTable)
			alias += "_" + ((OptimizerTable) this.table).getDuplicationId();
	}
	
	/**
	 * Returns the name of the attribute
	 * @return the name of the attribute
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the table of the attribute
	 * @return the table of the attribute
	 */
	public Table getTable(){
		return table;
	}
	

	public String getAlias(){
		return alias;
	}
	
	public String getRetrievalRepresentation(){
		String result = "";
		if(table.hasAlias())
			result += table.getAlias() + ".";
		result += name;
		
		return result;
	}

	public boolean equals(Object o){
		if(o instanceof Attribute){
			return ((Attribute) o).name.equals(name) 
					&& ((Attribute) o).table.equals(table);
		}
		else return false;
	}
	
	public int hashCode(){
		return table.hashCode();
	}
	
	public String toString(){
		return alias;
	}
}
