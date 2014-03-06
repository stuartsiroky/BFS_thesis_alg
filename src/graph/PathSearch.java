package graph;
import java.util.ArrayList;
import java.util.List;

import bfsNode.*;

public class PathSearch {
	BFSGraph graph;
	int nodesVisited;
	
	public PathSearch(BFSGraph b) {
		graph = b;
		nodesVisited = 0;
	}
	
	
	private boolean checkPath(ArrayList<BFSNode> path) {
		// check last element in path
		if(path.get(path.size()-1).getName() == 4) { //FIXME
			return false;
		} 
		return true;
	}
	
	private boolean followNxt(ArrayList<BFSNode> path) {
		BFSNode last = path.get(path.size()-1); 
		List<BFSNode> toList = last.getNext(); 
		boolean rtn_val = true;
		nodesVisited++;
		if (toList.size() != 0) {
			for (BFSNode v : toList) {	
				path.add(v);
				if(checkPath(path)) {
					rtn_val = followNxt(path);
				}
				else {
					System.out.println( "FAILING PATH" +path.toString());
					nodesVisited++;
					return false;
				}
			} // for list
		} // tolist == null
		else {
			System.out.println( "PASSING PATH" +path.toString());
		}
		// remove the last item.
		path.remove(path.size()-1);
		return rtn_val;

	}
	
	public boolean seachCheckPath(BFSNode startNode) {
		ArrayList<BFSNode> list = new ArrayList<BFSNode>();
		//add the firt element
		if(graph.contains(startNode)) {
			list.add(startNode);
			if(!followNxt(list)){
				//print failing path
				System.out.println("seachCheckPath nodes visited = "+nodesVisited);
				return false;
			}
			else {	
				System.out.println("seachCheckPath nodes visited = "+nodesVisited);
				return true;
			}
		} //graph contain start
		else {
			System.out.println("seachCheckPath nodes visited = "+nodesVisited);
			return false;
		}
		
	} //searchCheckPath
	
	
}
