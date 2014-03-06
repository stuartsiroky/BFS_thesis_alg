package example;

import java.util.ArrayList;

import graph.*;
import bfsNode.*;

public class Example {

	public static void main(String[] s) {
		BFSGraph Bgraph = new BFSGraph();

		BFSNode a = Bgraph.createNode("a");
		BFSNode b = Bgraph.createNode("b");
		BFSNode c = Bgraph.createNode("c");
		BFSNode d = Bgraph.createNode("d");
		BFSNode e = Bgraph.createNode("e");
		//BFSNode f = Bgraph.createNode("f");
		BFSNode g = Bgraph.createNode("g");
		BFSNode h = Bgraph.createNode("h");
		BFSNode j = Bgraph.createNode("j");
		BFSNode k = Bgraph.createNode("k");
		BFSNode l = Bgraph.createNode("l");
		BFSNode m = Bgraph.createNode("m");
		BFSNode n = Bgraph.createNode("n");
		BFSNode o = Bgraph.createNode("o");
		BFSNode p = Bgraph.createNode("p");
		
		BFSNode y = Bgraph.createNode("y");		
		BFSNode z = Bgraph.createNode("z");		
		
		
		Bgraph.toString();

		Bgraph.addEdge(a, b);	
		Bgraph.addEdge(b, c); 
		Bgraph.addEdge(c, d); 
		Bgraph.addEdge(d, y); 
		
		Bgraph.addEdge(b, h);
		Bgraph.addEdge(h, g);
		Bgraph.addEdge(g, y);
		
		Bgraph.addEdge(y, z);
		//Bgraph.addEdge(h, j);
		Bgraph.addEdge(g, j);
//FIXME		Bgraph.addEdge(j, h);

		Bgraph.addEdge(b, k);
		Bgraph.addEdge(k, l);
		Bgraph.addEdge(k, m);
		Bgraph.addEdge(m, l);
		Bgraph.addEdge(m, n);
		Bgraph.addEdge(n, l);
		
		Bgraph.addEdge(o, p);
		Bgraph.addEdge(p, c);
		
		Bgraph.addEdge(e, z);
		Bgraph.addEdge(g, e);
		Bgraph.addEdge(b, e);
		//System.out.println(Bgraph.toString());
		
		//Bgraph.BFSearch(a);
		//Bgraph.BFSearchRev(f);
		//Bgraph.BFSearchStart(a);
		BFSGraph reducedGraph;
		reducedGraph = Bgraph.getPaths(a, z);
//		reducedGraph = Bgraph.getPaths(g, z);
		if(reducedGraph != null) {
			System.out.println(reducedGraph.toString()+"\n\n");
		}

		//Bgraph.getPaths(a, f);
		//System.out.println(Bgraph.toString());
	    //Bgraph.FromPath(a, z);
	System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);
		//if(ps.seachCheckPath(a)) {
		if(ps.seachCheckPath(a)) {
			System.out.println("STUART success");
		}
		else {
			System.out.println("STUART failure");
		}
		
	}
}
