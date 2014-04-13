package graphs;

/**
 * This is a hamilton cycle implementation in a graph.
 * 
 * @author ankit
 *
 */
public class HamiltonCycle {

	public static void main(String[] args) {
		boolean graph1[][] = {{false, true, false, true, false},
                {true, false, true, true, true},
                {false, true, false, false, true},
                {true, true, false, false, true},
                {false, true, true, true, false},
               };
		new HamiltonCycle().hamCycle(graph1);
		System.out.println();
		boolean graph2[][] = {{false, true, false, true, false},
                {true, false, true, true, true},
                {false, true, false, false, true},
                {true, true, false, false, false},
                {false, true, true, false, false},
               };

		// Print the solution
		new HamiltonCycle().hamCycle(graph2);

	}
	
	
	/**
	 * This function solves the Hamiltonian Cycle problem using Backtracking.
  	   It mainly uses hamCycleUtil() to solve the problem. It returns false
  	   if there is no Hamiltonian Cycle possible, otherwise return true and
       prints the path. Please note that there may be more than one solutions,
  	   this function prints one of the feasible solutions.
	 * @param graph
	 */
	void hamCycle(boolean graph[][]){
		
		int path[] = new int[graph[0].length];
		
		for(int i = 0; i < path.length ; i++)
			path[i] = -1;
		
		 /* Let us put vertex 0 as the first vertex in the path. If there is
	       a Hamiltonian Cycle, then the path can be started from any point
	       of the cycle as the graph is "undirected" */
		path[0] = 0;
		if(!hamCycleUtil(graph, path, 1)){
			System.out.println("Solution doesn't exist");
			return;
		}
			
	System.out.println("Solution exists");
	for(int i : path){
		System.out.print(i +" - > " );
	}
		
	}
	
	/**
	 * This method checks if the vertex to be added is safe to be in the hamilton cycle.
	 * @param v
	 * @param graph
	 * @param path
	 * @param pos
	 * @return
	 */
	boolean isSafe(int v, boolean graph[][],int path[], int pos){
		//check if the vertex is connected to the vertex at the previous position
		if(!graph[path[pos-1]][v])
			return false;
		//check if the vertex already exists in the path
		for(int i = 1; i < pos; i++)
			if(path[i] == v)
				return false;
		return true;		
	}
	
	/**
	 * A recursive utility function to solve hamiltonian cycle problem
	 * 
	 * @param graph
	 * @param path
	 * @param pos
	 * @return
	 */
	boolean hamCycleUtil(boolean graph[][], int path[], int pos){
		
		int v = graph[0].length; 
		// base case :  when all the vertices are included in the path
		if(pos == v){
			// check whether a vertex exists from the last vertex of the path to
			// the starting vertex
			return (graph[path[pos-1]][path[0]])?true : false;
				
		}
		
		for( int i = 1; i < v; i++){
			//check if the vertex can be added to the hamilton cycle
			if(isSafe(i, graph, path, pos)){
				path[pos] = i;
				
				/* recur to construct rest of the path */
	            if (hamCycleUtil (graph, path, pos+1) == true)
	                return true;
	 
	            /* If adding vertex v doesn't lead to a solution,
	               then remove it */
	            path[pos] = -1;
				
			}
		}
		/* If no vertex can be added to Hamiltonian Cycle constructed so far,
	       then return false */
		return false;
	}
	
	
	
	
}
