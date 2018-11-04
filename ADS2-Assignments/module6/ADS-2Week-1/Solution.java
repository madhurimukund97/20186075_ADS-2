/**
 * Class for page rank.
 */
class PageRank {
    /**
     * { var_description }.
     */
    LinearProbingHashST<Integer, Double> lst;
    /**
     * vertices count.
     */
    private int vertices;
    /**
     * Digraph object.
     */
    private Digraph dg;
    /**
     * prvalue.
     */
    // private double prvalue = 0.0;
    /**
     * Constructs the object.
     *
     * @param      dg1   The dg 1
     */
    PageRank(final Digraph dg1) {
        this.dg = dg1;
    }
    /**
     * Gets the pr.
     *
     * @param      v     { parameter_description }
     *
     * @return     The pr.
     */
    public double getPR(final int v) {
        lst = new LinearProbingHashST<Integer, Double>();
        // double pas = 0.0;
        double prvalue = 0.0;
        for (int i = 1; i < dg.vertex(); i++) {
            lst.put(i, 1.0/dg.vertex());
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < dg.vertex(); j++) {
                
                for(int h : dg.adj(j)) {
                    if (dg.outdegree(h) == 0) {
                        prvalue = (double)1 / dg.outdegree(j);
                        lst.put(j, prvalue);
                    }
                    else {
                        prvalue = 1.0 / dg.vertex();
                        lst.put(j, prvalue);  
                    }
                }
            }
        }   
        //     // // for (int j = 1; j < v; j++) {
        //     //     if (dg.outdegree(j) == 0) {

        //     //     }
        //     //     
        //     //     for (int k : adj) {
        //     //         k++;
        //     //     }
        //     // }
        // }
        // for (int i = 0; i < dg.vertex(); i++) {
        //     if (dg.outdegree(i) == 0) {
        //         pas = (double) 1 / dg.vertex();
        //         lst.put(i, pas);
        //     } else {
        //         pas = (double) 1 / dg.vertex();
        //         lst.put(i, pas);
        //     }
        // }
        return prvalue;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public void display() {
        // System.out.println(
            // dg.vertex() + " vertices" + ", " + dg.edge() + " edges");
        // System.out.println();
        String str = "";
        // System.out.println(dg.toString());
        // System.out.println();
        for (int i = 0; i < dg.vertex(); i++) {
            str = i + " - " + getPR(dg.vertex());
            System.out.println(str);
        }
        // return str;
    }
}
/**
 * Class for web search.
 */
class WebSearch {
    /**
     * { var_description }.
     */
    private PageRank pg;
    /**
     * { var_description }.
     */
    private String filename;
    /**
     * Constructs the object.
     *
     * @param      pg1        The page 1
     * @param      filename1  The filename 1
     */
    WebSearch(final PageRank pg1, final String filename1) {
        this.pg = pg1;
        this.filename = filename1;
    }
    /**
     * { function_description }.
     *
     * @param      query  The query
     *
     * @return     { description_of_the_return_value }
     */
    public int iAmFeelingLucky(final String query) {
        return -1;

    }
}
/**
 * Class for solution.
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
        // read the first line of the input to get the number of vertices
        String vertices = StdIn.readLine();
        Digraph dg = new Digraph(Integer.parseInt(vertices));
        PageRank pg = new PageRank(dg);
        while (StdIn.hasNextLine()) {
            String[] adjlist = StdIn.readLine().split(" ");
            for (int i = 1; i < adjlist.length; i++) {
                dg.addEdge(Integer.parseInt(
                    adjlist[0]),Integer.parseInt(adjlist[i]));
            }
        }
        // System.out.println(pg);
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
        System.out.println(dg.toString());
        pg.display();
        // System.out.println("\n");
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
