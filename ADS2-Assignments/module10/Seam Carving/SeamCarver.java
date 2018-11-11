import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * double border.
     */
    private static final double BORDER = 1000;
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
        // final int thousand = 1000;
        // if (x == 0 || y == 0 || pict.width() - 1 == x
        //     || pict.height() - 1 == y) {
        //     return thousand;
        // }
        // Color left = pict.get(x, y - 1);
        // Color right = pict.get(x, y + 1);
        // Color top = pict.get(x - 1, y);
        // Color bottom = pict.get(x + 1, y);
        // int redh = top.getRed() - bottom.getRed();
        // int greenh = top.getGreen() - bottom.getGreen();
        // int blueh = top.getBlue() - bottom.getBlue();
        // int hor = redh * redh + greenh * greenh + blueh * blueh;
        // int redv = left.getRed() - right.getRed();
        // int greenv = left.getGreen() - right.getGreen();
        // int bluev = left.getBlue() - right.getBlue();
        // int vert = redv * redv + greenv * greenv + bluev * bluev;
        // double energy = Math.sqrt(hor + vert);
        // return energy;
        int w = width() - 1, h = height() - 1;
        if (x < 0 || x > w || y < 0 || y > h) {
            throw new java.lang.IllegalArgumentException("IllegalArgumentException");
        }
        if (x == 0 || x == w ||  y == 0 || y == h) {
            return BORDER;
        }
        return internalEnergy(x, y);
    }
    /**
     * // energy of pixel at column x and row y not on boarder
     *
     * @param      x  integer
     * @param      y  integer
     *
     * @return  // energy of pixel at column x and row y not on boarder
     */
    private double internalEnergy(final int x, final int y) {
        Color left = this.pict.get(x - 1, y);
        Color right = this.pict.get(x + 1, y);
        Color up = this.pict.get(x, y - 1);
        Color down = this.pict.get(x, y + 1);
        return Math.sqrt(gradient(left, right) + gradient(up, down));
    }
    /**
     * gradient method.
     *
     * @param      one   One
     * @param      two   Two
     *
     * @return  gradient horizontal and vertical.
     */
    private double gradient(final Color one, final Color two) {
        double red = one.getRed() - two.getRed();
        double green = one.getGreen() - two.getGreen();
        double blue = one.getBlue() - two.getBlue();
        return red * red + green * green + blue * blue;
    }
    /**
     * energy storage.
     *
     * @return  energies
     */
    private double[][] initEnergies() {
        double[][] energies = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energies[i][j] = energy(j, i);
            }
        }
        return energies;
    }
    /**
     *  // pass through an array and mark the.
     *   shorthest distance from top to entry.
     *
     * @param      energies  The energies
     */

    private void topologicalSort(final double[][] energies) {
        int h = energies.length, w = energies[0].length;
        for (int row = 1; row < h; row++) {
            for (int col = 0; col < w; col++) {
                double temp = energies[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, energies[row - 1][col - 1]);
                }

                if (col != (w - 1)) {
                    min = Math.min(min, energies[row - 1][col + 1]);
                } else {
                    min = min;
                }
                energies[row][col] += min;
            }
        }

    }
    /**
     * transpose matrix.
     *
     * @param      energies  The energies
     *
     * @return   transposed energies.
     */
    private double[][] transposeGrid(final double[][] energies) {
        int h = energies.length, w = energies[0].length;
        double[][] trEnergies = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                trEnergies[col][row] = energies[row][col];
            }
        }
        return trEnergies;
    }
    /**
     * vertical path.
     *
     * @param      energies  The energies
     *
     * @return vertical energies.
     */
    private int[] minVerticalPath(final double[][] energies) {
        int h = energies.length, w = energies[0].length;
        int[] path = new int[h];

        topologicalSort(energies);

        // find the lowest element in last row
        path[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (energies[h - 1][i] < energies[h - 1][path[h - 1]]) {
                path[h - 1] = i;
            }
        }
        // trace path back to first row
        // assuming we need the cheapest upper neighboring entry
        for (int row = h - 2; row >= 0; row--) {
            int col = path[row + 1];
            // three neighboring, priority to center
            path[row] = col;
            if (col > 0 && energies[row][col - 1] < energies[row][path[row]]) {
                path[row] = col - 1;
            }
            if (col < (w - 2) && energies[row][col + 1]
                < energies[row][path[row]]) {
                path[row] = col + 1;
            }
        }
        return path;
    }
    /**
     * sequence of indices for horizontal seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        double[][] transposeEnergies = transposeGrid(initEnergies());
        return minVerticalPath(transposeEnergies);
    }
    /**
     * sequence of indices for vertical seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        
        double[][] normalEnergies = initEnergies();
        return minVerticalPath(normalEnergies);
    }
    /**
     * Removes a horizontal seam.
     * remove horizontal seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        if (height() <= 1 || !isValid(seam, width(), height() - 1)) {
            throw new
            java.lang.IllegalArgumentException("IllegalArgumentException");
        }
        Picture pic = new Picture(width(), height() - 1);
        for (int w = 0; w < width(); w++) {
            for (int h = 0; h < seam[w]; h++) {
                pic.set(w, h, this.pict.get(w, h));
            }

            for (int h = seam[w] + 1; h < height(); h++) {
                pic.set(w, h - 1, this.pict.get(w, h));
            }

        }
        this.pict = pic;
    }
    /**
     * Removes a vertical seam.
     * remove vertical seam from current picture.
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {
        if (width() <= 1 || !isValid(seam, height(), width())) {
            throw new
            java.lang.IllegalArgumentException("IllegalArgumentException");
        }
        Picture pic = new Picture(width() - 1, height());
        for (int h = 0; h < height(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                pic.set(w, h, this.pict.get(w, h));
            }


            for (int w = seam[h] + 1; w < width(); w++) {
                pic.set(w - 1, h, this.pict.get(w, h));
            }

        }
        this.pict = pic;
    }
    private boolean isValid(final int[] a, final int len, final int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1) ||
                a[i] > Math.min(range, a[i - 1] + 1))
                return false;
        }
        return true;
    }
}
