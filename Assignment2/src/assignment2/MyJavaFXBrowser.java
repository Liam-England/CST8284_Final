/* 
 *	@FileName:  Menus.java
 *	@Author Liam England
 *	@Course:		CST8284 - OOP
 *	@Assignment:	2
 *	@Date:		January 12, 2018
 *	@Professor:	David Houtman
 *	
 *	@Purpose: The MyJavaFXBrowser.java file hosts the code required to load and launch the operational web browser.
 * 
 * 
 * 			Specific References placed in code with regards to usage.
 */

package assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The Main application that pulls the other classes together to display an operational Web Browser.
 * @author Liam England, Dave Houtman
 * @version 1.0
 * @see javafx.application.Application, javafx.scene, javafx.web
 * @since 1.8.0
 */
public class MyJavaFXBrowser extends Application {

	@Override
	public void start(Stage primaryStage) {
		
	    WebPage currentPage = new WebPage();
	    
	    currentPage.createWebEngine(primaryStage);
		
	    WebView webView = currentPage.getWebView();
		
		BorderPane root = new BorderPane();
		root.setTop(Menus.loadTopPanel(webView));
		root.setRight(Menus.loadRightPanel(webView));
		root.setBottom(Menus.loadBottomPanel(webView));
		
		//This line ensures that if the file "default.web" exists, the url is loaded as the homepage. Else, it loads google.ca
		String startupURL = (FileUtils.fileExists("default.web"))?FileUtils.getURLsFromFile("default.web").get(0):"https://www.google.ca/";
		Menus.goToURL(startupURL);
		root.setCenter(webView);	

		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	/**
	 * Overridden stop method that calls the storeURLsToFile method.
	 */
	@Override
	public void stop() {
		FileUtils.storeURLsToFile(Menus.getBookmarks(), "bookmarks.web");
	}
	/**
	 * The Main method of the application that launches the web browser.
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
