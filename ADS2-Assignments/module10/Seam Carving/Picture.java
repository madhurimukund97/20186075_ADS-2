import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
/**
 * class Picture.
 */
public final class Picture implements ActionListener {
    /**
     * the rasterized image.
     */
    private BufferedImage image;
    /**
     * on-screen view.
     */
    private JFrame frame;
    /**
     * name of file.
     */
    private String filename;
    /**
     * location of origin.
     */
    private boolean isOriginUpperLeft = true;
    /**
     * width and height.
     */
    private final int width, height;
    /**
     * Constructs the object.
     *
     * @param      width   The width
     * @param      height  The height
     */
    public Picture(int width, int height) {
        if (width  < 0) throw new IllegalArgumentException(
            "width must be non-negative");
        if (height < 0) throw new IllegalArgumentException(
            "height must be non-negative");
        this.width  = width;
        this.height = height;
        image = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_RGB);
    }
    /**
     * Constructs the object.
     *
     * @param      picture  The picture
     */
    public Picture(Picture picture) {
        if (picture == null) throw new IllegalArgumentException(
            "constructor argument is null");
        width  = picture.width();
        height = picture.height();
        image = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_RGB);
        filename = picture.filename;
        isOriginUpperLeft = picture.isOriginUpperLeft;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                image.setRGB(
                    col, row, picture.image.getRGB(col, row));
    }
    /**
     * Constructs the object.
     *
     * @param      filename  The filename
     */
    public Picture(String filename) {
        if (filename == null) throw new IllegalArgumentException(
            "constructor argument is null");

        this.filename = filename;
        try {
            // try to read from file in working directory.
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            }
            else {
                URL url = getClass().getResource(filename);
                if (url == null) {
                    url = new URL(filename);
                }
                image = ImageIO.read(url);
            }
            if (image == null) {
                throw new IllegalArgumentException(
                    "could not read image file: " + filename);
            }

            width  = image.getWidth(null);
            height = image.getHeight(null);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException(
                "could not open image file: " + filename, ioe);
        }
    }
    /**
     * Constructs the object.
     *
     * @param      file  The file
     */
    public Picture(File file) {
        if (file == null) throw new IllegalArgumentException(
            "constructor argument is null");

        try {
            image = ImageIO.read(file);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException(
                "could not open file: " + file, ioe);
        }
        if (image == null) {
            throw new IllegalArgumentException(
                "could not read file: " + file);
        }
        width  = image.getWidth(null);
        height = image.getHeight(null);
        filename = file.getName();
    }
    /**
     * Gets the j label.
     *
     * @return     The j label.
     */
    public JLabel getJLabel() {
        if (image == null) return null;
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }
    /**
     * Sets the origin upper left.
     */
    public void setOriginUpperLeft() {
        isOriginUpperLeft = true;
    }
    /**
     * Sets the origin to be the lower left pixel.
     */
    public void setOriginLowerLeft() {
        isOriginUpperLeft = false;
    }
    /**
     * Displays the picture in a window on the screen.
     */
    public void show() {

        // create the GUI for viewing the image if needed
        if (frame == null) {
            frame = new JFrame();

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(" Save...   ");
            menuItem1.addActionListener(this);           
            menuItem1.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S,
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);
            frame.setContentPane(getJLabel());
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (filename == null) frame.setTitle(width + "-by-" + height);
            else                  frame.setTitle(filename);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }
        frame.repaint();
    }
    /**
     * Returns the height of the picture.
     *
     * @return the height of the picture (in pixels)
     */
    public int height() {
        return height;
    }

    /**
     * Returns the width of the picture.
     *
     * @return the width of the picture (in pixels)
     */
    public int width() {
        return width;
    }
    /**
     * { function_description }.
     *
     * @param      row   The row
     */
    private void validateRowIndex(int row) {
        if (row < 0 || row >= height())
            throw new IllegalArgumentException("row index must be between 0 and " + (height() - 1) + ": " + row);
    }
    /**
     * { function_description }.
     *
     * @param      col   The col
     */
    private void validateColumnIndex(int col) {
        if (col < 0 || col >= width())
            throw new IllegalArgumentException("column index must be between 0 and " + (width() - 1) + ": " + col);
    }

    /**
     * Returns the color of pixel ({@code col}, {@code row}) as a {@link java.awt.Color}.
     *
     * @param col the column index
     * @param row the row index
     * @return the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public Color get(int col, int row) {
        validateColumnIndex(col);
        validateRowIndex(row);
        int rgb = getRGB(col, row);
        return new Color(rgb);
    }

   /**
     * Returns the color of pixel ({@code col}, {@code row}) as an {@code int}.
     * Using this method can be more efficient than {@link #get(int, int)} because
     * it does not create a {@code Color} object.
     *
     * @param col the column index
     * @param row the row index
     * @return the integer representation of the color of pixel ({@code col}, {@code row})
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public int getRGB(int col, int row) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (isOriginUpperLeft) return image.getRGB(col, row);
        else                   return image.getRGB(col, height - row - 1);
    }

   /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col the column index
     * @param row the row index
     * @param color the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws IllegalArgumentException if {@code color} is {@code null}
     */
    public void set(int col, int row, Color color) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (color == null) throw new IllegalArgumentException("color argument is null");
        int rgb = color.getRGB();
        setRGB(col, row, rgb);
    }

   /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col the column index
     * @param row the row index
     * @param rgb the integer representation of the color
     * @throws IllegalArgumentException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public void setRGB(int col, int row, int rgb) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (isOriginUpperLeft) image.setRGB(col, row, rgb);
        else                   image.setRGB(col, height - row - 1, rgb);
    }

   /**
     * Returns true if this picture is equal to the argument picture.
     *
     * @param other the other picture
     * @return {@code true} if this picture is the same dimension as {@code other}
     *         and if all pixels have the same color; {@code false} otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Picture that = (Picture) other;
        if (this.width()  != that.width())  return false;
        if (this.height() != that.height()) return false;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                if (this.getRGB(col, row) != that.getRGB(col, row)) return false;
        return true;
    }

   /**
     * Returns a string representation of this picture.
     * The result is a <code>width</code>-by-<code>height</code> matrix of pixels,
     * where the color of a pixel is represented using 6 hex digits to encode
     * the red, green, and blue components.
     *
     * @return a string representation of this picture
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(width +"-by-" + height + " picture (RGB values given in hex)\n");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int rgb = 0;
                if (isOriginUpperLeft) rgb = image.getRGB(col, row);
                else                   rgb = image.getRGB(col, height - row - 1);
                sb.append(String.format("#%06X ", rgb & 0xFFFFFF));
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * This operation is not supported because pictures are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
    }

   /**
     * Saves the picture to a file in either PNG or JPEG format.
     * The filetype extension must be either .png or .jpg.
     *
     * @param name the name of the file
     * @throws IllegalArgumentException if {@code name} is {@code null}
     */
    public void save(String name) {
        if (name == null) throw new IllegalArgumentException("argument to save() is null");
        save(new File(name));
        filename = name;
    }

   /**
     * Saves the picture to a file in a PNG or JPEG image format.
     *
     * @param  file the file
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public void save(File file) {
        if (file == null) throw new IllegalArgumentException("argument to save() is null");
        filename = file.getName();
        if (frame != null) frame.setTitle(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        if ("jpg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
            try {
                ImageIO.write(image, suffix, file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }

   /**
     * Opens a save dialog box when the user selects "Save As" from the menu.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                             "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
        if (chooser.getFile() != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }
}