package mycourse.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import mycourse.pageObjmodel.CartPage;
import mycourse.pageObjmodel.CheckOutPage;
import mycourse.pageObjmodel.LoginPage;
import mycourse.pageObjmodel.ProductCatalog;
import mycourse.pageObjmodel.confirmationPage;

public class SubmitOrder {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "D:\\chrome driver 120\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		String productName = "ADIDAS ORIGINAL";
		
		LoginPage login = new LoginPage(driver);
		login.goTo();
		
		
		ProductCatalog addProduct = login.loginApplication("aarthin31@gmail.com", "P@ssw0rd");
		@SuppressWarnings("unused")
		List<WebElement> products = addProduct.getPrdocutList();
		addProduct.addProductToCart(productName);
		
//		login.goTocartPage();
//		CartPage cartPage = new CartPage(driver);
		
		CartPage cartPage = login.goTocartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		confirmationPage cnfmpage = checkOut.submitOrder();
		String confirmMsg = cnfmpage.getMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
//		String myCountry = "ind";
//		CheckOutPage checkOut = new CheckOutPage(driver);
//		checkOut.enterCountry(myCountry);
//		List<WebElement> country = checkOut.getCountry();
//		checkOut.getCountryByName(myCountry);
	}

}
