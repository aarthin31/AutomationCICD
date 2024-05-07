package mycourse.pageObjmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mycourse.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(xpath = "(//a[normalize-space()='Place Order'])[1]")
	WebElement placetheOrder;
	
	By waitForCountry = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(waitForCountry);
		selectCountry.click();
	}
	
	public confirmationPage submitOrder() throws InterruptedException
	{
		AbstractComponents scroll = new AbstractComponents(driver);
		scroll.pageScroll();
		placetheOrder.click(); 
		return new confirmationPage(driver);
	}
	
//	@FindBy(css="input[placeholder='Select Country']")
//	WebElement selectCountry;
//	
//	@FindBy(css=".ta-item.list-group-item")
//	List<WebElement> country;
//
//	By waitforCountryList = By.cssSelector(".ta-results.list-group");
//	
//	//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
//	
//	public void enterCountry(String myCountry)
//	{
//		selectCountry.sendKeys(myCountry);
//	}
//	
//	public List<WebElement> getCountry()
//	{
//		waitForElementToAppear(waitforCountryList);
//		return country;
//	}
//	
//	public void getCountryByName(String myCountry) throws InterruptedException
//	{
//		pageScroll();
//		getCountry().stream().filter(c -> c.getText().equalsIgnoreCase(myCountry)).findFirst().ifPresent(WebElement::click);
//	}
}
