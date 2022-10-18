package myPackage;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Locked {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		int option;
		int suboption;
		System.out.println("Application  name: LockedMe.com");
		System.out.println("Application Developer : Amarthya");
		System.out.println("Designation : Full stack Developer");
		
		try {
			do {
				System.out.println("your are in Main Menu");
				System.err.println("Choose your option");
				System.out.println(" 1 -  Display the Current File Names");
				System.out.println(" 2 -  To do operations such as add file , delete file, search file..");
				System.out.println(" 3 -  Exit");
				option = sc.nextInt();
				fileOperations(sc, option);

			} while (true);
		} catch (Exception e) {
			do {
				System.err.println("Please enter a valid input...Only Numbers are allowed...");
				Scanner scn3 = new Scanner(System.in);
				System.out.println("Please choose your operations.");
				System.out.println(" 1 -  Display the Current File Names.");
				System.out.println(" 2 -  To do operations such as add file , delete file, search file..");
				System.out.println(" 3 -  Exit");
				option = scn3.nextInt();
				fileOperations(scn3, option);
			} while (true);
		}
	}

	public static void fileOperations(Scanner sc, int option) {
		int suboption;
		switch (option) {
		case 1:

			displayCurrentFiles();

			break;
		case 2: 

			do {
				suboption = optionsOfFileOperation(sc);
				switch (suboption)

				{
				case 11:

					addFile();

					break;
				case 12:
					
					deleteFile();

					break;
				case 13:

					searchFile();
					break;
				case 14:
					
					returnToMainMenu(sc);
					break;
				default:
					System.err.println("enter the valid input");
				}
			} while (true);

		case 3:
			
			System.out.println("Exit sucessful ");
			System.exit(0);
			break;
		default:
			System.err.println("ivalid input...");
			
		}
	}

	public static int optionsOfFileOperation(Scanner sc) {
		int suboption;
		System.out.println("Choose the operation to do...");
		System.out.println("11 - to Add File");
		System.out.println("12 - to Delete File");
		System.out.println("13 - to Search File");
		System.out.println("14 - Back to Main Menu");
		suboption = sc.nextInt();
		return suboption;
	}

	public static void displayCurrentFiles() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Directory of a File : ");
		String dirPath = scanner.nextLine(); 

		File folder = new File(dirPath);
		if (folder.isDirectory()) {
			File[] fileList = folder.listFiles();

			Arrays.sort(fileList);

			System.out.println("\nTotal number of files present in the  " + dirPath +" are : "+ fileList.length);

			FileFilter fileFilter = new FileFilter() {
				@Override
				public boolean accept(File file) {
					return !file.isDirectory();
				}
			};

			fileList = folder.listFiles(fileFilter);

			Arrays.sort(fileList, new Comparator() {
				@Override
				public int compare(Object f1, Object f2) {
					return ((File) f1).getName().compareTo(((File) f2).getName());
				}
			});

			for (File file : fileList) {
				System.out.println(file.getName());
			}
		}
	}

	public static void deleteFile() {
		Scanner scanD = new Scanner(System.in);

		System.out.println("Enter the directory where to delete a file ");
		String directoryPathD = scanD.nextLine();

		System.out.println("Enter the file name to be deleted.. ");
		String fileNme = scanD.nextLine();

		File directoryD = new File(directoryPathD.trim());

		// store all names with same name
		// with/without extension
		String[] flistD = directoryD.list();
		int flagD = 0;
		if (flistD == null) {
			System.out.println("Empty directory.");
		} else {

			// Linear search in the array
			for (int i = 0; i < flistD.length; i++) {
				String filename = flistD[i];
				if (filename.equalsIgnoreCase(fileNme.trim())) {
					System.out.println(filename + " found");
					flagD = 1;
					File myObj = new File(directoryPathD + "\\" + fileNme);
					if (myObj.delete()) {
						System.out.println("File Deleted Successfully..!");
					}

				}
			}
		}

		if (flagD == 0) {
			System.out.println(fileNme+" File Not Found in the "+directoryPathD);
		}
	}

	public static void returnToMainMenu(Scanner sc) {
		int option;
		System.out.println("your are in Main Menu");
		System.err.println("Choose your option");
		System.out.println(" 1 -  Display the Current File Names....");
		System.out.println(" 2 -  To do operations such as add file , delete file, search file..");
		System.out.println(" 3 -  Exit");
		option = sc.nextInt();
		fileOperations(sc, option);
	}

	public static void searchFile() {
		// you need to write a logic to search a file
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the directory where to search a file ");
		String directoryPath = scan.nextLine();

		System.out.println("Enter the file name to be searched.. ");
		String fileName = scan.nextLine();

		// Create an object of the File class
		// Replace the file path with path of the directory
		File directory = new File(directoryPath.trim());

		// store all names with same name
		// with/without extension
		String[] flist = directory.list();
		int flag = 0;
		if (flist == null) {
			System.out.println("Empty directory.");
		} 
		else {

			// Linear search in the array
			for (int i = 0; i < flist.length; i++) {
				String filename = flist[i];
				if (filename.equalsIgnoreCase(fileName.trim())) {
					System.out.println(filename+" is founded in given directory  "+ directoryPath);
					flag = 1;
				}
			}
		}

		if (flag == 0) {
			System.out.println(fileName+" File Not Founded in the"+directoryPath);
		}
	}

	public static void addFile() {
		try {
			Scanner sc = new Scanner(System.in); // object of Scanner class
			System.out.print("Enter the directory with file name \n");
			System.out.println("For Example : c:\\menu\\abc.txt");
			String name = sc.nextLine(); // variable name to store the file name
			FileOutputStream fos = new FileOutputStream(name, true); // true for append mode
			System.out.print("Add the content into file \n ");
			String str = sc.nextLine() + "\n"; // str stores the string which we have entered
			byte[] b = str.getBytes(); // converts string into bytes
			fos.write(b); // writes bytes into file
			fos.close(); // close the file
			System.out.println("file and content is succefully added");
		} catch (Exception e) {
		System.err.println("Please enter the correct path with file name");
		System.out.println("For Example : c:\\menu\\abc.txt");
		}
	}
}
