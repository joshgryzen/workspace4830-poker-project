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
public class LoginTests {
	private WebDriver driver;
	private String baseUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/home.jsp";
	private String loginUrl = "http://ec2-18-225-5-151.us-east-2.compute.amazonaws.com:8080/poker-project/loginScreen.html";
	
	@Before
	//Initalize the WebDriver instance.
	//Change path/to/chromedriver to the path on your local machine.
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", path/to/chromedriver);
		driver = new ChromeDriver;
	}
	
	@Test
	public void testSuccessfulLogin() {
	    // Provide correct username and password
	    String username = "newmysqlremoteuser";
	    String password = "mypassword";

	    // Simulate the login request and get the response
	    String loginResponse = simulateLogin(username, password);

	    // TODO: Find actual way to assert success and false -> I can't test the servlets because they are down and its late :(
	    assertTrue("Expected success message in login response", loginResponse.contains("success"));
	}

	@Test
	public void testFailedLogin() {
	    // Provide incorrect username or password
	    String username = "incorrect_username";
	    String password = "incorrect_password";

	    // Simulate the login request and get the response
	    String loginResponse = simulateLogin(username, password);

	    // TODO: Find actual way to assert success and false -> I can't test the servlets because they are down and its late :(
	    assertTrue("Expected failure message in login response", loginResponse.contains("failure"));
	}

	
	private String simulateLogin(String username, String password) {
	    // Open the servlet login page
	    driver.get(loginUrl);

	    // Find username and password input fields and submit button
	    WebElement usernameInput = driver.findElement(By.name("uname"));
	    WebElement passwordInput = driver.findElement(By.name("psw"));
	    WebElement loginButton = driver.findElement(By.id("loginButton"));

	    // Enter username and password
	    usernameInput.sendKeys(username);
	    passwordInput.sendKeys(password);

	    // Click login button
	    loginButton.click();
	    
	    String currentUrl = driver.getCurrentUrl();

	    // Temporarily returning the URL -> if this works maybe it is sufficient
	    // TODO: try and get some sort of status code back and return success / failure
	    return currentUrl;
	}
	
	@After
	//Close down the driver after the test is completed. 
	public void tearDown() {
		driver.quit()
	}
}