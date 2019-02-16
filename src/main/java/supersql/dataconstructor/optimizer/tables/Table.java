package supersql.dataconstructor.optimizer.tables;

import java.util.ArrayList;
import java.util.HashSet;

import supersql.dataconstructor.optimizer.attributes.Attribute;
import supersql.dataconstructor.optimizer.attributes.TfeAttribute;
import supersql.dataconstructor.optimizer.nodes.Node;

/**
 * Class defining tables of the database used for data retrieval. Two tables are equal if they have the same name and the same alias.
 * Each table has a set of attribute and a set of primary key that is a subset of the set of attributes
 * This object also stores the tables built from this table for the optimizer.
 * @author arnaudwolf
 *
 */
public class Table {
	protected String name;
	protected String alias;
	protected boolean hasAlias;
	protected HashSet<Attribute> attributes;
	protected HashSet<Attribute> primaryKeys;
	protected ArrayList<Attribute> tfeAttributes;
	private ArrayList<OptimizerTable> duplicatedTables; //Tables that have been duplicated from this table
	private int currentDuplicatedId;
	private HashSet<Node> nodes;
	
	/**
	 * Constructs a table from its name, without alias.
	 * @param name
	 */
	public Table(String name){
		this.name = name;
		this.alias = "";
		this.hasAlias = false;
		this.attributes = new HashSet<Attribute>();
		this.primaryKeys = new HashSet<Attribute>();
		this.duplicatedTables = new ArrayList<OptimizerTable>();
		this.currentDuplicatedId = 1;
		this.nodes = new HashSet<Node>();
		this.tfeAttributes = new ArrayList<Attribute>();
	}
	
	/**
	 * Constructs a table from its name and its alias
	 * @param name
	 * @param alias
	 */
	public Table(String name, String alias){
		this.name = name;
		this.alias = alias;
		this.hasAlias = !alias.isEmpty();
		this.attributes = new HashSet<Attribute>();
		this.primaryKeys = new HashSet<Attribute>();
		this.duplicatedTables = new ArrayList<OptimizerTable>();
		this.currentDuplicatedId = 1;
		this.nodes = new HashSet<Node>();
		this.tfeAttributes = new ArrayList<Attribute>();
	}
	
	
	/**
	 * Adds an attribute to the list of attributes of this table
	 * @param att: the attribute to be added
	 * @return this object
	 */
	public Table addAttribute(Attribute att){
		attributes.add(att);
		if(att instanceof TfeAttribute)
			tfeAttributes.add(att);
		
		return this;
	}
	
	
	/**
	 * Adds an attribute to the list of attributes and the list of primary keys of this table
	 * @param att: the attribute to be added
	 * @return this object
	 */
	public Table addPrimaryKey(Attribute att){
		addAttribute(att);
		primaryKeys.add(att);
		return this;
	}
	
	/**
	 * Adds a table duplicated from this table
	 * @param table: the table to be added
	 * @return this object
	 */
	public Table addDuplicatedTable(OptimizerTable table){
		duplicatedTables.add(table);
		nodes.add(table.getNode());
		currentDuplicatedId++;
		return this;
	}
	
	public Table addNode(Node n){
		nodes.add(n);
		return this;
	}
	
	/**
	 * Remove a table that was originally duplicated
	 * @param table: the table to be removed
	 * @return this object
	 */
	public Table removeDuplicatedTable(OptimizerTable table){
		duplicatedTables.remove(table);	
		return this;
	}
	
	public Table removeNode(Node n){
		nodes.remove(n);
		return this;
	}
	
	
	/**
	 * Returns the name of this table
	 * @return the name of this table
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the alias of this table
	 * @return the alias of this table
	 */
	public String getAlias(){
		return alias;
	}
	
	public int getCurrentDuplicatedId(){
		return currentDuplicatedId;
	}
	
	/**
	 * Returns the list of attributes of this table
	 * @return the list of attributes of this table
	 */
	public HashSet<Attribute> getAttributes(){
		return attributes;
	}
	
	public ArrayList<Attribute> getTfeAttributes(){
		return tfeAttributes;
	}
	
	/**
	 * Returns the attribute corresponding to the specified name in the list of attributes.
	 * Returns null if no attributes in this table has this name
	 * @param name
	 * @return
	 */
	public Attribute getAttribute(String name){
		for(Attribute att: attributes){
			if(att.getName().equals(name))
				return att;
		}
		return null;
	}
	
	/**
	 * Returns the list of primary keys of this table
	 * @return the list of primary keys of this table
	 */
	public HashSet<Attribute> getPrimaryKeys(){
		return primaryKeys;
	}
	
	/**
	 * Returns the list of duplicated tables from this table
	 * @return the list of duplicated tables from this table
	 */
	public ArrayList<OptimizerTable> getDuplicatedTables(){
		return duplicatedTables;
	}
	
	public HashSet<Node> getNodes(){
		return nodes;
	}
	
	public int numberOfAttributes(){
		return attributes.size();
	}
	
	/**
	 * Returns true if this table has a non empty alias
	 * @return true if this table has a non empty alias
	 */
	public boolean hasAlias(){
		return hasAlias;
	}
	
	
	public boolean containsPrimaryKey(Attribute attribute){
		return primaryKeys.contains(attribute);
	}
	
	public boolean isExternalTable(){
		return tfeAttributes.isEmpty();
	}
	
	public boolean isDuplicated(){
		return duplicatedTables.size() > 1;
	}
	
	public boolean equals(Object o){
		if(o instanceof Table)
			return ((Table) o).name.equals(name)
					&& ((Table) o).alias.equals(alias);
		else return false;
	}
	
	public int hashCode(){
		return name.hashCode();
	}
	
	public String toString(){
		String result = name;
		if(hasAlias)
			result += " AS " + alias;
		return result;
	}
}
