/**
 * Class for boggle solver.
 */
public class BoggleSolver {
	// Initializes the data structure using the given array of strings
	// as the dictionary.
	// (You can assume each word in the dictionary contains
	// only the uppercase letters A through Z.).
	/**
	 * TrieST object.
	*/
	private TrieST<Integer> trieobj;
	/**
	 * constructor.
	*/
	public BoggleSolver(String[] dictionary) {
		trieobj = new TrieST<Integer>();
		int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		for (String word : dictionary) {
			if (word.length() >= 8) {
				trieobj.put(word, 11);
			} else {
				trieobj.put(word, points[word.length()]);
			}
		}
	}
	/**
	 * Gets all valid words.
	 * Returns the set of all valid words
	 * in the given Boggle board, as an Iterable.
	 *
	 * @param      board  The board
	 *
	 * @return     All valid words.
	 */
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		return new Bag<String>();
	}
	// Returns the score of the given word
	// if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only
	// the uppercase letters A through Z.)
	/**
	 * Score.
	 *
	 * @param      word  The word
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int scoreOf(String word) {
		if (trieobj.contains(word)) {
			return trieobj.get(word);
		} else {
			return 0;
		}
	}
}