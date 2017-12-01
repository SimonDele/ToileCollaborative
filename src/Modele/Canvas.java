package Modele;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;

import Modele.rmi.CanvasRMIServer;
import Modele.rmi.CanvasRMIServerImpl;

public class Canvas implements Serializable {
	private transient BufferedImage drawing;
	public transient CanvasRMIServer canvasServer;
	
	public Canvas(String name, Boolean createServer) {
		System.out.println("canvas créé");
		if(createServer) {
			try {
				//System.out.println("zefds");
				canvasServer = new CanvasRMIServerImpl(null, name);
				this.canvasServer.addUser(this);
				try {
					this.setDrawing(ImageIO.read(new File("drawings/w.png")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}			
		}



	}
	public void setDrawing(BufferedImage image) {
		//Set the drawing here
		this.drawing = image;
		//Set the drawing in the server
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(drawing, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(baos);
		byte[] bytes = baos.toByteArray();
	    try {
	    	//System.out.println(this.canvasServer);
			this.canvasServer.setDrawing(bytes);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getDrawing() {
		byte[] bytes = null;
		try {
			bytes = this.canvasServer.getDrawingInBytes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drawing;
	}
	public void modif() {
		System.out.println("modif !!!");
	}
	public void setRMIServer(CanvasRMIServer server) {
		//System.out.println("setServer");
		this.canvasServer = server;
	}
    public void save(String name) throws IOException{
        ImageIO.write(drawing, "PNG", new File("drawings/" + name +".png"));
    }
}
