package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 class GraphListImpl {
	
	int v;
	List<Integer>[] adj;
	
	public GraphListImpl(int n){
		v = n;
		adj = (List<Integer>[]) new List[n];
		for (int i = 0; i < n; i++) 
            adj[i] = new ArrayList<Integer>(v);
	}
	
	public void addEdge(int i, int j) {
	        adj[i].add(j);
	        adj[j].add(i);
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

}
 
 public class GraphColoring{
	 
	public static void main(String[] args) {
		GraphListImpl g1 = new GraphListImpl(5);
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
	    g2.greedyColoring();
	}
	
	
	
 }
