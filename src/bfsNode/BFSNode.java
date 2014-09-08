package bfsNode;

import java.util.ArrayList;

import data_obj.Node;

public class BFSNode extends Node {
	
	public enum COLOR { WHITE,GRAY,BLACK };
	
	private static int nodeCnt = 0;
	public String name;
	private COLOR color;// -1=white, 0=gray, 1=black
	private int distance; // -1 is infinity cant have neg distance
//	private BFSNode predecessor;
	private ArrayList<BFSNode> predecessor; 
	private ArrayList<BFSNode> next; 
	public int weight;
	private boolean condition = false; //FIXME needs to be more general
	
	public BFSNode(String name) {
		super(nodeCnt);
		nodeCnt++;
		this.name = name;
		color = COLOR.WHITE;
		distance = -1;
		predecessor = new ArrayList<BFSNode>();
		next = new ArrayList<BFSNode>();
		weight = 1;
	}

	public String toString() {
		String s = "( "+name;
		s += " | ("+getName()+")";
		//s += " Color "+color;
		s += " Dist "+distance;
//		if(!predecessor.isEmpty()) {
//			s += " P { ";
//			for(BFSNode n: predecessor) {
//				s += n.getName()+", ";
//			}
//			s += "} ";	
//		}
//		if(!next.isEmpty()) {
//			s += " N { ";
//			for(BFSNode n: next) {
//				s += n.getName()+", ";
//			}
//			s += "} ";
//			}
		s += " )";
		return s;	
	}

	public boolean equals(Object n) {
		return equals((BFSNode) n);
	}
	
	public boolean equals(BFSNode n) {
		return name.equals(n.name);
	}

	public COLOR getColor() {
		return color;
	}

	public void setColor(COLOR color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public ArrayList<BFSNode> getPredecessors() {
		return predecessor;
	}

	public BFSNode getFirstPredecessor() {
		return predecessor.get(0);
	}

	public void addPredecessor(BFSNode p) {
		if(!predecessor.contains(p)) {
			predecessor.add(p);
		}
	}

	public void clearPredecessors() {
		predecessor.clear();
	}
	
	public ArrayList<BFSNode> getNext() {
		return next;
	}

	public BFSNode getFirstNext() {
		if(!next.isEmpty()) {
			return next.get(0);	
		}
		else {
			return null;
		}
	}

	public void addNext(BFSNode p) {
		if(!next.contains(p)) {
			next.add(p);
		}
	}

	public void clearNext() {
		next.clear();
	}

	public boolean condition() {
		return getCondition();
	}

	public boolean getCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	
}
