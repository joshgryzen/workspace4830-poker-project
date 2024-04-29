import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

//Third test, can we navigate from the insert cards page back to the home page?
public class BackToHomePageTest {
	private WebDriver driver;
	private String baseUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/home.jsp";
	
	@Before
	//Initalize the WebDriver instance.
	//Change path/to/chromedriver to the path on your local machine.
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", path/to/chromedriver);
		driver = new ChromeDriver;
	}
	@Test
	public void testNavigationToInsertCardsPage() {
		//Open the website.
		driver.get(baseUrl);
		
		//Next, we find the "Insert Cards" hyperlink.
		WebElement insertCardsLink = driver.findElement(By.linkText("Insert Cards"));
		
		//Next, we want to click on this link.
		insertCardsLink.click();
		
		//Now we want to find the "Home" hyperlink.
		WebElement homeLink = driver.findElement(By.linkText("Home"));
		
		//Next, click on that hyperlink.
		homeLink.click();
		
		//Next, grab the URL.
		currentUrl = driver.getCurrentUrl();
		
		//Now test that this URL is the home page.
		assertTrue("Expected URL contains 'home'", currentUrl.contains("home"));
		
		
	}
	@After
	//Close down the driver after the test is completed. 
	public void tearDown() {
		driver.quit()
	}
}