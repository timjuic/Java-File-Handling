package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
	Scanner scanner = new Scanner(System.in);
	File file;
	FileWriter writer;
	String fileName;
	String lastUsedFile;
    public static void main(String[] args) {
    	Main menu = new Main();
    	int choice = 0;
    	while (choice != 9) {
			System.out.println("1 - Create a new file");
			System.out.println("2 - Reading existing file");
			System.out.println("3 - Writing into a file");
			System.out.println("4 - Delete a file");
			System.out.println("9 - Exit from program");

			choice = menu.scanner.nextInt();

			switch(choice) {
				case 1: menu.createFile(); break;
				case 2: menu.readFile(); break;
				case 3: menu.writeToFile(); break;
				case 4: menu.deleteFile(); break;
			}
		}
	}

	void createFile() {
    	fileName = fileNamePrompt("create");
		file = new File(fileName);
		if (!file.exists()) {
			try {
				writer = new FileWriter(fileName);
				System.out.println("The file named: " + fileName + " has been created!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else System.out.println("File with that name already exists!");
	}

	void readFile() {
    	fileName = fileNamePrompt("read");
		file = new File(fileName);
		if (file.exists()) {
			try {
				Scanner myReader = new Scanner(file);
				FileReader reader = new FileReader(fileName);
				while (myReader.hasNextLine()) {
					String data = myReader.next() + myReader.nextLine();
					System.out.println(data);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred");
				e.printStackTrace();
			}
		} else System.out.println("File with name: " + fileName + " does not exist!");
	}

	void writeToFile() {
		fileName = fileNamePrompt("write");
		if (new File(fileName).exists()) {
			try {
				writer = new FileWriter(fileName);
				System.out.println("How many lines would you like to write: ");
				int numberOfLines = scanner.nextInt();
				String line = "";
				while (numberOfLines-- > 0 && !line.equalsIgnoreCase("!stop")) {
					System.out.print("Type the line (\"!stop\" to abort):");
					line = scanner.next() + scanner.nextLine();
					if (!line.equalsIgnoreCase("!stop")) writer.write(line + "\n");
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else System.out.println("File named \"" + fileName + "\" does not exist!");
	}

	void deleteFile() {
    	fileName = fileNamePrompt("delete");
    	file = new File(fileName);
    	if (file.exists()) {
    		file.delete();
			System.out.println("File \"" + fileName + "\" has been successfully deleted!");
		} else System.out.println("File named \"" + fileName + "\" does not exist!");
	}

	String fileNamePrompt(String action) {
    	if (action.equalsIgnoreCase("create")) System.out.println("Enter the name of the file you want to " + action + ": ");
    	else if (fileName == null) System.out.println("Enter the name of the file you want to " + action + ": ");
    	else System.out.println("Enter the name of the file you want to " + action + " (To use the last entered file press \"1\"): ");
		String answer = scanner.next() + scanner.nextLine();
		if (answer.equals("1") && fileName != null && !action.equalsIgnoreCase("create")) {
			System.out.println("Using the last entered file name: " + fileName);
			return fileName;
		}
		return answer;
	}
}
