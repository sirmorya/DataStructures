package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

 class GraphListImpl {
	
	int v;
	List<Integer>[] adj;
	
	public GraphListImpl(int n){
		v = n;
		adj = (List<Integer>[]) new List[n];
		for (int i = 0; i < n; i++) 
            adj[i] = new ArrayList<Integer>(v);
	}
	
	/**
	 * This method is used to add edges in an undirected graph.
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	        adj[j].add(i);
	}
	
	/**
	 * This method is used to add edges in a directed graph.
	 * @param i
	 * @param j
	 */
	public void addEdgeDirected(int i, int j) {
        adj[i].add(j);
    }
	
	
	public void removeEdge(int i, int j) {
        Iterator<Integer> it = adj[i].iterator();
        while (it.hasNext()) {
            if (it.next() == j) {
                it.remove();
                return;
            }
        }    
    }
	
	boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }
	
	List<Integer> outEdges(int i) {
	    return adj[i];
	}
	
/**
 * Basic Greedy Coloring Algorithm:

1. Color first vertex with first color.
2. Do following for remaining V-1 vertices.
          a) Consider the currently picked vertex and color it with the 
             lowest numbered color that has not been used on any previously
             colored vertices adjacent to it. If all previously used colors 
             appear on vertices adjacent to v, assign a new color to it.
             
 */
void greedyColoring(){
		
		int result[] = new int[v];
		boolean available[] = new boolean[v];
		result[0] = 0;
		
		for(int u = 1; u < v; u++){
			result[u] = -1;
		}
		
		for(int u = 1; u < v; u++){
						 
			 for(Integer i: adj[u]){
				 if(result[i] != -1)
					 available[result[i]] = true;
			 }
			 				
			 int cr;
			 for(cr = 0; cr < v;cr++)
				 if(!available[cr])
					 break;
			 result[u] = cr;
			 
			 for(Integer i: adj[u]){
				 if(result[i] != -1)
					 available[result[i]] = false;
			 }
		}
		
		for(int u = 0; u < v; u++)
			System.out.println("Vertex : "+ u + "  ---> "+result[u]);
	}

    /**
     * This method is used to find if a path exists between startVertex and endVertex using DFS
     * approach.
     *  
     * @param startVertex
     * @param endVertex
     * @return
     */
    boolean isDFSPath(int startVertex, int endVertex){
	   boolean found = false;
	   
	   Stack<Integer> stack = new Stack<Integer>();
	   int vertex;
	   stack.push(startVertex);
	   ArrayList<Integer> list = new ArrayList<Integer>();
	   do{
		   vertex = stack.pop();
		   if(vertex == endVertex)
			   found = true;
		   else{
			   if(!list.contains(vertex)){
				   
				   list.add(vertex);
				   for(Integer i : adj[vertex]){
					   if(!list.contains(i))
						   stack.add(i);
				   }
			   }
		   }
	   }while(!stack.isEmpty() && !found);
	     
	   return found;
   }
    
    
    /**
     * This method is used to find if a path exists between startVertex and endVertex using BFS
     * approach.
     * @param startVertex
     * @param endVertex
     * @return
     */
    boolean isBFSPath(int startVertex, int endVertex){
    	boolean found = false;
    	Queue<Integer> queue = new LinkedList<Integer>();
    	int vertex;
    	queue.add(startVertex);
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	do{
    		vertex = queue.poll();
    		if(vertex == endVertex)
    			found = true;
    		else if(!list.contains(vertex)){
    			list.add(vertex);
    			for(Integer i : adj[vertex]){
    				if(!list.contains(i))
    					queue.add(i);
    			}
    		}
    		
    	}while(!queue.isEmpty() && !found);
    	
    	return found;
    }
    
    /**
     * This method is used to detect if the graph is cyclic based on the DFS approach.
     * 
     * @return
     */
    public boolean isCyclic(){
    	
    	boolean visited[] = new boolean[v];
    	boolean recurStack[] = new boolean[v];
    	
    	for(int i = 0; i < v; i++){
    		visited[i] = false;
    		recurStack[i] = false;
    	}
    	
    	//Calling the recursive function to detect cycle in a graph
    	for(int i = 0; i < v; i++)
    		if(isCyclicUtil(i, visited, recurStack))
    			return true;
    	return false;
    }
    
    private boolean isCyclicUtil(int v, boolean[] vis, boolean[] r){
    	
    	if(!vis[v]){
    		vis[v] = true;
    		r[v] = true;
    		for(Integer i : adj[v]){
    			if(!vis[i] && isCyclicUtil(i, vis, r))
    				return true;
    			else if(r[i])
    				return true;
    		}
    	}
    	r[v] = false;
    	return false;
    }

}
 
 public class GraphColoring{
	 
	public static void main(String[] args) {
		/*Test for Greedy approach based coloring.
		 * 
		 * GraphListImpl g1 = new GraphListImpl(5);
		g1.addEdge(0, 1);
	    g1.addEdge(0, 2);
	    g1.addEdge(1, 2);
	    g1.addEdge(1, 3);
	    g1.addEdge(2, 3);
	    g1.addEdge(3, 4);
	    g1.greedyColoring();
	    System.out.println("Next case:");
	    GraphListImpl g2 = new GraphListImpl(5);
	    g2.addEdge(0, 1);
	    g2.addEdge(0, 2);
	    g2.addEdge(1, 2);
	    g2.addEdge(1, 4);
	    g2.addEdge(2, 4);
	    g2.addEdge(4, 3);
	    g2.greedyColoring();*/
		/*GraphListImpl g1 = new GraphListImpl(5);
		g1.addEdge(0, 1);
	    g1.addEdge(0, 2);
	    g1.addEdge(1, 2);
	    g1.addEdge(1, 3);
	    g1.addEdge(2, 3);
	    g1.addEdge(3, 4);
	    System.out.println("DFS result : "+ g1.isDFSPath(0, 3));
	    System.out.println("BFS result : "+ g1.isBFSPath(0, 3));*/
		
		GraphListImpl g1 = new GraphListImpl(4);
		g1.addEdgeDirected(0, 1);
	    g1.addEdgeDirected(0, 2);
	    g1.addEdgeDirected(1, 2);
	    g1.addEdgeDirected(2, 0);
	    g1.addEdgeDirected(2, 3);
	    g1.addEdgeDirected(3, 3);
	    System.out.println(g1.isCyclic() ? "Graph is cyclic." : "Graph is not cyclic.");
	    
	}
	
	
	
 }
