import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor not used.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        String[] words = loadWords();
        TST<Integer> tstobj = new TST<Integer>();
        String prefix = s.nextLine();
        int i = 0;
        for (String word : words) {
            SuffixArray sarr = new SuffixArray(word);
            for (int j = 0; j < word.length(); j++) {
                tstobj.put(sarr.select(j), i++);
            }
        }
        for (String word : tstobj.keysWithPrefix(prefix)) {
            System.out.println(word);
        }
    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
