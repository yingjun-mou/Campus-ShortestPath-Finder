package hw9;

import java.io.IOException;
import javax.swing.*;
import hw7.*;
/**
 * RPICampusPathsMain runs the path application and shows the shortest route.
 */
public class RPICampusPathsMain extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException{
		//read data from file
		CampusMap map = new CampusMap();
		try{
			map.createCampusMap("data/CU_map_data_Nodes.csv", "data/CU_map_data_Edges.csv");
		} catch (IOException badData) {
			return;
		}
		//set up frame
		JFrame frame = new JFrame("Campus Paths Finder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(1024, 768);
		frame.setSize(300, 600);
		//set up view and controller
		RPICampusPathsView view = new RPICampusPathsView(map,"data/CU_CampusMap_Nodes.png");
		RPICampusPathsController controller = new RPICampusPathsController(view, map, frame,"data/CU_CampusMap_Nodes.png");
		
		frame.add(controller);
	    frame.pack();
		frame.setVisible(true);//Never forget
		
	}
}