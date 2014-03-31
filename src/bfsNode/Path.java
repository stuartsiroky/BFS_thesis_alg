package bfsNode;

import java.util.ArrayList;

public class Path {
	private ArrayList<BFSNode> path = new ArrayList<BFSNode> ();
	private String condition;
	
	public Path() {
		path.clear();
		condition = "true";
	}
	
	public Path(Path p) {
		path.clear();
		condition = "true";
		for(BFSNode n: p.getPath()) {
			path.add(n);
			buildCondition(n);
		}
	}

	private void buildCondition(BFSNode p) {
		if(p instanceof ConditionNode) {
			condition += " & "+((ConditionNode) p).getSymbCondition();
		}
	}
	
	public ArrayList<BFSNode> getPath() {
		return path;
	}
	
	public void setPath(ArrayList<BFSNode> path) {
		this.path = path;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void addPath(BFSNode p){
		if(!path.contains(p)){
			path.add(p);
			buildCondition(p);
		}
	}

	public boolean isEmpty() {
		return path.isEmpty();
	}
	
	public String toString() {
		String str = "";
		int cnt = 0;
		for(BFSNode n: path) {
			if(cnt != 0) str += " <- ";
			str += n.toString();
			cnt++;
		}
		return str;
	}

	public boolean equals(Path p) {
		if(path.size() != p.getPath().size()) return false;
		ArrayList<BFSNode> pl = p.getPath();
		for(int i=0;i<path.size();i++) {
			if(!path.get(i).equals(pl.get(i))) return false;
		}
		return true;
	}
	
}
