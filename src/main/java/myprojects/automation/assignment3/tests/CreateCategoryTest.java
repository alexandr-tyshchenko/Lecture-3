package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.utils.BaseScript;
import myprojects.automation.assignment3.utils.EventHandler;
import myprojects.automation.assignment3.utils.GeneralActions;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexandr on 09.04.2017.
 */
public class CreateCategoryTest extends BaseScript
{
    public static void main(String[] args) throws InterruptedException
    {
        // TODO prepare driver object

        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        String testCategory= "Test Category";
        driver.register(new EventHandler());

        GeneralActions actions = new GeneralActions(driver);

        driver.get(Properties.getBaseAdminUrl());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        actions.login("webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");

        actions.createCategory(testCategory);

        WebElement filterName = driver.findElement(By.xpath("//input[@name='categoryFilter_name']"));
        filterName.sendKeys(testCategory);
        filterName.sendKeys(Keys.ENTER);
        actions.waitForContentLoad();

        List<WebElement> categories = driver.findElements(By.xpath("//tr//td[@class='pointer'][1]"));
        for (WebElement element : categories)
        {
            if (element.getText().equals(testCategory))
            {
                System.out.println("Category was created successfully");
            }
        }

        driver.close();
    }
}

