package Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import View.PCanva;

public class ListenersPCanva implements MouseListener, MouseMotionListener  {
	private ArrayList<Point> path;
	private PCanva pCanva;
	
	public ListenersPCanva(PCanva pCanva) {
		this.pCanva = pCanva;
		path = new ArrayList<Point>();
	}
	public ArrayList<Point> getPath(){
		return path;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.path = new ArrayList<Point>();	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.path.add(new Point(e.getX(), e.getY()));
		pCanva.drawPath();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
