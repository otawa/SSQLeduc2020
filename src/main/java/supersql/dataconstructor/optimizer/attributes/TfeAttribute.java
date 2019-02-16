package supersql.dataconstructor.optimizer.attributes;

import java.util.ArrayList;

import supersql.dataconstructor.optimizer.tables.Table;
/**
 * Class defining an attribute appearing in the TFE clause. In addition to the attributes of the Attribute class, it also contains the list of paths corresponding 
 * to the locations of this attribute in the TFE tree.
 * @author arnaudwolf
 *
 */
public class TfeAttribute extends Attribute {
	private ArrayList<TfePath> tfePaths;
	
	/**
	 * Constructs an TFE Attribute from its name and its table
	 * @param name
	 * @param table
	 */
	public TfeAttribute(String name, Table table){
		super(name, table);
		tfePaths = new ArrayList<TfePath>();
	}
	
	public TfeAttribute(String name, Table table, TfePath path){
		super(name, table);
		tfePaths = new ArrayList<TfePath>();
		tfePaths.add(path);
	}
	
	public TfeAttribute(String name, Table table, ArrayList<TfePath> paths){
		super(name, table);
		tfePaths = paths;
	}
	
	/**
	 * Adds a path to the list of paths of this attribute
	 * @param path
	 * @return
	 */
	public TfeAttribute addPath(TfePath path){
		tfePaths.add(path);
		return this;
	}
	
	/**
	 * Returns the list of paths of this attribute
	 * @return the list of paths of this attribute
	 */
	public ArrayList<TfePath> getTfePaths(){
		return tfePaths;
	}
}
