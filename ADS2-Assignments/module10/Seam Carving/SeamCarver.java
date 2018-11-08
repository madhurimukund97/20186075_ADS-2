import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * Picture object.
     */
    private Picture pict;
    /**
     * Constructs the object.
     * create a seam carver object based on the given picture.
     *
     * @param      picture  The picture
     */
    public SeamCarver(final Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.pict = picture;
    }
    /**
     * current picture.
     *
     * @return     { description_of_the_return_value }
     */
    public Picture picture() {
        return pict;
    }
    /**
     * width of current picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return pict.width();
    }
    /**
     * height of current picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return pict.height();
    }
    /**
     * energy of pixel at column x and row y.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double energy(final int x, final int y) {
        final int thousand = 1000;
        if (x == 0 || y == 0 || pict.width() - 1 == x ||
            pict.height() - 1 == y) {
            return thousand;
        }
        Color left = pict.get(x, y - 1);
        Color right = pict.get(x, y + 1);
        Color top = pict.get(x - 1, y);
        Color bottom = pict.get(x + 1, y);
        int redh = top.getRed() - bottom.getRed();
        int greenh = top.getGreen() - bottom.getGreen();
        int blueh = top.getBlue() - bottom.getBlue();
        int hor = redh * redh + greenh * greenh + blueh * blueh;
        int redv = left.getRed() - right.getRed();
        int greenv = left.getGreen() - right.getGreen();
        int bluev = left.getBlue() - right.getBlue();
        int vert = redv * redv + greenv * greenv + bluev * bluev;
        double energy = Math.sqrt(hor + vert);
        return energy;
    }
    /**
     * sequence of indices for horizontal seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        return new int[0];
    }
    /**
     * sequence of indices for vertical seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        return new int[0];
    }
    /**
     * Removes a horizontal seam.
     * remove horizontal seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {

    }
    /**
     * Removes a vertical seam.
     * remove vertical seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {

    }
}
