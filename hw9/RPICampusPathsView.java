package hw9;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import hw7.*;
import hw4.*;
import java.io.*;


public class RPICampusPathsView extends JPanel{
	private static final long serialVersionUID = 1L;//prevent InvalidClassException
	private CampusMap map;
	private BufferedImage img;
	private ArrayList<Edge<Entity, Double>> path;
	
	/**
	 * Creates RPIGUIView.
	 *
	 * @param model the CampusMap the view communicates with.
	 * @param mapFile the file of map picture.
	 * @effects constructor.
	 */
	public RPICampusPathsView (CampusMap model, String mapFile) throws IOException {
		setLayout(new BorderLayout());
		this.map = model;
		this.img = ImageIO.read(new File(mapFile));
		path = map.getPath("Carman","Carman");
	}
	
	/**
	 * Update the RPIGUIView as user search for route
	 *
	 * @param start the starting location
	 * @param dest the destination.
	 * @effects repaint the view with pat or no path available.
	 */
	public void pathUpdate(String start, String dest) {
		path = map.getPath(start, dest);
		repaint();		
	}
	
	/**
	 * Paint the map and route if available
	 *
	 * @effects paint required Component.
	 */
    @Override
	public void paintComponent(Graphics g) {
    	 ArrayList<Edge<Entity, Double>> pathList = path;
		 Graphics2D g2 = (Graphics2D) g;
		 super.paintComponent(g);
		 int windowWidth = getWidth();
		 int windowHeight = getHeight();
	 
		 int devX = 0;
		 int devY = 0;
		 
//		 double ratioX = (double) windowWidth / img.getWidth();
//		 double ratioY = (double) windowHeight / img.getHeight();
		 double ratioY = (double) windowHeight *3 / img.getHeight();
		 double ratioX = ratioY;		 
		 
//		 g2.drawImage(img, 0, 0, windowWidth, windowHeight, null);
		 g2.drawImage(img, 0, 0, 305, 600, null);
		 
		 if (pathList.size() > 0) {
			 for (Edge<Entity, Double> edge : pathList) {
//				 g2.setColor(new Color(0, 153, 255));
				 g2.setColor(new Color(255, 0, 0));
				 Entity start = edge.getParent();
				 Entity end = edge.getChild();
				 g2.setStroke(new BasicStroke(3));
				 g2.drawLine((int) Math.round(start.getX() * ratioX +devX), 
						 (int) Math.round(start.getY() * ratioY +devY), 
						 (int) Math.round(end.getX() * ratioX +devX), 
						 (int) Math.round(end.getY() * ratioY +devY));
				 g2.setColor(new Color(0, 255, 0, 0));
				 Ellipse2D.Double midPoint = new Ellipse2D.Double(end.getX() * 
						 ratioX+devX, end.getY() * ratioY+devY, ratioX * 10, ratioY * 10);
				 g2.fill(midPoint);
				 g2.draw(midPoint);
			 }
			 g2.setColor(new Color(0,0,0));
			 Entity start = pathList.get(0).getParent(); 
			 Entity end = pathList.get(pathList.size() - 1).getChild(); 
			 Ellipse2D.Double startPoint = new Ellipse2D.Double(start.getX() * 
					 ratioX+devX -4, start.getY() * ratioY+devY, ratioX * 25, ratioY * 25);
			 Ellipse2D.Double endPoint = new Ellipse2D.Double(end.getX() * 
					 ratioX+devX-4, end.getY() * ratioY+devY, ratioX * 25, ratioY * 25);
			 g2.fill(startPoint);
			 g2.draw(startPoint);
			 g2.setColor(new Color(255,0,0));
			 g2.fill(endPoint);
			 g2.draw(endPoint);
		 }
	 }

}