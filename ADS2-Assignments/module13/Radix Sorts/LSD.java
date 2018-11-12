/**
 * Class for lsd.
 */
public class LSD {
    /**
     * Integer bitsperbyte.
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * Constructs the object.
     */
    protected LSD() {
        // constructor not used.
    }
    /**
     * sort method.
     *
     * @param      a     { parameter_description }
     * @param      w     { parameter_description }
     */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        final int two = 256;
        int rad = two;   // extend ASCII alphabet size.
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[rad + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < rad; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
    /**
     * Returns a string representation of the object.
     *
     * @param      a     { parameter_description }
     */
    public void toString(final String[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i;
        for (i = 0; i < a.length - 1; i++) {
            sb.append(a[i] + ", ");
        }
        sb.append(a[i] + "]");
        System.out.println(sb.toString());
    }
}
