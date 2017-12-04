package Modele;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Canvas{
	private transient BufferedImage drawing;
	public String name;
	
	public Canvas(String name) {
		this.name = name;
	}
	public void setDrawing(BufferedImage image) {
		this.drawing = image;
	}
	public BufferedImage getDrawing() {
		return drawing;
	}
	public String getName() {
		return name;
	}
    public void save(String name) throws IOException{
        ImageIO.write(drawing, "PNG", new File("drawings/" + name +".png"));
    }
}
