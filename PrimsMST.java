package datastructures;

import java.util.ArrayList;
import java.util.List;


//Code containing the adjacency list node

class AdjacencyNode{
	int src;
	int dest;
	int weight;
	
	public AdjacencyNode(int desc, int weight){
		this.dest = desc;
		this.weight = weight;
	}
}


class PrimGraph{
	
	int v;
	List<AdjacencyNode>[] adjNode; 
	
	public PrimGraph(int v){
		this.v = v;
		adjNode = (List<AdjacencyNode>[]) new List[v];
		for(int i = 0; i < v; i++){
			adjNode[i] = new ArrayList<AdjacencyNode>();
		}
	}
	
	/**
	 * This is used to perform an undirected graph add operation.
	 * 
	 * @param src
	 * @param dest
	 * @param weight
	 */
	void addEdge(int src, int dest, int weight){
		
		adjNode[src].add(new AdjacencyNode(dest, weight));
		adjNode[dest].add(new AdjacencyNode(src, weight));
		
	}
	
}


/**
 * Structure to represent a min heap node
 * @author ankitsirmorya
 *
 */
class MinHeapNode{
	int v, key;
	
	public MinHeapNode(int v , int k){
		this.v = v;
		this.key = k;
	}
	
}

/**
 * Class to represent a min heap.
 * 
 * @author ankitsirmorya
 *
 */
class MinHeap{
	
	  int size;      // Number of heap nodes present currently
	  int capacity;  // Capacity of min heap
	  int[] pos;     // This is needed for decreaseKey()
	  MinHeapNode[] array;
	  
	  public MinHeap(int capacity){
		  this.capacity = capacity;
		  pos = new int[capacity];
		  array = new MinHeapNode[capacity];
	  }
	  
	
    /**
	 * This method is used to do the min heap operation from aparticular index.
	 * @param heap
	 * @param idx
	 */
	void minHeapify( int idx) {
		int smallest, left, right;
		smallest = idx;
		left = 2 * idx + 1;
		right = 2 * idx + 2;

		if (left < size && array[smallest].key > array[left].key)
			smallest = left;
		if (right < size
				&& array[smallest].key > array[right].key)
			smallest = right;

		if (smallest != idx) {
		

			pos[array[smallest].v] = idx;
			pos[array[idx].v] = smallest;

			//Swapping values
			MinHeapNode t = array[smallest];
			array[smallest] = array[idx];
			array[idx] = t;
			
			minHeapify(smallest);

		}

	}
	
	// A utility function to check if a given vertex
	// 'v' is in min heap or not
	boolean isInMinHeap(int v)
	{
	   if (pos[v] < size)
	     return true;
	   return false;
	}
	  
	  // A utility function to check if the given minHeap is ampty or not
	  boolean isEmpty(){
		  return this.size == 0;
	  }
	  
	  
    /**
	 * Standard function to extract minimum node from heap.
	 * 
	 * @return
	 */
	MinHeapNode extractMin(){
		
		if(size == 0) return null;
		
		//Store the root node as it is the minimum node in the heap.
		MinHeapNode root = this.array[0];
		
		//Moving the last node to the root position.
		MinHeapNode  lastNode  = this.array[this.size - 1];
		array[0] = lastNode;
		
		//Update the position of the last elelment
		pos[root.v] = size -1;
		pos[lastNode.v] = 0;
		
		// Reduce heap size and heapify root
		size --;
		minHeapify( 0);
		
		return root;
		  
	}
	
	
	/**
	 * This method is used to decrease the key value of a vertex.
	 * 
	 * @param v
	 * @param key
	 */
	void decreaseKey(int v, int key){
		
		//Getting position of the vertex in the heap
		int i = pos[v];
		
		array[i].key  = key;
		
		while(i >0 && array[i].key < array[(i-1)/2].key){
			
			 // Swap this node with its parent
			pos[array[i].v] = (i - 1)/2;
			pos[array[(i-1)/2].v] = i;
			
			//Swapping values
			MinHeapNode t = array[i];
			array[i] = array[(i - 1)/2];
			array[(i - 1)/2] = t;
			
			// Move the node to it's parent's index
			i = (i - 1)/2;
			
		}
		
	}
	  
}



public class PrimsMST {
	
	private int INT_MAX = 999999;

	public static void main(String[] args) {
		
		// Let us create the graph given in above fugure
	    int V = 9;
	    PrimGraph graph = new PrimGraph(V);
	    graph.addEdge( 0, 1, 4);
	    graph.addEdge( 0, 7, 8);
	    graph.addEdge( 1, 2, 8);
	    graph.addEdge( 1, 7, 11);
	    graph.addEdge( 2, 3, 7);
	    graph.addEdge( 2, 8, 2);
	    graph.addEdge( 2, 5, 4);
	    graph.addEdge( 3, 4, 9);
	    graph.addEdge( 3, 5, 14);
	    graph.addEdge( 4, 5, 10);
	    graph.addEdge( 5, 6, 2);
	    graph.addEdge( 6, 7, 1);
	    graph.addEdge( 6, 8, 6);
	    graph.addEdge( 7, 8, 7);
	    
	    
	    new PrimsMST().primMST(graph);
	}
	
	void primMST(PrimGraph graph){
		
		int v = graph.v;
		
		// Array to store constructed MST
		int parent[] = new int[v];
		int key[] = new int[v];
		
		MinHeap heap = new MinHeap(v);
		
		//Initialising the initialising the values in a heap. 
		for(int i = 1; i < v; i++){
			parent[i] = -1;
			key[i] = INT_MAX;
			heap.array[i] = new MinHeapNode(i, key[i]);
			heap.pos[i] = i;
		}
		
		// Make key value of 0th vertex as 0 so that it
	    // is extracted first
	    key[0] = 0;
	    heap.array[0] = new MinHeapNode(0, key[0]);
	    heap.pos[0]   = 0;
	    
	    heap.size = v;
	    
	    //Initially the heap contains all the nodes.
	    while(!heap.isEmpty()){
	    	
	    	MinHeapNode minHeapNode = heap.extractMin();
	    	int u = minHeapNode.v;
	    	
	    	for(AdjacencyNode node : graph.adjNode[u]){
	    		
	    		int w = node.dest;
	    		
	    		//if "w" isn't included in the MST and the weight of (u - w) is less than key value of "w" then update it's key[w] and parent[w]
	    		if(heap.isInMinHeap(w) && node.weight < key[w]){
	    			key[w] = node.weight;
	    			parent[w] = u;
	    			heap.decreaseKey(w, key[w]);
	    		}
	    		
	    	}
	    	
	    	
	    }
	    
	    for (int i = 1; i < v; ++i)
	       System.out.println(parent[i]+" - > "+ i);
		
	}
	
	
	
	
}
