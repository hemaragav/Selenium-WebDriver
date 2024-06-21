package Week4.Assingment;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesLearning {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[.='Try it']")).click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Hemalatha");
		String text = alert.getText();
		System.out.println(text);
		alert.accept();
		driver.close();

	}

}
