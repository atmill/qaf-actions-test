package qaf.actions.tests;

import org.testng.annotations.Test;

import com.qmetry.qaf.automation.data.MetaData;
import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.ui.WebDriverTestCase;

import qaf.actions.common.GoogleSearchPage;

/**
 * Tests that users can navigate to Google search page
 * 
 * @author amiller
 */
public class GoogleSearchTests extends WebDriverTestCase {
    @MetaData("{}")
    @Test(groups = {"test"}, description = "Validates that user can navigate to Google search page")
    public void logIntoMPM() {
        CommonStep.get("");
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.verifyOnPage();
    }
}
