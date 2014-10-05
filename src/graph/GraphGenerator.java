package graph;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
//import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
//import org.apache.bcel.generic.GotoInstruction;
//import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ReturnInstruction;

import bfsNode.FunctionNode;


public class GraphGenerator {
	public void createCFG(BFSGraph cfg, String className) throws ClassNotFoundException {
		//BFSGraph cfg = new BFSGraph();
		JavaClass jc = Repository.lookupClass(className);
		ClassGen cg = new ClassGen(jc);
		ConstantPool cp = jc.getConstantPool();
		ConstantPoolGen cpg = cg.getConstantPool();
		for (Method m: cg.getMethods()) {
			MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
			//System.out.println("+++++++++++++++++++++++++++++++++++++");
			//System.out.println(cg.getClassName()+"."+m.getName()+m.getSignature());//STUART
			InstructionList il = mg.getInstructionList();
			InstructionHandle[] handles = il.getInstructionHandles();
			String fNodeName = cg.getClassName()+"."+m.getName()+m.getSignature();
			FunctionNode fromNode = new FunctionNode(fNodeName);
			cfg.addNode(fromNode);
			for (InstructionHandle ih: handles) {
				Instruction insn = ih.getInstruction();
				
				if(insn instanceof InvokeInstruction){
					InvokeInstruction ii = (InvokeInstruction) insn;
				
					String cname    = ii.getClassName(cpg);
					String methname = ii.getMethodName(cpg);
					String signame = ii.getSignature(cpg);
					String tNodeName = cname+"."+methname+signame;
					//System.out.println("    "+cname+"."+methname+signame);//STUART
					FunctionNode toNode = new FunctionNode(tNodeName);
					cfg.addNode(toNode);
					cfg.addEdge(fromNode, toNode);
				}
			} //handles
		} //methods
		//return cfg;
	} //createCFG
	
}
