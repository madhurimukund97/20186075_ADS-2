import java.util.Scanner;
/**
 * Client program.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor not used.
    }
    /**
     * Main class.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int numofvertices = Integer.parseInt(s.nextLine());
        int numofedges = Integer.parseInt(s.nextLine());
        Digraph digraph = new Digraph(numofedges);
        while (s.hasNext()) {
            String[] tokens = s.nextLine().split(" ");
            digraph.addEdge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]));
        }
        DirectedCycle dircycle = new DirectedCycle(digraph);
        if (dircycle.hasCycle()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not bipartite");
        }
    }
}
