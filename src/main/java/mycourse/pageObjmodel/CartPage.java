package mycourse.pageObjmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mycourse.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement CheckOut;

	By cartWait = By.cssSelector(".cartSection h3");
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//	Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	public Boolean VerifyProductDisplay(String productName)
	{
		waitForElementToAppear(cartWait);
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	public CheckOutPage goTocheckOut()
	{
		CheckOut.click();
		return new CheckOutPage(driver);
	}
}
