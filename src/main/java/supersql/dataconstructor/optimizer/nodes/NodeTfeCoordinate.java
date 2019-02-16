package supersql.dataconstructor.optimizer.nodes;

import java.util.HashSet;

import supersql.dataconstructor.optimizer.attributes.TfePath;

public class NodeTfeCoordinate {
	private HashSet<NodeTfeCoordinate> subCoordinates;
	private int localIndex;
	private int depth;
	private NodeTfeCoordinate parent;
	public static final int ROOT = -1;
	
	//Generates the root of the coordinate tree
	public NodeTfeCoordinate(){
		localIndex = ROOT;
		depth = ROOT;
		subCoordinates = new HashSet<NodeTfeCoordinate>();
	}
	
	public NodeTfeCoordinate(int li, NodeTfeCoordinate p){
		localIndex = li;
		subCoordinates = new HashSet<NodeTfeCoordinate>();
		parent = p;
		depth = p.depth + 1;
	}
	
	public NodeTfeCoordinate addSubCoordinate(NodeTfeCoordinate sc){
		subCoordinates.add(sc);
		return this;
	}
	
	public HashSet<NodeTfeCoordinate> getSubCoordinates(){
		return subCoordinates;
	}
	
	public NodeTfeCoordinate getSubCoordinate(int localIndex){
		for(NodeTfeCoordinate subCoord: subCoordinates){
			if(subCoord.localIndex == localIndex)
				return subCoord;
		}
		
		return null;
	}
	
	public NodeTfeCoordinate getParent(){
		return parent;
	}
	
	public int getLocalIndex(){
		return localIndex;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public boolean isAttribute(){
		return subCoordinates.isEmpty();
	}
	
	public TfePath getLocalPath(){
		TfePath result = new TfePath();
		for(int i=0; i<depth; i++){
			result.addIndex(-1);
		}
		result.setLeafIndex(localIndex);
		if(localIndex != ROOT)
			parent.getLocalPath(result);
		return result;
	}
	
	public NodeTfeCoordinate addPath(TfePath tfePath){
		if(!tfePath.isEmpty()){
			int currentIndex = tfePath.getFirstIndex();
			NodeTfeCoordinate foundSubCoord = null;
			for(NodeTfeCoordinate subCoord: subCoordinates){
				if(subCoord.localIndex == currentIndex){
					foundSubCoord = subCoord;
					break;
				}
			}
			
			if(foundSubCoord == null){
				foundSubCoord = new NodeTfeCoordinate(currentIndex, this);
				this.addSubCoordinate(foundSubCoord);
			}
			
			foundSubCoord.addPath(tfePath.getSubPath());
		}
		else{
			boolean leafAlreadyExists = false;
			int leafIndex = tfePath.getLeafIndex();
			for(NodeTfeCoordinate subCoord: subCoordinates){
				if(subCoord.localIndex == leafIndex){
					leafAlreadyExists = true;
					break;
				}
			}
			
			if(!leafAlreadyExists){
				NodeTfeCoordinate newSubCoord = new NodeTfeCoordinate(leafIndex, this);
				this.addSubCoordinate(newSubCoord);
			}
		}
		
		return this;
	}
	
//	public NodeTfeCoordinate addPath(ArrayList<Integer> path){
//		if(!path.isEmpty()){
//			int currentIndex = path.get(0);
//			boolean containsIndex = false;
//			NodeTfeCoordinate subCoord = new NodeTfeCoordinate(currentIndex, this);
//			for(NodeTfeCoordinate sc : subCoordinates){
//				if(sc.localIndex == currentIndex){
//					containsIndex = true;
//					subCoord = sc;
//					break;
//				}
//			}
//			
//			if(!containsIndex)
//				subCoordinates.add(subCoord);
//				
//			if(path.size() > 1)
//				subCoord.addPath(new ArrayList<Integer>(path.subList(1, path.size())));
//		}
//		
//		return this;
//	}
	
	public boolean containsPath(TfePath tfePath){
		if(tfePath.isEmpty())
			return true;
		else{
			boolean result = false;
			for(NodeTfeCoordinate subCoord : subCoordinates){
				if(tfePath.getFirstIndex() == subCoord.localIndex){
					result = subCoord.containsPath(tfePath.getSubPath());
					break;
				}
			}
			return result;
		}
	}
	
	public static NodeTfeCoordinate fusionCoordinates(NodeTfeCoordinate coord1, NodeTfeCoordinate coord2){
		NodeTfeCoordinate newCoord = new NodeTfeCoordinate();
		fusionCoordinates(coord1, coord2, newCoord);
		return newCoord;
	}
	
	private void getLocalPath(TfePath result){
		if(localIndex != ROOT){
			result.getPath().set(depth, localIndex);
			parent.getLocalPath(result);
		}
	}

	private static void fusionCoordinates(NodeTfeCoordinate coord1, NodeTfeCoordinate coord2, NodeTfeCoordinate newCoord){
		HashSet<NodeTfeCoordinate> subCoordinates1 = coord1.subCoordinates;
		HashSet<NodeTfeCoordinate> subCoordinates2 = coord2.subCoordinates;
		
		for(NodeTfeCoordinate subCoord1: subCoordinates1){
			NodeTfeCoordinate newSubCoord = new NodeTfeCoordinate(subCoord1.localIndex, newCoord), subCoordToFusion = new NodeTfeCoordinate(subCoord1.localIndex, newCoord);
			newCoord.addSubCoordinate(newSubCoord);
			
			for(NodeTfeCoordinate subCoord2 : subCoordinates2){
				if(subCoord2.localIndex == subCoord1.localIndex){
					subCoordToFusion = subCoord2;
					break;
				}
			}
			fusionCoordinates(subCoord1, subCoordToFusion, newSubCoord);
		}
		
		HashSet<NodeTfeCoordinate> newSubCoordinates = newCoord.subCoordinates;
		for(NodeTfeCoordinate subCoord2: subCoordinates2){
			
			boolean alreadyFusioned = false;
			for(NodeTfeCoordinate newSubCoordCheck : newSubCoordinates){
				if(newSubCoordCheck.localIndex == subCoord2.localIndex){
					alreadyFusioned = true;
					break;
				}
			}
			
			if(!alreadyFusioned){
				NodeTfeCoordinate newSubCoord = new NodeTfeCoordinate(subCoord2.localIndex, newCoord);
				newCoord.addSubCoordinate(newSubCoord);
				fusionCoordinates(new NodeTfeCoordinate(subCoord2.localIndex, newCoord), subCoord2, newSubCoord);
			}
		}
	}
	
	public boolean equals(Object o){
		if(o instanceof NodeTfeCoordinate){
			NodeTfeCoordinate coord = (NodeTfeCoordinate) o;
			return coord.localIndex == this.localIndex && coord.subCoordinates.equals(this.subCoordinates);
		}
		else return false;
	}
	
	public int hashCode(){
		return localIndex;
	}
	
	public String toString(){
		String result = "";
		if(localIndex == ROOT){
			result += "{ROOT, " + subCoordinates.toString() + "}";
		}
		else{
			if(!subCoordinates.isEmpty())
				result += "{" + localIndex + ", " + subCoordinates.toString() + "}";
			else result += "{" + localIndex + "}";
		}	
		
		return result;
	}
}
