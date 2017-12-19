package Modele;

import java.io.Serializable;

public class Toolbox implements Serializable{
	private String shapeSelected;
	private int size;
	public final static int sizeS = 5;
	public final static int sizeM = 10;
	public final static int sizeL = 20;
	
	public Toolbox() {
		shapeSelected = "CIRCLE";
		size = sizeM;
	}
	
	public void setShapeSelected(String s) {
		this.shapeSelected = s;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getShapeSelected() {
		return shapeSelected;
	}
}
