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
	 * Client program.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(s.nextLine());
		int k = Integer.parseInt(s.nextLine());
		EdgeWeightedGraph edg = new EdgeWeightedGraph(n);
		for (int i = 0; i < k; i++) {
			String[] tokens = s.nextLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			double d = Double.parseDouble(tokens[2]);
			Edge edgobj = new Edge(a, b, d);
			edg.addEdge(edgobj);
		}
		String caseToGo = s.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(edg);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths,
			// where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] line = s.nextLine().split(" ");
			int src = Integer.parseInt(line[0]);
			int dest = Integer.parseInt(line[1]);
			DijkstraUndirectedSP obj = new DijkstraUndirectedSP(edg, src);
			if(obj.hasPathTo(dest)) {
				System.out.println(obj.distTo(dest));
			} else {
				System.out.println("No Path Found.");
			}
			break;
		case "ViaPaths":
			// Handle the case of ViaPaths,
			// where three integers are given.
			// First is the source and
			// second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;
			
		default:
			break;
		}
	}
}
