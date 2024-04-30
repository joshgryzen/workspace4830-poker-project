import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class VisualInputTooMany {
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
  public void testVisualInputTooMany() throws Exception {
    driver.get("http://localhost:8080/poker-project/cardgame.html");
    driver.findElement(By.id("rectangleFlop3")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img[2]")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img[3]")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img[4]")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img[5]")).click();
    driver.findElement(By.xpath("//div[@id='modalContent']/div/img[6]")).click();
    assertEquals("You can only select up to 5 cards for flop mode.", closeAlertAndGetItsText());
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
