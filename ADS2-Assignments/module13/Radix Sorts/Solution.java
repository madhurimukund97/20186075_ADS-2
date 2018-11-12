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
		int lines = Integer.parseInt(s.nextLine());
		String[] arr = new String[lines];
		MSD msdobj = new MSD();
		for (int i = 0; i < lines; i++) {
			arr[i] = s.nextLine();
		}
		msdobj.sort(arr);
		System.out.println(msdobj);
	}
}