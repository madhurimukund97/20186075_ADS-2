import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
	/**
	 * Picture object.
	 */
	Picture pict;
	/**
	 * Constructs the object.
	 * create a seam carver object based on the given picture.
	 *
	 * @param      picture  The picture
	 */
	public SeamCarver(Picture picture) {
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
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || pict.width() - 1 == x || pict.height() - 1 == y) {
			return 1000;
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

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}