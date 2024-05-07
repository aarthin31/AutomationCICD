package mycourse.pageObjmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mycourse.AbstractComponents.AbstractComponents;

public class confirmationPage extends AbstractComponents {

WebDriver driver;
	
	public confirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//String confirmMsg = driver.findElement(By.className("hero-primary")).getText();
	@FindBy(className = "hero-primary")
	WebElement confirmMsg;
	
	public String getMsg()
	{
		return confirmMsg.getText();
	}
}
