/* 
 *	@FileName:  Menus.java
 *	@Author Liam England
 *	@Course:		CST8284 - OOP
 *	@Assignment:	2
 *	@Date:		January 12, 2018
 *	@Professor:	David Houtman
 *	
 *	@Purpose: The WebPage.java file contains the code required to connect the browser to the internet and display the contents of connected web pages.
 * 
 * 			Specific References placed in code with regards to usage.
 */

package assignment2;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * 
 * The WebPage class hosts the WebView and WebEngine used to connect the GUI to the Internet and view the HTML/CSS/JavaScript on each browser page. 
 * @author Liam England, Dave Houtman
 * @version 1.0
 * @see javafx, javafx.beans.value, javafx.concurrent.Worker, javafx.scene.web, javafx.stage
 * @since 1.8.0
 *
 */
public class WebPage {
	/**
	 * The WebView that allows the WebPage to display html/css/javascript.
	 */
	private WebView webview = new WebView();
	/**
	 * The WebEngine that connects the application to the internet.
	 */
	private WebEngine engine;
	
	/**
	 * Invokes the WebEngine and adds a listener for state changes.
	 * @param stage JavaFX Stage that hosts the browser pages.
	 * @return WebEngine
	 */
	public WebEngine createWebEngine(Stage stage)  {
		
		WebView wv = getWebView();
		engine = wv.getEngine();
		
		
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState)  {
				
					if (newState == Worker.State.RUNNING) {
						stage.setTitle(engine.getLocation());
						/*
						 *  Additonal feature implemented using the example provided by
						 *  User Ugurcan Yildirim @ https://stackoverflow.com/questions/32486758/detect-url-changes-in-javafx-webview
						 */
						Menus.setURL(engine.getLocation());
					}
			}
		
		});
		
		return engine;
	}
	/**
	 * Returns the WebPage's WebView.
	 * @return WebView
	 */
	public WebView getWebView() {
		return webview;
	}
	/**
	 * Returns the WebPage's WebEngine
	 * @return WebEngine
	 */
	public WebEngine getWebEngine() {
		return engine;
	}
	
}