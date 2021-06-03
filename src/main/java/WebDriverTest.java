import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class WebDriverTest {


    public static void ebay() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ryan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless"); // uncomment this after you're done

        // create new driver
        WebDriver ebayDriver = new ChromeDriver(options);

        ebayDriver.get("http://www.ebay.com");

        // find search bar
        WebElement searchBar = ebayDriver.findElement(By.id("gh-ac"));
        // type iphone in search bar
        searchBar.sendKeys("iphone");

        // click on search
        WebElement searchButton = ebayDriver.findElement(By.id("gh-btn"));
        searchButton.click();

        System.out.println("Letting page load...");

        // let the page load
        // using implicit waits
        ebayDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        System.out.println("Finding products");

        // output product description and price for all products displayed on page one to a text file
        // class="s-item    s-item__sep-on-bottom      " is a sponsored item!!!!!

        List<WebElement> products = ebayDriver.findElements(By.xpath("//li[@class='s-item          ']"));
        // sample output
        System.out.println(products.size() + " products found");
        // iterate through the list and get the name and price
        // name is located in class="s-item__title"
        // prices is located in class="s-item__price"

        // Apple iphone 7/7 plus 32GB/128GB Unlocked Verizon at&t Smartphone  - $114.00 to $357.00
        try {
            // open a file
            FileWriter myWriter = new FileWriter("ebay.txt");
            // write to the file
            for(WebElement product : products) {
                String name = product.findElement(By.className("s-item__title")).getText();
                String price = product.findElement(By.className("s-item__price")).getText();
                myWriter.write(name + " - " + price + "\n");
            }
            // close the file
            myWriter.close();
            System.out.println("[ebay] Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("[ebay] An error occurred.");
            e.printStackTrace();
        }
        ebayDriver.quit();
    }


    public static void main(String[] args) throws InterruptedException {
        ebay();

    }
}
