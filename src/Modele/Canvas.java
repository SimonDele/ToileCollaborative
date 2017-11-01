package Modele;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Canvas{
	private BufferedImage drawing;
	
	public Canvas() {
	}
	public void setDrawing(BufferedImage image) {
		this.drawing = image;
	}
	public BufferedImage getDrawing() {
		return drawing;
	}
    public void save(String name) throws IOException{
        ImageIO.write(drawing, "PNG", new File("drawings/" + name +".png"));
    }
}
