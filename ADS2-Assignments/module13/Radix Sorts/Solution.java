import java.util.Scanner;
/**
 * Solution class.
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
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int lines = s.nextInt();
		int[] arr = new int[lines];
		LSD lsdobj = new LSD();
		for (int i = 0; i < lines; i++) {
			arr[i] = s.nextInt();
		}
		lsdobj.sort(arr);
		lsdobj.toString(arr);
	}
}
