package Week4.Assingment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class BigBasket {
	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.bigbasket.com/");

		driver.findElement(By.xpath("(//span[.='Shop by'])[2]")).click();
		Thread.sleep(2000);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.linkText("Foodgrains, Oil & Masala"))).perform();

		act.moveToElement(driver.findElement(By.linkText("Rice & Rice Products"))).perform();

		act.moveToElement(driver.findElement(By.linkText("Boiled & Steam Rice"))).click().perform();
		Thread.sleep(3000);

		driver.findElement(By.xpath("(//input[@placeholder='Search here'])[1]")).sendKeys("bb Royal");
		driver.findElement(By.id("i-BBRoyal")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[.='Tamil Ponni Boiled - Rice']")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parenttwindow = it.next();
		String childwindow = it.next();
		driver.switchTo().window(childwindow);
		Thread.sleep(2000);

		WebElement list = driver.findElement(By.xpath("//span[.='5 kg']"));
		act.moveToElement(list).perform();
		list.click();

		WebElement price = driver.findElement(By.xpath("//td[contains(.,'Price')]"));
		act.moveToElement(price).perform();

		System.out.println(price.getText());
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//button[.='Add to basket'])[1]")).click();
		WebElement toastmsg = driver
				.findElement(By.xpath("//p[.='An item has been added to your basket successfully']"));
		System.out.println(toastmsg.getText());

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:\\Karthik\\Hema\\Learning\\Java\\JavaSelenium\\screenshot\\image1.png"));

		driver.close();
		driver.switchTo().window(parenttwindow);
		driver.quit();

	}
}
