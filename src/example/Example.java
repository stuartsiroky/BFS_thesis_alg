package example;

//import java.util.Map;
//import java.io.IOException;

//import data_obj.AdjacencyList;
//import jimpleParser.JimpleParser;
import java.io.IOException;

import graph.*;
import bfsNode.*;

//import org.eclipse.jdt.core.dom.*;

public class Example {
	static long startTime;
	static long stopTime;
	
	public static void main(String[] s) {
		System.out.println("===========================");
		System.out.println("====== graphTest1 =========");
		System.out.println("===========================");
		graphTest1();
		System.out.println("\n\n");
		System.out.println("===========================");
		System.out.println("====== graphTest2 =========");
		System.out.println("===========================");
		graphTest2();
		System.out.println("===========================");
		System.out.println("====== graphTest3 =========");
		System.out.println("===========================");	
		graphTest3();
		System.out.println("===========================");
		System.out.println("====== graphTest4 =========");
		System.out.println("===========================");	
		graphTest4();
		/*BFSAdjacencyList adjList = new BFSAdjacencyList();	
		BFSNode source = new BFSNode("src");
		BFSNode target = new BFSNode("trg");
		adjList.addEdge(source, target, 1);
		adjList.addEdge(source, target, 1);
		adjList.addEdge(source, target, 1);
		System.out.println(adjList.toString());*/
	}

	
	private static void graphTest1() {
		BFSGraph Bgraph = new BFSGraph();

		FunctionNode a = Bgraph.createFNode("a");
		FunctionNode b = Bgraph.createFNode("b");
		FunctionNode c = Bgraph.createFNode("c");
		FunctionNode d = Bgraph.createFNode("d");
		FunctionNode e = Bgraph.createFNode("e");
		//FunctionNode f = Bgraph.createFNode("f");
		FunctionNode g = Bgraph.createFNode("g");
		FunctionNode h = Bgraph.createFNode("h");
		FunctionNode j = Bgraph.createFNode("j");
		FunctionNode k = Bgraph.createFNode("k");
		FunctionNode l = Bgraph.createFNode("l");
		FunctionNode m = Bgraph.createFNode("m");
		FunctionNode n = Bgraph.createFNode("n");
		FunctionNode o = Bgraph.createFNode("o");
		FunctionNode p = Bgraph.createFNode("p");
		
		FunctionNode y = Bgraph.createFNode("y");		
		FunctionNode z = Bgraph.createFNode("z");		
		
		
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
		//try {
		//	Bgraph.write_to_file("C:\\Users\\StuartSiroky\\Documents\\graph1_init.txt");
		//} catch (IOException e1) {
		//	// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		//Bgraph.BFSearch(a);
		//Bgraph.BFSearchRev(f);
		//Bgraph.BFSearchStart(a);
		BFSGraph reducedGraph;
		startTime = System.currentTimeMillis();
		reducedGraph = Bgraph.getPaths(a, z);
//		reducedGraph = Bgraph.getPaths(g, z);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to reduce graph "+(stopTime-startTime));
		if(reducedGraph != null) {
			System.out.println(reducedGraph.toString()+"\n\n");
		}

		//Bgraph.getPaths(a, f);
		//System.out.println(Bgraph.toString());
	    //Bgraph.FromPath(a, z);
	System.out.println("\n\n");
//		PathSearch ps = new PathSearch(reducedGraph);
		//if(ps.seachCheckPath(a)) {
/*		startTime = System.currentTimeMillis();
		if(ps.seachCheckPath(a)) {
			System.out.println("STUART success");
		}
		else {
			System.out.println("STUART failure");
		}
		stopTime = System.currentTimeMillis();
		System.out.println("Time to reduce graph "+(stopTime-startTime));
		*/
	}
	
	private static void graphTest2() {
		BFSGraph Bgraph = new BFSGraph();

		FunctionNode  m0 = new FunctionNode("main");
		ConditionNode m1 = new ConditionNode("main","(i>1)");
		ConditionNode m2 = new ConditionNode("main","!(i>1)");
		FunctionNode  f0 = new FunctionNode("foo");
		ConditionNode f1 = new ConditionNode("foo","(i>2)");
		FunctionNode  b0 = new FunctionNode("bar");
		ConditionNode b1 = new ConditionNode("bar","(i>1)");
		FunctionNode  fb = new FunctionNode("foobar");
		
		Bgraph.addNode(m0);
		Bgraph.addNode(m1);
		Bgraph.addNode(m2);
		Bgraph.addNode(f0);
		Bgraph.addNode(f1);
		Bgraph.addNode(b0);
		Bgraph.addNode(b1);
		Bgraph.addNode(fb);
		
		System.out.println(Bgraph.toString());
		Bgraph.addEdge(m0, m1);	
		Bgraph.addEdge(m0, m2); 
		Bgraph.addEdge(f0, f1); 
		Bgraph.addEdge(b0, b1); 
		Bgraph.addEdge(b0, fb);
		
		Bgraph.addEdge(m1, f0);
		Bgraph.addEdge(m2, b0);
		Bgraph.addEdge(b1, f0);
		Bgraph.addEdge(f1, fb);

		System.out.println(Bgraph.toString());
		
		BFSGraph reducedGraph;
		startTime = System.currentTimeMillis();
		reducedGraph = Bgraph.getPaths(m0, fb);
		//reducedGraph = Bgraph.getPaths(m2, fb);
		stopTime = System.currentTimeMillis();	
		System.out.println("Time to reduce graph "+(stopTime-startTime));
		if(reducedGraph != null) {
			System.out.println(reducedGraph.toString()+"\n\n");
		}

		System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);

//		ps.printPred();
//		ps.PrintNxt();
//		System.out.println("\n===Recursivly find all paths going forward");
//		startTime = System.currentTimeMillis();
//		if(ps.seachCheckPath(m0)) {
//			System.out.println("STUART success");
//		}
//		else {
//			System.out.println("STUART failure");
//		}
//		stopTime = System.currentTimeMillis();
//		System.out.println("Time to seachCheckPath "+(stopTime-startTime));
//		System.out.println("\n===BFS backwards to find all paths");
		startTime = System.currentTimeMillis();
		ps.DSE(fb,m0);
		//ps.DSE(fb,m2);		
		stopTime = System.currentTimeMillis();
		System.out.println("Time to DSE "+(stopTime-startTime));
		ps.createF2FPath();
		//ps.printF2FPath();
		}	
	
	private static void graphTest3() {
		BFSGraph Bgraph = new BFSGraph();
	
		FunctionNode  m0 = new FunctionNode("main");
		ConditionNode m1 = new ConditionNode("main","(i>1)");
		ConditionNode m2 = new ConditionNode("main","!(i>1)");
		FunctionNode  f0 = new FunctionNode("foo");
		ConditionNode f1 = new ConditionNode("foo","(i>2)");
		FunctionNode  b0 = new FunctionNode("bar");
		ConditionNode b1 = new ConditionNode("bar","(i>1)");
		FunctionNode  fb = new FunctionNode("foobar");
		FunctionNode  fbb = new FunctionNode("fake");
		
		Bgraph.addNode(m0);
		Bgraph.addNode(m1);
		Bgraph.addNode(m2);
		Bgraph.addNode(f0);
		Bgraph.addNode(f1);
		Bgraph.addNode(b0);
		Bgraph.addNode(b1);
		Bgraph.addNode(fb);
		Bgraph.addNode(fbb);
		System.out.println(Bgraph.toString());
		Bgraph.addEdge(m0, m1);	
		Bgraph.addEdge(m0, m2); 
		Bgraph.addEdge(f0, f1); 
		Bgraph.addEdge(b0, b1); 
		Bgraph.addEdge(b0, fb);
		
		Bgraph.addEdge(m1, f0);
		Bgraph.addEdge(m2, b0);
		Bgraph.addEdge(b1, f0);
		Bgraph.addEdge(f1, fb);
	
		System.out.println(Bgraph.toString());
		try {
			Bgraph.write_to_file("C:\\Users\\StuartSiroky\\Documents\\graph3_reduced.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BFSGraph reducedGraph;
		startTime = System.currentTimeMillis();
		//reducedGraph = Bgraph.getPaths(m0, fb);
		reducedGraph = Bgraph.getPaths(m0, fbb);
		//reducedGraph = Bgraph.getPaths(m2, fb);
		stopTime = System.currentTimeMillis();	
		System.out.println("Time to reduce graph "+(stopTime-startTime));
		if(reducedGraph != null) {
			System.out.println(reducedGraph.toString()+"\n\n");
		}
	
		System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);
	
	//	ps.printPred();
	//	ps.PrintNxt();
	//	System.out.println("\n===Recursivly find all paths going forward");
	//	startTime = System.currentTimeMillis();
	//	if(ps.seachCheckPath(m0)) {
	//		System.out.println("STUART success");
	//	}
	//	else {
	//		System.out.println("STUART failure");
	//	}
	//	stopTime = System.currentTimeMillis();
	//	System.out.println("Time to seachCheckPath "+(stopTime-startTime));
	//	System.out.println("\n===BFS backwards to find all paths");
		startTime = System.currentTimeMillis();
		ps.DSE(fb,m0);
		//ps.DSE(fb,m2);		
		stopTime = System.currentTimeMillis();
		System.out.println("Time to DSE "+(stopTime-startTime));
		ps.createF2FPath();
		//ps.printF2FPath();
		//		JimpleParser JP = new JimpleParser();
	//		try {
	//			JP.ReadJimple("C:\\Users\\ssiroky\\workspace\\BFS\\sootOutput\\codeExamples.DD.jimple");
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
		}


	private static void graphTest4() {
		BFSGraph Bgraph = new BFSGraph();
		
		try {
			Bgraph.read_from_file("C:\\Users\\StuartSiroky\\Documents\\graph3_reduced.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}

}
