package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class WishlistPage {
    WebDriver driver;
    WebDriverWait wait;

    By login = By.xpath("//a[contains(text(),'Log in')]");
    By emailid = By.xpath("//input[@name='Email']");
    By password = By.xpath("//input[@name='Password']");
    By loginButton = By.xpath("//button[contains(@class,'login')]");
    By computers =(By.xpath("(//a[contains(text(), 'Computers')])[1]"));
    By notebooks = By.linkText("Notebooks");
    By wishlistButton = By.xpath("(//button[contains(@class,'wishlist')])[1]");
    By addToWishlist = By.xpath("(//button[contains(@class,'wishlist')])[1]");
    By wishlistPage = By.xpath("//a[contains(text(),'wishlist')]");
    By wishlistPageVerification= By.xpath("//h1[contains(text(),'Wishlist')]");
    By checkbox= By.xpath("//input[@name='addtocart']");
    By addToCartButton= By.xpath("//button[@name='addtocartbutton']");
    By cartPageVerification= By.xpath("//h1[contains(text(),'Shopping cart')]");
    By logoutButton = By.xpath("//a[contains(@class,'logout')]");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void wishlist() throws IOException {
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//demonop.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        }
        catch (FileNotFoundException e) {e.printStackTrace();
        }XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String email = sheet.getRow(1).getCell(2).getStringCellValue();
        String pass = sheet.getRow(1).getCell(3).getStringCellValue();
        driver.findElement(login).click();
        driver.findElement(emailid).sendKeys(email);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        driver.findElement(computers).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(notebooks));
        driver.findElement(notebooks).click();
        driver.findElement(wishlistButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToWishlist));
        driver.findElement(addToWishlist).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistPage));
        driver.findElement(wishlistPage).click();
        boolean isWishlistDisplayed = driver.findElement(wishlistPageVerification).isDisplayed();
        Assert.assertTrue(isWishlistDisplayed, "Can not add to wishlist");
        driver.findElement(checkbox).click();driver.findElement(addToCartButton).click();
        boolean isCartDisplayed = driver.findElement(cartPageVerification).isDisplayed();
        Assert.assertTrue(isCartDisplayed, "Item not added to cart");
        driver.findElement(logoutButton).click();
    }
}