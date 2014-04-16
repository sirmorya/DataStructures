package graphs;

import java.util.ArrayList;
import java.util.Arrays;

class Edge implements Comparable<Edge>{
	int src, dest, weight;

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		
		if(this.weight > o.weight)
			return 1;
		else if(this.weight == o.weight)
			return 0;
		else
			return -1;
	}
	
	
	
}

class MSTGraph{
	int v, e;//vertices and edges
	Edge[] edges;
	public MSTGraph(int v, int e){
		this.v = v;
		this.e = e;
		edges = new Edge[e];
		for(int i=0 ;i < e; i++ )
			edges[i] = new Edge();
		
	}
	
}

/**
 * @author ankit
 *
 */
public class KruskalMST {

	public static void main(String[] args) {
		MSTGraph graph = new MSTGraph(4, 5);
		
		// add edge 0-1
	    graph.edges[0].src = 0;
	    graph.edges[0].dest = 1;
	    graph.edges[0].weight = 10;
	 
	    // add edges 0-2
	    graph.edges[1].src = 0;
	    graph.edges[1].dest = 2;
	    graph.edges[1].weight = 6;
	 
	    // add edges 0-3
	    graph.edges[2].src = 0;
	    graph.edges[2].dest = 3;
	    graph.edges[2].weight = 5;
	 
	    // add edges 1-3
	    graph.edges[3].src = 1;
	    graph.edges[3].dest = 3;
	    graph.edges[3].weight = 15;
	 
	    // add edges 2-3
	    graph.edges[4].src = 2;
	    graph.edges[4].dest = 3;
	    graph.edges[4].weight = 4;
	    	
	    new KruskalMST().kruskalMST(graph);
	    
	}
	
	class SubSet{
		int parent;
		//here rank denotes the number of nodes in a subset
		int rank;
	}
	
	/**
	 * A Utility method to find subset of an element i using path compression technique;
	 * @param subsets
	 * @param i
	 * @return
	 */
	int find(SubSet[] subsets,int i){
		
		if(subsets[i].parent != i)
			subsets[i].parent = find(subsets , subsets[i].parent);
		return subsets[i].parent;
	}
	
	
	/**
	 * Function that performs union of x and y using union by rank algorithm
	 * @param subsets
	 * @param x
	 * @param y
	 */
	void union(SubSet[] subsets, int x, int y){
		
		//finding the root of the two subsets
		//TODO - Check if it can be avoided
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);
		
		//The subset having lesser number of nodes will point to the one having larger number of nodes
		if(subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if(subsets[xroot].rank < subsets[yroot].rank)
			subsets[yroot].parent = xroot;
		else{
			// If ranks are same, then make one as root and increment
		    // its rank by one
			subsets[xroot].parent = yroot;
			subsets[yroot].rank ++;
		}
	}
	
	/**
	 * Kriskal Algo to find the minimum spanning tree.
	 * @param graph
	 */
	void kruskalMST(MSTGraph graph){
		
		//Sort the edges based on their weights
		Arrays.sort(graph.edges);
		
		//Initialise the elements of the subset
		SubSet[] subsets = new SubSet[graph.v];
		for(int i = 0; i< graph.v; i++){
			subsets[i] = new SubSet();
		}
		ArrayList<Edge> results = new ArrayList<Edge>();
		for(int i =0 ;i < graph.v ; i++){
			subsets[i].rank = 0;
			subsets[i].parent = i;
		}
		
		//Finding the set for the source and destination of each edge
		for(Edge e : graph.edges){
			int x = find(subsets , e.src);
			int y = find(subsets , e.dest);
			if( x != y){
				results.add(e);
				//Union of the two subsets
				union(subsets, x, y);
			}
		}
		
		for(Edge e : results){
			System.out.println(e.src+"--->"+e.dest+" has weight = "+e.weight);
		}
		
	}
	
	
}
