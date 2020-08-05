package qaf.actions.common;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.annotations.PageIdentifier;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;

public class GoogleSearchPage extends WebDriverBaseTestPage<WebDriverTestPage> {

    @PageIdentifier
    @FindBy(locator = "google.search.field")
    private QAFWebElement googleSearchField;
    
    
    @QAFTestStep(description = "verify on Google search page")
    public void verifyOnPage() {
        Validator.verifyTrue(
                googleSearchField.isPresent(),
                "User is not on Google search page",
                "User is on Google search page"
        );
    }
    
    @Override
    protected void openPage(PageLocator locator, Object... args) {
        // TODO Auto-generated method stub
        
    }

}
