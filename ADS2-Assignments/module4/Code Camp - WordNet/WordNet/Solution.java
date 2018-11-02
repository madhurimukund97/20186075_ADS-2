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
		String synset = StdIn.readLine();
		String hypernym = StdIn.readLine();
		String str = StdIn.readLine();
		switch (str) {
			case "Graph":
			try {
			WordNet wn = new WordNet(synset,hypernym);
			wn.display();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			case "Queries":
			try {
                WordNet wn1 = new WordNet(synset,
                                          hypernym);
                while (StdIn.hasNextLine()) {
                    String query = StdIn.readLine();
                    String[] qarray = query.split(" ");
                    if (qarray[0].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    }
                    System.out.println("distance = " + wn1.distance(qarray[0],
                                       qarray[1]) + ", ancestor = " + wn1.sap(qarray[0],
                                               qarray[1]));
                }
            } catch (Exception e) {
            	//e.printStackTrace();
                System.out.println(e.getMessage());
            }
            break;
			default:
			break;
		}	
	}
}
