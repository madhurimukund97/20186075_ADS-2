import java.util.Scanner;
class PageRank {
	/**
	 * vertices count.
	 */
	private int vertices;
	/**
	 * Digraph object.
	 */
	private Digraph dg;
	/**
	 * Constructs the object.
	 *
	 * @param      dg1   The dg 1
	 */
	PageRank(Digraph dg1) {
		this.dg = dg1;
	}
	/**
	 * Gets the pr.
	 *
	 * @param      v     { parameter_description }
	 *
	 * @return     The pr.
	 */
	public double getPR(int v) {
		double prvalue = 0.0;
		return prvalue;

	}
	/**
	 * Returns a string representation of the object.
	 *
	 * @return     String representation of the object.
	 */
	public String toString() {
        System.out.println(
            dg.vertex() + " vertices" + ", " + dg.edge() + " edges");
        System.out.println(dg.toString());
        System.out.println("\n");
        String str = "";
        return str;
	}
}
/**
 * Class for web search.
 */
class WebSearch {
	private PageRank pg;
	private String filename;
	WebSearch(PageRank pg1, String filename1) {
		this.pg = pg1;
		this.filename = filename1;
	}
	public int iAmFeelingLucky(String query) {
		return -1;

	}

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		String vertices = StdIn.readLine();
		Digraph dg = new Digraph(Integer.parseInt(vertices));
		PageRank pg = new PageRank(dg);
		while (StdIn.hasNextLine()) {
			String[] adjlist = StdIn.readLine().split(" ");
			for (int i = 0; i < adjlist.length; i++) {
				dg.addEdge(Integer.parseInt(
					adjlist[0]),Integer.parseInt(adjlist[i]));
			}
		}

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		WebSearch wsobj = new WebSearch(pg,file);
		// and pass the page rank object and the file path to the constructor
		while (StdIn.hasNextLine()) {
			String[] searchquery = StdIn.readLine().split("=");
			String searchword = searchquery[1];
			System.out.println(wsobj.iAmFeelingLucky(searchword));
		}
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
