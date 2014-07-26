package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * This class is used to perform breadth first traversal in a graph.
 * 
 * @author ankit
 *
 */
public class BFSGraph {

	public static void main(String[] args) {
		Graph g = new Graph();
	    g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
	    g.addEdge(2, 0);
	    g.addEdge(2, 3);
	    g.addEdge(3, 3);
	    new BFSGraph().BST(g, new GraphNode(2));
	}
	
	/**
	 * This is the method to perform breadth first traversal in a graph starting from rootNode "root".
	 * @param g
	 * @param root
	 */
	public void BST(Graph g, GraphNode root){
		//creating a queue
		Queue queue = new LinkedList<GraphNode>();
		GraphNode node = null;
		queue.add(root);
		//creating a set to store the list of visited nodes.
		List<GraphNode> visitedList = new ArrayList<GraphNode>();
		visitedList.add(root);
		while(!queue.isEmpty()){
			node = (GraphNode)queue.peek();
			System.out.print(node.val+"=>");
			queue.poll();
			for(GraphNode curNode : g.adjacentList.get(node)){
				//checking whether the node exists in the list of visited set of nodes
				if(!(visitedList.contains(curNode))){
					visitedList.add(curNode);
					queue.add(curNode);
				}
			}
			
			
		}
	}
}
