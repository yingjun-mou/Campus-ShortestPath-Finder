package hw7;

import java.io.*;

public class CampusPaths{

	/**
	 * print all buildings in the graph
	 * 
	 * @param a CampusMap object graph
	 */
	public static void listBuilding(CampusMap graph){
		System.out.print(graph.listAllBuildings());
	}
	
	/**
	 * main function reading user input
	 * @param args
	 */
	public static void main(String[] args) {
		CampusMap map = new CampusMap();	//map data
		try{
			map.createCampusMap("data/CU_map_data_Nodes.csv", "data/CU_map_data_Edges.csv");
		} catch (IOException badData) {
			return;
		}
		
		String menu = "b to list all building\n" + "r to request the route\n" + "q to quit\n";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(menu);
		String userinput = "";
		
		//reading user input
		while (!userinput.equals("q")) {
			try {
				userinput = reader.readLine();
			} catch (IOException badInput) {
				break;
			}
			
			if (userinput.equals("b")) {
				listBuilding(map);
			}
			else if (userinput.equals("r")) {
				String start;
				String end;
				System.out.print("First building id/name, followed by Enter: ");
				try {
					start = reader.readLine();
				} catch (IOException badinput) {
					break;
				}
				System.out.print("Second building id/name, followed by Enter: ");
				try {
					end = reader.readLine();
				} catch (IOException badinput) {
					break;
				}
				System.out.print(map.findPath(start, end)); 
			}
			else if (userinput.equals("q")) {
				break;
			}
			else if(userinput.equals("m"))
				System.out.print(menu);
			else {
				System.out.print("Unknown option\n");
			}
		}
		return;
		

	}
}