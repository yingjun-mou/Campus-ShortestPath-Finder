package hw9;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import hw7.*;

public class RPICampusPathsController extends JPanel {

	private static final long serialVersionUID = 1L;// prevent InvalidClassException
	private RPICampusPathsView viewPane;
	JFrame frame;

	/**
	 * Creates RPICampusPathsController.
	 *
	 * @param the RPICampusPathsView provides the map view.
	 * @param map the CampusMap the view communicates with.
	 * @param frame the JFrame displays the application
	 * @param mapFile the file of map picture.
	 * @effects constructor.
	 */
	public RPICampusPathsController(RPICampusPathsView view, CampusMap map, JFrame frame, String mapFile) {

		setLayout(new BorderLayout());
		this.frame = frame;
		// AUTO GENERATED try/catch block
		try {
			this.viewPane = new RPICampusPathsView(map, mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// set viewPane for map and controls for user interaction
		viewPane.setPreferredSize(new Dimension(300, 600));
		add(viewPane, BorderLayout.SOUTH);
		JPanel control1 = new JPanel(new BorderLayout());
		JPanel control2 = new JPanel(new BorderLayout());
		setControls(map,control1, control2);
		add(control1, BorderLayout.NORTH);
		add(control2, BorderLayout.CENTER);
	}

	/**
	 * Create a JPanel with User control buttons
	 * 
	 * @param the map with RPI campus data
	 */
	private void setControls(CampusMap map, JPanel control1, JPanel control2) {

		DefaultComboBoxModel<String> buildings1 = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> buildings2 = new DefaultComboBoxModel<String>();

		String[] allBuilding = map.getAllBuildings().split(",");
		// add \n to first element to prevent ArrayIndexOutOfBounds Exception.
		buildings1.addElement("\n" + allBuilding[0]);
		buildings2.addElement("\n" + allBuilding[0]);
		for (int i = 1; i < allBuilding.length; i++) {
			buildings1.addElement(allBuilding[i]);
			buildings2.addElement(allBuilding[i]);
		}
		JComboBox<String> boxInitial = new JComboBox<String>();
		JComboBox<String> boxFinal = new JComboBox<String>();
		boxInitial.setModel(buildings1);
		boxFinal.setModel(buildings2);
		control1.add(boxInitial, BorderLayout.CENTER);
		control2.add(boxFinal, BorderLayout.CENTER);

		JLabel labelStart = new JLabel("Starting from: ");
		JLabel labelDest = new JLabel("Destination: ");
		control1.add(labelStart, BorderLayout.WEST);	
		control2.add(labelDest, BorderLayout.WEST);

		// set up buttons
		JButton findPathButton = new JButton("Find Path");
		findPathButton.addActionListener(e -> {
			notifyOnSelect(boxInitial, boxFinal);
		});
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(e -> {
			boxInitial.setSelectedIndex(0);
			boxFinal.setSelectedIndex(0);
			notifyOnSelect(boxInitial, boxFinal);
//			frame.setSize(new Dimension(1024, 768));
			frame.setSize(new Dimension(300, 600));			
			frame.pack();
		});
		control1.add(findPathButton, BorderLayout.EAST); 
		control2.add(resetButton, BorderLayout.EAST);
		return;
	}	
	
	// Notify the viewer about user selection
	private void notifyOnSelect(JComboBox<String> boxInitial, JComboBox<String> boxFinal) {
		String start = (String) boxInitial.getSelectedItem();
		String dest = (String) boxFinal.getSelectedItem();
		start = start.split("\n")[1];
		dest = dest.split("\n")[1];
		viewPane.pathUpdate(start, dest);
	}

}