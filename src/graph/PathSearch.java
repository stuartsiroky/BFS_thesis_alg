package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bfsNode.*;

public class PathSearch {
	private BFSGraph graph;
	int nodesVisited, dseNodeCnt;
	private ArrayList<BFSNode> workList = new ArrayList<BFSNode>();
	// private BFSNode originFunction;
	private BFSNode endFunction;
	private Map<BFSNode, ArrayList<Path>> pathMap = new HashMap<BFSNode, ArrayList<Path>>();

	public PathSearch(BFSGraph b) {
		graph = b;
		nodesVisited = 0;
		workList.clear();
		// originFunction = null;
		endFunction = null;
		// pathMap.clear();
	}

	private void prevSearch(BFSNode n) {
		graph.BFSearch(n);
	}

	public void printPred() {
		String str = "";
		for (BFSNode n : graph.nodeList) {
			str = "Node " + n.toString();
			ArrayList<BFSNode> plist = n.getPredecessors();
			for (BFSNode p : plist) {
				str += " Pred " + p.toString();
			}
			System.out.println(str);
		}
	}

	public void PrintNxt() {
		String str = "";
		for (BFSNode n : graph.nodeList) {
			str = "Node " + n.toString();
			ArrayList<BFSNode> nlist = n.getNext();
			for (BFSNode p : nlist) {
				str += " Nxt " + p.toString();
			}
			System.out.println(str);
		}
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

	public void DSE(BFSNode finalNode, BFSNode startNode) {
		workList.clear();
		dseNodeCnt = 0;
		if (graph.contains(finalNode) && graph.contains(startNode)) {
			prevSearch(startNode);
			// printPred();
			endFunction = finalNode;
			addCallersWorklist(finalNode);
			while (!isWorklistEmpty()) {
				BFSNode n = workList.remove(0);
				manageTargets(n);
			}
			System.out.println("STUART DSE nodes Visited = " + dseNodeCnt);
			System.out.println("Feasible Paths from " + startNode.toString()
					+ " to " + finalNode.toString());
			// for (BFSNode n : graph.nodeList) {
			// System.out.println("sTUART node " + n.toString());
			// printPaths(n);
			// }
			printPaths(startNode);
		}
	}

	public void printPaths(BFSNode n) {
		if (pathMap.containsKey(n)) {
			ArrayList<Path> paths = pathMap.get(n);
			for (Path p : paths) {
				System.out.println(p.toString());
			}
		}
	}

	/*********************************************************************
	 * manage_targets() paths = get_paths(node); foreach(path: paths) f =
	 * path.path_end(); if(path.path_end() = startnode) //final method we want
	 * to reach update_paths(node,paths) else if(f.functionCall() and
	 * f.haspaths()) paths_prime = get_paths(f) if(feasible(paths+paths_prime))
	 * update(node,paths)
	 * 
	 * update_paths(f,p) add_path(f,p) c = callers(f) foreach(caller : c)
	 * if(!c.haspaths()) worklist.add(c)
	 * 
	 *************************************************************************/

	private boolean pathFeasible(BFSNode n, Path p) {
		boolean result = true;
		ArrayList<BFSNode> pp = p.getPath();
		for (BFSNode nn : pp) {
			result &= nn.condition();
		}
		result &= n.condition();
		// System.out.println("STUART pathFeasible from "+n.toString()+
		// " = "+result);
		return true;
		// FIXME return result;
	}

	private void updatePaths(BFSNode from, BFSNode to, Path topath) {
		// System.out.println("update from "+from.toString()+" to "+to.toString()+" path "+topath.toString());
		ArrayList<Path> paths = new ArrayList<Path>();
		if (hasPath(from)) {
			paths = pathMap.get(from);
			// System.out.println("updatePaths-hasPath");
		}
		Path npath = new Path(topath);
		npath.addPath(from);
		boolean contains_path = false;
		for (Path p : paths) {
			if (p.equals(npath))
				contains_path = true;
		}
		if (!contains_path) {
			paths.add(npath);
			pathMap.put(from, paths);
			addCallersWorklist(from);
		}

	}

	private void createPath(BFSNode from, BFSNode topath) {
		ArrayList<Path> paths = new ArrayList<Path>();
		if (!hasPath(from)) {
			Path p = new Path(); // create new path
			p.addPath(topath);
			if (pathFeasible(from, p)) {
				p.addPath(from);
				paths.add(p);
				// only add the callers that are not currently in the pathMap.
				addCallersWorklist(from);
			}
			pathMap.put(from, paths);
		}
	}

	private boolean hasPath(BFSNode n) {
		return pathMap.containsKey(n);
	}

	// private boolean callToFunc() {
	// return false;
	// }

	private void manageTargets(BFSNode n) {
		List<BFSNode> fromList = getPaths(n);
		dseNodeCnt++;
		if (fromList.size() != 0) {
			for (BFSNode v : fromList) { // inital paths are the tolist
				// if(end path of n == endFunction) updatePath()
				if (n.equals(endFunction)) {
					System.out.println("createPath " + n.toString());
					createPath(v, n);
				}
				// else if(end path of n = callTo(f) && hasPath(f))
				else if (hasPath(n)) {
					System.out.println("manageTargets-hasPath " + n.toString());
					ArrayList<Path> ppaths = pathMap.get(n);
					for (Path p : ppaths) {
						if (pathFeasible(v, p)) {
							// System.out.println("manageTargets-updatePaths"+v.toString()+n.toString()+" path "+p.toString());
							updatePaths(v, n, p);
						}
					}
				}
			}
		}
	}

	private ArrayList<BFSNode> getPaths(BFSNode n) {
		return n.getPredecessors();
	}

	private void addCallersWorklist(BFSNode n) {
		// System.out.println("Adding caller ==" + n.toString() +
		// "== to worklist");
		workList.add(n);
	}

	private boolean isWorklistEmpty() {
		return workList.isEmpty();
	}
}
