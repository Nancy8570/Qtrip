package qtriptest.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.UUID;

import qtriptest.*;


public class RegisterPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedUsername = "";

    @FindBy(id="floatingInput")
    WebElement username_txt_box;

    @FindBy(xpath="(//input[@id='floatingPassword'])[1]")
    WebElement password_text_box;

    @FindBy(xpath="(//input[@id='floatingPassword'])[2]")
    WebElement confirm_password_txt_box;

    @FindBy(xpath="//button[normalize-space()='Register Now']")
    WebElement register_now_button;

    public RegisterPage(RemoteWebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }

    public Boolean registerUser(String username, String password, Boolean makeUserDynamic) throws InterruptedException {

        String test_data_username;
        if (makeUserDynamic)
            // Concatenate the timestamp to string to form unique timestamp
            test_data_username = username + UUID.randomUUID().toString();
        else
            test_data_username = username;

        SeleniumWrapper.sendKeys(username_txt_box,test_data_username);
        SeleniumWrapper.sendKeys(password_text_box,password);
        SeleniumWrapper.sendKeys(confirm_password_txt_box,password);
        SeleniumWrapper.click(register_now_button,this.driver);

        this.lastGeneratedUsername = test_data_username;

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login/")));
        } catch (Exception e) {
            return false;
        }

        return this.driver.getCurrentUrl().endsWith("/login");
    }
    
}
