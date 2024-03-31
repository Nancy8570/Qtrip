package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import qtriptest.*;


public class AdventurePage {
    RemoteWebDriver driver;

    @FindBy(id = "duration-select")
    WebElement durationFilter;

    @FindBy(id = "category-select")
    WebElement categoryFilter;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearDuration;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearCategory;

    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void setFilterValue(String value) throws InterruptedException {
        SeleniumWrapper.click(durationFilter,this.driver);
        Select FilterDropdown = new Select(durationFilter);
        FilterDropdown.selectByVisibleText(value);
    }

    public void setCategoryValue(String value) throws InterruptedException {
        SeleniumWrapper.click(categoryFilter,this.driver);
        Select FilterDropdown = new Select(categoryFilter);
        FilterDropdown.selectByVisibleText(value);
    }

    public int getResultCount() {
        List<WebElement> resultGrid = driver.findElements(By.xpath("//div[@id='data']/div"));
        return resultGrid.size();
    }

    public void selectAdventure(String adventureName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement adventure = driver.findElement(By.xpath(String.format("//h5[text()='%s']", adventureName)));
        SeleniumWrapper.click(adventure,this.driver);
    }

    public void clearFilters() throws InterruptedException {
        SeleniumWrapper.click(clearCategory,this.driver);
        SeleniumWrapper.click(clearDuration,this.driver);
    }
}
