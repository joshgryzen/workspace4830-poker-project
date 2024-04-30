import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

//Fourth test. Now that we're at the insert cards page, do we get a win rate?
public class WinRateTest {
	private WebDriver driver;
	private String baseUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/home.jsp";
	
	@Before
	//Initalize the WebDriver instance.
	//Change path/to/chromedriver to the path on your local machine.
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\cjgry\\Desktop\\selenium-java-4.20.0\\selenium-chrome-driver-4.20.0.jar");
		driver = new ChromeDriver;
	}
	@Test
	public void testWinRate() {
		//Open the website.
		driver.get(baseUrl);
		
		//Next, we find the "Insert Cards" hyperlink.
		WebElement insertCardsLink = driver.findElement(By.linkText("Insert Cards"));
		
		//Next, we want to click on this link.
		insertCardsLink.click();
		
		//Next, fill in the form fields with data.
		WebElement handInput = driver.findElement(By.name("Hand:"));
		handInput.sendKeys("AS+KS");
		
		WebElement flopInput = driver.findElement(By.name("Flop:"));
		handInput.sendKeys("4H+3H+2H");
		
		WebElement turnInput = driver.findElement(By.name("Turn:"));
		handInput.sendKeys("2");
		
		WebElement playersInput = driver.findElement(By.name("Players:"));
		handInput.sendKeys("3");
		
		//Sumbit the form data
		WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
		submitButton.click();
		
		//Now we just want to check that the winrate exists, i.e. the field is non-empty.
		WebElement winRateElement = driver.findElement(By.xpath("//div[contains(text(), 'Winrate:']"));
		String winRateText = winRateElement.getText();
		assertTrue("Expected win rate text to be non-empty",!winRateText.isEmpty());
		
		
	}
	@After
	//Close down the driver after the test is completed. 
	public void tearDown() {
		driver.quit()
	}
}