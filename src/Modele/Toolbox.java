package Modele;

import java.io.Serializable;

/**
 * A set of tools to use to draw on a Canvas
 */
@SuppressWarnings("serial")
public class Toolbox implements Serializable{
	/**
	 * (To implement) A String to define the shape to draw - only functional is path
	 */
	private String shapeSelected;
	/**
	 * Size of the brush used to paint on the Canvas, among three fixed sizes
	 */
	private int size;
	/**
	 * Small brush size
	 */
	public final static int sizeS = 5;
	/**
	 * Meidum brush size
	 */
	public final static int sizeM = 10;
	/**
	 * Large brush size
	 */
	public final static int sizeL = 20;
	
	/**
	 * Constructor with medium size as default 
	 */
	public Toolbox() {
		shapeSelected = "CIRCLE";
		size = sizeM;
	}
	/**
	 * Getter for the shape (useless for now)
	 * @return the current shape
	 */
	public String getShapeSelected() {
		return shapeSelected;
	}
	/**
	 * Setter for the current shape (useless for now)
	 * @param s new shape 
	 */
	public void setShapeSelected(String s) {
		this.shapeSelected = s;
	}
	/**
	 * Getter for the current size
	 * @return the current size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Setter for the current size
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
