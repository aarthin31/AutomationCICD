package mycourse.pageObjmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mycourse.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css = ".ng-animating")
	WebElement toastClose;

	By waitforProducts = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getPrdocutList() {
		waitForElementToAppear(waitforProducts);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getPrdocutList().stream()
				.filter(myProduct -> myProduct.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return product;

	}

	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(toastClose);
	}

}
