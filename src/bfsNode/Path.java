package bfsNode;

import java.util.ArrayList;

public class Path {
	private ArrayList<BFSNode> path = new ArrayList<BFSNode> ();
	
	public Path() {
		path.clear();
	}
	
	public ArrayList<BFSNode> getPath() {
		return path;
	}

	public void setPath(ArrayList<BFSNode> path) {
		this.path = path;
	}

	public void addPath(BFSNode p){
		if(!path.contains(p)){
			path.add(p);
		}
	}

	public boolean isEmpty() {
		return path.isEmpty();
	}
	
}
