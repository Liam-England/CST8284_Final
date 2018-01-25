/* 
 *	@FileName:  Menus.java
 *	@Author Liam England
 *	@Course:		CST8284 - OOP
 *	@Assignment:	2
 *	@Date:		January 12, 2018
 *	@Professor:	David Houtman
 *	
 *	@Purpose: The Menus.java file contains the methods and properties required to build, display, and use a JavaFX based Web Browser,
 *			 as per the requirements outlined in Assignments 1 & 2 CST8284 - OOP.
 *			 The included methods and properties are used for both the visual and functional aspects of the GUI.
 * 
 * 
 * 			Specific References placed in code with regards to usage.
 *
 * 			General References Include:
 * 
 * 			Java Docs								 :  https://docs.oracle.com/javase/8/javafx/api/toc.htm
 * 
 * 			JavaFX Java GUI Tutorial - 15 - ListView :  https://www.youtube.com/watch?v=GbzKr46VvD0&ab_channel=thenewboston
 * 	
 * 			Using JavaFX UI Controls				 :	https://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
 */

package assignment2;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * Menus contains the methods and properties required to build, display, and use a JavaFX based Web Browser, as per the requirements outlined in Assignments 1 and 2 CST8284 - OOP,
 * The included methods and properties are used for both the visual and functional aspects of the GUI.
 * @author Liam England, Dave Houtman
 * @version 1.0
 * @see javafx, javafx.geometry, javafx.application, javafx.collections, javafx.event, javafx.scene, javafx.scene.control, javafx.scene.layout, javafx.scene.text, javafx.scene.web, 
 * javafx.scene.input, javax.xml.transform, java.io, java.util
 * @since 1.8.0
 * 
 *
 */
public class Menus {

	

	/**** Generic Menu/Menu Item Properties ****/
	/**
	 * Generic MenuItem to be used in methods.
	 */
	private static MenuItem mnuItm;
	/**
	 * Generic Menu to be used in methods.
	 */
	private static Menu mnu;

	/********* Address Bar Properties **********/
	/**
	 * VBox to host Address Bar and Menus once constructed.
	 */
	private static VBox topPanel = new VBox();
	/**
	 * HBox to host label, textfield, and button of address bar.
	 */
	private static HBox hbxAddressBar;
	/**
	 * TextField to be used to parse strings as URLs.
	 */
	private static TextField txtfldAddress;
	/**
	 * Go Button to fire URLs from address bar.
	 */
	private static Button btnGo;
	/**
	 * Boolean for Method Logic.
	 */
	private static boolean toggleAddress = true;


	/********* Bookmarks Properties **********/
	/**
	 * ArrayList to hold Strings of Bookmark URLs.
	 */
	private static ArrayList<String> bookmarkURLs = new ArrayList<>();
	/**
	 * CustomMenuItem to host bookmarked URLs as MenuItems.
	 */
	private static CustomMenuItem bookmarkCM;
	/**
	 * ContextMenu for feature option to delete bookmarks.
	 */
	private static ContextMenu bcon;
	
	
	/******** History Bar Properties **********/
	/**
	 * BorderPane to host the History Panel.
	 */
	private static BorderPane rightPanel = new BorderPane();
	/**
	 * HBox to host the Label and buttons of the History Panel.
	 */
	private static HBox hbxHist;
	/**
	 * Placeholder ObservableList to pass information to ListView.
	 */
	private static ObservableList<WebHistory.Entry> history;
	/**
	 * ListView to display list of session URL history.
	 */
	private static ListView<WebHistory.Entry> histList;
	/**
	 * Next index in History button.
	 */
	private static Button histNext;
	/**
	 * Previous index in History button.
	 */
	private static Button histBack;
	

	/**
	 * Boolean for Method Logic.
	 */
	private static boolean toggleHistory = true;
	
	
	/******* Display Code Properties ********/
	
	/**
	 * BorderPane to host Display Code panel.
	 */
	private static BorderPane bottomPanel = new BorderPane();
	
	/**
	 * VBox to host displayed code and label.
	 */
	private static VBox codeBox;
	
	/**
	 * String to hold HTML/CSS/JavaScript of a webpage.
	 */
	private static String xml;
	
	/**
	 * TextArea to display String xml in.
	 */
	private static TextArea cdTxt;
	
	/**
	 * ScrollPane to hold TextArea cdTxt.
	 */
	private static ScrollPane cdScroll;
	
	/**
	 * Boolean for Method Logic.
	 */
	private static boolean toggleDC = true;
	
	
	/**************** MenuBar ****************/
	/**
	 * Returns MenuBar completed with Feature Menus.
	 * @param wv The WebView associated with WebPage. 
	 * @return MenuBar 
	 */
	public static MenuBar getMenuBar(WebView wv) {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(getMnuFile(wv), getMnuSettings(wv), getMnuBookmarks(), getMnuHelp());
		return menuBar;
	}

	
	/***************** Menu ******************/
	/**
	 * Get drop down File menu with feature options.
	 * @param wv The WebView associated with WebPage. 
	 * @return Menu File
	 */
	private static Menu getMnuFile(WebView wv) {
		mnu = new Menu("_File");
		mnu.getItems().addAll(getMnuItmRefresh(wv), getMnuItmExit());
		return mnu;
	}
	/**
	 * Get drop down Settings menu with feature options.
	 * @param wv The WebView associated with WebPage. 
	 * @return Menu Settings
	 */
	private static Menu getMnuSettings(WebView wv) {
		mnu = new Menu("_Settings");
		mnu.getItems().addAll(getMnuItmHistory(wv), getMnuItmAddressBar(), getMnuItmSaveStartupPage(), getMnuItmDisplayCode(wv));
		return mnu;
	}
	/**
	 * Get drop down Bookmarks menu with feature options.
	 * @return Menu Bookmarks
	 */
	private static Menu getMnuBookmarks() {
		mnu = new Menu("_Bookmarks");
		mnu.getItems().addAll(getMnuItmBookmarks(mnu));
		loadBookmarksToMenu(mnu);
		return mnu;
	}
	/**
	 * Get drop down Help menu with feature options.
	 * @return Menu Help
	 */
	private static Menu getMnuHelp() {
		mnu = new Menu("_Help");
		mnu.getItems().addAll(getMnuItmJavaHelp(), getMnuItmAbout());
		return mnu;
	}

	
	/*************** MenuItems ***************/
	/**
	 * Get feature option Refresh, which refreshes the current browser page.
	 * @param wv WebView associated with WebPage
	 * @return MenuItem Refresh
	 */
	private static MenuItem getMnuItmRefresh(WebView wv) {
		mnuItm = new MenuItem("_Refresh");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+R"));
		mnuItm.setOnAction((ActionEvent e) -> wv.getEngine().reload());
		return mnuItm;
	}
	/**
	 * Get feature option Exit, which closes the application.
	 * @return MenuItem Exit
	 */
	private static MenuItem getMnuItmExit() {
		mnuItm = new MenuItem("_Exit");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+E"));
		mnuItm.setOnAction((ActionEvent e) -> Platform.exit());
		return mnuItm;
	}
	/**
	 * Get feature option Set Home Page, which sets the current browser page as the default startup page.
	 * @return MenuItem Set Home Page
	 */
	private static MenuItem getMnuItmSaveStartupPage() {
		mnuItm = new MenuItem("Set Home_Page");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+P"));
		mnuItm.setOnAction((ActionEvent e) -> {
			String currentURL = getCurrentURL();
			if (currentURL.length() > 0) {                  // if the currentURL is not ""...
				ArrayList<String> al = new ArrayList<>();
				al.add(currentURL);							// ...load it into the ArrayList and save it to the file
				FileUtils.storeURLsToFile(al, "default.web");
			}			
		});
		return mnuItm;
	}
	/**
	 * Get feature option Show/Hide Address Bar, which toggles the address textfield. 
	 * @return MenuItem Show/Hide Address Bar
	 */
	private static MenuItem getMnuItmAddressBar() {
		mnuItm = new MenuItem("_Show/Hide Address Bar");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		mnuItm.setOnAction((ActionEvent e) -> {
			if (toggleAddress)
				topPanel.getChildren().add(hbxAddressBar);
			else
				topPanel.getChildren().remove(hbxAddressBar);
			toggleAddress = !toggleAddress;
		});
		return mnuItm;
	}
	/**
	 * Get feature option Add Bookmark, which bookmarks the current browser page.
	 * @param mnuBookmarks The parent Menu of the Add Bookmark Feature
	 * @return MenuItem Add Bookmark
	 */
	private static MenuItem getMnuItmBookmarks(Menu mnuBookmarks) {
		mnuItm = new MenuItem("Add _Bookmark");
		mnuItm.setAccelerator(KeyCombination.keyCombination("Shift+SHORTCUT+S"));
		mnuItm.setOnAction((ActionEvent e) -> {
			addBookmarkToMenu(mnuBookmarks, getCurrentURL());
			getBookmarks().add(getCurrentURL());
		});
		return mnuItm;
	}
	/**
	 * Get feature option History, which toggles the display of the History panel.
	 * @param wv WebView associated with WebPage
	 * @return MenuItem History
	 */
	private static MenuItem getMnuItmHistory(WebView wv) {
		mnuItm = new MenuItem("_History");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+H"));
		mnuItm.setOnAction((ActionEvent e)-> {
			
			/*
			 * Toggle method taken from getMnuItmAddressBar.
			 * Written by Dave Houtman.
			 */
			
			
			if (toggleHistory) {
				buildHistory(wv);
				histList = new ListView<>(history);
				hbxHist = createHistBar(wv);
				rightPanel.setTop(hbxHist);
				rightPanel.setCenter(histList);}
			else
				rightPanel.getChildren().removeAll(hbxHist, histList);
			toggleHistory = !toggleHistory;
		});
		return mnuItm;
	}
	/**
	 * Get feature option Display Code, which toggles the display of a Text panel displaying the browser pages' HTML/Javascript/CSS
	 * @param wv
	 * @return MenuItem Display Code
	 */
	private static MenuItem getMnuItmDisplayCode(WebView wv) {
		mnuItm = new MenuItem("_Display Code");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+D"));
		mnuItm.setOnAction((ActionEvent e)-> {
			
			/*
			 * Toggle method taken from getMnuItmAddressBar.
			 * Written by Dave Houtman.
			 */
			
			
			if (toggleDC) {
				createCodeDisplay(wv);
				bottomPanel.setCenter(codeBox);}
			else
				bottomPanel.getChildren().remove(codeBox);
			
			toggleDC = !toggleDC;
		});
		return mnuItm;
	}

	/**
	 * Get feature option Java Help which queries google for "Java".
	 * @return MenuItem Java Help
	 */
	private static MenuItem getMnuItmJavaHelp() {
		mnuItm = new MenuItem("_Java Help");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+J"));
		mnuItm.setOnAction((ActionEvent e) -> goToURL("https://www.google.ca/search?q=java"));
		return mnuItm;
	}
	/**
	 * Get feature option About which displays an alert box with information on the application.
	 * @return MenuItem About
	 */
	private static MenuItem getMnuItmAbout() {
		/* From Marco Jakob, code.makery, 
		 * http://code.makery.ch/blog/javafx-dialogs-official/ */
		
		mnuItm = new MenuItem("_About");
		mnuItm.setAccelerator(KeyCombination.keyCombination("SHORTCUT+T"));
		mnuItm.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("CST2824 - Assignment 2");
			alert.setContentText("Code written by Liam England, \n With base code provided by \nProf. Dave Houtman,\n©2017 - 2018");
			alert.showAndWait();
		});
		return mnuItm;
	}
	
	/******* Panel Loading Methods ********/
	/**
	 * Method to load and return the VBox containing the MenuBar, submenus, and features options.
	 * @param wv WebView associated with WebPage
	 * @return VBox topPanel
	 */
	public static VBox loadTopPanel(WebView wv) {
		hbxAddressBar = createAddressBar(wv);  
		MenuBar mb = getMenuBar(wv);
	    topPanel.getChildren().add(mb);
	    return topPanel;
	}
	/**
	 * Method to return the BorderPane containing the History panel.
	 * @param wv WebView associated with WebPage
	 * @return BorderPane rightPanel
	 */
	public static BorderPane loadRightPanel(WebView wv) {
		return rightPanel;
	}
	/**
	 * Method to return the BorderPane containing the Display Code panel.
	 * @param wv WebView associated with WebPage
	 * @return BorderPane bottomPanel
	 */
	public static BorderPane loadBottomPanel(WebView wv) {
		return bottomPanel;
	}

	
	/*************** Address Bar Methods ***************/
	/**
	 * Method to build the Address Bar.
	 * @param wv WebView associated with WebPage
	 * @return HBox hbx that hosts a label, textfield, and button.
	 */
	public static HBox createAddressBar(WebView wv) {

		Label lblEnterURL = new Label("Enter URL:");
		lblEnterURL.setPadding(new Insets(4, 4, 4, 4));

		txtfldAddress = new TextField();
		
		
	/* Example of setOnKeyPressed followed from 
	 * https://www.programcreek.com/java-api-examples/index.php?class=javafx.scene.Scene&method=setOnKeyPressed	
	 */
		txtfldAddress.setOnKeyPressed((e) -> {
			KeyCode kc = e.getCode();	
			
			if (kc.equals(KeyCode.ENTER)) {
				goToURL(wv);
				};
			});
		
		btnGo = new Button("Go");
		btnGo.setOnAction((ActionEvent e) -> 
		goToURL(wv));
		
		HBox hbx = new HBox();	
		hbx.getChildren().addAll(lblEnterURL, txtfldAddress, btnGo);
		hbx.setHgrow(txtfldAddress, Priority.ALWAYS);
		return hbx;
	}
		
	/**
	 * Method to set the URL displayed in the address bar.
	 * @param URL String to be displayed in the address bar textfield.
	 */
	public static void setURL(String URL) {
	
		txtfldAddress.setText(URL);}
	
	/**
	 * Method to get the current String within the address bar textfield.
	 * @return String URL
	 */
	private static String getCurrentURL(){ 
		return txtfldAddress.getText();
		}
	
	//----  Overloaded goToURL methods  ----//
	/**
	 * Method to load the URL in the textfield of the address bar.
	 * @param wv WebView associated with WebPage
	 */
	public static void goToURL(WebView wv) {
		wv.getEngine().load(getCurrentURL());
	}
	/**
	 * Method to load a String URL and set it as the address bar.
	 * @param URL String to be passed into the address bar.
	 */
	public static void goToURL(String URL)  {		
			setURL(URL);
			btnGo.fire();
		}

	
	/*************** Bookmarks Methods ***************/
	
	/**
	 * Method to get the ArrayList of String used as bookmark URLs.
	 * @return ArrayList
	 */
	public static ArrayList<String> getBookmarks(){return bookmarkURLs;}
	
	/**
	 * Method set an ArrayList of String used as bookmark URLs
	 * @param al ArrayList
	 * 
	 */
	public static void setBookmarks(ArrayList<String> al) { bookmarkURLs = al;}
	
	/**
	 * Method to load saved bookmarks into the Bookmark dropdown menu.
	 * @param mnu Bookmark parent menu.
	 */
	private static void loadBookmarksToMenu(Menu mnu) {
		if (FileUtils.fileExists("bookmarks.web")){
			setBookmarks(FileUtils.getURLsFromFile("bookmarks.web"));
			for (String url: bookmarkURLs)
				addBookmarkToMenu(mnu, url);
		}
	}
	/**
	 * Method to create and load event handlers into bookmark feature options.
	 * @param mnu Bookmark parent menu,
	 * @param URL String of page to be saved.
	 */
	private static void addBookmarkToMenu(Menu mnu, String URL) { 
		bookmarkCM = new CustomMenuItem(new Text(URL));	
		
		bookmarkCM.setHideOnClick(false);
		
		/*	MouseEvents example used to implement the following code.
		 * 	Written by User James_D :
		 *  @ https://stackoverflow.com/questions/20635192/how-to-create-popup-menu
		 * 
		 * 	More info on ContextMenu found    @ 	https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ContextMenu.html
		 * 	More info on CustomMenuItem found @ 	https://docs.oracle.com/javafx/2/api/javafx/scene/control/CustomMenuItem.html
		 *  More info on ArrayList found	  @ 	https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
		 */
		bookmarkCM.getContent().setOnMouseClicked((MouseEvent e) -> { 
			
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				goToURL(URL);
				txtfldAddress.setText(getCurrentURL());
			}
			
			else if (e.getButton().equals(MouseButton.SECONDARY)) {
				
				bcon = new ContextMenu();
				
				mnuItm = new MenuItem("Delete Bookmark");
				mnuItm.setOnAction((ActionEvent ev) -> {
					removeBookmark(URL);
					mnu.hide();
					
				});
				
				bcon.getItems().add(mnuItm);
				bcon.show(topPanel, e.getScreenX(), e.getScreenY());
			}
		});
		if (mnu.getItems().size() == 1)
			mnu.getItems().add(new SeparatorMenuItem());
		mnu.getItems().add(bookmarkCM); // Add new URL to Menu
	}
	
	/**
	 * Method to delete selected bookmark. Requires session restart to display changes.
	 * @param URL String of the page to be removed.
	 */
	private static void removeBookmark(String URL) {
		getBookmarks().remove(URL);
		
	}
	
	/************* History Methods ***************/
	
	/**
	 * Method to build and load event handlers into the History Panel
	 * @param wv WebView associated with WebPage
	 * @return HBox containing Label and Forward/Backward buttons.
	 */
	public static HBox createHistBar(WebView wv) {
		
		/*
		 * Created using the provided example 
		 * @ https://stackoverflow.com/questions/18928333/how-to-program-back-and-forward-buttons-in-javafx-with-webview-and-webengine
		 * 
		 */
		
		Label histLbl = new Label("Session History");
		histLbl.setPadding(new Insets(8,8,8,8));
		
		histBack = new Button("\u23F4");
		
		
		histBack.setOnAction((ActionEvent e) -> 
		Platform.runLater(() -> {
			if (wv.getEngine().getHistory().getCurrentIndex() -1 >= 0) {  
				wv.getEngine().getHistory().go(-1);
			}
		}));
		
		
		histNext = new Button("\u23F5");
	
		histNext.setOnAction((ActionEvent e) -> 
			Platform.runLater(()-> {
			if (wv.getEngine().getHistory().getCurrentIndex() +1 < wv.getEngine().getHistory().getEntries().size()) { 
				wv.getEngine().getHistory().go(+1);
			}
		}));
	
		
		HBox hbx = new HBox();
		hbx.setMaxHeight(30);
		hbx.getChildren().addAll(histLbl, histBack, histNext);
			
		return hbx;
	}
	/**
	 * Method to load session history into a ListView.
	 * @param wv WebView associated with WebPage.
	 */
	public static void buildHistory(WebView wv) {
		history = wv.getEngine().getHistory().getEntries();
		
	}
	
	/************* Code Display Methods ************/
	
	/**
	 * Method to build the Display Code panel.
	 * @param wv WebView Associated with WebPage.
	 */
	public static void createCodeDisplay(WebView wv) {
		
		Label cdLbl = new Label(" Web Page Code Display");
		
		cdTxt = new TextArea(displayCode(wv));
		cdTxt.setMinHeight(280);
		cdTxt.setWrapText(true);
		
		cdScroll = new ScrollPane(cdTxt);
		cdScroll.setMinViewportHeight(300);
		cdScroll.setFitToWidth(true);
		
		codeBox = new VBox();
		
		codeBox.getChildren().addAll(cdLbl, cdScroll);
		
		
	}
	
	/**
	 * Method to read & return the HTML/JavaScript/CSS of the current browser page.
	 * @param wv WebView associated with WebPage.
	 * @return String of HTML/JavaScript/CSS.
	 */
	public static String displayCode(WebView wv)   {
		
		//Taken from provided example @ http://www.java2s.com/Tutorials/Java/JavaFX/1500__JavaFX_WebEngine.htm
		
		try {
			/**
			 * 
			 */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            
            StringWriter stringWriter = new StringWriter();
            
            transformer.transform(new DOMSource(wv.getEngine().getDocument()),
                new StreamResult(stringWriter));
           
            xml = stringWriter.getBuffer().toString();
            
            
          } catch (Exception e) {}
			
			return xml;

        }
	
}