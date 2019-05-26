package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In my implementation, Nodes are stored as keys in a HashMap and they have their children as values.
 * Edges are stored in a HashSet, represented by the parent node, child node and its own label/name.
 * It's easy to prevent duplicate by using HashMap and HashSet.
 */

public class Graph<T, E extends Comparable<E>> {
	HashMap<T, HashSet<Edge<T, E>>> graph;

	/**
	 * check representation
	 * 
	 * @throws exception
	 *             when rep invariants is not hold
	 */
	public void checkRep() {
		if (graph == null) {
			throw new RuntimeException("graph cannot be null");
		}
	}

	/**
	 * constructor
	 * 
	 * @effects creates blank graph
	 */
	public Graph() {
		this.graph = new HashMap<T, HashSet<Edge<T, E>>>();
		checkRep();
	}

	/**
	 * add node function
	 * 
	 * @param node
	 *            to be added to graph
	 * @modifies graph if node was not contained
	 * @throws exception
	 *             if node is null
	 */
	public void addNode(T node) {
		if (node == null) {
			throw new RuntimeException("Node cannot be null");
		}
		if (!graph.containsKey(node)) {
			graph.put(node, new HashSet<Edge<T,E>>());
			checkRep();
		}
	}

	/**
	 * add edge function
	 * 
	 * @param edge
	 *            to be added to graph
	 * @modifies graph if related node was not contained by adding those node(s)
	 */
	public void addEdge(T parent, T child, E str) {
		if ((graph.containsKey(parent)) && (graph.containsKey(child))) {
			Edge<T, E> newedge = new Edge<T,E>(parent, child, str);
			Set<Edge<T, E>> list = graph.get(parent);
			list.add(newedge);
			checkRep();
		}
		else if (graph.containsKey(parent)){
			graph.put(child, new HashSet<Edge<T,E>>());
			Edge<T, E> newedge = new Edge<T,E>(parent, child, str);
			Set<Edge<T, E>> list = graph.get(parent);
			list.add(newedge);
			checkRep();
		}
		else {
			graph.put(parent, new HashSet<Edge<T,E>>());
			graph.put(child, new HashSet<Edge<T,E>>());
			Edge<T, E> newedge = new Edge<T,E>(parent, child, str);
			Set<Edge<T, E>> list = graph.get(parent);
			list.add(newedge);
			checkRep();
		}
	}

	/**
	 * @returns a map of all the nodes in the graph
	 */
	public HashMap<T, HashSet<Edge<T, E>>> getNodes() {
		return new HashMap<T, HashSet<Edge<T,E>>>(graph);
	}

	/**
	 * @returns true if the graph contains node
	 */
	public boolean hasNode(T node) {
    		return graph.containsKey(node);
  	}
	
	/**
	 * @returns true if the graph contains edge
	 */
	public boolean hasEdge(T parent, T child, E str) {
		if (parent == null || child == null || str == null) {return false;}
		
		T node = parent;
		HashSet<Edge<T,E>> target = this.graph.get(node);
		if (target == null) {return false;}
		for (Edge<T,E> e : target) {
  	        if (e.getParent().equals(parent) && e.getChild().equals(child) && e.getName().equals(str))
  	            return true;
  	    }
		return false;
	}
	public Set<Edge<T, E>> getEdges(T target) {
		if (target == null) {
			throw new RuntimeException("target can't be null");
		}
		
		Set<Edge<T, E>> ret = new HashSet<Edge<T, E>>();
		
		if (this.graph.get(target) == null) {
			return ret;
		}
		
		for (Edge<T, E> e : this.graph.get(target)) {
			ret.add(e);
		}
		
		return ret;
	}

	/**
	 * Help list children for hw5
	 * 
	 * @return children of current node
	 */
	public List<String> listChildren(T node){
		T target = node;
		Set<Edge<T,E>> alledges = this.graph.get(target);
		List<String> children = new ArrayList<String>();
		//if(alledges != null) {
		for(Edge<T, E> e : alledges) {
			children.add(e.getChild()+"("+e.getName()+")");
		}
		Collections.sort(children);//}
		return children;
	}









}