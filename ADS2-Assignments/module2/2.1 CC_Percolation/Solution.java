import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * { Graph object }..
     */
    private Graph g;
    /**
     * { integer num }.
     */
    private int num;
    /**
     * { integer size }.
     */
    private int size;
    /**
     * { integer begin }.
     */
    private int begin;
    /**
     * { integer end  }.
     */
    private int end;
    /**
     * { integer count }.
     */
    private int count;
    /**
     * { boolean arr  }.
     */
    private boolean[] arr;
    /**
     * Constructs the object.
     *
     * @param      num1     { parameter_description }.
     */
    Percolation(final int num1) {
   // create n-by-n grid, with all sites blocked

    this.num = num1;
    this.size = num1 * num1;
    this.begin = size;
    this.count = 0;
    this.end = size + 1;
    arr = new boolean[size];
    g = new Graph(size + 2);
    for (int i = 0; i < num; i++) {
        g.addEdge(begin, i);
        g.addEdge(end, size - i - 1);
    }
    }
   /**
    * Searches for the first match.
    *
    * @param      n1    The n 1
    * @param      n2    The n 2
    *
    * @return     { description_of_the_return_value }.
    */
    private int indexOf(final int n1, final int n2) {
        return num * (n1 - 1) + n2 - 1;
    }
    /**
     * Opens sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void openSites(final int row, final int col) {
        if (arr[col] && !g.hasEdge(row, col)) {
            g.addEdge(row, col);
        }
    }
   /**
    * { open site (row, col) if it is not open already }.
    *
    * @param      row   The row
    * @param      col   The col
    */
    public void open(final int row, final int col) {
        // open site (row, col) if it is not open already
        int ind = indexOf(row, col);
        arr[ind] = true;
        count = count + 1;
        int up = ind - num;
        int down = ind + num;
        if (num == 1) {
            g.addEdge(begin, ind);
            g.addEdge(end, ind);
        }
        if (up >= 0) {
            openSites(ind, up);
        }
        if (down < size) {
            openSites(ind, down);
        }

        if (col == 1) {
            if (col != num) {
                openSites(ind, ind + 1);
            }
            return;
        }
        if (col == num) {
            openSites(ind, ind - 1);
            return;
        }
        // if (num == 2) {
        //     weigh.union(ind, ind + 1);
        //     weigh.union(ind, );
        //     // return;
        // }
        openSites(ind, ind + 1);
        openSites(ind, ind - 1);
    }
   /**
    * Determines if open.
    *
    * @param      row   The row
    * @param      col   The col
    *
    * @return     True if open, False otherwise.
    */
    public boolean isOpen(final int row, final int col) {
        // is site (row, col) open?
        return arr[indexOf(row, col)];
    }
   // /**
   //  * Determines if full.
   //  *
   //  * @param      row   The row
   //  * @param      col   The col
   //  *
   //  * @return     True if full, False otherwise.
   //  */
   //  public boolean isFull(int row, int col) {
   //      // is site (row, col) full?
   //      return false;
   //  }Integer.parseInt(tokens[0])
    /**
    * { function_description }.
    *
    * @return     { description_of_the_return_value }
    */
    public int numberOfOpenSites() {
        // number of open sites

        return count;
    }
   /**
    * { function_description }.
    *
    * @return     { description_of_the_return_value }
    */
    public boolean percolates() {
        CC conn = new CC(g);
        return conn.connected(begin, end);
    }
}
/**
 * Class for graphmatrix.
 */
class Graphmatrix {
    /**
     * { var_description }.
     */
    private int ver;
    /**
     * { var_description }.
     */
    private int edg;
    /**
     * { var_description }.
     */
    private int e;
    /**
     * { var_description }.
     */
    private int[][] adj;
    /**
     * Constructs the object.
     *
     * @param      verr  The verr
     * @param      edgg  The edgg
     */
    Graphmatrix(final int verr, final int edgg) {
        this.ver = verr;
        this.edg = edgg;
        this.e = 0;
        this.adj = new int[ver][ver];
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int ver() {
        return ver;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int edg() {
        return e;
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
        if (hasEdge(v, w)) {
            return;
        }
        e++;
        adj[v][w] = 1;
        adj[w][v] = 1;

    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        Bag<Integer> bag =  new Bag<Integer>();
        for (int i = 0; i < ver; i++) {
            if (adj[v][i] == 1) {
                bag.add(i);
            }
        }
        return bag;
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
        return (adj[v][w] == 1);
    }
    /**
     * { function_description }.
     *
     * @param      str        The string
     *
     * @return     { description_of_the_return_value }
     *
     * @throws     Exception  { exception_description }
     */
    public String display(final String[] str)
    throws Exception {
        if (ver <= 1 && edg <= 1) {
            System.out.println(ver() + " vertices"
                + ", " + edg() + " edges");
            throw new Exception("No edges");
        } else {
        System.out.println(ver() + " vertices"
            + ", " + edg() + " edges");
        String s = "";
        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < ver; j++) {
                s += adj[i][j] + " ";
            }
            s += "\n";
        }
        return s;
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
     * Main class.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int num = Integer.parseInt(s.nextLine());
        Percolation pobj = new Percolation(num);
        while (s.hasNext()) {
            String[] tokens = s.nextLine().split(" ");
            pobj.open(Integer.parseInt(tokens[0]), 
                Integer.parseInt(tokens[1]));
        }
        System.out.println(pobj.percolates()
            && pobj.numberOfOpenSites() != 0);
    }
}
