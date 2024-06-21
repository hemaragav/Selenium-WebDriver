package Week4.Assingment;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.testng.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Amazon {
	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/");

		WebElement findElement = driver.findElement(By.id("twotabsearchtextbox"));
		findElement.click();

		findElement.sendKeys("oneplus 9 pro", Keys.ENTER);

		WebElement mobile = driver.findElement(By.xpath("(//div[@class='puisg-col-inner'])[1]"));
		String mobilerate = mobile.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText();
		System.out.println("Mobilerate :" + mobilerate);

		Actions act = new Actions(driver);
		act.scrollByAmount(500, 500);

		String rating = mobile.findElement(By.xpath("//span[.='8']")).getText();
		System.out.println("Rating for the mpbile : " + rating);

		driver.findElement(
				By.xpath("//span[.='(Refurbished) OnePlus 9 Pro 5G (Morning Mist, 12GB RAM, 256GB Storage)']")).click();

		Thread.sleep(3000);

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parenttwindow = it.next();
		String childwindow = it.next();
		driver.switchTo().window(childwindow);

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:\\Karthik\\Hema\\Learning\\Java\\JavaSelenium\\screenshot\\image.png"));
		Thread.sleep(3000);

		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);

		String subTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		String[] split = subTotal.split("\\.");
		String expectedSubTotal = split[0];

		System.out.println("SubTotalrate: " + expectedSubTotal);

		if (expectedSubTotal.contains(mobilerate)) {

			System.out.println("the cost of the mobiles are matching");
		} else {
			System.out.println("the cost of the mobiles are not matching");

		}

		driver.close();
		driver.switchTo().window(parenttwindow);
		driver.quit();

	}
}