package hw7;

import java.io.*;
import java.util.*;
//import hw4.*;

//Parser to parse nodes and edges into the map
public class Parser {

	// default constructor
	public Parser() {
	}

	// Parse nodes and edges
	/**
	 * @param file
	 *            as file name
	 * @param map
	 *            storing parsed name/id pairs
	 * @param map
	 *            storing paresd entity infomations
	 * @modify the two maps
	 * @throws IOException
	 *             if file cannot be read of file not a CSV file
	 */
	public static void parseNode(String file, HashMap<String, String> nameid, HashMap<String, Entity> entityinfo)
			throws IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String[] info = line.split(",");
			if (info.length != 4) {
				throw new IOException("Incorrect file format (" + file + ").");
			}
			String name = info[0];
			String id = info[1];

			if (name.equals("")) {
				name = "Intersection"+ id;
			}
			
			nameid.putIfAbsent(name, id);
			double x = Double.parseDouble(info[2]);
			double y = Double.parseDouble(info[3]);
			entityinfo.putIfAbsent(id, new Entity(name, id, x, y));
		}
		reader.close();
	}
	/**
	 * @param file
	 *            as file name
	 * @param map
	 *            storing edge as id pair
	 * @modify map
	 * @throws IOException
	 *             if file cannot be read of file not a CSV file
	 */
	public static void parseEdge(String file, HashSet<HashSet<String>> edges) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String[] sample = line.split(",");
			if (sample.length != 2) {
				throw new IOException("Incorrect file format (" + file + ").");
			}
			String id1 = sample[0];
			String id2 = sample[1];
			HashSet<String> pair = new HashSet<String>();
			pair.add(id1);
			pair.add(id2);
			edges.add(pair);
		}
		

		reader.close();
	}

}