package Modele;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Canvas implements Serializable{
	private BufferedImage drawing;
	
	public Canvas() {
		
	}
	public void setDrawing(BufferedImage image) {
		this.drawing = image;
	}
	public BufferedImage getDrawing() {
		return drawing;
	}
}
