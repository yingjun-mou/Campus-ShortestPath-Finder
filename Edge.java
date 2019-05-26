package hw4;

public class Edge<T, E extends Comparable<E>> implements Comparable<Edge<T, E>> {

	private T parent;// Represent the starting node
	private T child;// Represent the ending node
	private E name;// Name of the node

	/**
	 * constructor
	 * 
	 * @param parent
	 * @param child
	 * @param edgename
	 * @effects creates a new edge object with starting node, ending node, and given
	 *          name
	 */
	public Edge(T parent, T child, E str) {

		this.parent = parent;
		this.child = child;
		this.name = str;
		checkRep();
	}

	/**
	 * check representation
	 * 
	 * @throws exception
	 *             when rep invariants is not hold
	 */
	public void checkRep() {
		if (parent == null || child == null || name == null) {
			throw new RuntimeException("Edge properties can never be null.");
		}
	}

	/**
	 * @return the parent node
	 */
	public T getParent() {
		return this.parent;
	}

	/**
	 * @return the child node
	 */
	public T getChild() {
		return this.child;
	}

	/**
	 * @return the name of the edge
	 */
	public E getName() {
		return this.name;
	}

	@Override
	public int compareTo(Edge<T, E> o) {
		// Auto-generated method stub
		return this.name.compareTo(o.name);
	}

}