// import java.util.List;
import java.util.ArrayList;
/**
 * Class for word net.
 */
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
    private ArrayList<String> id;
    /**
     * ST.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> noun;
    /**
     * vertices.
     */
    private int ver;
    /**
     * Constructs the object.
     * constructor takes the name of the two input files.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        // lp = new LinearProbingHashST<String, Integer>();
        // // int synset = readSynsetFile(synsets);
        // digrph = readHypernymFile(hypernyms, synset);
        id = new ArrayList<String>();
        noun = new LinearProbingHashST<String, ArrayList<Integer>>();
        ver = synsetFile(synsets);
        dg = new Digraph(ver);
        hypernymFile(hypernyms);
        sap = new SAP(dg);
    }
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
     * @param      synset  The synset
     *
     * @return     { description_of_the_return_value }
     */
    public int synsetFile(final String synset) {
        // int synsetid = 0;
            int ver1 = 0;
            In inp = new In("./Files/" + synset);
            while (!inp.isEmpty()) {
                ver1++;
                String[] arr = inp.readLine().split(",");
                int id1 = Integer.parseInt(arr[0]);
                id.add(id1, arr[1]);
                String[] arr1 = arr[1].split(" ");
                for (int i = 0; i < arr1.length; i++) {
                    ArrayList<Integer> list;
                    if (noun.contains(arr1[i])) {
                    list = noun.get(arr1[i]);
                    list.add(id1);
                } else {
                    list = new ArrayList<Integer>();
                    list.add(id1);
                }
                noun.put(arr1[i], list);
            }
            }
        return ver1;
    }
    /**
     * read hypernym file.
     *
     * @param      hypernym  The hypernym
     */
    public void hypernymFile(final String hypernym) {
        int temp = 0;
        In inp = new In("./Files/" + hypernym);
        while (!inp.isEmpty()) {
            temp++;
            String[] line = inp.readString().split(",");
            for (int i = 1; i < line.length; i++) {
                //System.out.println(line[0]+" " +line[1]);
                dg.addEdge(Integer.parseInt(
                    line[0]), Integer.parseInt(line[i]));
            }
        }
    }
    /**
     * Iterable noun.
     * returns all WordNet nouns.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return noun.keys();
    }
    /**
     * Determines if noun.
     * is the word a WordNet noun?
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        if (word.equals("null")) {
            throw new IllegalArgumentException();
        }
        return noun.contains(word);
    }
    /**
     * distance between nounA and nounB (defined below).
     *
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        ArrayList<Integer> arr1 = noun.get(nounA);
        ArrayList<Integer> arr2 = noun.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(arr1, arr2);

    }
    /**
     * a synset (second field of synsets.txt).
     * that is the common ancestor of nounA and nounB.
     * in a shortest ancestral path (defined below)
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
        ArrayList<Integer> arr1 = noun.get(nounA);
        ArrayList<Integer> arr2 = noun.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int ancestor = sap.ancestor(arr1, arr2);
        return id.get(ancestor);
    }
    /**
     * display method.
     */
    public void display() {
        DirectedCycle dc = new DirectedCycle(dg);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
            int outdegree = 0;

            for (int i = 0; i < dg.vertex(); i++) {
                if (dg.outdegree(i) == 0) {
                    outdegree++;
                }
            }
            if (outdegree > 1) {
                throw new IllegalArgumentException("Multiple roots");
            }
            System.out.println(dg);
        }
    }
}
