package Modele;

public class Toolbox {
	private String shapeSelected;
	private int size;
	
	public Toolbox() {
		shapeSelected = "CIRCLE";
		size = 20;
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
