package Modele.rmi;

import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Modele.Canvas;

public interface CanvasRMIServer extends Remote {
	
	public void setDrawing(byte[] image) throws RemoteException;
	public byte[] getDrawingInBytes() throws RemoteException;
    public void save(String name) throws RemoteException, IOException;
	public void addUser(Canvas canvas) throws RemoteException;
}
