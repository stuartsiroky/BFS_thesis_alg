package example;

import java.io.IOException;

import graph.*;
import bfsNode.*;
import jimpleParser.*;

//import org.eclipse.jdt.core.dom.*;

public class Example {
	static long startTime;
	static long stopTime;

	public static void main(String[] s) {
		System.out.println("===========================");
		System.out.println("====== graphTest1 =========");
		System.out.println("===========================");
//		graphTest1();
		System.out.println("\n\n");
		System.out.println("===========================");
		System.out.println("====== graphTest2 =========");
		System.out.println("===========================");
//		graphTest2();
		System.out.println("===========================");
		System.out.println("====== graphTest3 =========");
		System.out.println("===========================");
//		graphTest3();
		System.out.println("===========================");
		System.out.println("====== graphTest4 =========");
		System.out.println("===========================");
//		graphTest4();
		System.out.println("===========================");
		System.out.println("====== graphTest5 =========");
		System.out.println("===========================");
//		graphTest5();
		System.out.println("===========================");
		System.out.println("====== graphTest6 =========");
		System.out.println("===========================");
		graphTest6();
	}

	private static void graphTest1() {
		BFSGraph Bgraph = new BFSGraph();

		FunctionNode a = Bgraph.createFNode("a");
		FunctionNode b = Bgraph.createFNode("b");
		FunctionNode c = Bgraph.createFNode("c");
		FunctionNode d = Bgraph.createFNode("d");
		FunctionNode e = Bgraph.createFNode("e");
		// FunctionNode f = Bgraph.createFNode("f");
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
		// Bgraph.addEdge(h, j);
		Bgraph.addEdge(g, j);
		// FIXME Bgraph.addEdge(j, h);

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
		// System.out.println(Bgraph.toString());
		// try {
		// Bgraph.write_to_file("C:.Users.StuartSiroky.Documents.graph1_init.txt");
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// Bgraph.BFSearch(a);
		// Bgraph.BFSearchRev(f);
		// Bgraph.BFSearchStart(a);
		BFSGraph reducedGraph;
		startTime = System.currentTimeMillis();
		reducedGraph = Bgraph.getPaths(a, z);
		// reducedGraph = Bgraph.getPaths(g, z);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to reduce graph " + (stopTime - startTime));
		if (reducedGraph != null) {
			System.out.println(reducedGraph.toString() + "\n\n");
		}

		// Bgraph.getPaths(a, f);
		// System.out.println(Bgraph.toString());
		// Bgraph.FromPath(a, z);
		System.out.println("\n\n");
		// PathSearch ps = new PathSearch(reducedGraph);
		// if(ps.seachCheckPath(a)) {
		/*
		 * startTime = System.currentTimeMillis(); if(ps.seachCheckPath(a)) {
		 * System.out.println("STUART success"); } else {
		 * System.out.println("STUART failure"); } stopTime =
		 * System.currentTimeMillis();
		 * System.out.println("Time to reduce graph "+(stopTime-startTime));
		 */
	}

	private static void graphTest2() {
		BFSGraph Bgraph = new BFSGraph();

		FunctionNode m0 = new FunctionNode("main");
		ConditionNode m1 = new ConditionNode("main", "(i>1)");
		ConditionNode m2 = new ConditionNode("main", "!(i>1)");
		FunctionNode f0 = new FunctionNode("foo");
		ConditionNode f1 = new ConditionNode("foo", "(i>2)");
		FunctionNode b0 = new FunctionNode("bar");
		ConditionNode b1 = new ConditionNode("bar", "(i>1)");
		FunctionNode fb = new FunctionNode("foobar");

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
		// reducedGraph = Bgraph.getPaths(m2, fb);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to reduce graph " + (stopTime - startTime));
		if (reducedGraph != null) {
			System.out.println(reducedGraph.toString() + "\n\n");
		}

		System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);

		// ps.printPred();
		// ps.PrintNxt();
		// System.out.println("\n===Recursivly find all paths going forward");
		// startTime = System.currentTimeMillis();
		// if(ps.seachCheckPath(m0)) {
		// System.out.println("STUART success");
		// }
		// else {
		// System.out.println("STUART failure");
		// }
		// stopTime = System.currentTimeMillis();
		// System.out.println("Time to seachCheckPath "+(stopTime-startTime));
		// System.out.println("\n===BFS backwards to find all paths");
		startTime = System.currentTimeMillis();
		ps.DSE(fb, m0);
		// ps.DSE(fb,m2);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to DSE " + (stopTime - startTime));
		ps.createF2FPath();
		// ps.printF2FPath();
	}

	private static void graphTest3() {
		BFSGraph Bgraph = new BFSGraph();

		FunctionNode m0 = new FunctionNode("main");
		ConditionNode m1 = new ConditionNode("main", "(i>1)");
		ConditionNode m2 = new ConditionNode("main", "!(i>1)");
		FunctionNode f0 = new FunctionNode("foo");
		ConditionNode f1 = new ConditionNode("foo", "(i>2)");
		FunctionNode b0 = new FunctionNode("bar");
		ConditionNode b1 = new ConditionNode("bar", "(i>1)");
		FunctionNode fb = new FunctionNode("foobar");
		FunctionNode fbb = new FunctionNode("fake");

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
		// reducedGraph = Bgraph.getPaths(m0, fb);
		reducedGraph = Bgraph.getPaths(m0, fbb);
		// reducedGraph = Bgraph.getPaths(m2, fb);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to reduce graph " + (stopTime - startTime));
		if (reducedGraph != null) {
			System.out.println(reducedGraph.toString() + "\n\n");
		}

		System.out.println("\n\n");
		PathSearch ps = new PathSearch(reducedGraph);

		// ps.printPred();
		// ps.PrintNxt();
		// System.out.println("\n===Recursivly find all paths going forward");
		// startTime = System.currentTimeMillis();
		// if(ps.seachCheckPath(m0)) {
		// System.out.println("STUART success");
		// }
		// else {
		// System.out.println("STUART failure");
		// }
		// stopTime = System.currentTimeMillis();
		// System.out.println("Time to seachCheckPath "+(stopTime-startTime));
		// System.out.println("\n===BFS backwards to find all paths");
		startTime = System.currentTimeMillis();
		ps.DSE(fb, m0);
		// ps.DSE(fb,m2);
		stopTime = System.currentTimeMillis();
		System.out.println("Time to DSE " + (stopTime - startTime));
		ps.createF2FPath();

	}

	private static void graphTest4() {
		BFSGraph Bgraph = new BFSGraph();

		try {
			Bgraph.read_from_file("C:\\Users\\StuartSiroky\\Documents\\graph3_reduced.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void graphTest5() {
		JimpleParser JP = new JimpleParser();
		try {
			// JP.ReadJimple("C:.Users.StuartSiroky.workspace_kepler_1.0.SootExample.sootOutput.codeExamples.DD.jimple");
			// JP.ReadJimple("C:.Users.StuartSiroky.workspace_kepler_1.0.SootExample.sootOutput.codeExamples.E.jimple");
			// JP.ReadJimple("C:.Users.StuartSiroky.workspace_kepler_1.0.SootExample.sootOutput.codeExamples.FF.jimple");
			String base = "C:\\Users\\StuartSiroky\\git\\NoSwingCalc\\sootOutput\\";
			// base = base;
			JP.ReadJimple(base+"calc.noSwing.JButton.jimple");
			// JP.ReadJimple(base+"calc.noSwing.JPanel.jimple");
			JP.ReadJimple(base+"calc.view.CalculatorView.jimple");
			JP.ReadJimple(base+"calc.view.CalculatorView$Handler.jimple");
			JP.printCFG();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	BFSGraph cfg = new BFSGraph();
	GraphGenerator GG = new GraphGenerator();

	private static void graphTest6() {
		BFSGraph cfg = new BFSGraph();
		GraphGenerator GG = new GraphGenerator();
		String[] fileList = new String[17];
		
		fileList[0] = "calc.controller.AbstractController";
		fileList[1] = "calc.controller.CalculatorController";
		fileList[2] = "calc.model.AbstractModel";
		fileList[3] = "calc.model.CalculatorModel";
		fileList[4] = "calc.model.ModelEvent";
		fileList[5] = "calc.noSwing.ActionEvent";
		fileList[6] = "calc.noSwing.BorderLayout";
		fileList[7] = "calc.noSwing.Container";
		fileList[8] = "calc.noSwing.GridLayout";
		fileList[9] = "calc.noSwing.JButton";
		fileList[10] = "calc.noSwing.JPanel";
		fileList[11] = "calc.noSwing.JTextField";
		fileList[12] = "calc.noSwing.MyJFrame";
		fileList[13] = "calc.view.CalculatorView";
		fileList[14] = "calc.view.GridLayout";
		fileList[15] = "calc.view.JFrameView";
		fileList[16] = "calc.view.CalculatorView$Handler";

//		String[] fileList = new String[2];
//		fileList[0] = "calc.noSwing.JButton";
//		fileList[1] = "calc.view.CalculatorView$Handler";
		
		for (String f : fileList) {
			if (f != null) {
				try {
					System.out.println("STUART opening "+f);
					GG.createCFG(cfg, f);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		GG.cleanup_any_pure_interface(cfg);
		String startNodeName = "calc.view.CalculatorView.equals(Lcalc/view/CalculatorView;)V";
		String finalNodeName = "calc.model.CalculatorModel.equals()V";
		BFSNode Start = cfg.getNodeMatching(startNodeName);
		BFSNode End   = cfg.getNodeMatching(finalNodeName);
		System.out.println("STUART BFSGraph\n" + cfg.toString());
		System.out.println("GM Looking For StartNode = " + startNodeName);
		System.out.println("GM Looking For EndNode   = " + finalNodeName);
		if (Start != null && End != null) {
			BFSGraph reducedGraph = cfg.getPaths(Start, End);
			if (reducedGraph != null) {
				System.out.println(reducedGraph.toString() + "\n\n");
				try {
					reducedGraph.write_to_file("C:\\Users\\StuartSiroky\\Documents\\calcNoSwing_Reduced.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} else {
				System.out.println("WARNING: no reduced graph found\n");
			}
		} else {
			if (Start == null) {
				System.out.println("WARNING: could not find Start "
						+ startNodeName + "\n");
			}
			if (End == null) {
				System.out.println("WARNING: could not find Final "
						+ finalNodeName + "\n");
			}
		}
		System.out.println("=========================");
		System.out.println("=========================\n");

	
	}
}
