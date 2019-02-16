package supersql.dataconstructor.optimizer.attributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defining the localisation of an attribute in the Tfe tree. Is modelized by a list of integers, corresping to the indices in the nodes of the tree to follow.
 * @author arnaudwolf
 *
 */
public class TfePath {
	private ArrayList<Integer> path;
	private int leafIndex;
	
	/**
	 * Constructs an empty TFE path
	 */
	public TfePath(){
		path = new ArrayList<Integer>();
	}
	
	public TfePath(List<Integer> path, int index){
		this.path = new ArrayList<Integer>(path);
		leafIndex = index;
	}
	
	public TfePath setLeafIndex(int index){
		leafIndex = index;
		return this;
	}
	
	/**
	 * Adds an index at the end of the path
	 * @param index
	 * @return
	 */
	public TfePath addIndex(int index){
		path.add(index);
		return this;
	}
	
	/**
	 * Returns the list of indices composing this path
	 * @return the list of indices composing this path
	 */
	public ArrayList<Integer> getPath(){
		return path;
	}
	
	public int getLeafIndex(){
		return leafIndex;
	}
	
	public int getFirstIndex(){
		if(path.isEmpty())
			return -1;
		else return path.get(0);
	}
	
	public TfePath getSubPath(){
		if(path.size()>1)
			return new TfePath(path.subList(1, path.size()), leafIndex);
		else return new TfePath();
	}
	
	public int size(){
		return path.size();
	}
	
	public boolean isEmpty(){
		return path.isEmpty();
	}
	
	public boolean containsPath(TfePath tfePath){
		if(tfePath.size() > size())
			return false;
		for(int i=0; i<tfePath.size(); i++){
			if(tfePath.path.get(i) != path.get(i))
				return false;
		}
		
		return true;
	}
	
	public boolean equals(Object o){
		if(o instanceof TfePath)
			return ((TfePath) o).path.equals(path) && ((TfePath) o).leafIndex == leafIndex;
		else return false;
	}
	
	public int hashCode(){
		return path.hashCode();
	}
	
	public String toString(){
		return path.toString() + ": " + leafIndex;
	}
}
