package test;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner s = new Scanner(System.in);) {
			boolean k = true;
			System.out.println("WELCOME TO CONTACT APPLICATION");
			while (k) {
				int choice = getUserInput(s);
				switch (choice) {
				case 1:
					System.out.println("\nYou opted to add a new contact.");
					addContact(s);
					System.out.println("Below is the new updated list:");
					Contacts.listContacts();
					break;
				case 2:
					System.out.println("\nYou opted to search a contact.");
					searchContact(s);
					break;
				case 3:
					System.out.println("\nYou opted to edit a contact.");
					Contacts.listContacts();
					editContact(s);
					Contacts.listContacts();
					break;
				case 4:
					System.out
							.println("\nYou opted to list all contacts by Date.");
					Contacts.listContacts();
					break;
				case 5:
					System.out
							.println("\nYou opted to list all contacts by Name.");
					Contacts.listContactsByName();
					break;
				case 6:
					System.out.println("\nYou opted to delete a contact.");
					Contacts.listContacts();
					deleteContact(s);
					Contacts.listContacts();
					break;
				case 7:
					System.out.println("\nYou opted to view statistics on database.");
					Contacts.dbStats();
					break;
				case 8:
					k = false;
					System.out
							.println("\nYou have opted to exit the Application.Thank You!!");
					System.out
							.println("----------------------------------------------------------------------");
					break;
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	static int getUserInput(Scanner scanObj) {
		System.out.println("\nMENU");
		System.out.println("---------------------------------------------");
		System.out.println("1.Add a new contact");
		System.out.println("2.Search a contact");
		System.out.println("3.Edit a contact");
		System.out.println("4.List all contacts by date");
		System.out.println("5.List all contacts by name");
		System.out.println("6.Delete a contact");
		System.out.println("7.Contact Database statistics");
		System.out.println("8.Exit Application");
		System.out.println("---------------------------------------------\n");
		System.out.print("Enter Menu Option: ");
		try{
			int choice = Integer.parseInt(scanObj.nextLine());
			return choice;
		}
		catch(Exception e)
		{
			System.out.println("Exiting Application.Please enter an integer(1-8) next time.");
			int choice=8;
			return choice;
		}
		
	}

	static void addContact(Scanner scanObj) {
		System.out.println("enter contact details: ");
		Contacts ct = new Contacts();
		System.out.print("name: ");
		ct.setName(scanObj.nextLine());
		System.out.print("nickName: ");
		ct.setNickName(scanObj.nextLine());
		System.out.print("city: ");
		ct.setCity(scanObj.nextLine());
		System.out.print("email: ");
		ct.setEmail(scanObj.nextLine());
		System.out.print("Number: ");
		ct.setPhNumber(scanObj.nextLine());
		System.out.print("type(PERSONAL/BUSINESS): ");
		String type = scanObj.nextLine();
		if (type.equalsIgnoreCase("business"))
			ct.setType(Type.BUSINESS);
		else
			ct.setType(Type.PERSONAL);

		ct.insert();
	}

	static void searchContact(Scanner scanObj) {
		System.out.print("Please enter name or number you want to search:");
		String searchKey = scanObj.nextLine();
		Contacts.search(searchKey);
	}

	static void editContact(Scanner scanObj) {
		System.out.print("Which row you want to edit?: ");
		int row = Integer.parseInt(scanObj.nextLine());
		System.out
				.println("Which among the following you want to edit:\n1.name\n2.nickName\n3.city\n4.email\n5.number\n6.type");
		String edit = scanObj.nextLine();
		System.out.println("Assign the new values:");
		String newValue = scanObj.nextLine();
		Contacts.edit(edit, newValue, row);
	}

	static void deleteContact(Scanner scanObj) {
		System.out.println("Please enter the row you want to delete:");
		int deleteKey = Integer.parseInt(scanObj.nextLine());
		Contacts.delete(deleteKey);
	}
	

}
