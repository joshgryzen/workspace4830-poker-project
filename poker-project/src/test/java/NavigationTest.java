import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

//Second test, can we navigate from the home page to the insert cards page?
public class NavigationTest {
	private WebDriver driver;
	private String baseUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/home.jsp";
	private String loginUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/loginScreen.html";
	private String signupUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/signUpPage.html";

	
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
		
		//Now that we've clicked on the link, let's check the URL. 
		assertTrue("Expected URL contains 'insert'", currentUrl.contains("insert"));
	}
	
	@Test
	public void testNavigationToLoginFromHome() {
		//Open the website.
		driver.get(baseUrl);
		
		//Next, we find the "Log In" hyperlink.
		WebElement loginLink  = driver.findElement(By.linkText("Log In"));
		
		//Next, we want to click on this link.
		loginLink.click();
		
		//Now that we've clicked on the link, let's check the URL. 
		assertTrue("Expected URL contains 'loginScreen'", currentUrl.contains("loginScreen"));
	}
	
	@Test
	public void testNavigationToSignUpFromHome() {
		//Open the website.
		driver.get(baseUrl);
		
		//Next, we find the "Sign Up" hyperlink.
		WebElement signupLink  = driver.findElement(By.linkText("Sign Up"));
		
		//Next, we want to click on this link.
		signupLink.click();
		
		//Now that we've clicked on the link, let's check the URL. 
		assertTrue("Expected URL contains 'signUpPage'", currentUrl.contains("signUpPage"));
	}
	
	@Test
	public void testNavigationToSignUpFromLogin() {
		//Open the website.
		driver.get(loginUrl);
		
		//Next, we find the "Sign Up" hyperlink.
		WebElement signupLink  = driver.findElement(By.linkText("Sign Up Page!"));
		
		//Next, we want to click on this link.
		signupLink.click();
		
		//Now that we've clicked on the link, let's check the URL. 
		assertTrue("Expected URL contains 'signUpPage'", currentUrl.contains("signUpPage"));
	}
	
	@Test
	public void testNavigationToLoginFromSignUp() {
		//Open the website.
		driver.get(signupUrl);
		
		//Next, we find the "Sign Up" hyperlink.
		WebElement backLink  = driver.findElement(By.linkText("Go Back to Home Screen"));
		
		//Next, we want to click on this link.
		backLink.click();
		
		//Now that we've clicked on the link, let's check the URL. 
		assertTrue("Expected URL contains 'loginScreen'", currentUrl.contains("loginScreen"));
	}
	
	@After
	//Close down the driver after the test is completed. 
	public void tearDown() {
		driver.quit()
	}
}