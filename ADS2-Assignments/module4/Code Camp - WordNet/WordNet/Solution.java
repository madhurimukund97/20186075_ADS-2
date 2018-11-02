import java.util.Scanner;
/**
 * Solution class.
 */
public final class Solution {
	/**
	 * constructs an object.
	*/
	private Solution() {
		// constructor not used.
	}
	/**
	 * Main class.
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		String synset = StdIn.readString();
		String hypernym = StdIn.readString();
		String str = StdIn.readString();
		try {
		switch (str) {
			case "Graph":
				
					WordNet wn = new WordNet(synset,hypernym);
				
			break;
			case "Queries":
			
				String[] str1 = StdIn.readString().split(" ");
				if (str1[0].equals("null")) {
					throw new IllegalArgumentException("IllegalArgumentException");
				}
			
			break;
			default:
			break;
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
