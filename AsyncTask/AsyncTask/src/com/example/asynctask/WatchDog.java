package com.example.asynctask;

import android.content.Context;
import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class WatchDog extends UiAutomatorTestCase {

	
	private static final String LOG_TAG = "WatcherDemoEx1";
	private static final String MYOKCANCELDIALOGWATCHER_STRING = "OkCancelDialogWatcher";
	private Context context;
	
	public WatchDog(Context context) {
        this.context = context;
    }
		
	public void testWatcherDemoTestExample1() throws UiObjectNotFoundException {
		Log.w(LOG_TAG, "Starting our test!");

		// Simulate a short press on the HOME button based on the sample test case:
		//
		// http://developer.android.com/tools/testing/testing_ui.html#sample
		//
//		getUiDevice().pressHome();
//		
//		// We're now on the home screen. Launch the All Apps screen.
//		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
//		
//		// Simulate a click to bring up the All Apps screen.
//		allAppsButton.clickAndWaitForNewWindow();
//		
//		// In the All Apps screen, the Snow Report app should be in the Apps tab
//		// assuming it is installed prior to starting this test
//		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));
//		
//		// Simulate a click to enter the Apps tab.
//		appsTab.click();
//		
//		// Next, in the apps tabs, we can simulate a user swiping until they 
//		// come across the Snow Report app icon. Again, swiped from the 
//		// sample test case.
//		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
//		
//		// Set the swiping mode to horizontal (the default is vertical)
//		// This is only compatible with API lvl 17. Short of that it will crash
//		// with a "method not found" failure.
//		appViews.setAsHorizontalList();
//		
//		// Create a UiSelector to find the Snow Report app and simulate
//		// a user click to launch the app.
//		UiObject apiDemoApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "API Demos");
//		apiDemoApp.clickAndWaitForNewWindow();
//		
		/////////////////////////////////////////////////////////////////////
		// This concludes the section devoted to simply launching the app. //
		/////////////////////////////////////////////////////////////////////

		// Define watcher
		UiWatcher okCancelDialogWatcher = new UiWatcher() {
			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith("the process"));
				if(okCancelDialog.exists()){
					Log.w(LOG_TAG, "Found the example OK/Cancel dialog");
					UiObject okButton = new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
					try {
						okButton.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return (okCancelDialog.waitUntilGone(25000));
				}
				return false;
			}
		};
		
		// Register watcher
		UiDevice.getInstance().registerWatcher(MYOKCANCELDIALOGWATCHER_STRING, okCancelDialogWatcher);

		// Run watcher
		UiDevice.getInstance().runWatchers();

		
		/*
		 * This test demonstrates UiAutomator Watchers using the Emulator's
		 * API Demos App/Alert Dialogs
		 * 
		 * With this, the watcher will be set in advance to identify when
		 * an alert dialog is present and cancel it. The test will click
		 * on at least one item in the list.
		 */
		
//		// Get Api Demos list
//		UiCollection apiDemoList = new UiCollection(new UiSelector().className("android.widget.ListView"));
//		
//		// Click on App
//		UiObject appTextView = apiDemoList.getChildByText(new UiSelector()
//			.className(android.widget.TextView.class.getName()),
//			"App");
//		
//		appTextView.clickAndWaitForNewWindow();
//		
//		// Get App demo list
//		UiCollection appDemoList = new UiCollection(new UiSelector().className("android.widget.ListView"));
//		
//		// Click on Alert Dialogs
//		UiObject alertDialogTextView = appDemoList.getChildByText(new UiSelector()
//			.className(android.widget.TextView.class.getName()), "Alert Dialogs");
//		
//		alertDialogTextView.clickAndWaitForNewWindow();
//
//		// Click on button with text "OK Cancel dialog with a message"
//		UiObject okCancelDialogButton1 = new UiObject(new UiSelector().text("OK Cancel dialog with a message"));
//		okCancelDialogButton1.click();
//		
//		// Click on button with text "OK Cancel dialog with a long message"
//		// This is where the watcher should save our bacon. Yes, this is a poorly written test case, I know.
//		// Just go with me here, I'm making a point about Watchers. If the dialog from the previous button 
//		// press is still in place, this new selector will fail to find an object containing that text.
//		// Because the watcher is there, the dialog gets closed and we're all good.
//		
//		UiObject okCancelDialogButton2 = new UiObject(new UiSelector().text("OK Cancel dialog with a long message"));
//		okCancelDialogButton2.click();
		
	}

}



