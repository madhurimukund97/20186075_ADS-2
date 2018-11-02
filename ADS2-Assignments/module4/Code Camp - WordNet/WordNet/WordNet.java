import java.util.Arrays;
public class WordNet {
    /**
     * declaration of digraph.
     */
    private Digraph dg;
    /**
     * declaration of SAP.
     */
    private SAP sap;
    /**
     * declaration of ST.
     */
    // private LinearProbingHashST<Integer, String> id;
    /**
     * ST.
     */
    // private LinearProbingHashST<String, ArrayList<Integer>> noun;
    /**
     * Constructs the object.
     * constructor takes the name of the two input files.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        // lp = new LinearProbingHashST<String, Integer>();
        // // int synset = readSynsetFile(synsets);
        // digrph = readHypernymFile(hypernyms, synset);
        SynsetFile(synsets, hypernyms);
        
    }
    private int ver;
    // /**
    //  * Reads a file.
    //  *
    //  * @param      synets  The synets
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public String[] readFile(String synets) {
    //     In inp = new In("./Files/" + synets);
    //     String[] str = null;
    //     while (!inp.isEmpty()) {
    //         String[] lines = inp.readString().split(",");
    //         System.out.println(Arrays.toString(lines));
    //         int inp1 = Integer.parseInt(lines[0]);
    //         str = lines[1].split(" ");
    //     }
    //     return str;
    // }
    /**
     * read synset file.
     *
     * @param      synset     The synset
     * @param      hypernyms  The hypernyms
     */
    public void SynsetFile(String synset, String hypernyms) {
        int synsetid = 0;
        try {
            In inp = new In("./Files/" + synset);
            while (!inp.isEmpty()) {
                ver++;
                String[] file = inp.readString().split(",");
                synsetid = Integer.parseInt(file[0]);
                String[] noun = file[1].split(" ");
            }
            Digraph dobj = new Digraph(ver);
            HypernymFile(hypernyms, dobj);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    /**
     * read hypernym file.
     *
     * @param      hypernym  The hypernym
     * @param      synsetv   The synsetv
     */
    public void HypernymFile(String hypernym, Digraph synsetv) {
        try {
            In inp = new In("./Files/" + hypernym);
            Digraph digraph = new Digraph(synsetv);
            while (!inp.isEmpty()) {
                String[] line = inp.readString().split(",");
                for (int i = 1; i < line.length; i++) {
                    int hyponyms = Integer.parseInt(line[0]);
                    int hypernyms = Integer.parseInt(line[i]);
                    digraph.addEdge(hyponyms, hypernyms);
                }
            }
            DirectedCycle dcycle = new DirectedCycle(digraph);
            int temp = 0;
            for (int j = 0; j < ver; j++) {
                if (digraph.outdegree(j) == 0) {
                    temp++;
                }
                System.out.println(temp);
            }
            if (temp > 1) {
                    throw new IllegalArgumentException("Multiple roots");
            }
            if (dcycle.hasCycle()) {
                System.out.println("Cycle detected");            
            } else {
                System.out.println(digraph);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Iterable noun.
     * returns all WordNet nouns.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return null; 
    }
    /**
     * Determines if noun.
     * is the word a WordNet noun?
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(String word) {
        return false;
    }
    // /**
    //  * distance between nounA and nounB (defined below).
    //  * 
    //  *
    //  * @param      nounA  The noun a
    //  * @param      nounB  The noun b
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public int distance(String nounA, String nounB) {

    // }
    // /**
    //  * a synset (second field of synsets.txt).
    //  * that is the common ancestor of nounA and nounB.
    //  * in a shortest ancestral path (defined below)
    //  *
    //  * @param      nounA  The noun a
    //  * @param      nounB  The noun b
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public String sap(String nounA, String nounB) {

    // }
    // /**
    //  * do unit testing of this class.
    //  *
    //  * @param      args  The arguments
    //  */
    // public static void main(String[] args) {

    // }
}
