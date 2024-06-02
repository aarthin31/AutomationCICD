package mycourse.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//New comments added
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "D:\\chrome driver 120\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		String productName = "ADIDAS ORIGINAL";
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("aarthin31@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("P@ssw0rd");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

		WebElement product = products.stream().filter(myProduct -> myProduct.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group")));
		List<WebElement> country = driver.findElements(By.cssSelector(".ta-item.list-group-item"));
//		for(WebElement mycountry : country)
//		{
//			if(mycountry.getText().equalsIgnoreCase("India"))
//			{
//				mycountry.click();
//				break;
//			}
//		}
		
		country.stream()
		        .filter(mycountry -> mycountry.getText().equalsIgnoreCase("India"))
		        .findFirst()
		        .ifPresent(WebElement::click);
		
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//a[normalize-space()='Place Order'])[1]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hero-primary")));
		
		String confirmMsg = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
	}

}
