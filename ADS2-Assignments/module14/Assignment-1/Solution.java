import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
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
        for(String word : tstobj.keysWithPrefix(prefix))
            System.out.println(word);
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}
