package graphs;

/**
 * The class find shortest distances between every pair of vertices in a given edge weighted directed Graph.
 * @author ankit
 *
 */
public class FloydWarshall {

	private static int INFINITE = 999999;
	
	public static void main(String[] args) {
		
		 int graph[][] = { {0,   5,  INFINITE, 10},
                 {INFINITE, 0,   3, INFINITE},
                 {INFINITE, INFINITE, 0,   1},
                 {INFINITE, INFINITE, INFINITE, 0}
               };

		 // Print the solution
		 new FloydWarshall().floydWarshell(graph);
		
	}
	
	void floydWarshell(int graph[][]){
		
		int v = graph[0].length;
		int dist[][] = new int[v][v];
		
		for(int i = 0; i < v; i++){
			for(int j = 0; j < v; j++)
				dist[i][j] = graph[i][j];
		}
			
				
		/* Add all vertices one by one to the set of intermediate vertices.
	      ---> Before start of a iteration, we have shortest distances between all
	      pairs of vertices such that the shortest distances consider only the
	      vertices in set {0, 1, 2, .. k-1} as intermediate vertices.
	      ----> After the end of a iteration, vertex no. k is added to the set of
	      intermediate vertices and the set becomes {0, 1, 2, .. k} */
		for(int k =0 ;k < v; k++){
			for(int i = 0; i < v; i++){
				for(int j = 0; j < v; j++){
					if((dist[i][k] + dist[k][j]) < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
		//Printing the shortest distance between each vertices.
		for( int i = 0; i < v; i++){
			for( int j = 0; j < v ; j++){
				System.out.print( (dist[i][j] == INFINITE) ? "INF" : dist[i][j] +"\t"); 
			}
			System.out.println();	
		}
	}
	
	
	
}
