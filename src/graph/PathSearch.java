package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bfsNode.*;

public class PathSearch {
	private BFSGraph graph;
	int nodesVisited;
	private ArrayList<BFSNode> workList = new ArrayList<BFSNode>();
	private BFSNode originFunction;
	private BFSNode endFunction;
	private Map<BFSNode, ArrayList<Path>> pathMap = new HashMap<BFSNode, ArrayList<Path>>();

	public PathSearch(BFSGraph b) {
		graph = b;
		nodesVisited = 0;
		workList.clear();
		originFunction = null;
		endFunction = null;
		// pathMap.clear();
	}

	private boolean checkPath(ArrayList<BFSNode> path) {
		int curr_prop = -1;
		for (BFSNode n : path) {
			if (n.getName() > curr_prop) {
				curr_prop = n.getName();
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean followNxt(ArrayList<BFSNode> path) {
		BFSNode last = path.get(path.size() - 1);
		List<BFSNode> toList = last.getNext();
		boolean rtn_val = true;
		nodesVisited++;
		if (toList.size() != 0) {
			for (BFSNode v : toList) {
				if (rtn_val == true) {
					path.add(v);
					if (checkPath(path)) {
						rtn_val = followNxt(path);
					} else {
						System.out.println("FAILING PATH" + path.toString());
						nodesVisited++;
						rtn_val = false;
						return rtn_val;
					}
				}
			} // for list
		} // tolist == null
		else {
			System.out.println("PASSING PATH" + path.toString());
		}
		// remove the last item.
		path.remove(path.size() - 1);
		return rtn_val;

	}

	public boolean seachCheckPath(BFSNode startNode) {
		ArrayList<BFSNode> list = new ArrayList<BFSNode>();
		// add the first element
		if (graph.contains(startNode)) {
			list.add(startNode);
			if (!followNxt(list)) {
				// print failing path
				System.out.println("seachCheckPath nodes visited = "
						+ nodesVisited);
				return false;
			} else {
				System.out.println("seachCheckPath nodes visited = "
						+ nodesVisited);
				return true;
			}
		} // graph contain start
		else {
			System.out
					.println("seachCheckPath nodes visited = " + nodesVisited);
			return false;
		}

	} // searchCheckPath

	// ///////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////
	// // Directed symbolic execution algorithm ////
	// ///////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////

	public void DSE(BFSNode startNode, BFSNode endNode) {
		workList.clear();
		if (graph.contains(startNode) && graph.contains(endNode)) {
			originFunction = startNode;
			endFunction = endNode;
			workList.add(startNode);
			manageTargets(startNode);
		}
	}

	private boolean pathFeasible(BFSNode n, ArrayList<BFSNode> path) {
		boolean result = false;
		for (BFSNode nn : path) {
			result &= nn.condition();
		}
		result &= n.condition();
		return result;
	}

	private void updatePaths(BFSNode from, BFSNode topath) {
		ArrayList<Path> paths = new ArrayList<Path>();
		if (hasPathsOrigin(topath)) {
			ArrayList<Path> ppaths = pathMap.get(topath);
			for (Path p : ppaths) { // add to existing path
				p.addPath(from);
				paths.add(p);
			}

		} else {
			Path p = new Path(); // create new path
			p.addPath(topath);
			paths.add(p);
		}
		pathMap.put(from, paths);
		// only add the callers that are not currently in the pathMap.
		// addCallersWorklist(f)//TODO

		// addPath(n,path)
		// if(!hasPaths(n))
		// addCallersWorklist(f)//to worklist
	}

	private boolean hasPathsOrigin(BFSNode n) {
		return pathMap.containsKey(n);
	}

	private boolean callToFunc() {
		return false;
	}

	private void manageTargets(BFSNode n) {
		List<BFSNode> toList = n.getNext();
		// ArrayList<BFSNode> path = new ArrayList<BFSNode> ();//TODO remove new
		// FIXME path = n.path();????
		// TODO need to put in a node counter
		if (toList.size() != 0) {
			for (BFSNode v : toList) { // inital paths are the tolist
				// if(end path of n == endFunction) updatePath()
				if (v.equals(endFunction)) {
					updatePaths(v, n);
				}
				// else if(end path of n = callTo(f) && hasPath(f))
				// for(p' exists getPaths(f))
				// if(pathFeasable(p+p')
				// updatePaths()
				// FIXME else if (callToFunc() && hasPathsOrigin()) {
				// FIXME }

			}
		}
	}

	private ArrayList<BFSNode> get_paths(BFSNode n) {
		return n.getPredecessors();
	}

	private void addCallersWorkslist(BFSNode n) {
		workList.add(n);
	}
}
