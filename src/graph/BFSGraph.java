package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import data_obj.AdjacencyList;
import data_obj.Edge;
import bfsNode.BFSNode;
import bfsNode.BFSNode.COLOR;

public class BFSGraph {

	ArrayList<BFSNode> nodeList = new ArrayList<BFSNode>();
	AdjacencyList adjList = new AdjacencyList();

	public BFSGraph() {
	}
	
	public BFSGraph(ArrayList<BFSNode> nodes, AdjacencyList edges) {
		nodeList = nodes;
		for(BFSNode n: nodes) {
			List<Edge> toList = edges.getAdjacent(n);
			if (toList != null) {
				for (Edge e : toList) {
					BFSNode v = (BFSNode) e.getTo();
					if(nodeList.contains(v)) {
						addEdge(n,(BFSNode) v);
					}
				}
			}
		}
	}
	
	public boolean contains(BFSNode node) {
		if(nodeList.contains(node)) { 
			return true;
		}
		else {
			return false;
		}
	}

	public void addNode(BFSNode n) {
		if (!nodeList.contains(n)) {
			nodeList.add(n);
		} 
	}

	public BFSNode createNode(String name) {
		BFSNode n = new BFSNode(name);
		if (!nodeList.contains(n)) {
			nodeList.add(n);
			return n;
		} else {
			return null;
		}
	}

	public void addEdge(BFSNode from, BFSNode to) {
		adjList.addEdge(from, to, 1);
	}

	public String toString() {
		String out = "";
		for (BFSNode n : nodeList) {
			out += n.toString() + "\n";
		}
		out += "\n\n";
		out += adjList.toString();
		return out;
	}

	public void printPath(BFSNode from, BFSNode to) {
		if (from.equals(to)) {
			System.out.println(from.toString());
		} else if (to.getFirstPredecessor() == null) {
			System.out.println("No path from " + from.toString() + " to "
					+ to.toString());
		} else {
			printPath(from, to.getFirstPredecessor());
			System.out.println(to.toString());
		}
	}

	public boolean pathExists(BFSNode from, BFSNode to) {
		if (from.equals(to)) {
			return true;
		} else if (to.getFirstPredecessor() == null) {
			return false;
		} else {
			return pathExists(from, to.getFirstPredecessor());
		}
	}

	public boolean pathRevExists(BFSNode from, BFSNode to) {
		if (from.equals(to)) {
			return true;
		} else if (to.getFirstNext() == null) {
			return false;
		} else {
			return pathRevExists(from, to.getFirstNext());
		}
	}



	// public void printPath(BFSNode from, BFSNode to) {
	// System.out.println("Stuart printing path from "+from.toString()+" to "+to.toString());
	// AdjacencyList newlist = new AdjacencyList();
	// newlist = adjList.getPath(from, to);
	// System.out.println(newlist.printFromTo(from,to)+"\n");
	// //System.out.println(adjList.getPath(from, to));
	// }

	public void BFSearch(BFSNode startNode) {
		Queue<BFSNode> q = new LinkedList<BFSNode>();
		int cnt = 0; 
		initSearch(startNode);
		q.add(startNode);
		while (!q.isEmpty()) {
			BFSNode n = q.remove();
			cnt++;
			List<Edge> toList = adjList.getAdjacent(n);
			if (toList != null) {
				for (Edge e : toList) {
					BFSNode v = (BFSNode) e.getTo();
					if (v.getColor() == COLOR.WHITE) {
						v.setColor(COLOR.GRAY);
						v.setDistance(n.getDistance() + 1);
						v.addPredecessor(n);
						q.add(v);
					} // if WHITE
					else {
						v.addPredecessor(n);//add all other predecessors
					}// else if GREY
				} // for edge
			}// not null
			n.setColor(COLOR.BLACK);
		} // !q.isEmpty
		System.out.println("BFSearch nodes visited = "+cnt);
	}

	private void initSearch(BFSNode startNode) {
		for (BFSNode n : nodeList) {
			n.setColor(COLOR.WHITE);
			n.setDistance(-1);
			n.clearPredecessors();
			n.clearNext();
		}
		startNode.setColor(COLOR.GRAY);
		startNode.setDistance(0);
		startNode.clearPredecessors();
		startNode.clearNext();
	}

	int nodevisitcnt;
	public void FromPath(BFSNode startNode, BFSNode finishNode) {
		// do a BF search and then go backwards on the predecessors of the nodes
		BFSearch(startNode);
		if(pathExists(startNode,finishNode)){
			nodevisitcnt = 0;
			ArrayList<BFSNode> callTrace = new ArrayList<BFSNode>();
			traceBack(callTrace,finishNode);
			
		System.out.println("STUART ========\n"+callTrace.toString());
		}
		System.out.println("FromPath nodes visited = "+nodevisitcnt);
	}

	public void traceBack(ArrayList<BFSNode> callTrace, BFSNode finishNode) {
		if(!callTrace.contains(finishNode)) {
			callTrace.add(finishNode);
			nodevisitcnt++;
			ArrayList<BFSNode> fromList = finishNode.getPredecessors();
			for(BFSNode from: fromList) {
				traceBack(callTrace,from);
			}
		}	
	}
	
	public void BFSearchRev(BFSNode finalNode) {
		Queue<BFSNode> q = new LinkedList<BFSNode>();
		System.out.println("Number of node in starting graph "+nodeList.size());
		int cnt=0;
		initSearch(finalNode);
		q.add(finalNode);
		while (!q.isEmpty()) {
			BFSNode n = q.remove();
			cnt++;
			List<Edge> fromList = adjList.getReversedList().getAdjacent(n);
			//System.out.println("STUART "+n.toString());
			//System.out.println(" "+fromList.toString());
			if (fromList != null) {
				for (Edge e : fromList) {
					BFSNode v = (BFSNode) e.getTo();
					if (v.getColor() == COLOR.WHITE) {
						v.setColor(COLOR.GRAY);
						v.setDistance(n.getDistance() + 1);
						v.addNext(n);
						q.add(v);
					} // if WHITE
					else {
						v.addNext(n);//add all other predecessors
					}// else if GREY
				} // for edge
			}// not null
			n.setColor(COLOR.BLACK);
		} // !q.isEmpty
		System.out.println("BFSeachRev nodes visited = "+cnt);
	}

	public ArrayList<BFSNode> BFSearchStart(BFSNode startNode) {
		Queue<BFSNode> q = new LinkedList<BFSNode>();
		ArrayList<BFSNode> trace = new ArrayList<BFSNode>();
		int cnt = 0;
		// only clear color and dist
		clearColorDist(startNode);

		q.add(startNode);
		trace.add(startNode);
		while (!q.isEmpty()) {
			BFSNode n = q.remove();
			cnt++;
			List<BFSNode> toList = n.getNext();
			if (toList != null) {
				for (BFSNode v : toList) {
					if (v.getColor() == COLOR.WHITE) {
						v.setColor(COLOR.GRAY);
						v.setDistance(n.getDistance() + 1);
						v.addPredecessor(n);
						q.add(v);
						trace.add(v);
					} // if WHITE
					//else {
					//	v.addPredecessor(n);//add all other predecessors
					//}// else if GREY
				} // for edge
			}// not null
			n.setColor(COLOR.BLACK);
		} // !q.isEmpty
		System.out.println("BFSearchStart nodes visited = "+cnt);
		return trace;
	}

	public void clearColorDist(BFSNode startNode) {
		for (BFSNode n : nodeList) {
			n.setColor(COLOR.WHITE);
			n.setDistance(-1);
		}
		startNode.setColor(COLOR.GRAY);
		startNode.setDistance(0);
	}

	public BFSGraph getPaths(BFSNode startNode, BFSNode finalNode) {
		BFSearchRev(finalNode);
		if(pathRevExists(finalNode,startNode)){
			return new BFSGraph(BFSearchStart(startNode),adjList);
		}
		else {
			System.out.println("STUART failed to find path from "+finalNode.toString()+" to "+startNode.toString());
			return null;
		}
	}
		
	
	public int size() { 
		return nodeList.size();
	}
	
	
	
	
	
}
