package qaf.actions.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.qmetry.qaf.automation.core.ConfigurationManager;


/**
 * Dynamically sets up the TestNG configuration for running tests
 * 
 * @author amiller
 */
public class TestSuiteAlterer implements IAlterSuiteListener {
    /**
     * Capitalizes the first letter of the give word {@code word}
     * 
     * @param word The word whose first letter needs to be capitalized
     * @return The given word with its first letter capitalized
     */
    private String capitalizeFirstLetter(String word) {
        String firstLtr = word.substring(0, 1);
        return word.replace(firstLtr, firstLtr.toUpperCase());
    }
    
    /**
     * Sets up a test by creating a test for the given suite
     * 
     * @param suite The suite to add the new test to
     */
    private void setupTest(XmlSuite suite) {
        setupTest(suite, null, null);
    }
    
    /**
     * Sets up a test by creating a test for the given suite
     * 
     * @param suite The suite to add the new test to
     * @param groupName The name of the group for the test; can be null
     * @param testName The name of the test; can be null
     */
    private void setupTest(XmlSuite suite, String groupName, String testName) {
        XmlTest newTest = new XmlTest(suite);
        
        // Gets browser, env, and group variables
        String browser = System.getProperty("browser").toLowerCase();
        String env = System.getProperty("env").toLowerCase();
        
        String group = groupName;
        if (group == null) {
            group = System.getProperty("group").toLowerCase();
        }
        
        // Sets test name
        if (testName == null) {
            newTest.setName(
                    capitalizeFirstLetter(browser) + " " +
                    capitalizeFirstLetter(env) + " " +
                    capitalizeFirstLetter(group) + " Tests");
        } else {
            newTest.setName(testName);
        }
        
        // Sets driver and env.resources parameters
        HashMap<String, String> parameters = new HashMap<String, String>();
        browser = browser.equalsIgnoreCase("ie") ? "iexplore" : browser;
        //parameters.put("driver.name", browser + "RemoteDriver");
        parameters.put("driver.name", browser + "Driver");
        parameters.put("env.resources", "resources/common");
        newTest.setParameters(parameters);
        
        // Sets included group
        newTest.addIncludedGroup(group);
        // Sets excluded group
        newTest.addExcludedGroup(env + "skip");
        
        // Sets test package
        String testPkg = ConfigurationManager.getBundle().getString("test.pkg");
        ArrayList<XmlPackage> packages = new ArrayList<XmlPackage>();
        packages.add(new XmlPackage(testPkg));
        newTest.setPackages(packages);
    }

    @Override
    public void alter(List<XmlSuite> suites) {
        for (XmlSuite suite : suites) {
            setupTest(suite);
        }
    }
}
