import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class MonteCarloBad {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testMonteCarloBad() throws Exception {
    driver.get("http://localhost:8080/poker-project/insert.html");
    driver.findElement(By.name("hand")).click();
    driver.findElement(By.name("hand")).clear();
    driver.findElement(By.name("hand")).sendKeys("2S+7H");
    driver.findElement(By.name("flop")).click();
    driver.findElement(By.name("flop")).clear();
    driver.findElement(By.name("flop")).sendKeys("JD+AD+KD");
    driver.findElement(By.name("turnNumber")).click();
    driver.findElement(By.name("turnNumber")).clear();
    driver.findElement(By.name("turnNumber")).sendKeys("2");
    driver.findElement(By.name("playerNumber")).click();
    driver.findElement(By.name("playerNumber")).clear();
    driver.findElement(By.name("playerNumber")).sendKeys("4");
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
//    driver.get("http://localhost:8080/poker-project/CardInsertServlet");
    //Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().contains("Winrate: 0.0"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
