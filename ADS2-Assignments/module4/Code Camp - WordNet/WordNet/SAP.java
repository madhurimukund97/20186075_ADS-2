public class SAP {
    private Digraph dg;
    // private BreadthFirstDirectedPaths[] bfs;
    int distance;

    /**
     * constructor takes a digraph (not necessarily a DAG).
     *
     * @param      DAG   The dag
     *
     * @return     { description_of_the_return_value }
     */
    public SAP(Digraph dg) {
        this.dg = dg;
        this.distance = 0;


    }
    // /**
    //  * length of shortest ancestral path between v and w.
    //  * -1 if no such path.
    //  *
    //  * @param      v     { parameter_description }
    //  * @param      w     { parameter_description }
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public int length(int v, int w) {
    //     int len = Integer.MAX_VALUE;
    //     if (v < 0 || v > dg.vertex() - 1 || w < 0 || w > dg.vertex() - 1) {
    //         throw new IndexOutOfBoundsException();
    //     }
    //     if (bfs[v] == null) {
    //         bfs[v] = new BreadthFirstDirectedPaths(dg, v);
    //     }
    //     if (bfs[w] == null) {
    //         bfs[w] = new BreadthFirstDirectedPaths(dg, w);
    //     }
    //     for (int i = 0; i < dg.vertex(); i++) {
    //         if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
    //             int len1 = bfs[v].distTo(i) + bfs[w].distTo(i);
    //             if (len1 < len) {
    //                 len = len1;
    //             }
    //         }
    //     }
    //     bfs[v] = null;
    //     bfs[w] = null;
    //     if (len != Integer.MAX_VALUE) {
    //         return len;
    //     } else {
    //         return -1;
    //     }
    // }
    // /**
    //  * a common ancestor of v and w that participates
    //  * in a shortest ancestral path; -1 if no such path.
    //  *
    //  * @param      v     { parameter_description }
    //  * @param      w     { parameter_description }
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public int ancestor(int v, int w) {
    //     int len = Integer.MAX_VALUE;
    //     int ances = -1;
    //     if (v < 0 || v > dg.vertex() - 1 || w < 0 || w > dg.vertex() - 1) {
    //         throw new IndexOutOfBoundsException();
    //     }
    //     if (bfs[v] == null) {
    //         bfs[v] = new BreadthFirstDirectedPaths(dg, v);
    //     }
    //     if (bfs[w] == null) {
    //         bfs[w] = new BreadthFirstDirectedPaths(dg, w);
    //     }
    //     for (int i = 0; i < dg.vertex(); i++) {
    //         if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
    //             int l = bfs[v].distTo(i) + bfs[w].distTo(i);
    //             if (l < len) {
    //                 len = l;
    //                 ances = i;
    //             }
    //         }
    //     }
    //     bfs[v] = null;
    //     bfs[w] = null;
    //     return ances;
    // }
    /**
     * length of shortest ancestral path between any vertex in v
     * and any vertex in w; -1 if no such path.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        // if (v == null || w == null) {
        //     throw new NullPointerException();
        // }
        // int len = Integer.MAX_VALUE;
        // for (int i : v) {
        //     for (int j : w) {
        //         int len1 = length(i, j);
        //         if (len1 != -1 && len1 < len) {
        //             len = len1;
        //         }
        //     }
        // }
        // if (len != Integer.MAX_VALUE) {
        //     return len;
        // } else {
        //     return -1;
        // }
        ancestor(v, w);
        return distance;

    }
    /**
     * a common ancestor that participates in shortest ancestral path.
     * -1 if no such path.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        // if (v == null || w == null) {
        //     throw new NullPointerException();
        // }

        // int len = Integer.MAX_VALUE;
        // int ances = -1;
        // for (int i : v) {
        //     for (int j : w) {
        //         int len1 = length(i, j);
        //         if (len1 != -1 && len1 < len) {
        //             len = len1;
        //             ances = ancestor(i, j);
        //         }
        //     }
        // }
        // return ances;
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(dg, w);
        distance = Integer.MAX_VALUE;
        int ances = -1;
        for (int i = 0; i < dg.vertex(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int distance1 = bfsv.distTo(i) + bfsw.distTo(i);
                if (distance1 < distance) {
                    //shortPath = distance;
                    distance = distance1;
                    ances = i;
                }
                // return ancestor;
            }
        }
        return ances;
    }
    
    /**
     * do unit testing of this class.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        
    }
}
