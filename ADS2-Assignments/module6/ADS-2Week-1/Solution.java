import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * { var_description }.
     */
    private double[] prvalue;
    // LinearProbingHashST<Integer, Double> lst;
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
        // lst = new LinearProbingHashST<Integer, Double>();
        // double pas = 0.0;
        prvalue = new double[dg.vertex()];
        double prval = 0.0;
        for (int i = 0; i < dg.vertex(); i++) {
            // lst.put(i, 1.0 / dg.vertex());
            prvalue[i] = 1.0 / (double)dg.vertex();
        }
        
        // System.out.println(Arrays.toString(prvalue));
        prvalue = getprarr(prvalue);
        return prvalue[v];
    }

    public double[] getprarr(double[] pr) {
        // double prvalue = 0.0;
        for (int i = 0; i < 1000; i++) {
            double[] arr = new double[dg.vertex()];
            for (int j = 0; j < dg.vertex(); j++) {
            double val = 0.0;

                // Iterable<Integer> adjobj = dg.adj(j);
                // for (int k = 0; k < dg.vertex(); k++) {
                for(int h : dg.reverse().adj(j)) {
                    // if (dg.outdegree(h) == 0) {
                    //     prvalue = (double) 1 / dg.vertex();
                    //     lst.put(j, prvalue);
                    //     // k++;
                    // }
                    // else {
                    //     prvalue = (double) 1 / dg.vertex();
                    //     lst.put(j, prvalue);  
                    // }
                val = val + pr[h] / dg.outdegree(h);
                }
            arr[j] = val;    
            }
            pr = arr;
        }
        return pr;
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
        getPR(0);
        String str = "";
        // System.out.println(dg.toString());
        // System.out.println();
        for (int i = 0; i < dg.vertex(); i++) {
            str = i + " - " + prvalue[i];
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
        for (int i = 0; i < dg.vertex(); i++) {
            if (dg.outdegree(i) == 0) {
                for (int j = 0; j < dg.vertex(); j++) {
                    if (i != j) {
                        dg.addEdge(i, j);
                    }
                }
            }
        }
        pg.display();
        // System.out.println("\n");
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
