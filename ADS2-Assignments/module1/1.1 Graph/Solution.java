import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * number of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    int vertex();
    /**
     * number of edges.
     *
     * @return     { description_of_the_return_value }
     */
    int edge();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * Iterator..
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for graph.
 */
class GraphAdj implements Graph {
    /**
     * Integer vertex.
     */
    private int vertex;
    /**
     * Integer edge.
     */
    private int edge;
    /**
     * Bag of adjacent edges.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    GraphAdj() {
        //default constructor.
    }
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     */
    GraphAdj(final int v) {
        this.vertex = v;
        this.edge = 0;
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Number of vertices.
     *
     * @return     { returns Integer vertex }.
     */
    public int vertex() {
        return vertex;
    }
    /**
     * Number of edges.
     *
     * @return     { returns Integer edge }.
     */
    public int edge() {
        return edge;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            edge++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * Iterator used to know adjacent vertices.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int i : adj[v]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * Adjacency list representation.
     *
     * @param      v          { parameter_description }
     * @param      e          { parameter_description }
     * @param      list       The list
     *
     * @throws     Exception  { exception_description }
     */
    public void listrepr(final int v, final int e,
        final String[] list) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(
                vertex() + " vertices" + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(
                vertex() + " vertices" + ", " + edge() + " edges");
            for (int i = 0; i < list.length; i++) {
            String s = "";
            s = list[i] + ": ";
            for (int k : adj(i)) {
                s = s + list[k] + " ";
            }
            System.out.println(s);
            }
        }
    }
    /**
     * Adjacency matrix representation.
     *
     * @param      v          { parameter_description }
     * @param      e          { parameter_description }
     *
     * @throws     Exception  { exception_description }
     */
    public void matrixrepr(final int v, final int e) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(
                vertex() + " vertices" + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(
                vertex() + " vertices" + ", " + edge() + " edges");
            int[][] matrix = new int[v][v];
            for (int i = 0; i  < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (hasEdge(i, j)) {
                        matrix[i][j] = 1;
                    }
                }
            }
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

/**
 * Client program.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // constructor not used.
    }
    /**
     * Main program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        GraphAdj g = new GraphAdj();
        String str = s.nextLine();
        int v1 = Integer.parseInt(s.nextLine());
        int e1 = Integer.parseInt(s.nextLine());
        String[] tokens = s.nextLine().split(",");
        g = new GraphAdj(v1);
        while (s.hasNext()) {
            String[] path = s.nextLine().split(" ");
            g.addEdge(Integer.parseInt(path[0]),
            Integer.parseInt(path[1]));
        }
        switch (str) {
            case "List":
            try {
                g.listrepr(v1, e1, tokens);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
            case "Matrix":
            try {
                g.matrixrepr(v1, e1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
            default:
            break;
        }
    }
}


