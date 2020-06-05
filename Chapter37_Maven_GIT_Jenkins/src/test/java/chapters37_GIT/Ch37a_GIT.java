package chapters37_GIT;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Ch37a_GIT {
	
	String driverPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";
	
	String url = "http://www.demo.guru99.com/V4/";
	String userID = "mngr26593";
	String password = "ishal!12";
	
	
	WebDriver driver;
	
  
	@Test(priority=0)
	public void verifyLaunchPage() {
		
		WebElement launchPageHeading = driver.findElement(By.xpath("//*[text()='Guru99 Bank']"));
		
		Assert.assertTrue(launchPageHeading.isDisplayed(), "Home Page heading is not displayed");
		
		System.out.println("Homepage heading: " + launchPageHeading.getText());

	}
	
	@Test(priority=1)
	public void enterCredentials() throws InterruptedException {
		
		WebElement idBox = driver.findElement(By.xpath("//input[@name='uid']"));
		WebElement pwBox = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.name("btnLogin"));
		
			
		idBox.sendKeys(userID);
		pwBox.sendKeys(password);
		idBox.click();
		
		Thread.sleep(1000);

		login.click();
		
		System.out.println("Clicked on Login Button");
		
		Thread.sleep(3000);
	}
	
	
	@Test(priority=2)
	public void showID() throws InterruptedException {
		
		WebElement managerID = driver.findElement(By.xpath("//*[contains(text(),'Manger Id :')]"));
		
		Thread.sleep(1000);
		
		Assert.assertTrue(managerID.isDisplayed(), "Manager ID is not displayed in the dashboard");
		
		System.out.println(managerID.getText());
		
	}
	
	
	@Test(priority=3)
	public void VerifyHyperlinks() {
		
		WebElement newCustLink = driver.findElement(By.xpath("//a[@href='addcustomerpage.php']"));
		WebElement fundLink = driver.findElement(By.xpath("//a[@href='FundTransInput.php']"));
		
		Assert.assertTrue(newCustLink.isEnabled(), "Add new customer link is not enabled");
		System.out.println("Add new customer link is enabled");
		
		Assert.assertTrue(fundLink.isEnabled(), "Fund Transfer link is not available");
		System.out.println("Fund Transfer is available");
		
	}
	
	
	
	@Test(priority=4)
	public void logOut() throws InterruptedException {
		
		WebElement logOut = driver.findElement(By.linkText("Log out"));
		
		System.out.println("Logging Out");
		logOut.click();
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		
	}
	
	
	@BeforeTest
	public void initialize() {
		
		System.setProperty("webdriver.gecko.driver", driverPath);
    	
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(url);
		
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		
		System.out.println("Closing Browser...");
		Thread.sleep(3000);
		driver.quit();
	}
	
}