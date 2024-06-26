package OrikanTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing
{
	WebDriver driver;


	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Desktop\\Testing\\chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://orikan-ui-automation-test.azurewebsites.net/");
		Thread.sleep(2000);

		//Registration	
		driver.findElement(By.xpath("//*[@id='emailAddress']")).sendKeys("ram@orikan.com");

		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Test@1234");

		driver.findElement(By.xpath("//*[@id='confirmPassword']")).sendKeys("Test@1234");

		driver.findElement(By.xpath("//button[@class='wizard-button primary']")).click();



		// Contact Details:

		driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("ram");

		driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("rap");

		driver.findElement(By.xpath("//*[@id='addressLine1']")).sendKeys("11-1/24");

		driver.findElement(By.xpath("//*[@id='addressLine2']")).sendKeys("cars");

		driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("500061");

		driver.findElement(By.xpath("//*[@id='city']")).sendKeys("newyork");
		Thread.sleep(2000);
		Select State = new Select(driver.findElement(By.xpath("//select[@id='state']")));
		State.selectByVisibleText("New South Wales");	
		driver.findElement(By.xpath("(//button[@class='wizard-button primary'])[1]")).click();


		// Payment card details

		driver.findElement(By.xpath("//*[@id='cardHolderName']")).sendKeys("ramrap");
		driver.findElement(By.xpath("//*[@id='cardTypeMastercard']")).click();
		driver.findElement(By.xpath("//*[@id='cardNumber']")).sendKeys("4586782365981475");
		driver.findElement(By.xpath("//*[@id='cardCVV']")).sendKeys("564");
		Thread.sleep(2000);
		Select CardExpiryMonth = new Select(driver.findElement(By.xpath("//select[@id='cardExpiryMonth']")));
		CardExpiryMonth.selectByVisibleText("June");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='cardExpiryYear']")).sendKeys("2035");
		driver.findElement(By.xpath("(//button[@class='wizard-button primary'])")).click();

		// Term&conditions
		WebElement textArea = driver.findElement(By.xpath("//textarea[@id='termsAndConditions']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", textArea);

		//	textArea.sendKeys(" "); // Add a space or any character to move the cursor to the end

		driver.findElement(By.xpath("//input[@id='agreedToTerms']")).click();

		driver.findElement(By.xpath("//button[@class='wizard-button primary']")).click();

		//Submit page

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(100));
		WebElement successMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'has been successfully registered')]")));
		if (successMessage.getText().contains("has been successfully registered")) {
			System.out.println("Successful registration");
		} else {
			System.out.println("Registration failed or success message not found");
		}

		driver.close();
	}
}
