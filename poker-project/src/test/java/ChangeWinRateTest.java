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
public class ChangeWinRateTest {
	private WebDriver driver;
	private String baseUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/home.jsp";
	private String previousWinRateText:
		
	@Before
	//Initalize the WebDriver instance.
	//Change path/to/chromedriver to the path on your local machine.
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", path/to/chromedriver);
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
		
		//Now we want to get the win rate text.
		WebElement winRateElement = driver.findElement(By.xpath("//div[contains(text(), 'Winrate:']"));
		previousWinRateText = winRateElement.getText();
		
		//Next we want to return to the insert cards page. That means going home from here first.
		WebElement returnHomeLink = driver.findElement(By.linkText"Return Home"));
		returnHomeLink.click();
		
		//Now back to insert cards using that hyperlink.
		WebElement insertCardsLink = driver.findElement(By.linkText("Insert Cards"));
		insertCardsLink.click();
		
		//Now we resubmit the entire form changing only the hand.
		WebElement handInput = driver.findElement(By.name("Hand:"));
		handInput.sendKeys("AH+KH");
		
		WebElement flopInput = driver.findElement(By.name("Flop:"));
		handInput.sendKeys("4H+3H+2H");
		
		WebElement turnInput = driver.findElement(By.name("Turn:"));
		handInput.sendKeys("2");
		
		WebElement playersInput = driver.findElement(By.name("Players:"));
		handInput.sendKeys("3");
		
		//Sumbit the form data
		WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
		submitButton.click();
		
		//Now we get the new win rate text.
		WebElement winRateElement = driver.findElement(By.xpath("//div[contains(text(), 'Winrate:']"));
		String newWinRateText = winRateElement.getText();
		
		//Lastly, we simply test that they aren't the same.
		assertNotEquals("Expected new win rate text to not match previous", previousWinRateText, newWinRateText);
		
		
	}
	@After
	//Close down the driver after the test is completed. 
	public void tearDown() {
		driver.quit()
	}
}