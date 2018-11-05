/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * variable vertex.
     */
    private final int V;
    /**
     * variable edge.
     */
    private int E;
    /**
     * bag of adjacent vertices list.
     */
    private Bag<Edge>[] adj;
    /**
     * Constructs the object.
     *
     * @param      V integer vertex.
     */
    public EdgeWeightedGraph(final int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    /**
     * return vertices.
     *
     * @return vertex count
     */
    public int V() {
        return V;
    }
    /**
     * return edges.
     *
     * @return edge count
     */
    public int E() {
        return E;
    }
    /**
     * Adds an edge.
     *
     * @param      e Edge object
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    /**
     * returns adjacent vertex.
     *
     * @param      v integer.
     *
     * @return  adjacent vertex.
     */
    public Iterable<Edge> adj(final int v) {

        return adj[v];
    }
    /**
     * degree that gives size of adjacent list.
     *
     * @param      v vertex.
     *
     * @return degree.
     */
    public int degree(final int v) {

        return adj[v].size();
    }
    /**
     * edges that iterates and add.
     *
     * @return iterable list.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
}
