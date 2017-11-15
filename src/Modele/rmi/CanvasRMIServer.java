package Modele.rmi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CanvasRMIServer extends Remote {
	
	public void setDrawing(BufferedImage image) throws RemoteException;
	public BufferedImage getDrawing() throws RemoteException;
    public void save(String name) throws RemoteException, IOException;
    
}
