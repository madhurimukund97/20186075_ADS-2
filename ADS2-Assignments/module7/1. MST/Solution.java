import java.util.Scanner;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // constructor not used.
    }
    /**
     * client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int vertices = Integer.parseInt(s.nextLine());
        int edges = Integer.parseInt(s.nextLine());
        EdgeWeightedGraph edgeobj = new EdgeWeightedGraph(vertices);
        while (s.hasNextLine()) {
            String[] tokens = s.nextLine().split(" ");
            Edge obj = new Edge(
                Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Double.parseDouble(tokens[2]));
            edgeobj.addEdge(obj);
        }
        LazyPrimMST mst = new LazyPrimMST(edgeobj);
        System.out.printf("%.5f\n", mst.weight());
    }
}
