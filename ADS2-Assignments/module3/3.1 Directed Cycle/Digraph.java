import java.util.NoSuchElementException;
/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * Class digraph.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * number of vertices in this digraph.
     */
    private final int vertex;
    /**
     * number of edges in this digraph.
     */
    private int edge;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<Integer>[] adj;
    /**
     * indegree[v] = indegree of vertex v.
     */
    private int[] indegree;
    
    /**
     * Constructs the object.
     *
     * @param      V     { parameter_description }
     */
    public Digraph(final int vertex) {
        if (vertex < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertex = vertex;
        this.edge = 0;
        indegree = new int[vertex];
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int v = 0; v < vertex; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Constructs the object.
     *
     * @param      g    { parameter_description }
     */
    public Digraph(final Digraph g) {
        this(g.vertex());
        this.edge = g.edge();
        for (int v = 0; v < vertex; v++) {
            this.indegree[v] = g.indegree(v);
        }
        for (int v = 0; v < g.vertex(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
        
    /**
     * vertex count.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertex() {
        return vertex;
    }

    /**
     * edge count.
     *
     * @return     { description_of_the_return_value }
     */
    public int edge() {
        return edge;
    }
    /**
     * validate vertex.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertex) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertex - 1));
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edge++;
    }
    /**
     * Iterator.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }
    /**
     * outdegree.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }
    /**
     * indegree.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertex);
        for (int v = 0; v < vertex; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertex + " vertices, " + edge + " edges " + NEWLINE);
        for (int v = 0; v < vertex; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
