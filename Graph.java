package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class GraphNode{
	int val;
	boolean visited;
	public GraphNode(int u){
		this.val = u;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj != null && obj instanceof GraphNode){
			return this.val == ((GraphNode) obj).val;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.val;
	}
}

public class Graph{
	Map<GraphNode,List<GraphNode>> adjacentList = new HashMap<GraphNode, List<GraphNode>>();
	void addEdge(int u, int v){
		GraphNode uNode = new GraphNode(u);
		GraphNode vNode = new GraphNode(v);
		if(adjacentList.containsKey(uNode)){
			adjacentList.get(uNode).add(vNode);
		}else{
			List list = new ArrayList<GraphNode>();
			list.add(vNode);
			adjacentList.put(uNode, list);
		}
	}
	
	public static void main(String[] args) {
		    Graph g = new Graph();
		    g.addEdge(0, 1);
		    g.addEdge(0, 2);
		    g.addEdge(1, 2);
		    g.addEdge(2, 0);
		    g.addEdge(2, 3);
		    g.addEdge(3, 3);
		    g.displayAdjacencyList();
	}
	
	void displayAdjacencyList(){
		
		 //Generation Results
		  Iterator it = adjacentList.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.print(((GraphNode)pairs.getKey()).val + " = > ");
		        for(GraphNode graphNode :(List<GraphNode>) pairs.getValue()){
		        	System.out.print(graphNode.val+",");
		        }
		        it.remove(); // avoids a ConcurrentModificationException
		        System.out.println();
		    }
		    
	}
}