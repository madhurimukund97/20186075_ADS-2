import java.util.Scanner;
import java.util.Arrays;
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
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int lines = Integer.parseInt(s.nextLine());
        String[] arr = new String[lines];
        LSD lsdobj = new LSD();
        for (int i = 0; i < lines; i++) {
            arr[i] = s.nextLine();
        }
        lsdobj.sort(arr, arr[0].length());
        lsdobj.toString(arr);
        // System.out.println(Arrays.toString(arr));
    }
}
