package jimpleParser;

import graph.BFSGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bfsNode.BFSNode;
import bfsNode.FunctionNode;

public class JimpleParser {

	private BFSGraph cfg = new BFSGraph();
	private BFSNode CallingNode = null;
	private String ClassName = null;
	private String CallingFuncName = null;
	private String CurrFuncName = null;
	private String prevLine = null;
	private Map<String, ArrayList<String>> ClassFuncMap = new HashMap<String, ArrayList<String>>();
	// cfg.addEdge(m0, m1);

	private BufferedReader reader;

	public JimpleParser() {
	}

	public void ReadJimple(String file) throws IOException {
		reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			processLine(line);
			prevLine = line;
			// System.out.println(line);
		}
		System.out.println(cfg.toString());
	}

	private void processLine(String line) {
		String class_match = ".*\\s+class\\s+\\S*\\s+.*";
		String invoke_match = ".*invoke.*";
		// String funcDef_match = ".*public|static.*";
		String main_match = ".*public void main[(].*";
		String opencurly_match = "\\s*[{].*";

		if (line.matches(class_match)) {
			// System.out.println(line);
			extractClassName(line);
			//System.out.println("ClassName " + ClassName);
		} else if (line.matches(main_match)) {
			//System.out.println("Main " + line);
			extractMain(line);
		} else if (line.matches(invoke_match)) {
			//System.out.println("Invoke " + line);
			extractInvoke(line);
		} else if (line.matches(opencurly_match) && 
				!(prevLine.matches(class_match) || prevLine.matches(main_match))) {
			//System.out.println("Func " + prevLine);
			extractFunc(prevLine);
		}

	}

	private void extractMain(String line) {
		// create main node
		// add to function list
		CallingFuncName = ClassName + "main(java.lang.String[])";
		FunctionNode fn = new FunctionNode(CallingFuncName);
		CallingNode = fn;
		cfg.addNode((BFSNode) fn);
		addFuncList(ClassName, "void " + CurrFuncName);
	}

	private void extractFunc(String line) {
		String sArray[] = line.split("\\s+");
		if (sArray.length >= 3) {
			if (!sArray[sArray.length - 1].matches("[<]init[>][()]+")) { //ignore this case
				String rtn_type = sArray[sArray.length - 2];
				CallingFuncName = sArray[sArray.length - 1];
				FunctionNode f = manageFunctionNodes(ClassName,CallingFuncName,rtn_type);
				cfg.addNode(f);
				CallingNode = f;
			}
		}
	}

	private void extractInvoke(String line) {
		if (!line.matches(".*specialinvoke.*")) {
			String sArray[] = line.split("[<>]");
			String substr = sArray[1];
			sArray = substr.split("\\s+");
			if (sArray.length >= 3) {
				String class_n = sArray[sArray.length - 3];
				String rtn_type = sArray[sArray.length - 2];
				CurrFuncName = sArray[sArray.length - 1];
				FunctionNode fn = manageFunctionNodes(class_n, CurrFuncName, rtn_type);
				if (CallingNode != null) {
					cfg.addEdge(CallingNode, fn);
				}
			} else {
				System.out.println("ERROR:: " + sArray.toString());
			}
		}
	}

	private FunctionNode manageFunctionNodes(String class_n, String func_n, String rtn_type) {
		FunctionNode fn = new FunctionNode(class_n + func_n);
		cfg.addNode((BFSNode) fn);
		addFuncList(class_n, rtn_type + " " + func_n);
		return fn;
	}

	private void extractClassName(String line) {
		String sArray[] = line.split("\\s+");
		boolean match = false;
		for (String s : sArray) {
			if (match) {
				ClassName = s + ":";
				break;
			}
			if (s.matches("class")) {
				match = true;
			}
		}
	}

	private void addFuncList(String cname, String funcdef) {
		ArrayList<String> strList;
		// TODO strip off the : from the cname
		if (ClassFuncMap.containsKey(cname)) {
			strList = ClassFuncMap.get(cname);
		} else {
			strList = new ArrayList<String>();
		}
		strList.add(funcdef);
		ClassFuncMap.put(cname, strList);
	}

	public BFSGraph getCfg() {
		return cfg;
	}

}