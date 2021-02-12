
/**
 * @author jenni - jjarrell
 * CIS175 -Spring 2021
 * Feb 11, 2021
 */


import java.util.List;
import java.util.Scanner;

import controller.PlantItemHelper;
import model.PlantItem;

public class RunningConsole {

		static Scanner in = new Scanner(System.in);
		static PlantItemHelper pih = new PlantItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a plant type: ");
			String plantType = in.nextLine();
			System.out.print("Enter a location: ");
			String location = in.nextLine();

			
			PlantItem toAdd = new PlantItem(plantType, location);
			pih.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the plant type to delete: ");
			String plantType = in.nextLine();
			System.out.print("Enter the location to delete: ");
			String location = in.nextLine();
			
			PlantItem toDelete = new PlantItem(plantType, location);
			pih.deleteItem(toDelete);


		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Plant Type");
			System.out.println("2 : Search by Location");
			int searchBy = in.nextInt();
			in.nextLine();
			List<PlantItem> foundItems = null;
			if (searchBy == 1) {
				System.out.print("Enter the plant type name: ");
				String plantTypeName = in.nextLine();
				
			} else {
				System.out.print("Enter the location: ");
				String locationName = in.nextLine();
				

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (PlantItem l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				PlantItem toEdit = pih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getPlantType() + " from " + toEdit.getPlantType());
				System.out.println("1 : Update Plant Type");
				System.out.println("2 : Update Location");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Plant Type: ");
					String newPlantType = in.nextLine();
					toEdit.setPlantType(newPlantType);
				} else if (update == 2) {
					System.out.print("New Item: ");
					String newLocation = in.nextLine();
					toEdit.setLocation(newLocation);
				}

				pih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}
		

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the plant inventory! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a plant");
				System.out.println("*  2 -- Edit a plant");
				System.out.println("*  3 -- Delete a plant");
				System.out.println("*  4 -- View the inventory");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					pih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<PlantItem> allItems = pih.showAllItems();
			for(PlantItem singleItem : allItems){
			System.out.println(singleItem.returnItemDetails());
			
			}

		}
	}
