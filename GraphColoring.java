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
            adj[i] = new ArrayList<Integer>();
	}
	
	public void addEdge(int i, int j) {
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
	    
	}
 }
