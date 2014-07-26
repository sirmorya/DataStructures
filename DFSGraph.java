package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This method is used for the Depth First Traversal of a graph starting at root node "root".
 * 
 * @author ankit
 */
public class DFSGraph {

	public static void main(String[] args) {
		Graph g = new Graph();
	    g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
	    g.addEdge(2, 0);
	    g.addEdge(2, 3);
	    g.addEdge(3, 3);
	    new DFSGraph().DFT(g, new GraphNode(2));
	}
	
	/**
	 * This method is used for the Depth First Traversal of a graph starting at root node "root". 
	 * @param g
	 * @param root
	 */
	public void DFT(Graph g, GraphNode root){
		//Creating a stack
		Stack stack = new Stack<GraphNode>();
		//Creating a set of visited nodes
		List visitedList = new ArrayList<GraphNode>();
		//adding the root node in the visited list
		visitedList.add(root);
		stack.add(root);
		GraphNode tempNode = null, node = null;
		while(!stack.isEmpty()){
			tempNode = (GraphNode)stack.pop();
			System.out.println(tempNode.val);
			for(GraphNode gNode : g.adjacentList.get(tempNode)){
				if(!visitedList.contains(gNode)){
					stack.push(gNode);
					visitedList.add(gNode);
				}
			}
		}
	}
}
