package Modele;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Class of statics to turn a bufferedImage to an ImageIcon and back
 */
public class Converter {
	/**
	 * Turns a ImageIcon into a BufferedImage
	 * @param icon the original ImageIcon
	 * @return the associated BufferedImage
	 */
	public static BufferedImage toBufferedImage(ImageIcon icon) {
		BufferedImage bimg = new BufferedImage(
			    icon.getIconWidth(),
			    icon.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bimg.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bimg;
	}
	
	/**
	 * Turns a ImageIcon into a BufferedImage
	 * @param image the original BufferedImage
	 * @return the associated ImageIcon
	 */
	public static ImageIcon toIcon(BufferedImage image){
		return new ImageIcon(image);
	}
}
