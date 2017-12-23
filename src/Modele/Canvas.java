package Modele;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import View.MainFrame;

/**
 * Drawing that the members have drawn on their group. 
 */
@SuppressWarnings("serial")
public class Canvas implements Serializable {
	/**
	 * Sheer drawing (as ImageIcon), undirectly an array of pixels.
	 */
	public ImageIcon drawing;
	/**
	 * Canvas' width, fixated until infinite Canvas achieved
	 */
	public final static Integer width = 500;
	/**
 	 * Canvas' height, fixated until infinite Canvas achieved
	 */
	public final static Integer height = 500;
	
	public Canvas() {}
	
	/**
	 * Sets the Canvas to a new image
	 * @param image the new image
	 */
	public void setDrawing(ImageIcon image) {
		this.drawing = image;
	}
	/**
	 * @return the drawing 
	 */
	public ImageIcon getDrawing() {
		return this.drawing;
	}
	
	/**
	 * Saves the Canvas by writing it on a file so we can draw it back
	 * @param name name to give to the file (usually groupname)
	 * @throws IOException due to the file writing
	 */
    public void save(String name) throws IOException{
        ImageIO.write(Converter.toBufferedImage(drawing), "PNG", new File("drawings/" + name +".png"));
    }
    
    /**
     * Method to draw the path a drawer has dragged with his mouse
     * @param drawer the Member responsible for the path
     * @param path the arraylist of points that is the path to draw
     */
    public void drawPath(Member drawer, ArrayList<Point> path) {
        MainFrame.pCanva.drawPath(drawer, path);
    }
}
