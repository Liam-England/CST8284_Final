/* 
 *	@FileName:  Menus.java
 *	@Author Liam England
 *	@Course:		CST8284 - OOP
 *	@Assignment:	2
 *	@Date:		January 12, 2018
 *	@Professor:	David Houtman
 *	
 *	@Purpose: The FileUtils.java file contains the methods and properties required to load and save bookmarks and default URL files.
 * 
 * 
 * 			Specific References placed in code with regards to usage.
 */

package assignment2;

/*
 * Class Provided by Dave Houtman
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileUtils hosts the functional utilities to store and load Strings to/from files
 * @author Dave Houtman
 * @see java.io, java.util
 * 
 */
public class FileUtils {
	/**
	 * Boolean Method to see if the passed file exists
	 * @param f File Object
	 * @return Boolean
	 */
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length()>2));
	}
	/**
	 * Boolean Method to see if the passed file exists
	 * @param s String name of a file.
	 * @return Boolean
	 */
	public static boolean fileExists(String s) {
		return (fileExists(new File(s)));
	}
	
	/**
	 * Method to return an ArrayList of URLs from a file. 
	 * @param fileName Name of selected file.
	 * @return ArrayList
	 */
	public static ArrayList<String> getURLsFromFile(String fileName) {
		ArrayList<String> al = new ArrayList<>();
		try {
			File f = new File(fileName);
			Scanner URLString = new Scanner(f);
			while (URLString.hasNext())
				al.add(URLString.next());
			URLString.close();
		} catch (FileNotFoundException e) {
		}
		return al;
	}
	/**
	 * Method to store String URLs from an ArrayList to a file.
	 * @param al ArrayList to transfer strings to file
	 * @param fileName Name of the file.
	 * @return File
	 */
	public static File storeURLsToFile(ArrayList<String> al, String fileName) {
		File f = new File(fileName);
		if (FileUtils.fileExists(f)) f.delete();  // Remove old bookmarks file.
		try {
			PrintWriter pw = new PrintWriter(f);
			for (String s : al)	pw.println(s);
			pw.flush(); pw.close();
		} catch (FileNotFoundException e) {	}
		return f;
	}

}
