package example;

import graph.*;
import bfsNode.*;

public class Example {

	public static void main(String[] s) {
		
		graphTest1();
		graphTest2();
	}

	private static void graphTest1() {
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
		
		Bgraph.addEdge(b, g);
		Bgraph.addEdge(g, h);
		Bgraph.addEdge(h, y);
		
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
/*		BFSGraph reducedGraph;
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
		}*/
	}
	
	private static void graphTest2() {
		BFSGraph Bgraph = new BFSGraph();

		BFSNode m0 = Bgraph.createNode("main::true");
		BFSNode m1 = Bgraph.createNode("main::(i>1)");
		BFSNode m2 = Bgraph.createNode("main::!(i>1)");
		BFSNode f0 = Bgraph.createNode("foo::true");
		BFSNode f1 = Bgraph.createNode("foo::(i>2)");
		BFSNode b0 = Bgraph.createNode("bar::true");
		BFSNode b1 = Bgraph.createNode("bar::(i>1)");
		BFSNode fb = Bgraph.createNode("foobar::true");

		Bgraph.addEdge(m0, m1);	
		Bgraph.addEdge(m0, m2); 
		Bgraph.addEdge(f0, f1); 
		Bgraph.addEdge(b0, b1); 
		Bgraph.addEdge(b0, fb);
		
		Bgraph.addEdge(m1, f0);
		Bgraph.addEdge(m2, b0);
		Bgraph.addEdge(b1, f0);
		Bgraph.addEdge(f1, fb);

		//System.out.println(Bgraph.toString());
		
		BFSGraph reducedGraph;
		reducedGraph = Bgraph.getPaths(m0, fb);
		//reducedGraph = Bgraph.getPaths(m2, fb);
		if(reducedGraph != null) {
			System.out.println(reducedGraph.toString()+"\n\n");
		}

		System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);

//		ps.printPred();
//		ps.PrintNxt();
//		if(ps.seachCheckPath(m0)) {
//			System.out.println("STUART success");
//		}
//		else {
//			System.out.println("STUART failure");
//		}
		
		ps.DSE(fb,m0);
		//ps.DSE(fb,m2);		
	}	
	
	
}
