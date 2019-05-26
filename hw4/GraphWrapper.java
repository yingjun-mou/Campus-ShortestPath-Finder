package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import hw4.Graph;

public class GraphWrapper<T, E extends Comparable<E>>{
	private Graph<T,E> graph;
	
	/**
	 * constructor for graph adapter
	 * 
	 * @effects creates graph for graph adapter
	 */
	public GraphWrapper() {
		this.graph = new Graph<T,E>();
	}
	/**
     * @param node
     * @effects adds the node to graph G
     */
	public void addNode(T node) {
		graph.addNode(node);
	}
	
	/**
     * @param parent, child, value for an edge
     * @effects adds the edge to graph G if the edge is valid
     */
	public void addEdge(T parent, T child, E property) {
		//if(parent == child) {return;}
		graph.addEdge(parent,child,property);
	}
	
	/**
	 * get iterator function
	 * 
	 * @return a list of nodes
	 */
	public Iterator<T> listNodes(){
		List<T> nodes = new ArrayList<T>(graph.getNodes().keySet());
		Iterator<T> itr = nodes.iterator();
		return itr;
	}
	
	/**
	 * get iterator for list of nodes reachable from node represented by a string
	 * 
	 * @param  target node parent
	 * @return iterator for node list or null if parent has no children
	 */
	public Iterator<String> listChildren(T node){
		T target = node;
		Set<Edge<T,E>> alledges = this.graph.getEdges(target);
		List<String> children = new ArrayList<String>();
		for(Edge<T, E> e : alledges) {
			children.add(e.getChild()+"("+e.getName()+")");
		}
		Collections.sort(children);
		Iterator<String> itr = children.iterator();
		return itr;
	}
	
}