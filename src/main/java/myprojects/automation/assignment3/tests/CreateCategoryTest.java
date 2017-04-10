package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.utils.BaseScript;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Alexandr on 09.04.2017.
 */
public class CreateCategoryTest extends BaseScript {




    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = getConfiguredDriver();

        String a = "Test category";

        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0");

        WebElement emailInput = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='passwd']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@name='submitLogin']"));

        emailInput.sendKeys("webinar.test@gmail.com");
        passwordInput.sendKeys("Xcg7299bnSmMuRLp9ITw");
        submitButton.submit();

        new WebDriverWait(driver, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='subtab-AdminCatalog']")));

        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//li[@id='subtab-AdminCatalog']")));
        builder.moveToElement(driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']")));
        builder.click(driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']")));
        builder.perform();

        new WebDriverWait(driver, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='process-icon-new']")));

        WebElement createCategory = driver.findElement(By.xpath("//i[@class='process-icon-new']"));
        createCategory.click();

        new WebDriverWait(driver, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name_1']")));

        WebElement categoryName = driver.findElement(By.xpath("//input[@id='name_1']"));
        categoryName.sendKeys(a);

        WebElement saveCategory = driver.findElement(By.xpath("//i[@class='process-icon-save']"));
        saveCategory.submit();

        new WebDriverWait(driver, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

        WebElement category = driver.findElement(By.xpath("//li[@class='breadcrumb-current']"));
        category.click();

        new WebDriverWait(driver, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='categoryFilter_name']")));

        WebElement nameFilter = driver.findElement(By.xpath("//input[@name='categoryFilter_name']"));
        nameFilter.sendKeys(a);
        nameFilter.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Test category')]")));

        driver.quit();
    }
}

